// Updates the latex preview box based on the current contents of the code editor
function updateLatexPreview() {
  validateCode((isValid, issues) => {
    if (isValid) {
      var editor = getEditor();
      editor.xtextServices.generatorService._encodedResourceId = "latex.zkak";
      editor.xtextServices.generate().then((code) => {
        if (code === "") {
          document.getElementById("latex-code").value = "";
          document.getElementById("latex-preview").innerHTML = "";
        } else {
          updateLatexPreviewBox(code);
        }
      });
    }
  });
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

function zoomPreviewIn() {
  if (currentPreviewFontSize >= MAXIMUM_FONT_SIZE) return;

  currentPreviewFontSize += FONT_SIZE_INCREMENT;
  document.getElementById("latex-preview").style.fontSize = currentPreviewFontSize;
}

function zoomPreviewOut() {
  if (currentPreviewFontSize <= MINIMUM_FONT_SIZE) return;

  currentPreviewFontSize -= FONT_SIZE_INCREMENT;
  document.getElementById("latex-preview").style.fontSize = currentPreviewFontSize;
}