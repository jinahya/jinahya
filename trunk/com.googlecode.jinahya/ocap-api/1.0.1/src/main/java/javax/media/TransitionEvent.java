package javax.media;


/*
 * @version @(#)$Header: /home/cvs_test/CVSSource/ocap_reorg/ocap_api/src/javax/media/TransitionEvent.java,v 1.3 2007/07/13 00:20:13 agordon Exp $
 */
public class TransitionEvent extends ControllerEvent
{
    public TransitionEvent(Controller from, int previous, int current,
        int target)
    {
        super(from);
    }

    public int getPreviousState()
    {
        return 0;
    }

    public int getCurrentState()
    {
        return 0;
    }

    public int getTargetState()
    {
        return 0;
    }
}  
 
