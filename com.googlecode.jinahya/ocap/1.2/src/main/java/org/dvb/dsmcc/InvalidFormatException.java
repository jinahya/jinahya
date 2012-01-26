

   package org.dvb.dsmcc;

   import java.io.*;



/**
  * An InvalidFormatException is thrown when an inconsistent DSMCC message is
  * received.
  *
  *
  */

   public class InvalidFormatException extends DSMCCException {
   /**
   * Construct an InvalidFormatException with no detail message
   */
      public InvalidFormatException()
      {
      }
   
   /**
   * Construct an InvalidFormatException with the specified detail message
   * @param s the detail message
   */
      public InvalidFormatException(String s)
      {
      }
   }

