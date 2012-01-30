package javax.security.cert;

/**
 * Certificate Parsing Exception.
 * 
 * Certificate Parsing Exception. This is thrown whenever
 * invalid DER encoded certificate is parsed or unsupported DER features
 * are found in the Certificate.
 * <HR>
 * 
 * 
 */
public class CertificateParsingException extends CertificateException
{
	/**
	 * Constructs a CertificateParsingException with no detail message. A
	 * detail message is a String that describes this particular
	 * exception.</DL>
	 * 
	 */
    public CertificateParsingException()
    {
        super();
    }

	/**
	 * Constructs a CertificateParsingException with the specified detail
	 * message. A detail message is a String that describes this
	 * particular exception.
	 * 
	 * @param message - the detail message.
	 */
	public CertificateParsingException(java.lang.String message)
    {
        super(message);
    }
} 
 
