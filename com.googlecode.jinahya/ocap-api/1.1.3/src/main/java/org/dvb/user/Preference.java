package org.dvb.user ;

import java.util.Enumeration;
import java.util.Vector;

/**
 * This abstract class defines the Preference object. A Preference maps a
 * name to a list of favourite values. The first element in the list is the 
 * favourite value for this preference.
 * <p>The preference names are treated as case-insensitive. The preference names shall 
 * be considered equal at least when the method java.lang.String.equalsIgnoreCase() 
 * returns true for the strings when the locale "EN.UK" is used. Depending on the locale 
 * used in the implementation, implementations are allowed to consider equal also other 
 * upper and lower case character pairs in addition to those defined by the "EN.UK" locale.
 * <p>
 * The standardized preference names in the present document shall only use such letters 
 * where the upper and lower case characters are recognized by the "EN.UK" locale.
 */
public abstract class Preference 
{   
	/**
	 * This protected constructor is only present to enable sub-classes of
	 * this one to be defined by the platform. It is not intended to be used
	 * by inter-operable applications. 
	 */

  protected Preference()
  {
  }

  /**
    * Creates a new preference with the specified name and the specified
    * value. This single value will be the favourite one for this preference.
    *
    * @param name a String object representing the name of the preference.
    * @param value a String object representing the value of the preference.
     */
  public Preference (String name, String value) 
  {
  }
  
  /**
    * Creates a new preference with the specified name and the specified
    * value set. Each value in the value set must appear only once. The behaviour 
    * if a value is duplicated is implementation dependent.
    * @param name a String object representing the name of the preference.
    * @param value an array of String objects representing the set of values 
    * for this preference ordered from the most favourite to the least favourite.
    */
  public Preference (String name, String value[]) 
  {
  }
  
  /**
    * Adds a new value for this preference. The value is added to the end of
    * the list. If the value is already in the list then it is moved to the
    * end of the list.
    *
    * @param value a String object representing the new value.
    */
  public void add (String value) 
  {
  }

  /**
   * Adds several new values for this preferences. The values are added to the
   * end of the list in the same order as they are found in the array passed to
   * this method. Any values already in the list are moved to the position in
   * the list which they would have if they were not already present.
   *
   * @param values an array of strings representing the values to add
   * @since MHP 1.0.1
   */
	public void add( String values[])
	{
	}
  
  /**
    * Adds a new value for this preference. The value is inserted at the
    * specified position. If the value is already in the list then it is moved 
    * to the position specified.
    * If the position is greater than the length of the
    * list, then the value is added to the end of this list. If the position
    * is negative, then the value is added to the beginning of this list.
    *
    * @param position an int representing the position in the list.
    * @param value a String representing the new value to insert.
    */
  public void add (int position, String value) 
  {
  }
  
  /**
    * Returns the list of favourite values for this preference. Returns an 
    * empty array if no value sets are defined for this preference.
    *
    * @return an array of String representing the favourite values for this
    * preference.
    */
  public String[] getFavourites () 
  {
	return null;
  }
   
  /**
    * Returns the most favourite value for this preference, that is, the
    * first element of the list.
    *
    * @return a String representing the most favourite value for this preference. 
    * Returns null if no value is defined for this preference
    */
  public String getMostFavourite () 
  {
	return null;
  } 
   
  /**
    * Returns the name of the preference.
    *
    * @return a String object representing the name of the preference.
    */
  public String getName () 
  {
	return null;
  }
  
  /**
    * Returns the position in the list of the specified value.
    *
    * @param value a String representing the value to look for.
    * 
    * @return an integer representing the position of the value in the 
    * list counting from zero. If the value is not found then it returns -1.
    */
  public int getPosition (String value) 
  {
	return 0;
  }
   
  /**
    * Tests if this preference has at least one value set.
    *
    * @return true if this preference has at least one value set, 
    * false otherwise.
    */
  public boolean hasValue () 
  {
	return false;
  }
   
  /**
    * Removes the specified value from the list of favourites.
    * If the value is not in the list then the method call has no effect.
    *
    * @param value a String representing the value to remove.
    */
  public void remove (String value)
  {
  }
	/**
	 * Removes all the values of a preference
	 * @since MHP 1.0.1
	 */
	public void removeAll()
	{
	}
   
  /**
    * Sets the most favourite value for this preference. If the value is 
    * already in the list, then it is moved to the head. If the value is
    * not already in the list then it is added at the head.
    *
    * @param value the most favourite value
    */
  public void setMostFavourite (String value) 
  {
  }

  /**
    * Convert name and favourites to a String.
    *
    * @return the preference name and favourites
    */
  public String toString() 
  {
	return null;
  }
}







