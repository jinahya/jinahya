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



  


package javax.media;

import java.awt.Component;

/** 
 * <code>CachingControl</code> is an interface supported by <code>Players</code>
 * that are capable of reporting download progress.
 * Typically, this control is accessed through
 * the <code>Controller.getControls</code> method.
 * 
 * A <code>Controller</code> that supports this control will post
 * <code>CachingControlEvents</code> often enough to support the implementation
 * of custom progress GUIs.
 *
 * @see Controller
 * @see ControllerListener
 * @see CachingControlEvent
 * @see Player
 * @version 1.18, 97/08/25.
 */
public interface CachingControl extends Control
{
    /** 
     * Use to indicate that the <CODE>CachingControl</CODE> doesn't
     * know how long the content is.<p>
     * The definition is: LENGTH_UNKNOWN == Long.MAX_VALUE
     */
    public static final long LENGTH_UNKNOWN = 9223372036854775807L;

    /** 
     * Check whether or not media is being downloaded.
     *
     * @return  Returns <CODE>true</CODE> if media is being downloaded; 
     * otherwise returns <CODE>false</CODE>.
     *.
     */
    public boolean isDownloading();

    /** 
     * Get the total number of bytes in the media being downloaded. Returns
     * <code>LENGTH_UNKNOWN</code> if this information is not available.
     *
     * @return The media length in bytes, or <code>LENGTH_UNKNOWN</code>.
     */
    public long getContentLength();

    /** 
     * Get the total number of bytes of media data that have been downloaded so far.
     *
     * @return The number of bytes downloaded.
     */
    public long getContentProgress();

    /** 
     * Get a <CODE>Component</CODE> for displaying the download progress.
     *
     * @return Progress bar GUI.
     */
    public Component getProgressBarComponent();

    /** 
     * Get a <CODE>Component</CODE> that provides additional download control.
     *
     * Returns <CODE>null</CODE> if only a progress bar is provided. 
     *
     * @return Download control GUI.
     */
    public Component getControlComponent();
}
