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
 * <code>ControllerEvent</code> is the base class for events generated by
 * a <CODE>Controller</CODE>.
 *
 * These events are used by <CODE>ControllerListener</CODE>.
 *
 * @see Controller
 * @see ControllerListener
 * @see MediaEvent
 * @version 1.12, 05/10/17
 */
public class ControllerEvent extends java.util.EventObject implements MediaEvent
{
     Controller eventSrc;

    public ControllerEvent(Controller from) { 
        super(from);
    }

    /** 
     * Get the <CODE>Controller</CODE> that posted this event.  
     * The returned <CODE>Controller</CODE> has at least one active
     * listener. (The 
     * <CODE>addListener</CODE> method has been called on
     * the <CODE>Controller</CODE>).
     * 
     * @return The <CODE>Controller</CODE> that posted this event.
     */
    public Controller getSourceController() {
        return null;
    }

    public Object getSource() {
        return null;
    }
}
