Subzero - Zero Knowledge Compiler
=================================
Subzero is a declarative domain-specific language (DSL) that enables the specification of zero knowledge protocols.
This website features a code editor for writing Subzero programs, and a compiler to generate a Java project that can execute the protocol described by Subzero code.

Documentation
=============
- [Getting started](#getting-started)
   - [Compiler website](#compiler-website)
   - [Local build](#local-build)

- [Tutorial](#tutorial)
   - [Introduction](#introduction)
   - [DLog equality](#dlog-equality)
   - [Pedersen commitment](#pedersen-commitment)
   - [Pointcheval Sanders credential](#pointcheval-sanders-credential)

- [The Subzero language](#the-subzero-language)
   - [Identifiers](#identifiers)
   - [Type and scope](#type-and-scope)
   - [Expressions](#expressions)
   - [Values](#values)
   - [Operators](#operators)
   - [Pairings](#pairings)
   - [Comments](#comments)
   - [Subzero program layout](#subzero-program-layout)

- [Compiler website features](#compiler-website-features)
   - [Code editor](#code-editor)
   - [Compiler](#compiler)
   - [LaTeX preview](#latex-preview)
   - [Environment](#environment)
   - [Options](#options)
- [Additional language details](#additional-language-details)
   - [Implementation](#implementation)
   - [Syntax specification](#syntax-specification)

<!--startExclude-->

Getting started
===============

Compiler website
----------------
The Subzero compiler is available through the [cryptimeleon website](https://cryptimeleon.org/subzero).

Local build
-----------
You can also run an instance of the website yourself as a [Docker](https://www.docker.com/) container after cloning the repo.
```
git clone https://github.com/cryptimeleon/subzero.git
cd subzero/
docker build -t subzero .
docker run -p 8080:8080 subzero:latest
```

Go to http://localhost:8080/subzero
<!--endExclude-->


Tutorial
========
Introduction
------------
A Subzero program describes a single zero knowledge protocol.

All of the examples in this tutorial can be easily loaded into the code editor using the [examples dropdown](#examples-dropdown). It is recommended to follow along in the code editor, and use the [Environment](#environment) tab to view extra information about the protocol.

DLog equality
----------------
We begin with a simple first protocol that proves the equality of two discrete logarithms.

`
witness: k
b = a^k & h = g^k
`

The protocol starts with a witness variable declaration list, that declares a single witness variable `k`. Below this is the proof expression, which describes the protocol's proof of knowledge. It consists of a conjunction expression composed of two equality expressions, `b = a^k` and `h = g^k`.

The witness variable `k` is declared explicitly. The other variables, `a`, `b`, `h`, and `g` are common input variables that have been declared implicitly.


Pedersen commitment
-------------------


Pointcheval Sanders credential
------------------------------





The next section will further explain the language details introduced in this section.

The Subzero language
====================

Identifiers
-----------
Subzero has two types of identifiers: function identifiers and variable identifiers. All identifiers are case-sensitive.

### Function identifiers
A function identifier must begin with a letter, and can contain letters and numbers.

### Variable identifiers
A variable identifier must start with a letter, and can contain letters and numbers.

The identifier can also contain special formatting fragments, which allow for formatting of variables in the [LaTeX Preview](#latex-preview) tab. If you do not intend to use the preview, the rest of this section can be skipped.

The variable can have any number of terminating single quotes, or terminating substrings 'Prime', to add prime symbols after a variable name.

```
x'
x'''
xPrime
xPrimePrime
```

The variable can have a terminating underscore, or terminating substring 'Bar', to add a bar over the variable name.
```
x_
xBar
```

The variable can have a terminating tilde, or terminating substring 'Tilde', to add a tilde over the variable name.

```
x~
xTilde
```

The variable can have a terminating substring 'Hat', to add a hat over the variable name. Although intuitive, the caret cannot be used to add a hat as it is used as the exponentiation operator.

```
xHat
```

The variable can have a nonstarting and nonterminating underscore, or a nonstarting and nonterminating substring 'Sub', to add the portion immediately after as a subscript.
```
x_2
x_new
xSub2
xSubA
```

In the case where multiple of these features are used in an identifier, the fragments have a designated order. If symbols ('~', '_') are used for the tilde/bar, then the subscript fragment goes before the tilde/bar fragment, which goes before the prime fragment. Else if the substrings ('Tilde', 'Bar', 'Hat') are used, the tilde/bar/hat fragment goes before the subscript fragment, which goes before the prime fragment.

`
x_1~'
xTildeSub1Prime
`

If the name of a variable (excluding all other formatting fragments) is the name of a Greek letter in all lowercase letters, it will be displayed in the LaTeX preview as the Greek symbol. For uppercase Greek letters, simply capitalize the first letter of the name. Some shorthand names of Greek letters are also allowed.

`
theta
sigma_1'
eps
`

<details>
<summary>Click here to view all supported Greek letters</summary>

| Name | Greek letter | 
|:----:|:------------:|
|alpha|&alpha;|
|beta|&beta;|
|gamma|&gamma;|
|Gamma|&Gamma;|
|delta|&delta;|
|Delta|&Delta;|
|eps|&epsilon;|
|epsilon|&epsilon;|
|zeta|&zeta;|
|eta|&eta;|
|theta|&theta;|
|Theta|&Theta;|
|iota|&iota;|
|kappa|&kappa;|
|lambda|&lambda;|
|Lambda|&Lambda;|
|mu|&mu;|
|nu|&nu;|
|xi|&xi;|
|Xi|&Xi;|
|pi|&pi;|
|Pi|&Pi;|
|rho|&rho;|
|sigma|&sigma;|
|Sigma|&Sigma;|
|tau|&tau;|
|ups|&upsilon;|
|upsilon|&upsilon;|
|Ups|&Upsilon;|
|Upsilon|&Upsilon;|
|phi|&phi;|
|Phi|&Phi;|
|chi|&chi;|
|psi|&psi;|
|Psi|&Psi;|
|omega|&omega;|
|Omega|&Omega;|
</details>

Greek letters which share the same symbol as an English letter, such as capital alpha (A), are not supported. This is to avoid confusion from allowing distinct variable identifiers that would be visually identical in the LaTeX preview. 

Type and scope
--------------
Subzero uses a typing system where variables have both an algebraic type, and a proof role (type and role for short).

### Types
There are three distinct types in Subzero: `boolean`, `exponent`, and `group element`. Variables and function parameters can either be of type `exponent` or `group element`. A function's return type can be `boolean`, `exponent`, or `group element`. Types are not declared; instead, all variable, parameter and return types are inferred based on their context, and semantic errors will be shown if they are used in conflicting type contexts.

### Roles
The role determines the usage of a variable within the protocol. Every variable is either a witness variable, a public parameter variable, a common input variable, or a local variable. Witness and public parameter variables are declared explicitly within the witness and public parameter lists, respectively. Local variables are declared in the parameter list of their corresponding function definition. All other variables are implicitly declared as common input variables. This mix of explicit/implicit declaration is intended for readability and to keep protocols looking similar to the existing notation in literature.

### Group Types
All variables of algebraic type `group element` also have a group type. By default, the group type is `G1`. When pairing functions are used in a protocol, then the group type can also be `G2` or `GT`. See [pairing functions](#pairing-functions) for more details.

Note that function parameters will never have a group type, even if they have type `group element`.

### Scope
All function parameters have scope limited to the function body. All other variables, whether declared explicitly in a variable declaration list, or declared implicitly in the proof expression or any function body, have global scope.

Expressions
-----------
An expression is a construct that evaluates to a value of a specific type. Each expression is either an operation (which consists of an operator and operand expressions) or a value.

There are two main categories of expressions: algebraic and logical.

Algebraic expressions include sums, products, exponentiations, negations, variables, number literals, and function calls which return an algebraic expression. They evaluate to either an `exponent` or `group element` value.

Logical expressions include conjunctions, disjunctions, equalities, inequalities, and function calls which return a logical expression. They evaluate to a `boolean` value. Two other subcategories are used to further describe logical expressions.
Propositional expressions include conjunctions and disjunctions.
Comparison expressions include equalities and inequalities.

Values
------
### Function calls
A function call consists of the name of a valid function followed by a comma-delimited list of arguments enclosed in parantheses, where each argument is an algebraic expression.

```foo(a + b, bar(x,r), x, y^(a+b))```

The type of a function call is the return type of the corresponding function.

### Number literals
A number literal consists of any integer, and has type `exponent`.

### Variables
Variable names must use a valid [variable identifier](#variable-identifiers).

Operators
---------
The language has logical, comparison, and algebraic operators. These include various unary, binary, and ternary operators.

The following table shows all operators, with their precedence and associativity (when relevant). Operators are listed in descending precedence from top to bottom.

| Precedence | Operator | Description | Associativity | Type |
|:----------:|:--------:|:-----------:|:-------------:|:-----|
| 1 | x() | Function call | - | - |
| 2 | -x | Unary negation | - | Algebraic |
| 3 | x ^ y | Exponentiation | Right to left | Algebraic |
| 4 | x * y | Multiplication | Left to right | Algebraic |
| 4 | x / y | Division | Left to right | Algebraic |
| 5 | x + y | Addition | Left to right | Algebraic |
| 5 | x - y | Subtraction | Left to right | Algebraic |
| 6 | x = y | Equality | - | Comparison | Comparison |
| 6 | x > y | Greater than | - | Comparison |
| 6 | x < y | Less than | - | Comparison |
| 6 | x >= y | Greater than or equal | - | Comparison |
| 6 | x <= y | Less than or equal | - | Comparison |
| 6 | x > y >= z | Double inequality | - | Comparison |
| 7 | x \| y | Disjunction | Left to right | Logical |
| 8 | x & y | Conjunction | Left to right | Logical |

An expression surrounded by parentheses is also an expression, so the precedence order does not need to be remembered as long as sufficient parentheses are used.

### Conjunction
Conjunction expressions evaluate to a `boolean`, and both operands must be `boolean`. An expression evaluates to true if and only if the expressions A and B both evaluate to true.

```A & B```

### Disjunction
Disjunction expressions evaluate to a `boolean`, and both operands must be `boolean`. An expression evaluates to true if and only if at least one of the expressions A and B evaluate to true.

```A | B```

### Equality
Equality expressions evaluate to a `boolean`, and the operands must be either both `exponent` or both `group element`.

```A = B```

### Inequality
Inequality expressions evaluate to a `boolean`, and both operands must have type `exponent`.

#### Greater than
```A > B```

#### Less than
```A < B```

#### Greater than or equal to
```A >= B```

#### Less than or equal to
```A <= B```

#### Double inequality
```A > B > C```  

```A >= B > C```  

```A > B >= C```  

```A >= B >= C```  

```A < B < C```  

```A <= B < C```  

```A < B <= C```  

```A <= B <= C```

Comparisons (equalities and inequalities) can also have a subprotocol name specified after the expression that will appear in the generated code. It uses the same syntax as the protocol name.

```g^x * h^r = C [Example subprotocol]```

### Sum
Sum expressions evaluate to an `exponent`, and both operands must have type `exponent`.

#### Addition
```A + B```

#### Subtraction
```A - B```

### Product
Product expressions evaluate to an `exponent` or a `group element`. The operands must both have type `exponent`, or both have type `group element`.

#### Multiplication
```A * B```

##### Division
```A / B```

### Exponentiation
Exponentiation expressions evaluate to an `exponent` if the left operand is of type `exponent`, or to a `group element` if the left operand is of type `group element`. The right operand must be of type `exponent`.

```A ^ B```

### Unary negation
Negation expressions evaluate to an `exponent` and the operand must be of type `exponent`.

```-A```

Pairings
--------
Subzero currently has one built-in function: the pairing function `e`. The function takes two `group element` parameters, and returns a `group element`. Any `group element` variables within the expression passed as the second parameter will have group type `G2`, and any variables involved in an equality expression that contains a pairing (but not passed into the function) will have group type `GT`. All other `group element` variables have group type `G1`.

For example, in the expression `z = e(g, h)^x`, `z` has group type `GT`, `g` has group type `G1`, and `h` has group type `G2`.

If a `group element` variable is used in multiple contexts with conflicting group types, a semantic error will be shown.

Comments
--------
Single line comments start with `//`.
Multi line comments start with `/*` and end with `*/`.
Note that Subzero comments will not be included anywhere in the generated Java code.

```
// This is a single line comment
/* This is
   a multi-line
   comment */
```

Subzero program layout
----------------------
A Subzero program specifies a single zero knowledge proof of knowledge protocol.
A program consists of a protocol name, function definitions, variable declarations, and a proof expression.

### Protocol name
The protocol name is an optional string that will be used to name the generated classes during compilation; if omitted, a default protocol name will be used. It must be the first line of the program, between a pair of square brackets. The name must start with a letter, and can be followed by letters, numbers, underscores, and spaces.

```[My example protocol]```

During generation, the protocol name will be converted to a PascalCase prefix for class names. For example, the above name will become the prefix `MyExampleProtocol`.

### Function definitions
Zero or more functions can be defined at the start of the program, after the protocol name.
A function definition starts with a function name, which must be a valid [function identifier](#function-identifiers)
This is followed by a comma-separated list of parameter names inside parentheses, and finally a single expression inside curly braces. Both the expression and right curly brace can be optionally followed by a semicolon. Parameter names must be valid [variable identifiers](#variable-identifiers).

```
foo(a, b, c) {
  a + b + c;
};

bar(a, b, c) {
   a = b & 2 <= c < 10
}
```

Since all functions are pure functions (i.e. no side effects), they are also inlinable. To make a function inlined, prefix it with the `inline` keyword. In the generated Java code, a non-inlined function will generate as a class method and function calls to that method, whereas an inline function will generate the function body expression in place of every function call to it.

```
inline baz(g, x) {
   g^x
}
```

Any variable that references a parameter in the function is called a local variable.
Any other variable is a global variable, and can reference a witness variable, public parameter variable, or common input variable.

All parameters in the parameter list should be referenced at least once by a local variable, so that type inference can occur.
A warning will appear if there is a parameter with no variable referencing it.

Note that function definitions cannot contain function calls.

### Variable declarations
Variables are declared after any function definitions.

A variable declaration list begins with a role keyword with an optional colon, followed by a comma-separated list of variable names, with an optional semicolon at the end. The valid keywords are `witness` for witness variables, `pp` for public parameter variables, and `common` for common input variables. At most one variable declaration list for each role is allowed in a program, and a protocol must contain at least one witness variable.

Witness variables and public parameter variables are declared explicitly, whereas common input variables are declared implicitly by default (and thus a `common` declaration list is never necessary). Common input variables can also be declared explicitly if desired; if at least one common input variable is declared explicitly, then no common input variables are allowed to be implicitly declared.

Implicit declaration of common input variables allows for more readable protocols, that more closely resemble protocols in literature. Explicit declaration of common input variables ensures that variables are not implicitly declared by accident as a result of typos.

```
pp: a, b, c;
witness: d, e, f;
common: g, h, i;
```


```pp: m1, m2;```

```pp m1```

```witness: x, r;```

```witness x, r```

### The proof expression
This expression describes the zero knowledge argument of knowledge protocol, and is written after all variable declarations. It consists of a single logical expression followed by an optional semicolon. The expression can also be prefixed with the keyword `statement` with an optional colon after.

When the protocol is run, this expression evaluates to either true or false, signifying whether the protocol was run successfully or not.

Compiler website features
=========================
### Code editor
The editor has the following features:
- Syntax highlighting
- Syntax errors will occur when the entered text does not match the described EBNF grammar.
Validation errors and warnings will occur when the entered text does not match the additional semantic rules that dictate the structure of Subzero programs.
Errors will be displayed with a red X, and the corresponding error location will be underlined in red.
Warnings will be displayed with a yellow triangle, and the corresponding warning location will be underlined in yellow.
The red X and yellow triangle can be hovered over with the mouse to display information about the error/warning.
- Bracket matching
- Auto-indentation
- Increase or decrease editor font size with `Ctrl+'+'` and `Ctrl+'-'`
- Use `Ctrl+'l'` to jump to a specific line number
- Use `Ctrl+'s'` to save the Subzero program code 

### Compiler
Once a valid Subzero program is written, it can be compiled. This will generate a complete Java program (buildable with [Gradle](https://gradle.org/)) that specifies and runs the protocol using the Cryptimeleon [Math](https://github.com/cryptimeleon/math) and [Craco](https://github.com/cryptimeleon/craco) libraries. Note that because syntax and semantic errors are raised as a program is typed, once the protocol is free from errors in the editor then there will be no errors during compilation. If any compilation error is encountered, opening a [Github issue](https://github.com/cryptimeleon/subzero/issues) would be appreciated.

### Example protocols
A dropdown menu allows you to load existing example protocols into the editor. This is an easy way to become familiar with the language.

### LaTeX preview
[MathJax](https://www.mathjax.org/) is used to display formatted LaTeX based on the code in the editor. If the Subzero code is free of syntax and semantic errors, the LaTeX Preview tab will display a formatted LaTeX interpretation of the Subzero code. Because variable identifiers support  support a single non-terminating underscore, this allows for variables with subscripts, and because of the supported terminating single quotes, variables can also have the prime symbol at the end. A .tex file can also be downloaded containing the LaTeX text.

### Environment
The Environment tab displays information about all variables and functions, and updates as a protocol is written. For variables, the proof role and algebraic type are displayed, as well as the group type when relevant. For functions, the parameter types, return type, and origin (built-in function or user-defined) are displayed. Clicking a column header will also sort the table rows by that column.

### Options
By default, the program will be compiled and downloaded as a zipped Java project. In the Options tab you can choose to generate only certain classes of the project, and also view the Java classes in the website editor. This is useful for seeing how changes in the Subzero code affect the generated Java classes, without having to constantly unzip a full project.

Additional language details
===========================
The following section provides extra information about the language that is not necessary to know to write protocols, but provides greater insight for those who are curious.

Implementation
--------------
The Subzero compiler is written in Java and [Xtend](https://www.eclipse.org/xtend/), using the [Xtext](https://www.eclipse.org/Xtext/) language development framework. The compiler website is built with [Svelte](https://svelte.dev/), with [Ace](https://ace.c9.io/) for the code editor and [Carbon Design System](https://github.com/carbon-design-system/carbon) for the UI.

Syntax specification
--------------------
The following describes the [EBNF](https://en.wikipedia.org/wiki/Extended_Backus%E2%80%93Naur_form) (Extended Backus-Naur Form) specification of the DSL grammar.

Note that some programs that follow this syntax are not necessarily valid, as there are additional semantic rules for a valid protocol. A Subzero program consists of a single ```<protocol>```.

```
<protocol> ::= 
   <protocol-name>?
   <function-definition>* 


(<pp-list> ';'?)? <witness-list> ';'? 
   ('statement' ':'?)? <expression> ';'?

<function-definition> ::= 'inline'? <identifier> <parameter-list> '{' <expression> ';'? '}' ';'?
<parameter-list> ::= '(' (<parameter> (',' <parameter>)*)? ')'
<parameter> ::= <identifier>

<witness-list> ::= 'witness' ':'? (<witness> (',' <witness>)*)
<witness> ::= <identifier>

<pp-list> ::= 'pp' ':'? (<pp> (',' <pp>)*)
<pp> ::= <identifier>

<constant-list> ::= 'common' ':'? (<constant> (',' <constant>)*)
<constant> ::= <identifier>

<expression> ::= <conjunction>
<conjunction> ::= <disjunction> | <conjunction> '&' <disjunction>
<disjunction> ::= <comparison> | <disjunction> '|' <comparison>
<comparison> ::= <sum> | <comparison> <operator> <sum> (<operator> <sum>)? <protocol-name>?
<sum> ::= <product> | <sum> '+' <product> | <sum> '-' <product>
<product> ::= <power> | <product> '*' <power> | <product> '/' <power>
<power> ::= <construct> | <construct> '^' <power>
<construct> ::= <tuple> | <negative>
<tuple> ::= '(' <conjunction> (',' <conjunction>)+ ')'
<negative> ::= <value> | '-' <value>
<value> ::= <function-call> | <variable> | <number-literal> | '(' <brackets> ')'
<function-call> ::= <identifier> '(' (<argument> (',' <argument>)*)? ')'
<argument> ::= <conjunction>
<variable> ::= <identifier>
<number-literal> ::= [0-9]+
<brackets> ::= <conjunction>

<operator> ::= '=' | '<' | '<=' | '>' | '>='
<protocol-name> ::= '[' [a-zA-z] [a-zA-Z0-9_' ]* ']'
<identifier> ::= [a-zA-Z] [a-zA-Z0-9_']*
```
<!---

Semantic rules
----------------
These are additional validation rules which dictate the allowed structure and data of Subzero programs.


### Function definition rules
- Function definitions cannot contain any function calls
- Every function parameter should be used at least once in the function definition (warning)
- User-defined functions should be called at least once in the proof expression (warning)

### Witness list rules
- The witness list must contain at least one witness

### Function calls
- Function calls must reference either a valid user defined function or predefined function
- The number of arguments in a function call must match the number of parameters in the function definition
- The type of each argument in a function call must match the type of each parameter in the function definition
- Function calls cannot contain logical expressions as arguments, only algebraic expressions
- A function call whose return type is `boolean` cannot be within an algebraic expression or a comparison expression
- A function call whose return type is `exponent` or `group element` must be contained within a comparison expression

### Expression rules
- Conjunctions cannot be nested within algebraic expressions or comparison expressions
- Disjunctions cannot be nested within algebraic expressions or comparison expressions
- Comparison expressions cannot be contained within algebraic expressions or other comparison expressions
- Algebraic expressions in the proof expression must be nested within a comparison expression or function call
- Algebraic expressions must be contained within a comparison expression before being contained within a propositional expression

-->
