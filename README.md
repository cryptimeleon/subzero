Subzero - Zero Knowledge Compiler
============================
Subzero is a declarative domain-specific language (DSL) that enables the specification of zero knowledge protocols. It is written in Java and [Xtend](https://www.eclipse.org/xtend/) and uses the [Xtext](https://www.eclipse.org/Xtext/) language development framework.
This website features a code editor for writing Subzero programs, and a compiler to generate a Java project that can execute the protocol described by Subzero code.

Using the compiler
==================
The compiler website is available [here](https://cryptimeleon.org/subzero).

Building the project
====================
You can also run an instance of the website yourself. Make sure that you are using Java 11 and have [Maven](https://maven.apache.org/) installed. After downloading the repo, run the following commands:
```
cd org.cryptimeleon.subzero.parent/
mvn install
```
Next, to start the web server, run:
```
cd org.cryptimeleon.subzero.web/
mvn jetty:run
```
Go to http://localhost:8080/subzero


Getting started with Subzero
============================
### Identifiers
---
Subzero has two types of identifiers: function identifiers and variable identifiers.

#### Function identifiers
A function identifier must begin with a letter, and can contain letters and numbers.

#### Variable identifiers
A variable identifier must start with a letter, can contain letters and numbers, can contain at most one underscore, and any number of terminating single quotes. Single quotes and one underscore are allowed so that identifiers can contain subscripts or the prime symbol, respectively, which allows for nicer LaTeX output.

### Types
---
There are three distinct types in Subzero: `boolean`, `exponent`, and `group element`. Variables and function parameters can either be of type `exponent` or `group element`. A function's return type can be `boolean`, `exponent`, or `group element`. Types are not declared; instead, all variable, parameter and return types are inferred based on their context, and semantic errors will be shown if they are used in conflicting type contexts.

### Roles
---
In addition to a type, all variables have a role. This role determines the usage of the variable within the protocol. Every variable is either a witness variable, a public parameter variable, a common input variable, or a local variable. Witness and public parameter variables are declared explicitly within the witness and public parameter lists, respectively. Local variables are declared in the parameter list of their corresponding function definition. All other variables are implicitly declared as common input variables. This mix of explicit/implicit declaration is intended for readability and to keep protocols looking similar to the existing notation in literature.

### Expressions
---
An expression is a construct that evaluates to a value of a certain type. Each expression is either an operation (which consists of an operator and operand expressions) or a value.

There are two main categories of expressions: logical and algebraic.
Logical expressions include conjunctions, disjunctions, equalities, inequalities, and function calls which return a logical expression. They evaluate to a `boolean` value.
Algebraic expressions include sums, products, exponentiations, negations, variables, number literals, and function calls which return an algebraic expression. They evaluate to either an `exponent` or `group element` value.
Two other subcategories are used to further describe logical expressions.
Propositional expressions include conjunctions and disjunctions.
Comparison expressions include equalities and inequalities.

### Operators
---
The language contains various unary, binary, and ternary operators.

#### Conjunction
Conjunction expressions evaluate to a `boolean`, and both operands must be `boolean`. An expression evaluates to true if and only if the expressions A and B both evaluate to true.

```A & B```

#### Disjunction
Disjunction expressions evaluate to a `boolean`, and both operands must be `boolean`. An expression evaluates to true if and only if at least one of the expressions A and B evaluate to true.

```A | B```

#### Equality
Equality expressions evaluate to a `boolean`, and the operands must be either both `exponent` or both `group element`.

```A = B```

#### Inequality
Inequality expressions evaluate to a `boolean`, and both operands must have type `exponent`.

##### Greater than
```A > B```

##### Less than
```A < B```

##### Greater than or equal to
```A >= B```

##### Less than or equal to
```A <= B```

##### Double inequality
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

#### Sum
Sum expressions evaluate to an `exponent`, and both operands must have type `exponent`.

##### Addition
```A + B```

##### Subtraction
```A - B```

#### Product
Product expressions evaluate to an `exponent` or a `group element`. The operands must both have type `exponent`, or both have type `group element`.

##### Multiplication
```A * B```

###### Division
```A / B```

#### Exponentiation
Exponentiation expressions evaluate to an `exponent` if the left operand is of type `exponent`, or to a `group element` if the left operand is of type `group element`. The right operand must be of type `exponent`.

```A ^ B```

#### Unary negation
Negation expressions evaluate to an `exponent` and the operand must be of type `exponent`.

```-A```

### Values
---

#### Function calls
A function call consists of the name of a valid function followed by a comma-delimited list of arguments enclosed in parantheses, where each argument is an algebraic expression.

```foo(a + b, bar(x,r), x, y^(a+b))```

#### Number literals
A number literal consists of any integer, and has type `exponent`.

#### Variables
Variable names must use a valid [variable identifier](#variable-identifiers)

### Associativity and Precedence
The following table describes the precedence and associativity (when relevant) of each operator.
Operators are listed in descending precedence from top to bottom.

| Precedence | Operator | Description | Associativity |
|:----------:|:--------:|:-----------:|:-------------:|
| 1 | x() | Function call | - |
| 2 | -x | Unary negation | - |
| 3 | x ^ y | Exponentiation | Right |
| 4 | x * y | Multiplication | Left |
| 4 | x / y | Division | Left |
| 5 | x + y | Addition | Left |
| 5 | x - y | Subtraction | Left |
| 6 | x = y | Equality | - |
| 6 | x > y | Greater than | - |
| 6 | x < y | Less than | - |
| 6 | x >= y | Greater than or equal | - |
| 6 | x <= y | Less than or equal | - |
| 6 | x > y > z | Double inequality | - |
| 7 | x \| y | Disjunction | Left |
| 8 | x & y | Conjunction | Left |

An expression surrounded by parentheses is also an expression, so the precedence order does not need to be remembered as long as sufficient parentheses are used.

### Comments
Single line comments start with `//`.
Multi line comments start with `/*` and end with `*/`.
Note that Subzero comments will not be included anywhere in the generated Java code.

```
// This is a single line comment
/* This is
   a multi-line
   comment */
```

Format of a Subzero program
---------------------------
A Subzero program specifies a single zero knowledge proof of knowledge protocol.
A program consists of a protocol name, function definitions, a public parameter list, a witness list, and a proof expression.

### Protocol name
The protocol name is an optional string that will be used to name the generated classes during compilation; if omitted, a default protocol name will be used. It must be the first line of the program, between a pair of square brackets. The name must start with a letter, and can be followed by letters, numbers, underscores, and spaces.

```[My example protocol]```

During generation, the protocol name will be converted to a PascalCase prefix for class names. For example, the above name will become the prefix `MyExampleProtocol`.

### Function definitions
Zero or more functions can be defined at the start of the program, after the protocol name.
A function definition starts with a function name, which must be a valid [function identifier](#function-identifiers)
This is followed by a comma-delimited list of parameter names enclosed in parentheses, and finally a single expression (followed by an optional semicolon) enclosed in curly braces.
A parameter name starts with a letter, can contain letters and numbers, can contain at most 1 underscore (non-terminating), and can contain any number of terminating single quotes.

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

All parameters in the parameter list should be referenced at least once by a variable, so that type inference can occur.
A warning will appear if there is a parameter in the parameter list with no variable referencing it.

Note that user defined functions cannot contain function calls.


### Public parameter list
This is an optional list that declares public parameter variables. The colon and terminating semicolon are optional.

```pp: m1, m2;```

```pp m1```

A public parameter name starts with a letter, can contain letters and numbers, can contain at most 1 underscore (non-terminating), and can contain any number of terminating single quotes.

### Witness list
This is a list that declares witness variables. A protocol must have at least one witness, so this list is required. The colon and terminating semicolon are optional.

```witness: x, r;```

```witness x, r```

A witness name starts with a letter, can contain letters and numbers, can contain at most 1 underscore (non-terminating), and can contain any number of terminating single quotes.

### The proof expression
This expression describes the zero knowledge argument of knowledge protocol, and consists of a single logical expression followed by an optional semicolon.
When the protocol is run, this expression evaluates to either true or false, signifying whether the protocol was run successfully or not.

Compiler website features
=========================

### Code editor
The website uses [Ace](https://ace.c9.io/) to provide an easy to use code editor. The editor has the following features:
- Syntax highlighting
- Syntax errors will occur when the entered text does not match the described EBNF grammar.
Validation errors and warnings will occur when the entered text does not match the additional semantic rules that dictate the structure of Subzero programs.
Errors will be displayed with a red X, and the corresponding error location will be underlined in red.
Warnings will be displayed with a yellow triangle, and the corresponding warning location will be underlined in yellow.
The red X and yellow triangle can be hovered over with the mouse to display information about the error/warning.
- Automatic bracket matching
- Automatic indentation
- Increase or decrease editor font size with `Ctrl+'+'` and `Ctrl+'-'`
- Use `Ctrl+'l'` to jump to a specific line number
- Use `Ctrl+'s'` to save the Subzero program code 

### Compilation
Once a valid Subzero program is written, it can be compiled. This will generate a complete Java program (buildable with Gradle) that specifies and runs the protocol using the Cryptimeleon [Math](https://github.com/cryptimeleon/math) and [Craco](https://github.com/cryptimeleon/craco) libraries. Note that because syntax and semantic errors are raised as a program is typed, once the protocol is free from errors in the editor then there will be no errors during compilation.

### Example protocols
A dropdown menu allows you to load existing example protocols into the editor. This is an easy way to become familiar with the language.

### LaTeX preview
The website uses [MathJax](https://www.mathjax.org/) to display formatted LaTeX based on the code in the editor.
If the entered text in the code editor is free of syntax and semantic errors, this box will display a formatted LaTeX interpretation of the Subzero code. Because variable names support a single non-terminating underscore, this allows for variables with subscripts, and because of the supported terminating single quotes, variables can also have the prime symbol at the end.
The LaTeX preview font size can be increased and decreased by pressing the corresponding buttons in the bottom left corner of the display box.

Language details
================

Subzero syntax
--------------
The following describes the [EBNF](https://en.wikipedia.org/wiki/Extended_Backus%E2%80%93Naur_form) (Extended Backus-Naur Form) of the DSL grammar.
The grammar is similar to Camenisch-Stadler notation. Note that some programs that follow this syntax are not necessarily valid, as there are additional semantic rules for a valid protocol. A Subzero program consists of a single ```<protocol>```.

```
<protocol> ::= <protocol-name>? <function-definition>* (<pp-list> ';'?)? <witness-list> ';'? ('statement' ':'?)? <expression> ';'?

<function-definition> ::= 'inline'? <identifier> <parameter-list> '{' <expression> ';'? '}' ';'?
<parameter-list> ::= '(' (<parameter> (',' <parameter>)*)? ')'
<parameter> ::= <identifier>

<pp-list> ::= 'pp' ':'? (<pp> (',' <pp>)*)
<pp> ::= <identifier>

<witness-list> ::= 'witness' ':'? (<witness> (',' <witness>)*)
<witness> ::= <identifier>

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

### Name format rules
- Function names must start with a letter, and only contain letters and numbers
- Witness, public parameter, common input, and local variable names must start with a letter, and only contain letters, numbers, at most 1 underscore (non-terminating) and any number of terminating single quotes

### Name uniqueness rules
- Function definition names must be unique
- Function definition names cannot have the same name as a predefined function (e.g. 'e' is used already for pairings)
- Declared witness and public parameter names must be unique
- Function parameter names must be unique within their corresponding function signature

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

### String literal rules
- String literals can only be used as arguments in function calls

### Expression rules
- Conjunctions cannot be nested within algebraic expressions or comparison expressions
- Disjunctions cannot be nested within algebraic expressions or comparison expressions
- Comparison expressions cannot be contained within algebraic expressions or other comparison expressions
- Algebraic expressions in the proof expression must be nested within a comparison expression or function call
- Algebraic expressions must be contained within a comparison expression before being contained within a propositional expression

### Additional type rules
These errors can occur when expressions are used in incorrect or conflicting type contexts.
- Conjunctions must be of type `boolean`
- Conjunction operands must be of type `boolean`
- Disjunctions must be of type `boolean`
- Disjunction operands must be of type `boolean`
- Comparisons must be of type `boolean`
- Equality comparison operands must be the same type
- Inequality comparison operands must be of type `exponent`
- Sums must be of type `exponent`
- Sum operands must be of type `exponent`
- Products must be of type `exponent` or `group element`
- Product operands must be the same type
- The left operand of an exponentiation must be of type `exponent` or `group element`
- The right operand of an exponentiation must be of type `exponent`
- An exponentiation must be the same type as the type of its left operand
- Negatives must be of type `exponent`
- Number literals must be of type `exponent`
- The type of a function call to a predefined function must match the return type of the predefined function
- The type of each argument in a function call to a predefined function must match the type of the corresponding parameter of the predefined function

-->
