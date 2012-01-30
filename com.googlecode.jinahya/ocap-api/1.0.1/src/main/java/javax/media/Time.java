package javax.media;


/*
 * @version @(#)$Header: /home/cvs_test/CVSSource/ocap_reorg/ocap_api/src/javax/media/Time.java,v 1.3 2007/07/13 00:20:13 agordon Exp $
 */
public class Time extends java.lang.Object
{
    public static final long ONE_SECOND = 1000000000;
    protected long nanoseconds;

    public Time(long nano)
    {
        super();
        this.nanoseconds = nano;
    }

    public Time(double seconds)
    {
        super();
    }
        
    protected long secondsToNanoseconds(double seconds)
    {
        return 0;
    }

    public long getNanoseconds()
    {
        return nanoseconds;
    }

    public double getSeconds()
    {
        return 1.0;
    }
}

