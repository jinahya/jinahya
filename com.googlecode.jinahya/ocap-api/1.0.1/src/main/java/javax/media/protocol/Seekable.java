package javax.media.protocol;


/*
 * @version @(#)$Header: /home/cvs_test/CVSSource/ocap_reorg/ocap_api/src/javax/media/protocol/Seekable.java,v 1.3 2007/07/13 00:20:13 agordon Exp $
 */
public interface Seekable
{
    public abstract long seek(long where);

    public abstract long tell();

    public abstract boolean isRandomAccess();
}
 
