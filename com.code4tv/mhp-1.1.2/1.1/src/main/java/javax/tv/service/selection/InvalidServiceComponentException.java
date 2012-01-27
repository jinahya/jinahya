/*
 * @(#)InvalidServiceComponentException.java	1.2 00/08/15
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

package javax.tv.service.selection;

import javax.tv.locator.Locator;


/**
 * This exception is thrown when one or more service components are
 * not valid for usage in a particular context.  If multiple service
 * components are simultaneously invalid, this exception reports
 * one of them.
 */
public class InvalidServiceComponentException
             extends ServiceContextException {

  private Locator component = null;
	
  /**
   * Constructs an <code>InvalidServiceComponentException</code>
   * with no detail message.
   *
   * @param component A locator indicating the offending service
   * component.
   */
  public InvalidServiceComponentException(Locator component) {
	super();
	this.component = component;
  }
  
  /**
   *
   * Constructs an <code>InvalidServiceComponentException</code> with
   * the specified detail message.
   *
   * @param component A locator indicating the offending service
   * component.
   *
   * @param reason The reason why this component is invalid.
   */
  public InvalidServiceComponentException(Locator component, String reason) {
	super(reason);
	this.component = component;
  }

  /**
   * Reports the offending service components.
   *
   * @return A locator indicating the service component
   * that caused the exception.
   */
  public Locator getInvalidServiceComponent() { return component; }
}
