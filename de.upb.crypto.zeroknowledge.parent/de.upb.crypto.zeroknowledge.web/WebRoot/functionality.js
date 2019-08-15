// Checkmark icon
var CHECK = "images/check.svg";

// Blank transparent icon
var BLANK_IMAGE = "data:image/gif;base64,R0lGODlhAQABAIAAAAAAAP///yH5BAEAAAAALAAAAAABAAEAAAIBRAA7";

var currentEditorFontSize = 18;
var currentPreviewFontSize = 18;

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
  ace.edit("xtext-editor").setFontSize(currentEditorFontSize += 2);
}

// Decreases editor font size by 2pts, down to a min of 8
function zoomEditorOut() {
  if (currentEditorFontSize <= 8) return;
  ace.edit("xtext-editor").setFontSize(currentEditorFontSize -= 2);
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
  var code = ace.edit("xtext-editor").getValue();

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

  if (filename !== null) {

    downloadFile(filename + ".tex", latex);
  }
}

// Returns true if there are no syntax errors or validation errors in the code editor
function noCodeErrors(annotations) {
  var editor = ace.edit("xtext-editor");
  var annotations = editor.getSession().getAnnotations();

  return annotations === 0 || !annotations.some(function(annotation) {
    return annotation.type === "error";
  });
}

// Compile the contents of the code editor to Java code and download the generated Java project
function compileCode() {
  if (noCodeErrors()) {
    // HTTP request
    // ace.edit("xtext-editor").xtextServices.generate().then(function(errors) {
    //
    // });
  } else {
    consoleError("Code cannot be compiled until there are no syntax or validation errors.");
  }
}

// Updates the latex preview box based on the current contents of the code editor
function updateLatexPreview() {
  var code = ace.edit("xtext-editor").getValue();

  if (code === "") {
    document.getElementById("latex-preview").innerHTML = "";
  } else {
    // HTTP request to the server to produce latex text, and store the result in formatted_latex
    formatted_latex = code // Remove after the HTTP request is implemented

    updateLatexPreviewBox(formatted_latex);
  }
}

// Updates the latex preview box
function updateLatexPreviewBox(latex) {
  var box = document.getElementById("latex-preview");
  box.innerHTML = latex;
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
			this.getElementsByClassName("checkbox-icon")[0].src = CHECK;
		}
	});

	checkbox.addEventListener("mouseleave", function() {
		if (this.classList.contains("checkbox-off")) {
			this.getElementsByClassName("checkbox-icon")[0].src = BLANK_IMAGE;
		}
	});

	checkbox.addEventListener("focusin", function() {
		if (this.classList.contains("checkbox-off")) {
			this.getElementsByClassName("checkbox-icon")[0].src = CHECK;
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
  if (event.ctrlKey && (event.code === "Minus" || event.code === "Equal")) {
    event.preventDefault();
    event.stopPropagation();
    event.code === "Equal" ? zoomEditorIn() : zoomEditorOut();
  }
});

document.addEventListener("wheel", function(event) {
  if (event.ctrlKey) {
    event.preventDefault();
    event.deltaY > 0 ? zoomEditorOut() : zoomEditorIn();
  }
});
