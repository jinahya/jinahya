/*
 * ProgramMapTable.java
 */
package org.ocap.si;

/**
 * This interface represents an MPEG-2 Program Map Table (PMT).
 * <p></p>
 * For an Inband PMT, the getLocator() method defined in the SIElement
 * interface shall return an org.ocap.net.OcapLocator instance corresponding to
 * one of the following OCAP URL forms:
 * <p></p>
 * <tt>ocap://source_id</tt><p></p>
 * <tt>ocap://f=frequency.program_number</tt>
 * <p></p>
 * The form returned must match the form of the OCAP URL
 * passed to the previous call to
 * <tt>ProgramMapTableManager.retrieveInBand()</tt>
 * or
 * <tt>ProgramMapTableManager.addInBandChangeListener()</tt>.
 * <p></p>
 * For an OOB PMT, the returned OcapLocator
 * corresponds to the following OCAP URL form:
 * <p></p>
 * <tt>ocap://oobfdc.program_number</tt>
 * <p></p>
 * The getServiceInformationType() method defined in the SIElement interface 
 * shall return ServiceInformationType.UNKNOWN.
*/  
public interface ProgramMapTable extends Table
{
    /**
     * Get the program_number field, corresponds with the PMT.
     *
     * @return  The program number corresponds with the PMT.
     */
    public int getProgramNumber();


    /**
     * Get the PCR_PID field.  Thirteen bit field indicates the PID 
     * that shall contain the PCR fields of the transport stream packets.
     *
     * @return The PCR PID.
     */
    public int getPcrPID();


    /**
     * Get the outer descriptor loop.  List of descriptors that 
     * pertains to all programs.
     *
     * @return The outer descriptor loop.
     */
    public Descriptor [] getOuterDescriptorLoop();


    /**
     * Get elementary stream information blocks.  Each block contains 
     * elementary stream data for a particular stream type.
     *
     * @return The elementary stream information blocks.
     */
    public PMTElementaryStreamInfo[] getPMTElementaryStreamInfoLoop();
}


