package javax.media;


/*
 * @version @(#)$Header: /home/cvs_test/CVSSource/ocap_reorg/ocap_api/src/javax/media/GainChangeEvent.java,v 1.3 2007/07/13 00:20:11 agordon Exp $
 */
public class GainChangeEvent extends java.util.EventObject
    implements MediaEvent
{
    public GainChangeEvent(GainControl from, boolean mute, float dB,
        float level)
    {
        super(from);
    }

    public java.lang.Object getSource()
    {
        return null;
    }

    public GainControl getSourceGainControl()
    {
        return null;
    }

    public float getDB()
    {
        return 1.0f;
    }

    public float getLevel()
    {
        return 1.0f;
    }

    public boolean getMute()
    {
        return true;
    }
} 
 
