package javax.media;


/*
 * @version @(#)$Header: /home/cvs_test/CVSSource/ocap_reorg/ocap_api/src/javax/media/ConnectionErrorEvent.java,v 1.3 2007/07/13 00:20:10 agordon Exp $
 */
public class ConnectionErrorEvent extends ControllerErrorEvent
{
    public ConnectionErrorEvent(Controller from)
    {
        super(from);
    }

    public ConnectionErrorEvent(Controller from, String why)
    {
        super(from, why);
    }
} 
 
