package de.upb.crypto.zeroknowledge.model;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;


/**
 * Use this annotation on methods in the PredefinedFunctions class
 * to specify which parameters in the method signature are tuples,
 * and what their multiplicities are.
 * 
 * Non-tuple (scalar) parameters should have a multiplicity of 1.
 * 
 * This allows the validator to validate tuple multiplicities in operations.
 *
 * Usage example:
 * @TupleParameters({x, y, z})
 * where x, y, z are the multiplicities of the method's parameters
 */
@Retention(RUNTIME)
@Target(METHOD)
public @interface TupleParameters {
	public int[] value() default {};
}
