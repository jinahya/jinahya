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


  


package java.awt.event;

import java.util.EventListenerProxy;
import java.awt.AWTEvent;
import java.awt.event.AWTEventListener;

/** 
 * A class which extends the <code>EventListenerProxy</code>, specifically 
 * for adding an <code>AWTEventListener</code> for a specific event mask.
 * Instances of this class can be added as <code>AWTEventListener</code>s to
 * a Toolkit object. 
 * <p>
 * The <code>getAWTEventListeners</code> method of Toolkit can 
 * return a mixture of <code>AWTEventListener</code> and
 * <code>AWTEventListenerProxy</code> objects.
 * 
 * @see java.awt.Toolkit
 * @see java.util.EventListenerProxy
 * @since 1.4
 */
public class AWTEventListenerProxy extends EventListenerProxy
    implements java.awt.event.AWTEventListener
{

    /** 
     * Constructor which binds the AWTEventListener to a specific
     * event mask.
     * 
     * @param listener The listener object
     * @param eventMask The bitmap of event types to receive
     */
    public AWTEventListenerProxy(long eventMask, java.awt.event.AWTEventListener
        listener)
    { super(null); }

    /** 
     * Forwards the property change event to the listener delegate.
     *
     * @param evt the property change event
     */
    public void eventDispatched(AWTEvent evt) { }

    /** 
     * Returns the event mask associated with the
     * listener.
     */
    public long getEventMask() { return 0; }
}
