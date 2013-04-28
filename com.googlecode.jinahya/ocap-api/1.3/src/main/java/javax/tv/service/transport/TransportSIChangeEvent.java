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



  


package javax.tv.service.transport;

import javax.tv.service.*;

/** 
 * An <code>TransportSIChangeEvent</code> notifies an
 * <code>SIChangeListener</code> of changes detected to the SI on a
 * <code>Transport</code>.<p>
 *
 * Subtypes <code>ServiceDetailsChangeEvent</code>,
 * <code>TransportStreamChangeEvent</code>,
 * <code>NetworkChangeEvent</code> and <code>BouquetChangeEvent</code>
 * are used to signal changes to service details, transport streams,
 * networks and bouquets, respectively.  Changes to program events are
 * signaled through <code>ProgramScheduleChangeEvent</code>.
 *
 * @see Transport
 */
public abstract class TransportSIChangeEvent extends SIChangeEvent
{

    /** 
     * Constructs an <code>TransportSIChangeEvent</code>.
     *
     * @param transport The <code>Transport</code> on which the change
     * occurred.
     *
     * @param type The type of change that occurred.
     *
     * @param e The <code>SIElement</code> that changed.
     */
    public TransportSIChangeEvent(Transport transport, SIChangeType type,
        SIElement e)
    { super(transport, type, e); }

    /** 
     * Reports the <code>Transport</code> that generated the event.  It
     * will be identical to the object returned by the
     * <code>getSource()</code> method.
     *
     * @return The <code>Transport</code> that generated the event.
     */
    public Transport getTransport() {
        return null;
    }
}
