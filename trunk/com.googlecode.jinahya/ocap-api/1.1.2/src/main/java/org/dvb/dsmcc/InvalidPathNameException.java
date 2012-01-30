


   package org.dvb.dsmcc;

   import java.io.*;



/**
  * The InvalidPathNameException is thrown when the path name to a DSMCCObject
  *	does not exist or if the ServiceDomain has been detached.
  *
  */

   public class InvalidPathNameException extends DSMCCException {
   /**
   * Construct an InvalidPathNameException with no detail message
   */
      public InvalidPathNameException()
      {
      }
   
   /**
   * Construct an InvalidPathNameException with the specified detail message
   * @param s the detail message
   */
      public InvalidPathNameException(String s)
      {
      }
   
   }

