package javax.media;


/*
 * @version @(#)$Header: /home/cvs_test/CVSSource/ocap_reorg/ocap_api/src/javax/media/MediaError.java,v 1.3 2007/07/13 00:20:11 agordon Exp $
 */
public class MediaError extends java.lang.Error
{
    public MediaError()
    {
        super();
    }

    public MediaError(String reason)
    {
        super(reason);
    }
}
 
