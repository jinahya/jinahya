


   package org.dvb.dsmcc;

   import java.io.*;



/**
  * A NotLoadedException is thrown when an operation fails 
  * because information which is required to be loaded has not been.
  */

   public class NotLoadedException extends DSMCCException {
   
   /**
   * Construct a NotLoadedException with no detail message
   *
   */
      public NotLoadedException()
      {
      }
   
   /**
   * Construct a NotLoadedException with the specified detail message
   * @param s the detail message
   */
      public NotLoadedException(String s)
      {
      }
   }

