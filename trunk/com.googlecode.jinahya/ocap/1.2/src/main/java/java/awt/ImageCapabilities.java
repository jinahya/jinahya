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



package java.awt;

/**
 * Capabilities and properties of images.
 * @author Michael Martak
 * @since 1.4
 */
public class ImageCapabilities implements Cloneable {
    
    private boolean accelerated = false;
    
    /**
     * Creates a new object for specifying image capabilities.
     * @param accelerated whether or not an accelerated image is desired
     */
    public ImageCapabilities(boolean accelerated) {
        this.accelerated = accelerated;
    }
    
    /**
     * Returns <code>true</code> if the object whose capabilities are
     * encapsulated in this <code>ImageCapabilities</code> can be or is
     * accelerated. 
     * @return whether or not an image can be, or is, accelerated.  There are
     * various platform-specific ways to accelerate an image, including
     * pixmaps, VRAM, AGP.  This is the general acceleration method (as
     * opposed to residing in system memory).
     */
    public boolean isAccelerated() {
        return accelerated;
    }
    
    /**
     * Returns <code>true</code> if the <code>VolatileImage</code>
     * described by this <code>ImageCapabilities</code> can lose
     * its surfaces.
     * @return whether or not a volatile image is subject to losing its surfaces
     * at the whim of the operating system.
     */
    public boolean isTrueVolatile() {
        return false;
    }

    /**
     * @return a copy of this ImageCapabilities object.
     */
    public Object clone() {
        try {
            return super.clone();
        } catch (CloneNotSupportedException e) {
            // Since we implement Cloneable, this should never happen
            throw new InternalError();
        }
    }

}
