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


  


package java.lang.reflect;

/** 
 * <code>InvocationHandler</code> is the interface implemented by
 * the <i>invocation handler</i> of a proxy instance.
 *
 * <p>Each proxy instance has an associated invocation handler.
 * When a method is invoked on a proxy instance, the method
 * invocation is encoded and dispatched to the <code>invoke</code>
 * method of its invocation handler.
 *
 * @author	Peter Jones
 * @version	1.5, 00/02/02
 * @see		Proxy
 * @since	JDK1.3
 */
public interface InvocationHandler
{

    /** 
     * Processes a method invocation on a proxy instance and returns
     * the result.  This method will be invoked on an invocation handler
     * when a method is invoked on a proxy instance that it is
     * associated with.
     *
     * @param	proxy the proxy instance that the method was invoked on
     *
     * @param	method the <code>Method</code> instance corresponding to
     * the interface method invoked on the proxy instance.  The declaring
     * class of the <code>Method</code> object will be the interface that
     * the method was declared in, which may be a superinterface of the
     * proxy interface that the proxy class inherits the method through.
     *
     * @param	args an array of objects containing the values of the
     * arguments passed in the method invocation on the proxy instance,
     * or <code>null</code> if interface method takes no arguments.
     * Arguments of primitive types are wrapped in instances of the
     * appropriate primitive wrapper class, such as
     * <code>java.lang.Integer</code> or <code>java.lang.Boolean</code>.
     *
     * @return	the value to return from the method invocation on the
     * proxy instance.  If the declared return type of the interface
     * method is a primitive type, then the value returned by
     * this method must be an instance of the corresponding primitive
     * wrapper class; otherwise, it must be a type assignable to the
     * declared return type.  If the value returned by this method is
     * <code>null</code> and the interface method's return type is
     * primitive, then a <code>NullPointerException</code> will be
     * thrown by the method invocation on the proxy instance.  If the
     * value returned by this method is otherwise not compatible with
     * the interface method's declared return type as described above,
     * a <code>ClassCastException</code> will be thrown by the method
     * invocation on the proxy instance.
     *
     * @throws	Throwable the exception to throw from the method
     * invocation on the proxy instance.  The exception's type must be
     * assignable either to any of the exception types declared in the
     * <code>throws</code> clause of the interface method or to the
     * unchecked exception types <code>java.lang.RuntimeException</code>
     * or <code>java.lang.Error</code>.  If a checked exception is
     * thrown by this method that is not assignable to any of the
     * exception types declared in the <code>throws</code> clause of
     * the interface method, then an
     * {@link UndeclaredThrowableException} containing the
     * exception that was thrown by this method will be thrown by the
     * method invocation on the proxy instance.
     *
     * @see	UndeclaredThrowableException
     */
    public Object invoke(Object proxy, Method method, Object[] args)
        throws Throwable;
}
