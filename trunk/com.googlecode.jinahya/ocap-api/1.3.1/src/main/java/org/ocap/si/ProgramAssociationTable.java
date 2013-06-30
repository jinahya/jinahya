/*
 * ProgramAssociationTable.java
 */
package org.ocap.si;

/**
 * This interface represents an MPEG-2 Program Association Table (PAT).
 * <p></p>
 * For an Inband PAT, the getLocator() method defined in the SIElement
 * interface shall return an org.ocap.net.OcapLocator instance corresponding to
 * one of the following OCAP URL forms:
 * <p></p>
 * <tt>ocap://source_id</tt></br>
 * <tt>ocap://f=frequency.program_number</tt>
 * <p></p>
 * The form returned must match the form of the OCAP URL
 * passed to the previous call to
 * <tt>ProgramAssociationTableManager.retrieveInBand()</tt>
 * or
 * <tt>ProgramAssociationTableManager.addInBandChangeListener()</tt>.
 * <p></p>
 * For an OOB PAT, the returned OcapLocator
 * corresponds to the following OCAP URL form:
 * <p></p>
 * <tt>ocap://oobfdc.program_number</tt>
 * <p></p>
 * The getServiceInformationType() method defined in the SIElement interface 
 * shall return ServiceInformationType.UNKNOWN.
 */
public interface ProgramAssociationTable extends Table
{
    /**
     * Get the identifier for this table.
     *
     * @return The table identifier.
     */
    public short getTableId();

    /**
     * Get the program loop in Program Association Table.
     *
     * @return The list of PATProgram which represents the program loop in Program Association Table.
     */
    public PATProgram[] getPrograms();
}



