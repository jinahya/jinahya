package org.dvb.internet;

import javax.tv.service.ServiceType;
								   
/**
 * Class representing the additional service types available in the Internet Access profile.
 * When this service type is used in a <code>java.tv.service.navigation.ServiceTypeFilter</code>
 * for filtering available services on their type, all internet client services shall be 
 * returned. Applications wishing to obtain a specific sub-type of internet client
 * service should use <code>InternetServiceFilter</code>.
 * @see InternetServiceFilter
 */
public class InternetServiceType extends ServiceType
{
	
	/**
	 *	WWW service type
	 */
	public static final ServiceType INTERNET_CLIENT = new InternetServiceType("InternetClient");

	/**
 	 * This protected constructor is provided for implementation use and
	 * to enable future evolution of this or other specifications. It shall
	 * not be called by inter-operable applications.
	 *
	 * @param name the name of the service type
	 */

	protected InternetServiceType(java.lang.String name)
	{
		super(name);
	}

}

