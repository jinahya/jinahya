/*
 * @(#)Controls.java	1.5 98/03/28
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

package javax.media.protocol;

/**
 * 
 * <code>Controls</code> provides an interface for
 * obtaining objects by interface or class name.
 * This is useful in the case where support for a particular
 * interface cannot be determined at runtime, or where a
 * different object is required to implement the behavior.
 * The <code>object</code> returned from <code>getControl</code>
 * is assumed to control the <code>object</code> that
 * <code>getControl</code> was invoked on.
 **/
public interface Controls {

    /**
     * Obtain the collection of objects that
     * control the object that implements this interface.
     * <p>
     *
     * If no controls are supported, a zero length
     * array is returned.
     *
     * @return the collection of object controls
     */
    public Object[] getControls();

    /**
     * Obtain the object that implements the specified
     * <code>Class</code> or <code>Interface</code>
     * The full class or interface name must be used.
     * <p>
     * 
     * If the control is not supported then <code>null</code>
     * is returned.
     *
     * @return the object that implements the control,
     * or <code>null</code>.
     */
    public Object getControl(String controlType);
    
}
