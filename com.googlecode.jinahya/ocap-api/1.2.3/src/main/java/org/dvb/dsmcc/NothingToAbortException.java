

   package org.dvb.dsmcc;

   import java.io.*;




/**
  * A NothingToAbortException is thrown when the abort method is called and
  * there is no loading in progress.
  *
  *
  */

   public class NothingToAbortException extends DSMCCException {
   
   /**
   * Construct a NothingToAbortException with no detail message
   *
   */
      public NothingToAbortException()
      {
      }
   
   /**
   * Construct a NothingToAbortException with the specified detail message
   * @param s the detail message
   */
      public NothingToAbortException(String s)
      {
      }  
   }




