/*
 * @(#)SIElementFilter.java	1.20 00/09/29
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

import javax.tv.service.*;
import javax.tv.service.guide.*;


/**
 * <code>SIElementFilter</code> represents a
 * <code>ServiceFilter</code> based on a particular
 * <code>SIElement</code> (such as a <code>TransportStream</code> or
 * <code>ProgramEvent</code>).  A <code>ServiceList</code> resulting
 * from this filter will include only <code>Service</code> objects
 * with one or more corresponding <code>ServiceDetails</code>,
 * <code>sd</code>, such that:
 * <ul>
 * <li> <code>sd</code> is contained by
 * the specified <code>SIElement</code>, or
 * <li><code>sd</code>
 * contains the specified <code>SIElement</code>
 * </ul>
 * -- according to the
 * type of <code>SIElement</code> provided.  Note that no guarantee
 * is made that every <code>SIElement</code> type is supported for
 * filtering.
 *
 * @see SIElement
 * @see ServiceList
 */
public final class SIElementFilter extends ServiceFilter {

  private SIElement element;
	
  /**
   * Constructs the filter based on a particular <code>SIElement</code>.
   *
   * @param element An <code>SIElement</code> indicating the services
   * to be included in a resulting service list.
   *
   * @throws FilterNotSupportedException If <code>element</code> is
   * not supported for filtering.  */
  public SIElementFilter(SIElement element) throws FilterNotSupportedException {
	if (element == null) {
		throw new NullPointerException();
	}
 	if ((element instanceof Service) 
            || (element instanceof ServiceDetails)
            || (element instanceof ServiceComponent)
            || (element instanceof ProgramEvent) ) {
		this.element = element;
	} else {
		throw new FilterNotSupportedException("SIElement not supported for the filtering"); 
	}
  }
  
  /**
   * Reports the <code>SIElement</code> used to create this filter.
   *
   * @return The <code>SIElement</code> used to create this filter.
   */
  public SIElement getFilterValue() {
	return element;
  }
  
  /**
   * Tests if the given service passes the filter.
   *
   * @param service An individual <code>Service</code> to be evaluated
   * against the filtering algorithm.
   *
   * @return <code>true</code> if <code>service</code> has a
   * corresponding <code>ServiceDetails</code> which contains or
   * is contained by the <code>SIElement</code> indicated
   * by the filter value; <code>false</code> otherwise.
   **/
  public boolean accept(Service service) {
	if (service == null) {
		throw new NullPointerException("accept: service == null");
	}
		
	if (element instanceof Service) {
		return service == element;
	} else if (element instanceof ServiceDetails) {
		return service == ((ServiceDetails)element).getService();
	} else if (element instanceof ServiceComponent) {
		return service == ((ServiceComponent)element).getService();
	} else if (element instanceof ProgramEvent) {
		return service == ((ProgramEvent)element).getService();
        }
	return false;
  }
}
