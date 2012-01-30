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
 * The <code>Compiler</code> class is provided to support
 * Java-to-native-code compilers and related services. By design, the
 * <code>Compiler</code> class does nothing; it serves as a
 * placeholder for a JIT compiler implementation.
 * <p>
 * When the Java Virtual Machine first starts, it determines if the
 * system property <code>java.compiler</code> exists. (System
 * properties are accessible through <code>getProperty</code> and, 
 * a method defined by the <code>System</code> class.) If so, and the
 * name isn't <b>NONE</b> or <b>none</b>, the internal JIT is enabled.
 * <p>
 * If no compiler is available, these methods do nothing.
 *
 * @author  Frank Yellin
 * @version 1.17 10/17/00
 * @see     java.lang.System#getProperty(java.lang.String)
 * @see     java.lang.System#getProperty(java.lang.String, java.lang.String)
 * @since   JDK1.0
 */
public final class Compiler
{

    /*
     * This hidden constructor does not necessarily correspond to
     * a constructor in the original source file -- it keeps javadoc
     * from generating an inappropriate default constructor.
     */
    private Compiler() { }

    /** 
     * Compiles the specified class.
     *
     * @param   clazz   a class.
     * @return  <code>true</code> if the compilation succeeded;
     *          <code>false</code> if the compilation failed or no compiler
     *          is available.
     * @exception NullPointerException if <code>clazz</code> is 
     *          <code>null</code>.
     */
    public static boolean compileClass(java.lang.Class clazz) {
        return false;
    }

    /** 
     * Compiles all classes whose name matches the specified string.
     *
     * @param   string   the name of the classes to compile.
     * @return  <code>true</code> if the compilation succeeded;
     *          <code>false</code> if the compilation failed or no compiler
     *          is available.
     * @exception NullPointerException if <code>string</code> is 
     *          <code>null</code>.
     */
    public static boolean compileClasses(java.lang.String string) {
        return false;
    }

    /** 
     * Examines the argument type and its fields and perform some documented
     * operation. No specific operations are required.
     *
     * @param   any   an argument.
     * @return  a compiler-specific value, or <code>null</code> if no compiler
     *          is available.
     * @exception NullPointerException if <code>any</code> is 
     *          <code>null</code>.
     */
    public static java.lang.Object command(java.lang.Object any) {
        return null;
    }

    /** 
     * Cause the Compiler to resume operation. (This a noop on Solaris).
     */
    public static void enable() { }

    /** 
     * Cause the Compiler to cease operation.  (This a noop on Solaris).
     */
    public static void disable() { }
}
