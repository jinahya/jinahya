package javax.media;


/*
 * @version @(#)$Header: /home/cvs_test/CVSSource/ocap_reorg/ocap_api/src/javax/media/DurationUpdateEvent.java,v 1.3 2007/07/13 00:20:11 agordon Exp $
 */
public class DurationUpdateEvent extends ControllerEvent
{
    public DurationUpdateEvent(Controller from, Time newDuration)
    {
        super(from);
    }

    public Time getDuration()
    {
        return null;
    }
}
 
