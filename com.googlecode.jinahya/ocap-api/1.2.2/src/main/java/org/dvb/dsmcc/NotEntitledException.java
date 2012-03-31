

   package org.dvb.dsmcc;

   import java.io.*;
   import org.davic.net.*;


/**
  * This Exception is thrown when the user is not entitled to access
  * the content of the object (the Elementary Stream is scrambled and the user
  * is not entitled).
  *
  */

   public class NotEntitledException extends DSMCCException {
   
   /**
   * construct a NotEntitledException with no detail message
   */
      public NotEntitledException()
      {
      }

   /**
   * construct a NotEntitledException with a detail message
   * @param s detail message
   */
      public NotEntitledException(String s)
      {
      }
   
   }

