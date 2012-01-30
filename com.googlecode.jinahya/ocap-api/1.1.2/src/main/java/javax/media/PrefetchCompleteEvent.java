package javax.media;


/*
 * @version @(#)$Header: /home/cvs_test/CVSSource/ocap_reorg/ocap_api/src/javax/media/PrefetchCompleteEvent.java,v 1.3 2007/07/13 00:20:11 agordon Exp $
 */
public class PrefetchCompleteEvent extends TransitionEvent
{
    public PrefetchCompleteEvent(Controller from, int previous,
        int current, int target)
    {
        super(from, previous, current, target);
    }
} 
  
