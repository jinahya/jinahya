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
 * A <code>Method</code> provides information about, and access to, a single method
 * on a class or interface.  The reflected method may be a class method
 * or an instance method (including an abstract method).
 *
 * <p>A <code>Method</code> permits widening conversions to occur when matching the
 * actual parameters to invokewith the underlying method's formal
 * parameters, but it throws an <code>IllegalArgumentException</code> if a
 * narrowing conversion would occur.
 *
 * @see Member
 * @see java.lang.Class
 * @see java.lang.Class#getMethods()
 * @see java.lang.Class#getMethod(String, Class[])
 * @see java.lang.Class#getDeclaredMethods()
 * @see java.lang.Class#getDeclaredMethod(String, Class[])
 *
 * @author Nakul Saraiya
 */
public final class Method extends AccessibleObject implements Member
{

    /*
     * This hidden constructor does not necessarily correspond to
     * a constructor in the original source file -- it keeps javadoc
     * from generating an inappropriate default constructor.
     */
    private Method() { }

    /** 
     * Returns the <code>Class</code> object representing the class or interface
     * that declares the method represented by this <code>Method</code> object.
     */
    public Class getDeclaringClass() {
        return null;
    }

    /** 
     * Returns the name of the method represented by this <code>Method</code> 
     * object, as a <code>String</code>.
     */
    public String getName() {
        return null;
    }

    /** 
     * Returns the Java language modifiers for the method represented
     * by this <code>Method</code> object, as an integer. The <code>Modifier</code> class should
     * be used to decode the modifiers.
     *
     * @see Modifier
     */
    public int getModifiers() {
        return 0;
    }

    /** 
     * Returns a <code>Class</code> object that represents the formal return type
     * of the method represented by this <code>Method</code> object.
     * 
     * @return the return type for the method this object represents
     */
    public Class getReturnType() {
        return null;
    }

    /** 
     * Returns an array of <code>Class</code> objects that represent the formal
     * parameter types, in declaration order, of the method
     * represented by this <code>Method</code> object.  Returns an array of length
     * 0 if the underlying method takes no parameters.
     * 
     * @return the parameter types for the method this object
     * represents
     */
    public Class[] getParameterTypes() {
        return null;
    }

    /** 
     * Returns an array of <code>Class</code> objects that represent 
     * the types of the exceptions declared to be thrown
     * by the underlying method
     * represented by this <code>Method</code> object.  Returns an array of length
     * 0 if the method declares no exceptions in its <code>throws</code> clause.
     * 
     * @return the exception types declared as being thrown by the
     * method this object represents
     */
    public Class[] getExceptionTypes() {
        return null;
    }

    /** 
     * Compares this <code>Method</code> against the specified object.  Returns
     * true if the objects are the same.  Two <code>Methods</code> are the same if
     * they were declared by the same class and have the same name
     * and formal parameter types and return type.
     */
    public boolean equals(Object obj) {
        return false;
    }

    /** 
     * Returns a hashcode for this <code>Method</code>.  The hashcode is computed
     * as the exclusive-or of the hashcodes for the underlying
     * method's declaring class name and the method's name.
     */
    public int hashCode() {
        return 0;
    }

    /** 
     * Returns a string describing this <code>Method</code>.  The string is
     * formatted as the method access modifiers, if any, followed by
     * the method return type, followed by a space, followed by the
     * class declaring the method, followed by a period, followed by
     * the method name, followed by a parenthesized, comma-separated
     * list of the method's formal parameter types. If the method
     * throws checked exceptions, the parameter list is followed by a
     * space, followed by the word throws followed by a
     * comma-separated list of the thrown exception types.
     * For example:
     * <pre>
     *    public boolean java.lang.Object.equals(java.lang.Object)
     * </pre>
     *
     * <p>The access modifiers are placed in canonical order as
     * specified by "The Java Language Specification".  This is
     * <tt>public</tt>, <tt>protected</tt> or <tt>private</tt> first,
     * and then other modifiers in the following order:
     * <tt>abstract</tt>, <tt>static</tt>, <tt>final</tt>,
     * <tt>synchronized</tt> <tt>native</tt>.
     */
    public String toString() {
        return null;
    }

    /** 
     * Invokes the underlying method represented by this <code>Method</code> 
     * object, on the specified object with the specified parameters.
     * Individual parameters are automatically unwrapped to match
     * primitive formal parameters, and both primitive and reference
     * parameters are subject to method invocation conversions as
     * necessary.
     *
     * <p>If the underlying method is static, then the specified <code>obj</code> 
     * argument is ignored. It may be null.
     *
     * <p>If the number of formal parameters required by the underlying method is
     * 0, the supplied <code>args</code> array may be of length 0 or null.
     *
     * <p>If the underlying method is an instance method, it is invoked
     * using dynamic method lookup as documented in The Java Language
     * Specification, Second Edition, section 15.12.4.4; in particular,
     * overriding based on the runtime type of the target object will occur.
     *
     * <p>If the underlying method is static, the class that declared
     * the method is initialized if it has not already been initialized.
     *
     * <p>If the method completes normally, the value it returns is
     * returned to the caller of invoke; if the value has a primitive
     * type, it is first appropriately wrapped in an object. If the
     * underlying method return type is void, the invocation returns
     * null.
     *
     * @param obj  the object the underlying method is invoked from
     * @param args the arguments used for the method call
     * @return the result of dispatching the method represented by
     * this object on <code>obj</code> with parameters
     * <code>args</code>
     *
     * @exception IllegalAccessException    if this <code>Method</code> object
     *              enforces Java language access control and the underlying
     *              method is inaccessible.
     * @exception IllegalArgumentException  if the method is an
     *              instance method and the specified object argument
     *              is not an instance of the class or interface
     *              declaring the underlying method (or of a subclass
     *              or implementor thereof); if the number of actual
     *              and formal parameters differ; if an unwrapping
     *              conversion for primitive arguments fails; or if,
     *              after possible unwrapping, a parameter value
     *              cannot be converted to the corresponding formal
     *              parameter type by a method invocation conversion.
     * @exception InvocationTargetException if the underlying method
     *              throws an exception.
     * @exception NullPointerException      if the specified object is null
     *              and the method is an instance method.
     * @exception ExceptionInInitializerError if the initialization
     * provoked by this method fails.
     */
    public Object invoke(Object obj, Object[] args)
        throws IllegalAccessException, IllegalArgumentException,
        InvocationTargetException
    {
        return null;
    }
}
