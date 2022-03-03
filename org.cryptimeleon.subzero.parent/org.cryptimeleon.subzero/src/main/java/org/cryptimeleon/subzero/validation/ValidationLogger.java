package org.cryptimeleon.subzero.validation;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;

public class ValidationLogger {
    @FunctionalInterface
    public interface LogFunction {
        void log(String message, EObject source, EStructuralFeature feature);
    }

    private final LogFunction errorFunction;
    private final LogFunction warningFunction;
    private final LogFunction infoFunction;

    public ValidationLogger(LogFunction errorFunction, LogFunction warningFunction, LogFunction infoFunction) {
        this.errorFunction = errorFunction;
        this.warningFunction = warningFunction;
        this.infoFunction = infoFunction;
    }

    public void error(EObject source, String message) {
        errorFunction.log(message, source, ValidationUtils.getDefaultFeature(source));
    }

    public void error(EObject source, EStructuralFeature feature, String message) {
        errorFunction.log(message, source, feature);
    }

    public void error(EObject source, String message, Object... parameters) {
        errorFunction.log(String.format(message, parameters), source, ValidationUtils.getDefaultFeature(source));
    }

    public void error(EObject source, EStructuralFeature feature, String message, Object... parameters) {
        errorFunction.log(String.format(message, parameters), source, feature);
    }

    public void warning(EObject source, String message) {
        warningFunction.log(message, source, ValidationUtils.getDefaultFeature(source));
    }

    public void warning(EObject source, EStructuralFeature feature, String message) {
        warningFunction.log(message, source, feature);
    }

    public void warning(EObject source, String message, Object... parameters) {
        warningFunction.log(String.format(message, parameters), source, ValidationUtils.getDefaultFeature(source));
    }

    public void warning(EObject source, EStructuralFeature feature, String message, Object... parameters) {
        warningFunction.log(String.format(message, parameters), source, feature);
    }

    public void info(EObject source, String message) {
        infoFunction.log(message, source, ValidationUtils.getDefaultFeature(source));
    }

    public void info(EObject source, EStructuralFeature feature, String message) {
        infoFunction.log(message, source, feature);
    }

    public void info(EObject source, String message, Object... parameters) {
        infoFunction.log(String.format(message, parameters), source, ValidationUtils.getDefaultFeature(source));
    }

    public void info(EObject source, EStructuralFeature feature, String message, Object... parameters) {
        infoFunction.log(String.format(message, parameters), source, feature);
    }
}
