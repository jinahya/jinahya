package javax.media;


/*
 * @version @(#)$Header: /home/cvs_test/CVSSource/ocap_reorg/ocap_api/src/javax/media/ResourceUnavailableEvent.java,v 1.3 2007/07/13 00:20:13 agordon Exp $
 */
public class ResourceUnavailableEvent extends ControllerErrorEvent
{
    public ResourceUnavailableEvent(Controller from)
    {
        super(from);
    }

    public ResourceUnavailableEvent(Controller from, String message)
    {
        super(from, message);
    }
} 
  
