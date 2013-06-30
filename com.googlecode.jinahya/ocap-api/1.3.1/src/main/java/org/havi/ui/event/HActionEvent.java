package org.havi.ui.event;

/*
 * Copyright 2000-2003 by HAVi, Inc. Java is a trademark of Sun
 * Microsystems, Inc. All rights reserved.  
 */


import org.havi.ui.HActionable;

/**
   An {@link org.havi.ui.event.HActionEvent HActionEvent} event is used to
   interact with a component implementing the {@link
   org.havi.ui.HActionInputPreferred HActionInputPreferred} interface
   as follows:

   <p><ul>

   <li> An {@link org.havi.ui.event.HActionEvent HActionEvent} event may be sent
   from the HAVi system to the component to cause the component to
   become actioned.

   <li> An {@link org.havi.ui.event.HActionEvent HActionEvent} event is sent from
   the component to all registered {@link
   org.havi.ui.event.HActionListener HActionListeners} whenever the
   component is actioned.
   
   </ul><p>

   All interoperable HAVi components which expect to receive {@link
   org.havi.ui.event.HActionEvent HActionEvent} events must implement the
   {@link org.havi.ui.HActionInputPreferred
   HActionInputPreferred} interface.
   
 */
public class HActionEvent 
    extends java.awt.event.ActionEvent
{

    /**
     * Constructs an {@link org.havi.ui.event.HActionEvent
     * HActionEvent}.
     * 
     * @param source The {@link org.havi.ui.HActionInputPreferred 
     * HActionInputPreferred} component which has been actioned. 
     * @param id The event id of the {@link
     * org.havi.ui.event.HActionEvent HActionEvent} generated
     * by the {@link org.havi.ui.HActionInputPreferred HActionInputPreferred}
     * component. This is the value that will be returned by the
     * event object's <code>getID</code> method.  
     * @param command A String which is used as the action command
     * string for this event.
     */
    public HActionEvent(org.havi.ui.HActionInputPreferred source, int id, String command)
    {
	super(source, id, command);
    }

    /**
     * Retrieve the action command string associated with this event.
     *
     * @return the String which was used as the action command
     * string when this event was constructed.
     */
    public String getActionCommand() 
    {
        return("");
    }

    /**
     * Returns any modifiers held down during this action event.
     * <p>
     * Modifiers are not used for <code>HActionEvent</code>s with the
     * HAVi platform. Interoperable HAVi applications shall not use the
     * return value of this method.
     * 
     * @return an implementation-specific value. 
     */
    public int getModifiers() 
    {
        return(0);
    }



}


