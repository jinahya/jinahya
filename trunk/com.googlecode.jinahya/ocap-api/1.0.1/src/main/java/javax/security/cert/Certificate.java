package javax.security.cert;

/**
 * Abstract class for managing a variety of identity certificates.
 * 
 * <p>Abstract class for managing a variety of identity certificates.
 * An identity certificate is a guarantee by a principal that
 * a public key is that of another principal.  (A principal represents
 * an entity such as an individual user, a group, or a corporation.)
 * <p>
 * This class is an abstraction for certificates that have different
 * formats but important common uses.  For example, different types of
 * certificates, such as X.509 and PGP, share general certificate
 * functionality (like encoding and verifying) and
 * some types of information (like a public key).
 * <p>
 * X.509, PGP, and SDSI certificates can all be implemented by
 * subclassing the Certificate class, even though they contain different
 * sets of information, and they store and retrieve the information in
 * different ways.
 * <HR>
 * 
 * 
 */
public abstract class Certificate extends java.lang.Object
{
    public Certificate()
    {
        super();
    }

	/**
	 * Compares this certificate for equality with the specified
	 * object. If the <code>other</code> object is an
	 * <code>instanceof</code> <code>Certificate</code>, then
	 * its encoded form is retrieved and compared with the
	 * encoded form of this certificate.
	 * 
	 * @param other - the object to test for equality with this certificate.
	 * @return true iff the encoded forms of the two certificates match, false otherwise.
	 * @see equals in class Object
	 */
	public boolean equals( Object other)
    {
        return true;
    }

	/**
	 * Returns a hashcode value for this certificate from its
	 * encoded form.
	 * 
	 * @return the hashcode value.
	 * @see hashCode in class Object
	 */
    public int hashCode()
    {
        return 0;
    }

	/**
	 * Returns the encoded form of this certificate. It is
	 * assumed that each certificate type would have only a single
	 * form of encoding; for example, X.509 certificates would
	 * be encoded as ASN.1 DER.
	 * 
	 * @throws CertificateEncodingException - on internal certificate encoding failure
	 */
    public abstract byte[] getEncoded() throws CertificateEncodingException;

	/**
	 * Verifies that this certificate was signed using the
	 * private key that corresponds to the specified public key.
	 * 
	 * @param key - the PublicKey used to carry out the verification.
	 * @throws NoSuchAlgorithmException - on unsupported signature algorithms.
	 * @throws InvalidKeyException - on incorrect key.
	 * @throws NoSuchProviderException - if there's no default provider.
	 * @throws SignatureException - on signature errors.
	 * @throws CertificateException - on encoding errors.
	 */
	public abstract void verify(java.security.PublicKey key)
        throws CertificateException, java.security.NoSuchAlgorithmException,
        java.security.InvalidKeyException,
        java.security.NoSuchProviderException, 
        java.security.SignatureException;

	/**
	 * Verifies that this certificate was signed using the
	 * private key that corresponds to the specified public key.
	 * This method uses the signature verification engine
	 * supplied by the specified provider.
	 * 
	 * @param key - the PublicKey used to carry out the verification.
	 * @param sigProvider - the name of the signature provider.
	 * @throws NoSuchAlgorithmException - on unsupported signature algorithms.
	 * @throws InvalidKeyException - on incorrect key.
	 * @throws NoSuchProviderException - on incorrect provider.
	 * @throws SignatureException - on signature errors.
	 * @throws CertificateException - on encoding errors.
	 */
	public abstract void verify(java.security.PublicKey key,
        java.lang.String sigProvider) throws CertificateException,
        java.security.NoSuchAlgorithmException,
        java.security.InvalidKeyException,
        java.security.NoSuchProviderException,
        java.security.SignatureException;

	/**
	 * Returns a string representation of this certificate.
	 * 
	 * @return a string representation of this certificate.
	 * @see toString in class Object
	 */
	public abstract java.lang.String toString();

	/**
	 * Gets the public key from this certificate.
	 * 
	 * @return the public key.
	 */
	public abstract java.security.PublicKey getPublicKey();
}
