package org.ocap.hardware.frontpanel;

import java.util.Hashtable;

/**
 * This interface represents the set of individual indicators on a front
 * panel (e.g. power, recording).  An individual indicator is a single
 * lamp or icon that can be turned on or off.  It may have properties
 * such as color and brightness that can be set.
 */
public interface IndicatorDisplay
{
    /**
     * Set of possible individual indicator names.  Host devices MAY come
     * with other implementation specific indicators.
     */
    /** Power LED */
    public String POWER     = "power";
    /** RF bypass LED */
    public String RFBYPASS  = "rfbypass";
    /** Message LED */
    public String MESSAGE   = "message";
    /** Record LED */
    public String RECORD    = "record";
    /** Remote LED */
    public String REMOTE    = "remote";

    /**
     * Gets the set of reserved indicators.  The HashTable returned SHALL
     * contain the name of the indicator and a corresponding
     * {@link Indicator} object.  The set of standardized indicators
     * includes "power", "rfbypass", "message", and "record".  The
     * Indicator object associated with each indicator can be used to
     * change the color and brightness (i.e. turn on or off) if those
     * properties are supported.
     *
     * @return The set of Indicators reserved using
     *     {@link FrontPanelManager#reserveIndicator}
     */
    public Hashtable getIndicators();


}
