package javax.media.protocol;


/*
 * @version @(#)$Header: /home/cvs_test/CVSSource/ocap_reorg/ocap_api/src/javax/media/protocol/PushSourceStream.java,v 1.3 2007/07/13 00:20:13 agordon Exp $
 */
public interface PushSourceStream extends SourceStream
{
    public int read(byte buffer[], int offset, int length);

    public int getMinimumTransferSize();

    public void setTransferHandler(SourceTransferHandler transferHandler);
}
