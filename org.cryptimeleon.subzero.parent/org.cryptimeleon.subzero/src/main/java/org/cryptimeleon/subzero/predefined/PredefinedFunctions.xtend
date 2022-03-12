package org.cryptimeleon.subzero.predefined;

import org.cryptimeleon.math.expressions.group.GroupElementExpression;

/** 
 * A class to hold all predefined functions.
 * 
 * To add a new predefined function, complete the following:
 *
 * 1. Create an abstract method with the desired return type, function name, and parameters.
 *    The return type can be GroupElementExpression, ExponentExpr, boolean, SchnorrFragment, or void
 *    Functions with GroupElementExpression return type will be treated as GROUP_ELEMENT functions.
 *    Functions with ExponentExpr return type will be treated as EXPONENT functions.
 *    Functions with boolean, SchnorrFragment, or void return types will be treated as BOOLEAN functions.
 *    Parameter types can be GroupElementExpression or ExponentExpr (treated as GROUP_ELEMENT or EXPONENT, respectively).
 *    Void functions will also have a SubprotocolSpecBuilder object called 'subprotocolSpecBuilder' prepended as the first parameter.
 *    This parameter should NOT be added to the abstract method.
 *
 * 2. Create a static string field with the same name as the function name, that contains the body of the generated function as a template
 *    string. To use imported classes in the function body, first import the class into this file. Next, add «ClassName.use()», replacing
 *    ClassName with the desired class. This will be replaced with ClassName in the actual method body string. The .use() method
 *    call is an extension method that adds the class to the PredefinedImports object to track classes that must be imported
 *    for this specific function, and it simply returns the class name as a string.
 *
 * 3. Use @Inline on the method to specify that it is an inline function, and that its method body should be placed
 *    inline in the generated code. If this annotation is used, all usages of the parameter variables in
 *    the method body must be prefixed with $. The parameter variables you use are merely placeholders for the
 *    passed arguments, and this approach makes it easy to substitute the placeholder variables with the actual
 *    arguments. The method body must also be a single Java expression.
 *    If no annotation is used, the function will NOT be inlined, and uses of the variables should NOT be
 *    prefixed with $.
 *
 * 4. Use @ReturnsTuple(x) on the method to specify that the function returns a tuple, where x is its multiplicity.
 *    If no annotation is used, the return size defaults to 1.
 *
 * 5. Use @TupleParameters({x, y, z, ...}) on the method to specify that the function has tuple parameters, and the
 *    multiplicities of these parameters (use 1 for scalars).
 *    If no annotation is used, the parameter size defaults to 1.
 *
 */
package abstract class PredefinedFunctions {

    package static final extension PredefinedImports predefinedImports = new PredefinedImports();

	private new() {
		throw new AssertionError();
	}

	@Inline
	def abstract GroupElementExpression e(GroupElementExpression g1elem, GroupElementExpression g2elem);
	static String e = '''e.applyExpr($g1elem, $g2elem)''';

}
