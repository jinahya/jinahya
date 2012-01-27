/*
 * @(#)MediaSelectListener.java	1.10 00/10/09
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
import java.util.*;


/**
 * The <code>MediaSelectListener</code> interface is implemented by
 * applications in order to receive notification of selection
 * operations on a <code>MediaSelectControl</code>.
 */
public interface MediaSelectListener extends java.util.EventListener {

    /**
     * Notifies the <code>MediaSelectListener</code> that a selection
     * has completed.
     *
     * @param event MediaSelectEvent describing the completion of a
     * selection operation.
     **/
    public void selectionComplete(MediaSelectEvent event);

}
