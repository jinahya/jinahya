

   package org.dvb.dsmcc;

   import java.io.*;



/**
  * An MPEGDEliveryException is thrown when an error 
  * (for instance, a time out or accessing the data would
  * require tuning)
  * occurs while loading data from an MPEG Stream.
  *
  *
  */

   public class MPEGDeliveryException extends DSMCCException {
   
   /**
   * Construct an MPEGDeliveryException with no detail message
   */
      public MPEGDeliveryException()
      {
      }
   
   /**
   * Construct an MPEGDeliveryException with the specified detail message
   * @param s the detail message
   */
      public MPEGDeliveryException(String s)
      {
      }
   
   
   }

