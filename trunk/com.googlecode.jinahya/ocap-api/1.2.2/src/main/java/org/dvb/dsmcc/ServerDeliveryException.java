

   package org.dvb.dsmcc;

   import java.io.*;



/**
  * A ServerDeliveryException is thrown when the local machine can not
  * communicate with the server. This exception is only used with files implemented by
  * delivery over a bi-directional IP connection. For
  * the object carousel the <code>MPEGDeliveryException</code> is used instead.
  *
  */

   public class ServerDeliveryException extends DSMCCException {
   
   /**
   * Construct a ServerDeliveryException with no detail message
   */
      public ServerDeliveryException()
      {
      }
   
   /**
   * Construct a ServerDeliveryException with the specified detail message
   * @param s the detail message
   */
      public ServerDeliveryException(String s)
      {
      }
   
   }

