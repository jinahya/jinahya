package org.dvb.locator;

import org.davic.net.InvalidLocatorException;

/**
 * Used to reference a service that does not carry any DVB-SI and hence
 * does not appear in the service list of a receiver.
 * The external form of locators of this class is implementation
 * specific.
 * @since MHP 1.1.2
 */
public class FrequencyLocator extends org.davic.net.Locator {
    /**
     * Constructor for referencing a service which does not carry any DVB-SI.
     * @param delivery_system_descriptor one of the delivery system descriptors defined in clause 6.2.13 ("Delivery system descriptors") of the DVB-SI specification.
     * @param program_number the MPEG program_number of the service within the transport stream
     * @throws InvalidLocatorException if the delivery_system_descriptor parameter is incorrect - the descriptor tag does not define one of the permitted descriptors, the length of the array is not correct for the descriptor tag, any values within the descriptor are outside any range defined for them.
     */
    public FrequencyLocator( byte[] delivery_system_descriptor, int program_number) 
	throws InvalidLocatorException {
	super("a");
	}
    /**
      * Return the delivery system descriptor as passed into the constructor
      * @return a byte array containing a DVB-SI delivery system descriptor
      */
    public byte[] getDeliverySystemDescriptor() { return null;}

     /**
      * Return the MPEG program number as passed in to the constructor
      * @return an MPEG program number
      */
     public int getProgramNumber() { return 0;}
}

