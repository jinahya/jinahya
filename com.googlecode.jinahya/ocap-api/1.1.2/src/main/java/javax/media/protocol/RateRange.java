package javax.media.protocol;


/*
 * @version @(#)$Header: /home/cvs_test/CVSSource/ocap_reorg/ocap_api/src/javax/media/protocol/RateRange.java,v 1.3 2007/07/13 00:20:13 agordon Exp $
 */
public class RateRange extends Object
{
    public RateRange(RateRange r)
    {
        super();
    }

    public RateRange(float init, float min, float max, boolean isExact)
    {
        super();
    }

    public float setCurrentRate(float rate)
    {
        return 1.0f;
    }

    public float getCurrentRate()
    {
        return 1.0f;
    }

    public float getMinimumRate()
    {
        return 1.0f;
    }

    public float getMaximumRate()
    {
        return 1.0f;
    }

    public boolean isExact()
    {
        return true;
    }
} 
 
