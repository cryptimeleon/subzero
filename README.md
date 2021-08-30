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
   - [Basic proof of partial knowledge](#basic-proof-of-partial-knowledge)
   - [Pointcheval Sanders credential](#pointcheval-sanders-credential)

- [The Subzero language](#the-subzero-language)
   - [Type system](#type-system)
   - [Program layout](#program-layout)
   - [Protocol name](#protocol-name)
   - [Function definitions](#function-definitions)
   - [Variable declarations](#variable-declarations)
   - [The proof expression](#the-proof-expression)
   - [Scope](#scope)
   - [Identifiers](#identifiers)
   - [Expressions](#expressions)
   - [Values](#values)
   - [Operators](#operators)
   - [Pairings](#pairings)
   - [Comments](#comments)

- [Compiler website features](#compiler-website-features)
   - [Code editor](#code-editor)
   - [Example protocols](#example-protocols)
   - [Compiler](#compiler)
   - [LaTeX preview](#latex-preview)
   - [Environment](#environment)
   - [Options](#options)

- [Additional language details](#additional-language-details)
   - [Implementation](#implementation)
   - [Generated Java project](#generated-java-project)
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
A Subzero program describes a single zero knowledge protocol. The language grammar is designed to be close to protocols in literature, similar to Camenisch-Stadler notation.

All of the examples in this tutorial can be easily loaded into the code editor using the [example protocols dropdown](#example-protocols). It is recommended to follow along in the code editor, and use the [Environment](#environment) tab to view extra information about the protocol.

DLog equality
-------------
We begin with a simple first protocol that proves the equality of two discrete logarithms.

```
witness: k
b = a^k & h = g^k
```

The protocol starts with a witness variable declaration list, that declares a single witness variable `k`. Below this is the proof expression, which describes the protocol's proof of knowledge. It consists of a conjunction expression (`&`) composed of two equality (`=`) expressions, `b = a^k` and `h = g^k`.

The witness variable `k` is declared explicitly. The other variables, `a`, `b`, `g`, and `h` are common input variables that have been declared implicitly.

Every variable has an algebraic type, that is either `group element` or `exponent`, which is inferred based on its context. By default, a variable is `group element` unless it appears in a specific `exponent` context. In the protocol above, `k` has type `exponent`, since it appears in the exponentiation (`^`) expressions `a^k` and `g^k`. The other variables `a`, `b`, `g`, and `h` have type `group element`. The [Environment](#environment) tab can be used to see the inferred type of all variables.

Pedersen commitment
-------------------
```
[Pedersen commitment with range proof]

pp : h_1, h_2, g
witness : m_1, m_2, r

C_1 = h_1^m_1 * h_2^m_2 * g^r & 20 <= m_1 + m_2 <= 100

```

This protocol introduces a few more concepts. First, we start the protocol with a protocol name in between square brackets. This string will be used to name the Java classes in the generated project; if omitted, a default will be used.

Next, we have a new variable declaration list beginning with the keyword `pp`, which explicitly declares public parameter variables. Once again, witness variables are explicitly declared, and the remaining variables are implicitly declared common input variables.

The protocol also has a double inequality expression `20 <= m_1 + m_2 <= 100`, which represents a range proof. Subzero supports both single and double inequalities with the usual relational operators (`<`, `>`, `<=`, `>=`). `*` and `+` are used for multiplication and addition expressions. `/` and `-` are used similarly for division and subtraction expressions.

Basic proof of partial knowledge
--------------------------------
```
[Partial knowledge]

witness: x,r;
g^x * h^r = C
& (h^r = C_2 | h^x = C_2)
```
This protocol contains a disjunction (`|`) expression `h^r = C_2 | h^x = C_2` which represents a proof of partial knowledge. Because the two operand equality expressions `h^r = C_2` and `h^x = C_2` are quite similar, we can move these out into a function `checkDLog` and replace the expressions with two function calls.

```
[Partial knowledge]

checkDLog(y) {
  h^y = C_2
}

witness: x,r;
g^x * h^r = C
& (checkDLog(r) | checkDLog(x))
```

The function definition consists of the function identifier `checkDLog`, the parameter declaration list (only `y`), and the function body expression `h^y = C_2`. Function bodies can implicitly declare common input variables (`C_2` in this case), and use implicitly declared common input variables from the proof expression or other function definitions (`h` in this case). All function definitions must appear after the protocol name (if present) and before variable declarations.

Since this function is rather short, we may want it to be inlined in the generated code. To do this, we simply add the `inline` keyword.
```
inline checkDLog(y) {
  h^y = C_2
}
```

Pointcheval Sanders credential
------------------------------
```
[Pointcheval Sanders credential showing]

witness: age, pos, r

e(sigma_1', X~) * e(sigma_1', Y_1~^age * Y_2~^pos) * e(sigma_1', g~)^r = e(sigma_2', g~) // valid signature
& (age < 18 | pos = 17) // young or student

```

The final example protocol shows a few more features. Single-line comments start with `//`. Multi-line comments are also supported between `/*` and `*/`.

Variable identifiers support more than letters and numbers, to allow special formatting in the [LaTeX preview](#latex-preview) tab. Underscores allow for subscripts, tildes allow for overtildes, single quotes allow for prime symbols, and names of Greek letters will display as the Greek symbol.

Finally, there are several function calls to the `e` function. This is the built-in pairing function, which allows for constructing pairing-based schemes, and takes in two `group element` expressions to pair.

The next section will further explain the language details introduced in this section.

The Subzero language
====================

Type system
-----------
Subzero uses a type system where variables have both an algebraic type, and a proof role (type and role for short).

### Types
There are three distinct types in Subzero: `boolean`, `exponent`, and `group element`. Variables and function parameters can either be of type `exponent` or `group element`. A function's return type can be `boolean`, `exponent`, or `group element`. Types are not declared; instead, all variable, parameter and return types are inferred based on their context, and semantic errors will be shown if they are used in conflicting type contexts.

### Roles
The role determines the usage of a variable within the protocol. Every variable is either a witness variable, a public parameter variable, a common input variable, or a local variable. Witness and public parameter variables are declared explicitly in [variable declaration lists](#variable-declarations). Local variables are declared in the parameter list of their corresponding function definition. All other variables are implicitly declared as common input variables; alternatively, common input variables can also be declared explicitly in a variable declaration list.

The role is not relevant for function return values, or when passing in function arguments.

### Group Types
All variables of algebraic type `group element` also have a group type. By default, the group type is `G1`. When pairings are used in a protocol, then the group type can also be `G2` or `GT`. See [pairings](#pairings) for more details.

Note that function parameters and function return types will never have a group type, even if they have type `group element`.

Program layout
--------------
A Subzero program specifies a single zero knowledge proof of knowledge protocol.
A program consists of an optional protocol name, optional function definitions, variable declarations, and a proof expression. The protocol must specify these in the given order.

```
// Protocol name
[Example protocol]

// Function definitions
foo(a, b) {
   ...
}

inline bar(c, d) {
   ...
}

// Variable declarations
witness: ...
pp: ...
common: ...

// Proof expression
...

```

Protocol name
-------------
The protocol name is an optional string that will be used to name the generated classes during compilation; if omitted, a default protocol name will be used. It must be the first line of the program, between a pair of square brackets. The name must start with a letter, and can be followed by letters, numbers, underscores, and spaces.

```[My example protocol]```

During generation, the protocol name will be converted to a PascalCase prefix for class names. For example, the above name will become the prefix `MyExampleProtocol`.

Function definitions
--------------------
Zero or more functions can be defined at the start of the program, after the protocol name. A function definition starts with a function name, which must be a valid [function identifier](#function-identifiers). This is followed by a comma-separated list of parameter names inside parentheses, and finally a single expression inside curly braces. Both the expression and right curly brace can be optionally followed by a semicolon. Parameter names must be valid [variable identifiers](#variable-identifiers).

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

Any variable that references a parameter in the function is called a local variable. Any other variable is a global variable, and can reference a witness variable, public parameter variable, or common input variable.

All parameters in the parameter list should be referenced at least once by a local variable, so that type inference can occur. A warning will appear if there is a parameter with no variable referencing it.

Note that function definitions cannot contain function calls or disjunctions at this time.

Variable declarations
---------------------
Variables are declared after any function definitions.

A variable declaration list begins with a role keyword with an optional colon, followed by a comma-separated list of variable names, with an optional semicolon at the end. The valid keywords are `witness` for witness variables, `pp` for public parameter variables, and `common` for common input variables. At most one variable declaration list for each role is allowed in a program, and a protocol must contain at least one witness variable.

Witness variables and public parameter variables are always declared explicitly, whereas common input variables are declared implicitly by default (and thus a `common` declaration list is never necessary). Common input variables can also be declared explicitly if desired; if at least one common input variable is declared explicitly, then no common input variables are allowed to be implicitly declared.

Implicit declaration of common input variables allows for more readable protocols that more closely resemble protocols in literature. Explicit declaration of common input variables ensures that variables are not implicitly declared by accident as a result of typos.

```
pp: a, b, c;
witness: d, e, f;
common: g, h, i;
```

```
witness x, r
pp m1
```

```
pp: m1, m2;
witness: x, r;
common: g, h;
```

The proof expression
--------------------
This expression describes the zero knowledge argument of knowledge protocol, and is written after all variable declarations. It consists of a single logical expression followed by an optional semicolon. The expression can also be prefixed with the keyword `statement` with an optional colon after.

When the protocol is run, this expression evaluates to either true or false, signifying whether the protocol was run successfully or not.

```
b = a^k & h = g^k
```

```
statement: g^x * h^r = C_1 & h^r = C_2;
```

Nothing can be written after the proof expression.

Scope
-----
All function parameters have scope limited to the function body. All other variables, whether declared explicitly in a variable declaration list, or declared implicitly in the proof expression or any function body, have global scope.

Identifiers
-----------
Subzero has two types of identifiers: function identifiers and variable identifiers. All identifiers are case-sensitive.

### Function identifiers
A function identifier must begin with a letter, and can contain letters and numbers.

### Variable identifiers
A variable identifier must start with a letter, and can contain letters and numbers, as well as some special characters under certain conditions.

The identifier can contain special formatting fragments, which allow for formatting of variables in the [LaTeX Preview](#latex-preview) tab. If you do not intend to use the preview, the rest of this section can be skipped.

The variable can have any number of terminating single quotes, or terminating substrings `Prime`, to add prime symbols after a variable name.

```
x'
x'''
xPrime
xPrimePrime
```

The variable can have a terminating underscore, or terminating substring `Bar`, to add a bar over the variable name.
```
x_
xBar
```

The variable can have a terminating tilde, or terminating substring `Tilde`, to add a tilde over the variable name.

```
x~
xTilde
```

The variable can have a terminating substring `Hat`, to add a hat over the variable name. Although intuitive, the caret cannot be used to add a hat as it is used as the exponentiation operator.

```
xHat
```

The variable can have a nonstarting and nonterminating underscore, or a nonstarting and nonterminating substring `Sub`, to add the portion immediately after as a subscript.
```
x_2
x_new
xSub2
xSubA
```

In the case where multiple of these features are used in an identifier, the fragments have a designated order. If symbols (`~`, `_`) are used for the tilde/bar, then the subscript fragment goes before the tilde/bar fragment, which goes before the prime fragment. Otherwise if the substrings (`Tilde`, `Bar`, `Hat`) are used, the tilde/bar/hat fragment goes before the subscript fragment, which goes before the prime fragment.

```
x_1~'
xTildeSub1Prime
```

If the name of a variable (excluding all other formatting fragments) is the name of a Greek letter in all lowercase letters, it will be displayed in the LaTeX preview as the Greek symbol. For uppercase Greek letters, simply capitalize the first letter of the name. Some shorthand names of Greek letters are also allowed.

```
theta
sigma_1'
eps
```

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

Greek letters which share the same symbol as an English letter, such as capital alpha (A), are not supported. 

All Subzero identifiers with special formatting will be converted to valid Java identifiers during compilation.

Expressions
-----------
An expression is a value or an operation (which consists of an operator and operand expressions) that evaluates to a specific value of a certain type. There are three kinds of expression: logical, comparison, and algebraic.

Logical expressions include conjunctions and disjunctions, and function calls which return a logical expression. They evaluate to a `boolean` value.
Comparison expressions include equalities and inequalities, and function calls which return a comparison expression. They evaluate to a `boolean` value.
Algebraic expressions include sums, products, exponentiations, negations, variables, number literals, and function calls which return an algebraic expression. They evaluate to either an `exponent` or `group element` value.

Values
------
### Function calls
A function call consists of the name of a valid function followed by a comma-delimited list of arguments enclosed in parentheses, where each argument is an algebraic expression.

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

| Precedence | Operator | Description | Associativity | Category |
|:----------:|:--------:|:-----------:|:-------------:|:--------:|
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

### Conjunction (logical AND)
Conjunction expressions evaluate to a `boolean`, and both operands must be `boolean`. An expression evaluates to true if and only if the expressions A and B both evaluate to true.

```A & B```

### Disjunction (logical OR)
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

#### Division
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

Compiler website features
=========================
The compiler website has many features to help make writing Subzero protocols easier.

Code editor
-----------
The editor has the following features:
- Syntax highlighting
- Syntax errors
- Descriptive semantic errors and warnings (hover over the red X or yellow triangle)
- Bracket matching
- Auto-indentation
- Control font size with `Ctrl+'+'` and `Ctrl+'-'`
- Use `Ctrl+'l'` to jump to a specific line number
- Use `Ctrl+'f'` and `Ctrl+'h'` for find and replace
- Use `Ctrl+'s'` to save the Subzero program code

Example protocols
-----------------
A dropdown menu allows you to load existing example protocols into the editor. This is an easy way to become familiar with the language.

Compiler
--------
Once a valid Subzero program is written, it can be compiled. This will generate a complete Java program (buildable with [Gradle](https://gradle.org/)) that specifies and runs the protocol using the Cryptimeleon [Math](https://github.com/cryptimeleon/math) and [Craco](https://github.com/cryptimeleon/craco) libraries. Note that because syntax and semantic errors are raised as a program is typed, once the protocol is free from errors in the editor then there should be no errors during compilation. If any compilation error is encountered, opening a [Github issue](https://github.com/cryptimeleon/subzero/issues) would be appreciated.

LaTeX preview
-------------
This tab displays formatted LaTeX based on the code in the editor. If the Subzero code is free of syntax and semantic errors, the LaTeX Preview tab will display a formatted LaTeX interpretation of the Subzero code. Because variable identifiers support special formatting fragments, this allows for variables with subscripts, tildes, bars, hats, primes, and Greek letters. A TEX file can also be downloaded containing the LaTeX text.

Environment
-----------
The Environment tab displays information about all variables and functions, and updates as a protocol is written. For variables, the proof role and algebraic type are displayed, as well as the group type when relevant. For functions, the parameter types, return type, and origin (built-in function or user-defined) are displayed. Clicking a column header will also sort the table rows by that column.

Options
-------
By default, the program will be compiled and downloaded as a zipped Java project. In the Options tab you can choose to generate only certain classes of the project, and also view the Java classes in the website editor. This is useful for seeing how changes in the Subzero code affect the generated Java classes, without having to constantly unzip a full project.

Additional language details
===========================
The following section provides extra information about the language that is not necessary to know to write protocols, but provides greater insight for those who are curious.

Implementation
--------------
The Subzero compiler is written in Java and [Xtend](https://www.eclipse.org/xtend/), using the [Xtext](https://www.eclipse.org/Xtext/) language development framework. The compiler website is built with [Svelte](https://svelte.dev/). It uses [Ace](https://ace.c9.io/) for the code editor, [MathJax](https://www.mathjax.org/) for the LaTeX preview, and [Carbon Design System](https://github.com/carbon-design-system/carbon) for the UI. 

Generated Java project
----------------------
When a Subzero protocol is compiled, it will generate a full Java project buildable with Gradle, containing 2-3 classes.

### Protocol class
This class provides the specification of the zero knowledge proof of knowledge protocol, using the Cryptimeleon Math and Craco libraries.

### Public parameters class
This class is required whenever the protocol contains an inequality expression (a range proof), or a disjunction expression (a partial proof of knowledge) that is contained anywhere in a conjunction expression.

### Test class
This class will create an instance of the protocol and run it. Variables will be instantiated with default values (which may result in the test failing), and thus this class should be edited as needed.

Syntax specification
--------------------
The following describes the [EBNF](https://en.wikipedia.org/wiki/Extended_Backus%E2%80%93Naur_form) (Extended Backus-Naur Form) specification of the DSL grammar.

Note that some programs that follow this syntax are not necessarily valid, as there are additional semantic rules for a valid protocol. The grammar is designed to be more permissive than necessary to allow for detailed semantic errors, as opposed to obscure syntax errors.

A Subzero program consists of a single ```<protocol>```.

```
<protocol> ::= 
   <protocol-name>?
   <function-definition>* 
   ((<witness-list> | <pp-list> | <constant-list>) ';'?)+
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


