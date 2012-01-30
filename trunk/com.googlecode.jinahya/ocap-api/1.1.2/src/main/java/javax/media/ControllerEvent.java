package javax.media;


/*
 * @version @(#)$Header: /home/cvs_test/CVSSource/ocap_reorg/ocap_api/src/javax/media/ControllerEvent.java,v 1.3 2007/07/13 00:20:11 agordon Exp $
 */
public class ControllerEvent extends java.util.EventObject
    implements MediaEvent
{
    public ControllerEvent(Controller from)
    {
        super(from);
    }

    public Controller getSourceController()
    {
        return null;
    }

    public java.lang.Object getSource()
    {
        return null;
    }
}

