/*
 * @(#)MediaHandler.java	1.5 98/03/28
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

import javax.media.protocol.DataSource;
import java.io.IOException;

/**
 * <code>MediaHandler</code> is the base interface for objects
 * that read and manage media content delivered from a
 * <code>DataSource</code>.
 * <p>
 * 
 * There are currently two supported types of <code>MediaHandler/code>:
 * <code>Player</code> and <code>MediaProxy</code>.
 **/
public interface MediaHandler {

    /**
     * Set the media source the <code>MediaHandler</code>
     * should use to obtain content.
     *
     * @param source The <code>DataSource</code> used
     * by this <code>MediaHandler</code>.
     *
     * @exception IOException Thrown if there is an error
     * using the <code>DataSource</code>
     *
     * @exception IncompatibleSourceException Thrown if
     * this <code>MediaHandler</code> cannot make use
     * of the <code>DataSource</code>.
     */
    public void setSource(DataSource source)
	throws IOException, IncompatibleSourceException;

}
