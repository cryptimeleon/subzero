package org.cryptimeleon.zeroknowledge.predefined;

import java.math.BigInteger;

import org.cryptimeleon.math.expressions.group.GroupElementExpression;
import org.cryptimeleon.math.structures.groups.GroupElement;

/**
 * A class to hold all predefined functions.
 * 
 * Use @ReturnsTuple(x) to specify that a function returns a tuple, where x is its multiplicity
 * Use @TupleParameters({x, y, z, ...}) to specify that a function has tuple parameters, and the
 * multiplicities of these parameters (use 1 for scalars)
 * If no annotation is used, the return size defaults to 1 and each parameter size defaults to 1
 */
public class PredefinedFunctions {
	
	// Example functions
	
	@ReturnsTuple(2)
	@TupleParameters({1,2,3})
	public static GroupElement a(GroupElement a1, GroupElement a2, GroupElement a3) {
		return a1.pow(BigInteger.valueOf(1));
	}
	
	public static String b() {
		return "test";
	}
	
	@ReturnsTuple(100)
	public static GroupElement c(GroupElement testing) {
		return testing.pow(BigInteger.valueOf(1));
	}
	
}
