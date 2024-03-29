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

import java.lang.ref.*;
import java.util.*;

/** 
 * <code>Proxy</code> provides static methods for creating dynamic proxy
 * classes and instances, and it is also the superclass of all
 * dynamic proxy classes created by those methods.
 *
 * <p>To create a proxy for some interface <code>Foo</code>:
 * <pre>
 *     InvocationHandler handler = new MyInvocationHandler(...);
 *     Class proxyClass = Proxy.getProxyClass(
 *         Foo.class.getClassLoader(), new Class[] { Foo.class });
 *     Foo f = (Foo) proxyClass.
 *         getConstructor(new Class[] { InvocationHandler.class }).
 *         newInstance(new Object[] { handler });
 * </pre>
 * or more simply:
 * <pre>
 *     Foo f = (Foo) Proxy.newProxyInstance(Foo.class.getClassLoader(),
 *                                          new Class[] { Foo.class },
 *                                          handler);
 * </pre>
 *
 * <p>A <i>dynamic proxy class</i> (simply referred to as a <i>proxy
 * class</i> below) is a class that implements a list of interfaces
 * specified at runtime when the class is created, with behavior as
 * described below.
 *
 * A <i>proxy interface</i> is such an interface that is implemented
 * by a proxy class.
 *
 * A <i>proxy instance</i> is an instance of a proxy class.
 *
 * Each proxy instance has an associated <i>invocation handler</i>
 * object, which implements the interface {@link InvocationHandler}.
 * A method invocation on a proxy instance through one of its proxy
 * interfaces will be dispatched to the {@link InvocationHandler#invoke
 * invoke} method of the instance's invocation handler, passing the proxy
 * instance, a <code>java.lang.reflect.Method</code> object identifying
 * the method that was invoked, and an array of type <code>Object</code>
 * containing the arguments.  The invocation handler processes the
 * encoded method invocation as appropriate and the result that it
 * returns will be returned as the result of the method invocation on
 * the proxy instance.
 *
 * <p>A proxy class has the following properties:
 *
 * <ul>
 * <li>Proxy classes are public, final, and not abstract.
 *
 * <li>The unqualified name of a proxy class is unspecified.  The space
 * of class names that begin with the string <code>"$Proxy"</code>
 * should be, however, reserved for proxy classes.
 *
 * <li>A proxy class extends <code>java.lang.reflect.Proxy</code>.
 *
 * <li>A proxy class implements exactly the interfaces specified at its
 * creation, in the same order.
 *
 * <li>If a proxy class implements a non-public interface, then it will
 * be defined in the same package as that interface.  Otherwise, the
 * package of a proxy class is also unspecified.  Note that package
 * sealing will not prevent a proxy class from being successfully defined
 * in a particular package at runtime, and neither will classes already
 * defined by the same class loader and the same package with particular
 * signers.
 *
 * <li>Since a proxy class implements all of the interfaces specified at
 * its creation, invoking <code>getInterfaces</code> on its
 * <code>Class</code> object will return an array containing the same
 * list of interfaces (in the order specified at its creation), invoking
 * <code>getMethods</code> on its <code>Class</code> object will return
 * an array of <code>Method</code> objects that include all of the
 * methods in those interfaces, and invoking <code>getMethod</code> will
 * find methods in the proxy interfaces as would be expected.
 *
 * <li>The {@link Proxy#isProxyClass Proxy.isProxyClass} method will
 * return true if it is passed a proxy class-- a class returned by
 * <code>Proxy.getProxyClass</code> or the class of an object returned by
 * <code>Proxy.newProxyInstance</code>-- and false otherwise.
 *
 * <li>The <code>java.security.ProtectionDomain</code> of a proxy class
 * is the same as that of system classes loaded by the bootstrap class
 * loader, such as <code>java.lang.Object</code>, because the code for a
 * proxy class is generated by trusted system code.  This protection
 * domain will typically be granted
 * <code>java.security.AllPermission</code>.
 *
 * <li>Each proxy class has one public constructor that takes one argument,
 * an implementation of the interface {@link InvocationHandler}, to set
 * the invocation handler for a proxy instance.  Rather than having to use
 * the reflection API to access the public constructor, a proxy instance
 * can be also be created by calling the {@link Proxy#newProxyInstance
 * Proxy.newInstance} method, which combines the actions of calling
 * {@link Proxy#getProxyClass Proxy.getProxyClass} with invoking the
 * constructor with an invocation handler.
 * </ul>
 *
 * <p>A proxy instance has the following properties:
 *
 * <ul>
 * <li>Given a proxy instance <code>proxy</code> and one of the
 * interfaces implemented by its proxy class <code>Foo</code>, the
 * following expression will return true:
 * <pre>
 *     <code>proxy instanceof Foo</code>
 * </pre>
 * and the following cast operation will succeed (rather than throwing
 * a <code>ClassCastException</code>):
 * <pre>
 *     <code>(Foo) proxy</code>
 * </pre>
 *
 * <li>Each proxy instance has an associated invocation handler, the one
 * that was passed to its constructor.  The static
 * {@link Proxy#getInvocationHandler Proxy.getInvocationHandler} method
 * will return the invocation handler associated with the proxy instance
 * passed as its argument.
 *
 * <li>An interface method invocation on a proxy instance will be
 * encoded and dispatched to the invocation handler's {@link
 * InvocationHandler#invoke invoke} method as described in the
 * documentation for that method.
 *
 * <li>An invocation of the <code>hashCode</code>,
 * <code>equals</code>, or <code>toString</code> methods declared in
 * <code>java.lang.Object</code> on a proxy instance will be encoded and
 * dispatched to the invocation handler's <code>invoke</code> method in
 * the same manner as interface method invocations are encoded and
 * dispatched, as described above.  The declaring class of the
 * <code>Method</code> object passed to <code>invoke</code> will be
 * <code>java.lang.Object</code>.  Other public methods of a proxy
 * instance inherited from <code>java.lang.Object</code> are not
 * overridden by a proxy class, so invocations of those methods behave
 * like they do for instances of <code>java.lang.Object</code>.
 * </ul>
 *
 * <h3>Methods Duplicated in Multiple Proxy Interfaces</h3>
 *
 * <p>When two or more interfaces of a proxy class contain a method with
 * the same name and parameter signature, the order of the proxy class's
 * interfaces becomes significant.  When such a <i>duplicate method</i>
 * is invoked on a proxy instance, the <code>Method</code> object passed
 * to the invocation handler will not necessarily be the one whose
 * declaring class is assignable from the reference type of the interface
 * that the proxy's method was invoked through.  This limitation exists
 * because the corresponding method implementation in the generated proxy
 * class cannot determine which interface it was invoked through.
 * Therefore, when a duplicate method is invoked on a proxy instance,
 * the <code>Method</code> object for the method in the foremost interface
 * that contains the method (either directly or inherited through a
 * superinterface) in the proxy class's list of interfaces is passed to
 * the invocation handler's <code>invoke</code> method, regardless of the
 * reference type through which the method invocation occurred.
 *
 * <p>If a proxy interface contains a method with the same name and
 * parameter signature as the <code>hashCode</code>, <code>equals</code>,
 * or <code>toString</code> methods of <code>java.lang.Object</code>,
 * when such a method is invoked on a proxy instance, the
 * <code>Method</code> object passed to the invocation handler will have
 * <code>java.lang.Object</code> as its declaring class.  In other words,
 * the public, non-final methods of <code>java.lang.Object</code>
 * logically precede all of the proxy interfaces for the determination of
 * which <code>Method</code> object to pass to the invocation handler.
 *
 * <p>Note also that when a duplicate method is dispatched to an
 * invocation handler, the <code>invoke</code> method may only throw
 * checked exception types that are assignable to one of the exception
 * types in the <code>throws</code> clause of the method in <i>all</i> of
 * the proxy interfaces that it can be invoked through.  If the
 * <code>invoke</code> method throws a checked exception that is not
 * assignable to any of the exception types declared by the method in one
 * of the the proxy interfaces that it can be invoked through, then an
 * unchecked <code>UndeclaredThrowableException</code> will be thrown by
 * the invocation on the proxy instance.  This restriction means that not
 * all of the exception types returned by invoking
 * <code>getExceptionTypes</code> on the <code>Method</code> object
 * passed to the <code>invoke</code> method can necessarily be thrown
 * successfully by the <code>invoke</code> method.
 *
 * @author	Peter Jones
 * @version	1.8, 00/02/02
 * @see		InvocationHandler
 * @since	JDK1.3
 */
public class Proxy implements java.io.Serializable
{
    /** 
     * the invocation handler for this proxy instance.
     * @serial
     */
    protected InvocationHandler h;

    /** 
     * Constructs a new <code>Proxy</code> instance from a subclass
     * (typically, a dynamic proxy class) with the specified value
     * for its invocation handler.
     *
     * @param   h the invocation handler for this proxy instance
     */
    protected Proxy(InvocationHandler h) { }

    /** 
     * Returns the <code>java.lang.Class</code> object for a proxy class
     * given a class loader and an array of interfaces.  The proxy class
     * will be defined by the specified class loader and will implement
     * all of the supplied interfaces.  If a proxy class for the same
     * permutation of interfaces has already been defined by the class
     * loader, then the existing proxy class will be returned; otherwise,
     * a proxy class for those interfaces will be generated dynamically
     * and defined by the class loader.
     *
     * <p>There are several restrictions on the parameters that may be
     * passed to <code>Proxy.getProxyClass</code>:
     *
     * <ul>
     * <li>All of the <code>Class</code> objects in the
     * <code>interfaces</code> array must represent interfaces, not
     * classes or primitive types.
     *
     * <li>No two elements in the <code>interfaces</code> array may
     * refer to identical <code>Class</code> objects.
     *
     * <li>All of the interface types must be visible by name through the
     * specified class loader.  In other words, for class loader
     * <code>cl</code> and every interface <code>i</code>, the following
     * expression must be true:
     * <pre>
     *     Class.forName(i.getName(), false, cl) == i
     * </pre>
     *
     * <li>All non-public interfaces must be in the same package;
     * otherwise, it would not be possible for the proxy class to
     * implement all of the interfaces, regardless of what package it is
     * defined in.
     *
     * <li>No two interfaces may each have a method with the same name
     * and parameter signature but different return type.
     *
     * <li>The resulting proxy class must not exceed any limits imposed
     * on classes by the virtual machine.  For example, the VM may limit
     * the number of interfaces that a class may implement to 65535; in
     * that case, the size of the <code>interfaces</code> array must not
     * exceed 65535.
     * </ul>
     *
     * <p>If any of these restrictions are violated,
     * <code>Proxy.getProxyClass</code> will throw an
     * <code>IllegalArgumentException</code>.  If the <code>interfaces</code>
     * array argument or any of its elements are <code>null</code>, a
     * <code>NullPointerException</code> will be thrown.
     *
     * <p>Note that the order of the specified proxy interfaces is
     * significant: two requests for a proxy class with the same combination
     * of interfaces but in a different order will result in two distinct
     * proxy classes.
     *
     * @param	loader the class loader to define the proxy class
     * @param	interfaces the list of interfaces for the proxy class
     *		to implement
     * @return	a proxy class that is defined in the specified class loader
     *		and that implements the specified interfaces
     * @throws	IllegalArgumentException if any of the restrictions on the
     *		parameters that may be passed to <code>getProxyClass</code>
     *		are violated
     * @throws	NullPointerException if the <code>interfaces</code> array
     *		argument or any of its elements are <code>null</code>
     */
    public static Class getProxyClass(ClassLoader loader, Class[] interfaces)
        throws IllegalArgumentException
    {
        return null;
    }

    /** 
     * Returns an instance of a proxy class for the specified interfaces
     * that dispatches method invocations to the specified invocation
     * handler.  This method is equivalent to:
     * <pre>
     *     Proxy.getProxyClass(loader, interfaces).
     *         getConstructor(new Class[] { InvocationHandler.class }).
     *         newInstance(new Object[] { handler });
     * </pre>
     *
     * <p><code>Proxy.newProxyInstance</code> throws
     * <code>IllegalArgumentException</code> for the same reasons that
     * <code>Proxy.getProxyClass</code> does.
     *
     * @param	loader the class loader to define the proxy class
     * @param	interfaces the list of interfaces for the proxy class
     *		to implement
     * @param   h the invocation handler to dispatch method invocations to
     * @return	a proxy instance with the specified invocation handler of a
     *		proxy class that is defined by the specified class loader
     *		and that implements the specified interfaces
     * @throws	IllegalArgumentException if any of the restrictions on the
     *		parameters that may be passed to <code>getProxyClass</code>
     *		are violated
     * @throws	NullPointerException if the <code>interfaces</code> array
     *		argument or any of its elements are <code>null</code>, or
     *		if the invocation handler, <code>h</code>, is
     *		<code>null</code>
     */
    public static Object newProxyInstance(ClassLoader loader, Class[]
        interfaces, InvocationHandler h) throws IllegalArgumentException
    {
        return null;
    }

    /** 
     * Returns true if and only if the specified class was dynamically
     * generated to be a proxy class using the <code>getProxyClass</code>
     * method or the <code>newProxyInstance</code> method.
     *
     * <p>The reliability of this method is important for the ability
     * to use it to make security decisions, so its implementation should
     * not just test if the class in question extends <code>Proxy</code>.
     *
     * @param	cl the class to test
     * @return  <code>true</code> if the class is a proxy class and
     *		<code>false</code> otherwise
     * @throws	NullPointerException if <code>cl</code> is <code>null</code>
     */
    public static boolean isProxyClass(Class cl) {
        return false;
    }

    /** 
     * Returns the invocation handler for the specified proxy instance.
     *
     * @param	proxy the proxy instance to return the invocation handler for
     * @return	the invocation handler for the proxy instance
     * @throws	IllegalArgumentException if the argument is not a
     *		proxy instance
     */
    public static InvocationHandler getInvocationHandler(Object proxy)
        throws IllegalArgumentException
    {
        return null;
    }
}
