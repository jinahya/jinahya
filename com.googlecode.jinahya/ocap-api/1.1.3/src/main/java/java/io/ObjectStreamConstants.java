/*
<p>This is not an official specification document, and usage is restricted.
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
This work corresponds to the API signatures of JSR 219: Foundation
Profile 1.1. In the event of a discrepency between this work and the
JSR 219 specification, which is available at
http://www.jcp.org/en/jsr/detail?id=219, the latter takes precedence.
*/


  


package java.io;

/** 
 * Constants written into the Object Serialization Stream. 
 *
 * @author  unascribed
 * @version 1.29, 02/02/00
 * @since JDK 1.1
 */
public interface ObjectStreamConstants
{
    /** 
     * Magic number that is written to the stream header.
     */
    public static final short STREAM_MAGIC = -21267;

    /** 
     * Version number that is written to the stream header.
     */
    public static final short STREAM_VERSION = 5;

    /** 
     * First tag value.
     */
    public static final byte TC_BASE = 112;

    /** 
     * Null object reference.
     */
    public static final byte TC_NULL = 112;

    /** 
     * Reference to an object already written into the stream.
     */
    public static final byte TC_REFERENCE = 113;

    /** 
     * new Class Descriptor.
     */
    public static final byte TC_CLASSDESC = 114;

    /** 
     * new Object.
     */
    public static final byte TC_OBJECT = 115;

    /** 
     * new String.
     */
    public static final byte TC_STRING = 116;

    /** 
     * new Array.
     */
    public static final byte TC_ARRAY = 117;

    /** 
     * Reference to Class.
     */
    public static final byte TC_CLASS = 118;

    /** 
     * Block of optional data. Byte following tag indicates number
     * of bytes in this block data.
     */
    public static final byte TC_BLOCKDATA = 119;

    /** 
     * End of optional block data blocks for an object.
     */
    public static final byte TC_ENDBLOCKDATA = 120;

    /** 
     * Reset stream context. All handles written into stream are reset.
     */
    public static final byte TC_RESET = 121;

    /** 
     * long Block data. The long following the tag indicates the
     * number of bytes in this block data.
     */
    public static final byte TC_BLOCKDATALONG = 122;

    /** 
     * Exception during write. 
     */
    public static final byte TC_EXCEPTION = 123;

    /** 
     * Long string.
     */
    public static final byte TC_LONGSTRING = 124;

    /** 
     * new Proxy Class Descriptor.
     */
    public static final byte TC_PROXYCLASSDESC = 125;

    /** 
     * Last tag value.
     */
    public static final byte TC_MAX = 125;

    /** 
     * First wire handle to be assigned. 
     */
    public static final int baseWireHandle = 8257536;

    /** 
     * Bit mask for ObjectStreamClass flag. Indicates a Serializable class 
     * defines its own writeObject method.
     */
    public static final byte SC_WRITE_METHOD = 1;

    /** 
     * Bit mask for ObejctStreamClass flag. Indicates Externalizable data 
     * written in Block Data mode.
     * Added for PROTOCOL_VERSION_2.
     *
     * @see #PROTOCOL_VERSION_2
     * @since 1.2
     */
    public static final byte SC_BLOCK_DATA = 8;

    /** 
     * Bit mask for ObjectStreamClass flag. Indicates class is Serializable.
     */
    public static final byte SC_SERIALIZABLE = 2;

    /** 
     * Bit mask for ObjectStreamClass flag. Indicates class is Externalizable.
     */
    public static final byte SC_EXTERNALIZABLE = 4;

    /** 
     * Enable substitution of one object for another during 
     * serialization/deserialization.
     *
     * @see java.io.ObjectOutputStream#enableReplaceObject(boolean)
     * @see java.io.ObjectInputStream#enableResolveObject(boolean)
     * @since 1.2
     */
    public static final SerializablePermission SUBSTITUTION_PERMISSION = null;

    /** 
     * Enable overriding of readObject and writeObject.
     *
     * @see java.io.ObjectOutputStream#writeObjectOverride(Object)
     * @see java.io.ObjectInputStream#readObjectOverride()
     * @since 1.2
     */
    public static final SerializablePermission
        SUBCLASS_IMPLEMENTATION_PERMISSION = null;

    /** 
     * A Stream Protocol Version. <p>
     * 
     * All externalizable data is written in JDK 1.1 external data 
     * format after calling this method. This version is needed to write 
     * streams containing Externalizable data that can be read by 
     * pre-JDK 1.1.6 JVMs.
     *
     * @see java.io.ObjectOutputStream#useProtocolVersion(int)
     * @since 1.2
     */
    public static final int PROTOCOL_VERSION_1 = 1;

    /** 
     * A Stream Protocol Version. <p>
     * 
     * This protocol is written by JVM 1.2.
     *
     * Externalizable data is written in block data mode and is 
     * terminated with TC_ENDBLOCKDATA. Externalizable classdescriptor
     * flags has SC_BLOCK_DATA enabled. JVM 1.1.6 and greater can 
     * read this format change.
     *
     * Enables writing a nonSerializable class descriptor into the
     * stream. The serialVersionUID of a nonSerializable class is 
     * set to 0L. 
     *
     * @see java.io.ObjectOutputStream#useProtocolVersion(int)
     * @see #SC_BLOCK_DATA
     * @since 1.2
     */
    public static final int PROTOCOL_VERSION_2 = 2;
}
