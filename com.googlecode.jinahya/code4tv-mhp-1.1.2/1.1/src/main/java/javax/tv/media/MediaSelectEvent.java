/*
 * @(#)MediaSelectEvent.java	1.15 00/08/26
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

package javax.tv.media;
import javax.tv.locator.*;
import javax.media.Controller;


/**
 * <code>MediaSelectEvent</code> is the base class of events sent to
 * <code>MediaSelectListener</code> instances.
 *
 * @see MediaSelectListener
 **/
public abstract class MediaSelectEvent extends java.util.EventObject {

    private Locator selection[];
    
    /**
     * Creates a new <code>MediaSelectEvent</code>.
     * 
     * @param controller The Controller that generated this event.
     * @param selection The <code>Locator</code> instances on which
     * selection was attempted.
     */
    public MediaSelectEvent(Controller controller, Locator[] selection) {
	super(controller);
	this.selection = selection;
    }

    /**
     * Reports the Controller that generated this event.
     *
     * @return The Controller that generated this event.
     */
    public Controller getController() {
	return (Controller)getSource();
    }

    /**
     * Reports the selection that caused this event.
     *
     * @return The selection that caused this event.
     */
    public Locator[] getSelection() {
	return selection;
    }
}

