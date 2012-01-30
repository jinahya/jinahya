   package org.dvb.user ;

/**
 * A facility maps a preference's name to a single value or to an array of 
 * values. A facility enables an application to define the list of values
 * supported for a specified preference. For example, if an application is 
 * available in English or French then it can create a Facility 
 * ("User Language", {"English", "French"}). When the application 
 * will retrieve the "User Language" from the general preference it will
 * specify the associated facility in order to get a Preference which
 * will contain a set a values compatible with those supported by the application.
 */
   public class Facility 
   {   
   /**
    * Creates a Facility with a single value. This facility can be used by
    * an application to retrieve a preference compatible with its capabilities.
    *
    * @param preference a String representing the name of the preference.
    * @param value a String representing the value of the preference.
    */
      public Facility (String preference, String value) 
      {
      }
   
   /**
    * Creates a Facility with a set of values. This facility can be used by an
    * application to retrieve a preference compatible with its capabilities.
    *
    * @param preference a String representing the name of the preference.
    * @param values an array of String representing the set of values.
    */
      public Facility (String preference, String values[]) 
      {
      }
   }

