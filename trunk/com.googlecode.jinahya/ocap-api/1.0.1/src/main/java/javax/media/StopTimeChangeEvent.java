package javax.media;


/*
 * @version @(#)$Header: /home/cvs_test/CVSSource/ocap_reorg/ocap_api/src/javax/media/StopTimeChangeEvent.java,v 1.3 2007/07/13 00:20:13 agordon Exp $
 */
public class StopTimeChangeEvent extends ControllerEvent
{
    public StopTimeChangeEvent(Controller from, Time newStopTime)
    {
        super(from);
    }

    public Time getStopTime()
    {
        return null;
    }
}
 
