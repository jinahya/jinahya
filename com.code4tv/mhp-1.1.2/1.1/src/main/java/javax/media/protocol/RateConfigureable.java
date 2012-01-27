/*
 * @(#)RateConfigureable.java	1.9 98/03/28
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
 * <code>DataSources</code> support the
 * <CODE>RateConfigureable</CODE> interface if they use
 * different rate-configurations to support multiple
 * media display speeds.
 *
 * @see DataSource
 * @see RateConfiguration
 * @see RateRange
 * @version 1.9, 98/03/28.
 */

public interface RateConfigureable {

    /**
     * Get the rate configurations that this object supports.
     * There must always be one and only one for
     * a <CODE>RateConfiguration</CODE> that covers a rate of 1.0.
     *
     * @return The collection of <CODE>RateConfigurations</CODE> that this
     * source supports.
     */
    public RateConfiguration[] getRateConfigurations();

    /**
     * Set a new <CODE>RateConfiguration</CODE>.
     * The new configuration should have been obtained by calling
     * <CODE>getRateConfigurations</CODE>.
     * Returns the actual <CODE>RateConfiguration</CODE> used.
     *
     * @param config The <CODE>RateConfiguration</CODE> to use.
     * @return The actual <CODE>RateConfiguration</CODE> used by the source.
     */
    public RateConfiguration setRateConfiguration(RateConfiguration config);

}
