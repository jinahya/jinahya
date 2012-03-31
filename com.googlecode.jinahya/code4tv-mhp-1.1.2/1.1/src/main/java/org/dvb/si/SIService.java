
package org.dvb.si;

/**
  * This interface represents a particular service carried by a transport stream. 
  * Information that can be obtained through the methods of this interface
  * is retrieved from the SDT table.
  * <p>
  * Each object that implements the SIService interface
  * is identified by the combination of the following identifiers: 
  * original_network_id, transport_stream_id, service_id.
  */

public interface SIService extends SIInformation, TextualServiceIdentifierQuery
{
	
  /**
    * Gets a DvbLocator that identifies this service.
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
   * Get the service type.
   * The service type is extracted from the service_descriptor.
   * 
   * @return The service type. (Some of the possible values are defined
   *     in the SIServiceType interface.)
   * 
   * @see SIServiceType
   */
  public short getSIServiceType();

  /** 
    * This method returns the name of the service represented by this service. 
    * If the language returned by <code>javax.tv.service.SIManager.getPreferredLanguage</code>
    * is one of those in the multilingual_service_name_descriptor, return the name in that 
    * language, otherwise return an implementation dependent selection between the names in 
    * the multilingual_service_name_descriptor and the name in the service_descriptor.
    * If this descriptor is not present "" is returned. All control characters as 
    * defined in ETR 211 are ignored. For each character the DVB-SI 8 bit character 
    * code is mapped to the appropriate Unicode representation.
    * @return The name of this service.
    */
  public String getName();
  
  /** 
    * This method returns the short name (ETR 211) of this service
    * without emphasis marks.
    * If the language returned by <code>javax.tv.service.SIManager.getPreferredLanguage</code>
    * is one of those in the multilingual_service_name_descriptor, return the name in that 
    * language, otherwise return an implementation dependent selection between the names in 
    * the multilingual_service_name_descriptor and the name in the service_descriptor.
      * If the descriptor is not present, "" is returned. If the string can be found but does 
      * not contain control codes for abbreviating it, the full string shall be returned.
    * For each character the DVB-SI 8 bit character code is mapped to the appropriate 
    * Unicode representation.
    * @return The short name of this service.
    */
  public String getShortServiceName();
  
  /** 
    * This method returns the service provider name of this service
    * If the language returned by <code>javax.tv.service.SIManager.getPreferredLanguage</code>
    * is one of those in the multilingual_service_name_descriptor, return the name in that 
    * language, otherwise return an implementation dependent selection between the names in 
    * the multilingual_service_name_descriptor and the name in the service_descriptor.
    * If this descriptor is not present "" is returned.
    * All control characters as defined in ETR 211 are ignored. For each character 
    * the DVB-SI 8 bit character code is mapped to the appropriate Unicode representation.
    * @return The service provider name of this service.
    */
  public String getProviderName();
  
  /** 
    * This method returns the short name (ETR 211) of the service provider of  
    * this service without emphasis marks. 
    * If the language returned by <code>javax.tv.service.SIManager.getPreferredLanguage</code>
    * is one of those in the multilingual_service_name_descriptor, return the name in that 
    * language, otherwise return an implementation dependent selection between the names in 
    * the multilingual_service_name_descriptor and the name in the service_descriptor.
    * If the descriptor is not present, "" is returned. If the string can be found but does 
    * not contain control codes for abbreviating it, the full string shall be returned.
    * For each character the DVB-SI 8 bit character code is mapped to the appropriate 
    * Unicode representation.
    * @return The short service provider name of this service.
    */
  public String getShortProviderName();

  /**
    * Get the EIT_schedule_flag value, true indicates this services has scheduled 
    * event information.
    * @return The EIT_schedule_flag value.
    */
  public boolean getEITScheduleFlag();

  /**
    * Get the EIT_present_following_flag value, true indicates this 
    * service has present and/or following event information.
    * @return The EIT_present_following_flag value.
    */
  public boolean getEITPresentFollowingFlag();

  /**
    * Retrieve the running status of this service.
    * @return The running status (the possible values are defined in the 
    *         SIRunningStatus interface)
    * @see SIRunningStatus
    */
  public byte getRunningStatus();

  /**
    * Retrieve the free_CA_mode value of this service, false indicates none of the components 
    * of this service are scrambled.
    * @return The free_CA_mode value of this service.
    */
  public boolean getFreeCAMode();

  /**
    * Retrieve information associated with the present event from the EIT-present/following.
    * <p>
    * The SIIterator that is returned with the event when the request completes
    * successfully will contain an object that implements the SIEvent interface.
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
    * @see SIEvent
    * @see DescriptorTag
    */
  public SIRequest retrievePresentSIEvent(short retrieveMode, Object appData, 
					  SIRetrievalListener listener,
					  short[]someDescriptorTags) 
    throws SIIllegalArgumentException;
  
  /**
    * Retrieve information associated with the following event from the EIT-present/following. 
    * <p>
    * The SIIterator that is returned with the event when the request completes
    * successfully will contain an object that implements the SIEvent interface.
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
    * @see SIEvent
    * @see DescriptorTag
    */
  public SIRequest retrieveFollowingSIEvent(short retrieveMode, Object appData, 
					    SIRetrievalListener listener,
					    short[]someDescriptorTags) 
    throws SIIllegalArgumentException;
  
  /** 
    * Retrieve information associated with the scheduled events within the service 
    * for a requested period from the EIT-schedule. The events are presented in the 
    * order they are present in the EIT-schedule. A scheduled event is retrieved by 
    * this method if the time interval from the start time of the event (inclusive) 
    * (as returned by SIEvent.getStartTime) to the end time of the event (exclusive) 
    * (as defined by the sum of SIEvent.getStartTime and SIEvent.getDuration) 
    * intersects the time interval from startTime (inclusive) to endTime (exclusive) 
    * specified by the input parameters to this method.
    * <p>
    * The SIIterator that is returned with the event when the request completes
    * successfully will contain one or more objects that implement the SIEvent
    * interface.
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
    * @param someDescriptorTags A list of hints for descriptors (identified by their tags) 
    *                           the application is interested in. If the array contains -1 
    *                           as its one and only element, the application is interested 
    *                           in all descriptors. If someDescriptorTags is null, the 
    *                           application is not interested in descriptors.
    *                           All values 
    *                           that are out of the valid range for descriptor tags (i.e. 
    *                           0...255) are ignored, except for the special meaning of -1 as 
    *                           the only element in the array.
    * @param startTime          The beginning of the required period in UTC time.
    * @param endTime            The end of the required period in UTC time.
    * @return                   An SIRequest object 
    * 
    * @exception SIIllegalArgumentException thrown if the retrieveMode is invalid 
    * @exception SIInvalidPeriodException   When no valid period is indicated.
    * @see SIRequest
    * @see SIRetrievalListener
    * @see SIEvent
    * @see DescriptorTag
    */
  public SIRequest retrieveScheduledSIEvents(short retrieveMode, Object appData, 
					     SIRetrievalListener listener,
					     short[] someDescriptorTags,
					     java.util.Date startTime,
					     java.util.Date endTime) 
    throws SIIllegalArgumentException, SIInvalidPeriodException;

  /**
    * Retrieve the PMTService information associated with this service.
    * <p>
    * The SIIterator that is returned with the event when the request completes
    * successfully will contain an object that implements the PMTService 
    * interface.
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
    * @see PMTService
    * @see DescriptorTag
    */
  public SIRequest retrievePMTService(short retrieveMode, Object appData, 
				      SIRetrievalListener listener,
				      short[] someDescriptorTags) 
    throws SIIllegalArgumentException;

}

