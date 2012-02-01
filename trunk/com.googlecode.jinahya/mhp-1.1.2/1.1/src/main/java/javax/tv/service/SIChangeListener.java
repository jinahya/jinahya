/*
 * @(#)SIChangeListener.java	1.13 00/09/29
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

package javax.tv.service;

import java.util.EventListener;

/**
 * This is the super-interface of the interfaces by which applications
 * may receive notification of changes to <code>SIElement</code> data.
 * Applications that wish to receive notification of changes to
 * <code>SIElement</code> data must implement the appropriate
 * <code>SIChangeListener</code> sub-interface.
 */
public interface SIChangeListener extends EventListener {
}
