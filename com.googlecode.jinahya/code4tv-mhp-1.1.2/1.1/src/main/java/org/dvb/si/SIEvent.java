package org.dvb.si;

/**
  * This interface represents a particular event within a service. 
  * <p>
  * Each object that implements the SIEvent interface is defined 
  * by the combination of the identifiers
  * original_network_id, transport_stream_id, service_id, event_id.
  * <p>Where methods return values from a short_event_descriptor, the following 
  * algorithm shall be used where more than one such descriptor is present.
  * If the language returned by <code>javax.tv.service.SIManager.getPreferredLanguage</code>
  * is one of those for which there is a short_event_descriptor then return the value from 
  * that descriptor. Otherwise return an implementation dependent selection between the 
  * values in the available short_event_descriptors.
  * @see SIService
  */

public interface SIEvent extends SIInformation {

 /**
    * Gets a DvbLocator that identifies this event.
    * @return The DvbLocator of this event
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
    * Get the event identification.
    * @return The event identification.
    */
  public int getEventID();
  
  /**
    * Get the start time of this event in UTC time.
    * @return The start time of this event.
    */
  public java.util.Date getStartTime();

  /**
    * Get the duration of this event.
    * @return The duration in seconds.
    */
  public long getDuration();
  
  /**
    * Get the running status of this event.
    * @return The running status (the possible values are defined in the 
    *         SIRunningStatus interface).
    * @see SIRunningStatus
    */
  public byte getRunningStatus();
  
  /**
    * Get the free_CA_mode value for this event, false indicates none of the 
    * component streams of this event are scrambled.
    * @return The free_CA_mode value.
    */
  public boolean getFreeCAMode();
  
  /**
    * This method returns the name of this event.
    * The name is extracted from a short_event_descriptor. When this information is 
    * not available "" is returned.
    * All control characters as defined in ETR 211 are ignored. For each character 
    * the DVB-SI 8 bit character code is mapped to the appropriate Unicode representation.
    * @return The event name of this event.
    */
  public java.lang.String getName();

  /**
    * This method returns the short event name (ETR 211) of this event
    * without emphasis marks. The name is extracted from 
    * a short_event_descriptor.  
      * If the descriptor is not present, "" is returned. If the string can be found but does 
      * not contain control codes for abbreviating it, the full string shall be returned.
    * For each character the DVB-SI 8 bit character code is mapped to the appropriate 
    * Unicode representation.
    * @return The short event name of this event.
    */
  public String getShortEventName();
  
  /**
    * This method returns the description of this event. 
    * The description is extracted from a short_event_descriptor. When this information 
    * is not available, "" is returned. For each character the DVB-SI 8 bit character code 
    * is mapped to the appropriate Unicode representation
    * @return The short description of this event.
    */
  public java.lang.String getShortDescription();

  /**
    * This method returns the level 1 content nibbles of this event. This information is 
    * extracted from the content_descriptor. If this descriptor is not present an empty 
    * array is returned (array with length 0).
    * The return value is an array, each array element describes one content nibble. 
    * In each nibble the data occupies the four least significant bits of the returned bytes
    * with the four most significant bits set to 0.
    * @return All level 1 content nibbles related to the event.
    */
  public byte[] getLevel1ContentNibbles();

  /**
    * This method returns the content nibbles related to the event. This information 
    * is extracted from the content_descriptor. If this descriptor is not present 
    * an empty array is returned (array with length 0). 
    * The return value is an array, each array element describes one content nibble. 
    * In each nibble the level 1 content nibbles occupy the four most significant 
    * bits of the returned bytes, level 2 content nibbles the four least significant bits.
    * @return The content nibbles related to the event; level 1 content nibbles occupy the 
    *         four most significant bits of the returned bytes, level 2 content nibbles 
    *         the four least significant bits.
    */
  public byte[] getContentNibbles();

  /**
    * This method retrieves the SIService object representing the service the event, 
    * represented by this SIEvent, is part of.
    * <p>
    * The SIIterator that is returned with the event when the request completes
    * successfully will contain an object that implements the SIService interface.
    * If no matching object was found, the appropriate one of the following
    * events is sent: SINotInCacheEvent SIObjectNotInTableEvent
    * or SITableNotFoundEvent.
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
    * @param someDescriptorTags A list of hints for descriptors (identified by their tags)
    *                           the application is interested in. If the array contains -1 
    *                           as its one and only element, the application is interested 
    *                           in all descriptors. If someDescriptorTags is null, the 
    *                           application is not interested in descriptors. 
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
  public SIRequest retrieveSIService(short retrieveMode, Object appData, 
				     SIRetrievalListener listener,
				     short[] someDescriptorTags) 
    throws SIIllegalArgumentException;
}



