/*
 * PMTElementaryStreamInfo.java
 */
package org.ocap.si;

/**
 * This interface represents an MPEG-2 PMT Elementary Stream Info loop. 
 * Each PMT will contain a loop of these blocks, as a block per an 
 * elementary stream.
 */
public interface PMTElementaryStreamInfo
{
    /**
     * Get the stream_type field.  Eight bit field specifying the type 
     * of program element carried within the packets within the PID 
     * returned by getElementaryPID().  See the StreamType interface for 
     * defined values.
     *
     * @return The stream type.
     */
    public short getStreamType();


    /**
     * Get the elementary_PID field.  Thirteen bit field specifying the 
     * PID of the associated elementary stream.
     *
     * @return The elementary PID.
     */
    public short getElementaryPID();


    /**
     * Get the descriptors associated with the elementary stream.
     *
     * @return The descriptor loop.
     */
    public Descriptor[] getDescriptorLoop();


    /**
     * Get the locator for the elementary stream.
     * <p></p>
     * For an Inband PMT, the returned OcapLocator
     * corresponds to one of the following OCAP URL forms:
     * <p></p>
     * <tt>ocap://source_id.@&ltcomponent_tag&gt{&&ltcomponent_tag&gt}</tt></br>
     * <tt>ocap://source_id.+PID</tt></br>
     * <tt>ocap://f=frequency.program_number.@&ltcomponent_tag&gt{&&ltcomponent_tag&gt}</tt></br>
     * <tt>ocap://f=frequency.program_number.+PID</tt>
     * <p></p>
     * The forms including the PID are returned if and only if no component
     * tags are signaled.
     * </br>
     * The form returned (apart from the component tag and PID elements)
     * must correspond to the form of the OCAP URL
     * passed to the previous call to
     * <tt>ProgramMapTableManager.retrieveInBand()</tt>
     * or
     * <tt>ProgramMapTableManager.addInBandChangeListener()</tt>.
     * <p></p>
     * For an OOB PMT, the returned OcapLocator
     * corresponds to one of the following OCAP URL forms:
     * <p></p>
     * <tt>ocap://oobfdc.program_number.@&ltcomponent_tag&gt{&&ltcomponent_tag&gt}</tt></br>
     * <tt>ocap://oobfdc.program_number.+PID</tt>
     * <p></p>
     * The form including the PID is returned if and only if no component tags
     * are signaled.
     * </br>
     * <p></p>
     * @return   The string which represents the URL of the elementary stream 
     *           represented by this PMTElementaryStreamInfo. 
     */
    public java.lang.String getLocatorString();
}


