package javax.media.protocol;


/*
 * @version @(#)$Header: /home/cvs_test/CVSSource/ocap_reorg/ocap_api/src/javax/media/protocol/PushDataSource.java,v 1.3 2007/07/13 00:20:13 agordon Exp $
 */
public abstract class PushDataSource extends DataSource
{
    public PushDataSource()
    {
        super();
    }

    public abstract PushSourceStream[] getStreams();
}
  
