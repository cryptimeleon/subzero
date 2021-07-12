

// Returns true if the 'Enable LaTEX preview' option is checked
function isLatexPreviewEnabled() {
  return true;
  // return document.getElementById("enable-latex-preview").classList.contains("checkbox-on");
}

// Returns true if the 'Enable continuous LaTEX preview update' option is checked
function isContinuousPreviewEnabled() {
  return true;
  // return document.getElementById("enable-continuous-preview").classList.contains("checkbox-on");
}

// Returns true if the 'Enable inlining functions in LaTEX preview' option is checked
function isLatexInliningEnabled() {
  return false;
  // return document.getElementById("enable-inlined-latex").classList.contains("checkbox-on");
}

// Returns true if 'Enable inlining functions in generated Java code' option is checked
function isJavaInliningEnabled() {
  return false;
  // return document.getElementById("enable-inlined-java").classList.contains("checkbox-on");
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
  // compileCode();
  compilePackage();
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

/*
  * Zoom button listeners
*/
document.getElementById("zoom-out-button").addEventListener("click", function() {
  zoomPreviewOut();
});

document.getElementById("zoom-in-button").addEventListener("click", function() {
  zoomPreviewIn();
});
