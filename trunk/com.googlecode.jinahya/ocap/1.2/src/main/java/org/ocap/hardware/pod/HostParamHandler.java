// HostParamHandler.java

package org.ocap.hardware.pod;

/**
 * <p>
 * A class that implements this interface can reject the update of the 
 * Feature parameter in the Host device. Feature parameter is defined for 
 * the Generic Feature Control Support in the OpenCable CableCARD Interface 
 * specification. 
 * An OCAP-J application can set only one instance of such classes to the 
 * OCAP implementation via the 
 * {@link org.ocap.hardware.pod.POD#setHostParamHandler} method. 
 * </p><p>
 * Before Feature parameter in the Host is modified, the
 * {@link HostParamHandler#notifyUpdate} method shall be called with the 
 * Feature ID to be modified and its Feature parameter value. And only if 
 * the HostParamHandler.notifyUpdate() method returns true, the Feature 
 * parameter value in the Host device will be modified. 
 * Note that the Host device may reject the update of Feature parameter 
 * even if the HostParamHandler.notifyUpdate() method returns true. 
 * </p><p>
 * The Feature ID and the Feature parameter value format are defined in 
 * the CableCARD Interface 2.0 Specification [4]. 
 * For example, the Feature ID of "RF Output Channel" Feature is 0x1, and 
 * its parameter value format is <br>
 * <code>
 * Rf_output_channel() {<br>
 * &nbsp;&nbsp;&nbsp;&nbsp;Output_channel<br>
 * &nbsp;&nbsp;&nbsp;&nbsp;Output_channel_ui<br>
 * }<br>
 * </code>
 * </p><p>
 * The Feature parameters in the Host device will be modified by the 
 * following cases. 
 * <ul>
 * <li> The CableCARD sends feature_parameters APDU to the Host.  
 *      (See the [CCIF 2.0].)
 * <li> The Host modifies its own Feature parameters. 
 * <li> An OCAP-J application calls the 
 *      {@link org.ocap.hardware.pod.POD#updateHostParam} method.
 * </ul>
 * In every cases, the HostParamHandler.notifyUpdate() method shall be called. 
 * </p>
 *
 */
public interface HostParamHandler
{
    /**
     * <p>
     * This is a call back method to notify an update of the Feature 
     * parameter in the Host device. This method shall be called every time 
     * before the Feature parameter is modified. Only if this method returns 
     * true, the Host device can modify its Feature parameter by the specified 
     * value. 
     * </p><p>
     * Note that the Host device may reject the update of Feature parameter 
     * even if the HostParamHandler.notifyUpdate() method returns true. 
     * </p><p>
     * This method should return immediately without blocking. 
     * </p>
     *
     * @param featureID  a Feature ID for the Generic Feature Control Support 
     *                in the CableCARD Interface 2.0 Specification [4]. 
     *                The Feature ID reserved for proprietary use (0x70 - 0xFF) 
     *                can be specified. 
     *
     * @param value  a Feature parameter value for the specified featureID. 
     *                An actual format of each Feature parameter is defined 
     *                in the CableCARD Interface 2.0 Specification [4]. 
     *                For example, if the featureID is 0x1, the value is <br>
     *                <code>
     *                Rf_output_channel() {<br>
     *                &nbsp;&nbsp;&nbsp;&nbsp;Output_channel<br>
     *                &nbsp;&nbsp;&nbsp;&nbsp;Output_channel_ui<br>
     *                }
     *                </code>
     *
     * @return <code>true</code> to accept the modification of the specified 
     *                value. <code>false</code> to reject it.
     *
     * @see org.ocap.hardware.pod.POD#setHostParamHandler
     **/
    public boolean notifyUpdate(int featureID, byte[] value);
}
