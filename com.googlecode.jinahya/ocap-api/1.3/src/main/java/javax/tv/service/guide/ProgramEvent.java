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

import javax.tv.service.SIElement;
import javax.tv.service.SIRequest;
import javax.tv.service.SIRequestor;
import javax.tv.service.Service;

/** 
 * <code>ProgramEvent</code> represents collection of elementary
 * streams with a common time base, an associated start time, and an
 * associated end time. An event is equivalent to the common industry
 * usage of "TV program." <p>
 *
 *
 * The Event Information Table (EIT) contains information (titles, start
 * times, etc.) for events on defined services. An event is, in most cases,
 * a typical TV program, however its definition may be extended to include
 * particular data broadcasting sessions and other information segments.<p>
 * 
 * A <code>ProgramEvent</code> object may optionally implement the
 * <code>CAIdentification</code> interface. Note that all time values
 * are in UTC time. <P>
 *
 * @see java.util.Date java.util.Date
 *
 * @see javax.tv.service.navigation.CAIdentification
 *
 * @see <a href="../../../../overview-summary.html#guidelines-opinterfaces">Optionally implemented interfaces</a> 
 */
public interface ProgramEvent extends SIElement
{

    /** 
     * Returns the start time of this program event. The start time is in UTC
     * time.
     *
     * @return This program's start time (UTC).
     */
    public java.util.Date getStartTime();

    /** 
     * Returns the end time of this program event. The end time is in UTC time.
     *
     * @return This program's end time (UTC).
     */
    public java.util.Date getEndTime();

    /** 
     * Returns the duration of this program event in seconds.
     *
     * @return This program's duration in seconds.
     */
    public long getDuration();

    /** 
     * Returns the program event title. This information may be obtained in
     * the ATSC EIT table or the DVB Short Event Descriptor.
     *
     * @return A string representing this program's title, or an empty
     * string if the title is unavailable.
     */
    public String getName();

    /** 
     * Retrieves a textual description of the event. This method
     * delivers its results asynchronously.
     *
     * @param requestor The <code>SIRequestor</code> to be notified
     * when this retrieval operation completes.
     * 
     * @return An <code>SIRequest</code> object identifying this
     * asynchronous retrieval request.
     *
     * @see ProgramEventDescription 
     */
    public SIRequest retrieveDescription(javax.tv.service.SIRequestor
        requestor);

    /** 
     * Reports content advisory information associated with this program for
     * the local rating region.
     *
     * @return A <code>ContentRatingAdvisory</code> object describing the
     * rating of this <code>ProgramEvent</code> or <code>null</code> if
     * no rating information is available.
     * 
     */
    public ContentRatingAdvisory getRating();

    /** 
     * Reports the <code>Service</code> this program event is associated with.
     *
     * @return The <code>Service</code> this program event is delivered on.
     */
    public Service getService();

    /** 
     * Retrieves an array of service components which are part of this
     * <code>ProgramEvent</code>.  Service component information may not
     * always be available. If the <code>ProgramEvent</code> is current,
     * this method will provide only service components associated with
     * the <code>Service</code> to which the <code>ProgramEvent</code>
     * belongs.  If the <code>ProgramEvent</code> is not current, no
     * guarantee is provided that all or even any of its service
     * components will be available.<p>
     *
     * This method delivers its results asynchronously.  The retrieved
     * array will only contain <code>ServiceComponent</code> instances
     * <code>c</code> for which the caller has
     * <code>javax.tv.service.ReadPermission(c.getLocator())</code>.  If
     * no <code>ServiceComponent</code> instances meet this criteria,
     * this method will result in an <code>SIRequestFailureType</code> of
     * <code>DATA_UNAVAILABLE</code>.
     *
     * @param requestor The <code>SIRequestor</code> to be notified
     * when this retrieval operation completes.
     * 
     * @return An <code>SIRequest</code> object identifying this
     * asynchronous retrieval request.
     * 
     * @see javax.tv.service.navigation.ServiceComponent
     * @see javax.tv.service.ReadPermission
     */
    public SIRequest retrieveComponents(javax.tv.service.SIRequestor requestor);
}
