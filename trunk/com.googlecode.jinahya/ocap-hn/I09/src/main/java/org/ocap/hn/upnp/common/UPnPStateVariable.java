package org.ocap.hn.upnp.common;

/**
 * This interface is an abstract representation of a UPnP state variable.
 * It provides the data constituting a state variable that is
 * independent of the network interface on which it has been advertised.
 */
public interface UPnPStateVariable {

    /**
     * Gets the allowed values for this UPnP state variable.
     * The value returned is formatted per the UPnP Device
     * Architecture specification, service description,
     * {@code allowedValueList} element definition. If the
     * <code>UPnPStateVariable</code> does not have an
     * allowedValueList specified, returns zero length array.
     *
     * @return An array containing the allowed values for this state
     * variable.  Each element in the array contains the
     * value of one allowedValue element in the
     * allowedValueList.  The array has the same order as
     * the allowedValueList element.
     */
    String[] getAllowedValues();

    /**
     * Reports the default value of this UPnP state variable.
     * This value is taken from the {@code defaultValue} element in
     * the UPnP service description that defines this state variable.
     *
     * @return The default value of this state variable. Returns an empty
     * string if the variable does not have a defaultValue.
     */
    String getDefaultValue();

    /**
     * Gets the allowedValueRange maximum value of this UPnP state
     * variable.  The value returned is formatted per the UPnP Device
     * Architecture specification, service description,
     * {@code allowedValueRange} maximum element definition.
     *
     * @return A <code>String</code> containing the maximum allowed
     * value for this state variable. Returns an empty
     * string if the variable does not have an
     * allowedValueRange.
     */
    String getMaximumValue();

    /**
     * Gets the allowedValueRange minimum value for this UPnP state
     * variable.  The value returned is formatted per the UPnP Device
     * Architecture specification, service description,
     * {@code allowedValueRange} minimum element definition.
     *
     * @return A <code>String</code> containing the minimum allowed
     * value for this state variable. Returns an empty
     * string if the variable does not have an
     * allowedValueRange.
     */
    String getMinimumValue();

    /**
     * Gets the name of this UPnP state variable.
     * This value is taken from the {@code name} element of
     * the UPnP service description {@code stateVariable} element.
     *
     * @return The name of the state variable.
     */
    String getName();

    /**
     * Gets the data type of this UPnP state variable.  The value returned
     * is formatted per the UPnP Device Architecture specification,
     * service description, {@code dataType} element definition.
     *
     * @return The data type of the state variable.
     */
    String getDataType();

    /**
     * Gets the allowedValueRange step value for this UPnP state variable.
     * The value returned is formatted per the UPnP Device
     * Architecture specification, service description,
     * {@code allowedValueRange step} element definition.
     * <p>
     * Note that if the {@code step} element is omitted and the data type
     * of the state variable is an integer, the step value is considered to
     * be 1.
     *
     * @return A <code>String</code> containing the {@code step} value for
     * this state variable. Returns an empty string if service description of
     * this variable does not specify a {@code step} value.
     */
    String getStepValue();

    /**
     * Indicates if this state variable is evented.
     * The value is taken from the {@code sendEvents} attribute in the
     * UPnP service description that defines this state variable.
     *
     * @return True if this UPnP state variable is evented, otherwise
     * returns false.
     */
    boolean isEvented();

}
