package org.davic.mpeg;

/**
 * This class represents an MPEG-2 Transport Stream with its 
 * associated Service Information (SI) as known to a decoder.
 * <p>
 * A TransportStream object models a specific transport stream that can be 
 * accessed through a specific network interface. This implies that a Transport
 * Stream object has implicitly a connection to a specific network interface. 
 * Thus, if two or more network interfaces can deliver the same transport 
 * stream, this will be modeled by two or more separate TransportStream objects. 
 * A network interface does not need to be tuned to a transport stream if an 
 * application wants to use the corresponding TransportStream object.
 * <p>
 * If the corresponding network interface is currently tuned to the 
 * TransportStream, then an application can query the TransportStream for the 
 * services it contains, and the services for which elementary streams it 
 * contains. If the corresponding network interface is not currently tuned to 
 * the TransportStream, then the application can query the TransportStream for
 * the services it contains. If the STB has cached the required information 
 * it can return it, otherwise it should return null. If an application queries
 * a Service object for elementary stream information and the corresponding 
 * TransportStream is not currently tuned to, then the Service object should 
 * return null.
 * <p>
 * If an application has two references to a TransportStream object and those 
 * TransportStream objects model the same transport stream coming from the same
 * network interface, then the equals() method (inherited from java.lang.Object) 
 * return true when comparing both TransportStream objects. The references 
 * themselves are not necessarily the same, although they may be.
 * <p>
 * Note: If an application wants to know to which network interface a 
 * TransportStream object is connected to, it should use the Tuning API if it 
 * is available.
 */

public abstract class TransportStream {

	protected TransportStream()
	{
	}

	/**
	  * @return the transport_stream_id of this transport stream.
	  */
	public int getTransportStreamId()
	{
	  return 0;
	}

	/**
	  * @param serviceId the id of the requested service within this transport stream
	  * @return a reference to the service object that represents 
	  * the service from which this MPEG-2 TS is accessed. 
	  * If the required information is not available or the indicated 
	  * service does not exist, null is returned.
	  *
	  */
	public Service retrieveService(int serviceId)
	{
	  return null;
	}

	/**
	  * @return the array of all service objects belonging to this transport stream.  
	  * When the required information is not available null is returned.
	  */
	public Service[] retrieveServices()
	{
	  return new Service[1];
	}

	/*
	  * @return the time the information contained in the transport
	  * stream object was last updated from a live transport stream. 
	  * If the transport stream is currently live, it returns null.
	  */
	/*public java.util.Date getUpdateTime()
	{
	  return new java.util.Date();
	}*/
}










