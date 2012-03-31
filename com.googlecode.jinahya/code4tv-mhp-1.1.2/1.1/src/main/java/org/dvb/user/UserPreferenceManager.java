
package org.dvb.user ;

import java.io.*;

import java.util.*;

/**
 * The UserPreferenceManager class gives access to the user preference settings. This
 * class provides a set of methods that allow an application to read or
 * save user settings. It also provides a mechanism to notify applications when
 * a preference has been modified. The value of a user setting, retrieved with
 * the read method, is a copy of the value that is stored in the receiver. The
 * write method, if authorized, overwrites the stored value.<p>
 * NOTE: MHP implementations are not required to validate the values in Preference objects, 
 * even those which are saved using the write method. Applications with write permissions 
 * need to be very careful that the values written are valid. Applications reading 
 * permissions need to be aware of the possibility that a previous application has set 
 * an invalid value.
 */
public class UserPreferenceManager {
  
  private UserPreferenceManager() {}
   
  /**
   * Return an instance of the <code>UserPreferenceManager</code> for this application.
   * Repeated calls to this method by the same application shall return the
   * same instance.
   * @return an instance of <code>UserPreferenceManager</code>
   */
  public static UserPreferenceManager getInstance(){ return null; }

  /**
    * Allows an application to read a specified user preference.
    * When end-user preferences are read into a <code>Preference</code> object from the MHP 
    * terminal, the ordering of these values shall be as determined by the end-user, from 
    * most preferred to least preferred to the extent that this is known.
    *
    * @param p an object representing the preference to read.
    * @throws SecurityException if the calling application is denied access to this preference
    */
  public void read (Preference p) {}   
   
  /**
    * Allows an application to read a specified user preference taking
    * into account the facility defined by the application. 
    * After this method returns, the values in the <code>Preference</code> object shall be the values 
    * of that user preference with any unsupported values from the <code>Facility</code> removed from 
    * that list. Note that the order of values returned here need not be the same as that 
    * returned by <code>read(Preference)</code>.<p>
    * If the intersection
    * between the two sets of values is empty then the preference will have
    * no value. If there is a mis-match between the name of the preference used
    * when constructing the facility and the name of the preference used in this
    * method then the preference will have no value.
    *
    * @param p an object representing the preference to read. 
    * @param facility the preferred values of the application for the preference
    * @throws SecurityException if the calling application is denied access to this preference
    */
  public void read 
      (Preference p, Facility facility) {}  
   
  /**
    * Saves the specified user preference. If this method succeeds then
    * it will change the value of this preference for all future MHP 
    * applications.
    *
    * @param p the preference to save.
    * @throws UnsupportedPreferenceException if the preference provided is not a standardized 
    * preference as defined for use with <code>GeneralPreference</code>.
    * @throws java.lang.SecurityException if the application does not have permission to call this method
    * @throws IOException if saving the preference fails for other I/O reasons
    */
  public void write (Preference p) throws
	UnsupportedPreferenceException, IOException {}
   
  /**
    * Adds a listener for changes in user preferences as held in the MHP terminal. 
    * Specifically this includes changes made by MHP applications succeeding in 
    * calling the write() method on this class. If the implementation of the MHP 
    * terminal allows the end user to change preferences then these changes also 
    * includes changes made to preferences by this mechanism. It does not include 
     * changes made to a Preference instance within the scope of a single MHP application.    *
    * @param l the listener to add.
    */
  public void addUserPreferenceChangeListener
      (UserPreferenceChangeListener l) {}
   
  /**
    * Removes a listener for changes in user preferences. 
    *
    * @param l the listener to remove.
    */
  public void removeUserPreferenceChangeListener
      (UserPreferenceChangeListener l) {}
} 


