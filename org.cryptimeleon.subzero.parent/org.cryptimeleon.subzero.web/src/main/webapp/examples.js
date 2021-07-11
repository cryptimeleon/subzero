var EDITOR_HAS_CHANGED = false;

let examplesSelect = document.getElementById("examples");

for (protocolName in PROTOCOLS) {
    let option = document.createElement("option");
    option.value = protocolName;
    option.innerHTML = protocolName;
    examplesSelect.appendChild(option);
}

examplesSelect.addEventListener("change", function() {
    let protocolName = document.getElementById("examples").value;
    let protocol = PROTOCOLS[protocolName];

    if (protocol !== undefined) {
        let editor = getEditor();

        let loadProtocol = true;
        if (editorTextHasChanged) {
            loadProtocol = window.confirm("Loading an example protocol will overwrite the current editor text. Are you sure?");
        }
        editor.setValue(protocol.substring(1, protocol.length-1));
        editorTextHasChanged = false;
    }
});