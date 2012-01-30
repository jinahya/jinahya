/*
<p>This is not an official specification document, and usage is restricted.
</p>
<a name="notice"><strong><center>
NOTICE
</center></strong><br>
<br>

(c) 2005-2007 Sun Microsystems, Inc. All Rights Reserved.
<p>
Neither this file nor any files generated from it describe a complete
specification, and they may only be used as described below. For
example, no permission is given for you to incorporate this file, in
whole or in part, in an implementation of a Java specification.
<p>
Sun Microsystems Inc. owns the copyright in this file and it is provided
to you for informative, as opposed to normative, use. The file and any
files generated from it may be used to generate other informative
documentation, such as a unified set of documents of API signatures for
a platform that includes technologies expressed as Java APIs. The file
may also be used to produce "compilation stubs," which allow
applications to be compiled and validated for such platforms.
<p>
Any work generated from this file, such as unified javadocs or compiled
stub files, must be accompanied by this notice in its entirety.
<p>
This work corresponds to the API signatures of JSR 219: Foundation
Profile 1.1. In the event of a discrepency between this work and the
JSR 219 specification, which is available at
http://www.jcp.org/en/jsr/detail?id=219, the latter takes precedence.
*/


  


package java.lang;

/** 
 * An IllegalAccessException is thrown when an application tries 
 * to reflectively create an instance (other than an array), 
 * set or get a field, or invoke a method, but the currently 
 * executing method does not have access to the definition of 
 * the specified class, field, method or constructor.
 *
 * @author  unascribed
 * @version 1.14, 03/12/05
 * @see     Class#newInstance()
 * @see     java.lang.reflect.Field#set(Object, Object)
 * @see     java.lang.reflect.Field#setBoolean(Object, boolean)
 * @see     java.lang.reflect.Field#setByte(Object, byte)
 * @see     java.lang.reflect.Field#setShort(Object, short)
 * @see     java.lang.reflect.Field#setChar(Object, char)
 * @see     java.lang.reflect.Field#setInt(Object, int)
 * @see     java.lang.reflect.Field#setLong(Object, long)
 * @see     java.lang.reflect.Field#setFloat(Object, float)
 * @see     java.lang.reflect.Field#setDouble(Object, double)
 * @see     java.lang.reflect.Field#get(Object)
 * @see     java.lang.reflect.Field#getBoolean(Object)
 * @see     java.lang.reflect.Field#getByte(Object)
 * @see     java.lang.reflect.Field#getShort(Object)
 * @see     java.lang.reflect.Field#getChar(Object)
 * @see     java.lang.reflect.Field#getInt(Object)
 * @see     java.lang.reflect.Field#getLong(Object)
 * @see     java.lang.reflect.Field#getFloat(Object)
 * @see     java.lang.reflect.Field#getDouble(Object)
 * @see     java.lang.reflect.Method#invoke(Object, Object[])
 * @see     java.lang.reflect.Constructor#newInstance(Object[])
 * @since   JDK1.0
 */
public class IllegalAccessException extends java.lang.Exception
{

    /** 
     * Constructs an <code>IllegalAccessException</code> without a 
     * detail message. 
     */
    public IllegalAccessException() { }

    /** 
     * Constructs an <code>IllegalAccessException</code> with a detail message. 
     *
     * @param   s   the detail message.
     */
    public IllegalAccessException(java.lang.String s) { }
}
