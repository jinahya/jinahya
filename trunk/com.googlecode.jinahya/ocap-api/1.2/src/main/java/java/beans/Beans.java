/*
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
This work corresponds to the API signatures of JSR 217: Personal Basis
Profile 1.1. In the event of a discrepency between this work and the
JSR 217 specification, which is available at
http://www.jcp.org/en/jsr/detail?id=217, the latter takes precedence.
*/


  


package java.beans;

// import java.applet.*;
import java.awt.*;
import java.io.*;

// import java.beans.AppletInitializer;
// import java.beans.beancontext.BeanContext;
import java.lang.reflect.Constructor;
import java.net.URL;
import java.lang.reflect.Array;

/** 
 * This class provides some general purpose beans control methods.
 */
public class Beans
{

    public Beans() { }

   /** 
     * <p>
     * Instantiate a JavaBean.
     * </p>
     *
     * <!- PBP/PP 5041659 ->
     * <em>
     * The bean is created based on a name relative to a class-loader.
     * This name should be a dot-separated name such as "a.b.c".
     * <p>
     * In Beans 1.0 the given name can indicate either a serialized object
     * or a class.  Other mechanisms may be added in the future.  In
     * beans 1.0 we first try to treat the beanName as a serialized object
     * name then as a class name.
     * <p>
     * When using the beanName as a serialized object name we convert the
     * given beanName to a resource pathname and add a trailing ".ser" suffix.
     * We then try to load a serialized object from that resource.
     * <p>
     * For example, given a beanName of "x.y", Beans.instantiate would first
     * try to read a serialized object from the resource "x/y.ser" and if
     * that failed it would try to load the class "x.y" and create an
     * instance of that class.
     * <p>
     * 
     * </em>
     *
     * @param     cls         the class-loader from which we should create
     * 		              the bean.  If this is null, then the system
     *                        class-loader is used.
     * @param     beanName    the name of the bean within the class-loader.
     *   	              For example "sun.beanbox.foobah"
     *
     * @exception java.lang.ClassNotFoundException if the class of a serialized
     *              object could not be found.
     * @exception java.io.IOException if an I/O error occurs.
     */
    public static Object instantiate(ClassLoader cls, String beanName)
        throws IOException, ClassNotFoundException
    { return null; }

    // /** 
     // * <p>
     // * Instantiate a JavaBean.
     // * </p>
     // *
     // * @param     cls         the class-loader from which we should create
     // * 		              the bean.  If this is null, then the system
     // *                        class-loader is used.
     // * @param     beanName    the name of the bean within the class-loader.
     // *   	              For example "sun.beanbox.foobah"
     // * @param     beanContext The BeanContext in which to nest the new bean
     // *
     // * @exception java.lang.ClassNotFoundException if the class of a serialized
     // *              object could not be found.
     // * @exception java.io.IOException if an I/O error occurs.
     // */
    // public static Object instantiate(ClassLoader cls, String beanName,
        // BeanContext beanContext) throws IOException, ClassNotFoundException
    // { }

    // /** 
     // * Instantiate a bean.
     // * <p>
     // * The bean is created based on a name relative to a class-loader.
     // * This name should be a dot-separated name such as "a.b.c".
     // * <p>
     // * In Beans 1.0 the given name can indicate either a serialized object
     // * or a class.  Other mechanisms may be added in the future.  In
     // * beans 1.0 we first try to treat the beanName as a serialized object
     // * name then as a class name.
     // * <p>
     // * When using the beanName as a serialized object name we convert the
     // * given beanName to a resource pathname and add a trailing ".ser" suffix.
     // * We then try to load a serialized object from that resource.
     // * <p>
     // * For example, given a beanName of "x.y", Beans.instantiate would first
     // * try to read a serialized object from the resource "x/y.ser" and if
     // * that failed it would try to load the class "x.y" and create an
     // * instance of that class.
     // * <p>
     // * If the bean is a subtype of java.applet.Applet, then it is given
     // * some special initialization.  First, it is supplied with a default
     // * AppletStub and AppletContext.  Second, if it was instantiated from
     // * a classname the applet's "init" method is called.  (If the bean was
     // * deserialized this step is skipped.)
     // * <p>
     // * Note that for beans which are applets, it is the caller's responsiblity
     // * to call "start" on the applet.  For correct behaviour, this should be done
     // * after the applet has been added into a visible AWT container.
     // * <p>
     // * Note that applets created via beans.instantiate run in a slightly
     // * different environment than applets running inside browsers.  In
     // * particular, bean applets have no access to "parameters", so they may
     // * wish to provide property get/set methods to set parameter values.  We
     // * advise bean-applet developers to test their bean-applets against both
     // * the SDK appletviewer (for a reference browser environment) and the
     // * BDK BeanBox (for a reference bean container).
     // *
     // * @param     cls         the class-loader from which we should create
     // * 		              the bean.  If this is null, then the system
     // *                        class-loader is used.
     // * @param     beanName    the name of the bean within the class-loader.
     // *   	              For example "sun.beanbox.foobah"
     // * @param     beanContext The BeanContext in which to nest the new bean
     // * @param     initializer The AppletInitializer for the new bean
     // *
     // * @exception java.lang.ClassNotFoundException if the class of a serialized
     // *              object could not be found.
     // * @exception java.io.IOException if an I/O error occurs.
     // */
    // public static Object instantiate(ClassLoader cls, String beanName,
        // BeanContext beanContext, java.beans.AppletInitializer initializer)
        // throws IOException, ClassNotFoundException
    // { }

    // /** 
     // * From a given bean, obtain an object representing a specified
     // * type view of that source object.
     // * <p>
     // * The result may be the same object or a different object.  If
     // * the requested target view isn't available then the given
     // * bean is returned.
     // * <p>
     // * This method is provided in Beans 1.0 as a hook to allow the
     // * addition of more flexible bean behaviour in the future.
     // *
     // * @param bean        Object from which we want to obtain a view.
     // * @param targetType  The type of view we'd like to get.
     // *
     // */
    // public static Object getInstanceOf(Object bean, Class targetType) { }

    // /** 
     // * Check if a bean can be viewed as a given target type.
     // * The result will be true if the Beans.getInstanceof method
     // * can be used on the given bean to obtain an object that
     // * represents the specified targetType type view.
     // *
     // * @param bean  Bean from which we want to obtain a view.
     // * @param targetType  The type of view we'd like to get.
     // * @return "true" if the given bean supports the given targetType.
     // *
     // */
    // public static boolean isInstanceOf(Object bean, Class targetType) { }

  // PBP/PP
  // [6187212]

    /** 
     * Test if we are in design-mode.
     *
     * <p>
     * <em>This method always returns <code>false</code> in
     * this Profile.</em>
     * @return  True if we are running in an application construction
     *		environment.
     *
     * 
     * 
     */
    public static boolean isDesignTime() {return false; }

    /** 
     * Determines whether beans can assume a GUI is available.
     *
     * @return  True if we are running in an environment where beans
     *	   can assume that an interactive GUI is available, so they
     *	   can pop up dialog boxes, etc.  This will normally return
     *	   true in a windowing environment, and will normally return
     *	   false in a server environment or if an application is
     *	   running as part of a batch job.
     *
     * @see java.beans.Visibility
     *
     */
    public static boolean isGuiAvailable() {return false; }

    // /** 
     // * Used to indicate whether of not we are running in an application
     // * builder environment.  
     // * 
     // * <p>Note that this method is security checked
     // * and is not available to (for example) untrusted applets.
     // * More specifically, if there is a security manager, 
     // * its <code>checkPropertiesAccess</code> 
     // * method is called. This could result in a SecurityException.
     // *
     // * @param isDesignTime  True if we're in an application builder tool.
     // * @exception  SecurityException  if a security manager exists and its  
     // *             <code>checkPropertiesAccess</code> method doesn't allow setting
     // *              of system properties.
     // * @see SecurityManager#checkPropertiesAccess
     // */
    // public static void setDesignTime(boolean isDesignTime)
        // throws SecurityException
    // { }

    // /** 
     // * Used to indicate whether of not we are running in an environment
     // * where GUI interaction is available.  
     // * 
     // * <p>Note that this method is security checked
     // * and is not available to (for example) untrusted applets.
     // * More specifically, if there is a security manager, 
     // * its <code>checkPropertiesAccess</code> 
     // * method is called. This could result in a SecurityException.
     // *
     // * @param isGuiAvailable  True if GUI interaction is available.
     // * @exception  SecurityException  if a security manager exists and its  
     // *             <code>checkPropertiesAccess</code> method doesn't allow setting
     // *              of system properties.
     // * @see SecurityManager#checkPropertiesAccess
     // */
    // public static void setGuiAvailable(boolean isGuiAvailable)
        // throws SecurityException
    // { }
}
