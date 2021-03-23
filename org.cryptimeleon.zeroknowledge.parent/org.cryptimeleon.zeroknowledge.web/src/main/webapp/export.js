

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

function validateCode(callback) {
  var request = $.ajax({
      url: "http://" + location.host + "/xtext-service/validate?resource=code.zkak"
  });
      
  request.done((result) => {
    issues = result.issues;
    isValid = issues.length === 0 || !issues.some((issue) => {
      return issue.severity === "error";
    });
    callback(isValid, issues);
  });
}

// Compile the contents of the code editor to Java code and download the generated Java project
function compileCode() {
  validateCode((isValid, issues) => {
    if (isValid) {
      var editor = getEditor();
      editor.xtextServices.generatorService._encodedResourceId = "code.zkak";
      editor.xtextServices.generate().then((code) => {
        console.log(code);
        if (code === "") {
          consoleError("There is no code to download");
          return;
        }
    
        var filename = prompt("Enter a name for the code file (file will be saved with .java extension)");
    
        if (filename) {
          var blob = new Blob([code], {type: "text/plain;charset=utf-8"});
          window.saveAs(blob, filename);
        }
      });
    } else {
      consoleError("Code cannot be compiled until there are no syntax or validation errors.");
    }
  });
}

// Compile the contents of the code editor to Java code and download the generated Java project
function compilePackage() {
  validateCode((isValid, issues) => {
    if (isValid) {
      var editor = getEditor();
      editor.xtextServices.generatorService._encodedResourceId = "code.zkak";
      editor.xtextServices.generate().then((projectJSON) => {
        console.log(projectJSON);
        let project = $.parseJSON(projectJSON);
        let projectZip = new JSZip();
        createProjectZip(projectZip, project);

        var filename = prompt("Enter a name for the zipped project (project will be saved with .zip extension)");
    
        if (filename) {
          projectZip.generateAsync({type:"blob"}).then(function(content) {
            saveAs(content, filename + ".zip");
          });
        }
      });
    } else {
      consoleError("Code cannot be compiled until there are no syntax or validation errors.");
    }
  });
}

function createProjectZip(zip, currentFolder) {
  for (const [name, contents] of Object.entries(currentFolder)) {
    if ($.type(contents) === 'string') {
      if (name.endsWith(".jar")) {
        zip.file(name, contents, {base64: true});
      } else {
        zip.file(name, contents);
      }
    } else {
      createProjectZip(zip.folder(name), contents);
    }
  }
}



