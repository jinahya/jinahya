package org.dvb.user ;

/**
 * This class defines the event sent to appropriate listeners when a user
 * preference has been changed.
 */
public class UserPreferenceChangeEvent extends java.util.EventObject
{
  /**
    * Constructs a new event.
    *
    * @param preferenceName the name of the modified preference.
    */
  public UserPreferenceChangeEvent (String preferenceName) 
  {
    super(preferenceName);
  }

  /**
    * Returns the name of the modified Preference
    *
    * @return the Preference name.
    */
  public String getName () {
    return (String)getSource();
  }
}
    

