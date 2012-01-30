package javax.media;


/*
 * @version @(#)$Header: /home/cvs_test/CVSSource/ocap_reorg/ocap_api/src/javax/media/Clock.java,v 1.3 2007/07/13 00:20:10 agordon Exp $
 */
public interface Clock
{
    public static final Time RESET = new Time(2001);

    public abstract void setTimeBase(TimeBase master) 
        throws IncompatibleTimeBaseException;

    public abstract void syncStart(Time at);

    public abstract void stop();

    public abstract void setStopTime(Time stopTime);

    public abstract Time getStopTime();

    public abstract void setMediaTime(Time now);

    public abstract Time getMediaTime();

    public abstract long getMediaNanoseconds();

    public abstract Time getSyncTime();

    public abstract TimeBase getTimeBase();

    public abstract Time mapToTimeBase(Time t) throws ClockStoppedException;

    public abstract float getRate();

    public abstract float setRate(float factor);
} 
  
