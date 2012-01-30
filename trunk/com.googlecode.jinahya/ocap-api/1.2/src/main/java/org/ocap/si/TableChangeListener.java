/*
 * TableChangeListener.java
 */

package org.ocap.si;

import javax.tv.service.*;
/**
 * This interface is implemented by an application for notification of 
 * change to a table. The ProgramAassociationTableManager and 
 * the ProgramMapTableManager call the TableChangeListener.notifyChange 
 * method with the concrete event sub class of 
 * the org.javax.tv.service.SIChangeEvent. The concrete event sub class 
 * of the SIChangeEvent depends on the implement.
 */
public interface TableChangeListener extends SIChangeListener
{
/**
 * This method notifies that the SI table has changed.
 *
 * @param event  An event instance that notifies the SI update.
 */
  public void notifyChange(javax.tv.service.SIChangeEvent event);
}


