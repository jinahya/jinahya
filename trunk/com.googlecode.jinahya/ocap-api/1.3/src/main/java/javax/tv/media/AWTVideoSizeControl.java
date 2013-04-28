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



  


package javax.tv.media;

import java.awt.Rectangle;
import java.awt.Dimension;

/** 
 * <code>AWTVideoSizeControl</code> allows setting clipping, scaling, and
 * translation of a video stream in a simple, interoperable way.  Not
 * all possible combinations of positioning will be supported, so this
 * interface provides a mechanism to discover how closely the
 * underlying platform will approximate a request for positioning.
 * 
 * <p> All interactions via AWTVideoSizeControl happen in the
 * coordinate space of the screen.  For example, successfully setting
 * the video's position to the location reported by
 * <code>Component.getLocationOnScreen()</code> on the
 * <code>Xlet</code>'s root container will cause the upper left-hand
 * corner of the video and the root container to coincide.  <p> The
 * screen, in the context of AWT, is the area into which graphics
 * drawing operations are done.  Its size is given by
 * java.awt.Toolkit.getScreenSize(), and locations reported by
 * Component.getLocationOnScreen() are given in the screen's
 * coordinate system.<p>
 * 
 * Instances of <code>AWTVideoSizeControl</code> may be obtained from
 * a JMF <code>Player</code> via the methods
 * <code>getControl(String)</code> and <code>getControls()</code>.
 * Note that a Java TV API implementation may not always or ever
 * support <code>AWTVideoSizeControl</code> for a given Player; in
 * such a case, the failure modes specified by the two aforementioned
 * methods will apply.
 * 
 * @version     1.16, 10/09/00
 * @author      Bill Foote
 *
 * @see javax.tv.media.AWTVideoSize
 * @see java.awt.Component#getLocationOnScreen java.awt.Component.getLocationOnScreen()
 * @see javax.media.Player
 */
public interface AWTVideoSizeControl extends javax.media.Control
{

    /** 
     * Reports the <code>AWTVideoSize</code> at which the Player is
     * currently operating.
     *
     * @return A copy of the JMF Player's current video size, in the AWT
     * coordinate space.  
     */
    public AWTVideoSize getSize();

    /** 
     * Reports the default <code>AWTVideoSize</code> for this control.
     * For the background video plane, this will be the size that the
     * video would be presented at if no program had manipulated the
     * video size.
     *
     * @return The default <code>AWTVideoSize</code>.
     */
    public AWTVideoSize getDefaultSize();

    /** 
     * Reports the size of the source video, in the screen's
     * coordinate system.
     *
     * @return The size of the source video.
     */
    public Dimension getSourceVideoSize();

    /** 
     * Sets the video size.  If the size provided cannot be supported
     * by the underlying platform, this method does nothing and
     * returns <code>false</code>.
     *
     * @param sz The desired video size, in the AWT coordinate space.
     *
     * @return <code>true</code> if the size was successfully changed;
     * <code>false</code> if the platform is incapable of supporting
     * the given size.
     *
     * @see #checkSize(AWTVideoSize) 
     */
    public boolean setSize(AWTVideoSize sz);

    /** 
     * Reports how closely the underlying platform can approximate a
     * desired video size.  If the underlying platform cannot support
     * the given size, this method gives the closest approximation
     * that the platform is capable of.
     *
     * @param sz The desired video size.
     *
     * @return The actual size that the platform would be able to set.  
     */
    public AWTVideoSize checkSize(AWTVideoSize sz);
}
