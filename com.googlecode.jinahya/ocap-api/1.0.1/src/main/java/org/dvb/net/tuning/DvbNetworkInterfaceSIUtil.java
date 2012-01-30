package org.dvb.net.tuning;

/**
  * Each SI database is associated with a network interface
  * and vice versa. This class allows the application
  * to query this association.
  * @since MHP 1.0.1
  */

public class DvbNetworkInterfaceSIUtil {

	private DvbNetworkInterfaceSIUtil()
	{
	}
  /**
    * Gets the SI database for a particular network interface.
    *
    * @param ni the network interface for which the associated
    * SI database will be returned.
    *
    * @return the associated SI database
    */
   public static org.dvb.si.SIDatabase getSIDatabase(
	org.davic.net.tuning.NetworkInterface ni) 
	{
		return null;
	};

    /**
     * Gets the network interface for a particular SI database.
     * @param sd the SI database for which the associated
     * network interface will be returned.
     *
     * @return the associated network interface
     */
   public static org.davic.net.tuning.NetworkInterface
   getNetworkInterface(org.dvb.si.SIDatabase sd) {
	return null;
	};
}


