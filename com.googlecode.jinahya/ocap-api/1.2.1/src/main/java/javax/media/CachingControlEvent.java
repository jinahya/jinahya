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

/** 
 * This event is generated by a <CODE>Controller</CODE> that supports
 * the <code>CachingControl</code> interface.  It is posted when the caching 
 * state changes.
 *
 * @see Controller
 * @see ControllerListener
 * @see CachingControl
 * @version 1.10, 97/08/23.
 */
public class CachingControlEvent extends ControllerEvent
{
     CachingControl control;

     long progress;

    /** 
     * Construct a <CODE>CachingControlEvent</CODE> from the required elements.
     */
    public CachingControlEvent(Controller from, CachingControl cacheControl,
        long progress)
    { super(from); }

    /** 
     * Get the <code>CachingControl</code> object that generated
     * the event.
     *
     * @return The <code>CachingControl</code> object.
     */
    public CachingControl getCachingControl() {
        return null;
    }

    /** 
     * Get the total number of bytes of media data that have been downloaded so far.
     *
     * @return The number of bytes of media data downloaded.
     */
    public long getContentProgress() {
        return -1;
    }
}
