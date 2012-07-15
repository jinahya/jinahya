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
 * The Modifier class provides <code>static</code> methods and
 * constants to decode class and member access modifiers.  The sets of
 * modifiers are represented as integers with distinct bit positions
 * representing different modifiers.  The values for the constants
 * representing the modifiers are taken from <a
 * href="http://java.sun.com/docs/books/vmspec/2nd-edition/html/VMSpecTOC.doc.html"><i>The
 * Java</i><sup><small>TM</small></sup> <i>Virtual Machine Specification, Second
 * edition</i></a> tables 
 * <a href="http://java.sun.com/docs/books/vmspec/2nd-edition/html/ClassFile.doc.html#75734">4.1</a>,
 * <a href="http://java.sun.com/docs/books/vmspec/2nd-edition/html/ClassFile.doc.html#88358">4.4</a>,
 * <a href="http://java.sun.com/docs/books/vmspec/2nd-edition/html/ClassFile.doc.html#75568">4.5</a>, and 
 * <a href="http://java.sun.com/docs/books/vmspec/2nd-edition/html/ClassFile.doc.html#88478">4.7</a>.
 *
 * @see Class#getModifiers()
 * @see Member#getModifiers()
 *
 * @author Nakul Saraiya
 */
public class Modifier
{
    /** 
     * The <code>int</code> value representing the <code>public</code> 
     * modifier.
     */
    public static final int PUBLIC = 1;

    /** 
     * The <code>int</code> value representing the <code>private</code> 
     * modifier.
     */
    public static final int PRIVATE = 2;

    /** 
     * The <code>int</code> value representing the <code>protected</code> 
     * modifier.
     */
    public static final int PROTECTED = 4;

    /** 
     * The <code>int</code> value representing the <code>static</code> 
     * modifier.
     */
    public static final int STATIC = 8;

    /** 
     * The <code>int</code> value representing the <code>final</code> 
     * modifier.
     */
    public static final int FINAL = 16;

    /** 
     * The <code>int</code> value representing the <code>synchronized</code> 
     * modifier.
     */
    public static final int SYNCHRONIZED = 32;

    /** 
     * The <code>int</code> value representing the <code>volatile</code> 
     * modifier.
     */
    public static final int VOLATILE = 64;

    /** 
     * The <code>int</code> value representing the <code>transient</code> 
     * modifier.
     */
    public static final int TRANSIENT = 128;

    /** 
     * The <code>int</code> value representing the <code>native</code> 
     * modifier.
     */
    public static final int NATIVE = 256;

    /** 
     * The <code>int</code> value representing the <code>interface</code> 
     * modifier.
     */
    public static final int INTERFACE = 512;

    /** 
     * The <code>int</code> value representing the <code>abstract</code> 
     * modifier.
     */
    public static final int ABSTRACT = 1024;

    /** 
     * The <code>int</code> value representing the <code>strictfp</code> 
     * modifier.
     */
    public static final int STRICT = 2048;

    public Modifier() { }

    /** 
     * Return <tt>true</tt> if the integer argument includes the
     * <tt>public</tt> modifer, <tt>false</tt> otherwise.
     *
     * @param 	mod a set of modifers
     * @return <tt>true</tt> if <code>mod</code> includes the
     * <tt>public</tt> modifier; <tt>false</tt> otherwise.
     */
    public static boolean isPublic(int mod) {
        return false;
    }

    /** 
     * Return <tt>true</tt> if the integer argument includes the
     * <tt>private</tt> modifer, <tt>false</tt> otherwise.
     *
     * @param 	mod a set of modifers
     * @return <tt>true</tt> if <code>mod</code> includes the
     * <tt>private</tt> modifier; <tt>false</tt> otherwise.
     */
    public static boolean isPrivate(int mod) {
        return false;
    }

    /** 
     * Return <tt>true</tt> if the integer argument includes the
     * <tt>protected</tt> modifer, <tt>false</tt> otherwise.
     *
     * @param 	mod a set of modifers
     * @return <tt>true</tt> if <code>mod</code> includes the
     * <tt>protected</tt> modifier; <tt>false</tt> otherwise.
     */
    public static boolean isProtected(int mod) {
        return false;
    }

    /** 
     * Return <tt>true</tt> if the integer argument includes the
     * <tt>static</tt> modifer, <tt>false</tt> otherwise.
     *
     * @param 	mod a set of modifers
     * @return <tt>true</tt> if <code>mod</code> includes the
     * <tt>static</tt> modifier; <tt>false</tt> otherwise.
     */
    public static boolean isStatic(int mod) {
        return false;
    }

    /** 
     * Return <tt>true</tt> if the integer argument includes the
     * <tt>final</tt> modifer, <tt>false</tt> otherwise.
     *
     * @param 	mod a set of modifers
     * @return <tt>true</tt> if <code>mod</code> includes the
     * <tt>final</tt> modifier; <tt>false</tt> otherwise.
     */
    public static boolean isFinal(int mod) {
        return false;
    }

    /** 
     * Return <tt>true</tt> if the integer argument includes the
     * <tt>synchronized</tt> modifer, <tt>false</tt> otherwise.
     *
     * @param 	mod a set of modifers
     * @return <tt>true</tt> if <code>mod</code> includes the
     * <tt>synchronized</tt> modifier; <tt>false</tt> otherwise.
     */
    public static boolean isSynchronized(int mod) {
        return false;
    }

    /** 
     * Return <tt>true</tt> if the integer argument includes the
     * <tt>volatile</tt> modifer, <tt>false</tt> otherwise.
     *
     * @param 	mod a set of modifers
     * @return <tt>true</tt> if <code>mod</code> includes the
     * <tt>volatile</tt> modifier; <tt>false</tt> otherwise.
     */
    public static boolean isVolatile(int mod) {
        return false;
    }

    /** 
     * Return <tt>true</tt> if the integer argument includes the
     * <tt>transient</tt> modifer, <tt>false</tt> otherwise.
     *
     * @param 	mod a set of modifers
     * @return <tt>true</tt> if <code>mod</code> includes the
     * <tt>transient</tt> modifier; <tt>false</tt> otherwise.
     */
    public static boolean isTransient(int mod) {
        return false;
    }

    /** 
     * Return <tt>true</tt> if the integer argument includes the
     * <tt>native</tt> modifer, <tt>false</tt> otherwise.
     *
     * @param 	mod a set of modifers
     * @return <tt>true</tt> if <code>mod</code> includes the
     * <tt>native</tt> modifier; <tt>false</tt> otherwise.
     */
    public static boolean isNative(int mod) {
        return false;
    }

    /** 
     * Return <tt>true</tt> if the integer argument includes the
     * <tt>interface</tt> modifer, <tt>false</tt> otherwise.
     *
     * @param 	mod a set of modifers
     * @return <tt>true</tt> if <code>mod</code> includes the
     * <tt>interface</tt> modifier; <tt>false</tt> otherwise.
     */
    public static boolean isInterface(int mod) {
        return false;
    }

    /** 
     * Return <tt>true</tt> if the integer argument includes the
     * <tt>abstract</tt> modifer, <tt>false</tt> otherwise.
     *
     * @param 	mod a set of modifers
     * @return <tt>true</tt> if <code>mod</code> includes the
     * <tt>abstract</tt> modifier; <tt>false</tt> otherwise.
     */
    public static boolean isAbstract(int mod) {
        return false;
    }

    /** 
     * Return <tt>true</tt> if the integer argument includes the
     * <tt>strictfp</tt> modifer, <tt>false</tt> otherwise.
     *
     * @param 	mod a set of modifers
     * @return <tt>true</tt> if <code>mod</code> includes the
     * <tt>strictfp</tt> modifier; <tt>false</tt> otherwise.
     */
    public static boolean isStrict(int mod) {
        return false;
    }

    /** 
     * Return a string describing the access modifier flags in
     * the specified modifier. For example:
     * <blockquote><pre>
     *    public final synchronized strictfp
     * </pre></blockquote>
     * The modifier names are returned in an order consistent with the
     * suggested modifier orderings given in <a
     * href="http://java.sun.com/docs/books/jls/second_edition/html/j.title.doc.html"><em>The
     * Java Language Specification, Second Edition</em></a> sections
     * <a href="http://java.sun.com/docs/books/jls/second_edition/html/classes.doc.html#21613">&sect;8.1.1</a>, 
     * <a href="http://java.sun.com/docs/books/jls/second_edition/html/classes.doc.html#78091">&sect;8.3.1</a>, 
     * <a href="http://java.sun.com/docs/books/jls/second_edition/html/classes.doc.html#78188">&sect;8.4.3</a>, 
     * <a href="http://java.sun.com/docs/books/jls/second_edition/html/classes.doc.html#42018">&sect;8.8.3</a>, and
     * <a href="http://java.sun.com/docs/books/jls/second_edition/html/interfaces.doc.html#235947">&sect;9.1.1</a>.  
     * The full modifier ordering used by this method is:
     * <blockquote> <code> 
     * public protected private abstract static final transient
     * volatile synchronized native strictfp
     * interface </code> </blockquote> 
     * The <code>interface</code> modifier discussed in this class is
     * not a true modifier in the Java language and it appears after
     * all other modifiers listed by this method.  This method may
     * return a string of modifiers that are not valid modifiers of a
     * Java entity; in other words, no checking is done on the
     * possible validity of the combination of modifiers represented
     * by the input.
     *
     * @param	mod a set of modifers
     * @return	a string representation of the set of modifers
     * represented by <code>mod</code>
     */
    public static String toString(int mod) {
        return null;
    }
}
