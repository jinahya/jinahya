package org.davic.mpeg.dvb;

/**
 * This class represents one elementary stream within a transport stream
 * as used in DVB.
 * 
 * @version created for DAVIC 1.3.1
 */

public class DvbElementaryStream extends org.davic.mpeg.ElementaryStream
{
	protected DvbElementaryStream()
	{
	}

	/**
	  * @return the DVB component tag of this elementary stream, or null 
	  * if none is present.
	  */

	public Integer getComponentTag()
	{
	  return null;
	}
}

