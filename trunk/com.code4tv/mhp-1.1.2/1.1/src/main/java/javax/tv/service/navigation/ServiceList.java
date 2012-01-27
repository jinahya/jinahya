/*
 * @(#)ServiceList.java	1.27 00/10/09
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

import javax.tv.locator.*;
import javax.tv.service.Service;


/**
 *
 * <code>ServiceList</code> represents an ordered list of
 * <code>Service</code> objects based on a specific grouping rule
 * defined by a <code>ServiceFilter</code>.  The objects in a
 * <code>ServiceList</code> are numbered from 0 to <code>size()
 * -1</code>.
 *
 * A <code>ServiceList</code> is <i>immutable</i>.  In other words,
 * once a <code>ServiceList</code> instance is created, the elements
 * in the list and their order will never change.  All classes that
 * implement the <code>ServiceList</code> interface are required to
 * maintain this property.
 *
 * @see javax.tv.service.Service
 * @see ServiceFilter
 * @see #size
 * */
public interface ServiceList {
  
  /**
   * Generates a new <code>ServiceList</code> containing the
   * same elements as the current list, sorted in ascending
   * order by service name.
   *
   * @return A <code>ServiceList</code> sorted by service name.
   * 
   * @see Service#getName
   */
  public ServiceList sortByName();


 /**
  * Generates a new <code>ServiceList</code> containing the
  * same elements as the current list, sorted in ascending
  * order by service number.
  * 
  * @return A <code>ServiceList</code> sorted by service number.
  * 
  * @throws SortNotAvailableException If any of the
  * <code>Service</code> objects in this <code>ServiceList</code>
  * do not implement the <code>ServiceNumber</code> interface.
  *
  * @see javax.tv.service.ServiceNumber
  */
  public ServiceList sortByNumber() throws SortNotAvailableException;
	
  
  /**
   * Reports the <code>Service</code> corresponding to the specified
   * locator if it is a member of this list.
   *
   * @param locator Specifies the <code>Service</code> to be searched for.
   *
   * @return The <code>Service</code> corresponding to
   * <code>locator</code>, or <code>null</code> if the
   * <code>Service</code> is not a member of this list.
   *
   * @throws InvalidLocatorException If <code>locator</code> does not
   * reference a valid <code>Service</code>.  */
  public Service findService(Locator locator)
    throws InvalidLocatorException;
  

  /**
   *
   * Creates a new <code>ServiceList</code> object that is a subset of
   * this list, based on the conditions specified by a
   * <code>ServiceFilter</code> object. This method may be used to
   * generate increasingly specialized lists of <code>Service</code>
   * objects based on multiple filtering criteria.  If the filter is
   * <code>null</code>, the resulting <code>ServiceList</code> will be
   * a duplicate of this list.  <p>
   *
   * Note that the <code>accept</code> method of the given
   * <code>ServiceFilter</code> will be invoked for each
   * <code>Service</code> to be filtered using the same application
   * thread that invokes this method.
   *
   * @param filter A filter constraining the requested service list,
   * or <code>null</code>.
   *
   * @return A <code>ServiceList</code> object created based on the
   * specified filtering rules.
   *
   * @see ServiceFilter#accept */
  public ServiceList filterServices(ServiceFilter filter);


  /**
   * Generates an iterator on the <code>Service</code> elements
   * in this list.
   *
   * @return A <code>ServiceIterator</code> on the
   * <code>Service</code>s in this list.
   */
  public ServiceIterator createServiceIterator();

  /**
   * Tests if the indicated <code>Service</code> object is contained
   * in the list.
   *
   * @param service The <code>Service</code> object for which to search.
   *
   * @return <code>true</code> if the specified <code>Service</code>
   * is member of the list; <code>false</code> otherwise.  */
  public boolean contains(Service service);

  /**
   * Reports the position of the first occurrence of the
   * indicated <code>Service</code> object in the list.
   *
   * @param service The <code>Service</code> object for which to search.
   *
   * @return The index of the first occurrence of the
   * <code>service</code>, or <code>-1</code> if <code>service</code>
   * is not contained in the list.
   */
  public int indexOf(Service service);


  /**
   * Reports the number of <code>Service</code> objects in the list.
   *
   * @return The number of <code>Service</code> objects in the list.
   */
  public int size();
  
  
  /**
   * Reports the <code>Service</code> at the specified index position.
   * 
   * @param index A position in the <code>ServiceList</code>.
   * 
   * @return The <code>Service</code> at the specified index.
   *
   * @throws IndexOutOfBoundsException If <code>index</code> < 0 or
   * <code>index</code> > <code>size()-1</code>.
   */
  public Service getService(int index);

  /**
   * Compares the specified object with this <code>ServiceList</code>
   * for equality. Returns <code>true</code> if and only if the
   * specified object is also a <code>ServiceList</code>, both lists
   * have the same size, and all corresponding pairs of elements in
   * the two lists are equal.  (Two elements e1 and e2 are equal if
   * (e1==null ? e2==null : e1.equals(e2)).) In other words, two lists
   * are defined to be equal if they contain the same elements in the
   * same order.
   *
   * @param o The object to be compared for equality with this list.
   *
   * @return <code>true</code> if the specified object is equal to
   * this list; <code>false</code> otherwise.
   */
  public boolean equals(Object o);

  /**
   * Provides the hash code value for this <code>ServiceList</code>.
   * Two <code>ServiceList</code> objects that are equal will have
   * the same hash code.
   * 
   * @return The hash code value of this <code>ServiceList</code>.
   */
  public int hashCode();
}


