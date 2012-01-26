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
 * The <code>Duration</code> interface provides a way to determine the
 * duration of the media being played by a media object.
 * Media objects that expose a media duration
 * implement this interface. 
 * <p>
 *
 * A <code>Controller</code> that supports the <code>Duration</code> interface
 * posts a <code>DurationUpdateEvent</code> whenever its
 * duration changes.
 * 
 * @see Controller
 * @see DurationUpdateEvent
 *
 * @version 1.16, 97/08/23
 */
public interface Duration
{
    /** 
     * Returned by <code>getDuration</code>.
     */
    public static final Time DURATION_UNBOUNDED = null;

    /** 
     * Returned by <code>getDuration</code>.
     */
    public static final Time DURATION_UNKNOWN = null;

    /** 
     * Get the duration of the media represented
     * by this object.
     * The value returned is the media's duration
     * when played at the default rate.
     * If the duration can't be determined  (for example, the media object is presenting live
     * video)  <CODE>getDuration</CODE> returns <CODE>DURATION_UNKNOWN</CODE>.
     *
     * @return A <CODE>Time</CODE> object representing the duration or DURATION_UNKNOWN.
     */
    public Time getDuration();
}
