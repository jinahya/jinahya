package javax.media;


/*
 * @version @(#)$Header: /home/cvs_test/CVSSource/ocap_reorg/ocap_api/src/javax/media/ControllerListener.java,v 1.3 2007/07/13 00:20:11 agordon Exp $
 */
public interface ControllerListener extends java.util.EventListener
{
    public abstract void controllerUpdate(ControllerEvent event);
}

