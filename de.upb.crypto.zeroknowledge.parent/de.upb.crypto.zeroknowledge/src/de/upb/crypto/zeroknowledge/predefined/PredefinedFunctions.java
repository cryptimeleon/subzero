package de.upb.crypto.zeroknowledge.predefined;

import java.util.Map;
import java.util.ArrayList;
import java.util.HashMap;

import de.upb.crypto.zeroknowledge.model.ExponentExpr;
import de.upb.crypto.zeroknowledge.model.GroupElementExpression;
import de.upb.crypto.zeroknowledge.model.ReturnsTuple;
import de.upb.crypto.zeroknowledge.model.TupleParameters;

// Use @ReturnsTuple(x) and @TupleParameters({x, y, z, ...}) to specify
// the return size and parameter sizes
// If no annotation is used, the return size defaults to 1 and each
// parameter size defaults to 1
public class PredefinedFunctions {
	
	@ReturnsTuple(2)
	@TupleParameters({1,2,3})
	public static GroupElementExpression a(GroupElementExpression a1, GroupElementExpression a2, GroupElementExpression a3) {
		return new GroupElementExpression();
	}
	
	public static String b() {
		return "test";
	}
	
	@ReturnsTuple(100)
	public static GroupElementExpression c(GroupElementExpression testing) {
		return new GroupElementExpression();
	}
	
}
