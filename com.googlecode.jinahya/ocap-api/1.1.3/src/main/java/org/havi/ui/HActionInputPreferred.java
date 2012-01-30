
package org.havi.ui;

/*
 * Copyright 2000-2003 by HAVi, Inc. Java is a trademark of Sun
 * Microsystems, Inc. All rights reserved.  
 */


/**
 * A component which implements   {@link org.havi.ui.HActionInputPreferred   HActionInputPreferred}   indicates that this component expects to receive   {@link org.havi.ui.event.HActionEvent   HActionEvent}   input events.<p> All interoperable implementations of the   {@link org.havi.ui.HActionInputPreferred   HActionInputPreferred}  interface must extend   {@link org.havi.ui.HComponent   HComponent}  . <p> Note that the <code>java.awt.Component</code> method <code>isFocusTraversable</code> should always return true for a <code>java.awt.Component</code> implementing this interface.
 */

public interface HActionInputPreferred 
{
    /**
     * Process an <code>HActionEvent</code> sent to this HActionInputPreferred.
     * 
     * @param evt the <code>HActionEvent</code> to process. 
     */
    public void processHActionEvent(org.havi.ui.event.HActionEvent evt);


     /**
      * Gets the command name for the <code>HActionEvent</code>
      * event fired by this HActionInputPreferred. If this HActionInputPreferred
      * has no action command then an empty string shall be returned.
      * 
      * @return A <code>String</code> representing the command name of the 
      * HActionEvent fired by this HActionInputPreferred.
      */
    public java.lang.String getActionCommand();


}









