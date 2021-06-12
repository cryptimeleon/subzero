package org.cryptimeleon.subzero.predefined

import java.util.List
import java.util.ArrayList
import org.cryptimeleon.subzero.model.Type

class PairingFunction extends PredefinedFunction {
	
	override String generateFunction() {
		return "this.e.applyExpr(g1elem, g2elem)";
	}
	
	override String getName() {
		return "e";
	}
	
	override List<FunctionParameter> getParameters() {
		val List<FunctionParameter> parameters = new ArrayList<FunctionParameter>();
		
		parameters.add(new FunctionParameter("g1elem", Type.GROUP_ELEMENT));
		parameters.add(new FunctionParameter("g2elem", Type.GROUP_ELEMENT));
		
		return parameters;
	}
	
	override Type getReturnType() {
		return Type.GROUP_ELEMENT;
	}
	
}