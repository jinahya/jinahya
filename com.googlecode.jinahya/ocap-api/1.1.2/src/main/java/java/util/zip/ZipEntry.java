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


  


package java.util.zip;

import java.util.Date;
import java.util.Calendar;

/** 
 * This class is used to represent a ZIP file entry.
 *
 * @version	1.32, 05/03/00
 * @author	David Connelly
 */
public class ZipEntry implements ZipConstants, Cloneable
{
    /** 
     * Compression method for uncompressed entries.
     */
    public static final int STORED = 0;

    /** 
     * Compression method for compressed (deflated) entries.
     */
    public static final int DEFLATED = 8;

    /** 
     * Creates a new zip entry with the specified name.
     *
     * @param name the entry name
     * @exception NullPointerException if the entry name is null
     * @exception IllegalArgumentException if the entry name is longer than
     *		  0xFFFF bytes
     */
    public ZipEntry(String name) { }

    /** 
     * Creates a new zip entry with fields taken from the specified
     * zip entry.
     * @param e a zip Entry object
     */
    public ZipEntry(ZipEntry e) { }

    /** 
     * Returns the name of the entry.
     * @return the name of the entry
     */
    public String getName() {
        return null;
    }

    /** 
     * Sets the modification time of the entry.
     * @param time the entry modification time in number of milliseconds
     *		   since the epoch
     * @see #getTime()
     */
    public void setTime(long time) { }

    /** 
     * Returns the modification time of the entry, or -1 if not specified.
     * @return the modification time of the entry, or -1 if not specified
     * @see #setTime(long)
     */
    public long getTime() {
        return -1;
    }

    /** 
     * Sets the uncompressed size of the entry data.
     * @param size the uncompressed size in bytes
     * @exception IllegalArgumentException if the specified size is less
     *		  than 0 or greater than 0xFFFFFFFF bytes
     * @see #getSize()
     */
    public void setSize(long size) { }

    /** 
     * Returns the uncompressed size of the entry data, or -1 if not known.
     * @return the uncompressed size of the entry data, or -1 if not known
     * @see #setSize(long)
     */
    public long getSize() {
        return -1;
    }

    /** 
     * Returns the size of the compressed entry data, or -1 if not known.
     * In the case of a stored entry, the compressed size will be the same
     * as the uncompressed size of the entry.
     * @return the size of the compressed entry data, or -1 if not known
     * @see #setCompressedSize(long)
     */
    public long getCompressedSize() {
        return -1;
    }

    /** 
     * Sets the size of the compressed entry data.
     * @param csize the compressed size to set to
     * @see #getCompressedSize()
     */
    public void setCompressedSize(long csize) { }

    /** 
     * Sets the CRC-32 checksum of the uncompressed entry data.
     * @param crc the CRC-32 value
     * @exception IllegalArgumentException if the specified CRC-32 value is
     *		  less than 0 or greater than 0xFFFFFFFF
     * @see #setCrc(long)
     */
    public void setCrc(long crc) { }

    /** 
     * Returns the CRC-32 checksum of the uncompressed entry data, or -1 if
     * not known.
     * @return the CRC-32 checksum of the uncompressed entry data, or -1 if
     * not known
     * @see #getCrc()
     */
    public long getCrc() {
        return -1;
    }

    /** 
     * Sets the compression method for the entry.
     * @param method the compression method, either STORED or DEFLATED
     * @exception IllegalArgumentException if the specified compression
     *		  method is invalid
     * @see #getMethod()
     */
    public void setMethod(int method) { }

    /** 
     * Returns the compression method of the entry, or -1 if not specified.
     * @return the compression method of the entry, or -1 if not specified
     * @see #setMethod(int)
     */
    public int getMethod() {
        return 0;
    }

    /** 
     * Sets the optional extra field data for the entry.
     * @param extra the extra field data bytes
     * @exception IllegalArgumentException if the length of the specified
     *		  extra field data is greater than 0xFFFF bytes
     * @see #getExtra()
     */
    public void setExtra(byte[] extra) { }

    /** 
     * Returns the extra field data for the entry, or null if none.
     * @return the extra field data for the entry, or null if none
     * @see #setExtra(byte[])
     */
    public byte[] getExtra() {
        return null;
    }

    /** 
     * Sets the optional comment string for the entry.
     * @param comment the comment string
     * @exception IllegalArgumentException if the length of the specified
     *		  comment string is greater than 0xFFFF bytes
     * @see #getComment()
     */
    public void setComment(String comment) { }

    /** 
     * Returns the comment string for the entry, or null if none.
     * @return the comment string for the entry, or null if none
     * @see #setComment(String)
     */
    public String getComment() {
        return null;
    }

    /** 
     * Returns true if this is a directory entry. A directory entry is
     * defined to be one whose name ends with a '/'.
     * @return true if this is a directory entry
     */
    public boolean isDirectory() {
        return false;
    }

    /** 
     * Returns a string representation of the ZIP entry.
     */
    public String toString() {
        return null;
    }

    /** 
     * Returns the hash code value for this entry.
     */
    public int hashCode() {
        return 0;
    }

    /** 
     * Returns a copy of this entry.
     */
    public Object clone() {
        return null;
    }
}
