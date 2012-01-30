package org.dvb.spi.selection;

import org.dvb.spi.util.MultilingualString;
//import org.dvb.spi.si.simple.ServiceComponentDescription;

/**
 * Represents the description of a service.
 * @since MHP 1.1.3
 **/

public interface ServiceDescription {
	/**
	 * Return the long name of this service.
	 * @return the name
	 */
	public MultilingualString getLongName(String perferredLanguage);

	/**
	 * Return the type of this service. The service type returned
	 * shall be from a class loaded by the system classloader.
	 *
	 * @return the service type of this service
	 * @throws IllegalArgumentException if the returned ServiceType
	 * is not from a class loaded by the system classloader.
	 */
	public javax.tv.service.ServiceType getServiceType();

	/**
	 * Return the type of the delivery system.
	 * @return the type of the delivery system by which this service
	 * is delivered
	 * @throws IllegalArgumentException if the returned ServiceType
	 * is not from a class loaded by the system classloader.
	 */
	public javax.tv.service.navigation.DeliverySystemType getDeliverySystemType();

//	public ServiceComponentDescription[] getServiceComponents();
}


