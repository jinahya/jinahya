package org.ocap.media;

/**
 * This interface contains constant definitions that can be used
 * with the {@link MediaAccessHandlerRegistrar#setSignaledBlocking} method.
 * The constants coincide with CEA-766-A April 2001 table 3.
 */
public interface ParentalControlRatings
{
    
    /**
     * See CEA-766-A April 2001 for definitions.
     */

    public static final int TV_NONE = 1;
    public static final int TV_Y = 2;
    public static final int TV_Y7 = 3;
    public static final int TV_Y7_FV = 4;
    public static final int TV_G = 5;
    public static final int TV_PG = 6;
    public static final int TV_PG_D = 7;
    public static final int TV_PG_L = 8;
    public static final int TV_PG_S = 9;
    public static final int TV_PG_V = 10;
    public static final int TV_PG_D_L = 11;
    public static final int TV_PG_D_S = 12;
    public static final int TV_PG_D_V = 13;
    public static final int TV_PG_L_S = 14;
    public static final int TV_PG_L_V = 15;
    public static final int TV_PG_S_V = 16;
    public static final int TV_PG_D_L_S = 17;
    public static final int TV_PG_D_L_V = 18;
    public static final int TV_PG_D_S_V = 19;
    public static final int TV_PG_L_S_V = 20;
    public static final int TV_PG_D_L_S_V = 21;
    public static final int TV_14 = 22;
    public static final int TV_14_D = 23;
    public static final int TV_14_L = 24;
    public static final int TV_14_S = 25;
    public static final int TV_14_V = 26;
    public static final int TV_14_D_L = 27;
    public static final int TV_14_D_S = 28;
    public static final int TV_14_D_V = 29;
    public static final int TV_14_L_S = 30;
    public static final int TV_14_L_V = 31;
    public static final int TV_14_S_V = 32;
    public static final int TV_14_D_L_S = 33;
    public static final int TV_14_D_L_V = 34;
    public static final int TV_14_D_S_V = 35;
    public static final int TV_14_L_S_V = 36;
    public static final int TV_14_D_L_S_V = 37;
    public static final int TV_MA = 38;
    public static final int TV_MA_L = 39;
    public static final int TV_MA_S = 40;
    public static final int TV_MA_V = 41;
    public static final int TV_MA_L_S = 42;
    public static final int TV_MA_L_V = 43;
    public static final int TV_MA_S_V = 44;
    public static final int TV_MA_L_S_V = 45;
    public static final int MPAA_NA = 0x100;
    public static final int MPAA_G = 0x200;
    public static final int MPAA_PG = 0x300;
    public static final int MPAA_PG13 = 0x400;
    public static final int MPAA_R = 0x500;
    public static final int MPAA_NC_17 = 0x600;
    public static final int MPAA_X = 0x700;
    public static final int MPAA_NOT_RATED = 0x800;
}
