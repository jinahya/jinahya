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

/** 
 * <p>A public key. This interface contains no methods or constants.
 * It merely serves to group (and provide type safety for) all public key
 * interfaces.
 *
 * Note: The specialized public key interfaces extend this interface.
 * See, for example, the DSAPublicKey interface in
 * <code>java.security.interfaces</code>.
 * NOTE: <B>DSAPublicKey</B> is found in J2ME CDC profiles such as 
 * J2ME Foundation Profile.
 *
 * @see Key
 * @see PrivateKey
 * @see Certificate
 * @see Signature#initVerify
 * @see java.security.interfaces.DSAPublicKey
 * @see java.security.interfaces.RSAPublicKey
 *
 * @version 1.28 00/02/02
 */
public interface PublicKey extends Key
{
    /** 
     * The class fingerprint that is set to indicate serialization
     * compatibility with a previous version of the class.
     */
    public static final long serialVersionUID = 7187392471159151072L;
}
