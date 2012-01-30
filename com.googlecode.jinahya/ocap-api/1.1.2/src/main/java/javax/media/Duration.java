package javax.media;


/*
 * @version @(#)$Header: /home/cvs_test/CVSSource/ocap_reorg/ocap_api/src/javax/media/Duration.java,v 1.3 2007/07/13 00:20:11 agordon Exp $
 */
public interface Duration
{
    public static final Time DURATION_UNBOUNDED = new Time(2001);
    public static final Time DURATION_UNKNOWN = new Time(2001);

    public abstract Time getDuration();
} 
 
