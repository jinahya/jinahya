package org.havi.ui.event;

/*
 * Copyright 2000-2003 by HAVi, Inc. Java is a trademark of Sun
 * Microsystems, Inc. All rights reserved.  
 */



/**
   An {@link org.havi.ui.event.HFocusEvent HFocusEvent} event is used to
   interact with a component implementing the {@link
   org.havi.ui.HNavigationInputPreferred HNavigationInputPreferred}
   interface as follows:

   <p><ul>

   <li> An {@link org.havi.ui.event.HFocusEvent HFocusEvent} event may be sent
   from the HAVi system to the component to inform the component that
   it has gained or lost the input focus, or that it should transfer
   focus to another component.

   <li> An {@link org.havi.ui.event.HFocusEvent HFocusEvent} event is sent
   from the component to all registered {@link
   org.havi.ui.event.HFocusListener HFocusListener} listeners whenever
   the component focus status changes. 
   
   </ul><p>

   Note that because the underlying focus mechanism is based on AWT,
   focus transfer events do not guarantee that another component will
   actually get focus, or that the current component will lose focus.

   <p>
   All interoperable HAVi components which expect to receive {@link
   org.havi.ui.event.HFocusEvent HFocusEvent} events must implement the
   {@link org.havi.ui.HNavigationInputPreferred
   HNavigationInputPreferred} interface.
   
 */
public class HFocusEvent 
    extends java.awt.event.FocusEvent
{
    /**
     * The first integer id in the range of event ids supported by the
     * {@link org.havi.ui.event.HFocusEvent HFocusEvent} class.
     */
    public static final int HFOCUS_FIRST = HTextEvent.TEXT_LAST + 1;
    
    /**
     * An event id which indicates that the component should transfer
     * focus to the component identified by the data returned from the
     * {@link org.havi.ui.event.HFocusEvent#getTransferId
     * getTransferId} method. 
     * <p>
     * If a component matching the data cannot be found the component
     * receiving this event should do nothing. 
     */
    public static final int FOCUS_TRANSFER = HFOCUS_FIRST;


     /**
     * The last integer id in the range of event ids supported by the
     * {@link org.havi.ui.event.HFocusEvent HFocusEvent} class.
     */
    public static final int HFOCUS_LAST = FOCUS_TRANSFER;

    /**
     * A constant returned from the {@link
     * org.havi.ui.event.HFocusEvent#getTransferId getTransferId}
     * method if the event id is not {@link
     * org.havi.ui.event.HFocusEvent#FOCUS_TRANSFER FOCUS_TRANSFER}.
     */
    public static final int NO_TRANSFER_ID = -1;

    /**
     * Constructs an {@link org.havi.ui.event.HFocusEvent
     * HFocusEvent}.
     * 
     * @param source The <code>java.awt.Component</code> component
     * which originated this event.
     * @param id The event id of the {@link
     * org.havi.ui.event.HFocusEvent HFocusEvent} generated
     * by the {@link org.havi.ui.HNavigable HNavigable}
     * component. This is the value that will be returned by the
     * event object's <code>getID</code> method.  
     */
    public HFocusEvent(java.awt.Component source, int id)
    {
	super(source, id, false);
    }

    /**
     * Constructs an {@link org.havi.ui.event.HFocusEvent
     * HFocusEvent}.
     * 
     * @param source The <code>java.awt.Component</code> component
     * which originated this event.
     * @param id The event id of the {@link
     * org.havi.ui.event.HFocusEvent HFocusEvent} generated
     * by the {@link org.havi.ui.HNavigable HNavigable}
     * component. This is the value that will be returned by the
     * event object's <code>getID</code> method.  
     * @param transfer a key which maps to the component to transfer
     * focus to, if the <code>id</code> parameter has the value {@link
     * org.havi.ui.event.HFocusEvent#FOCUS_TRANSFER
     * FOCUS_TRANSFER}. If the <code>id</code> parameter does not have
     * this value {@link org.havi.ui.event.HFocusEvent#NO_TRANSFER_ID
     * NO_TRANSFER_ID} is substituted for its value.  
     */
    public HFocusEvent(java.awt.Component source, int id, int transfer)
    {
	super(source, id, false);
    }

    /**
     * Returns whether or not this focus change event is a temporary
     * change. 
     * 
     * @return an implementation specific value. The HAVi UI does not
     * use temporary focus events and interoperable applications shall
     * not call this method.
     */
    public boolean isTemporary() 
    {
	return (false);
    }

    /**
     * Returns a key which maps to the component to transfer
     * focus to. 
     * 
     * @return a key which maps to the component to transfer focus to,
     * or {@link org.havi.ui.event.HFocusEvent#NO_TRANSFER_ID
     * NO_TRANSFER_ID} if the id of this event is not {@link
     * org.havi.ui.event.HFocusEvent#FOCUS_TRANSFER FOCUS_TRANSFER}.  
     * <p>
     * The return value of this function is used to pass key codes to
     * an {@link org.havi.ui.HNavigable HNavigable} to implement focus
     * transfer for HAVi UI components.
     */
    public int getTransferId()
    {
	return (-1);
    }

}


