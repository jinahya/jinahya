package org.dvb.application;

import java.util.*;

/**
 * The <code>AppID</code> is a representation of the unique identifier for 
 * applications.
 * <p> Its string form is the Hex representation of the 48 bit number.
 */
public class AppID {
    /**
     * This method returns the integer value of the organization number supplied in the constructor.
     *
     * @return the integer value of the organization number supplied in the constructor.
     * @since   MHP1.0
     */
    public int getOID(){return 1;};
    
    /**
     * This method returns the integer value of the application count supplied in the constructor 
     *
     * @return the integer value of the application count supplied in the constructor 
     * @since   MHP1.0
     */
    public int getAID(){return 1;};
    
    /**
     * Create a new AppID based on the given integers. There is no range checking on these numbers.
     *
     * @param     oid     the globally unique organization number.
     * @param     aid     the unique count within the organization.
     * @since   MHP1.0
     */
    public AppID(int oid, int aid){}

    /**
     * This method returns a string containing the Hex representation of the 48 bit 
     * number.
     * The string shall be formatted as specified in the clause on "Text encoding of 
     * application identifiers" in the System Integration clause of the MHP specification.
     * 
     * @return a string containing the Hex representation of the 48 bit 
     * number.
     * @since   MHP1.0
     */
    public String toString(){return null;}

   /** Compares two AppIDs for equality.
    * @param   obj   the reference object with which to compare.
    * @return  <code>true</code> if this obj is an AppID
    *          and its Organisation ID and its Application ID
    *          match the IDs for this AppID; <code>false</code> otherwise.
    */
  public boolean equals( Object obj ) {
    return false;
  }

  /** Returns a hash code value for this AppID.
    * The hashcode for two AppIDs with the same Organisation ID
    * and Application ID are equal.
    * @return  a hash code value for this AppID
    */
  public int hashCode() {
    return 0;
  }

}

