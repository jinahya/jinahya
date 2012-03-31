/*
 * @(#)ServiceInformationType.java	1.20 00/09/16
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

package javax.tv.service;


/**
 * 
 * This class represents values of service information (SI) formats.
 */
public class ServiceInformationType {

  private String name = null;

  /**
   * Creates a service information type object.
   *
   * @param name The string name of this type (e.g., "ATSC_PSIP").
   */
  protected ServiceInformationType(String name) {
	this.name = name;
	if (name == null) {
		throw new NullPointerException("Name is null");
	}
  }
	
  /**
   * Provides the string name of the SI type.  For the type objects
   * defined in this class, the string name will be identical to the
   * class variable name.
   */
  public String toString() { return name;}


  /**
   * ATSC PSIP format.
   */
  public static final ServiceInformationType ATSC_PSIP;
  
  /**
   * DVB SI format.
   */
  public static final ServiceInformationType DVB_SI;
  
  /**
   * SCTE SI format.
   */
  public static final ServiceInformationType SCTE_SI;

  /**
   * Unknown format.
   */
  public static final ServiceInformationType UNKNOWN;


  // Needed for compilation
  static {
    ATSC_PSIP = new ServiceInformationType("ATSC_PSIP");
    DVB_SI = new ServiceInformationType("DVB_SI");
    SCTE_SI = new ServiceInformationType("SCTE_SI");
    UNKNOWN = new ServiceInformationType("UNKNOWN");
  }
}
