/*
 * Specifies the syntax highlighting rules for the website code editor
*/
define(["ace/lib/oop", "ace/mode/text", "ace/mode/text_highlight_rules"], function(oop, mText, mTextHighlightRules) {
	var HighlightRules = function() {
		
		var identifier = "([a-zA-Z][a-zA-Z0-9_\\']*)";
		var operator = "&|\\||=|!=|<|<=|>|>=|\\^|\\*|\\+|\\-";
		var number = "[0-9]+";

		var protocolNameStart = {token: ["string"], regex: "\\[", next: "protocolName"};
		var functionName = {token: ["keyword", "support.function", "lparen"], regex: "(inline)?" + identifier + "(\\()", next: "parameterList"};
		var witnessList = {token: ["keyword", "colon"], regex: "(witness)(:?)", next: "witnessDeclarationList"};
		var ppList = {token: ["keyword", "colon"], regex: "(pp)(:?)", next: "ppDeclarationList"};

		var createDeclarationListState = function(nextState) {
			return [
				{token: ["variable", "comma"], regex: identifier + "(,)"},
				{token: ["variable", "end"], regex: identifier + "(;?)", next: nextState},
			]
		}

		var highlightingRules = {
			"start": [
				protocolNameStart,
				functionName,
				ppList,
				witnessList,
			],

			"protocolName": [
				{token: ["string"], "regex": "[a-zA-Z][a-zA-Z0-9_\\' ]*\\]", next: "afterProtocolName"},
				{defaultToken: "string"}
			],

			"ppDeclarationList": createDeclarationListState("afterPP"),
			"witnessDeclarationList": createDeclarationListState("mainBody"),

			"afterProtocolName": [
				functionName,
				ppList,
				witnessList
			],

			"afterPP": [
				witnessList,
			],

			"parameterList": [
				{token: ["variable", "comma"], regex: identifier + "(,)"},
				{token: "rparen", regex: "(\\))", next: "functionBody"},
				{token: ["variable", "rparen"], regex: identifier + "(\\))", next: "functionBody"},
			],

			"functionBody": [
				{token: "string", regex: "\\\"", push: "stringLiteral"},
				{token: ["support.function", "lparen"], regex: identifier + "(\\()", push: "argumentList"},
				{token: "variable", regex: identifier},
				{token: "operator", regex: operator},
				{token: "constant.numeric", regex: number},
				{token: "rbrace", regex: "\\}", next: "start"},
			],

			"argumentList": [
				{token: "string", regex: "\\\"", push: "stringLiteral"},
				{token: ["support.function", "lparen"], regex: identifier + "(\\()", push: "argumentList"},
				{token: "variable", regex: identifier},
				{token: "operator", regex: operator},
				{token: "constant.numeric", regex: number},
				{token: "comma", regex: ","},
				{token: "rparen", regex: "\\)", next: "pop"},
			],

			"mainBody": [
				{token: "string", regex: "\\\"", push: "stringLiteral"},
				{token: ["support.function", "lparen"], regex: identifier + "(\\()", push: "argumentList"},
				{token: "variable", regex: identifier},
				{token: "operator", regex: operator},
				{token: "constant.numeric", regex: number},
			],

			"blockComment": [
				{token: "comment", regex: "(?:[^*]*\\*+)(?:[^/*][^*]*\\*+)*(/)", next: "pop"},
				{defaultToken: "comment"},
			],

			"singleComment": [
				{token: "comment", regex: "[^\\n\\r]+?$", next: "pop"},
				{defaultToken: "comment"},
			],

			"stringLiteral": [
				{token: "string", regex: "[^\\\"]*\\\"", next: "pop"},
				{defaultToken: "string"}
			],
		};

		// Add comment transitions to each rule state
		for (key in highlightingRules) {
			highlightingRules[key].push({token: "comment.block", regex: "/\\*", push: "blockComment"});
			highlightingRules[key].push({token: "comment.line.double-slash", regex: "//$"});
			highlightingRules[key].push({token: "comment.line.double-slash", regex: "//", push: "singleComment"});
		}

		this.$rules = highlightingRules;
		this.normalizeRules(); // Needed to enable using push and pop
	};
	oop.inherits(HighlightRules, mTextHighlightRules.TextHighlightRules);
	
	var Mode = function() {
		this.HighlightRules = HighlightRules;
	};
	oop.inherits(Mode, mText.Mode);
	Mode.prototype.$id = "xtext/zkak";
	Mode.prototype.getCompletions = function(state, session, pos, prefix) {
		return [];
	}
	
	return {
		Mode: Mode
	};
});
