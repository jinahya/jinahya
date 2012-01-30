package javax.media;


/*
 * @version @(#)$Header: /home/cvs_test/CVSSource/ocap_reorg/ocap_api/src/javax/media/InternalErrorEvent.java,v 1.3 2007/07/13 00:20:11 agordon Exp $
 */
public class InternalErrorEvent extends ControllerErrorEvent
{
    public InternalErrorEvent(Controller from)
    {
        super(from);
    }

    public InternalErrorEvent(Controller from, String message)
    {
        super(from, message);
    }
}
 
