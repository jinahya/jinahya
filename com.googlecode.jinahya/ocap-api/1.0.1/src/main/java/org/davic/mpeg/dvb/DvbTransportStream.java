package org.davic.mpeg.dvb;

/**
 * This class represents an MPEG-2 Transport Stream as used
 * in DVB with its  associated Service Information (SI) as 
 * known to a decoder.
 *
 * @version created for DAVIC 1.3.1
 */

public class DvbTransportStream extends org.davic.mpeg.TransportStream {

	protected DvbTransportStream()
	{
	}

	/**
	  * @return the original_network_id of this transport stream.
	  */
	public int getOriginalNetworkId()
	{
	  return 0;
	}

	/**
	  * @return the network_id of the network from which this MPEG-2 TS is accessed.
	  */
	public int getNetworkId()
	{
	  return 0;
	}
}


