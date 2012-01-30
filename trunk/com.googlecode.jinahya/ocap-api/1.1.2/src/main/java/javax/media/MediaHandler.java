package javax.media;


/*
 * @version @(#)$Header: /home/cvs_test/CVSSource/ocap_reorg/ocap_api/src/javax/media/MediaHandler.java,v 1.3 2007/07/13 00:20:11 agordon Exp $
 */
public interface MediaHandler
{
    public abstract void setSource(javax.media.protocol.DataSource source) 
        throws java.io.IOException, IncompatibleSourceException;
} 

