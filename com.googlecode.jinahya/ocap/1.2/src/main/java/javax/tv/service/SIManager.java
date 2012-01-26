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



  


package javax.tv.service;

import javax.tv.locator.*;
import javax.tv.service.navigation.*;

import javax.tv.service.transport.Transport;

/** 
 * An <code>SIManager</code> represents a managing entity which has
 * knowledge of all the broadcast resources available to a receiver.  An
 * <code>SIManager</code> can be used to retrieve any
 * <code>SIElement</code> or create a collection of
 * <code>Service</code> objects according to filtering rules. <p>
 *
 * An <code>SIManager</code> may also be used to set parameters such
 * as the preferred language for multilingual text information.
 * Normally an application will create a single instance of
 * <code>SIManager</code> and use that instance to access all SI
 * information.  If an application creates more than one instance of
 * <code>SIManager</code> it may experience degraded caching
 * performance, particularly if the <code>SIManager</code> instances
 * use different preferred languages.
 *
 * @see Service
 * @see SIElement
 * 
 */
public abstract class SIManager
{

    /** 
     * Constructs an <code>SIManager</code> object.
     */
    protected SIManager() { }

    /** 
     * Creates a new instance of <code>SIManager</code>.
     *
     * @return A new instance of <code>SIManager</code>.
     */
    public static SIManager createInstance() {
        return null;
    }

    /** 
     * Overrides the system-level preferred language for objects
     * obtained through this <code>SIManager</code>.  The specified
     * language will be used by the textual information obtained from
     * the <code>SIElement</code> objects obtained through this
     * <code>SIManager</code>, if such information is available in the
     * specified language. If the specified language is not available
     * the system-level preferred language is used. If the system-level
     * preferred language is not available either, the first available
     * language will be used.<p>
     *
     * The preferred language is indicated using a language code.
     * This is typically a three-character language code as specified by
     * ISO 639.2/B, but the code may be system-dependent.
     *
     * @param language A string representing the desired language code.
     * If the input parameter is <code>null</code>, any language
     * preference previously set using this method is canceled.
     */
    public abstract void setPreferredLanguage(String language);

    /** 
     * Reports the preferred language for this <code>SIManager</code>.
     * The resulting string is a language code indicating
     * the language desired when retrieving multilingual text.  This
     * is typically a three-character code as specified by ISO 639.2/B.
     *
     * @return A string representing a language code defining the language
     * used to retrieve multilingual strings.  If no language preference
     * is in effect, <code>null</code> is returned.
     */
    public abstract String getPreferredLanguage();

    /** 
     * Provides a hint to the SI database that the application desires
     * SI information as complete as possible about the specified
     * <code>SIElement</code>. As a result, the SI database might tune
     * to the transport stream delivering the desired information and
     * begin caching it, depending on resource availability.
     *
     * <p> For example, if the given <code>Locator</code> references a
     * bouquet, the database might begin caching
     * <code>ServiceDetails</code> data for services that are part of
     * the bouquet. If the given <code>Locator</code>
     * references a service, the database might start caching
     * <code>ProgramEvent</code> data for the service.
     *
     * <p> Note that this method returns immediately and that there is
     * no indication of the completion of any resulting SI database
     * operations.  Since it is only a hint for cache optimization, no
     * specific behavior for this method is guaranteed.
     * 
     * @param locator A locator referencing the <code>SIElement</code>
     * for which complete information is desired.
     *
     * @param active A flag indicating whether this interest is active
     * or not. A value of <code>true</code> means that the application
     * is interested in the <code>SIElement</code>; <code>false</code>
     * means that the application wants to cancel a previously shown
     * interest for the <code>SIElement</code>.
     *
     * @throws InvalidLocatorException If <code>locator</code> does not
     * reference a valid <code>SIElement</code>.
     *
     * @throws SecurityException If the caller does not have
     * <code>javax.tv.service.ReadPermission(locator)</code>.
     *
     * @see javax.tv.service.ReadPermission 
     */
    public abstract void registerInterest(Locator locator, boolean active)
        throws InvalidLocatorException, SecurityException;

    /** 
     * Provides the names of available rating dimensions in the local
     * rating region.  A zero-length array is returned if no rating
     * dimensions are available.
     *
     * @return An array of strings representing the names of available
     * rating dimensions in this rating region.
     *
     * @see RatingDimension 
     */
    public abstract String[] getSupportedDimensions();

    /** 
     * Reports the <code>RatingDimension</code> corresponding to the
     * specified string name.
     *
     * @param name The name of the requested rating dimension.
     *
     * @return The requested <code>RatingDimension</code>.
     * 
     * @throws SIException If <code>name</code> is not a supported
     * rating dimension, as returned by <code>getSupportedDimensions()</code>.
     *
     * @see #getSupportedDimensions 
     */
    public abstract RatingDimension getRatingDimension(String name)
        throws SIException;

    /** 
     * Reports the various content delivery mechanisms currently
     * available on this platform.  The implementation must be capable
     * of supporting at least one <code>Transport</code> instance.
     *
     * @return An array of <code>Transport</code> objects representing
     * the content delivery mechanisms currently available on this
     * platform.
     */
    public abstract Transport[] getTransports();

    /** 
     * Retrieves the <code>SIElement</code> corresponding to the
     * specified <code>Locator</code>. If the locator identifies more
     * than one <code>SIElement</code>, all matching
     * <code>SIElements</code> are retrieved. <p>
     *
     * For example, multiple <code>SIElement</code> objects are
     * retrieved when the locator represents identical content delivered
     * over different media (terrestrial, satellite, cable, etc.) or a
     * specific program event made available at different times,
     * possibly on different services. <p>
     *
     * This call retrieves various types of <code>SIElement</code>
     * instances according to the locator specified.  For example, if
     * the locator is a transport-dependent locator to a service (and
     * therefore to a <code>ServiceDetails</code> -- see {@link
     * Service#getLocator} for more information), a
     * <code>ServiceDetails</code> object is retrieved; if the locator
     * represents a program event, a <code>ProgramEvent</code> object is
     * retrieved; if the locator represents a service component, a
     * <code>ServiceComponent</code> is retrieved.<p>
     *
     * <p>This method delivers its results asynchronously.
     *
     * @param locator A locator referencing one or more SIElements.
     *
     * @param requestor The <code>SIRequestor</code> to be notified
     * when this retrieval operation completes.
     * 
     * @return An <code>SIRequest</code> object identifying this
     * asynchronous retrieval request.
     *
     * @throws InvalidLocatorException If <code>locator</code> does not
     * reference a valid <code>SIElement</code>.
     *
     * @throws SecurityException if the caller does not have
     * <code>javax.tv.service.ReadPermission(locator)</code>.
     *
     * @see SIElement
     */
    public abstract SIRequest retrieveSIElement(Locator locator, SIRequestor
        requestor) throws InvalidLocatorException, SecurityException;

    /** 
     * Provides the <code>Service</code> identified by a given
     * <code>Locator</code>.  An implementation must be capable of
     * supporting at least one <code>Service</code> instance.
     * <p>
     * If the specified <code>locator</code> references a transport-dependent
     * <code>SIElement</code>, and the <code>locator</code> data contains
     * sufficient information to identify the <code>Service</code> which
     * contains the <code>SIElement</code>, then that <code>Service</code>
     * will be returned.
     *
     * @param locator A locator identifying a service.
     *
     * @return The <code>Service</code> object corresponding to the
     * specified locator.
     *
     * @throws InvalidLocatorException If <code>locator</code> does not
     * identify a valid <code>Service</code>.
     *
     * @throws SecurityException If the caller does not have
     * <code>javax.tv.service.ReadPermission(locator)</code>.
     *
     * @see javax.tv.service.ReadPermission 
     */
    public abstract Service getService(Locator locator)
        throws InvalidLocatorException, SecurityException;

    /** 
     * Retrieves the <code>ServiceDetails</code> object corresponding to
     * the given <code>Locator</code>.
     *
     * <p>Note that the locator may point to an SI element lower in the
     * hierarchy than a service (such as a program event). In such a
     * case, the <code>ServiceDetails</code> for the service that the
     * program event is part of will be returned. <p>
     *
     * If a transport-independent locator is provided, one or more
     * <code>ServiceDetails</code> objects may be returned.  However, it
     * is permissible in this case for this method to always retrieve
     * a single <code>ServiceDetails</code> object, as determined
     * by the implementation, user preferences, or availability.  To
     * obtain all of the corresponding <code>ServiceDetails</code>
     * objects, the application may transform the transport-independent
     * locator into multiple transport-dependent locators and retrieve
     * a <code>ServiceDetails</code> object for each.<p>
     *
     * This method delivers its results asynchronously.
     *
     * @param locator A locator referencing a Service
     *
     * @param requestor The <code>SIRequestor</code> to be notified
     * when this retrieval operation completes.
     * 
     * @return An <code>SIRequest</code> object identifying this
     * asynchronous retrieval request.
     *
     * @throws InvalidLocatorException If <code>locator</code> does not
     * reference a valid <code>Service</code>.
     *
     * @throws SecurityException If the caller does not have
     * <code>javax.tv.service.ReadPermission(locator)</code>.
     *
     * @see Locator
     * @see ServiceDetails
     * @see javax.tv.service.ReadPermission 
     */
    public abstract SIRequest retrieveServiceDetails(Locator locator,
        SIRequestor requestor)
        throws InvalidLocatorException, SecurityException;

    /** 
     * Retrieves the <code>ProgramEvent</code> corresponding to the
     * given <code>Locator</code>.  If a transport-independent locator
     * is provided (e.g., one referencing the same movie shown at
     * different times and/or on different services), this method may
     * retrieve multiple <code>ProgramEvent</code> objects.<p>
     *
     * This method delivers its results asynchronously.
     *
     * @param locator A locator referencing a ProgramEvent
     *
     * @param requestor The <code>SIRequestor</code> to be notified
     * when this retrieval operation completes.
     * 
     * @return An <code>SIRequest</code> object identifying this
     * asynchronous retrieval request.
     *
     * @throws InvalidLocatorException If <code>locator</code> does not
     * reference a valid <code>ProgramEvent</code>.
     *
     * @throws SecurityException If the caller does not have
     * <code>javax.tv.service.ReadPermission(locator)</code>.
     *
     * @see javax.tv.service.guide.ProgramEvent
     * @see javax.tv.service.ReadPermission 
     */
    public abstract SIRequest retrieveProgramEvent(Locator locator, SIRequestor
        requestor) throws InvalidLocatorException, SecurityException;

    /** 
     * Filters the available services using the
     * <code>ServiceFilter</code> provided to generate a
     * <code>ServiceList</code> containing the matching services.  If
     * the filter parameter is <code>null</code>, a list of all known
     * services is generated.  Only <code>Service</code> instances for
     * which the caller has <code>javax.tv.service.ReadPermission</code>
     * on the underlying locator will be present in the returned
     * list.<p>
     * 
     * Note that for each <code>Service</code> to be filtered, the
     * <code>accept()</code> method of the given
     * <code>ServiceFilter</code> will be invoked with the same
     * application thread that invokes this method.
     *
     * @param filter A <code>ServiceFilter</code> by which to generate
     * the requested service list, or <code>null</code>.
     *
     * @return A <code>ServiceList</code> generated according to the
     * specified filtering rules.
     *
     * @see ServiceFilter#accept 
     */
    public abstract ServiceList filterServices(ServiceFilter filter);
}
