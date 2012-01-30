package org.dvb.si;

/**
  * This interface is the base interface for representing information about
  * transport streams. <p>
  * Transport stream retrieval methods in the SIDatabase class and the 
  * SINetwork interface use the NIT table and will return objects that implement
  * the SITransportStreamNIT interface. <p>
  * Transport stream retrieval methods in the SIBouquet interface use 
  * the BAT table and will return objects that implement the SITransportStreamBAT
  * interface.
  */

public interface SITransportStream extends SIInformation {
	
  /**
    * Gets a DvbLocator that identifies this transport stream.
    * @return The DvbLocator of this transport stream.
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
    * Retrieve information associated with services carried via the transport stream.
    * This method works in the same way for objects that implement
    * the SITransportStreamNIT and SITransportStreamBAT interfaces.
    * <p>
    * The SIIterator that is returned with the event when the request completes
    * successfully will contain objects that implement the SIService interface.
    *
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
    * @param someDescriptorTags A list of hints for descriptors (identified by their 
    *                           tags) the application is interested in. If the array 
    *                           contains -1 as its one and only element, the application
    *                           is interested in all descriptors. If someDescriptorTags 
    *                           is null, the application is not interested in descriptors.
    *                           All values 
    *                           that are out of the valid range for descriptor tags (i.e. 
    *                           0...255) are ignored, except for the special meaning of -1 as 
    *                           the only element in the array.
    * @return                   An SIRequest object 
    * 
    * @exception SIIllegalArgumentException thrown if the retrieveMode is invalid 
    *
    * @see SIRequest
    * @see SIRetrievalListener
    * @see SIService
    * @see DescriptorTag
    */
  public SIRequest retrieveSIServices(short retrieveMode, Object appData, 
				      SIRetrievalListener listener,
				      short[] someDescriptorTags) 
    throws SIIllegalArgumentException;
}




