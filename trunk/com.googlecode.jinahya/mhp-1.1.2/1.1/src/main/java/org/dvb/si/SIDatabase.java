package org.dvb.si;

/**
  * This class represents the root of the SI information hierarchy. 
  * There is one SIDatabase per network interface. In a system with 
  * a single network interface there is only one SIDatabase object.
  *
  * <p>When adding a listener to monitor for changes in an SI table (or data 
  * carried in an SI table), an event shall not be generated for the current 
  * version of that table (or data) found in the network at the time the listener 
  * is added. Events shall only be generated for changes following the detection 
  * of that current version.
  */

public class SIDatabase{
  /**
    * This constructor is provided for the use of implementations and specifications which 
    * extend the present document. Applications shall not define sub-classes of this class.
    * Implementations are not required to behave correctly if any such application defined 
    * sub-classes are used.
    */
  protected SIDatabase() {
  }
  
  /**
    * Return an array of SIDatabase objects (one object per network interface).
    * In a system with one network interface, the length of this array will be one.
    * The network interface of each SIDatabase is used as data source for all new 
    * data accessed by this SIDatabase or SIInformation instances obtained from it.
    * <p>
    * This is the first method to be called to access the DVB-SI API. 
    * The returned SIDatabase objects
    * provide the access point to the DVB-SI information.
    * @return An array of SIDatabase objects, one per network interface.
    */
  public static SIDatabase[] getSIDatabase() {
    // actual code shall replace the following statement
    return (null);
  }

  /**
    * Retrieve information associated with bouquets.
    * A bouquet can be specified by its identification. When bouquetId is set to -1, 
    * all bouquets signalled in the BAT of the currently received
    * transport stream on that network interface are retrieved.
    * <p>
    * The SIIterator that is returned with the event when the request completes
    * successfully will contain one or more objects that implement the SIBouquet interface.
    *If no matching object was found, the appropriate one of the 
    * following events is sent: <code>SINotInCacheEvent</code>, 
    * <code>SIObjectNotInTableEvent</code> or <code>SITableNotFoundEvent</code>.
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
    * @param bouquetId          Identifier of the bouquet to be retrieved or -1 for all
    *			        bouquets signalled on the currently received transport stream.
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
    * @exception SIIllegalArgumentException thrown if the retrieveMode is invalid or 
    *                                       the numeric identifiers are out of range
    * 
    * @see SIRequest
    * @see SIRetrievalListener
    * @see SIBouquet
    * @see DescriptorTag
    */
  public SIRequest retrieveSIBouquets(short retrieveMode, Object appData, 
				      SIRetrievalListener listener,
				      int bouquetId, short[] someDescriptorTags) 
    throws SIIllegalArgumentException {
    // actual code shall replace the following statement
    return (null);
  }

  /**
    * Retrieve information associated with the actual network.
    * The actual network is the network carrying the transport stream currently 
    * selected by the network interface connected to this SIDatabase.
    * <p>
    * The SIIterator that is returned with the event when the request completes
    * successfully will contain an object that implements the SINetwork interface.
    * If no matching object was found, the appropriate one of the following
    * events is sent: SINotInCacheEvent 
    * or SITableNotFoundEvent
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
    * @param someDescriptorTags A list of hints for descriptors (identified by 
    *                           their tags) the application is interested in. 
    *                           If the array contains -1 as its one and only element, 
    *                           the application is interested in all descriptors. 
    *                           If someDescriptorTags is null, the application is 
    *                           not interested in descriptors. 
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
    * @see SINetwork
    * @see DescriptorTag
    */
  public SIRequest retrieveActualSINetwork(short retrieveMode, Object appData, 
					   SIRetrievalListener listener,
					   short[] someDescriptorTags) 
    throws SIIllegalArgumentException {
    // actual code shall replace the following statement
    return (null);
  }

  /**
    * Retrieve information associated with networks.
    * A network can be specified by its identification. When
    * networkId is set to -1, all networks signalled in NIT Actual and Other of the 
    * currently received TransportStream on that network interface shall be retrieved.
    * <p>
    * The SIIterator that is returned with the event when the request completes
    * successfully will contain one or more objects that implement the SINetwork interface.
    * If no matching object was found, the appropriate one of the 
    * following events is sent: <code>SINotInCacheEvent</code>, 
    * <code>SITableNotFoundEvent</code>.
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
    * @param networkId          Identification of the network to be retrieved or -1 for all
    *                           networks currently signalled.
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
    * @exception SIIllegalArgumentException thrown if the retrieveMode is invalid or 
    *                                       the numeric identifiers are out of range
    * 
    * @see SIRequest
    * @see SIRetrievalListener
    * @see SINetwork
    * @see DescriptorTag
    */
  public SIRequest retrieveSINetworks(short retrieveMode, Object appData, 
				      SIRetrievalListener listener, int networkId,
				      short[] someDescriptorTags) 
    throws SIIllegalArgumentException {
    // actual code shall replace the following statement
    return (null);
  }
  
  /**
    * Retrieve information associated with the actual transport stream.
    * The actual transport stream is the transport stream currently selected 
    * by the network interface connected to this SIDatabase.
    * <p>
    * The SIIterator that is returned with the event when the request completes
    * successfully will contain an object that implements the SITransportStreamNIT 
    * interface. If no matching object was found, the appropriate one of the following
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
    * @see SITransportStream
    * @see DescriptorTag
    */
  public SIRequest retrieveActualSITransportStream(short retrieveMode, Object appData, 
						   SIRetrievalListener listener, 
						   short[] someDescriptorTags)     
    throws SIIllegalArgumentException {
    // actual code shall replace the following statement
    return (null);
  }
  
  /**
    * Retrieve information associated with the actual services.
    * The actual services are the services in the transport stream 
    * currently selected by the network interface connected to this SIDatabase.
    * <p>
    * The SIIterator that is returned with the event when the request completes
    * successfully will contain one or more objects that implement the SIService interface.
    * If no matching object was found, the appropriate one of the 
    * following events is sent: <code>SINotInCacheEvent</code>, 
    * <code>SIObjectNotInTableEvent</code> or <code>SITableNotFoundEvent</code>.
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
  public SIRequest retrieveActualSIServices(short retrieveMode, Object appData, 
					    SIRetrievalListener listener, 
					    short[] someDescriptorTags) 
    throws SIIllegalArgumentException {
    // actual code shall replace the following statement
    return (null);
  }

  /**
    * Retrieve information associated with services.
    * The required services can be specified by their identification. When -1
    * is specified for <code>transportStreamId</code> then services shall be
    * retrieved regardless of their transport stream id. When -1 is specified for
    * <code>serviceId</code> then services shall be retrieved regardless of their
    * service id.
    * <p>
    * The SIIterator that is returned with the event when the request completes
    * successfully will contain one or more objects that implement the SIService interface.
    * If no matching object was found, the appropriate one of the 
    * following events is sent: <code>SINotInCacheEvent</code>, 
    * <code>SIObjectNotInTableEvent</code> or <code>SITableNotFoundEvent</code>.
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
    * @param originalNetworkId  Identification of the services to be retrieved: 
    *                           original network identifier
    * @param transportStreamId  Identification of the services to be retrieved: 
    *                           transport stream identifier (-1 means return services regardless
    *				of their transport stream id)
    * @param serviceId          Identification of the services to be retrieved: 
    *                           service identifier (-1 means return services regardless of
    *				their service id)
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
    * @exception SIIllegalArgumentException thrown if the retrieveMode is invalid or 
    *                                       the numeric identifiers are out of range
    * 
    * @see SIRequest
    * @see SIRetrievalListener
    * @see SIService
    * @see DescriptorTag
    */
  public SIRequest retrieveSIServices(short retrieveMode, Object appData, 
				      SIRetrievalListener listener, 
				      int originalNetworkId,
				      int transportStreamId,
				      int serviceId,
				      short[] someDescriptorTags)
    throws SIIllegalArgumentException {
    // actual code shall replace the following statement
    return (null);
  }


  /**
    * Retrieve information associated with a service.
    * The required service can be specified by its DVB locator.
    * <p>
    * The SIIterator that is returned with the event when the request completes
    * successfully will contain an object that implements the SIService interface.
    * If no matching object was found, the appropriate one of the following
    * events is sent: SINotInCacheEvent SIObjectNotInTableEvent
    * or SITableNotFoundEvent"
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
    * @param dvbLocator         DVB locator identifying the service.The locator 
    *                           may be more specific than identifying a service, 
    *                           but this method will only use the parts starting from
    *                           the beginning up to the service id.
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
    * @exception SIIllegalArgumentException thrown if the retrieveMode is invalid or 
    *                                       the locator is invalid and does not 
    *                                       identify a service 
    * 
    * @see SIRequest
    * @see SIRetrievalListener
    * @see SIService
    * @see DescriptorTag
    */
  public SIRequest retrieveSIService(short retrieveMode, Object appData, 
				     SIRetrievalListener listener, 
				     org.davic.net.dvb.DvbLocator dvbLocator,
				     short[] someDescriptorTags)
    throws SIIllegalArgumentException {
    // actual code shall replace the following statement
    return (null);
  }


  /**
    * Retrieve PMT information associated with services from the actual
    * transport stream of this SIDatabase object.
    * The required services can be specified by their identification. When -1 
    * is specified as <code>serviceId</code> then services shall be retrieved
    * regardless of their service id.
    * <p>
    * The SIIterator that is returned with the event when the request completes
    * successfully will contain one or more objects that implement the PMTService interface.
    * If no matching object was found, the appropriate one of the 
    * following events is sent: <code>SINotInCacheEvent</code>, 
    * <code>SIObjectNotInTableEvent</code> or <code>SITableNotFoundEvent</code>.
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
    * @param serviceId          Identification of the services to be retrieved: 
    *                           service identifier (-1 means return services regardless of
    * 				their service id)
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
    * @exception SIIllegalArgumentException thrown if the retrieveMode is invalid or 
    *                                       the numeric identifiers are out of range
    * 
    * @see SIRequest
    * @see SIRetrievalListener
    * @see SIService
    * @see DescriptorTag
    */
  public SIRequest retrievePMTServices(short retrieveMode, Object appData, 
				       SIRetrievalListener listener,
				       int serviceId,
				       short[] someDescriptorTags)
    throws SIIllegalArgumentException {
    // actual code shall replace the following statement
    return (null);
  }


  /**
    * Retrieve PMT information associated with a service.
    * The required service can be specified by its DVB locator.
    * <p>
    * The SIIterator that is returned with the event when the request completes
    * successfully will contain an object that implements the PMTService interface.
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
    * @param dvbLocator         DVB Locator identifying the service. The locator 
    *                           may be more specific than identifying a service, 
    *                           but this method will only use the parts starting from
    *                           the beginning up to the service id.
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
    * @exception SIIllegalArgumentException thrown if the retrieveMode is invalid or 
    *                                       the locator is invalid and does not 
    *                                       identify a service 
    * 
    * @see SIRequest
    * @see SIRetrievalListener
    * @see SIService
    * @see DescriptorTag
    */
  public SIRequest retrievePMTService(short retrieveMode, Object appData, 
				      SIRetrievalListener listener, 
				      org.davic.net.dvb.DvbLocator dvbLocator,
				      short[] someDescriptorTags)
    throws SIIllegalArgumentException {
    // actual code shall replace the following statement
    return (null);
  }


  /**
    * Retrieve PMT elementary stream information associated with components of a 
    * service from the actual transport stream of this SIDatabase object.
    * The elementary streams can be specified by their identification. When -1 is
    * specified for <code>componentTag</code> then elementary streams shall be 
    * retrieved regardless of their component tag.
    * <p>
    * The SIIterator that is returned with the event when the request completes
    * successfully will contain one or more objects that implement the PMTElementaryStream
    * interface. If no matching object was found, the appropriate one of the 
    * following events is sent: <code>SINotInCacheEvent</code>, 
    * <code>SIObjectNotInTableEvent</code> or <code>SITableNotFoundEvent</code>.
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
    * @param serviceId          Identification of the elementary streams to be retrieved: 
    *                           service identifier
    * @param componentTag       Identification of the elementary streams to be retrieved:
    *                           component tag (-1 means return elementary streams regardless
    *				of their component tag)
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
    * @exception SIIllegalArgumentException thrown if the retrieveMode is invalid or 
    *                                       the numeric identifiers are out of range
    * 
    * @see SIRequest
    * @see SIRetrievalListener
    * @see SIService
    * @see DescriptorTag
    */
  public SIRequest retrievePMTElementaryStreams(short retrieveMode, Object appData, 
						SIRetrievalListener listener, 
						int serviceId,
						int componentTag,
						short[] someDescriptorTags)
    throws SIIllegalArgumentException {
    // actual code shall replace the following statement
    return (null);
  }


  /**
    * Retrieve PMT elementary stream information associated with components of a service.
    * The required component(s) can be specified by its DVB locator.
    * <p>
    * The SIIterator that is returned with the event when the request completes
    * successfully will contain one or more objects that implement the PMTElementaryStream 
    * interface. If no matching object was found, the appropriate one of the 
    * following events is sent: <code>SINotInCacheEvent</code>, 
    * <code>SIObjectNotInTableEvent</code> or <code>SITableNotFoundEvent</code>.
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
    * @param dvbLocator         DVB Locator identifying the component(s) of a service.
    *                           The locator may be more specific than identifying one or more 
    *                           service components, but this method will only use the 
    *                           parts starting from the beginning up to the component tags.
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
    * @exception SIIllegalArgumentException thrown if the retrieveMode is invalid or if the
    *                                              locator is invalid and does not identify
    *                                              one or more service components
    * 
    * @see SIRequest
    * @see SIRetrievalListener
    * @see SIService
    * @see DescriptorTag
    */
  public SIRequest retrievePMTElementaryStreams(short retrieveMode, Object appData, 
						SIRetrievalListener listener, 
						org.davic.net.dvb.DvbLocator dvbLocator,
						short[] someDescriptorTags)
    throws SIIllegalArgumentException {
    // actual code shall replace the following statement
    return (null);
  }


  /**
    * Retrieve information associated with time from the Time and Date Table (TDT) 
    * from the actual transport stream.
    * <p>
    * The SIIterator that is returned with the event when the request completes
    * successfully will contain an object that implements the SITime interface.
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
    * @return                   An SIRequest object 
    * 
    * @exception SIIllegalArgumentException thrown if the retrieveMode is invalid 
    *
    * @see SIRequest
    * @see SIRetrievalListener
    * @see SITime
    */
  public SIRequest retrieveSITimeFromTDT(short retrieveMode, Object appData, 
					 SIRetrievalListener listener) 
    throws SIIllegalArgumentException {
    // actual code shall replace the following statement
    return (null);
  }
  
  /**
    * Retrieve information associated with time from the Time Offset Table (TOT) 
    * from the actual transport stream.
    * The time information will be accompanied with offset information
    * <p>
    * The SIIterator that is returned with the event when the request completes
    * successfully will contain an object that implements the SITime interface.
    * If no matching object was found, the appropriate one of the following
    * events is sent: SINotInCacheEvent SIObjectNotInTableEvent
    * or SITableNotFoundEvent.
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
    * @return                   An SIRequest object 
    * 
    * @exception SIIllegalArgumentException thrown if the retrieveMode is invalid 
    *
    * @see SIRequest
    * @see SIRetrievalListener
    * @see SITime
    */
  public SIRequest retrieveSITimeFromTOT(short retrieveMode, Object appData, 
					 SIRetrievalListener listener,
					 short[] someDescriptorTags) 
    throws SIIllegalArgumentException {
    // actual code shall replace the following statement
    return (null);
  }
  
  /**
    * Retrieve the SITransportStreamDescription object representing the information 
    * of the TSDT table in the actual transport stream of this SIDatabase object.
    * <p>
    * The SIIterator that is returned with the event when the request completes
    * successfully will contain an object that implements the 
    * SITransportStreamDescription interface.
    * If no matching object was found, the appropriate one of the following
    * events is sent: SINotInCacheEvent SIObjectNotInTableEvent
    * or SITableNotFoundEvent.
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
    * @return                   An SIRequest object 
    *
    * @exception SIIllegalArgumentException thrown if the retrieveMode is invalid 
    * 
    * @see SIRequest
    * @see SIRetrievalListener
    * @see SITransportStreamDescription
    * @see DescriptorTag
    */
  public SIRequest retrieveSITransportStreamDescription(short retrieveMode, 
							Object appData, 
							SIRetrievalListener listener,
							short[] someDescriptorTags) 
    throws SIIllegalArgumentException {
    // actual code shall replace the following statement
    return (null);
  }


  /**
    * Initiate monitoring of the network information. When the network information
    * changes, an event will be delivered to the registered listener object.
    * <p>
    * How the monitoring is performed is implementation dependent and especially
    * does not necessarily need to be continuous. The event will be delivered as 
    * soon as the implementation notices the change which might have some delay
    * relative to when the change was actually made in the stream due to resources
    * for the monitoring being scheduled between the monitoring activities of different
    * tables.
    * The present document does not set any minimum requirements for monitoring of the SI
    * tables. This is to be done at a best effort basis by the implementation and is entirely 
    * implementation dependent. The only requirement is that when an implementation detects a 
    * change, e.g. because a resident Navigator or an MHP application has retrieved some SI 
    * information from the stream, then these listeners are notified of the change.
    * <p>
    * The monitoring stops silently and permanently when the network interface with which
    * this SIDatabase object is associated starts tuning to another
    * transport stream.
    *
    * @param listener           listener object that will receive events when a 
    *                           change in the information is detected.
    * @param networkId          network identifier of the network whose information
    *                           will be monitored.
    * 
    * @exception SIIllegalArgumentException thrown if the identifiers are invalid (e.g.
    *                                       out of range)
    *
    * @see SIMonitoringListener
    * @see SIMonitoringEvent
    */
  public void addNetworkMonitoringListener(SIMonitoringListener listener, 
					   int networkId)
    throws SIIllegalArgumentException {
  }
  

  /**
    * Removes the registration of an event listener for network information monitoring.
    * If this method is called with a listener that is registered but not with the same 
    * identifiers of the SI objects as given in the parameter, the method shall fail silently 
    * and the listeners stays registered with those identifiers that it has been added. 
    *
    * @param listener           listener object that has previously been registered
    * @param networkId          network identifier of the network which is no longer
    *                           to be monitored by the listener
    * 
    * @exception SIIllegalArgumentException thrown if the identifiers are invalid (e.g.
    *                                       out of range)
    *
    * @see SIMonitoringListener
    * @see SIMonitoringEvent
    */
  public void removeNetworkMonitoringListener(SIMonitoringListener listener, 
					      int networkId)
    throws SIIllegalArgumentException {
  }
  
  /**
    * Initiate monitoring of the bouquet information. When the bouquet information
    * changes, an event will be delivered to the registered listener object.
    * <p>
    * How the monitoring is performed is implementation dependent and especially
    * does not necessarily need to be continuous. The event will be delivered as 
    * soon as the implementation notices the change which might have some delay
    * relative to when the change was actually made in the stream due to resources
    * for the monitoring being scheduled between the monitoring activities of different
    * tables. 
    * The present document does not set any minimum requirements for monitoring of the SI
    * tables. This is to be done at a best effort basis by the implementation and is entirely 
    * implementation dependent. The only requirement is that when an implementation detects a 
    * change, e.g. because a resident Navigator or an MHP application has retrieved some SI 
    * information from the stream, then these listeners are notified of the change.
    * <p>
    * The monitoring stops silently and permanently when the network interface with which
    * this SIDatabase object is associated starts tuning to another
    * transport stream.
    * 
    * @param listener           listener object that will receive events when a 
    *                           change in the information is detected.
    * @param bouquetId          bouquet identifier of the bouquet whose information
    *                           will be monitored.
    * 
    * @exception SIIllegalArgumentException thrown if the identifiers are invalid (e.g.
    *                                       out of range)
    *
    * @see SIMonitoringListener
    * @see SIMonitoringEvent
    */
  public void addBouquetMonitoringListener(SIMonitoringListener listener, 
					   int bouquetId)
    throws SIIllegalArgumentException {
  }
  

  /**
    * Removes the registration of an event listener for bouquet information monitoring.
    * If this method is called with a listener that is registered but not with the same 
    * identifiers of the SI objects as given in the parameters, the method shall fail silently 
    * and the listeners stays registered with those identifiers that it has been added. 
    *
    * @param listener           listener object that has previously been registered
    * @param bouquetId          bouquet identifier of the bouquet whose information
    *                           has been requested to be monitored
    * 
    * @exception SIIllegalArgumentException thrown if the identifiers are invalid (e.g.
    *                                       out of range)
    *
    * @see SIMonitoringListener
    * @see SIMonitoringEvent
    */
  public void removeBouquetMonitoringListener(SIMonitoringListener listener, 
					      int bouquetId)
    throws SIIllegalArgumentException {
  }
  

  /**
    * Initiate monitoring of information in the SDT related to services. When the 
    * information related to services changes, an event will be delivered to the registered 
    * listener object.
    * <p>
    * The scope of the monitoring is determined by the original network identifier
    * and transport stream identifier. The listener will be notified about the 
    * change of the information in any service within that scope.
    * <p>
    * How the monitoring is performed is implementation dependent and especially
    * does not necessarily need to be continuous. The event will be delivered as 
    * soon as the implementation notices the change which might have some delay
    * relative to when the change was actually made in the stream due to resources
    * for the monitoring being scheduled between the monitoring activities of different
    * tables. 
    * The present document does not set any minimum requirements for monitoring of the SI
    * tables. This is to be done at a best effort basis by the implementation and is entirely 
    * implementation dependent. The only requirement is that when an implementation detects a 
    * change, e.g. because a resident Navigator or an MHP application has retrieved some SI 
    * information from the stream, then these listeners are notified of the change.
    * <p>
    * The monitoring stops silently and permanently when the network interface with which
    * this SIDatabase object is associated starts tuning to another
    * transport stream.
    * 
    * @param listener           listener object that will receive events when a 
    *                           change in the information is detected.
    * @param originalNetworkId  original network identifier specifying the 
    *                           scope of the monitoring.
    * @param transportStreamId  transport stream identifier specifying the 
    *                           scope of the monitoring.
    * 
    * @exception SIIllegalArgumentException thrown if the identifiers are invalid (e.g.
    *                                       out of range)
    *
    * @see SIMonitoringListener
    * @see SIMonitoringEvent
    */
  public void addServiceMonitoringListener(SIMonitoringListener listener, 
					   int originalNetworkId,
					   int transportStreamId)
    throws SIIllegalArgumentException {
  }
  

  /**
    * Removes the registration of an event listener for monitoring information
    * related to services.
    * If this method is called with a listener that is registered but not with the same 
    * identifiers of the SI objects as given in the parameters, the method shall fail silently 
    * and the listeners stays registered with those identifiers that it has been added. 
    *
    * @param listener           listener object that has previously been registered
    * @param originalNetworkId  original network identifier specifying the 
    *                           scope of the monitoring.
    * @param transportStreamId  transport stream identifier specifying the 
    *                           scope of the monitoring.
    * 
    * @exception SIIllegalArgumentException thrown if the identifiers are invalid (e.g.
    *                                       out of range)
    *
    * @see SIMonitoringListener
    * @see SIMonitoringEvent
    */
  public void removeServiceMonitoringListener(SIMonitoringListener listener, 
					      int originalNetworkId,
					      int transportStreamId)
    throws SIIllegalArgumentException {
  }
  


  /**
    * Initiate monitoring of information in the PMT related to a service. When the information
    * related to a service changes, an event will be delivered to the registered 
    * listener object.
    * <p>
    * How the monitoring is performed is implementation dependent and especially
    * does not necessarily need to be continuous. The event will be delivered as 
    * soon as the implementation notices the change which might have some delay
    * relative to when the change was actually made in the stream due to resources
    * for the monitoring being scheduled between the monitoring activities of different
    * tables. 
    * Except as specified below, the present document does not set any minimum requirements for monitoring of the SI
    * tables. This is to be done at a best effort basis by the implementation and is entirely 
    * implementation dependent. The only requirement is that when an implementation detects a 
    * change, e.g. because a resident Navigator or an MHP application has retrieved some SI 
    * information from the stream, then these listeners are notified of the change.
    * When the referenced service is the currently selected service within a service 
    * context, the terminal shall monitor this PMT and report the changes to any registered listener(s).
    * <p>
    * The monitoring stops silently and permanently when the network interface with which
    * this SIDatabase object is associated starts tuning to another
    * transport stream.
    * @param listener           listener object that will receive events when a 
    *                           change in the information is detected.
    * @param originalNetworkId  original network identifier of the service
    * @param transportStreamId  transport stream identifier of the service 
    * @param serviceId          service identifier specifying the service 
    *                           whose information will be monitored
    * 
    * @exception SIIllegalArgumentException thrown if the identifiers are invalid (e.g.
    *                                       out of range)
    *
    * @see SIMonitoringListener
    * @see SIMonitoringEvent
    */
  public void addPMTServiceMonitoringListener(SIMonitoringListener listener, 
					      int originalNetworkId,
					      int transportStreamId, 
					      int serviceId)
    throws SIIllegalArgumentException {
  }
  

  /**
    * Removes the registration of an event listener for monitoring information in the PMT
    * related to a service.
    * If this method is called with a listener that is registered but not with the same 
    * identifiers of the SI objects as given in the parameters, the method shall fail silently 
    * and the listeners stays registered with those identifiers that it has been added. 
    *
    * @param listener           listener object that has previously been registered
    * @param originalNetworkId  original network identifier of the service
    * @param transportStreamId  transport stream identifier of the service 
    * @param serviceId          service identifier specifying the service 
    *                           whose information has been requested to be monitored
    * 
    * @exception SIIllegalArgumentException thrown if the identifiers are invalid (e.g.
    *                                       out of range)
    *
    * @see SIMonitoringListener
    * @see SIMonitoringEvent
    */
  public void removePMTServiceMonitoringListener(SIMonitoringListener listener, 
					      int originalNetworkId,
					      int transportStreamId,
					      int serviceId)
    throws SIIllegalArgumentException {
  }

  /**
    * Initiate monitoring of information in the EIT related to present and following
    * events. When the information related to those events changes, an event will 
    * be delivered to the registered listener object.
    * <p>
    * The scope of the monitoring is determined by the original network identifier,
    * transport stream identifier and service identifier. The listener will be notified 
    * about the change of the information in any present and following event within that scope.
    * <p>
    * How the monitoring is performed is implementation dependent and especially
    * does not necessarily need to be continuous. The event will be delivered as 
    * soon as the implementation notices the change which might have some delay
    * relative to when the change was actually made in the stream due to resources
    * for the monitoring being scheduled between the monitoring activities of different
    * tables. 
    * The present document does not set any minimum requirements for monitoring of the SI
    * tables. This is to be done at a best effort basis by the implementation and is entirely 
    * implementation dependent. The only requirement is that when an implementation detects a 
    * change, e.g. because a resident Navigator or an MHP application has retrieved some SI 
    * information from the stream, then these listeners are notified of the change.
    * <p>
    * The monitoring stops silently and permanently when the network interface with which
    * this SIDatabase object is associated starts tuning to another
    * transport stream.
    * When the referenced service is carried in the currently tuned transport stream, 
    * the terminal shall monitor this EITp/f actual and report the changes to any registered listener(s).
    * 
    * @param listener           listener object that will receive events when a 
    *                           change in the information is detected.
    * @param originalNetworkId  original network identifier specifying the 
    *                           scope of the monitoring.
    * @param transportStreamId  transport stream identifier specifying the 
    *                           scope of the monitoring.
    * @param serviceId          service identifier specifying the scope of the monitoring
    * 
    * @exception SIIllegalArgumentException thrown if the identifiers are invalid (e.g.
    *                                       out of range)
    *
    * @see SIMonitoringListener
    * @see SIMonitoringEvent
    */
  public void addEventPresentFollowingMonitoringListener(SIMonitoringListener listener, 
							 int originalNetworkId,
							 int transportStreamId,
							 int serviceId)
    throws SIIllegalArgumentException {
  }
  

  /**
    * Removes the registration of an event listener for monitoring information
    * related to present and following events
    * If this method is called with a listener that is registered but not with the same 
    * identifiers of the SI objects as given in the parameters, the method shall fail silently 
    * and the listeners stays registered with those identifiers that it has been added. 
    *
    * @param listener           listener object that has previously been registered
    * @param originalNetworkId  original network identifier specifying the 
    *                           scope of the monitoring.
    * @param transportStreamId  transport stream identifier specifying the 
    *                           scope of the monitoring.
    * @param serviceId          service identifier specifying the scope of the
    *                           monitoring
    * 
    * @exception SIIllegalArgumentException thrown if the identifiers are invalid (e.g.
    *                                       out of range)
    *
    * @see SIMonitoringListener
    * @see SIMonitoringEvent
    */
  public void removeEventPresentFollowingMonitoringListener(SIMonitoringListener listener, 
							    int originalNetworkId,
							    int transportStreamId,
							    int serviceId)
    throws SIIllegalArgumentException {
  }
  

  /**
    * Initiate monitoring of information in the EIT related to scheduled
    * events. When the information related to those events changes, an event will 
    * be delivered to the registered listener object.
    * <p>
    * The scope of the monitoring is determined by the original network identifier,
    * transport stream identifier, service identifier, start time and end time of the 
    * schedule period. The listener will be notified about the change of the information 
    * in any scheduled event within that scope. The scope of the start time and end time 
    * shall be as specified for the parameters of the same name in SIService.retrieveScheduledSIEvents. 
    * <p>
    * How the monitoring is performed is implementation dependent and especially
    * does not necessarily need to be continuous. The event will be delivered as 
    * soon as the implementation notices the change which might have some delay
    * relative to when the change was actually made in the stream due to resources
    * for the monitoring being scheduled between the monitoring activities of different
    * tables. 
    * The present document does not set any minimum requirements for monitoring of the SI
    * tables. This is to be done at a best effort basis by the implementation and is entirely 
    * implementation dependent. The only requirement is that when an implementation detects a 
    * change, e.g. because a resident Navigator or an MHP application has retrieved some SI 
    * information from the stream, then these listeners are notified of the change.
    * <p>
    * The monitoring stops silently and permanently when the network interface with which
    * this SIDatabase object is associated starts tuning to another
    * transport stream.
    * 
    * @param listener           listener object that will receive events when a 
    *                           change in the information is detected.
    * @param originalNetworkId  original network identifier specifying the 
    *                           scope of the monitoring.
    * @param transportStreamId  transport stream identifier specifying the 
    *                           scope of the monitoring.
    * @param serviceId          service identifier specifying the scope of the monitoring
    * @param startTime          start time of the schedule period           
    * @param endTime            end time of the schedule period
    *
    * @exception SIIllegalArgumentException thrown if the identifiers are invalid (e.g.
    *                                       out of range)
    * @exception SIInvalidPeriodException   thrown if end time is before start time
    *
    * @see SIMonitoringListener
    * @see SIMonitoringEvent
    */
  public void addEventScheduleMonitoringListener(SIMonitoringListener listener, 
						 int originalNetworkId,
						 int transportStreamId,
						 int serviceId,
						 java.util.Date startTime,
						 java.util.Date endTime)
    throws SIIllegalArgumentException, SIInvalidPeriodException {
  }
  

  /**
    * Removes the registration of an event listener for monitoring information
    * related to scheduled events for all periods
    * If this method is called with a listener that is registered but not with the same 
    * identifiers of the SI objects as given in the parameters, the method shall fail silently 
    * and the listeners stays registered with those identifiers that it has been added. 
    *
    * @param listener           listener object that has previously been registered
    * @param originalNetworkId  original network identifier specifying the 
    *                           scope of the monitoring.
    * @param transportStreamId  transport stream identifier specifying the 
    *                           scope of the monitoring.
    * @param serviceId          service identifier specifying the scope of the
    *                           monitoring
    * 
    * @exception SIIllegalArgumentException thrown if the identifiers are invalid (e.g.
    *                                       out of range)
    *
    * @see SIMonitoringListener
    * @see SIMonitoringEvent
    */
  public void removeEventScheduleMonitoringListener(SIMonitoringListener listener, 
						    int originalNetworkId,
						    int transportStreamId,
						    int serviceId)
    throws SIIllegalArgumentException {
  }
  
}

