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


  


package java.security;

import java.util.*;

import java.io.IOException;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.io.InputStream;
import java.io.ByteArrayInputStream;

/** 
 * This class defines the <i>Service Provider Interface</i> (<b>SPI</b>)
 * for the <code>MessageDigest</code> class, which provides the functionality
 * of a message digest algorithm, such as MD5 or SHA. Message digests are
 * secure one-way hash functions that take arbitrary-sized data and output a
 * fixed-length hash value.
 *
 * <p> All the abstract methods in this class must be implemented by a
 * cryptographic service provider who wishes to supply the implementation
 * of a particular message digest algorithm.
 *
 * <p> Implementations are free to implement the Cloneable interface.
 *
 * @author Benjamin Renaud 
 *
 * @version 1.11, 02/02/00
 *
 * @see MessageDigest
 */
public abstract class MessageDigestSpi
{

    public MessageDigestSpi() { }

    /** 
     * Returns the digest length in bytes.
     *
     * <p>This concrete method has been added to this previously-defined
     * abstract class. (For backwards compatibility, it cannot be abstract.)
     * 
     * <p>The default behavior is to return 0.
     * 
     * <p>This method may be overridden by a provider to return the digest
     * length.
     *
     * @return the digest length in bytes.
     *
     * @since 1.2
     */
    protected int engineGetDigestLength() {
        return 0;
    }

    /** 
     * Updates the digest using the specified byte.
     *
     * @param input the byte to use for the update.
     */
    protected abstract void engineUpdate(byte input);

    /** 
     * Updates the digest using the specified array of bytes,    
     * starting at the specified offset.
     *
     * @param input the array of bytes to use for the update.
     *
     * @param offset the offset to start from in the array of bytes.
     *
     * @param len the number of bytes to use, starting at 
     * <code>offset</code>.
     */
    protected abstract void engineUpdate(byte[] input, int offset, int len);

    /** 
     * Completes the hash computation by performing final
     * operations such as padding. Once <code>engineDigest</code> has 
     * been called, the engine should be reset (see 
     * {@link #engineReset() engineReset}).  
     * Resetting is the responsibility of the
     * engine implementor.
     *
     * @return the array of bytes for the resulting hash value.  
     */
    protected abstract byte[] engineDigest();

    /** 
     * Completes the hash computation by performing final
     * operations such as padding. Once <code>engineDigest</code> has
     * been called, the engine should be reset (see 
     * {@link #engineReset() engineReset}).  
     * Resetting is the responsibility of the
     * engine implementor.
     *
     * This method should be abstract, but we leave it concrete for
     * binary compatibility.  Knowledgeable providers should override this
     * method.
     *
     * @param buf the output buffer in which to store the digest
     *
     * @param offset offset to start from in the output buffer
     *
     * @param len number of bytes within buf allotted for the digest.
     * Both this default implementation and the SUN provider do not
     * return partial digests.  The presence of this parameter is solely
     * for consistency in our API's.  If the value of this parameter is less
     * than the actual digest length, the method will throw a DigestException.
     * This parameter is ignored if its value is greater than or equal to
     * the actual digest length.
     *
     * @return the length of the digest stored in the output buffer.
     * 
     * @exception DigestException if an error occurs.
     *
     * @since 1.2
     */
    protected int engineDigest(byte[] buf, int offset, int len)
        throws DigestException
    {
        return 0;
    }

    /** 
     * Resets the digest for further use.
     */
    protected abstract void engineReset();

    /** 
     * Returns a clone if the implementation is cloneable.    
     * 
     * @return a clone if the implementation is cloneable.
     *
     * @exception CloneNotSupportedException if this is called on an
     * implementation that does not support <code>Cloneable</code>.
     */
    public Object clone() throws CloneNotSupportedException {
        return null;
    }
}
