/**
<p>This is not an official specification document, and usage is restricted.
</p>
<a name="notice"><strong><center>
NOTICE
</center></strong><br>
<br>

(c) 2005-2008 Sun Microsystems, Inc. All Rights Reserved.
<p>

Neither this file nor any files generated from it describe a complete
specification, and they may only be used as described below.  
<p>
Sun Microsystems Inc. owns the copyright in this file and it is provided
to you for informative use only. For example, 
this file and any files generated from it may be used to generate other documentation, 
such as a unified set of documents of API signatures for a platform 
that includes technologies expressed as Java APIs. 
This file may also be used to produce "compilation stubs," 
which allow applications to be compiled and validated for such platforms. 
By contrast, no permission is given for you to incorporate this file, 
in whole or in part, in an implementation of a Java specification.
<p>
Any work generated from this file, such as unified javadocs or compiled
stub files, must be accompanied by this notice in its entirety.
<p>
This work corresponds to the API signatures of JSR 927: Java TV API 1.1.1.  
In the event of a discrepency between this work and the JSR 927 specification, 
which is available at http://www.jcp.org/en/jsr/detail?id=927, the latter takes precedence.
*/



  


package javax.tv.graphics;

/** 
 * A class that allows an Xlet to get the root container for its AWT
 * components.
 *
 * @version     1.13, 10/24/05
 */
public class TVContainer
{

    /*
     * This hidden constructor does not necessarily correspond to
     * a constructor in the original source file -- it keeps javadoc
     * from generating an inappropriate default constructor.
     */
    private TVContainer() { }

    /** 
     * Get the parent container for an Xlet to put its AWT components
     * in, if the Xlet has a graphical representation.  Xlets without a
     * graphical representation should never call this method.  If the Xlet
     * is the only Xlet that is currently active to invoke this method,
     * it will return an instance of <code>java.awt.Container</code> that
     * is initially invisible, with an undefined
     * size and position.  If another Xlet that is currently
     * active has requested a root container (via this API, or some
     * other platform-specific API), this method may return null.
     * <p>
     * If this method is called multiple times for the same XletContext, 
     * the same container will be returned each time.
     * <p>
     * The methods for setting the size and position of the xlet's root
     * container shall attempt to change the shape of the container, but
     * they may fail silently or make platform specific approximations.
     * For example, a request to change the shape of the root container might
     * result in its overlapping with another root container, and overlapping
     * root containers might not be supported by the hardware.  An application
     * that needs to discover if a request to change the size or position
     * has succeeded should query the component for the result.
     *
     * @param ctx The XletContext of the Xlet requesting the container.
     * 
     * @return An AWT <code>Container</code>, or <code>null</code> if no
     * container is available.
     */
    public static java.awt.Container getRootContainer(javax.tv.xlet.XletContext
        ctx)
    {
        return null;
    }
}
