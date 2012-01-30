package javax.media;


/*
 * @version @(#)$Header: /home/cvs_test/CVSSource/ocap_reorg/ocap_api/src/javax/media/ControllerClosedEvent.java,v 1.3 2007/07/13 00:20:11 agordon Exp $
 */
public class ControllerClosedEvent extends ControllerEvent
{
    protected String message;

    public ControllerClosedEvent(Controller from)
    {
        super(from);
    }

    public ControllerClosedEvent(Controller from, String why)
    {
        super(from);
        this.message = why;
    }

    public String getMessage()
    {
        return message;
    }
}
 
