// POD.java

package org.ocap.hardware.pod;

import java.util.Enumeration;

/**
 * This class provides an access to functions and information of the 
 * OpenCable CableCARD device on the OCAP Host device. 
 * The following functions and information are provided. 
 * <ul>
 * <li> Get a list of all applications in the CableCARD device. 
 * <li> Get Feature list supported by the Host. 
 * <li> Get a manufacture ID and a version number of the CableCARD device. 
 * <li> Get a current status of the CableCARD device.
 * <li> Update the Feature parameter in the Host. 
 * <li> Reject updating of the Feature parameter in the Host. 
 * </ul>
 */
public class POD {
    /**
     * A constructor of this class.  An application must use the 
     * {@link POD#getInstance} method to create an instance.
     */
    protected POD() {
    }


    /**
     * This method returns the sole instance of the POD class. 
     * The POD instance is either a singleton for each OCAP 
     * application or a singleton for an entire OCAP implementation. 
     *
     * @return   a singleton POD instance.
     *
     * @throws SecurityException if the caller does not have 
     *             MonitorAppPermission("podApplication").
     *
     */
    public static POD getInstance(){
        return null;
    }


    /**
     * This method provides a current status of the CableCARD device. 
     *
     * @return  true if the CableCARD device has completed the booting process. 
     */
    public boolean isReady() {
        return true;
    }


    /**
     * This method returns a CableCARD device manufacturer ID.
     *
     * @return  a pod_manufacturer_id in the Application_info_cnf() APDU 
     *             defined in [CCIF 2.0].
     *
     * @throws IllegalStateException  if the CableCARD is not ready, i.e., 
     *             the {@link #isReady} method returns false. 
     */
    public int getManufacturerID() {
        return 0;
    }


    /**
     * This method returns a CableCARD device version number.
     *
     * @return  pod_version_number in the Application_info_cnf() APDU 
     *             defined in [CCIF 2.0].
     *
     * @throws IllegalStateException  if the CableCARD is not ready, i.e., 
     *             the {@link #isReady} method returns false. 
     */
    public int getVersionNumber() {
        return 0;
    }


    /**
     * <p>
     * This method returns the CableCARD device applications listed in the 
     * Application_info_cnf() APDU defined in the OpenCable CableCARD 
     * Interface specification. 
     * </p><p>
     * Note that the Host need not to send the Application_info_req APDU. 
     * It may cache the information. 
     * </p>
     *
     * @return a list of CableCARD device applications in the CableCARD device.
     *
     * @throws IllegalStateException  if the CableCARD is not ready, i.e., 
     *             the {@link #isReady} method returns false. 
     */
    public PODApplication[] getApplications() {
        return null;
    }


    /**
     * This method returns a list of the Feature IDs supported by the Host 
     * device. Feature ID is defined in the OpenCable CableCARD Interface 
     * specification. 
     *
     * @return  a list of Feature IDs supported by the Host device. 
     */
    public int[] getHostFeatureList() {
        return null;
    }


    /**
     * <p>
     * This method updates the Feature parameter value in the Host device. 
     * In this method call, the {@link HostParamHandler#notifyUpdate} method 
     * shall be called. The notifyUpdate() method may reject update of the 
     * Feature parameter and also the Host device may reject it. 
     * The updated Feature parameter shall be notified to the CableCARD device 
     * according to [CCIF 2.0] after this 
     * method returns, but this method doesn't confirm a successful 
     * notification to the CableCARD device. 
     * </p><p>
     * The Feature ID and Feature parameter format is defined in [CCIF 2.0]. 
     * See also the 
     * {@link org.ocap.hardware.pod.HostParamHandler} for more information. 
     * </p><p>
     * Note that the 
     * {@link org.ocap.hardware.pod.HostParamHandler#notifyUpdate} method 
     * shall be called before the Feature parameter is updated by this method 
     * call. 
     * </p>
     *
     * @see org.ocap.hardware.pod.HostParamHandler
     *
     * @param featureID  a Feature ID to be updated. Feature ID is defined 
     *                in [CCIF 2.0]. 
     *                The Feature ID reserved for proprietary use (0x70 - 0xFF) 
     *                can be specified. 
     *
     * @param value  a new Feature parameter value for the specified featureID. 
     *                An actual format of each Feature parameter is defined 
     *                in [CCIF 2.0]. 
     *                For example, if the featureID is 0x1, the value is <br>
     *                <code>
     *                Rf_output_channel() {<br>
     *                &nbsp;&nbsp;&nbsp;&nbsp;Output_channel<br>
     *                &nbsp;&nbsp;&nbsp;&nbsp;Output_channel_ui<br>
     *                }<br>
     *                </code>
     *
     * @return <code>true</code> if update was successful. 
     *             <code>false</code> if rejected by the Host. 
     *
     * @throws IllegalArgumentException if the specified featureID is not in a 
     *                range of 0 <= featureID <= 0xFF, or the value is null. 
     */
    public boolean updateHostParam(int featureID, byte[] value) {
        return true;
    }


    /**
     * This method returns the current Feature parameter value in the Host 
     * device for the specified Feature ID. 
     * The Feature ID and Feature parameter format is defined in 
     * [CCIF 2.0]. See also the 
     * {@link org.ocap.hardware.pod.HostParamHandler} for more information. 
     *
     * @see org.ocap.hardware.pod.HostParamHandler
     *
     * @param featureID  a Feature ID defined 
     *                in [CCIF 2.0]. 
     *                The Feature ID reserved for proprietary use (0x70 - 0xFF) 
     *                can be specified. 
     *
     * @return  a current Feature parameter value for the specified featureID. 
     *                For example, if the featureID is 0x1, the value is <br>
     *                <code>
     *                Rf_output_channel() {<br>
     *                &nbsp;&nbsp;&nbsp;&nbsp;Output_channel<br>
     *                &nbsp;&nbsp;&nbsp;&nbsp;Output_channel_ui<br>
     *                }<br>
     *                </code>
     *                An array of length zero, if the specified featureID is 
     *                not supported. 
     *
     * @throws IllegalArgumentException if the specified featureID is not in a 
     *                range of 0 <= featureID <= 0xFF. 
     */
    public byte[] getHostParam(int featureID) {
        return null;
    }


    /**
     * This method sets an instance of a class that implements 
     * the HostParamHandler interface. 
     * Only one instance of such class can be set to the OCAP system. 
     * Multiple calls of this method replace the previous instance 
     * by a new one. 
     * By default, no HostParamHandler is set, i.e., all update of Feature 
     * parameter is decided by the Host device. 
     *
     * @param handler  an instance of a class that implements the 
     *             HostParamHandler. if <code>null</code> is specified, 
     *             the current HostParamHandler is removed. 
     *
     * @see org.ocap.hardware.pod.HostParamHandler
     */
    public void setHostParamHandler(HostParamHandler handler) {
    }
}
