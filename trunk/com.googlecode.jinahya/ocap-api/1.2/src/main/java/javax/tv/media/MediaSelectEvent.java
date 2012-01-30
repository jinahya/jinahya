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

import javax.tv.locator.*;

import javax.media.Controller;

/** 
 * <code>MediaSelectEvent</code> is the base class of events sent to
 * <code>MediaSelectListener</code> instances.
 *
 * @see MediaSelectListener
 */
public abstract class MediaSelectEvent extends java.util.EventObject
{

    /** 
     * Creates a new <code>MediaSelectEvent</code>.
     * 
     * @param controller The Controller that generated this event.
     * @param selection The <code>Locator</code> instances on which
     * selection was attempted.
     */
    public MediaSelectEvent(Controller controller, Locator[] selection) { 
        super(controller);
    }

    /** 
     * Reports the Controller that generated this event.
     *
     * @return The Controller that generated this event.
     */
    public Controller getController() {
        return null;
    }

    /** 
     * Reports the selection that caused this event.
     * This corresponds to the service component(s) specified as parameters
     * of the <code>MediaSelectControl</code> methods
     * {@link MediaSelectControl#add add(Locator)},
     * {@link MediaSelectControl#remove remove(Locator)},
     * {@link MediaSelectControl#select(Locator) select(Locator)} and
     * {@link MediaSelectControl#select(Locator[]) select(Locator[])},
     * and the paramter <code>toComponent</code> of the method
     * {@link MediaSelectControl#replace(Locator, Locator) replace(fromComponent, toComponent)}.
     *
     * @return The selection that caused this event.
     * @see MediaSelectControl
     */
    public Locator[] getSelection() {
        return null;
    }
}
