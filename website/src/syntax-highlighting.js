// Specifies the syntax highlighting rules for the website code editor
define(["ace/lib/oop", "ace/mode/text", "ace/mode/text_highlight_rules"], function(oop, mText, mTextHighlightRules) {
	var HighlightRules = function() {
		
		// Reused regex definitions
		const identifier = "([a-zA-Z][a-zA-Z0-9_~\\']*)";
		const protocol = "[a-zA-Z][a-zA-Z0-9_\\' ]*\\]";
		const operator = "&|\\||=|!=|<|<=|>|>=|\\^|\\*|\\+|\\-";
		const number = "[0-9]+";
		const leftParen = "(\\()";
		const rightParen = "(\\))";
		const space = "([ ]+)";

		// Reused token definitions
		const protocolNameStart = {token: ["string"], regex: "\\[", next: "protocolName"};
		const subprotocolNameStart = {token: ["string"], regex: "\\[", push: "subprotocolName"};
		
		const inline = {token: "keyword", regex: "(inline)", next: "inlineFunctionName"};
		const functionName = {token: ["support.function", "lparen"], regex: identifier + leftParen, next: "parameterList"};
		
		const witnessList = {token: ["keyword", "colon"], regex: "(witness)(:?)", next: 'declarationList'};
		const ppList = {token: ["keyword", "colon"], regex: "(pp)(:?)", next: 'declarationList'};
		const constantList = {token: ["keyword", "colon"], regex: "(common)(:?)", next: 'declarationList'};

		const functionCallStart = {token: ["support.function", "lparen"], regex: identifier + leftParen, push: "argumentList"};
		const preFunctionCallStart = {token: ["support.function", "lparen"], regex: identifier + leftParen, next: "preArgumentList"};
		
		// Partial reused token definitions (needs next state)
		const variable = {token: "variable", regex: identifier};
		const constant = {token: "constant.numeric", regex: number};
		const operation = {token: "operator", regex: operator};
		const separator = {token: "comma", regex: ","};

		const highlightingRules = {
			"start": [
				protocolNameStart,
				inline,
				functionName,
				witnessList,
				ppList,
				constantList,
			],

			"protocolName": [
				{token: ["string"], "regex": protocol, next: "afterProtocolName"},
				{defaultToken: "string"}
			],

			"afterProtocolName": [
				inline,
				functionName,
				witnessList,
				ppList,
				constantList,
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
				functionCallStart,
				variable,
				operation,
				constant,
				subprotocolNameStart,
				{token: "rbrace", regex: "\\}", next: "afterProtocolName"},
			],

			'declarationList': [
				{token: ["variable", "comma"], regex: identifier + "(,)"},
				{token: ["variable", "end"], regex: identifier + "(;?)", next: 'variableDeclarations'},
			],

			'variableDeclarations': [
				witnessList,
				ppList,
				constantList,
				preFunctionCallStart,
				{...variable, next: 'mainBody'},
				{...operation, next: 'mainBody'},
				{...constant, next: 'mainBody'}
			],

			"preArgumentList": [
				functionCallStart,
				variable,
				operation,
				constant,
				separator,
				{token: "rparen", regex: rightParen, next: "mainBody"},
			],

			"argumentList": [
				functionCallStart,
				variable,
				operation,
				constant,
				separator,
				{token: "rparen", regex: rightParen, next: "pop"},
			],

			"mainBody": [
				{token: ["keyword", "colon"], regex: "(statement)(:?)"},
				functionCallStart,
				variable,
				operation,
				constant,
				subprotocolNameStart,
			],

			"subprotocolName": [
				{token: ["string"], "regex": protocol, next: "pop"},
				{defaultToken: "string"},
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