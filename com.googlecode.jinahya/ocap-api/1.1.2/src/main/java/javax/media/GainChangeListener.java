package javax.media;


/*
 * @version @(#)$Header: /home/cvs_test/CVSSource/ocap_reorg/ocap_api/src/javax/media/GainChangeListener.java,v 1.3 2007/07/13 00:20:11 agordon Exp $
 */
public interface GainChangeListener extends java.util.EventListener
{
    public abstract void gainChange(GainChangeEvent event);
}

