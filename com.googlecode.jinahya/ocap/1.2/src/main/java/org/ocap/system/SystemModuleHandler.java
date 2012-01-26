package org.ocap.system;

/**
 * The SystemModuleHandler is used by an OCAP-J application  
 * for the following purposes: 
 * 1) receive an APDU from the CableCARD device, 
 * 2) detect an unsent APDU to the POD in case of an error, 
 * and 3) notification of becoming registered and unregistered. 
 * 
 * @see SystemModuleRegistrar
 *
 * @author Patrick Ladd
 * @author Shigeaki Watanabe (modified by ECN 03.0531-4)
 */
public interface SystemModuleHandler {
    /**
     * <p>
     * This is a call back method to notify an APDU received from the 
     * CableCARD device.
     * The APDU structure is defined in Table 16 in Section 8.3 of EIA-679-B 
     * referred by [CCIF 2.0] and SCTE 28 2003. 
     * The APDU structure consists of apdu_tag, length_field and data_byte.
      * </p><p>
     * For the Private Host Application on the SAS Resource, the 
     * SystemModuleHandler is bound to a specific session number (and 
     * a specific Private Host Application ID) when it is registered 
     * via the {@link SystemModuleRegistrar#registerSASHandler} method. 
     * Only the receiveAPDU() method that is bound to the session of the 
     * received APDU shall be called only once by the OCAP implementation. 
     * </p><p>
     * For the MMI Resource and the Application Information Resource, 
     * the OCAP-J application can receive APDUs for both Resources by 
     * a single SystemModuleHandler. 
     * The OCAP implementation shall call the receiveAPDU() method of 
     * the SystemModuleHandler registered via the 
     * {@link SystemModuleRegistrar#registerMMIHandler} method 
     * only once for both the MMI and Application Information APDU. 
     * </p><p>
     * The OCAP implementation extract the APDU from an SPDU from 
     * the CableCARD device according to the OpenCable CableCARD Interface 
     * Specification, and then call this method. Note that the OCAP 
     * implementation simply retrieves the field values from the APDU 
     * and call this method. No validity check is done by the OCAP 
     * implementation. Though SPDU and TPDU mechanism may detect a 
     * destruction of the APDU structure while transmitting, the OCAP 
     * shall call this method every time when it receives an APDU. In 
     * such case, the parameters may be invalid so that the OCAP-J 
     * application can detect an error. 
     * </p><p>
     * Note that if the CableCARD device returns an APDU indicating an error 
     * condition, this method is called instead of the sendAPDUFailed() 
     * method. 
     * </p><p>
     * This method shall return immediately. 
     * <p>
     *
     * @param apduTag  an apdu_tag value in the APDU coming from the 
     *             CableCARD device. I.e., first 3 bytes. If the corresponding 
     *             bytes are missed, they are filled by zero. Note that 
     *             the OCAP implementation calls this method according 
     *             to the session number, so the apdu_tag value may be 
     *             out of the valid range. 
     *
     * @param lengthField  a length_field value in the APDU coming from 
     *             the CableCARD device.  This is a decimal int value 
     *             converted from a length field encoded in ASN.1 BER.
     *             If the corresponding bytes are missing, the value of
     *             this parameter is set to 0.
     *
     * @param dataByte  an data_byte bytes in the APDU coming from the 
     *             CableCARD device. If the corresponding bytes are missed since 
     *             signaling trouble, only existing bytes are specified. 
     *             If they are more than expected length, all existing 
     *             bytes are specified. 
     *             The APDU consists of the specified apdu_tag, dataByte 
     *             and length_field. The APDU format is defined in [CCIF 2.0]. 
     *
     */
    public void receiveAPDU(int apduTag, int lengthField, byte[] dataByte);


    /**
     * This is a call back method to notify an error has occurred 
     * while sending an APDU via the {@link SystemModule#sendAPDU} 
     * method. This method shall return immediately. 
     *
     * @param apduTag  an apdu_tag of the APDU that was failed to 
     *             be sent. 
     *             This is the apduTag value specified in the 
     *             SystemModule.sendAPDU() method. 
     *
     * @param dataByte  an data_byte of the APDU that was failed 
     *             to be sent. 
     *             This is dataByte value specified in the 
     *             SystemModule.sendAPDU() method. 
     *
     */
    public void sendAPDUFailed(int apduTag, byte[] dataByte);


    /**
     * This is a call back method to notify that the 
     * SystemModuleHandler is being unregistered and give a chance 
     * to do a termination procedure. This method returns after the 
     * termination procedure has finished. 
     */
    public void notifyUnregister();


    /**
     * This is a call back method to notify that this 
     * SystemModuleHandler is ready to receive an APDU, and returns a 
     * SystemModule to send an APDU to the CableCARD device. 
     *
     * @param systemModule  a SystemModule instance corresponding to 
     *             this SystemModuleHandler. The returned SystemModule 
     *             sends an APDU using the same session that this 
     *             SystemModuleHandler receives an APDU. 
     *             Null is specified, if the OCAP implementation fails 
     *             to establish a SAS connection or fails to create an 
     *             SystemModule instance due to lack of resource. 
     *
     */
    public void ready(SystemModule systemModule);
}
