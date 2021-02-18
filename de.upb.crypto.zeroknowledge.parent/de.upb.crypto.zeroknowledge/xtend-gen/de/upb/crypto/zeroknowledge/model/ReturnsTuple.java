package de.upb.crypto.zeroknowledge.model;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

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
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface ReturnsTuple {
  public int value() default 1;
}
