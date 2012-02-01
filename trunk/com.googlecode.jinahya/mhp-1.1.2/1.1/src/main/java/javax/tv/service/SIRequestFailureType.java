/*
 * @(#)SIRequestFailureType.java	1.11 00/10/09
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
 * This class represents reason codes for failure of asynchronous SI
 * retrieval requests.  It is subclassed to provide the individual
 * reason codes.
 *
 * @see SIRequestor#notifyFailure
 * @see SIRequest
 */
public class SIRequestFailureType {
  
  private String name = null;
  
  /**
   * Creates an <code>SIRequestFailureType</code> object.
   *
   * @param name The string name of this type (e.g., "CANCELED").
   */
  protected SIRequestFailureType(String name) {
	this.name = name;
	if (name == null) {
 		throw new NullPointerException("Name is null");
	}
  }

  /**
   * Provides the string name of the type.  For the type objects
   * defined in this class, the string name will be identical to the
   * class variable name.
   *
   * @return The string name of the type.
   */
  public String toString() { return name; }

  /**
   * The reason generated when the <code>SIRequest</code> is canceled.
   *
   * @see SIRequest#cancel
   */
  public static final SIRequestFailureType CANCELED;

  /**
   * The reason generated when the resources required to fulfill an
   * asynchronous SI retrieval (such as a tuner, section filter, etc.)
   * are unavailable. The application may attempt to release some
   * resources and attempt the request again.
   */
  public static final SIRequestFailureType INSUFFICIENT_RESOURCES;

  /**
   * The reason generated when the system cannot find the
   * requested data. This occurs when the
   * requested data is not present in the transport stream, when the
   * data is present on some transport/network but the SI database
   * does not know about it, or when the type of requested data is
   * not supported by the broadcast environment.
   */
  public static final SIRequestFailureType DATA_UNAVAILABLE;

  /**
   * The reason for the failure is unknown.
   */
  public static final SIRequestFailureType UNKNOWN;

  
  // Needed for compilation
  static {
    CANCELED         = new SIRequestFailureType("CANCELED");
    INSUFFICIENT_RESOURCES =
      new SIRequestFailureType("INSUFFICIENT_RESOURCES");
    DATA_UNAVAILABLE = new SIRequestFailureType("DATA_UNAVAILABLE");
    UNKNOWN          = new SIRequestFailureType("UNKNOWN");
  }
}
