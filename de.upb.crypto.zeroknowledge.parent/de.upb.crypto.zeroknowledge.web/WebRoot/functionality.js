// Checkmark icon
var CHECK_ICON = "images/check.svg";

// The length of indentation when pressing tab in the code editor
var INDENT_SPACES = 2;

// Blank transparent icon
var BLANK_IMAGE = "data:image/gif;base64,R0lGODlhAQABAIAAAAAAAP///yH5BAEAAAAALAAAAAABAAEAAAIBRAA7";

var currentEditorFontSize = 18;
var currentPreviewFontSize = 18;

// Stores whether the last key pressed was
var lastCharWasLeftCurlyBrace = false;

function getEditor() {
  return ace.edit("xtext-editor");
}

function getIndentation(text) {
  for (var i = 0; i < text.length; i++) {
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
  for (let i = 0; i < INDENT_SPACES; i++) nextLine += " ";
  nextLine += "\n" + indentation + "}"
  editor.setValue(code.substring(0, cursorIndex) + nextLine + code.substring(cursorIndex));
  editor.gotoLine(row + 2, indentation.length + INDENT_SPACES);
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

// Writes an error message to the console box in the lower right corner
function consoleError(message) {
  document.getElementById("console").innerHTML = "<span class='error'>Error: </span>" + message;
}

// Writes a warning message to the console box in the lower right corner
function consoleWarning(message) {
  document.getElementById("console").innerHTML = "<span class='warning'>Warning: </span>" + message;
}

// Writes an info message to the console box in the lower right corner
function consoleInfo(message) {
  document.getElementById("console").innerHTML = "<span class='info'>Info: </span>" + message;
}

// Increases editor font size by 2pts, up to a max of 40
function zoomEditorIn() {
  if (currentEditorFontSize >= 40) return;
  getEditor().setFontSize(currentEditorFontSize += 2);
}

// Decreases editor font size by 2pts, down to a min of 8
function zoomEditorOut() {
  if (currentEditorFontSize <= 8) return;
  getEditor().setFontSize(currentEditorFontSize -= 2);
}

function zoomPreviewIn() {
  if (currentPreviewFontSize >= 40) return;
  document.getElementById("latex-preview").style.fontSize = (currentPreviewFontSize += 2);
}

function zoomPreviewOut() {
  if (currentPreviewFontSize <= 8) return;
  document.getElementById("latex-preview").style.fontSize = (currentPreviewFontSize -= 2);
}

// Returns true if the 'Enable LaTEX preview' option is checked
function isLatexPreviewEnabled() {
  return document.getElementById("enable-latex-preview").classList.contains("checkbox-on");
}

// Returns true if the 'Enable continuous LaTEX preview update' option is checked
function isContinuousPreviewEnabled() {
  return document.getElementById("enable-continuous-preview").classList.contains("checkbox-on");
}

// Returns true if the 'Enable inlining functions in LaTEX preview' option is checked
function isLatexInliningEnabled() {
  return document.getElementById("enable-inlined-latex").classList.contains("checkbox-on");
}

// Returns true if 'Enable inlining functions in generated Java code' option is checked
function isJavaInliningEnabled() {
  return document.getElementById("enable-inlined-java").classList.contains("checkbox-on");
}

// Downloads a file to the client's default download folder
// Filename must include extension suffix
// Data is the string to be saved as a file
function downloadFile(filename, data) {
  var temp = document.createElement("a");

  temp.setAttribute("href", "data:text/plain;charset=utf-8," + encodeURIComponent(data));
  temp.setAttribute("download", filename);
  temp.style.display = "none";

  document.body.appendChild(temp);
  temp.click();
  document.body.removeChild(temp);
}

// Downloads the code editor's contents
function downloadCode() {
  var code = getEditor().getValue();

  if (code === "") {
    consoleError("There is no code to download");
    return;
  }

  var filename = prompt("Enter a name for the code file (file will be saved with .zkak extension)");

  if (filename !== null) {
    downloadFile(filename + ".zkak", code);
  }

}

// Downloads the raw latex used to create the latex preview
function downloadRawLatex() {

  var latex = document.getElementById("latex-code").value;

  if (latex === "") {
    consoleError("There is no raw latex to download");
    return;
  }

  var filename = prompt("Enter a name for the latex file (file will be saved with .tex extension)");

  if (filename) {
    downloadFile(filename + ".tex", latex);
  }
}

// Returns true if there are no syntax errors or validation errors in the code editor
function noCodeErrors(annotations) {
  var editor = getEditor();
  var annotations = editor.getSession().getAnnotations();

  return annotations === 0 || !annotations.some(function(annotation) {
    return annotation.type === "error";
  });
}

// Compile the contents of the code editor to Java code and download the generated Java project
function compileCode() {
  if (noCodeErrors()) {
    getEditor().xtextServices.generate().then((code) => {
      if (code === "") {
        consoleError("There is no code to download");
        return;
      }
  
      var filename = prompt("Enter a name for the code file (file will be saved with .zkak extension)");
  
      if (filename) {
        downloadFile(filename + ".java", code);
      }
    });
  } else {
    consoleError("Code cannot be compiled until there are no syntax or validation errors.");
  }
}

// Updates the latex preview box based on the current contents of the code editor
function updateLatexPreview() {
  var code = getEditor().getValue();

  if (code === "") {
    document.getElementById("latex-code").value = "";
    document.getElementById("latex-preview").innerHTML = "";
  } else {
    // HTTP request to the server to produce latex text, and store the result in formatted_latex
    formatted_latex = code // Remove after the HTTP request is implemented

    updateLatexPreviewBox(formatted_latex);
  }
}

// Updates the latex preview box
function updateLatexPreviewBox(latex) {
  document.getElementById("latex-code").value = latex;
  document.getElementById("latex-preview").innerHTML = latex;
  MathJax.Hub.Queue(["Typeset", MathJax.Hub, "latex-preview"]);
}

function disableLatexPreviewBox() {
  document.getElementById("latex-preview").innerHTML = "Latex preview currently&nbsp<span class='error'>disabled</span>";
}

function enableLatexPreviewBox() {
  updateLatexPreviewBox(ace.editor("xtext-editor").getValue());
}

/*
 * Button Listeners
*/
document.getElementById("download-code").addEventListener("click", function() {
  this.blur();
  downloadCode();
});

document.getElementById("download-latex-code").addEventListener("click", function() {
  this.blur();
  downloadRawLatex();
});

document.getElementById("compile-code").addEventListener("click", function() {
  this.blur();
  compileCode();
});

document.getElementById("update-latex-preview").addEventListener("click", function() {
  this.blur();
  updateLatexPreview();
});

/*
 * Checkbox listeners
*/
for (let checkbox of document.getElementsByClassName("option-checkbox")) {
  checkbox.addEventListener("mouseenter", function() {
		if (this.classList.contains("checkbox-off") && !this.classList.contains("disabled")) {
			this.getElementsByClassName("checkbox-icon")[0].src = CHECK_ICON;
		}
	});

	checkbox.addEventListener("mouseleave", function() {
		if (this.classList.contains("checkbox-off")) {
			this.getElementsByClassName("checkbox-icon")[0].src = BLANK_IMAGE;
		}
	});

	checkbox.addEventListener("focusin", function() {
		if (this.classList.contains("checkbox-off")) {
			this.getElementsByClassName("checkbox-icon")[0].src = CHECK_ICON;
		}
	});

	checkbox.addEventListener("focusout", function() {
		if (this.classList.contains("checkbox-off")) {
			this.getElementsByClassName("checkbox-icon")[0].src = BLANK_IMAGE;
		}
	});

	checkbox.addEventListener("click", function() {
    if (this.classList.contains("checkbox-off")) {
      this.classList.remove("checkbox-off");
      this.classList.add("checkbox-on");
    } else {
      this.classList.remove("checkbox-on");
      this.classList.add("checkbox-off");
    }

		this.blur();
	});
}

document.getElementById("enable-latex-preview").addEventListener("click", function() {
  if (this.classList.contains("checkbox-on")) {
    updateLatexPreview();
    document.getElementById("update-latex-preview").disabled = false;
    document.getElementById("enable-continuous-preview").classList.remove("disabled");
    document.getElementById("enable-continuous-preview-text").classList.remove("disabled");
  } else {
    disableLatexPreviewBox();
    document.getElementById("update-latex-preview").disabled = true;
    document.getElementById("enable-continuous-preview").classList.remove("checkbox-on");
    document.getElementById("enable-continuous-preview").classList.add("disabled");
    document.getElementById("enable-continuous-preview-icon").src = BLANK_IMAGE;
    document.getElementById("enable-continuous-preview-text").classList.add("disabled");
  }
});

document.getElementById("enable-continuous-preview").addEventListener("click", function() {
  if (this.classList.contains("checkbox-on")) {
    updateLatexPreview();
    document.getElementById("update-latex-preview").disabled = true;
  } else if (isLatexPreviewEnabled()) {
    document.getElementById("update-latex-preview").disabled = false;
  }
});

document.getElementById("enable-inlined-latex").addEventListener("click", function() {

});

document.getElementById("enable-inlined-java").addEventListener("click", function() {

});

/*
 * Zoom button listeners
*/
document.getElementById("zoom-out-button").addEventListener("click", function() {
  zoomPreviewOut();
});

document.getElementById("zoom-in-button").addEventListener("click", function() {
  zoomPreviewIn();
});

/*
 * Global listeners
*/


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
