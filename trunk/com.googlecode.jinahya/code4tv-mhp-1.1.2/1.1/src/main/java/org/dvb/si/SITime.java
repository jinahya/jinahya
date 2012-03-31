package org.dvb.si;

/**
  * This interface represents the Time and Date Table (TDT) and the (optional) 
  * Time Offset Table (TOT).
  * When it represents a TDT table, the <code>retrieveDescriptors</code> and 
  * <code>getDescriptorTags</code> methods behave as documented in the case 
  * when there are no descriptors, because the TDT does not contain any descriptors. 
  * @see SIDatabase
  */

public interface SITime extends SIInformation {
  
  /**
    * Get the UTC time as coded in the TDT or TOT table.
    * @return The UTC as coded in the TDT or TOT table.
    */
  public java.util.Date getUTCTime();
}

