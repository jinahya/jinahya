package org.dvb.si;

/**
  * This interface represents an elementary stream of a service.
  * <p>
  * For each running service there is a PMT describing 
  * the elementary streams of the service. 
  * An object that implements this interface represents one such elementary stream.
  * Each object that implements the PMTElementaryStream interface is identified by 
  * the combination of the identifiers original_network_id, transport_stream_id, 
  * service_id, component_tag (or elementary_PID).
  *
  * @see PMTService
  * @see PMTStreamType
  */

public interface PMTElementaryStream extends SIInformation {

 /**
   * Gets a DvbLocator that identifies this elementary stream
   * @return The DvbLocator of this elementary stream
   */
  public org.davic.net.dvb.DvbLocator getDvbLocator();
  
  /**
    * Get the original network identification identifier.
    * @return The original network identification.
    */
  public int getOriginalNetworkID();

  /**
    * Get the transport stream identification identifier.
    * @return The transport stream identification.
    */
  public int getTransportStreamID();

  /**
    * Get the service identification identifier.
    * @return The service identification.
    */
  public int getServiceID();

  /**
    * Get the component tag identifier.
    * @return The component tag. If the elementary stream does not have an 
    *         associated component tag, this method returns -2.
    */
  public int getComponentTag();

  /**
    * Get the stream type of this elementary stream. 
    * The value returned shall be the actual value from the descriptor loop and is not limited
    * to the set of values defined in <code>PMTStreamType</code>.
    * @return The stream type (some of the possible values are defined in the PMTStreamType interface).
    * @see PMTStreamType
    */
  public byte getStreamType();

  /**
    * Get the elementary PID.
    * @return The PID the data of elementary stream is sent on in the transport stream.
    */
  
  public short getElementaryPID();
}


