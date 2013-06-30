package org.ocap.application;

import org.dvb.application.*;
import java.io.*;
import javax.tv.service.selection.ServiceContext;
import java.util.Properties;
import java.util.Date;

/**
 * <P>
 * This class represents the application manager functionality used by 
 * the Monitor Application. It provides a means of acquiring application 
 * signaling and registering a new unbound application for applications 
 * that have MonitorAppPermission. 
 * </P><P>
 * An application which has MonitorAppPermission 
 * may have a subclass of the AppsDatabaseFilter class, 
 * a class implementing the AppSignalHandler interface or 
 * a class implementing SecurityPolicyHandler interface 
 * and may set an instance of them in the AppManagerProxy. 
 * </P><P>
 * See the section 10 Application Model and 11 Application Signaling 
 * in this specification for details. 
 * </P>
 */
public class AppManagerProxy
{
    /**
     * This is a constructor of this class. 
     * An application must use the 
     * {@link AppManagerProxy#getInstance} 
     * method to create an instance. 
     */
    protected AppManagerProxy() {
    }


    /**
     * This method returns the sole instance of the AppManagerProxy class. 
     * The AppManagerProxy instance is either a singleton for each OCAP 
     * application, or a singleton for an entire OCAP implementation. 
     * 
     * @return	The AppManagerProxy instance. 
     */
    public static AppManagerProxy getInstance() {
        return null;
    }


    /**
     * This method sets an instance of a concrete class that extends 
     * AppsDatabaseFilter that decides whether the application is 
     * allowed to be launched or not for all applications to be launched. 
     * At most, only one instance of a concrete class that extends 
     * AppsDatabaseFilter can be set to the AppManagerProxy. 
     * Multiple calls of this method replace the previous instance 
     * by a new one. 
     * If no AppsDatabaseFilter has been set, then any application is 
     * allowed to be launched. 
     * By default, no AppsDatabaseFilter is set, i.e., all 
     * applications are allowed to be launched. 
     * Note that the specified AppsDatabaseFilter can't prevent 
     * registering applications to a service. 
     * 
     * @param  filter An instance of a concrete class of the 
     *         AppsDatabaseFilter that decides whether the 
     *         application is allowed to be launched or not. 
     *         If null is set, the AppsDatabaseFilter 
     *         will be removed. 
     *
     * @throws SecurityException if the caller does not have 
     *         MonitorAppPermission("handler.appFilter")
     */
    public void setAppFilter(AppsDatabaseFilter filter) {
    }


    /**
     * This method sets an instance of a class that implements the 
     * AppSignalHandler interface. 
     * At most, only one AppSignalHandler can be set. 
     * Multiple calls of this method replace the previous instance 
     * by a new one. 
     * If no AppSignalHandler has been set, then application information 
     * is updated immediately. 
     * By default, no AppSignalHandler is set, i.e., application 
     * information is updated immediately. 
     * The OCAP implementation SHALL call the accept() method of the 
     * application filter whenever it launches any type of application. 
     * After the monitor application has indicated that it has set its 
     * filters, the accept() method in the monitor application is called 
     * prior to launching any application. 
     * If the method returns "false" for the application to be launched, 
     * the application MUST NOT be launched, otherwise the implementation 
     * continues with the process of launching the application. 
     * As an optimization, the implementation SHOULD mark, in the applications 
     * database, any applications that have not been accepted through filter. 
     * This mark SHOULD remain until the current filters are replaced and 
     * SHOULD be used to prevent repeated requests being sent to the filtering 
     * application to validate the launching of any application that has 
     * previously been denied. 
     * The implementation MUST remove all filtering marks in the case that 
     * the current filters are replaced or removed. 
     * If the application is not marked, as previously filtered out, the 
     * implementation MUST call any registered AppDatabaseFilter.accept() 
     * between the time that the implementation receives a request to launch, 
     * and the actual launch of the application.
     * 
     * @param  handler An instance of a class implementing the 
     *         AppSignalHandler interface that decides 
     *         whether application information is updated 
     *         using the new version of the XAIT or not. 
     *         If null is set, the AppSignalHandler be removed. 
     *
     * @throws SecurityException if the caller does not have 
     *         MonitorAppPermission("registrar")
     */
    public void setAppSignalHandler(AppSignalHandler handler) {
    }


    /**
     * This method sets an instance of a class that implements the 
     * SecurityPolicyHandler interface. 
     * At most, only one SecurityPolicyHandler can be set. 
     * Multiple calls of this method replace the previous instance 
     * by a new one. 
     * If no SecurityPolicyHandler has been set, then the requested 
     * set of Permissions are granted to an application to be launched. 
     * By default, no SecurityPolicyHandler is set, i.e., the requested 
     * set of Permissions are granted to an application to be launched. 
     * 
     * @param  handler An instance of a class implementing 
     *         the SecurityPolicyHandler interface 
     *         that may modify a set of Permission granted to an application 
     *         to be launched. 
     *         If null is set, the SecurityPolicyHandler is removed. 
     *
     * @throws SecurityException if the caller does not have 
     *         MonitorAppPermission("security")
     */
    public void setSecurityPolicyHandler(SecurityPolicyHandler handler){
    }


    /**
     * <P>
     * This method registers  new unbound application entries. 
     * </P><P>
     * Generally, the ServiceList and, for currently selected abstract services, 
     * the AppsDatabase are updated when a new XAIT is received from the network. 
     * This method registers new unbound application entries 
     * without the network signaled XAIT. 
     * </P><P>
     * If there has already been an entry in the ServiceList or AppsDatabase 
     * registered via the registerUnboundApp(AppID) method with 
     * the same combination of an AppID and a service name, the existing 
     * entry is replaced by the one specified by this method. 
     * Errors in the xait are not indicated to the calling application and
     * incorrect xait information will be treated as described in DVB MHP
     * 1.0.2 Section 10.4.1 Data Errors.
     * </P><P>
     * Note that the application entry registered by this method is 
     * processed in the same manner as applications signaled by the 
     * XAIT. 
     * Note that the {@link AppSignalHandler#notifyXAITUpdate} 
     * method is not called. 
     * </P>
     *
     * @param  xait An instance of java.io.InputStream that provides 
     *         an XAIT formatted stream. If an XAIT consists of multiple 
     *         sections, an instance of java.io.InputStream SHALL provide
     *         simple concatenation of them.  All section header values
     *         of each section shall be valid.  Sections shall be concatenated
     *         in order of ascending section number.  Duplicate sections 
     *         should not be included.  If duplicates are included in the
     *         java.io.InputStream this method will discard all but the
     *         first occurrence.
     *
     * @throws IllegalArgumentException if the InputStream does not represent 
     *         a sequence of XAIT sections with valid section headers.
     *         Note that this exception is not thrown when descriptors in the 
     *         XAIT are invalid. The descriptors may be analyzed asynchronously. 
     *
     * @throws IOException if an I/O error occurs. 
     *
     * @throws SecurityException if the caller does not have 
     *         MonitorAppPermission("registrar")
     */
    public void registerUnboundApp(java.io.InputStream xait)
        throws IOException {
    }


    /**
     * <P>
     * This method unregisters an unbound application from 
     * the AppsDatabase. 
     * </P><P>
     * This method unregisters an existing unbound application entry 
     * from AppsDatabase without the XAIT signaling. The unbound 
     * application entry is specified by an appid and a service_id. 
     * The application must have been previously registered by a call 
     * to registerUnboundApp(InputStream) from the same application 
     * that is making the call to unregisterUnboundApp(AppID). 
     * </P><P>
     * If there is no specified entry in the service at the time 
     * this method is called, this method has no effect. 
     * If the application to be unregistered has been launched, 
     * it shall be killed. 
     * </P>
     *
     * @param serviceId The service identifier to which this 
     *         application is registered. 
     * 
     * @param appid An AppID instance identifies the application entry 
     *         to be unregistered from the service.
     *
     * @throws IllegalArgumentException if this method attempts 
     *         to modify an application signaled in the XAIT or an AIT
     *         or a host device manufacturer application. 
     *
     * @throws SecurityException if the caller does not have 
     *         MonitorAppPermission("registrar")
     *
     */
    public void unregisterUnboundApp(int serviceId, AppID appid){
    }
    
    /**
     * This method returns the set of application types supported by this 
     * OCAP implementation. The values returned shall be those used in 
     * the application_type field of the AIT and XAIT.
     * @return an array containing all the supported application types
     */
    public static int[] getSupportedApplicationTypes() {
        return null;
    }
    
    /**
     * This method sets the priority for the application.  This method can be
     * called at any time but has no affect upon resource contention 
     * resolutions that occurred before it was called.  The priority set SHALL
     * persist until a new version of the application is signaled, a reboot
     * occurs, or this method is called again.  If the application is running
     * the priority will be changed when the application is relaunched.
     *
     * @param priority New priority for the application with appId.
     * @param appId Application identifier of the application to have its
     *      priority changed.
     *
     * @throws SecurityException is thrown when the caller does not have
     *            MonitorAppPermission("servicemanager").
     * @throws IllegalStateException if the application, i.e. Xlet, is
     *         currently set at monitor application priority.
     */
    public void setApplicationPriority(int priority, AppID appId)
    {
    }

    /**
     * Registers addressing properties used for comparison when an 
     * addressing_descriptor from an AIT or XAIT is evaluated.  The 
     * implementation SHALL maintain a set of properties registered by
     * any application.  The implementation SHALL adhere to the
     * following rules in order when registering each property passed
     * in the properties parameter:
     * <li>When a property contains a value that is not an instance of
     * java.lang.String the property is ignored.</li>
     * <li>When a property key is 0 length the property is ignored.</li>
     * <li>When a property key is a duplicate of a Java system property the
     * property is ignored.</li>
     * <li>When a property key is a duplicate of a property previously
     * registered by this method it is ignored.</li>
     * <li>When a property key is a duplicate of an addressable attribute
     * retrieved from the security system the property is ignored.</li>
     * <li>When a property key is not registered and the value is not a
     * 0 length String the property is added.  If a property is not
     * registered and the property value is a 0 length String the 
     * property is ignored.</li>
     * 
     * @param properties The set of properties to be registered.
     * @param persist If true the properties parameters are stored in 
     *      persistent storage, otherwise they are not stored and 
     *      SHALL be removed immediately if previously stored.
     * @param expirationDate Date the implementation SHALL remove the
     *      properties from persistent storage.  Only applies if the
     *      persist parameter is set to true. If the date is in the
     *      past then no expiration date is set.
     * @throws SecurityException if the calling application is not
     *      granted MonitorAppPermission("properties").
     */
    public void registerAddressingProperties(Properties properties,
                                             boolean persist,
                                             Date expirationDate)
    {
    }

    /**
     * Gets the addressing properties previously registered by the
     * <code>registerAddressingProperties</code> method.  The set of properties
     * returned by this method may be out of date as soon as this method
     * returns.
     * 
     * @return The set of registered addressing properties.  If no addressing
     *      properties have been registered an empty Properties object is 
     *      returned.
     */
    public Properties getAddressingProperties()
    {
        return null;
    }

    /**
     * Removes addressing properties set by the
     * <code>registerAddressingProperties</code> method.  Each String in the
     * properties parameter SHALL be compared to registered property keys
     * and if a match is found the property SHALL be removed.  If the
     * properties parameter is null all registered properties SHALL be removed
     * from both volatile storage and non-volatile storage if persistently
     * stored.
     * 
     * @param properties The properties to remove.
     *
     * @throws SecurityException if the calling application is not granted
     *      MonitorAppPermission("properties").
     */
    public void removeAddressingProperties(String [] properties)
    {
    }

    /**
     * Gets the security system Host addressable attributes queried by the 
     * implementation.  The implementation SHALL format addressable attributes
     * sent by the security system into name/value pairs in the returned
     * <code>Properties</code>.  The set of properties returned by this
     * method may be out of date as soon as this method returns.
     * 
     * @return The set of addressable attributes set by the security system.
     */
    public Properties getSecurityAddressableAttributes()
    {
        return null;
    }
}
