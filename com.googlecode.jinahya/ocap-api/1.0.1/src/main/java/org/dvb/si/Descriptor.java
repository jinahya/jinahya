package org.dvb.si;

/**
  * This class represents a descriptor within a sub-table.
  * <p>
  * A descriptor consist of three fields: a tag, a contentLength and the content.
  * <p>
  * The tag uniquely identifies the descriptor type. 
  * The content length indicates the number of bytes in the content. 
  * The content consists of an array of bytes of length content length. The data
  * represented by the content is descriptor type dependent.
  * 
  * @see DescriptorTag
  */

public class Descriptor{

	/**
	 * This constructor is provided for the use of implementations and specifications 
	 * which extend the present document. Applications shall not define sub-classes of 
	 * this class. Implementations are not required to behave correctly if any such 
	 * application defined sub-classes are used.
	 */
	protected Descriptor() {}

  /**
    * Get the descriptor tag.
    * The value returned shall be the actual value used and is not limited to the
    * values defined in <code>DescriptorTag</code>.
    *
    * @return The descriptor tag (the most common values are defined in the 
    *         DescriptorTag interface)
    * @see    DescriptorTag
    */
  public short getTag() {
    // actual code shall replace the following statement
    return ((short) 0);
  }

  /**
    * This method returns the length of the descriptor content as 
    * coded in the length field of this descriptor.
    * 
    * @return The length of the descriptor content.
    */
  public short getContentLength() {
    // actual code shall replace the following statement
    return ((short) 0);
  }

  /**
    * Get a particular byte within the descriptor content
    * 
    * @param index index to the descriptor content. Value 0 corresponds to the 
    *              first byte after the length field.
    * @return The required byte
    * @exception IndexOutOfBoundsException if index < 0  or index >= ContentLength
    */
  public byte getByteAt(int index) throws java.lang.IndexOutOfBoundsException {
    // actual code shall replace the following statement
    return ((byte) 0);
  }

  /**
    * Get a copy of the content of this descriptor (everything after the length field).
    *
    * @return a copy of the content of the descriptor
    */
  public byte[] getContent() {
    // actual code shall replace the following statement
    return (null);
  }

}

