

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