/*
 * Descriptor.java
 */
package org.ocap.si;

/**
 * This class represents an MPEG-2 descriptor.
 */
public abstract class Descriptor
{
    /**
     * Get the descriptor_tag field.  Eight bit field that identifies 
     * each descriptor. The range of valid MPEG-2 descriptor tag values 
     * is 0x2 through 0xFF.
     *
     * @return    The descriptor tag.
     */
    public abstract short getTag();


    /**
     * Get the descriptor_length field.  Eight bit field specifying the 
     * number of bytes of the descriptor immediately following the 
     * descriptor_length field.
     *
     * @return    The descriptor length.
     */
    public abstract short getContentLength();


    /**
     * Get the data contained within this descriptor. The data is returned 
     * in an array of bytes and consists of the data immediately following 
     * the descriptor_length field with length indicated by that field.
     *
     * @return    The descriptor data.
     */
    public abstract byte[] getContent();


    /** 
     * Get a particular byte within the descriptor content. The data is 
     * returned in a byte which is located at the position specified by 
     * an index parameter in the data content immediately following the 
     * descriptor_length field.
     *
     * @param index  An index to the descriptor content. Value 0 
     *            corresponds to the first byte after the length field.
     *
     * @return    The required byte data.
     *
     * @throws  IndexOutOfBoundsException  if index < 0 or index >= 
     *            ContentLength 
     */
    public abstract byte getByteAt(int index);
}
