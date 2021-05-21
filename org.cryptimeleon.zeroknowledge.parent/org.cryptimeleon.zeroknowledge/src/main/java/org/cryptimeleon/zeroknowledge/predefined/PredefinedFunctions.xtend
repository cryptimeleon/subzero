package org.cryptimeleon.zeroknowledge.predefined

import org.cryptimeleon.math.expressions.group.GroupElementExpression

/** 
 * A class to hold all predefined functions.
 * 
 * To create a new predefined function, complete the following:
 * 1. Create an abstract method with the desired return type, function name, and parameters.
 *    The return type can be GroupElementExpression, ExponentExpr, boolean, SchnorrFragment, or void
 *    Functions with GroupElementExpression return type will be treated as GROUP_ELEMENT functions.
 *    Functions with ExponentExpr return type will be treated as EXPONENT functions.
 *    Functions with boolean, SchnorrFragment, or void return types will be treated as BOOLEAN functions.
 *    Parameter types can be String, GroupElementExpression, or ExponentExpr (treated as STRING, GROUP_ELEMENT, or EXPONENT, respectively).
 *    Void functions will also have a SubprotocolSpecBuilder object called 'subprotocolSpecBuilder' added as the first parameter.
 *    This parameter should NOT be added to the abstract method.
 * 2. Use @ReturnsTuple(x) on the method to specify that the function returns a tuple, where x is its multiplicity.
 *    If no annotation is used, the return size defaults to 1.
 * 3. Use @TupleParameters({x, y, z, ...}) on the method to specify that the function has tuple parameters, and the
 *    multiplicities of these parameters (use 1 for scalars).
 *    If no annotation is used, the parameter size defaults to 1.
 * 4. Create a string field with the same name as the function name, that contains the body of the generated function.
 
 * 
 */
abstract class PredefinedFunctions {
	private new() {
		throw new AssertionError()
	}

	
	def abstract GroupElementExpression e(GroupElementExpression g1elem, GroupElementExpression g2elem);
	String e = '''
		return this.e.applyExpr(g1elem, g2elem);
	''';

	
}
