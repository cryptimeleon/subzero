/*
 * Button Listeners
*/
document.getElementById("compile-code").addEventListener("click", function() {
  this.blur();
  // compileCode();
  compilePackage();
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
