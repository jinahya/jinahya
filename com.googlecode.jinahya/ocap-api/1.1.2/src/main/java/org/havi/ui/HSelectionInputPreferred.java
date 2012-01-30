
package org.havi.ui;

/*
 * Copyright 2000-2003 by HAVi, Inc. Java is a trademark of Sun
 * Microsystems, Inc. All rights reserved.  
 */


/**
 * A component which implements  {@link org.havi.ui.HSelectionInputPreferred  HSelectionInputPreferred}  indicates that this component expects to receive  {@link org.havi.ui.event.HItemEvent  HItemEvent}  input events. <p> The system must provide a means of generating <code>HItemEvent</code> events as necessary. For platforms with a restricted number of physical keys this may involve a &quot;virtual keyboard&quot; or similar mechanism. The system might use the information returned by the method  {@link HOrientable#getOrientation}  of the super interface to select appropriate key mappings for this event. The mechanisms to generate this event shall not be effective while the component is disabled (see  {@link HComponent#setEnabled} ).   <p> Widgets of HAVi compliant applications implementing the HSelectionInputPreferred interface must have HComponent in their inheritance tree. <p> Note that the <code>java.awt.Component</code> method <code>isFocusTraversable</code> shall always return true for a <code>java.awt.Component</code> implementing this interface.
 */

public interface HSelectionInputPreferred extends HOrientable
{
    /**
	 * Get the selection mode for this  {@link org.havi.ui.HSelectionInputPreferred  HSelectionInputPreferred} . If the returned value is <code>true</code> the component is in selection mode, and the selection may be changed.  <p> The component is switched into and out of selection mode on receiving  {@link org.havi.ui.event.HItemEvent#ITEM_START_CHANGE  ITEM_START_CHANGE}  and  {@link org.havi.ui.event.HItemEvent#ITEM_END_CHANGE  ITEM_END_CHANGE}  events. Note that these events are ignored, if the component is disabled.
	 * @return  true if this component is in selection mode, false  otherwise.
	 * @see  HComponent#setEnabled
	 * @uml.property  name="selectionMode"
	 */
    public boolean getSelectionMode();

    /**
	 * Set the selection mode for this  {@link org.havi.ui.HSelectionInputPreferred  HSelectionInputPreferred} .  <p> This method is provided for the convenience of component implementors. Interoperable applications shall not call this method. It cannot be made protected because interfaces cannot have protected methods. <p> Calls to this method shall be ignored, if the component is disabled.
	 * @param edit  true to switch this component into selection mode,  false otherwise.
	 * @see HComponent#setEnabled
	 * @see  HSelectionInputPreferred#getSelectionMode
	 * @uml.property  name="selectionMode"
	 */
    public void setSelectionMode(boolean edit);

    /**
     * Process an {@link org.havi.ui.event.HItemEvent
     * HItemEvent} sent to this {@link org.havi.ui.HSelectionInputPreferred
     * HSelectionInputPreferred}.
     * <p>
     * Widgets implementing this interface shall ignore
     * <code>HItemEvent</code>a, while the component is disabled.
     * 
     * @param evt the {@link org.havi.ui.event.HItemEvent
     * HItemEvent} to process. 
     * @see HComponent#setEnabled
     */
    public void processHItemEvent(org.havi.ui.event.HItemEvent evt);
}









