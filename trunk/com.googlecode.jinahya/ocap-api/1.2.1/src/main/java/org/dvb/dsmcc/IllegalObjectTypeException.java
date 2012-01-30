

   package org.dvb.dsmcc;

   import java.io.*;
   import org.davic.net.*;


/**
  * This Exception is thrown when the application attempted to create
  * a DSMCCStream or DSMCCStreamEvent object with an object or a path
  * that did not correspond to a DSMCC Stream or DSMCC StreamEvent respectively
  *
  */

   public class IllegalObjectTypeException extends DSMCCException {

   /**
   * constructor of the exception with no detail message
   */
      public IllegalObjectTypeException ()
      {
      }
   
   /**
   * constructor of the exception
   * @param s detail message
   */
      public IllegalObjectTypeException (String s)
      {
      }
   
   }

