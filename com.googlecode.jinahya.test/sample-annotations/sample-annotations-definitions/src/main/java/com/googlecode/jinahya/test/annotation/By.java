

package com.googlecode.jinahya.test.annotation;


import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;


/**
 *
 * @author Jin Kwon <onacit at gmail.com>
 */
@Documented
@Retention(RetentionPolicy.SOURCE)
public @interface By {


    String name();


    String comment() default "";


}

