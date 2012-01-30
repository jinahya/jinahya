package javax.media.protocol;


/*
 * @version @(#)$Header: /home/cvs_test/CVSSource/ocap_reorg/ocap_api/src/javax/media/protocol/DataSource.java,v 1.3 2007/07/13 00:20:13 agordon Exp $
 */
public abstract class DataSource extends Object 
    implements Controls, javax.media.Duration
{
    public DataSource()
    {
        super();
    }

    public DataSource(javax.media.MediaLocator source)
    {
        super();
    }

    public void setLocator(javax.media.MediaLocator source)
    {
    }

    public javax.media.MediaLocator getLocator()
    {
        return null;
    }

    protected void initCheck()
    {
    }

    public abstract String getContentType();

    public abstract void connect() throws java.io.IOException;

    public abstract void disconnect();

    public abstract void start() throws java.io.IOException;

    public abstract void stop() throws java.io.IOException;
}
 
