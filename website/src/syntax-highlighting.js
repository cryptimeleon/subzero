// Specifies the syntax highlighting rules for the website code editor
define(["ace/lib/oop", "ace/mode/text", "ace/mode/text_highlight_rules"], function(oop, mText, mTextHighlightRules) {
	var HighlightRules = function() {
		
		// Reused regex definitions
		var identifier = "([a-zA-Z][a-zA-Z0-9_~\\']*)";
		var operator = "&|\\||=|!=|<|<=|>|>=|\\^|\\*|\\+|\\-";
		var number = "[0-9]+";
		var leftParen = "(\\()";
		var rightParen = "(\\))";
		var space = "([ ]+)";

		// Reused token definitions
		var protocolNameStart = {token: ["string"], regex: "\\[", next: "protocolName"};
		var subprotocolNameStart = {token: ["string"], regex: "\\[", push: "subprotocolName"};
		var inline = {token: "keyword", regex: "(inline)", next: "inlineFunctionName"};
		var functionName = {token: ["support.function", "lparen"], regex: identifier + leftParen, next: "parameterList"};
		var witnessList = {token: ["keyword", "colon"], regex: "(witness)(:?)", next: "witnessDeclarationList"};
		var ppList = {token: ["keyword", "colon"], regex: "(pp)(:?)", next: "ppDeclarationList"};
		var stringStart = {token: "string", regex: "\\\"", push: "stringLiteral"};
		var functionCallStart = {token: ["support.function", "lparen"], regex: identifier + leftParen, push: "argumentList"};
		var variable = {token: "variable", regex: identifier};
		var constant = {token: "constant.numeric", regex: number};
		var operation = {token: "operator", regex: operator};

		// Reused state definitions
		var createDeclarationListState = function(nextState) {
			return [
				{token: ["variable", "comma"], regex: identifier + "(,)"},
				{token: ["variable", "end"], regex: identifier + "(;?)", next: nextState},
			]
		}

		var highlightingRules = {
			"start": [
				protocolNameStart,
				inline,
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
				inline,
				functionName,
				ppList,
				witnessList
			],

			"afterPP": [
				witnessList,
			],

			"inlineFunctionName": [
				functionName,
			],

			"parameterList": [
				{token: ["variable", "comma"], regex: identifier + "(,)"},
				{token: "rparen", regex: rightParen, next: "functionBody"},
				{token: ["variable", "rparen"], regex: identifier + rightParen, next: "functionBody"},
			],

			"functionBody": [
				stringStart,
				functionCallStart,
				variable,
				operation,
				constant,
				subprotocolNameStart,
				{token: "rbrace", regex: "\\}", next: "start"},
			],

			"argumentList": [
				stringStart,
				functionCallStart,
				variable,
				operation,
				constant,
				{token: "comma", regex: ","},
				{token: "rparen", regex: rightParen, next: "pop"},
			],

			"mainBody": [
				{token: ["keyword", "colon"], regex: "(statement)(:?)"},
				stringStart,
				functionCallStart,
				variable,
				operation,
				constant,
				subprotocolNameStart,
			],

			"subprotocolName": [
				{token: ["string"], "regex": "[a-zA-Z][a-zA-Z0-9_\\' ]*\\]", next: "pop"},
				{defaultToken: "string"},
			],

			"stringLiteral": [
				{token: "string", regex: "[^\\\"]*\\\"", next: "pop"},
				{defaultToken: "string"}
			],

			"blockComment": [
				{token: "comment", regex: "(?:[^*]*\\*+)(?:[^/*][^*]*\\*+)*(/)", next: "pop"},
				{defaultToken: "comment"},
			],

			"singleComment": [
				{token: "comment", regex: "[^\\n\\r]+?$", next: "pop"},
				{defaultToken: "comment"},
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
	// Mode.prototype.$id = "xtext/" + DSL_EXTENSION;
	Mode.prototype.$id = "xtext/sub0";
	Mode.prototype.getCompletions = function(state, session, pos, prefix) {
		return [];
	}
	
	return {
		Mode: Mode
	};
});
