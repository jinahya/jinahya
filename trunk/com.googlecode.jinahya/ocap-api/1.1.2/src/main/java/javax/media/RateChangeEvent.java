package javax.media;


/*
 * @version @(#)$Header: /home/cvs_test/CVSSource/ocap_reorg/ocap_api/src/javax/media/RateChangeEvent.java,v 1.3 2007/07/13 00:20:13 agordon Exp $
 */
public class RateChangeEvent extends ControllerEvent
{
    public RateChangeEvent(Controller from, float newRate)
    {
        super(from);
    }

    public float getRate()
    {
        return 1.0f;
    }
} 
  