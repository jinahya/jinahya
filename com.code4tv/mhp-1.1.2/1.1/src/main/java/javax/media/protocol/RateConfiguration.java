/*
 * @(#)RateConfiguration.java	1.9 98/03/28
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
 * A configuration of streams for a particular rate.
 *
 * @see DataSource
 * @see RateConfigureable
 * @version 1.9, 98/03/28.
 */

public interface RateConfiguration {

    /**
     * Get the <CODE>RateRange</CODE> for this configuration.
     *
     * @return The rate supported by this configuration.
     */
    public RateRange getRate();
    
    /**
     * Get the streams that will have content at this rate.
     *
     * @return The streams supported at this rate.
     */
    public SourceStream[] getStreams();
}
    

    
