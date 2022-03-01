package org.cryptimeleon.subzero.model;

import java.util.ArrayList;
import java.lang.reflect.Method;
import java.util.Collections;

import org.cryptimeleon.subzero.predefined.TupleParameters;
import org.cryptimeleon.subzero.predefined.ReturnsTuple;
import org.cryptimeleon.craco.protocols.arguments.sigma.schnorr.SchnorrFragment;
import java.util.List;

/**
 * Contains information about a function's name, return type, and parameters
 */
public class FunctionSignature {

    private final String name;
    private final Type returnType;
    private final int returnSize;
    private final int parameterCount;
    private final List<String> parameterNames;
    private final List<Type> parameterTypes;
    private final List<Integer> parameterSizes;

	public FunctionSignature(String name, Type returnType, int returnSize, List<String> parameterNames, List<Type> parameterTypes, List<Integer> parameterSizes) {
        this.name = name;
        this.returnType = returnType;
        this.returnSize = returnSize;
        this.parameterCount = parameterNames.size();
        this.parameterNames = new ArrayList<>(parameterNames);
        this.parameterTypes = new ArrayList<>(parameterTypes);
        this.parameterSizes = new ArrayList<>(parameterSizes);
    }

    public FunctionSignature(String name, Type returnType, List<String> parameterNames, List<Type> parameterTypes) {
        this(name, returnType, 1, parameterNames, parameterTypes, new ArrayList<>(Collections.nCopies(parameterTypes.size(), 1)));
    }

    public FunctionSignature(Method method) {
        name = method.getName();

        Class<?> returnClass = method.getReturnType();
        if (returnClass == void.class || returnClass == SchnorrFragment.class) {
            returnType = Type.BOOLEAN;
        } else {
            returnType = Type.toType(method.getReturnType());
        }

        ReturnsTuple returnAnnotation = method.getAnnotation(ReturnsTuple.class);
        if (returnAnnotation != null) {
            returnSize = returnAnnotation.value();
        } else {
            returnSize = 1;
        }

        TupleParameters parametersAnnotation = method.getAnnotation(TupleParameters.class);
        if (parametersAnnotation != null) {
            parameterSizes = new ArrayList<>(parametersAnnotation.value().length);
            for (int size : parametersAnnotation.value()) {
                parameterSizes.add(size);
            }
        } else {
            parameterSizes = new ArrayList<>(Collections.nCopies(method.getParameterCount(), 1));
        }

        parameterCount = method.getParameterTypes().length;
        parameterNames = new ArrayList<>();
        parameterTypes = new ArrayList<>();

        for (int i = 0; i < parameterCount; i++) {
            parameterNames.add("arg" + (i+1));
        }

        for (Class<?> classObject : method.getParameterTypes()) {
            parameterTypes.add(Type.toType(classObject));
        }
    }

    public String getName() {
        return name;
    }

    public int getParameterCount() {
        return parameterCount;
    }

    public Type getReturnType() {
        return returnType;
    }

    public int getReturnSize() {
        return returnSize;
    }

    public boolean returnsTuple() {
        return returnSize > 1;
    }

    public List<String> getParameterNames() {
        return parameterNames;
    }

    public List<Type> getParameterTypes() {
        return parameterTypes;
    }

    public List<Integer> getParameterSizes() {
        return parameterSizes;
    }

}
