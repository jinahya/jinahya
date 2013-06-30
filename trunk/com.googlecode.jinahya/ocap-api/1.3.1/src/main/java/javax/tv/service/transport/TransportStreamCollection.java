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
import javax.tv.locator.*;

/** 
 * This interface represents a collection of transport streams on a
 * <code>Transport</code>.  <code>TransportStreamCollection</code> may
 * be optionally implemented by <code>Transport</code> objects,
 * depending on the SI data carried on that transport.
 *
 * @see <a href="../../../../overview-summary.html#guidelines-opinterfaces">Optionally implemented interfaces</a>
 */
public interface TransportStreamCollection extends Transport
{

    /** 
     * Retrieves the specified <code>TransportStream</code> from the
     * collection.
     *
     * @param locator Locator referencing the
     * <code>TransportStream</code> of interest.
     *
     * @param requestor The <code>SIRequestor</code> to be notified
     * when this retrieval operation completes.
     * 
     * @return An <code>SIRequest</code> object identifying this
     * asynchronous retrieval request.  
     *
     * @throws InvalidLocatorException If <code>locator</code> does not
     * reference a valid transport stream.
     *
     * @throws SecurityException If the caller does not have
     * <code>javax.tv.service.ReadPermission(locator)</code>.
     *
     * @see TransportStream
     * @see javax.tv.service.ReadPermission
     */
    public SIRequest retrieveTransportStream(Locator locator, SIRequestor
        requestor) throws InvalidLocatorException, SecurityException;

    /** 
     * Retrieves an array of the <code>TransportStream</code> objects in
     * this <code>TransportStreamCollection</code>.  The array will only
     * contain <code>TransportStream</code> instances <code>ts</code>
     * for which the caller has
     * <code>javax.tv.service.ReadPermission(ts.getLocator())</code>. If
     * no <code>TransportStream</code> instances meet this criteria,
     * this method will result in an <code>SIRequestFailureType</code>
     * of <code>DATA_UNAVAILABLE</code>.<p>
     * 
     * This method delivers its results asynchronously.
     *
     * @param requestor The <code>SIRequestor</code> to be notified
     * when this retrieval operation completes.
     * 
     * @return An <code>SIRequest</code> object identifying this
     * asynchronous retrieval request.
     *
     * @see TransportStream
     * @see javax.tv.service.ReadPermission
     */
    public SIRequest retrieveTransportStreams(SIRequestor requestor);

    /** 
     * Registers a <code>TransportStreamChangeListener</code> to be
     * notified of changes to a <code>TransportStream</code> that is
     * part of this <code>TransportStreamCollection</code>. Subsequent
     * notification is made via <code>TransportStreamChangeEvent</code>
     * with this <code>TransportStreamCollection</code> as the event
     * source and an <code>SIChangeType</code> of <code>ADD</code>,
     * <code>REMOVE</code> or <code>MODIFY</code>.  Only changes to
     * <code>TransportStream</code> instances <code>ts</code> for which
     * the caller has
     * <code>javax.tv.service.ReadPermission(ts.getLocator())</code>
     * will be reported.<p>
     * 
     * This method is only a request for notification.  No guarantee is
     * provided that the SI database will detect all, or even any, SI
     * changes or whether such changes will be detected in a timely
     * fashion.<p>
     * 
     * If the specified <code>TransportStreamChangeListener</code> is
     * already registered, no action is performed.
     *
     * @param listener A <code>TransportStreamChangeListener</code> to be
     * notified about changes related to <code>TransportStream</code>
     * carried on this <code>Transport</code>.
     *
     * @see TransportStreamChangeEvent 
     * @see javax.tv.service.ReadPermission
     */
    public void addTransportStreamChangeListener(TransportStreamChangeListener
        listener);

    /** 
     * Called to unregister an
     * <code>TransportStreamChangeListener</code>.  If the specified
     * <code>TransportStreamChangeListener</code> is not registered, no
     * action is performed.
     *
     * @param listener A previously registered listener.  
     */
    public void
        removeTransportStreamChangeListener(TransportStreamChangeListener
        listener);
}
