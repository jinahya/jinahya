package javax.media;


/*
 * @version @(#)$Header: /home/cvs_test/CVSSource/ocap_reorg/ocap_api/src/javax/media/ControllerErrorEvent.java,v 1.3 2007/07/13 00:20:11 agordon Exp $
 */
public class ControllerErrorEvent extends ControllerClosedEvent
{
    public ControllerErrorEvent(Controller from)
    {
        super(from);
    }

    public ControllerErrorEvent(Controller from, String why)
    {
        super(from, why);
    }
} 
  
