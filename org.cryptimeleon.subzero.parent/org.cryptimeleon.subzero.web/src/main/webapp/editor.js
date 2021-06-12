// Timer for continuous LaTeX preview updating
var TIMER_INTERVAL = 600;
var timer;

var SELECTION_UPDATE_DELAY = 550;
var TEXT_UPDATE_DELAY = 500;
var DEFAULT_FONT_SIZE = 18;
var MINIMUM_FONT_SIZE = 8;
var MAXIMUM_FONT_SIZE = 40;

var SESSION_ID = Math.random().toString(36).substring(2, 12);
var CODE_RESOURCE_ID = `${SESSION_ID}.code.${DSL_EXTENSION}`;
var LATEX_RESOURCE_ID = `${SESSION_ID}.latex.${DSL_EXTENSION}`;



// Checkmark icon
var CHECK_ICON = "images/check.svg";

// The length of indentation when pressing tab in the code editor
var TAB_SIZE = 2;

// Blank transparent icon
var BLANK_IMAGE = "data:image/gif;base64,R0lGODlhAQABAIAAAAAAAP///yH5BAEAAAAALAAAAAABAAEAAAIBRAA7";

var currentEditorFontSize = DEFAULT_FONT_SIZE;
var currentPreviewFontSize = DEFAULT_FONT_SIZE;
var FONT_SIZE_INCREMENT = 2;

// Stores whether the last key pressed was
var lastCharWasLeftCurlyBrace = false;

// Returns the editor object
function getEditor() {
  return ace.edit("xtext-editor");
}

// Creates the code editor and sets up features
function createEditor(xtext) {
  var editor = xtext.createEditor({
    baseUrl: baseUrl,
    xtextLang: DSL_EXTENSION,
		resourceId: CODE_RESOURCE_ID,
    loadFromServer: false,
    sendFullText: true,
    syntaxDefinition: "syntax-highlighting",
    theme: "ace/theme/monokai",
    selectionUpdateDelay: SELECTION_UPDATE_DELAY,
    textUpdateDelay: TEXT_UPDATE_DELAY,
    wrap: true // Enables word wrap
  });

  var session = editor.getSession();

  editor.setFontSize(DEFAULT_FONT_SIZE);
  editor.on("change", function() {
    if (isContinuousPreviewEnabled()) {
        clearTimeout(timer);
        timer = setTimeout(updateLatexPreview, TIMER_INTERVAL);
    }
  });

  session.setOptions({
    tabSize: TAB_SIZE,
    useSoftTabs: true
  });

  let hasInfo = false;
  session.on("changeAnnotation", function() {
    var annotations = session.getAnnotations() || [];
    
    if (hasInfo) {
      hasInfo = false;
      return;
    }

    var infoAnnotation;
    for (let i = 0; i < annotations.length; i++) {
      let annotation = annotations[i];
      if (annotation.type === 'info') {
        hasInfo = true;
        infoAnnotation = annotation;
        annotations.splice(i, 1);
        
        break;
      }
    }

    if (hasInfo) {
      updateEnvironment(JSON.parse(infoAnnotation.text));
      session.setAnnotations(annotations);
    }
  });

  editor.commands.addCommand({
    name: 'save',
    bindKey: {win: "Ctrl-S", "mac": "Cmd-S"},
    exec: function() {
      downloadCode();
    }
  })

  // Stores whether the last char typed in the editor was a '}'
  // Used for auto-indentation
  var lastCharWasLeftCurlyBrace = false;

  // Auto-indentation
  // Adds an indent when writing a function definition
  // A better solution might be possible for this
  editor.textInput.getElement().addEventListener("keydown", function(event) {
    if (event.key === "(") matchingParentheses();
    if (event.key === ")") {
      // Stop the ')' from being typed in the code editor
      event.preventDefault();
      closingParentheses();
    }

    if (lastCharWasLeftCurlyBrace && event.key === "Enter") {
      // Stop a new line from being created in the code editor
      event.preventDefault();
      matchingBrace();
    }

    lastCharWasLeftCurlyBrace = event.key === "{"
  });
}

// Returns a string that is the indentation at the start of some text
function getIndentation(text) {
  for (let i = 0; i < text.length; i++) {
    if (text.charAt(i) !== " ") {
      return text.substring(0, i);
    }
  }
  return text;
}

// Adds indentation and a closing right brace
function matchingBrace() {
  var editor = getEditor();
  var code = editor.getValue();
  var cursorIndex = editor.session.doc.positionToIndex(editor.selection.getCursor());
  var cursorPosition = editor.getCursorPosition();
  var column = cursorPosition.column;
  var row = cursorPosition.row;
  var currentLine = editor.getSession().getLine(row);
  var indentation = getIndentation(currentLine);
  var nextLine = "\n" + indentation;
  for (let i = 0; i < TAB_SIZE; i++) nextLine += " ";
  nextLine += "\n" + indentation + "}"
  editor.setValue(code.substring(0, cursorIndex) + nextLine + code.substring(cursorIndex));
  editor.gotoLine(row + 2, indentation.length + TAB_SIZE);
}

// Adds a matching closing parentheses when an open parenthese is typed
function matchingParentheses() {
  var editor = getEditor();
  var code = editor.getValue();
  var cursorIndex = editor.session.doc.positionToIndex(editor.selection.getCursor());
  var cursorPosition = editor.getCursorPosition();
  var column = cursorPosition.column;
  var row = cursorPosition.row;
  editor.setValue(code.substring(0, cursorIndex) + ")" + code.substring(cursorIndex));
  editor.gotoLine(row + 1, column);
}

// Handles when a closing parentheses is typed
function closingParentheses() {
  var editor = getEditor();
  var code = editor.getValue();
  var cursorIndex = editor.session.doc.positionToIndex(editor.selection.getCursor());
  var cursorPosition = editor.getCursorPosition();
  var column = cursorPosition.column;
  var row = cursorPosition.row;
  // Note: cursorIndex is always less than or equal to code length
  if (cursorIndex === code.length) {
    // Cursor is at the very end of the document
    editor.setValue(code + ")");
  } else if (code.charAt(cursorIndex) !== ")"){
    editor.setValue(code.substring(0, cursorIndex) + ")" + code.substring(cursorIndex));
  }

  editor.gotoLine(row + 1, column + 1);
}

// Increases editor font size
function zoomEditorIn() {
  if (currentEditorFontSize >= MAXIMUM_FONT_SIZE) return;

  currentEditorFontSize += FONT_SIZE_INCREMENT;
  getEditor().setFontSize(currentEditorFontSize);
}

// Decreases editor font size
function zoomEditorOut() {
  if (currentEditorFontSize <= MINIMUM_FONT_SIZE) return;

  currentEditorFontSize -= FONT_SIZE_INCREMENT;
  getEditor().setFontSize(currentEditorFontSize);
}

// Overrides default browser zooming in/out and replaces it
// with zoom for the code editor
document.addEventListener("keydown", function(event) {

  if (event.ctrlKey && (event.key === '-' || event.key === "=")) {
    event.preventDefault();
    event.stopPropagation();
    event.key === "=" ? zoomEditorIn() : zoomEditorOut();
  }
});

document.addEventListener("wheel", function(event) {
  if (event.ctrlKey) {
    event.preventDefault();
    event.deltaY > 0 ? zoomEditorOut() : zoomEditorIn();
  }
});