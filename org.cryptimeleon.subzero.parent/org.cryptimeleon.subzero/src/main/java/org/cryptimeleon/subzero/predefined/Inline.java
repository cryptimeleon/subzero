package org.cryptimeleon.subzero.predefined;

import java.lang.annotation.Target;
import java.lang.annotation.Retention;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Use this annotation on function in the PredefinedFunctions class
 * to specify that it should be inlined in the generated code
 */
@Retention(RUNTIME)
@Target(METHOD)
public @interface Inline {
}