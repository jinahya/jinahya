package org.dvb.si;

/**
  * This interface represents a particular service carried by a transport stream.
  * The information is retrieved from the PMT table.
  * <p>
  * Each object that implements the PMTService interface is
  * identified by the combination of the following 
  * identifiers: original_network_id, transport_stream_id, service_id.
  */

public interface PMTService extends SIInformation {

 /**
    * Gets a DvbLocator that identifies this service
    * @return The DvbLocator of this service
    */
  public org.davic.net.dvb.DvbLocator getDvbLocator();

  /**
    * Get the original network identification.
    * @return The original network identification identifier.
    */
  public int getOriginalNetworkID();

  /**
    * Get the transport stream identification.
    * @return The transport stream identification identifier.
    */
  public int getTransportStreamID();

  /**
    * Get the service identification.
    * @return The service identification identifier.
    */
  public int getServiceID();
  
  /**
   * Get the PCR pid.
   * @return The PCR pid.
   */
  public int getPcrPid();

  /**
    * Retrieve information associated with the elementary streams which 
    * compose this service from the Program Map Table (PMT).
    * <p>
    * The SIIterator that is returned with the event when the request completes
    * successfully will contain one or more objects that implement the 
    * PMTElementaryStream interface.
    * If no matching object was found, the appropriate one of the following
    * events is sent: SINotInCacheEvent SIObjectNotInTableEvent
    * or SITableNotFoundEvent.
    * This method will retrieve PMTElementaryStreams from the same sub-table version as 
    * this PMTService instance. If this version of the sub-table is no longer available,
    * an SITableUpdatedEvent is returned.
    * @param retrieveMode       Mode of retrieval indicating whether the data should
    *                           be retrieved only from the cache (FROM_CACHE_ONLY), 
    *                           from the cache if available and if not from the stream
    *                           (FROM_CACHE_OR_STREAM), or always from the stream 
    *                           (FROM_STREAM_ONLY).                         
    * @param appData            An object supplied by the application. This object will
    *                           be delivered to the listener when the request completes.
    *                           The application can use this objects for internal
    *                           communication purposes. If the application does not 
    *                           need any application data, the parameter can be null.
    * @param listener           SIRetrievalListener that will receive the event informing
    *                           about the completion of the request.
    * @param somePMTDescriptorTags A list of hints for descriptors (identified by their 
    *                           tags) the application is interested in. If the array 
    *                           contains -1 as its one and only element, the application 
    *                           is interested in all descriptors. If somePMTDescriptorTags 
    *                           is null, the application is not interested in descriptors.
    *                           All non applicable tag values are ignored.
    * @return                   An SIRequest object 
    * 
    * @exception SIIllegalArgumentException thrown if the retrieveMode is invalid  
    * 
    * @see SIRequest
    * @see SIRetrievalListener
    * @see PMTElementaryStream
    */
  public SIRequest retrievePMTElementaryStreams(short retrieveMode, Object appData, 
						SIRetrievalListener listener,
						short[] somePMTDescriptorTags)
		throws SIIllegalArgumentException;
}

