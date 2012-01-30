package javax.security.cert;

/**
 * Certificate Expired Exception.
 * 
 * Certificate Expired Exception. This is thrown whenever the current
 * <code>Date</code> or the specified <code>Date</code> is after the
 * <code>notAfter</code> date/time specified in the validity period
 * of the certificate.
 * <HR>
 * 
 * 
 */
public class CertificateExpiredException extends CertificateException
{
	/**
	 * Constructs a CertificateExpiredException with no detail message. A
	 * detail message is a String that describes this particular
	 * exception.</DL>
	 * 
	 */
    public CertificateExpiredException()
    {
        super();
    }

	/**
	 * Constructs a CertificateExpiredException with the specified detail
	 * message. A detail message is a String that describes this
	 * particular exception.
	 * 
	 * @param message - the detail message.
	 */
	public CertificateExpiredException(java.lang.String message)
    {
        super(message);
    }
}
 
