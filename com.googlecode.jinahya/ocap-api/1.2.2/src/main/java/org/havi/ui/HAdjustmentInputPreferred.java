package org.havi.ui;

/*
 * Copyright 2000-2003 by HAVi, Inc. Java is a trademark of Sun
 * Microsystems, Inc. All rights reserved.  
 */


/**
 * A component which implements <code>HAdjustmentInputPreferred</code> indicates that this component expects to receive  {@link org.havi.ui.event.HAdjustmentEvent}  input events. <p> The system must provide a means of generating <code>HAdjustmentEvent</code> events as necessary.  For platforms with a restricted number of physical keys this may involve a &quot;virtual keyboard&quot; or similar mechanism. The system might use the information returned by the method {@link HOrientable#getOrientation}  of the super interface to select appropriate key mappings for this event. The mechanisms to generate this event shall not be effective while the component is disabled (see  {@link HComponent#setEnabled} ). <p> Widgets of HAVi compliant applications implementing the <code>HAdjustmentInputPreferred</code> interface must have  {@link HComponent}  in their inheritance tree. <p> Note that the <code>java.awt.Component</code> method <code>isFocusTraversable</code> shall always return true for a <code>java.awt.Component</code> implementing this interface.
 */
public interface HAdjustmentInputPreferred extends HOrientable
{
    /**
	 * Get the adjustment mode for this <code>HAdjustmentInputPreferred</code>. If the returned value is <code>true</code> the component is in adjustment mode, and its value may be changed on receipt of  {@link org.havi.ui.event.HAdjustmentEvent#ADJUST_LESS}  and {@link org.havi.ui.event.HAdjustmentEvent#ADJUST_MORE}  events. <p> The component is switched into and out of adjustment mode on receiving  {@link org.havi.ui.event.HAdjustmentEvent#ADJUST_START_CHANGE} {@link org.havi.ui.event.HAdjustmentEvent#ADJUST_END_CHANGE} events. Note that these events are ignored, if the component is disabled.
	 * @return  true if this component is in adjustment mode, false  otherwise.
	 * @see  HComponent#setEnabled
	 * @uml.property  name="adjustMode"
	 */
    public boolean getAdjustMode();

    /**
	 * Set the adjustment mode for this <code>HAdjustmentInputPreferred</code>. <p> This method is provided for the convenience of component implementors. Interoperable applications shall not call this method. It cannot be made protected because interfaces cannot have protected methods. <p> Calls to this method shall be ignored, if the component is disabled.
	 * @param adjust  true to switch this component into adjustment mode,  false otherwise.
	 * @see HAdjustmentInputPreferred#getAdjustMode  
	 * @see  HComponent#setEnabled
	 * @uml.property  name="adjustMode"
	 */
    public void setAdjustMode(boolean adjust);

    /**
     * Process an <code>HAdjustmentEvent</code>
     * sent to this <code>HAdjustmentInputPreferred</code>.
     * Widgets implementing this interface
     * shall ignore <code>HAdjustmentEvents</code>, while the
     * component is disabled.
     * 
     * @param evt the <code>HAdjustmentEvent</code> to process.
     * @see HComponent#setEnabled
     */
    public void processHAdjustmentEvent(org.havi.ui.event.HAdjustmentEvent evt);
}









