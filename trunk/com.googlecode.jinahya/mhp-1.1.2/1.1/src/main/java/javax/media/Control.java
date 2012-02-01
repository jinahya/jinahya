/*
 * @(#)Control.java	1.15 98/03/28
 *
 * Copyright 1996-1998 by Sun Microsystems, Inc.,
 * 901 San Antonio Road, Palo Alto, California, 94303, U.S.A.
 * All rights reserved.
 *
 * This software is the confidential and proprietary information
 * of Sun Microsystems, Inc. ("Confidential Information").  You
 * shall not disclose such Confidential Information and shall use
 * it only in accordance with the terms of the license agreement
 * you entered into with Sun.
 */

package javax.media;

import java.awt.Component;

/**
 * The base interface for processing <CODE>Control</CODE> objects.
 *
 * @version 1.15, 98/03/28
 */

public interface Control {
    
    /**
     * Get the <code>Component</code> associated with this
     * <code>Control</code> object.
     * For example, this method might return
     * a slider for volume control or a panel containing radio buttons for 
     * CODEC control.
     * The <code>getControlComponent</code> method can return
     * <CODE>null</CODE> if there is no GUI control for
     * this <code>Control</code>.
     */
    public Component getControlComponent();
}
