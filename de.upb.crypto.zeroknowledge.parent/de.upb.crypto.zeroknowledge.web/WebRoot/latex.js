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