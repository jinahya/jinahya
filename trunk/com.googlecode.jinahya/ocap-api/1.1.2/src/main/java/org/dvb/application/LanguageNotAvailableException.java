package org.dvb.application ;

/**
 * The <code>LanguageNotAvailableException</code> exception is thrown if the 
 * application asks for the name of an application in a language not signalled
 * in the AIT.
 *
 * @since   MHP1.0
 */
public class LanguageNotAvailableException extends Exception {
   /**
   * Construct a LanguageNotAvailableException with no detail message
   */
      public LanguageNotAvailableException()
      {
      }

   /**
   * Construct a LanguageNotAvailableException with a detail message
   * @param s detail message
   */
      public LanguageNotAvailableException(String s)
      {
      }
}

