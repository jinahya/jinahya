package javax.media;


/*
 * @version @(#)$Header: /home/cvs_test/CVSSource/ocap_reorg/ocap_api/src/javax/media/CachingControl.java,v 1.3 2007/07/13 00:20:10 agordon Exp $
 */
public interface CachingControl extends Control
{
    public static final long LENGTH_UNKNOWN = Long.MAX_VALUE;

    public abstract boolean isDownloading();

    public abstract long getContentLength();

    public abstract long getContentProgress();

    public abstract java.awt.Component getProgressBarComponent();

    public abstract java.awt.Component getControlComponent();
}

