/*
Loads all required libraries
*/

var baseUrl = window.location.pathname;
var fileIndex = baseUrl.indexOf("index.html");
if (fileIndex > 0) {
	baseUrl = baseUrl.slice(0, fileIndex);
}

require.config({
	baseUrl: baseUrl,
	paths: {
		"jquery": "webjars/jquery/3.5.1/jquery.min",
		"ace/ext/language_tools": "webjars/ace/1.3.3/src/ext-language_tools",
		"xtext/xtext-ace": "xtext/2.24.0/xtext-ace",
	}
});
require(["webjars/ace/1.3.3/src/ace"], function() {
	require(["xtext/xtext-ace"], function(xtext) {
		createEditor(xtext);
	});
});
require(["webjars/jszip/3.6.0/dist/jszip.min.js"], function(JSZip_) {
	JSZip = JSZip_;
});
require(["webjars/filesaver/2.0.4/dist/FileSaver.min.js"]);
require(["https://cdnjs.cloudflare.com/ajax/libs/mathjax/2.7.5/MathJax.js?config=TeX-MML-AM_CHTML"], function() {
	MathJax.Hub.Config({
		skipStartupTypeset: true,
		messageStyle: "none",
		CommonHTML: {
			linebreaks: {
				automatic: true
			}
		},
		"HTML-CSS": {
			linebreaks: {
				automatic: true
			}
		},
		SVG: {
			linebreaks: {
				automatic: true
			}
		}
	});
});