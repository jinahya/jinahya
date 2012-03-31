package org.dvb.internet;

import javax.tv.service.ServiceType;
import javax.tv.service.Service;
import javax.tv.service.navigation.ServiceFilter;
								   
/**
 * <code>InternetServiceFilter</code> represents a service type for a
 * particular kind of internet client. A <code>ServiceList</code> resulting 
 * from this filter will include only services providing access to the
 * specified type of internet client.
 */
public final class InternetServiceFilter extends ServiceFilter
{
	/**
	 * Constant identifying an email client service
	 */
	public static final int EMAIL_CLIENT = 1;
	/**
	 * Constant identifying a WWW client service
	 */
	public static final int WWW_CLIENT = 2;
	/**
	 * Constant identifying a usenet news client service
	 */
	public static final int NEWS_CLIENT = 3;

	/**
 	 * Constructs the filter based on a particular type of
 	 * internet client service. The types of service required
	 * are those defined by the constants in this class. Support
	 * for other values is platform dependent. Platforms not
	 * supporting services of the type specified shall return an
	 * empty <code>ServiceList</code> when instances of this class constructed
	 * using that type are used.
	 *
	 * @param service_type the type of service required
	 */

	public InternetServiceFilter(int service_type)
	{
		super();
	}
	/**
	 * Tests if a particular service represents an internet client of
	 * the type specified in the constructor of this instance.
	 *
	 * @param service A Service to be evaluated against the filtering algorithm.
	 * @return true if service satisfies the filtering algorithm; false otherwise.
	 */
	 public boolean accept(Service service) 
	{
		return false;
	}
}

