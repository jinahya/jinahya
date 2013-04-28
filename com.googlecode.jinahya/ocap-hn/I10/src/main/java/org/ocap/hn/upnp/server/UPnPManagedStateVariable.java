package org.ocap.hn.upnp.server;

import org.ocap.hn.upnp.common.UPnPAdvertisedStateVariable;
import org.ocap.hn.upnp.common.UPnPStateVariable;


/**
 * This interface provides the server representation of a UPnP 
 * state variable. 
 */
public interface UPnPManagedStateVariable extends UPnPStateVariable
{

    /**
     * Sets the value of this state variable.  If the UPnP state variable
     * corresponding to this <code>UPnPManagedStateVariable</code> 
     * is evented the host will send the appropriate UPnP event, 
     * subject to the moderation constraints set for this 
     * <code>UPnPManagedStateVariable</code>.
     *
     * @param value The new value of the state variable.
     *
     * @throws IllegalArgumentException if {@code value} violates the
     * constraints of this variable's {@code allowedValueList} or
     * {@code allowedValueRange}, or does not
     * conform to the required format of the state variable's {@code dataType}
     * as indicated by the UPnP Device Architecture Specification.
     *
     * @throws SecurityException if the calling application has not been
     *      granted MonitorAppPermission("handler.homenetwork").
     *
     * @see #getAllowedValues()
     * @see #getMinimumValue()
     * @see #getMaximumValue()
     * @see #getDataType()
     */
    public void setValue(String value) throws SecurityException;

    /**
     * Reports the current value of this <code>UPnPManagedStateVariable</code>.
     * This method will return the most recent value specified by
     * {@link #setValue}, or the variable's default value if its value has
     * not been set.
     *
     * @return The value of this <code>UPnPManagedStateVariable</code>.
     */
    public String getValue();

    /**
     * Sets the moderation interval of this state variable, in 
     * milliseconds. Corresponds to the UPnP DA 1.0 "maximumRate". 
     *
     * @param interval New moderation interval of the state 
     *                 variable, in milliseconds. A value of zero
     *                 indicates unmoderated.
     * 
     * @throws UnsupportedOperationException if the corresponding 
     *                                       <code>UPnPStateVariable</code>
     *                                       is not evented.
     * @throws IllegalArgumentException if {@code interval} is negative.
     * @throws SecurityException if the calling application has not been
     *      granted MonitorAppPermission("handler.homenetwork").
     */
    public void setModerationInterval(int interval) throws SecurityException;

    /**
     * Gets the moderation interval of this state variable, in 
     * milliseconds. Corresponds to the UPnP DA 1.0 "maximumRate". 
     *
     * @throws UnsupportedOperationException if the corresponding 
     *                                       <code>UPnPStateVariable</code>
     *                                       is not evented.
     *  
     * @return The current moderation interval for this 
     *         <code>UPnPManagedStateVariable</code> in
     *         milliseconds. A value of zero indicates unmoderated.
     */
    public int getModerationInterval();

    /**
     * Sets the moderation delta of this state variable. Corresponds
     * to the UPnP DA 1.0 "minimumDelta". 
     *
     * @param delta New moderation delta of the state 
     *                 variable. A value of zero indicates
     *                 unmoderated.
     * 
     * @throws UnsupportedOperationException if the state variable is not
     *         evented or if the data type of this variable is not numeric.
     *
     * @throws IllegalArgumentException if {@code delta} is negative.
     *
     * @throws SecurityException if the calling application has not been
     *      granted MonitorAppPermission("handler.homenetwork").
     *
     * @see #getDataType()
     */
    public void setModerationDelta(int delta) throws SecurityException;

    /**
     * Gets the moderation delta of this state variable. Corresponds
     * to the UPnP DA 1.0 "minimumDelta". 
     *
     * @throws UnsupportedOperationException if the corresponding 
     *                                       <code>UPnPStateVariable</code>
     *                                       is not evented.
     *  
     * @return The current moderation delta for this 
     *         <code>UPnPManagedStateVariable</code>. A value of
     *         zero indicates unmoderated.
     */
    public int getModerationDelta();

    /**
     * Gets the network representations of this
     * <code>UPnPManagedStateVariable</code>.
     * Since the UPnP service description contains
     * information specific to the network interface on which it is advertised,
     * there can be multiple {@code UPnPAdvertisedStateVarable} objects
     * associated with a single {@code UPnPManagedStateVariable}.
     *
     * @return The network representations of this
     * {@code UPnPManagedStateVariable}.  Returns a zero-length array if
     * the corresponding UPnP service has not been advertised on a network
     * interface.
     */
    public UPnPAdvertisedStateVariable[] getAdvertisedStateVariables();

    /**
     * Gets the service that this state variable is a member of.
     *
     * @return The <code>UPnPManagedService</code> that this state 
     *         variable is a member of.
     */
    public UPnPManagedService getService();


    ////////  Methods inherited from UPnPStateVariable  ////////

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     * If the corresponding UPnP service has been advertised,
     * this method returns the same value
     * as {@code getAdvertisedStateVariables()[0].getAllowedValues()}.
     */
    public String[] getAllowedValues();

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     * If the corresponding UPnP service has been advertised,
     * this method returns the same value
     * as {@code getAdvertisedStateVariables()[0].getDefaultValue()}.
     */
    public String getDefaultValue();

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     * If the corresponding UPnP service has been advertised,
     * this method returns the same value
     * as {@code getAdvertisedStateVariables()[0].getMaximumValue()}.
     */
    public String getMaximumValue();

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     * If the corresponding UPnP service has been advertised,
     * this method returns the same value
     * as {@code getAdvertisedStateVariables()[0].getMinimumValue()}.
     */
    public String getMinimumValue();

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     * If the corresponding UPnP service has been advertised,
     * this method returns the same value
     * as {@code getAdvertisedStateVariables()[0].getName()}.
     */
    public String getName();

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     * If the corresponding UPnP service has been advertised,
     * this method returns the same value
     * as {@code getAdvertisedStateVariables()[0].getStateVariableType()}.
     */
    public String getDataType();

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     * If the corresponding UPnP service has been advertised,
     * this method returns the same value
     * as {@code getAdvertisedStateVariables()[0].getStepValue()}.
     */
    public String getStepValue();

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     * If the corresponding UPnP service has been advertised,
     * this method returns the same value
     * as {@code getAdvertisedStateVariables()[0].isEvented()}.
     */
    public boolean isEvented();
}
