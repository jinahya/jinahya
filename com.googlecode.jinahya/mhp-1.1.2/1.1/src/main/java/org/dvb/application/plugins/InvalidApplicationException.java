package org.dvb.application.plugins ;

/**
 * Thrown if an application is not valid for execution by a plug-in.
 *
 * @since   MHP1.1
 */
public class InvalidApplicationException extends Exception {
   /**
   * Construct a InvalidApplicationException with no detail message
   */
      public InvalidApplicationException()
      {
      }

   /**
   * Construct a InvalidApplicationException with a detail message
   * @param s detail message
   */
      public InvalidApplicationException(String s)
      {
      }
}

