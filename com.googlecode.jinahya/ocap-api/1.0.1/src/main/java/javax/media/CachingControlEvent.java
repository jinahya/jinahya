package javax.media;


/*
 * @version @(#)$Header: /home/cvs_test/CVSSource/ocap_reorg/ocap_api/src/javax/media/CachingControlEvent.java,v 1.3 2007/07/13 00:20:10 agordon Exp $
 */
public class CachingControlEvent extends ControllerEvent
{
    public CachingControlEvent(Controller from, CachingControl cacheControl,
        long progress)
    {
        super(from);
    }

    public CachingControl getCachingControl()
    {
        return null;
    }

    public long getContentProgress()
    {
        return 0;
    }
} 
 
