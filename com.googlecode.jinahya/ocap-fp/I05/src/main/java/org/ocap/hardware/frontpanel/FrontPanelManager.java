package org.ocap.hardware.frontpanel;

import org.davic.resources.ResourceClient;

/**
 * This class represents an optional front panel display and SHOULD not be
 * present in any device that does not support one.  A front panel
 * may include a text based display with one or more rows of characters.
 * This API is agnostic as to the type of hardware used in the display
 * (e.g. segmented LED, LCD).  The display may also contain individual
 * indicators for status indication such as power.
 */
public class FrontPanelManager
{

    /**
     * Protected constructor.  Cannot be used by an application.
     */
    protected FrontPanelManager()
    {
    }

    /**
     * Gets the singleton instance of the front panel manager.  The singleton
     * MAY be implemented using application or implementation scope.
     *
     * @return The front panel manager.
     *
     * @throws SecurityException if the calling application does not have
     *      MonitorAppPermission("frontpanel").
     */
    public static FrontPanelManager getInstance()
    {
        return null;
    }

    /**
     * Reserves the front panel text display for exclusive use by an application.
     * The ResourceProxy of the TextDisplay SHALL be used for resource
     * contention.
     *
     * @param resourceClient A DAVIC resource client for resource control.
     *
     * @return True if the implementation accepted the reservation request,
     *      otherwise returns false.
     */
    public boolean reserveTextDisplay(ResourceClient resourceClient)
    {
        return false;
    }

    /**
     * Reserves one of the indicators for exclusive use by an application.
     * The ResourceProxy of the Indicator SHALL be used for resource
     * contention.
     *
     * @param resourceClient A DAVIC resource client for resource control.
     * @param indicator One of the indicator String names found in the
     *      table returned by the
     *      {@link FrontPanelManager#getSupportedIndicators} method.
     *
     * @return True if the implementation accepted the reservation request,
     *      otherwise returns false.
     *
     * @throws IllegalArgumentException if the indicator argument is not
     *      contained in the table returned by the
     *      {@link FrontPanelManager#getSupportedIndicators} method.
     */
    public boolean reserveIndicator(ResourceClient resourceClient,
            String indicator)
    {
        return false;
    }

    /**
     * Releases the front panel text display from a previous reservation.  If
     * the calling application is not the application that reserved the front
     * panel, or if the front panel is not reserved when this method is called,
     * this method does nothing.
     */
    public void releaseTextDisplay()
    {
    }

    /**
     * Releases a front panel indicator from a previous reservation.  If the
     * calling application is not the application that reserved the indicator,
     * or if the indicator is not reserved when this method is called, this
     * method does nothing.
     *
     * @param indicator One of the indicator String names found in the
     *      table returned by the 
     *      {@link FrontPanelManager#getSupportedIndicators} method.
     *
     * @throws IllegalArgumentException if the indicator argument is not
     *      contained in the table returned by the
     *      {@link FrontPanelManager#getSupportedIndicators} method.
     */
    public void releaseIndicator(String indicator)
    {
    }

    /**
     * Gets the front panel text display.  A front panel must be reserved
     * before an application can get it with this method.
     *
     * @return Front panel text display, or null if the application has not
     *      reserved it.
     */
    public TextDisplay getTextDisplay()
    {
        return null;
    }

    /**
     * Gets the set of available indicators.  The array returned SHALL contain 
     * the name of all the indicators supported with
     * {@link #getIndicatorDisplay(String[])}.  The set of standardized indicators
     * includes "power", "rfbypass", "message", and "record" and these MAY be
     * returned. 
     *
     * @return The set of supported indicators.  MAY return indicators not
     *      included in the standardized set.
     */
    public String[] getSupportedIndicators()
    {
        return null;
    }

    /**
     * Gets the individual indicators display.  Indicators must be reserved
     * before an application can get them with this method.
     *
     * @param indicators Set of indicator names.
     *
     * @return Set of individual indicators, or null if the application has
     *      not reserved one or more of the parameter indicators.
     *
     * @throws IllegalArgumentException if any of the indicator arguments are
     *      not contained in the table returned by the
     *      {@link IndicatorDisplay#getIndicators} method.
     */
    public IndicatorDisplay getIndicatorDisplay(String[] indicators)
    {
        return null;
    }

    /**
     * Provides an indication of whether or not text display is supported.
     * For backwards compatibility, the implementation must allow an application
     * to reserve, get, and write to the text display even if the device does not
     * contain a text display.  If an application attempts to write to a
     * nonexistent text display, the implementation shall not return an error.
     *
     * @return True if text display is supported, false if text display is not
     * supported
     *
     */
    public boolean isTextDisplaySupported()
    {
        return false;
    }
}
