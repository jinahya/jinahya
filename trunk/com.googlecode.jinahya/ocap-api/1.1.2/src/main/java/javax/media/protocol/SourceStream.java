package javax.media.protocol;


/*
 * @version @(#)$Header: /home/cvs_test/CVSSource/ocap_reorg/ocap_api/src/javax/media/protocol/SourceStream.java,v 1.3 2007/07/13 00:20:13 agordon Exp $
 */
public interface SourceStream extends Controls
{
    public static final long LENGTH_UNKNOWN = -1;

    public abstract ContentDescriptor getContentDescriptor();

    public abstract long getContentLength();

    public abstract boolean endOfStream();
}
 
