package org.ocap.hn.content.navigation;

import org.ocap.hn.Device;
import org.ocap.hn.content.ContentEntry;

/**
 * This class represents a filtering criteria based on a particular device. Applications
 * may use this filter to create a ContentList with content entries from a particular device.
 * @author Labeeb Ismail, Liberate
 * @author Dr. Immo Benjes <immo.benjes@philips.com>, Philips Digital Systems Labs, Redhill, UK
 */

public class DeviceFilter extends ContentDatabaseFilter {

	/**
	 * Creates a new DeviceFilter. Only ContentItems which are hosted by the specified Device
	 * will pass this filter.  
	 * @param device the device to filter on
	 */
	public DeviceFilter(Device device) {}

	/**
	 * Inherited from ContentDatabaseFilter. This function SHALL return true if the
	 * entry passed in is from the device specified in the constructor of this class.
	 * 
	 * @param entry The entry to test for acceptance
	 * 
	 * @return <code>true</code> if the entry passed in is from the associated device,
	 *  <code>false</code> otherwise.
	 */
	public boolean accept(ContentEntry entry) {return true;}
}

