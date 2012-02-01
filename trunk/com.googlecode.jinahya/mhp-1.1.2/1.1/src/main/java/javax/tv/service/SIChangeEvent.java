/*
 * @(#)SIChangeEvent.java	1.25 00/08/28
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

import java.util.EventObject;


/**
 * <code>SIChangeEvent</code> objects are sent to
 * <code>SIChangeListener</code> instances to signal detected changes
 * in the SI database.<p>
 *
 * Note that while the SI database may detect changes, notification of
 * which specific <code>SIElement</code> has changed is not guaranteed.
 * The entity reported by the method <code>getSIElement()</code> will
 * be either:
 * <ul>
 * <li>The specific SI element that changed, or<p>
 * <li>An SI element that contains, however indirectly, the specific SI
 * element that changed, or<p>
 * <li><code>null</code>, if the specific changed element is unknown.
 * </ul>
 * 
 * The level of specificity provided by the change mechanism is
 * entirely dependent on the capabilities and current resources of the
 * implementation.
 *
 * <code>SIChangeEvent</code> instances also report the kind of change
 * that occurred to the SI element, via the method
 * <code>getChangeType()</code>:
 * <ul>
 *
 * <li>An <code>SIChangeType</code> of <code>ADD</code> indicates that
 * the reported SI element is new in the database.<p>
 *
 * <li>An <code>SIChangeType</code> of <code>REMOVE</code> indicates
 * that the reported SI element is defunct and no longer cached by the
 * database.  The results of subsequent method invocations on the
 * removed SIElement are undefined.<p>
 *
 * <li>An <code>SIChangeType</code> of <code>MODIFY</code> indicates
 * that the data encapsulated by the reported SI element has changed.
 * 
 * </ul>
 *
 * In the event that the SIElement reported by this event is not
 * the actual element that changed in the broadcast (i.e. it is
 * instead a containing element or <code>null</code>), the
 * <code>SIChangeType</code> will be <code>MODIFY</code>.
 * Individual SI element changes are reported only once, i.e.,
 * a change to an SI element is not also reported as a change
 * to any containing (or "parent") SI elements.
 *
 * @see #getSIElement
 * @see #getChangeType
 */
public abstract class SIChangeEvent extends EventObject {

   SIChangeType type;
   SIElement element;

  /**
   * Constructs an <code>SIChangeEvent</code> object.
   *
   * @param source The entity in which the change occurred.
   *
   * @param type The type of change that occurred.
   *
   * @param e The <code>SIElement</code> that changed, or
   * <code>null</code> if this is unknown.
   */
  public SIChangeEvent(Object source, SIChangeType type, SIElement e) {
    	super(source);
	this.type = type;
	this.element = e;
  }
  
  /**
   * Reports the <code>SIElement</code> that changed.<p>
   * 
   * This method may return <code>null</code>, since it is not
   * guaranteed that the SI database can or will determine which
   * element in a particular table changed.
   *
   * @return The <code>SIElement</code> that changed, or
   * <code>null</code> if this is unknown.  */
  public SIElement getSIElement() {
	return element;
  }
  
  /**
   * Indicates the type of change that occurred.
   *
   * @return The type of change that occurred.
   */
  public SIChangeType getChangeType() {
	return type;
  }
}
