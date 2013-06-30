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



  


package javax.tv.service.guide;

import javax.tv.service.*;

/** 
 * A <code>ProgramScheduleEvent</code> notifies an
 * <code>ProgramScheduleListener</code> of changes to program events
 * detected in a <code>ProgramSchedule</code>.  Specifically, this
 * event signals the addition, removal, or modification of a
 * <code>ProgramEvent</code> in a <code>ProgramSchedule</code>, or a
 * change to the <code>ProgramEvent</code> that is current.<p>
 *
 * The class <code>ProgramScheduleChangeType</code> defines the kinds
 * of changes reported by <code>ProgramScheduleEvent</code>.  A
 * <code>ProgramScheduleChangeType</code> of
 * <code>CURRENT_PROGRAM_EVENT</code> indicates that the current
 * <code>ProgramEvent</code> of a <code>ProgramSchedule</code> has
 * changed in identity.
 *
 * @see ProgramScheduleListener
 * @see ProgramScheduleChangeType
 */
public class ProgramScheduleEvent extends SIChangeEvent
{

    /** 
     * Constructs a <code>ProgramScheduleEvent</code>.
     *
     * @param schedule The schedule in which the change occurred.
     *
     * @param type The type of change that occurred.
     *
     * @param e The <code>ProgramEvent</code> that changed.
     */
    public ProgramScheduleEvent(ProgramSchedule schedule, SIChangeType type,
        ProgramEvent e)
    { super(schedule, type, e); }

    /** 
     * Reports the <code>ProgramSchedule</code> that generated the
     * event.  The object returned will be identical to the object
     * returned by the inherited <code>EventObject.getSource()</code>
     * method.
     *
     * @return The <code>ProgramSchedule</code> that generated the event.
     *
     * @see java.util.EventObject#getSource
     */
    public ProgramSchedule getProgramSchedule() {
        return null;
    }

    /** 
     * Reports the <code>ProgramEvent</code> that changed.  If the
     * <code>ProgramScheduleChangeType</code> is
     * <code>CURRENT_PROGRAM_EVENT</code>, the <code>ProgramEvent</code>
     * that became current will be returned.  The object returned will
     * be identical to the object returned by inherited
     * <code>SIChangeEvent.getSIElement</code> method.
     *
     * @return The <code>ProgramEvent</code> that changed.
     *
     * @see javax.tv.service.SIChangeEvent#getSIElement
     */
    public ProgramEvent getProgramEvent() {
        return null;
    }
}
