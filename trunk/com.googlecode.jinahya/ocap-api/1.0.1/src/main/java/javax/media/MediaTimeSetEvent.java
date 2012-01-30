package javax.media;


/*
 * @version @(#)$Header: /home/cvs_test/CVSSource/ocap_reorg/ocap_api/src/javax/media/MediaTimeSetEvent.java,v 1.3 2007/07/13 00:20:11 agordon Exp $
 */
public class MediaTimeSetEvent extends ControllerEvent
{
    public MediaTimeSetEvent(Controller from, Time newMediaTime)
    {
        super(from);
    }

    public Time getMediaTime()
    {
        return null;
    }
}
  
