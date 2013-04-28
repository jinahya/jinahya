/*
 * ClosedCaptioningAttribute.java
 *
 * Created on September 18, 2004, 10:10 AM
 */

package org.ocap.media;

/**
 * <p>
 * This class represents a system wide preference of closed-captioning 
 * representation. The OCAP implementation shall display closed-captioning 
 * according to preference values that is specified by this class. 
 * Application developers should be aware that the FCC has defined 
 * strict rules regarding display of CC and EAS 
 * (see http://ftp.fcc.gov/cgb/dro/caption.html for FCC closed captioning 
 * rules). 
 *
 * @author  Shigeaki Watanabe (Panasonic)
 * @version 1.0
 */
public class ClosedCaptioningAttribute {
    /**
     * Indicates a pen color attribute to draw closed-captioning text. 
     * Identical to the "fg color" parameter of SetPenColor command of 
     * EIA-708-B. 
     * For an analog captioning, an equivalent attribute is assigned. 
     */
    public final static int CC_ATTRIBUTE_PEN_FG_COLOR = 0; 

    /**
     * Indicates a pen back ground color attribute to draw closed-captioning 
     * text. 
     * Identical to the "bg color" parameter of SetPenColor command of 
     * EIA-708-B. 
     * For an analog captioning, an equivalent attribute is assigned. 
     */
    public final static int CC_ATTRIBUTE_PEN_BG_COLOR = 1; 

    /**
     * Indicates a pen opacity attribute of a closed-captioning text. 
     * Identical to the "fg opacity" parameter of SetPenColor command of 
     * EIA-708-B. 
     * For an analog captioning, an equivalent attribute is assigned. 
     */
    public final static int CC_ATTRIBUTE_PEN_FG_OPACITY = 2;

    /**
     * Indicates a pen back ground opacity attribute of a closed-captioning 
     * text. 
     * Identical to the "bg opacity" parameter of SetPenColor command of 
     * EIA-708-B. 
     * For an analog captioning, an equivalent attribute is assigned. 
     */
    public final static int CC_ATTRIBUTE_PEN_BG_OPACITY = 3;


    /**
     * Indicates a font style attribute of a closed-captioning text. 
     * Identical to the "font style" parameter of SetPenAttributes command 
     * of EIA-708-B. 
     * For an analog captioning, an equivalent attribute is assigned. 
     */
    public final static int CC_ATTRIBUTE_FONT_STYLE = 4;

    /**
     * Indicates a font size attribute of a closed-captioning text. 
     * Identical to the "pen size" parameter of SetPenAttributes command 
     * of EIA-708-B. 
     * For an analog captioning, an equivalent attribute is assigned. 
     */
    public final static int CC_ATTRIBUTE_PEN_SIZE = 5;

    /**
     * Indicates a font face attribute of a closed-captioning text. 
     * Identical to the "italics" parameter of SetPenAttributes command of 
     * EIA-708-B. 
     * For an analog captioning, an equivalent attribute is assigned. 
     */
    public final static int CC_ATTRIBUTE_FONT_ITALICIZED = 6;

    /**
     * Indicates a font face attribute of a closed-captioning text. 
     * Identical to the "underline" parameter of SetPenAttributes command 
     * of EIA-708-B. 
     * For an analog captioning, an equivalent attribute is assigned. 
     */
    public final static int CC_ATTRIBUTE_FONT_UNDERLINE = 7;

    /**
     * Indicates a window fill color attribute of a closed-captioning window. 
     * Identical to the "fill color" parameter of SetWindowAttributes command 
     * of EIA-708-B. 
     * For an analog captioning, an equivalent attribute is assigned. 
     */
    public final static int CC_ATTRIBUTE_WINDOW_FILL_COLOR = 8;

    /**
     * Indicates a border type attribute of a closed-captioning window. 
     * Identical to the "fill opacity" parameter of SetWindowAttributes 
     * command of EIA-708-B. 
     * For an analog captioning, an equivalent attribute is assigned. 
     */
    public final static int CC_ATTRIBUTE_WINDOW_FILL_OPACITY = 9;

    /**
     * Indicates a border type attribute of a closed-captioning window. 
     * Identical to the "border color" parameter of SetWindowAttributes 
     * command of EIA-708-B. 
     * For an analog captioning, an equivalent attribute is assigned. 
     */
    public final static int CC_ATTRIBUTE_WINDOW_BORDER_TYPE = 10;

    /**
     * Indicates a border color attribute of a closed-captioning window. 
     * Identical to the "border color" parameter of SetWindowAttributes 
     * command of EIA-708-B. 
     * For an analog captioning, an equivalent attribute is assigned. 
     */
    public final static int CC_ATTRIBUTE_WINDOW_BORDER_COLOR = 11;


    /**
     * Indicates a small pen size. 
     */
    public final static int CC_PEN_SIZE_SMALL = 0;

    /**
     * Indicates a standard pen size. 
     */
    public final static int CC_PEN_SIZE_STANDARD = 1;

    /**
     * Indicates a large pen size. 
     */
    public final static int CC_PEN_SIZE_LARGE = 2;


    /**
     * Indicates a opacity value for a solid. 
     */
    public final static int CC_OPACITY_SOLID = 0;

    /**
     * Indicates a opacity value for a flash. 
     */
    public final static int CC_OPACITY_FLASH = 1;

    /**
     * Indicates a opacity value for a translucent. 
     */
    public final static int CC_OPACITY_TRANSLUCENT = 2;

    /**
     * Indicates a opacity value for a transparent. 
     */
    public final static int CC_OPACITY_TRANSPARENT = 3;


    /**
     * Indicates a border type of NONE. 
     */
    public final static int CC_BORDER_NONE = 0;

    /**
     * Indicates a border type of RAISED. 
     */
    public final static int CC_BORDER_RAISED = 1;

    /**
     * Indicates a border type of DEPRESSED. 
     */
    public final static int CC_BORDER_DEPRESSED = 2;

    /**
     * Indicates a border type of UNIFORM. 
     */
    public final static int CC_BORDER_UNIFORM = 3;

    /**
     * Indicates a border type of SHADOW_LEFT. 
     */
    public final static int CC_BORDER_SHADOW_LEFT = 4;

    /**
     * Indicates a border type of SHADOW_RIGHT. 
     */
    public final static int CC_BORDER_SHADOW_RIGHT = 5;



    /**
     * Indicates an analog type closed-captioning. 
     */
    public final static int CC_TYPE_ANALOG = 0;

    /**
     * Indicates an digital type closed-captioning. 
     */
    public final static int CC_TYPE_DIGITAL = 1;



    /**
     * A constructor of this class. 
     * An application shall not call this constructor directly. 
     */
    protected ClosedCaptioningAttribute() {}


    /**
     * This method returns an instance of this class. It is not required to 
     * be a singleton manner. 
     *
     * @return  A ClosedCaptioningAttribute instance. 
     *
     * @throws SecurityException  if the caller doesn't have 
     *          MonitorAppPermission("handler.closedCaptioning"). 
     */
    public static ClosedCaptioningAttribute getInstance()  {
        return null;
    }


    /**
     * <p>
     * This method returns a possible attribute values applied to an 
     * closed-captioning text on a screen. 
     * Note that the possible font attribute may be different from the 
     * possible font for Java application since the closed-captioning module 
     * may be implemented by native language. 
     * </p>
     *
     * @return  an array of possible attribute values of an closed-captioning 
     *          text corresponding to the specified attribute parameter. 
     *          <ul>
     *          <li> If the attribute parameter is CC_ATTRIBUTE_PEN_FG_COLOR 
     *          or CC_ATTRIBUTE_PEN_BG_COLOR, an array of java.awt.Color that 
     *          represents possible font color returns. The Color.getString() 
     *          shall return a text expression of its color to show a user. 
     *          <li> If the attribute parameter is CC_ATTRIBUTE_PEN_FG_OPACITY 
     *          or CC_ATTRIBUTE_PEN_BG_OPACITY, an array of constants that 
     *          represents possible opacity returns. The opacity constants has 
     *          a prefix of CC_OPACITY_. 
     *          <li> If the attribute parameter is CC_ATTRIBUTE_FONT_STYLE, an 
     *          array of String that represents possible font style returns. 
     *          It is recommended that the String is one of font style defined 
     *          in EIA-708-B but not restricted to it. The host device can 
     *          provide a new style. 
     *          <li> If the attribute parameter is CC_ATTRIBUTE_PEN_SIZE, an 
     *          array of constants that represents possible pen size returns. 
     *          The pen size constants has a prefix of CC_PEN_SIZE_. 
     *          <li> If the attribute parameter is CC_ATTRIBUTE_FONT_ITALICIZED, 
     *          an array of possible Integer value (YES=1, NO=0) returns. 
     *          I.e., if the host can select a plane font or an italicized font, 
     *          an array of [0, 1] (or [1, 0]) returns. If the host only supports 
     *          a plane font, [0] returns. 
     *          <li> If the attribute parameter is CC_ATTRIBUTE_FONT_UNDERLINE, 
     *          an array of possible Integer value (YES=1, NO=0) returns. 
     *          See also the CC_ATTRIBUTE_FONT_ITALICIZED description. 
     *          <li> If the attribute parameter is 
     *          CC_ATTRIBUTE_WINDOW_FILL_COLOR, an array of java.awt.Color that 
     *          represents possible window fill color returns. 
     *          The Color.getString() shall return a text expression of its 
     *          color to show a user. 
     *          <li> If the attribute parameter is 
     *          CC_ATTRIBUTE_WINDOW_FILL_OPACITY an array of constants that 
     *          represents possible opacity returns. The opacity constants has 
     *          a prefix of CC_OPACITY_. 
     *          <li> If the attribute parameter is 
     *          CC_ATTRIBUTE_WINDOW_BORDER_TYPE an array of constants that 
     *          represents possible border type returns. The border type 
     *          constants has a prefix of CC_BORDER_. 
     *          <li> If the attribute parameter is 
     *          CC_ATTRIBUTE_WINDOW_BORDER_COLOR, an array of java.awt.Color 
     *          that represents possible window border color returns. 
     *          The Color.getString() shall return a text expression of its 
     *          color to show a user. 
     *          </ul>
     *
     * @param attribute  specify an attribute to get possible values. 
     *          One of constants that has a CC_ATTRIBUTE_ prefix shall be 
     *          specified. 
     *
     * @param ccType  either CC_ANALOG or CC_DIGITAL to specify a type of 
     *          closed-captioning. 
     *
     * @throws IllegalArgumentException  if a specified attribute or 
     *          ccType parameter is out of range. 
     *
     */
    public Object[] getCCCapability(int attribute, int ccType) {
        return null;
    }


    /**
     * <p>
     * This method sets a preferred attribute values applied to a 
     * closed-captioning text on a screen. Some attribute values can be 
     * specified by one call of this method. 
     * If one of the specified attribute value is invalid, i.e., the value 
     * is not included in the return value of the {@link #getCCCapability} 
     * method, this method changes none of current attribute values and 
     * throw an exception. 
     * </p>
     *
     * @param attribute  an array of attributes to be set a preferred value. 
     *          One of constants that has a CC_ATTRIBUTE_ prefix shall be 
     *          specified. 
     *
     * @param value  an array of preferred values to be used to draw a 
     *          closed-captioning text. 
     *          The value shall be one of the return value from the 
     *          {@link ClosedCaptioningAttribute#getCCCapability} method for 
     *          the specified attribute, or null to set a host’s default 
     *          value. 
     *          The i-th item of the value array corresponds to the i-th 
     *          item of the attribute array. 
     *
     * @param ccType  either CC_ANALOG or CC_DIGITAL to specify a type of 
     *          closed-captioning. 
     *
     * @throws IllegalArgumentException  if a specified attribute, value, or 
     *          ccType parameter is out of range or not a capable value, or if 
     *          a length of a specified attribute array doesn’t matches with 
     *          a length of a specified value array. 
     */
    public void setCCAttribute(int attribute[], Object value[], int ccType) {
    }


    /**
     * <p>
     * This method returns a current attribute values applied to a 
     * closed-captioning text on a screen. 
     * </p>
     *
     * @return  a current attribute value corresponding to the specified 
     *          closed-captioning attribute parameter. See the 
     *          {@link #getCCCapability} method for an applicable value. 
     *
     * @param attribute  specify an attribute to get a preferred values. 
     *          One of constants that has a CC_ATTRIBUTE_ prefix shall be 
     *          specified. See the {@link #getCCCapability} method also. 
     *
     * @param ccType either CC_ANALOG or CC_DIGITAL to specify a type of 
     *          closed-captioning. 
     *
     * @throws IllegalArgumentException  if a specified attribute or 
     *          ccType parameter is out of range. 
     */
    public Object getCCAttribute(int attribute, int ccType) {
        return null;
    }
}

