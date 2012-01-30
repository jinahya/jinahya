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
 * @(#)X509KeyManager.java	1.7 05/03/12
 */
  
/*
 * NOTE:
 * Because of various external restrictions (i.e. US export
 * regulations, etc.), the actual source code can not be provided
 * at this time. This file represents the skeleton of the source
 * file, so that javadocs of the API can be created.
 */

package javax.net.ssl;

import java.security.KeyManagementException;
import java.security.PrivateKey;
import java.security.Principal;
import java.security.cert.X509Certificate;
import java.net.Socket;

/** 
 * Instances of this interface manage which X509 certificate-based
 * key pairs are used to authenticate the local side of a secure
 * socket.
 * <P>
 * During secure socket negotiations, implentations
 * call methods in this interface to:
 * <UL>
 * <LI>	determine the set of aliases that are available for negotiations
 *	based on the criteria presented,
 * <LI>	select the <ITALIC> best alias </ITALIC> based on
 *	the criteria presented, and
 * <LI>	obtain the corresponding key material for given aliases.
 * </UL>
 *
 * @since 1.4
 * @version 1.13
 */
public interface X509KeyManager extends KeyManager
{

    /** 
     * Get the matching aliases for authenticating the client side of a secure
     * socket given the public key type and the list of
     * certificate issuer authorities recognized by the peer (if any).
     *
     * @param keyType the key algorithm type name
     * @param issuers the list of acceptable CA issuer subject names,
     *		or null if it does not matter which issuers are used.
     * @return an array of the matching alias names, or null if there
     *		were no matches.
     */
    public String[] getClientAliases(String keyType, Principal[] issuers);

    /** 
     * Choose an alias to authenticate the client side of a secure
     * socket given the public key type and the list of
     * certificate issuer authorities recognized by the peer (if any).
     *
     * @param keyType the key algorithm type name(s), ordered
     *		with the most-preferred key type first.
     * @param issuers the list of acceptable CA issuer subject names
     *		 or null if it does not matter which issuers are used.
     * @param socket the socket to be used for this connection.  This
     *		parameter can be null, in which case this method will
     *		return the most generic alias to use.
     * @return the alias name for the desired key, or null if there
     *		are no matches.
     */
    public String chooseClientAlias(String[] keyType, Principal[] issuers,
        Socket socket);

    /** 
     * Get the matching aliases for authenticating the server side of a secure
     * socket given the public key type and the list of
     * certificate issuer authorities recognized by the peer (if any).
     *
     * @param keyType the key algorithm type name
     * @param issuers the list of acceptable CA issuer subject names
     *		or null if it does not matter which issuers are used.
     * @return an array of the matching alias names, or null
     *		if there were no matches.
     */
    public String[] getServerAliases(String keyType, Principal[] issuers);

    /** 
     * Choose an alias to authenticate the server side of a secure
     * socket given the public key type and the list of
     * certificate issuer authorities recognized by the peer (if any).
     *
     * @param keyType the key algorithm type name.
     * @param issuers the list of acceptable CA issuer subject names
     *		or null if it does not matter which issuers are used.
     * @param socket the socket to be used for this connection.  This
     *		parameter can be null, in which case this method will
     *		return the most generic alias to use.
     * @return the alias name for the desired key, or null if there
     *		are no matches.
     */
    public String chooseServerAlias(String keyType, Principal[] issuers, Socket
        socket);

    /** 
     * Returns the certificate chain associated with the given alias.
     *
     * @param alias the alias name
     * @return the certificate chain (ordered with the user's certificate first
     *		and the root certificate authority last), or null
     *		if the alias can't be found.
     */
    public X509Certificate[] getCertificateChain(String alias);

    /** 
     * Returns the key associated with the given alias.
     *
     * @param alias the alias name
     * @return the requested key, or null if the alias can't be found.
     */
    public PrivateKey getPrivateKey(String alias);
}
