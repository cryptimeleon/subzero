0K - Zero Knowledge Compiler
============================
0K is a DSL (domain-specific language) that enables the specification of zero knowledge protocols. It is written in Java and uses the [Xtext](https://www.eclipse.org/Xtext/) language development framework.
This website features a code editor for writing 0K programs, and a compiler to generate a Java project that can execute the protocol described by 0K code.

0K Syntax
---------
The following describes the EBNF (Extended Backus-Naur Form) of the domain specific language grammar.
The grammar is similar to Camenisch-Stadler notation.
A 0K program consists of a single ```<model>```.

```
<model> ::= <function-definition>* <witness-list> ';'? <expression> ';'?

<function-definition> ::= <identifier> <parameter-list> '{' <expression> ';'? '}' ';'?
<parameter-list> ::= '(' (<parameter> (',' <parameter>)*)? ')'
<parameter> ::= <identifier>

<witness-list> ::= '(' (<witness> (',' <witness>)*)? ')'
<witness> ::= <identifier>

<expression> ::= <conjunction>
<conjunction> ::= <disjunction> | <conjunction> '&' <disjunction>
<disjunction> ::= <comparison> | <disjunction> '|' <comparison>
<comparison> ::= <sum> | <comparison> '=' <sum> | <comparison> '!=' <sum> | <comparison> '<' <sum>
                       | <comparison> '>' <sum> | <comparison> '<=' <sum> | <comparison> '>=' <sum>
<sum> ::= <product> | <sum> '+' <product> | <sum> '-' <product>
<product> ::= <power> | <product> '*' <power> | <product> '/' <power>
<power> ::= <construct> | <construct> '^' <power>
<construct> ::= <string-literal> | <tuple> | <negative>
<string-literal> ::= '"' ('//' . | !('\\' | '"') )* '"'
<tuple> ::= '(' <conjunction> (',' <conjunction>)+ ')'
<negative> ::= <value> | '-' <value>
<value> ::= <function-call> | <variable> | <number-literal> | '(' <brackets> ')'
<function-call> ::= <identifier> '(' (<argument> (',' <argument>)*)? ')'
<argument> ::= <conjunction>
<variable> ::= <identifier>
<number-literal> ::= [0-9]+
<brackets> ::= <conjunction>

<identifier> ::= [a-zA-Z] [a-zA-Z0-9_']*
```

0K Language Specification
-------------------------
### 0K expressions
An expression is a construct that evaluates to some value.
Expressions can contain nested expressions, which terminate in values.
Expressions can be viewed as binary trees, where leaf nodes are values and non-leaf nodes are operators. Any subtree is also a valid expression.

Expressions are divided into two kinds: logical and algebraic.
Logical expressions are conjunctions, disjunctions, comparisons, and function calls which return a logical expression. They evaluate to a BOOLEAN value.
Algebraic expressions are sums, products, exponentiations, negatives, variables, number literals, and function calls which return an algebraic expression. They evaluate to either an EXPONENT or GROUP_ELEMENT value.
Two other terms are used to further describe logical expressions.
Propositional expressions are conjunctions and disjunctions.
Comparison expressions are just comparisons.

### Types
There are 4 types of value.
STRING is used for string literals, and can only be used as an argument in function calls to predefined functions.
BOOLEAN is the result of evaluating a logical expression.
EXPONENT and GROUP_ELEMENT are the possible results of evaluting an algebraic expression.
There is no type declaration and all types are inferred (explained later).

### Operators
---
The language contains various binary operators and one unary operator

#### Conjunction
```a & b```

#### Disjunction
```a | b```

#### Comparison
Equality
```a = b```

Inequality
```a != b```

Greater than
```a > b```

Less than
```a < b```

Greater than or equal to
```a >= b```

Less than or equal to
```a <= b```

#### Sum
Addition
```a + b```

Subtraction
```a - b```

#### Product
Multiplication
```a * b```

Division
```a / b```

#### Exponentiation
```a ^ b```

#### Unary negation
```-a```

### Values
---

#### Function calls
A function call consists of the name of a valid function followed by a comma-delimited list of arguments enclosed in parantheses, where each argument is some algebraic expression or string literal.

```functionCall(a + b, "string", x, y^(a+b))```

#### Number literals
Any integer

#### Variables
Variable names must start with a letter, can contain letters and numbers, can contain at most one underscore, and any number of terminating single quotes. Single quotes and one underscore are allowed so that names can contain subscripts or the prime symbol, respectively, which allows for nicer LaTeX output.
Variables can either be of type EXPONENT or GROUP_ELEMENT, not STRING or BOOLEAN.
Variables can also be scalar or be a tuple of some arbitrary size.

#### String literals
Any string of characters, enclosed in double quotes. Only used as arguments in function calls to predefined functions.

```"This is a string"```

### Tuples
Tuples are a comma-delimited list of at least 2 expressions enclosed in parentheses.
All expressions within the tuple must be either all EXPONENT or all GROUP_ELEMENT.

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
| 6 | x != y | Inequality | - |
| 6 | x > y | Greater than | - |
| 6 | x < y | Less than | - |
| 6 | x >= y | Greater than or equal | - |
| 6 | x <= y | Less than or equal | - |
| 7 | x \| y | Disjunction | Left |
| 8 | x & y | Conjunction | Left |

### Comments
Single line comments start with `//`.
Multi line comments start with `/*` and end with `*/`.

```
// This is a single line comment
/* This
   is
   a
   multi-line
   comment */
```

### Format of a 0K program
---
A 0K program consists of function definitions, a witness list, and a proof expression.

#### The proof expression
This expression describes the zero knowledge argument of knowledge protocol, and consists of a single logical expression followed by an optional semicolon.
When the protocol is run, this expression returns either true or false, signifying whether the protocol was run successfully or not.

#### Witness list
The witness list follows any user defined functions.
It is a comma-delimited list of witness names enclosed in parentheses, ending with an optional semicolon.
A witness name starts with a letter, can contain letters and numbers, can contain at most 1 underscore, and can contain any number of terminating single quotes.

```(x1, x2, x3, x4, x5);```

#### Function definitions
The user can define zero or more functions at the start of the program.
A function definition starts with a function name, which must begin with a letter, and can contain letters and numbers.
This is followed by a comma-delimited list of parameter names enclosed in parentheses, and finally a 0K expression (followed by an optional semicolon) enclosed in curly braces, with an optional terminating semicolon.
A parameter name starts with a letter, can contain letters and numbers, can contain at most 1 underscore, and can contain any number of terminating single quotes.

```
functionName(parameterA, parameterB, parameterC) {
  parameterA + parameterB + parameterC;
};
```

Any variable that references a parameter in the function is called a local variable.
Any other variable is a global variable.

All parameters in the parameter list should be referenced at least once by a variable, so that type inference can occur.
A warning will appear if there is a parameter in the parameter list with no variable referencing it.

Note that user defined functions cannot contain function calls to user defined functions, only to predefined functions, thus recursion is not supported.

### Variable declaration
Witnesses and function parameters must be declared. All other variables are global, and do not need to be declared.

### Type Inference
In 0K there is no need to declare the type of a variable; it supports full type inference.
The type of each variable is determined based on its context. The parser will label as many variables as possible as EXPONENT, and then all remaining variables will be labeled as GROUP_ELEMENT.

### Size inference
0K supports both scalar and tuple variables. There is also no need to declare the size of a variable.
The size of a variable will be inferred based on the context, and will default to a scalar. Thus, it becomes a tuple only if it is involved in an operation with some other explicit tuple, that is, a parentheses-enclosed list of expressions).

Compiler Website Features
-------------------------

### Code editor
The website uses [Ace](https://ace.c9.io/) to provide an easy to use code editor.
Syntax errors will occur when the entered text does not match the described EBNF grammar.
Validation errors and warnings will occur when the entered text does not match the additional validation rules that dictate the structure of 0K programs.
Errors will be displayed with a red X, and the corresponding error location will be underlined in red.
Warnings will be displayed with a yellow triangle, and the corresponding warning location will be underlined in yellow.
The red X and yellow triangle can be hovered over with the mouse to display information about the error/warning.
The code editor font size can be increased and decreased by pressing `Ctrl+'+'` and `Ctrl+'-'` respectively.

### LaTeX preview
The website uses [MathJax](https://www.mathjax.org/) to display formatted LaTeX based on the code in the editor.
If the entered text in the code editor is free of syntax and validation errors, and if the corresponding checkboxes are enabled, this box will display a formatted LaTeX interpretation of the 0K code.
The LaTeX preview font size can be increased and decreased by pressing the corresponding buttons in the top left corner of the display box.

### Checkboxes
---
There are 4 checkboxes which toggle various functionality on the website.

#### Enable LaTeX preview
The LaTeX preview box will now display formatted LaTeX.
By default, enabling this option will generate the formatted LaTeX once, and subsequent updates are done by pressing the 'Update LaTeX preview' button.

#### Enable continuous LaTeX preview update
This option can only be enabled if 'Enable LaTeX preview' is checked.
The LaTeX preview box will now be continuously updated, after a short pause after the user stops typing in the text editor.

#### Enable inlining functions in LaTeX preview
If this is enabled, functions will be inlined before the editor code is displayed as formatted LaTeX.
Inlining means that any function call in the code will be replaced with the corresponding function definition, where all local variables have been replaced by the corresponding arguments in the function call.
The exception to this is function calls to predefined functions, which will not be inlined.

#### Enable inlining functions in generated Java code
If this is enabled, functions will be inlined before the editor code is compiled to Java code.
Inlining means that any function call in the code will be replaced with the corresponding function definition, where all local variables have been replaced by the corresponding arguments in the function call.
The exception to this is function calls to predefined functions, which will not be inlined.

### Buttons
---

#### Download editor code
Downloads a text file with the .zkak extension containing the current contents of the code editor.

#### Download LaTeX code
Downloads a file with the .tex extension containing the LaTeX code used to generate the current LaTeX preview.

#### Compile and download Java code
If the current contents of the code editor are free of syntax and validation errors, parses the code into a syntax tree model, then compiles this to Java code.
A Java project is then created and downloaded by the user.

#### Update LaTeX preview
This button is only enabled when 'Enable LaTeX preview' is checked and 'Enable continuous LaTeX preview update' is unchecked.
Manually updates the LaTeX preview box based on the current contents of the code editor.

### Console
The console box in the bottom right corner of the page displays error, warning, and info messages. These messages can be generated when downloading editor code, downloading LaTeX code, or during compilation to Java code.


Validation rules
----------------
These are additional rules which dictate the allowed structure and data of 0K programs.

### Name format rules
- Function names must start with a letter, and only contain letters and numbers
- Witness names must start with a letter, and only contain letters, numbers, at most 1 underscore (to denote subscript) and any number of terminating single quotes
- Variable names must start with a letter, and only contain letters, numbers, at most 1 underscore (to denote subscript) and any number of terminating single quotes
- Parameter names must start with a letter, and only contain letters, numbers, at most 1 underscore (to denote subscript) and any number of terminating single quotes

### Name uniqueness rules
- Function definition names must be unique
- Function definition names cannot have the same name as a predefined function
- Witness names must be unique
- Function parameter names must be unique within their corresponding function signature

### Function definition rules
- Function definitions cannot contain function calls to functions defined by the user, only to predefined functions
- Every function parameter should be used at least once in the function definition (warning)
- User-defined functions should be called at least once in the proof expression (warning)

### Witness list rules
- The witness list must contain at least one witness

### Function calls
- Function calls must reference either a valid user defined function or predefined function
- The number of arguments in a function call must match the number of parameters in the function definition
- The type of each argument in a function call must match the type of each parameter in the function definition
- The size of each argument in a function call must match the size of each parameter in the function definition
- Function calls cannot contain logical expressions as arguments
- A function call whose return type is BOOLEAN cannot be within an algebraic expression or a comparison expression
- A function call whose return type is EXPONENT or GROUP_ELEMENT must be contained within a comparison expression

### String literal rules
- String literals can only be used as arguments in function calls

### Expression rules
- Conjunctions cannot be nested within algebraic expressions or comparison expressions
- Disjunctions cannot be nested within algebraic expressions or comparison expressions
- Comparison expressions cannot be contained within algebraic expressions or other comparison expressions
- Algebraic expressions in the proof expression must be nested within a comparison expression or function call
- Algebraic expressions must be contained within a comparison expression before being contained within a propositional expression

### Additional type rules
These occur when grammar errors cause the type inference to mislabel the type of nodes in the syntax tree
- Conjunctions must be of type BOOLEAN
- Conjunction operands must be of type BOOLEAN
- Disjunctions must be of type BOOLEAN
- Disjunction operands must be of type BOOLEAN
- Comparisons must be of type BOOLEAN
- Comparison operands must be the same type
- Sums must be of type EXPONENT
- Sum operands must be of type EXPONENT
- Product operands must be the same type
- The left operand of an exponentiation must be of type EXPONENT or GROUP_ELEMENT
- The right operand of an exponentiation must be of type EXPONENT
- An exponentiation must be the same type as the type of its left operand
- Negatives must be of type EXPONENT
- Number literals must be of type EXPONENT
- String literals must be of type STRING
- Tuple elements must be either all of type EXPONENT or all of type GROUP_ELEMENT
- The type of a function call to a predefined function must match the return type of the predefined function
- The type of each argument in a function call to a predefined function must match the type of the corresponding parameter of the predefined function

### Tuple rules
- Tuples cannot be nested within other tuples, unless first nested within a function call

### Additional size rules
These occur when grammar errors cause the size inference to mislabel the size of nodes in the syntax tree
- Conjunctions must be scalar
- Disjunctions must be scalar
- Comparisons must be scalar
- Comparison operands must be the same size
- Sum operands must be the same size
- Product operands of a GROUP_ELEMENT product must be the same size
- Product operands of an EXPONENT product must be the same size, or one is a scalar and the other is a tuple
- The right operand of an exponentiation must be a scalar
- Number literals must be scalar
- String literals must be scalar
- Tuples must have a size of at least 2
- The size of a tuple must match the number of elements it contains
