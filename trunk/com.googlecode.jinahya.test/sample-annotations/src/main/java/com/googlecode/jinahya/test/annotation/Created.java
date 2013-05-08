

package com.googlecode.jinahya.test.annotation;


import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


/**
 *
 * @author Jin Kwon <onacit at gmail.com>
 */
@Documented
@Retention(RetentionPolicy.SOURCE)
@Target({ElementType.ANNOTATION_TYPE, ElementType.CONSTRUCTOR,
         ElementType.METHOD, ElementType.TYPE})
public @interface Created {


    By[] value();


}

