

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

  // There are no annotations, or all annotations are not errors
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
  
      var filename = prompt("Enter a name for the code file (file will be saved with .java extension)");
  
      if (filename) {
        downloadFile(filename + ".java", code);
      }
    });
  } else {
    consoleError("Code cannot be compiled until there are no syntax or validation errors.");
  }
}


