package javax.media;


/*
 * @version @(#)$Header: /home/cvs_test/CVSSource/ocap_reorg/ocap_api/src/javax/media/Controller.java,v 1.3 2007/07/13 00:20:11 agordon Exp $
 */
public interface Controller extends Clock, Duration
{
    public static final Time LATENCY_UNKNOWN = new Time(2001);
    public static final int Unrealized = 100;
    public static final int Realizing = 200;
    public static final int Realized = 300;
    public static final int Prefetching = 400;
    public static final int Prefetched = 500;
    public static final int Started = 600;

    public abstract int getState();

    public abstract int getTargetState();

    public abstract void realize();

    public abstract void prefetch();

    public abstract void deallocate();

    public abstract void close();

    public abstract Time getStartLatency();

    public abstract Control[] getControls();

    public abstract Control getControl(String forName);

    public abstract void addControllerListener(ControllerListener listener);

    public abstract void removeControllerListener(ControllerListener listener);
}

