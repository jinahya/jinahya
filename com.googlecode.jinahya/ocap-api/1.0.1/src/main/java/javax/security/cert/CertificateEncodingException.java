package javax.security.cert;

/**
 * Certificate Encoding Exception.
 * 
 * Certificate Encoding Exception. This is thrown whenever an error
 * occurs whilst attempting to encode a certificate.
 * <HR>
 * 
 * 
 */
public class CertificateEncodingException extends CertificateException
{
	/**
	 * Constructs a CertificateEncodingException with no detail message. A
	 * detail message is a String that describes this particular
	 * exception.</DL>
	 * 
	 */
    public CertificateEncodingException()
    {
        super();
    }

	/**
	 * Constructs a CertificateEncodingException with the specified detail
	 * message. A detail message is a String that describes this
	 * particular exception.
	 * 
	 * @param message - the detail message.
	 */
	public CertificateEncodingException(java.lang.String message)
    {
        super(message);
    }
}
  
