/*
 * Table.java
 */
package org.ocap.si;

/**
 * This interface represents an MPEG-2 Program Specific Information 
 * (PSI) table structure.
 */
public interface Table extends javax.tv.service.SIElement
{
    /**
     * Returns the table_id field of the table.  Eight bit field that 
     * identifies the table.
     *
     * @return The table Id.
     */
    public short getTableId();
}


