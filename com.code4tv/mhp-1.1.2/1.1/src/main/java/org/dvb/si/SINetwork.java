package org.dvb.si;

/**
  * This interface (together with the SITransportStreamNIT interface)
  * represents a sub-table of the Network Information Table (NIT) 
  * describing a particular network.
  * <p>
  * Each object that implements the SINetwork interface is identified
  * by the identifier network_id. 
  * @see SITransportStream
  * @see SITransportStreamNIT
  */

public interface SINetwork extends SIInformation {

    /** 
      * This method defines extra semantics for the
      * SIInformation.retrieveDescriptors method (first prototype).
      *
      * If the NIT sub-table on which this SINetwork object is based consists
      * of multiple sections, then this method returns the requested descriptors
      * in the order they appear when concatenating the descriptor loops of the 
      * different sections.
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
      * @return                   An SIRequest object 
      * 
      * @exception SIIllegalArgumentException thrown if the retrieveMode is invalid 
      *
      * @see SIInformation
      * @see SIInformation#retrieveDescriptors
      */
    public SIRequest retrieveDescriptors(short retrieveMode, Object appData, 
					 SIRetrievalListener listener) 
	throws SIIllegalArgumentException;

    /** 
      * This method defines extra semantics for the
      * SIInformation.retrieveDescriptors method (second prototype).
      *
      * If the NIT sub-table on which this SINetwork object is based consists
      * of multiple sections, then this method returns the requested descriptors
      * in the order they appear when concatenating the descriptor loops of the 
      * different sections.
      * 
      * The tag values included in the someDescriptorsTags parameter are used for filtering 
      * the information that is returned. Only information related to descriptors whose 
      * tag value is included in the someDescriptorTags array is retrieved, unless the 
      * someDescriptorTags array contains -1 as its one and only item.
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
      * @param someDescriptorTags Descriptor tag values that are used for filtering descriptors 
      *                           from descriptors included in the SI table item corresponding 
      *                           to this object. If the array contains -1 as its one and only 
      *                           element, all descriptors related to this object are retrieved.
      *                           All values that are out of the valid range for descriptor tags
      *                           (i.e. 0...255) are ignored, except for the special
      *                           meaning of -1 as the only element in the array.
      *
      * @return                   An SIRequest object 
      * 
      * @exception SIIllegalArgumentException thrown if the retrieveMode is invalid 
      *
      * @see SIInformation
      * @see SIInformation#retrieveDescriptors(short, Object, SIRetrievalListener, short[])
      */
    public SIRequest retrieveDescriptors(short retrieveMode, Object appData, 
					 SIRetrievalListener listener,
					 short[] someDescriptorTags) 
	throws SIIllegalArgumentException;
    
    /** 
      * This method defines extra semantics for the
      * SIInformation.getDescriptorTags method.
      * 
      * If the NIT sub-table on which this SINetwork object is based consists
      * of multiple sections, then this method returns the descriptor tags
      * in the order they appear when concatenating the descriptor loops of the 
      * different sections.
      * 
      * @return    The tags of the descriptors actually broadcast for the object 
      *            (identified by their tags).
      *
      * @see SIInformation
      * @see SIInformation#getDescriptorTags
      */
    public short[] getDescriptorTags();
    
    /**
      * Get the identification of this network.
      * @return The network identification identifier.
      */
    public int getNetworkID();
    
    /** 
      * This method returns the name of this network. 
      * If the language returned by <code>javax.tv.service.SIManager.getPreferredLanguage</code>
      * is one of those in the multilingual_network_name_descriptor, return the name in that 
      * language, otherwise return an implementation dependent selection between the names in 
      * the multilingual_network_name_descriptor and the name in the network_name_descriptor.
      * When this information is not available "" is returned. All control characters 
      * as defined in ETR 211 are ignored. For each character the DVB-SI 8 bit character 
      * code is mapped to the appropriate Unicode representation.
      * @return The network name of this network.
      */
    public java.lang.String getName();
    
    /** 
      * This method returns the short name (ETR 211) of this network
      * without emphasis marks. 
      * If the language returned by <code>javax.tv.service.SIManager.getPreferredLanguage</code>
      * is one of those in the multilingual_network_name_descriptor, return the name in that 
      * language, otherwise return an implementation dependent selection between the names in 
      * the multilingual_network_name_descriptor and the name in the network_name_descriptor.
      * If the descriptor is not present, "" is returned. If the string can be found but does 
      * not contain control codes for abbreviating it, the full string shall be returned.
      * For each character the DVB-SI 8 bit character code is mapped to the appropriate 
      * Unicode representation.
      * @return The short network name of this network.
      */
    public String getShortNetworkName();
    
    /**
      * Retrieve information associated with transport streams carried via the network.
      * <p>
      * The SIIterator that is returned with the event when the request completes
      * successfully will contain one or more objects that implement the
      * SITransportStreamNIT interface.
      * This method will retrieve SITransportStreams from the same sub-table version as 
      * this SINetwork instance. If this version of the sub-table is no longer available,
      * an SITableUpdatedEvent is returned.
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
      *                           in all descriptors. If someDescriptorTags is null, 
      *                           the behaviour is implementation dependent.
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
      * @see SITransportStreamNIT
      * @see DescriptorTag
      */
    public SIRequest retrieveSITransportStreams(short retrieveMode, Object appData, 
						SIRetrievalListener listener,
						short[] someDescriptorTags) 
	throws SIIllegalArgumentException;
}










