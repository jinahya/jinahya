/*
 * @(#)ServiceType.java	1.13 00/08/06
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
 * This class represents service type values such as "digital television",
 * "digital radio", "NVOD reference service", "NVOD time-shifted service",
 * "analog television", "analog radio", "data broadcast" and "application".
 * These basic service types can be extended by subclassing.<p>
 *
 * (These values are mappable to the ATSC service type in the VCT table and
 * the DVB service type in the Service Descriptor.)
 */
public class ServiceType {

  private String name = null;

  /**
   * Creates a service type object.
   *
   * @param name The string name of this type (e.g., "DIGITAL_TV").
   */
  protected ServiceType(String name) {
  }
  
  
  /**
   * Provides the string name of the type.  For the type objects
   * defined in this class, the string name will be identical to the
   * class variable name.
   */
  public String toString() {
	  return null; 
  }


  /**
   * Digital TV service type.
   */
  public static final ServiceType DIGITAL_TV=null;
  
  /**
   * Digital radio service type.
   */
  public static final ServiceType DIGITAL_RADIO=null;
  
  /**
   * NVOD reference service type.
   */
  public static final ServiceType NVOD_REFERENCE=null;
	
  /**
   * NVOD time-shifted service type.
   */
  public static final ServiceType NVOD_TIME_SHIFTED=null;
	
  /**
   * Analog TV service type.
   */
  public static final ServiceType ANALOG_TV=null;
	
  /**
   * Analog radio service type.
   */
  public static final ServiceType ANALOG_RADIO=null;
	
  /**
   * Data broadcast service type identifying a data service.
   */
  public static final ServiceType DATA_BROADCAST=null;
	
  /**
   * Data application service type identifying an interactive application.
   */
  public static final ServiceType DATA_APPLICATION=null;
	
  /**
   * Unknown service type.
   */
  public static final ServiceType UNKNOWN=null;
}
