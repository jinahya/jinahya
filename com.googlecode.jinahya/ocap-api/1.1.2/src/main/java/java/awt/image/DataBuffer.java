/*
</p>
<a name="notice"><strong><center>
NOTICE
</center></strong><br>
<br>

(c) 2005-2007 Sun Microsystems, Inc. All Rights Reserved.
<p>
Neither this file nor any files generated from it describe a complete
specification, and they may only be used as described below. For
example, no permission is given for you to incorporate this file, in
whole or in part, in an implementation of a Java specification.
<p>
Sun Microsystems Inc. owns the copyright in this file and it is provided
to you for informative, as opposed to normative, use. The file and any
files generated from it may be used to generate other informative
documentation, such as a unified set of documents of API signatures for
a platform that includes technologies expressed as Java APIs. The file
may also be used to produce "compilation stubs," which allow
applications to be compiled and validated for such platforms.
<p>
Any work generated from this file, such as unified javadocs or compiled
stub files, must be accompanied by this notice in its entirety.
<p>
This work corresponds to the API signatures of JSR 217: Personal Basis
Profile 1.1. In the event of a discrepency between this work and the
JSR 217 specification, which is available at
http://www.jcp.org/en/jsr/detail?id=217, the latter takes precedence.
*/


  


package java.awt.image;

// PBP/PP
/** 
 * 

 *
 * <em>The <code>DataBuffer</code> class defines data storage formats
 * used in various image operations. Note that it serves no other 
 * purpose in this Profile.</em>
 *
 * @see 
 */
public abstract class DataBuffer
{
    /** Tag for unsigned byte data.  */
    public static final int TYPE_BYTE = 0;

    /** Tag for unsigned short data.  */
    public static final int TYPE_USHORT = 1;

    // /** Tag for signed short data.  Placeholder for future use.  */
    // public static final int TYPE_SHORT = 0;

    /** Tag for int data.  */
    public static final int TYPE_INT = 3;

    // /** Tag for float data.  Placeholder for future use.  */
    // public static final int TYPE_FLOAT = 0;
// 
    // /** Tag for double data.  Placeholder for future use.  */
    // public static final int TYPE_DOUBLE = 0;

    /** Tag for undefined data.  */
    public static final int TYPE_UNDEFINED = 32;
// 
    // /** The data type of this DataBuffer.  */
    // protected int dataType;
// 
    // /** The number of banks in this DataBuffer.  */
    // protected int banks;

    // /** 
     // *Offset into default (first) bank from which to get the first element. 
     // */
    // protected int offset;
// 
    // /** Usable size of all banks.  */
    // protected int size;
// 
    // /** Offsets into all banks.  */
    // protected int[] offsets;


    /**
     * PBP: Placeholder to hide constructor.
     */
    DataBuffer() {}


    // /** 
     // *  Constructs a DataBuffer containing one bank of the specified
     // *  data type and size.
     // *  @param dataType the data type of this <code>DataBuffer</code>
     // *  @param size the size of the banks
     // */
    // protected DataBuffer(int dataType, int size) { }
// 
    // /** 
     // *  Constructs a DataBuffer containing the specified number of
     // *  banks.  Each bank has the specified size and an offset of 0.
     // *  @param dataType the data type of this <code>DataBuffer</code>
     // *  @param size the size of the banks
     // *  @param numBanks the number of banks in this
     // *         <code>DataBuffer</code>
     // */
    // protected DataBuffer(int dataType, int size, int numBanks) { }
// 
    // /** 
     // *  Constructs a DataBuffer that contains the specified number
     // *  of banks.  Each bank has the specified datatype, size and offset.
     // *  @param dataType the data type of this <code>DataBuffer</code>
     // *  @param size the size of the banks
     // *  @param numBanks the number of banks in this
     // *         <code>DataBuffer</code>
     // *  @param offset the offset for each bank
     // */
    // protected DataBuffer(int dataType, int size, int numBanks, int offset) { }
// 
    // /** 
     // *  Constructs a DataBuffer which contains the specified number
     // *  of banks.  Each bank has the specified datatype and size.  The
     // *  offset for each bank is specified by its respective entry in
     // *  the offsets array.
     // *  @param dataType the data type of this <code>DataBuffer</code>
     // *  @param size the size of the banks
     // *  @param numBanks the number of banks in this
     // *         <code>DataBuffer</code>
     // *  @param offsets an array containing an offset for each bank.
     // *  @throws ArrayIndexOutOfBoundsException if <code>numBanks</code>
     // *          does not equal the length of <code>offsets</code>
     // */
    // protected DataBuffer(int dataType, int size, int numBanks, int[] offsets)
    // { }
// 
    // /** 
     // *Returns the size (in bits) of the data type, given a datatype tag. 
     // * @param type the value of one of the defined datatype tags
     // * @return the size of the data type
     // * @throws IllegalArgumentException if <code>type</code> is less than 
     // *         zero or greater than {@link #TYPE_DOUBLE}
     // */
    // public static int getDataTypeSize(int type) { }
// 
    // /** 
     // *Returns the data type of this DataBuffer.
     // *   @return the data type of this <code>DataBuffer</code>.
     // */
    // public int getDataType() { }
// 
    // /** 
     // *Returns the size (in array elements) of all banks.
     // *   @return the size of all banks.
     // */
    // public int getSize() { }
// 
    // /** 
     // *Returns the offset of the default bank in array elements.
     // *  @return the offset of the default bank.
     // */
    // public int getOffset() { }
// 
    // /** 
     // *Returns the offsets (in array elements) of all the banks.
     // *  @return the offsets of all banks.
     // */
    // public int[] getOffsets() { }
// 
    // /** 
     // *Returns the number of banks in this DataBuffer. 
     // *  @return the number of banks.
     // */
    // public int getNumBanks() { }
// 
    // /** 
     // * Returns the requested data array element from the first (default) bank
     // * as an integer.
     // * @param i the index of the requested data array element
     // * @return the data array element at the specified index.
     // * @see #setElem(int, int)
     // * @see #setElem(int, int, int)
     // */
    // public int getElem(int i) { }
// 
    // /** 
     // * Returns the requested data array element from the specified bank
     // * as an integer.
     // * @param bank the specified bank
     // * @param i the index of the requested data array element
     // * @return the data array element at the specified index from the
     // *         specified bank at the specified index.
     // * @see #setElem(int, int)
     // * @see #setElem(int, int, int)
     // */
    // public abstract int getElem(int bank, int i);
// 
    // /** 
     // * Sets the requested data array element in the first (default) bank
     // * from the given integer.
     // * @param i the specified index into the data array
     // * @param val the data to set the element at the specified index in
     // * the data array
     // * @see #getElem(int)
     // * @see #getElem(int, int)
     // */
    // public void setElem(int i, int val) { }
// 
    // /** 
     // * Sets the requested data array element in the specified bank
     // * from the given integer.
     // * @param bank the specified bank
     // * @param i the specified index into the data array
     // * @param val  the data to set the element in the specified bank
     // * at the specified index in the data array
     // * @see #getElem(int)
     // * @see #getElem(int, int)
     // */
    // public abstract void setElem(int bank, int i, int val);
// 
    // /** 
     // * Returns the requested data array element from the first (default) bank
     // * as a float.  The implementation in this class is to cast getElem(i)
     // * to a float.  Subclasses may override this method if another
     // * implementation is needed.
     // * @param i the index of the requested data array element
     // * @return a float value representing the data array element at the
     // *  specified index.
     // * @see #setElemFloat(int, float)
     // * @see #setElemFloat(int, int, float)
     // */
    // public float getElemFloat(int i) { }
// 
    // /** 
     // * Returns the requested data array element from the specified bank
     // * as a float.  The implementation in this class is to cast 
     // * {@link #getElem(int, int)}
     // * to a float.  Subclasses can override this method if another 
     // * implementation is needed.
     // * @param bank the specified bank
     // * @param i the index of the requested data array element
     // * @return a float value representing the data array element from the
     // * specified bank at the specified index.
     // * @see #setElemFloat(int, float)
     // * @see #setElemFloat(int, int, float)
     // */
    // public float getElemFloat(int bank, int i) { }
// 
    // /** 
     // * Sets the requested data array element in the first (default) bank
     // * from the given float.  The implementation in this class is to cast
     // * val to an int and call {@link #setElem(int, int)}.  Subclasses 
     // * can override this method if another implementation is needed.
     // * @param i the specified index
     // * @param val the value to set the element at the specified index in
     // * the data array
     // * @see #getElemFloat(int)
     // * @see #getElemFloat(int, int)
     // */
    // public void setElemFloat(int i, float val) { }
// 
    // /** 
     // * Sets the requested data array element in the specified bank
     // * from the given float.  The implementation in this class is to cast            
     // * val to an int and call {@link #setElem(int, int)}.  Subclasses can
     // * override this method if another implementation is needed.
     // * @param bank the specified bank
     // * @param i the specified index  
     // * @param val the value to set the element in the specified bank at
     // * the specified index in the data array
     // * @see #getElemFloat(int)
     // * @see #getElemFloat(int, int)
     // */
    // public void setElemFloat(int bank, int i, float val) { }
// 
    // /** 
     // * Returns the requested data array element from the first (default) bank
     // * as a double.  The implementation in this class is to cast 
     // * {@link #getElem(int)}
     // * to a double.  Subclasses can override this method if another 
     // * implementation is needed.
     // * @param i the specified index
     // * @return a double value representing the element at the specified
     // * index in the data array.
     // * @see #setElemDouble(int, double)
     // * @see #setElemDouble(int, int, double)
     // */
    // public double getElemDouble(int i) { }
// 
    // /** 
     // * Returns the requested data array element from the specified bank as
     // * a double.  The implementation in this class is to cast getElem(bank, i)
     // * to a double.  Subclasses may override this method if another
     // * implementation is needed.
     // * @param bank the specified bank
     // * @param i the specified index
     // * @return a double value representing the element from the specified
     // * bank at the specified index in the data array.
     // * @see #setElemDouble(int, double)
     // * @see #setElemDouble(int, int, double)
     // */
    // public double getElemDouble(int bank, int i) { }
// 
    // /** 
     // * Sets the requested data array element in the first (default) bank
     // * from the given double.  The implementation in this class is to cast
     // * val to an int and call {@link #setElem(int, int)}.  Subclasses can
     // * override this method if another implementation is needed.
     // * @param i the specified index
     // * @param val the value to set the element at the specified index
     // * in the data array
     // * @see #getElemDouble(int)
     // * @see #getElemDouble(int, int)
     // */
    // public void setElemDouble(int i, double val) { }
// 
    // /** 
     // * Sets the requested data array element in the specified bank
     // * from the given double.  The implementation in this class is to cast
     // * val to an int and call {@link #setElem(int, int)}.  Subclasses can
     // * override this method if another implementation is needed.
     // * @param bank the specified bank
     // * @param i the specified index
     // * @param val the value to set the element in the specified bank
     // * at the specified index of the data array
     // * @see #getElemDouble(int)
     // * @see #getElemDouble(int, int)
     // */
    // public void setElemDouble(int bank, int i, double val) { }
}
