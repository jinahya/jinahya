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



  


package javax.tv.service.navigation;

import javax.tv.service.*;

import javax.tv.service.guide.ProgramSchedule;

/** 
 * This interface provides access to service meta-data. It provides more
 * information about a <code>Service</code> object and represents a
 * specific instance of a service bound to a transport stream. <p>
 *
 * A <code>ServiceDetails</code> object may optionally implement the
 * <code>ServiceNumber</code> interface to report service numbers as
 * assigned by the broadcaster of the service.<p>
 *
 * A <code>ServiceDetails</code> object may optionally implement the
 * <code>ServiceProviderInformation</code> interface to report information
 * concerning the service provider.
 *
 * @see javax.tv.service.Service
 *
 * @see javax.tv.service.ServiceNumber
 *
 * @see ServiceProviderInformation
 *
 * @see <a href="../../../../overview-summary.html#guidelines-opinterfaces">Optionally implemented interfaces</a>
 */
public interface ServiceDetails extends SIElement, CAIdentification
{

    /** 
     * Retrieves a textual description of this service if available.
     * This method delivers its results asynchronously.
     *
     * @param requestor The <code>SIRequestor</code> to be notified
     * when this retrieval operation completes.
     * 
     * @return An <code>SIRequest</code> object identifying this
     * asynchronous retrieval request.
     *
     * @see ServiceDescription
     */
    public SIRequest retrieveServiceDescription(SIRequestor requestor);

    /** 
     * Returns the type of this service, for example, "digital
     * television", "digital radio", "NVOD", etc. These values can be
     * mapped to the ATSC service type in the VCT table and the DVB
     * service type in the Service Descriptor.
     *
     * @return Service type of this service.  
     */
    public ServiceType getServiceType();

    /** 
     * Retrieves an array of elementary components which are part of
     * this service.  The array will only contain
     * <code>ServiceComponent</code> instances <code>c</code> for which
     * the caller has
     * <code>javax.tv.service.ReadPermission(c.getLocator())</code>.  If
     * no <code>ServiceComponent</code> instances meet this criteria,
     * this method will result in an <code>SIRequestFailureType</code> of
     * <code>DATA_UNAVAILABLE</code>.
     *
     * This method delivers its results asynchronously.
     *
     * @param requestor The <code>SIRequestor</code> to be notified
     * when this retrieval operation completes.
     * 
     * @return An <code>SIRequest</code> object identifying this
     * asynchronous retrieval request.
     *
     * @see ServiceComponent
     * @see javax.tv.service.ReadPermission
     */
    public SIRequest retrieveComponents(SIRequestor requestor);

    /** 
     * Returns a schedule of program events associated with this service.
     *
     * @return The program schedule for this service, or <code>null</code>
     * if no schedule is available.
     */
    public ProgramSchedule getProgramSchedule();

    /** 
     * Called to obtain a full service name. For example, this
     * information may be delivered in the ATSC Extended Channel Name
     * Descriptor, the DVB Service Descriptor or the DVB Multilingual
     * Service Name Descriptor.
     *
     * @return A string representing the full service name, or an empty
     * string if the name is not available.
     */
    public String getLongName();

    /** 
     * Returns the <code>Service</code> this <code>ServiceDetails</code>
     * object is associated with.
     *
     * @return The <code>Service</code> to which this
     * <code>ServiceDetails</code> belongs.
     */
    public Service getService();

    /** 
     * Registers a <code>ServiceComponentChangeListener</code> to be
     * notified of changes to a <code>ServiceComponent</code> that is
     * part of this <code>ServiceDetails</code>. Subsequent notification
     * is made via <code>ServiceComponentChangeEvent</code> with this
     * <code>ServiceDetails</code> instance as the event source and an
     * <code>SIChangeType</code> of <code>ADD</code>,
     * <code>REMOVE</code> or <code>MODIFY</code>. Only changes to
     * <code>ServiceComponent</code> instances <code>c</code> for which
     * the caller has
     * <code>javax.tv.service.ReadPermission(c.getLocator())</code> will
     * be reported.<p>
     * 
     * This method is only a request for notification.  No guarantee is
     * provided that the SI database will detect all, or even any, SI
     * changes or whether such changes will be detected in a timely
     * fashion.<p>
     * 
     * If the specified <code>ServiceComponentChangeListener</code> is
     * already registered, no action is performed.
     *
     * @param listener A <code>ServiceComponentChangeListener</code> to be
     * notified about changes related to a <code>ServiceComponent</code>
     * in this <code>ServiceDetails</code>.
     *
     * @see ServiceComponentChangeEvent
     * @see javax.tv.service.ReadPermission
     */
    public void addServiceComponentChangeListener(ServiceComponentChangeListener
        listener);

    /** 
     * Called to unregister an
     * <code>ServiceComponentChangeListener</code>.  If the specified
     * <code>ServiceComponentChangeListener</code> is not registered, no
     * action is performed.
     *
     * @param listener A previously registered listener.  
     */
    public void
        removeServiceComponentChangeListener(ServiceComponentChangeListener
        listener);

    /** 
     * Reports the type of mechanism by which this service was
     * delivered.
     *
     * @return The delivery system type of this service.
     */
    public DeliverySystemType getDeliverySystemType();
}
