package javax.security.cert;

/**
 * This exception indicates one of a variety of certificate problems.
 * 
 * This exception indicates one of a variety of certificate problems.
 * <HR>
 * 
 * 
 */
public class CertificateException extends java.lang.Exception
{
    public CertificateException()
    {
        super();
    }

	/**
	 * Constructs a certificate exception with the given detail
	 * message. A detail message is a String that describes this
	 * particular exception.
	 * 
	 * @param msg - the detail message.
	 */
	public CertificateException(java.lang.String msg)
    {
        super(msg);
    }
}
 
