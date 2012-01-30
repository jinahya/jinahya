package javax.media;


/*
 * @version @(#)$Header: /home/cvs_test/CVSSource/ocap_reorg/ocap_api/src/javax/media/EndOfMediaEvent.java,v 1.3 2007/07/13 00:20:11 agordon Exp $
 */
public class EndOfMediaEvent extends StopEvent
{
    public EndOfMediaEvent(Controller from, int previous, int current,
        int target, Time mediaTime)
    {
        super(from, previous, current, target, mediaTime);
    }
} 
 
