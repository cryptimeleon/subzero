package org.cryptimeleon.subzero.predefined;

import java.lang.annotation.Target;
import java.lang.annotation.Retention;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;


/**
 * Use this annotation on methods in the PredefinedFunctions class
 * to specify that the method returns a tuple, and what the multiplicity
 * of the tuple is.
 *
 * This allows the validator to validate tuple multiplicities in operations
 *
 * Usage example:
 * @ReturnsTuple(x)
 * where x is the multiplicity of the tuple returned by the function
 */
@Retention(RUNTIME)
@Target(METHOD)
public @interface ReturnsTuple {
    int value() default 1;
}