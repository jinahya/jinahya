package javax.media;


/*
 * @version @(#)$Header: /home/cvs_test/CVSSource/ocap_reorg/ocap_api/src/javax/media/MediaProxy.java,v 1.3 2007/07/13 00:20:11 agordon Exp $
 */
public interface MediaProxy extends MediaHandler
{
    public abstract javax.media.protocol.DataSource getDataSource() 
        throws java.io.IOException, NoDataSourceException;
}

