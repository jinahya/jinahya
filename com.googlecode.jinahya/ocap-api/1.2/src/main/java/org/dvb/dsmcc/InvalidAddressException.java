

   package org.dvb.dsmcc;

   import java.io.*;




/**
  * An InvalidAddressException is thrown when the format of an NSAP address
  * is not recognized.
  *
  *
  */

   public class InvalidAddressException extends DSMCCException {
   
   /**
   * Construct a InvalidAddressException with no detail message
   *
   */
      public InvalidAddressException()
      {
      }
   /**
   * Construct a InvalidAddressException with the specified detail message
   * @param s the detail message
   */
      public InvalidAddressException(String s)
      {
      }  
   }

