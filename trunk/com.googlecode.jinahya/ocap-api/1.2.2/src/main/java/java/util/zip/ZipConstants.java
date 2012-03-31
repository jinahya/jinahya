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

/*
 * This interface defines the constants that are used by the classes
 * which manipulate ZIP files.
 *
 * @version	1.15, 02/02/00
 * @author	David Connelly
 */
interface ZipConstants {
    /*
     * Header signatures
     */
    static long LOCSIG = 0x04034b50L;	// "PK\003\004"
    static long EXTSIG = 0x08074b50L;	// "PK\007\008"
    static long CENSIG = 0x02014b50L;	// "PK\001\002"
    static long ENDSIG = 0x06054b50L;	// "PK\005\006"

    /*
     * Header sizes in bytes (including signatures)
     */
    static final int LOCHDR = 30;	// LOC header size
    static final int EXTHDR = 16;	// EXT header size
    static final int CENHDR = 46;	// CEN header size
    static final int ENDHDR = 22;	// END header size

    /*
     * Local file (LOC) header field offsets
     */
    static final int LOCVER = 4;	// version needed to extract
    static final int LOCFLG = 6;	// general purpose bit flag
    static final int LOCHOW = 8;	// compression method
    static final int LOCTIM = 10;	// modification time
    static final int LOCCRC = 14;	// uncompressed file crc-32 value
    static final int LOCSIZ = 18;	// compressed size
    static final int LOCLEN = 22;	// uncompressed size
    static final int LOCNAM = 26;	// filename length
    static final int LOCEXT = 28;	// extra field length

    /*
     * Extra local (EXT) header field offsets
     */
    static final int EXTCRC = 4;	// uncompressed file crc-32 value
    static final int EXTSIZ = 8;	// compressed size
    static final int EXTLEN = 12;	// uncompressed size

    /*
     * Central directory (CEN) header field offsets
     */
    static final int CENVEM = 4;	// version made by
    static final int CENVER = 6;	// version needed to extract
    static final int CENFLG = 8;	// encrypt, decrypt flags
    static final int CENHOW = 10;	// compression method
    static final int CENTIM = 12;	// modification time
    static final int CENCRC = 16;	// uncompressed file crc-32 value
    static final int CENSIZ = 20;	// compressed size
    static final int CENLEN = 24;	// uncompressed size
    static final int CENNAM = 28;	// filename length
    static final int CENEXT = 30;	// extra field length
    static final int CENCOM = 32;	// comment length
    static final int CENDSK = 34;	// disk number start
    static final int CENATT = 36;	// internal file attributes
    static final int CENATX = 38;	// external file attributes
    static final int CENOFF = 42;	// LOC header offset

    /*
     * End of central directory (END) header field offsets
     */
    static final int ENDSUB = 8;	// number of entries on this disk
    static final int ENDTOT = 10;	// total number of entries
    static final int ENDSIZ = 12;	// central directory size in bytes
    static final int ENDOFF = 16;	// offset of first CEN header
    static final int ENDCOM = 20;	// zip file comment length
}
