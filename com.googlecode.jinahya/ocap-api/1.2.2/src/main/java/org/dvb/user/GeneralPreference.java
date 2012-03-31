   package org.dvb.user ;

/**
 * This class defines a set of general preferences. These preferences 
 * are read from the receiver and each application (downloaded or not) 
 * can access them through the <code>UserPreferenceManager.read</code> method. The 
 * standardized preferences are "User Language", "Parental Rating", 
 * "User Name", "User Address", "User @", "Country Code", "Default Font Size".<p>
 * When constructed, objects of this class are empty and have no values
 * defined. Values may be added using the add methods inherited from the
 * Preference class or by calling <code>UserPreferenceManager.read</code>.<p>
 * The encodings of these standardized preferences are as follows.<ul>
 * <li>User Language: 3 letter ISO 639 language codes; 
 * <li>Parental Rating: string using the same encoding as returned by <code>javax.tv.service.guide.ContentRatingAdvisory.getDisplayText</code>; 
 * <li>User Name: Name of the user. This shall be in an order that is appropriate for 
 * presentation directly to the user, e.g. in Western Europe, listing the first name first 
 * and the family name last is recommended as being culturally appropriate in many locales.
 * <li>User Address: postal address of the user, may contain multiple lines separated by 
 * carriage return characters (as defined in table D-4). 
 * <li>User @: e-mail address of the user in the SMTP form as defined in RFC821; 
 * <li>Country Code: two letter ISO 3166-1 country code; 
 * <li>Default Font Size: preferred font size for normal body text expressed in points, 
 * decimal integer value encoded as a string (26 is the default; differing size indicates a 
 * preference of different font size than usual)
 * </ul>
 * The preference names are treated as case-insensitive. The preference names shall be 
 * considered equal at least when the method java.lang.String.equalsIgnoreCase() returns 
 * true for the strings when the locale "EN.UK" is used. Depending on the locale used in 
 * the implementation, implementations are allowed to consider equal also other upper 
 * and lower case character pairs in addition to those defined by the "EN.UK" locale.
 * <p>
 * The standardized preference names in the present document shall only use such letters 
 * where the upper and lower case characters are recognized by the "EN.UK" locale.
 */
   public final class GeneralPreference extends Preference
   {   
   /**
    * Constructs a GeneralPreference object. A general preference maps a 
    * preference name to a list of strings.
    *
    * @param name the general preference name.
    *
    * @exception IllegalArgumentException if the preference's name is not 
    * supported.
    */
      public GeneralPreference (String name) throws IllegalArgumentException 
      {
      }
   }

