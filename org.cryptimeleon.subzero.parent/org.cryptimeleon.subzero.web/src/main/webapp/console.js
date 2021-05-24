/*
Functions for displaying messages in the console box
*/
function getConsole() {
    return document.getElementById("console");
}

// Writes an error message to the console box in the lower right corner
function consoleError(message) {
    getConsole().innerHTML = "<span class='error'>Error: </span>" + message;
}

// Writes a warning message to the console box in the lower right corner
function consoleWarning(message) {
    getConsole().innerHTML = "<span class='warning'>Warning: </span>" + message;
}

// Writes an info message to the console box in the lower right corner
function consoleInfo(message) {
    getConsole().innerHTML = "<span class='info'>Info: </span>" + message;
}