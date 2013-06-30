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
 * <code>TransitionEvent</code> is a <code>ControllerEvent</code> that indicates
 * that a <code>Controller</code> has changed state.
 *
 *
 * @see Controller
 * @see ControllerListener
 * @version 1.10, 97/08/23
 */
public class TransitionEvent extends ControllerEvent
{
     int previousState;

     int currentState;

     int targetState;

    /** 
     * Construct a new <CODE>TransitionEvent</CODE>.
     *
     * @param from The <code>Controller</code> that is generating this event.
     * @param previous The state that the <code>Controller</code> was in before this event.
     * @param current The state that the <code>Controller</code> is in as a result of this event.
     * @param target The state that the <code>Controller</code> is heading to.
     */
    public TransitionEvent(Controller from, int previous, int current, int
        target)
    { super(from); }

    /** 
     * Get the state that the <code>Controller</code> was in before this event occurred.
     *
     * @return The  <code>Controller's</code> previous state.
     */
    public int getPreviousState() {
        return 0;
    }

    /** 
     * Get the <code>Controller's</code> state at the time this event was
     * generated
     *
     * @return The <code>Controller's</code> current state.
     */
    public int getCurrentState() {
        return 0;
    }

    /** 
     * Get the <code>Controller's</code> target state at the time this event
     * was generated.
     *
     * @return The <code>Controller's</code> target state.
     */
    public int getTargetState() {
        return 0;
    }
}
