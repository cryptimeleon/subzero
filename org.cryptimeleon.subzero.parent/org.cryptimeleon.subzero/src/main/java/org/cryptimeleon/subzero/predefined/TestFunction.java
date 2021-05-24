package org.cryptimeleon.subzero.predefined;

import java.util.ArrayList;
import java.util.List;

import org.cryptimeleon.subzero.model.Type;

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
	public List<FunctionParameter> getParameters() {
		List<FunctionParameter> parameters = new ArrayList<FunctionParameter>();
		parameters.add(new FunctionParameter("a", Type.EXPONENT));
		parameters.add(new FunctionParameter("b", Type.EXPONENT));
		return parameters;
	}

	@Override
	public String generateFunction() {
		String body = "return a.isEqualTo(b);";
		return generateFunctionFromBody(body);
	}
}
