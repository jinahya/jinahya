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




/*
 * @(#)TrustManagerFactorySpi.java	1.6 05/03/12
 */
  
/*
 * NOTE:
 * Because of various external restrictions (i.e. US export
 * regulations, etc.), the actual source code can not be provided
 * at this time. This file represents the skeleton of the source
 * file, so that javadocs of the API can be created.
 */

package javax.net.ssl;

import java.security.*;

/** 
 * This class defines the <i>Service Provider Interface</i> (<b>SPI</b>)
 * for the <code>TrustManagerFactory</code> class.
 *
 * <p> All the abstract methods in this class must be implemented by each
 * cryptographic service provider who wishes to supply the implementation
 * of a particular trust manager factory.
 *
 * @since 1.4
 * @see TrustManagerFactory
 * @see TrustManager
 * @version 1.11
 */
public abstract class TrustManagerFactorySpi
{

    public TrustManagerFactorySpi() { }

    /** 
     * Initializes this factory with a source of certificate
     * authorities and related trust material.
     *
     * @param ks the key store or null
     * @throws KeyStoreException if this operation fails
     * @see TrustManagerFactory#init(KeyStore)
     */
    protected abstract void engineInit(KeyStore ks) throws KeyStoreException;

    /** 
     * Initializes this factory with a source of provider-specific
     * key material.
     * <P>
     * In some cases, initialization parameters other than a keystore
     * may be needed by a provider.  Users of that
     * particular provider are expected to pass an implementation of
     * the appropriate <CODE>ManagerFactoryParameters</CODE> as
     * defined by the provider.  The provider can then call the
     * specified methods in the <CODE>ManagerFactoryParameters</CODE>
     * implementation to obtain the needed information.
     *
     * @param spec an implementation of a provider-specific parameter
     *		specification
     * @throws InvalidAlgorithmParameterException if there is problem
     *		with the parameters
     * @see TrustManagerFactory#init(ManagerFactoryParameters spec)
     */
    protected abstract void engineInit(ManagerFactoryParameters spec)
        throws InvalidAlgorithmParameterException;

    /** 
     * Returns one trust manager for each type of trust material.
     *
     * @return the trust managers
     */
    protected abstract TrustManager[] engineGetTrustManagers();
}
