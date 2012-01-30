/*
 * PATProgram.java
*/
package org.ocap.si;

/**
 * This interface represents an program block in the information loop of 
 * the MPEG-2 PAT.Each PAT will contain a loop of these blocks.
 */
public interface PATProgram
{
    /**
     * Get the program_number field.  Sixteen bit field, specifies the 
     * program to which the program_map_PID applies.  If the value is 
     * 0x0000, the program_map_PID, as returned bygetPID(), references 
     * the network PID.
     *
     * @return  The program number.
     */
    public int getProgramNumber();

    /**
     * Get the program_map_PID field.  Thirteen bit field specifying 
     * the PID of the transport stream packets. 
     *
     * @return  The program map PID.
     */
    public int getPID();
}


