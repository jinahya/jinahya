package org.dvb.si;

/**
  * Objects of this class are sent to listener objects of the using application to 
  * notify that a change in the monitored information has happened.
  * @see SIMonitoringType
  * @see SIMonitoringListener
  */

public class SIMonitoringEvent extends java.util.EventObject {

  /** Constructor for the event object
    * @param source the SIDatabase object which is the source of the event
    * @param objectType type of the SIInformation object 
    *                   (constants in SIMonitoringType)
    * @param networkId networkId
    * @param bouquetId bouquetId
    * @param originalNetworkId originalNetworkId
    * @param transportStreamId transportStreamId
    * @param serviceId serviceId
    * @param startTime start time of event schedule period
    * @param endTime end time of event schedule period
    */
  public SIMonitoringEvent(SIDatabase source, byte objectType,
			   int networkId, int bouquetId, int originalNetworkId, 
			   int transportStreamId, int serviceId, 
			   java.util.Date startTime, java.util.Date endTime) {
	  super(source);
  }

  /**
    * Gets the SIDatabase instance that is sending the event.
    * @return the SIDatabase instance that is the source of this event.
    */
  public Object getSource() {
    return (null);
  }

  /**
    * Get the SIInformation type of the information that has changed
    * @return The SIInformation type (the possible values are defined in the 
    *         SIMonitoringType interface).
    * @see SIMonitoringType
    */
  public byte getSIInformationType() {
    // actual code shall replace the following statement
    return ((byte) 0);
  }

  /** Returns the networkId of the network.
    * This method is only applicable if the SIInformation type 
    * returned with the getSIInformationType method is NETWORK.
    * @return the networkId or -2 if not applicable for this event
    */
  public int getNetworkID() {
    return 0;
  }

  /** Returns the bouquetId of the bouquet.
    * This method is only applicable if the SIInformation type 
    * returned with the getSIInformationType method is BOUQUET.
    * @return the bouquetId or -2 if not applicable for this event
    */
  public int getBouquetID() {
    return 0;
  }

  /** Returns the originalNetworkId of the SIInformation objects
    * This method is only applicable if the SIInformation type 
    * returned with the getSIInformationType method is SERVICE, PMT_SERVICE,
    * PRESENT_FOLLOWING_EVENT or SCHEDULED_EVENT.
    * @return the originalNetworkId or -2 if not applicable for this event
    */
  public int getOriginalNetworkID() {
    return 0;
  }

  /** Returns the transportStreamId of the SIInformation objects
    * This method is only applicable if the SIInformation type 
    * returned with the getSIInformationType method is SERVICE, PMT_SERVICE,
    * PRESENT_FOLLOWING_EVENT or SCHEDULED_EVENT.
    * @return the transportStreamId or -2 if not applicable for this event
    */
  public int getTransportStreamID() {
    return 0;
  }

  /** Returns the serviceId of the SIInformation objects
    * This method is only applicable if the SIInformation type 
    * returned with the getSIInformationType method is PMT_SERVICE,
    * PRESENT_FOLLOWING_EVENT or SCHEDULED_EVENT.
    * @return the serviceId or -2 if not applicable for this event
    */
  public int getServiceID() {
    return 0;
  }

  /** Returns the start time of the schedule period whose event information
    * has changed.
    * This method is only applicable if the SIInformation type 
    * returned with the getSIInformationType method is SCHEDULED_EVENT.
    * @return the start time or null if not applicable for this event
    */
  public java.util.Date getStartTime() {
    return null;
  }

  /** Returns the end time of the schedule period whose event information
    * has changed.
    * This method is only applicable if the SIInformation type 
    * returned with the getSIInformationType method is SCHEDULED_EVENT.
    * @return the end time or null if not applicable for this event
    */
  public java.util.Date getEndTime() {
    return null;
  }
}



