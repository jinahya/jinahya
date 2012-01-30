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
 * <code>StartEvent</code> is a <code>TransitionEvent</code> that indicates that
 * a <code>Controller</code> has entered the <i>Started</i> state.
 * Entering the <i>Started</i> state implies that 
 * <code>syncStart</code> has been invoked, providing a new
 * <i>media time</i> to <i>time-base time</i> mapping.
 * <code>StartEvent</code> provides the <I>time-base time</I>
 * and the <I>media-time</I> that <i>Started</i> this <CODE>Controller</CODE>.
 * 
 * @see Controller
 * @see ControllerListener
 * @version 1.31, 97/08/23
 */
public class StartEvent extends TransitionEvent
{
    private Time mediaTime;

    private Time timeBaseTime;

    /** 
     * Construct a new <code>StartEvent</code>.
     * The <code>from</code> argument identifies the <code>Controller</code> that
     * is generating this event.
     * The <code>mediaTime</code> and the <code>tbTime</code> identify the <I>media-time</I> to
     * <I>time-base-time</I> mapping that <i>Started</i> the <code>Controller</code>
     * @param from The <code>Controller</code> that has <I>Started</I>.
     * @param mediaTime The media time when the <code>Controller</code> <I>Started</I>.
     * @param tbTime The time-base time when the <code>Controller</code> <I>Started</I>.
     *
     */
    public StartEvent(Controller from, int previous, int current, int target,
        Time mediaTime, Time tbTime)
    { super(from, previous, current, target); }

    /** 
     * Get the clock time (<I>media time</I>) when the <code>Controller</code> started.
     *
     * @return The <code>Controller's</code>&nbsp;<I>media time</I> when it started.
     */
    public Time getMediaTime() {
        return null;
    }

    /** 
     * Get the time-base time that started the <code>Controller</code>.
     * @return The <I>time-base time</I> associated with the <code>Controller</code> when it started.
     */
    public Time getTimeBaseTime() {
        return null;
    }
}
