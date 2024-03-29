/*
 * @(#)DeliverySystemType.java	1.16 00/08/26
 *
 * Copyright 1998-2000 by Sun Microsystems, Inc.,
 * 901 San Antonio Road, Palo Alto, California, 94303, U.S.A.
 * All rights reserved.
 * 
 * This software is the confidential and proprietary information
 * of Sun Microsystems, Inc. ("Confidential Information").  You
 * shall not disclose such Confidential Information and shall use
 * it only in accordance with the terms of the license agreement
 * you entered into with Sun.
 */

package javax.tv.service.navigation;


/**
 * This class represents values of various types of delivery systems,
 * for example, satellite, cable, etc.
 */
public class DeliverySystemType {

  /**
   * Creates a delivery system type object.
   *
   * @param name The string name of this type (e.g., "SATELLITE").
   */
  protected DeliverySystemType(String name) {
  }
	
  /**
   * Provides the string name of delivery system type.  For the type
   * objects defined in this class, the string name will be identical
   * to the class variable name.  */
  public String toString() {
	  return null; 
  }


  /**
   * Satellite delivery system type.
   */
  public static final DeliverySystemType SATELLITE=null;
  
  /**
   * Cable delivery system type.
   */
  public static final DeliverySystemType CABLE=null;
  
  /**
   * Terrestrial delivery system type.
   */
  public static final DeliverySystemType TERRESTRIAL=null;

  /**
   * Unknown delivery system type.
   */
  public static final DeliverySystemType UNKNOWN=null;

}
