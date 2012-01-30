/*
 * EASModuleRegistrar.java
 *
 * Created on September 18, 2004, 10:35 AM
 */

package org.ocap.system;

/**
 * An OCAP-J application can set and get a preferred attribute value of an 
 * EAS alert text. 
 * The capability method provides possible attribute values. And also, an 
 * OCAP-J application can set an EASHandler to be notified of a private 
 * descriptor indicating an alternative audio, if the alert_priority=15 but 
 * no audio specified by SCTE 18 is available.
 * The OCAP implementation may throw an exception if a specified preference 
 * doesn't conform to FCC rules or the SCTE 18 specification. For example, 
 * some color combinations could make text unreadable. 
 * See http://www.fcc.gov/eb/eas/ for FCC rules. 
 * </p><p>
 * See also Section 20 <I>Baseline Functionality</I> for detail on EAS 
 * functionality.
 *  
 * @author Shigeaki Watanabe (modify)
 * @since 1.0
 */

public class EASModuleRegistrar {

    /**
     * A constructor of this class.  An application must use the 
     * {@link EASModuleRegistrar#getInstance} method to create an 
     * instance.
     */
    protected EASModuleRegistrar () {
    }


    /**
     * This method returns a sole instance of the 
     * EASModuleRegistrar class. 
     * The EASModuleRegistrar instance is either a singleton for 
     * each OCAP application or a singleton for an entire OCAP 
     * implementation. 
     *
     * @return   a singleton EASModuleRegistrar instance.
     *
     * @throws SecurityException if the caller does not have 
     *             MonitorAppPermission("handler.eas").
     *
     */
    public static EASModuleRegistrar getInstance() {
        return null;
    }

    /**
     * Indicates a font color attribute of an EAS alert text. 
     */
    public final static int EAS_ATTRIBUTE_FONT_COLOR = 1; 

    /**
     * Indicates a font style attribute of an EAS alert text. 
     */
    public final static int EAS_ATTRIBUTE_FONT_STYLE = 2;

    /**
     * Indicates a font face attribute of an EAS alert text. 
     */
    public final static int EAS_ATTRIBUTE_FONT_FACE = 3;

    /**
     * Indicates a font size attribute of an EAS alert text. 
     */
    public final static int EAS_ATTRIBUTE_FONT_SIZE = 4;

    /**
     * Indicates a background color attribute of an EAS alert text. 
     */
    public final static int EAS_ATTRIBUTE_BACK_COLOR = 5;

    /**
     * Indicates a font opacity attribute of an EAS alert text. 
     */
    public final static int EAS_ATTRIBUTE_FONT_OPACITY = 6;

    /**
     * Indicates a background opacity attribute of an EAS alert text. 
     */
    public final static int EAS_ATTRIBUTE_BACK_OPACITY = 7;


    /**
     * <p>
     * This method registers an EASHandler instance. At most only one 
     * EASHandler instance can be registered. Multiple calls of this 
     * method replace the previous instance by a new one. By default, 
     * no instance is registered. If null is specified, this method do 
     * nothing and raise an exception. 
     * </p>
     *
     * @throws IllegalArgumentException  if null is specified. 
     */
    public void registerEASHandler(EASHandler handler) {
    }


    /**
     * <p>
     * This method unregisters the current registered EASHandler instance. 
     * If no EASHandler instance has registered, do nothing. 
     * </p>
     */
    public void unregisterEASHandler() {
    }


    /**
     * <p>
     * This method returns a possible attribute values applied to an EAS alert 
     * text on a screen. Note that the possible font attribute may be different 
     * from the possible font for Java application since the EAS may be 
     * implemented by native language. 
     * </p>
     *
     * @return  an array of possible attribute values of an alert text 
     *          corresponding to the specified attribute parameter. 
     *          <ul>
     *          <li> If the attribute parameter is EAS_ATTRIBUTE_FONT_COLOR, 
     *          an array of java.awt.Color that represents possible font 
     *          color returns. 
     *          The Color.getString() shall return a text expression 
     *          of its color to show a user. 
     *          <li> If the attribute parameter is EAS_ATTRIBUTE_FONT_STYLE, 
     *          an array of String that represents possible font style returns. 
     *          <li> If the attribute parameter is EAS_ATTRIBUTE_FONT_FACE, 
     *          an array of String that represents possible font face name 
     *          returns. 
     *          <li> If the attribute parameter is EAS_ATTRIBUTE_FONT_SIZE, 
     *          an array of Integer of possible font size returns. 
     *          <li> If the attribute parameter is EAS_ATTRIBUTE_BACK_COLOR, 
     *          an array of java.awt.Color that represents possible background 
     *          color returns. The Color.getString() shall return a text 
     *          expression of its color to show a user. 
     *          </ul>
     *
     * @param attribute  one of constants that has a prefix of EAS_ATTRIBUTE_
     *          to specify an attribute to get possible values. 
     *
     * @throws IllegalArgumentException  if the attribute is out of range. 
     */
    public Object[] getEASCapability(int attribute) {
        return null;
    }


    /**
     * <p>
     * This method sets a preferred attribute values applied to an EAS alert 
     * text on a screen. If the specified attribute value is invalid, i.e., 
     * the value is not included in the return value of the 
     * {@link #getEASCapability} method, this method doesn't change current 
     * attribute value and throw an exception. If the specified attribute is 
     * EAS_ATTRIBUTE_FONT_OPACITY or EAS_ATTRIBUTE_BACK_OPACITY, this method accepts   
     * any Float value and the OCAP implementation tries to apply it. The OCAP 
     * implementation may not able to apply the specified opacity exactly. 
     * </p><p>
     * Note that even if the application could set a preference 
     * successfully, the OCAP implementation may not apply it to an 
     * EAS message text on a screen if a conflict with FCC rule 
     * or SCTE 18 specification is found while displaying process. 
     * </p>
     *
     * @param attribute  an array of constants that has a prefix of 
     *          EAS_ATTRIBUTE_ to specify an attribute. 
     *
     * @param value  an array of preferred attribute values to be set to an 
     *          alert text. The i-th item of the value array corresponds to 
     *          the i-th item of the attribute array. 
     *          <ul>
     *          <li> If the attribute parameter is EAS_ATTRIBUTE_FONT_COLOR, 
     *          an instance of java.awt.Color that represents preferred font 
     *          color. 
     *          <li> If the attribute parameter is EAS_ATTRIBUTE_FONT_STYLE, 
     *          an String that represents preferred font style. 
     *          <li> If the attribute parameter is EAS_ATTRIBUTE_FONT_FACE, 
     *          an String that represents preferred font face name. 
     *          <li> If the attribute parameter is EAS_ATTRIBUTE_FONT_SIZE, 
     *          an Integer of preferred font size. 
     *          <li> If the attribute parameter is EAS_ATTRIBUTE_BACK_COLOR, 
     *          an instance of java.awt.Color that represents preferred 
     *          background color. 
     *          <li> If the attribute parameter is EAS_ATTRIBUTE_FONT_OPACITY, 
     *          an Float of preferred font opacity. 
     *          <li> If the attribute parameter is EAS_ATTRIBUTE_BACK_OPACITY, 
     *          an Float of preferred background opacity. 
     *          </ul>
     *
     * @throws IllegalArgumentException  if the attribute is out of range 
     *             or the value is not a possible value or if the specified 
     *             preference conflicts with FCC rules or SCTE 18. For example, 
     *             an EAS message is invisible since same color is specified to 
     *             a font and background. Criteria of visibility depends on 
     *             a manufacturer or a display device etc. 
     */
    public void setEASAttribute(int attribute[], Object value[]) {
    }


    /**
     * <p>
     * This method returns a current attribute values applied to an EAS alert 
     * text on a screen. 
     * </p>
     *
     * @return  a current attribute values of an alert text 
     *          corresponding to the specified attribute parameter. 
     *          <ul>
     *          <li> If the attribute parameter is EAS_ATTRIBUTE_FONT_COLOR, 
     *          an instance of java.awt.Color that represents current font 
     *          color returns. 
     *          <li> If the attribute parameter is EAS_ATTRIBUTE_FONT_STYLE, 
     *          an String that represents current font style returns. 
     *          <li> If the attribute parameter is EAS_ATTRIBUTE_FONT_FACE, 
     *          an String that represents current font face name returns. 
     *          <li> If the attribute parameter is EAS_ATTRIBUTE_FONT_SIZE, 
     *          an Integer of current font size returns. 
     *          <li> If the attribute parameter is EAS_ATTRIBUTE_BACK_COLOR, 
     *          an instance of java.awt.Color that represents current 
     *          background color returns. 
     *          <li> If the attribute parameter is EAS_ATTRIBUTE_FONT_OPACITY, 
     *          an Float of current font opacity returns. 
     *          <li> If the attribute parameter is EAS_ATTRIBUTE_BACK_OPACITY, 
     *          an Float of current background opacity returns. 
     *          </ul>
     *
     * @param attribute  one of constants that has a prefix of EAS_ATTRIBUTE_ 
     *          to specify an attribute to get current values. 
     *
     * @throws IllegalArgumentException  if the attribute is out of range. 
     */
    public Object getEASAttribute(int attribute) {
        return null;
    }
}

