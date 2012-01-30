package javax.media;


/*
 * @version @(#)$Header: /home/cvs_test/CVSSource/ocap_reorg/ocap_api/src/javax/media/StartEvent.java,v 1.3 2007/07/13 00:20:13 agordon Exp $
 */
public class StartEvent extends TransitionEvent
{
    public StartEvent(Controller from, int previous, int current, int target,
        Time mediaTime, Time tbTime)
    {
        super(from, previous, current, target);
    }

    public Time getMediaTime()
    {
        return null;
    }

    public Time getTimeBaseTime()
    {
        return null;
    }
}
  
