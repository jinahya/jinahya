package javax.media;


/*
 * @version @(#)$Header: /home/cvs_test/CVSSource/ocap_reorg/ocap_api/src/javax/media/Manager.java,v 1.3 2007/07/13 00:20:11 agordon Exp $
 */
public final class Manager extends Object
{
    public static final String UNKNOWN_CONTENT_NAME = "unknown_content_name";

    public static Player createPlayer(java.net.URL sourceURL) 
        throws java.io.IOException, NoPlayerException
    {
        return null;
    }

    public static Player createPlayer(MediaLocator sourceLocator) 
        throws java.io.IOException, NoPlayerException
    {
        return null;
    }

    public static Player createPlayer(javax.media.protocol.DataSource source) 
        throws java.io.IOException, NoPlayerException
    {
        return null;
    }

    public static javax.media.protocol.DataSource createDataSource(
        java.net.URL sourceURL) throws java.io.IOException, 
        NoDataSourceException
    {
        return null;
    }

    public static javax.media.protocol.DataSource createDataSource(
        MediaLocator sourceLocator) throws java.io.IOException, 
        NoDataSourceException
    {
        return null;
    }

    public static TimeBase getSystemTimeBase()
    {
        return null;
    }

    public static java.util.Vector getDataSourceList(String protocolName)
    {
        return null;
    }

    public static java.util.Vector getHandlerClassList(String contentName)
    {
        return null;
    }
} 
 
