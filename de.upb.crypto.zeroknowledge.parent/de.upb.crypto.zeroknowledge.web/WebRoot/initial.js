var TIMER_INTERVAL = 500;
var timer;
var baseUrl = window.location.pathname;
var fileIndex = baseUrl.indexOf("index.html");
if (fileIndex > 0)
  baseUrl = baseUrl.slice(0, fileIndex);
require.config({
  baseUrl: baseUrl,
  paths: {
    "jquery": "webjars/jquery/3.3.1-1/jquery.min",
    "ace/ext/language_tools": "webjars/ace/1.3.3/src/ext-language_tools",
    "xtext/xtext-ace": "xtext/2.17.0/xtext-ace"
  }
});
require(["webjars/ace/1.3.3/src/ace"], function() {
  require(["xtext/xtext-ace"], function(xtext) {
    var editor = xtext.createEditor({
      baseUrl: baseUrl,
      syntaxDefinition: "xtext-resources/generated/mode-zkak",
      theme: "ace/theme/monokai",
      selectionUpdateDelay: 550,
      textUpdateDelay: 500
    })

    editor.setFontSize(18);
    editor.on("change", function() {
      if (isContinuousPreviewEnabled()) {
          clearTimeout(timer);
          var code = editor.getValue()
          timer = setTimeout(updateLatexPreview, TIMER_INTERVAL, code);
      }
    });

    editor.getSession().setOptions({
      tabSize: 2,
      useSoftTabs: true
    });

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
        setTimeout(matchingBrace, 1);
      }

      lastCharWasLeftCurlyBrace = event.key === "{"
    });

  });
});
