package javax.media.protocol;


/*
 * @version @(#)$Header: /home/cvs_test/CVSSource/ocap_reorg/ocap_api/src/javax/media/protocol/Positionable.java,v 1.3 2007/07/13 00:20:13 agordon Exp $
 */
public interface Positionable
{
    public static final int RoundUp = 1;
    public static final int RoundDown  = 2;
    public static final int RoundNearest = 3;

    public abstract javax.media.Time setPosition(
        javax.media.Time where, int rounding);

    public abstract boolean isRandomAccess();
}
 
