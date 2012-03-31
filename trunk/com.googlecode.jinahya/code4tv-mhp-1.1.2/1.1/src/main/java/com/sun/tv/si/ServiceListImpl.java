/*
 * @(#)ServiceListImpl.java	1.6 99/10/21
 *
 * Copyright 2000 by Sun Microsystems, Inc.,
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

/**
 *
 * <code>ServiceList</code> represents a collection of
 * <code>Service</code> objects based on a specific grouping rule
 * defined by a <code>ServiceFilter</code>.
 * <code>ServiceList</code> extends <code>ServiceIterator</code>
 * to permit applications to browse its contents.
 *
 * @see javax.tv.service.Service
 * @see ServiceFilter
 */

/**
 * <code>ServiceIterator</code> maintains an ordered list of
 * <code>Service</code> objects numbered <code>0..N-1</code> and an
 * index value to the "current" service.  Applications may use the
 * <code>ServiceIterator</code> interface to browse a collection of
 * <code>Service</code> objects forward or backward.<p>
 *
 * The iterator index may range in value from <code>0</code> to
 * <code>N-1</code>, where <code>N</code> is the length of the list as
 * returned by the method <code>getSize</code>.  The initial value of
 * the index is <code>0</code>.
 *
 * @see ServiceList
 * @see #getSize
 */

import java.util.*;
import com.sun.tv.*;
import com.sun.tv.util.*;
import javax.tv.locator.*;
import javax.tv.service.*;
import javax.tv.service.navigation.*;
import java.util.NoSuchElementException;

public class ServiceListImpl implements ServiceList {

	Vector services;

	public ServiceListImpl(Service[] list) {
		services = new Vector();
		if(list != null) {
			for (int i = 0; i < list.length; i++) {
				services.addElement(list[i]);
			}
		}
	 }

	public ServiceListImpl(Vector list) {
		if(list != null) {
			services = list;
		} else {
			services = new Vector();
		}
	 }

	private boolean equals(Service s1, Service s2) {
		if (s1 == null || s2 == null)
			return false;
		return s1.getName().equalsIgnoreCase(s2.getName());
	}

 /**
  * This method performs a <code>toBeginning</code> operation on the
  * iterator.
  */
  public ServiceList sortByName() {
	if (services == null || services.size() == 0) 
		return new ServiceListImpl(new Service[0]);

	Object o[] = new Object[services.size()];

	for (int i = 0; i < o.length; i++) {
	    o[i] = services.elementAt(i);
	}

	QuickSort.sort(o, new NameCompare());

	Service s[] = new Service[o.length];
 	for (int i = 0; i < o.length; i++) {
 		s[i] = (Service)o[i];
 	}

	return new ServiceListImpl(s);
  }

 /**
  * Sorts the contents of the list by service number, in ascending
  * order.
  * 
  * @throws SortNotAvailableException If the contents cannot be
  * sorted by service number.
  *
  * If this method is successful, it performs a
  * <code>toBeginning</code> operation on the iterator.
  *
  * @see ServiceIterator#toBeginning
  */
  public ServiceList sortByNumber() throws SortNotAvailableException {
	if (services == null || services.size() == 0) 
		return new ServiceListImpl(new Service[0]);

	Object o[] = new Object[services.size()];

	for (int i = 0; i < o.length; i++) {
	    o[i] = services.elementAt(i);
	}

	QuickSort.sort(o, new NumberCompare());

	Service s[] = new Service[o.length];
 	for (int i = 0; i < o.length; i++) {
 		s[i] = (Service)o[i];
 	}

	return new ServiceListImpl(s);
  }

  
  /**
   * Reports the Service corresponding to the specified locator
   * if it is a member of this list.
   *
   * @param locator Specifies the <code>Service</code> to be searched for.
   *
   * @return Service The <code>Service</code> corresponding to
   * <code>locator</code>, or <code>null</code> if the
   * <code>Service</code> is not a member of this list.
   *
   * @throws InvalidLocatorException If <code>locator</code> does not
   * reference a valid <code>Service</code>.
   */
  public Service findService(Locator locator) throws InvalidLocatorException {
	if (locator == null) {
		throw new NullPointerException();
	}

	if (LocatorImpl.isService(locator) == false) {
		throw new InvalidLocatorException(locator);
	}

	String ef = locator.toExternalForm();

	for (int i = services.size()-1; i >=0; i--) {
		Service service = (Service)services.elementAt(i);
		if (ef.equalsIgnoreCase(service.getLocator().toExternalForm())) {
			return service;
		}
	}
	return null;
   }

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
   * <code>ServiceFilter<code> will be invoked for each
   * <code>Service</code> to be filtered using the same application
   * thread that invokes <code>createServiceList</code>.<p>
   *
   * @param filter A filter constraining the requested service list,
   * or <code>null</code>.
   *
   * @return A <code>ServiceList</code> object created based on the
   * specified filtering rules.
   *
   * @see ServiceFilter#accept
   */
  public ServiceList filterServices(ServiceFilter filter) {
	int count = 0;
	for (int i = 0; i < services.size(); i++) {
		Service service = (Service)services.elementAt(i);
		if (filter == null || filter.accept(service)) {
			count++; 
		}
	}

	Service list[] = new Service[count];

	count = 0;
	for (int i = 0; i < services.size(); i++) {
		Service service = (Service)services.elementAt(i);
		if (filter == null || filter.accept(service)) {
			list[count++] = service;
		}
	}

	return new ServiceListImpl(list);
  }

  /**
   * Generates an iterator on the <code>Service</code>s in this list.
   *
   * @return An iterator on the <code>Service</code>s in this list.
   */
  public ServiceIterator createServiceIterator() {
	return new ServiceIteratorImpl(services);
  }

  /**
   * Tests if the indicated <code>Service</code> object is contained
   * in the list.
   *
   * @param service The <code>Service</code> object for which to search.
   *
   * @return <code>true</code> if the specified <code>Service</code>
   * is member of the list; <code>false</code> otherwise.  */
  public boolean contains(Service service) {
	if (service == null) {
		throw new NullPointerException();
	}
	return (indexOf(service) != -1);
  }

  /**
   * Reports the position of the first occurrence of the
   * indicated <code>Service</code> object in the list.
   *
   * @param service The <code>Service</code> object for which to search.
   *
   * @return The index of the first occurrence of the service, or
   * <code>-1</code> if <code>service</code> is not contained in the list.
   */
  public int indexOf(Service service) {
	if (service == null) {
		throw new NullPointerException();
	}

	for( int i = 0; i < services.size(); i++) {
		if (equals((Service)services.elementAt(i), service)) { 
			return i;
		}
	}
	return -1;
  }


  /**
   * Reports the number of <code>Service</code> objects in the list.
   *
   * @return The number of <code>Service</code> objects in the list.
   */
  public int size() {
	return services.size();
  }
  
  
  /**
   * Reports the service at the specified index position.
   * 
   * @return The service at the specified index.
   *
   * @throws IndexOutOfBoundsException If <code>index</code> &< 0 or
   * <code>index</code> &> <code>size()-1</code>.
   */
  public Service getService(int index) {
	if (index < 0 || index >= services.size()) {
		throw new IndexOutOfBoundsException("index < 0 || index >= " + services.size());
	}
	return (Service)services.elementAt(index);
  }
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
  public boolean equals(Object o)
  {
      if (!(o instanceof ServiceList)) {
         return false;
      }
      ServiceList list = (ServiceList) o;
      if (list.size() != this.size()) {
         return false;
      }

      for (int i = 0; i < this.size(); i++ ) {
         if (!(this.getService(i).equals(list.getService(i)))) {
            return false;
         }
      }

      return true;
   }

  /**
   * Provides the hash code value for this <code>ServiceList</code>.
   * Two <code>ServiceList</code> objects that are equal will have
   * the same hash code.
   *
   * @return The hash code value of this <code>ServiceList</code>.
   */
  public int hashCode() {
     int hashcode = 0;
     for (int i = 0; i < this.size(); i++ ) {
        hashcode = (hashcode ^ (this.getService(i).hashCode()));
     }
     return hashcode;
  } 
}

class NameCompare implements CompareInterface {
  public int compareTo(Object a, Object b) {
  	String sa = (a == null) ? " " : ((Service)a).getName();
  	String sb = (b == null) ? " " : ((Service)b).getName();
  	if (sa == null) {
  		sa = " ";
  	}
  	if (sb == null) {
  		sb = " ";
  	}
  	return sa.compareTo(sb);
  }
}

class NumberCompare implements CompareInterface {
  public int compareTo(Object a, Object b) {
  	int sa = (a == null) ? 0 : ((ServiceNumber)a).getServiceNumber();
  	int sb = (b == null) ? 0 : ((ServiceNumber)b).getServiceNumber();
	if (sa != sb) {
		return sa - sb;
	}

  	int ma = (a == null) ? 0 : ((ServiceMinorNumber)a).getMinorNumber();
  	int mb = (b == null) ? 0 : ((ServiceMinorNumber)b).getMinorNumber();
  	return ma - mb;
  }
}
