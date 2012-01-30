package javax.media;


/*
 * @version @(#)$Header: /home/cvs_test/CVSSource/ocap_reorg/ocap_api/src/javax/media/StopEvent.java,v 1.3 2007/07/13 00:20:13 agordon Exp $
 */
public class StopEvent extends TransitionEvent
{
    public StopEvent(Controller from, int previous, int current, 
        int target, Time mediaTime)
    {
        super(from, previous, current, target);
    }

    public Time getMediaTime()
    {
        return null;
    }
}
 
