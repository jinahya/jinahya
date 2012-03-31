/*
 * @(#)ServiceIterator.java	1.10 00/07/03
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

package com.sun.tv.si;

import java.util.*;
import javax.tv.service.navigation.*;
import java.util.NoSuchElementException;
import javax.tv.service.Service;

/**
 * <code>ServiceIterator</code> permits iteration over an ordered
 * list of <code>Service</code> objects.  Applications may use the
 * <code>ServiceIterator</code> interface to browse a
 * <code>ServiceList</code> forward or backward.<p>
 *
 * Upon initial usage, <code>hasPrevious()</code> will return
 * <code>false</code> and <code>nextService()</code> will return the
 * first <code>Service</code> in the list, if present.
 *
 * @see ServiceList */
public class ServiceIteratorImpl implements ServiceIterator {

	int theIndex;
	Vector services;

 /**
  *
  */
  public ServiceIteratorImpl(Vector list) {
  	if(list != null) {
  		services = list;
  	} else {
		services = new Vector();
  	}
  	theIndex = -1;
  }

  /**
   *
   * Resets the iterator to the beginning of the list, such that
   * <code>hasPrevious</code> returns <code>false</code>.
   *
   * */
  public void toBeginning() {
	theIndex = -1;
  }

  /**
   *
   * Sets the iterator to the end of the list, such that
   * <code>hasNext</code> returns <code>false</code>.
   */
  public void toEnd() {
	theIndex = services.size();
  }


 /**
  *
  * Reports the next <code>Service</code> object in the list.  This
  * method may be called repeatedly to iterate through the list.
  *
  * @return The <code>Service</code> object at the next position in
  * the list.
  *
  * @throws NoSuchElementException If the iteration has no next
  * <code>Service</code>.
  */
  public Service nextService() {
	if (hasNext() == false) {
		String msg = "next index (" + theIndex + ") is out of range.";
		throw new NoSuchElementException(msg);
	}
	theIndex++;
	return (Service)services.elementAt(theIndex);
  }

 /**
  *
  * Reports the previous <code>Service</code> object in the list.
  * This method may be called repeatedly to iterate through the list
  * in reverse order.
  *
  * @return The <code>Service</code> object at the previous position
  * in the list.
  *
  * @throws NoSuchElementException If the iteration has no previous
  * <code>Service</code>.
  */
  public Service previousService() {
	if (hasPrevious() == false) {
		String msg = "next index (" + theIndex + ") is out of range.";
		throw new NoSuchElementException(msg);
	}
	theIndex--;
	return (Service)services.elementAt(theIndex);
  }

  /**
   * Tests if there is a <code>Service</code> in the next position in
   * the list.
   * 
   * @return <code>true</code> if there is a <code>Service</code> in
   * the next position in the list; <code>false</code> otherwise.
   */
  public boolean hasNext() {
	int index = theIndex + 1;
	return !(index < 0 || index > services.size() - 1);
  }

  /**
   *
   * Tests if there is a <code>Service</code> in the previous
   * position in the list.
   * 
   * @return <code>true</code> if there is a <code>Service</code> in
   * the previous position in the list; <code>false</code> otherwise.
   */
  public boolean hasPrevious() {
	int index = theIndex - 1;
	return !(index < 0 || index > services.size() - 1);
  }
}
