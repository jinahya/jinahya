package javax.media.protocol;


/*
 * @version @(#)$Header: /home/cvs_test/CVSSource/ocap_reorg/ocap_api/src/javax/media/protocol/URLDataSource.java,v 1.3 2007/07/13 00:20:13 agordon Exp $
 */
public class URLDataSource extends PullDataSource
{
    protected java.net.URLConnection conn;

    protected ContentDescriptor contentType;

    // Removed sources field in line with TAM232r26, A.3.1

    protected boolean connected;

    protected URLDataSource()
    {
        super();
    }

    public URLDataSource(java.net.URL url) throws java.io.IOException
    {
        super();
    }

    public PullSourceStream[] getStreams()
    {
        return null;
    }

    public void connect() throws java.io.IOException
    {
    }

    public String getContentType()
    {
        return null;
    }

    public void disconnect()
    {
    }

    public void start() throws java.io.IOException
    {
    }

    public void stop() throws java.io.IOException
    {
    }

    public javax.media.Time getDuration()
    {
        return null;
    }

    public Object[] getControls()
    {
        return null;
    }

    public Object getControl(String controlName)
    {
        return null;
    }
}

