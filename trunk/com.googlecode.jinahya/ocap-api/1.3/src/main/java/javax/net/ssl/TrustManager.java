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
This work corresponds to the API signatures of CDC Security
(Java Secure Socket Extension - JSSE) Optional Package interfaces and modules.

*/



  


package javax.net.ssl;

/** 
 * This is the base interface for JSSE trust managers.
 * <P>
 * <code>TrustManager</code>s are responsible for managing the trust material
 * that is used when making trust decisions, and for deciding whether
 * credentials presented by a peer should be accepted.
 * <P>
 * <code>TrustManager</code>s are created by either
 * using a <code>TrustManagerFactory</code>,
 * or by implementing one of the <code>TrustManager</code> subclasses.
 *
 * @see TrustManagerFactory
 * @since 1.4
 * @version 1.10
 */
public interface TrustManager
{
}
