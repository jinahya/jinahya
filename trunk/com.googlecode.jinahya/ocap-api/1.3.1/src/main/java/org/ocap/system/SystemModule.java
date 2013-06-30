package org.ocap.system;

/**
 * <p>
 * The SystemModule is used by an OCAP-J application 
 * to send an APDU to the CableCARD device. 
 * A SystemModule instance is provided by the 
 * {@link SystemModuleHandler#ready} method after calling the 
 * {@link SystemModuleRegistrar#registerSASHandler} method or the 
 * {@link SystemModuleRegistrar#registerMMIHandler} method. 
 * </p>
 *
 * @see SystemModuleRegistrar
 *
 * @author Patrick Ladd
 * @author Shigeaki Watanabe (modified by ECN 03.0531-4)
 */
public interface SystemModule {
    /**
     * <p>
     * This method sends an APDU to the CableCARD device. 
     * The APDU structure is defined in Table 16 in Section 8.3 of EIA-679-B 
     * referred by [CCIF 2.0] and SCTE 28 2003. 
     * The APDU structure consists of apdu_tag, length_field and data_byte.
     * </p><p>
     * For the Private Host Application of the SAS Resource, the 
     * session number for sending the APDU is decided by the OCAP 
     * implementation automatically when registered via the 
     * {@link SystemModuleRegistrar#registerSASHandler} method. 
     * Sending APDU is delegated to the SAS Resource. 
     * </p><p>
     * For the MMI Resource and Application Information Resource, 
     * sending APDU is delegated to the resident MMI and Application 
     * Information Resources.
     * The OCAP-J application can send APDUs of either MMI Resource 
     * or Application Information Resource via a single SystemModule. 
     * The OCAP implementation SHALL investigate the apdu_tag field 
     * in the APDU and send the APDU to the CableCARD device using the 
     * session of the Resource specified by the apdu_tag. 
     * The session established by the resident MMI Resource and 
     * Application Information Resource is used to send the APDU. 
     * </p><p>
     * For both above, the delegated Resource encodes the specified 
     * APDU into an SPDU complementing a length_field and sends it 
     * to the CableCARD device according to the OpenCable CableCARD Interface 
     * Specification. 
     * </p><p>
     * The OCAP implementation doesn't have to confirm the validity 
     * of the specified dataByte parameter, but SHALL confirm the 
     * validity of the specified apduTag value. 
     * </p><p>
     * This method returns immediately and doesn't confirm success of 
     * sending the APDU. Errors detected while sending the APDU are 
     * notified via the {@link SystemModuleHandler#sendAPDUFailed} 
     * method. 
     * </p>
     *
     * @param apduTag  an apdu_tag value for the APDU to be sent to the 
     *             CableCARD device. 
     *
     * @param dataByte  a data_byte binary for the APDU to be sent to 
     *             the CableCARD device. 
     *             This value shall contain only the data_byte part of 
     *             an APDU structure defined in the OpenCable CableCARD 
     *             Interface Specification. The APDU consists of the 
     *             specified apduTag and dataByte and a length_field 
     *             complemented by the OCAP implementation. 
     *
     * @throws IllegalArgumentException if the specified apdu_tag value 
     *             is invalid (i.e., the value is not among possible tag 
     *             values for MMI Resource or Application Information 
     *             Resource). 
     *             Possible apdu_tag values and possible direction for 
     *             each Resource are defined in the OpenCable CableCARD 
     *             Interface Specification. 
     *
     */
    public void sendAPDU(int apduTag, byte[] dataByte);
}
