/*
 * @(#)MediaProxy.java	1.11 98/03/28
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

/*
 * Typically, a <code>MediaProxy</code> reads a text configuration file
 * that contains all of the information needed to 
 * make a connection to a server and obtain media data.
 * To produce a <code>Player</code> from a <code>MediaLocator</code>
 * referencing the configuration file,
 * <code>Manger</code>:
 * <ul>
 * <li>constructs a <code>DataSource</code>
 * for the protocol described by the <code>MediaLocator</code>
 * <li>constructs a <code>MediaProxy</code> to read
 * the configuration file using the content-type of the
 * <code>DataSource</code>
 * <li> obtains a new <code>DataSource</code>
 * from the <code>MediaProxy</code>
 * <li>constructs the <code>Player</code> using the content-type of the new
 * <code>DataSource</code>
 * </ul>
 */
public interface MediaProxy extends MediaHandler {

    /**
     * Obtain the new <code>DataSource</code>.
     * The <code>DataSource</code> is already connected.
     *
     * @exception IOException Thrown when if there are IO
     * problems in reading the the original or new
     * <code>DataSource</code>.
     *
     * @exception NoDataSourceException Thrown if this proxy
     * can't produce a <code>DataSource</code>.
     * 
     * @return the new <code>DataSource</code> for this content.
     */
    public DataSource getDataSource()
	throws IOException, NoDataSourceException;

}
