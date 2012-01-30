package javax.security.cert;

/**
 * Certificate is not yet valid exception.
 * 
 * Certificate is not yet valid exception. This is thrown whenever
 * the current <code>Date</code> or the specified <code>Date</code>
 * is before the <code>notBefore</code> date/time in the Certificate
 * validity period.
 * <HR>
 * 
 * 
 */
public class CertificateNotYetValidException extends CertificateException
{
	/**
	 * Constructs a CertificateNotYetValidException with no detail message. A
	 * detail message is a String that describes this particular
	 * exception.</DL>
	 * 
	 */
    public CertificateNotYetValidException()
    {
        super();
    }

	/**
	 * Constructs a CertificateNotYetValidException with the specified detail
	 * message. A detail message is a String that describes this
	 * particular exception.
	 * 
	 * @param message - the detail message.
	 */
	public CertificateNotYetValidException(java.lang.String message)
    {
        super(message);
    }
}
 
