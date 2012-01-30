package javax.media.protocol;


/*
 * @version @(#)$Header: /home/cvs_test/CVSSource/ocap_reorg/ocap_api/src/javax/media/protocol/Controls.java,v 1.3 2007/07/13 00:20:13 agordon Exp $
 */
public interface Controls
{
    public abstract Object[] getControls();

    public abstract Object getControl(String controlType);
}
 
