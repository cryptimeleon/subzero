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
		
		const witnessList = {token: ["keyword", "colon"], regex: "(witness)(:?)"};
		const ppList = {token: ["keyword", "colon"], regex: "(pp)(:?)"};
		const constantList = {token: ["keyword", "colon"], regex: "(common)(:?)"};

		const initialWitnessList = {...witnessList, next: 'initialWitnessList'};
		const initialPPList = {...ppList, next: 'initialPPList'};
		const initialConstantList = {...constantList, next: 'initialConstantList'};
		
		const functionCallStart = {token: ["support.function", "lparen"], regex: identifier + leftParen, push: "argumentList"};
		const preFunctionCallStart = {token: ["support.function", "lparen"], regex: identifier + leftParen, next: "preArgumentList"};
		
		// Partial reused token definitions (needs next state)
		const variable = {token: "variable", regex: identifier};
		const constant = {token: "constant.numeric", regex: number};
		const operation = {token: "operator", regex: operator};
		const separator = {token: "comma", regex: ","};

		// Partial reused state definitions
		const preBodyState = [
			preFunctionCallStart,
			{...variable, next: 'mainBody'},
			{...operation, next: 'mainBody'},
			{...constant, next: 'mainBody'}
		];

		// Generate state definitions
		const createDeclarationListState = (nextState) => {
			return [
				{token: ["variable", "comma"], regex: identifier + "(,)"},
				{token: ["variable", "end"], regex: identifier + "(;?)", next: nextState},
			]
		}

		const highlightingRules = {
			"start": [
				protocolNameStart,
				inline,
				functionName,
				initialWitnessList,
				initialPPList,
				initialConstantList,
			],

			"protocolName": [
				{token: ["string"], "regex": protocol, next: "afterProtocolName"},
				{defaultToken: "string"}
			],

			"afterProtocolName": [
				inline,
				functionName,
				initialWitnessList,
				initialPPList,
				initialConstantList,
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

			'initialWitnessList': createDeclarationListState('afterWitness'),

			'initialPPList': createDeclarationListState('afterPP'),

			'initialConstantList': createDeclarationListState('afterConstant'),

			'afterWitness': [
				{...ppList, next: 'ppListAfterWitness'},
				{...constantList, next: 'constantListAfterWitness'},
				...preBodyState
			],

			'afterPP': [
				{...witnessList, next: 'witnessListAfterPP'},
				{...constantList, next: 'constantListAfterPP'},
				...preBodyState
			],

			'afterConstant': [
				{...witnessList, next: 'witnessListAfterConstant'},
				{...ppList, next: 'ppListAfterConstant'},
				...preBodyState
			],
			
			'ppListAfterWitness': createDeclarationListState('afterWitnessAndPP'),

			'constantListAfterWitness': createDeclarationListState('afterWitnessAndConstant'),

			'witnessListAfterPP': createDeclarationListState('afterWitnessAndPP'),

			'constantListAfterPP': createDeclarationListState('afterPPAndConstant'),

			'witnessListAfterConstant': createDeclarationListState('afterWitnessAndConstant'),

			'ppListAfterConstant': createDeclarationListState('afterPPAndConstant'),

			'afterWitnessAndPP': [
				{...constantList, next: 'endingConstantList'},
				...preBodyState,
			],

			'afterWitnessAndConstant': [
				{...ppList, next: 'endingPPList'},
				...preBodyState,
			],

			'afterPPAndConstant': [
				{...witnessList, next: 'endingWitnessList'},
				...preBodyState,
			],

			'endingWitnessList': createDeclarationListState('mainBody'),

			'endingPPList': createDeclarationListState('mainBody'),
			
			'endingConstantList': createDeclarationListState('mainBody'),

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