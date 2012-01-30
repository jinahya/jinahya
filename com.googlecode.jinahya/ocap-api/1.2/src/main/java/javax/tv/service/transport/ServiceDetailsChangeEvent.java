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

import javax.tv.service.SIChangeType;
import javax.tv.service.navigation.ServiceDetails;

/** 
 * A <code>ServiceDetailsChangeEvent</code> notifies an
 * <code>ServiceDetailsChangeListener</code> of changes detected to a
 * <code>ServiceDetails</code> on a <code>Transport</code>.
 * Specifically, this event signals the addition, removal, or
 * modification of a <code>ServiceDetails</code>.
 * 
 * @see Transport
 * @see ServiceDetails
 */
public class ServiceDetailsChangeEvent extends TransportSIChangeEvent
{

    /** 
     * Constructs a <code>ServiceDetailsChangeEvent</code>.
     *
     * @param transport The <code>Transport</code> on which the change
     * occurred.
     *
     * @param type The type of change that occurred.
     *
     * @param s The <code>ServiceDetails</code> that changed.
     */
    public ServiceDetailsChangeEvent(Transport transport, SIChangeType type,
        ServiceDetails s)
    { super(transport, type, s); }

    /** 
     * Reports the <code>ServiceDetails</code> that changed.  It will be
     * identical to the object returned by the inherited
     * <code>SIChangeEvent.getSIElement</code> method.
     *
     * @return The <code>ServiceDetails</code> that changed.  
     */
    public ServiceDetails getServiceDetails() {
        return null;
    }
}
