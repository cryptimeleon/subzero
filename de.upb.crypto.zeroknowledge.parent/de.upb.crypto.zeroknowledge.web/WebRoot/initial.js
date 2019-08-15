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
    })

  });
});
