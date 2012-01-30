package javax.media;


/*
 * @version @(#)$Header: /home/cvs_test/CVSSource/ocap_reorg/ocap_api/src/javax/media/Player.java,v 1.3 2007/07/13 00:20:11 agordon Exp $
 */
public interface Player extends MediaHandler, Controller, Duration
{
    public abstract java.awt.Component getVisualComponent();

    public abstract GainControl getGainControl();

    public abstract java.awt.Component getControlPanelComponent();

    public abstract void start();

    public abstract void addController(Controller newController) 
        throws IncompatibleTimeBaseException;

    public abstract void removeController(Controller oldController);
}

