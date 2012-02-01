package org.dvb.internet;

import java.net.URL;
import javax.tv.service.*;
import javax.tv.service.navigation.*;
import java.io.*;

/**
 * The base class for the interface to resident applications that 
 * are supported by the internet access profile.
 * 
 * <P>The lifecycle of an application which implements this interface or its subclasses
 * is for a broadcast service. The application is started by selecting the appropriate 
 * service (using the <code>Locator</code> object returned by calls to <code>getLocator()
 * </code>). If this service is selected in the service context which contains the 
 * executing application, any currently presented content will be stopped and the 
 * application will be destroyed before the client is launched. Calling <code>destroy()</code>
 * or <code>stop()</code> on the service context in which the client is running will cause 
 * the client to be terminated.
 * 
 * <P>Methods in this API will not affect the lifecycle of the calling application.
 */
public interface InternetClientService extends javax.tv.service.Service {
	
	/**
	 * Returns true if the application can run without having to stop the downloaded MHP 
	 * application.
	 * 
	 * @return true if the application can be run without stopping the calling 
	 * application, or false otherwise.
	 */
	public boolean canRunApplication();
		
	/**
	 *	This method will always fail when called for an <code>InternetClient</code>. 
	 *	The requestor will always be notified of a failure of type DATA_UNAVAILABLE.
	 *
	 *	@param requestor - The <code>SIRequestor</code> to be notified when this retrieval operation completes.
	 *	@return An <code>SIRequest</code> object identifying the request
	 */
	public SIRequest retrieveDetails(SIRequestor requestor);
	/**
	 *	Returns a short service name or an acronym. In the case of subclasses of <code>InternetClient</code>,
	 *      the returned value is implementation dependent
	 *
	 *	@return A string representing this service's short name. 
	 */
	public java.lang.String getName();
	/**
	 *	This method indicates whether the service represented by this Service
	 *	is available on multiple transports. This method has no effect in
	 *      the case of an <code>InternetClient</code>
	 * 
	 *	@return FALSE always for <code>InternetClient</code> instances
	 */
	public boolean hasMultipleInstances();
	/**
	 *	Returns the type of this service. In the case of internet clients, one 
	 *  of the service types defined in the <code>InternetServiceType</code> class shall be 
	 * returned.
	 * 
	 *	@return The service type of this Service. 
	 */
	public ServiceType getServiceType();

	/**
	 * Returns all <code>InternetClientService</code>s supported by the same
	 * application as this one. This <code>InternetClientService</code> is included in the array.
	 *
	 * @return an array of <code>InternetClientService<code>s
	 */
	public InternetClientService[] getSupportedClientServices();
}

