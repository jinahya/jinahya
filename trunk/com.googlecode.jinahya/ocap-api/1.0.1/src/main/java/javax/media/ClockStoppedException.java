package javax.media;


/*
 * @version @(#)$Header: /home/cvs_test/CVSSource/ocap_reorg/ocap_api/src/javax/media/ClockStoppedException.java,v 1.3 2007/07/13 00:20:10 agordon Exp $
 */
public class ClockStoppedException extends MediaException
{
    public ClockStoppedException()
    {
        super();
    }

    public ClockStoppedException(String reason)
    {
        super(reason);
    }
} 
  
