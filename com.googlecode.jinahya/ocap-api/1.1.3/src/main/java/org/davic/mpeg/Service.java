package org.davic.mpeg;

/**
  * This class is used to represent a Service within an MPEG Transport Stream.
  *
  */

public class Service
{
	protected Service()
	{
	}

	/**
	 * @return a reference to the TransportStream object to which this Service belongs.
	 */
	public TransportStream getTransportStream()
	{
	  return null;
	}

	/**
	 * @return the service_id (or equivalently the program_number) of this service.
	 */
	public int getServiceId()
	{
	  return 0;
	}

	/**
	  * @param pid the value of MPEG-2 Transport Stream packets 
	  * that carry the elementary stream.
	  *
	  * @return a reference to the ElementaryStream object that 
	  * represents the Elementary Stream carried by packets with 
	  * the specified PID. Null is returned if the specified PID 
	  * is not present within this service or if  no Elementary 
	  * Stream is carried by the specified PID or if the required 
	  * information is not available.
	  *
	  *
	  */
	public ElementaryStream retrieveElementaryStream(int pid)
	{
	  return null;
	}

	/**
	  * @return the array of all ElementaryStream objects present within this service.
	  * When the required information is not available null is returned.
	  */

	public ElementaryStream[] retrieveElementaryStreams()
	{
	  return null;
	}
}


