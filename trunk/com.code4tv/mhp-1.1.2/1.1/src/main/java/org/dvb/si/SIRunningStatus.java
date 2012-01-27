package org.dvb.si;

/**
  * This interface defines the constants corresponding to the running status values 
  * for services and events.
  */

public interface SIRunningStatus
{
    /** Constant value for the running status as specified in EN 300 468
      */
    public static final byte UNDEFINED               = 0;
    
    /** Constant value for the running status as specified in EN 300 468
      */
    public static final byte NOT_RUNNING             = 1;
    
    /** Constant value for the running status as specified in EN 300 468
      */
    public static final byte STARTS_IN_A_FEW_SECONDS = 2;
    
    /** Constant value for the running status as specified in EN 300 468
      */
    public static final byte PAUSING                 = 3;
    
    /** Constant value for the running status as specified in EN 300 468
      */
    public static final byte RUNNING                 = 4;
}

