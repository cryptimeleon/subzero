package org.cryptimeleon.zeroknowledge.predefined;

import java.util.ArrayList;
import java.util.List;

import org.cryptimeleon.zeroknowledge.model.Type;

public class TestFunction extends PredefinedFunction {

	@Override
	public String getName() {
		return "test";
	}

	@Override
	public Type getReturnType() {
		return Type.BOOLEAN;
	}

	@Override
	public List<PredefinedFunctionParameter> getParameters() {
		List<PredefinedFunctionParameter> parameters = new ArrayList<PredefinedFunctionParameter>();
		parameters.add(new PredefinedFunctionParameter("a", Type.EXPONENT));
		parameters.add(new PredefinedFunctionParameter("b", Type.EXPONENT));
		return parameters;
	}

	@Override
	public String generateFunction() {
		String body = "return a.isEqualTo(b);";
		return generateFunctionFromBody(body);
	}
}
