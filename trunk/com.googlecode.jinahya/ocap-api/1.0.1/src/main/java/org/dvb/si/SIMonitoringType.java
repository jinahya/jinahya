package org.dvb.si;

/**
  * This interface defines the constants corresponding to the SI information 
  * type values in SIMonitoringEvent.
  * @see SIMonitoringListener
  * @see SIMonitoringEvent
  */

public interface SIMonitoringType
{

  /** Constant for the type of SIInformation object: Network
    */
  public final static byte NETWORK = 1;

  /** Constant for the type of SIInformation object: Bouquet
    */
  public final static byte BOUQUET = 2;

  /** Constant for the type of SIInformation object: Service
    */
  public final static byte SERVICE = 3;

  /** Constant for the type of SIInformation object: PMTService
    */
  public final static byte PMT_SERVICE = 4;

  /** Constant for the type of SIInformation object: Present or following event
    */
  public final static byte PRESENT_FOLLOWING_EVENT = 5;

  /** Constant for the type of SIInformation object: Scheduled event
    */
  public final static byte SCHEDULED_EVENT = 6;

}

