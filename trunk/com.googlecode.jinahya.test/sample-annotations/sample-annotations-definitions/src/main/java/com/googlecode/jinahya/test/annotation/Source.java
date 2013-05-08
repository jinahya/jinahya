

package com.googlecode.jinahya.test.annotation;


import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Target;


/**
 *
 * @author Jin Kwon <onacit at gmail.com>
 */
@Documented
@Target({ElementType.ANNOTATION_TYPE, ElementType.CONSTRUCTOR,
         ElementType.METHOD, ElementType.TYPE})
public @interface Source {


    By[] created();


    By[] reviewed() default {};


}

