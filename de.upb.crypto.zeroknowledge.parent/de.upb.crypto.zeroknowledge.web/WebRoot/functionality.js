// Describes a blank icon, used as an image source when no icon should be displayed
var BLANK_IMAGE = "data:image/gif;base64,R0lGODlhAQABAAAAACwAAAAAAQABAAA=";
var CHECK = "images/check.svg";

var currentEditorFontSize = 18;

// Writes a message to the console box in the lower right corner
// Prefixes the message with a colored message type
// Valid message types are 'error', 'warning', and 'info'
function consoleLog(messageType, message) {
  if (messageType === "error") {
    output = "<span class='error'>Error: </span>" + message;
  } else if (messageType === "warning") {
    output = "<span class='warning'>Warning: </span>" + message;
  } else if (messageType === "info") {
    output = "<span class='info'>Info: </span>" + message;
  }

  document.getElementById("console").innerHTML = output;
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
    consoleLog("error", "There is no code to download");
    return;
  }

  var filename = prompt("Enter a name for the code file (file will be saved with .zkak extension)");

  if (filename !== null) {
    downloadFile(filename + ".zkak", code);
  }

}

// Downloads the raw latex used to create the latex preview
function downloadRawLatex() {

  var latex = document.getElementById("raw-latex").value;

  if (latex === "") {
    consoleLog("error", "There is no raw latex to download");
    return;
  }

  var filename = prompt("Enter a name for the latex file (file will be saved with .tex extension)");

  if (filename !== null) {

    downloadFile(filename + ".tex", latex);
  }
}

function compileCode() {

}

// Updates the latex preview box based on the current contents of the code editor
function updateLatexPreview() {

}

/*
 * Button Listeners
*/
document.getElementById("download-code").addEventListener("click", function() {
  this.blur();
  downloadCode();
});

document.getElementById("download-raw-latex").addEventListener("click", function() {
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
		if (this.classList.contains("checkbox-off")) {
      console.log(this.firstChild);
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
			this.firstChild.src = CHECK;
		}
	});

	checkbox.addEventListener("focusout", function() {
		if (this.classList.contains("checkbox-off")) {
			this.firstChild.src = BLANK_IMAGE;
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


document.getElementById("enable-latex-preview").addEventListener("change", function() {
  this.blur();
  if (this.checked) {
    document.getElementById("update-latex-preview").disabled = false;
    document.getElementById("enable-continuous-preview").disabled = false;
  } else {
    document.getElementById("update-latex-preview").disabled = true;
    document.getElementById("enable-continuous-preview").checked = false;
    document.getElementById("enable-continuous-preview").disabled = true;
  }
});

document.getElementById("enable-continuous-preview").addEventListener("change", function() {
  this.blur();
  if (this.checked) {
    document.getElementById("update-latex-preview").disabled = true;
  } else if (document.getElementById("enable-latex-preview").checked) {
    document.getElementById("update-latex-preview").disabled = false;
  }
});

document.getElementById("enable-inlined-latex").addEventListener("change", function() {

});

document.getElementById("enable-inlined-java").addEventListener("change", function() {

});


// Overrides default browser zooming in/out and replaces it
// with zoom for the code editor
document.addEventListener("keydown", function(event) {
  if (event.ctrlKey && (event.keyCode === 187 || event.keyCode === 189)) {
    event.preventDefault();
    event.stopPropagation();
    event.keyCode === 187 ? zoomEditorIn() : zoomEditorOut();
  }
});
