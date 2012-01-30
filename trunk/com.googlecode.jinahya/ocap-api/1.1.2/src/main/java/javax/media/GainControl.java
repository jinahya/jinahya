package javax.media;


/*
 * @version @(#)$Header: /home/cvs_test/CVSSource/ocap_reorg/ocap_api/src/javax/media/GainControl.java,v 1.3 2007/07/13 00:20:11 agordon Exp $
 */
public interface GainControl extends Control
{
    public abstract void setMute(boolean mute);

    public abstract boolean getMute();

    public abstract float setDB(float gain);

    public abstract float getDB();

    public abstract float setLevel(float level);

    public abstract float getLevel();

    public abstract void addGainChangeListener(GainChangeListener listener);

    public abstract void removeGainChangeListener(GainChangeListener listener);
} 
 
