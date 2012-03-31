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

/** 
 * A class that can be used to compute the Adler-32 checksum of a data
 * stream. An Adler-32 checksum is almost as reliable as a CRC-32 but
 * can be computed much faster.
 *
 * @see		Checksum
 * @version 	1.23, 05/03/00
 * @author 	David Connelly
 */
public class Adler32 implements Checksum
{

    /** 
     * Creates a new Adler32 object.
     */
    public Adler32() { }

    /** 
     * Updates checksum with specified byte.
     * 
     * @param b an array of bytes
     */
    public void update(int b) { }

    /** 
     * Updates checksum with specified array of bytes.
     */
    public void update(byte[] b, int off, int len) { }

    /** 
     * Updates checksum with specified array of bytes.
     */
    public void update(byte[] b) { }

    /** 
     * Resets checksum to initial value.
     */
    public void reset() { }

    /** 
     * Returns checksum value.
     */
    public long getValue() {
        return -1;
    }
}
