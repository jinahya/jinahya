package javax.media.protocol;


/*
 * @version @(#)$Header: /home/cvs_test/CVSSource/ocap_reorg/ocap_api/src/javax/media/protocol/PullSourceStream.java,v 1.3 2007/07/13 00:20:13 agordon Exp $
 */
public interface PullSourceStream extends SourceStream
{
    public abstract boolean willReadBlock();

    public abstract int read(byte buffer[], int offset, int length) 
        throws java.io.IOException;
}
 
