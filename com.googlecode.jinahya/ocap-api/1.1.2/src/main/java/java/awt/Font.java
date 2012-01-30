/*
</p>
<a name="notice"><strong><center>
NOTICE
</center></strong><br>
<br>

(c) 2005-2007 Sun Microsystems, Inc. All Rights Reserved.
<p>
Neither this file nor any files generated from it describe a complete
specification, and they may only be used as described below. For
example, no permission is given for you to incorporate this file, in
whole or in part, in an implementation of a Java specification.
<p>
Sun Microsystems Inc. owns the copyright in this file and it is provided
to you for informative, as opposed to normative, use. The file and any
files generated from it may be used to generate other informative
documentation, such as a unified set of documents of API signatures for
a platform that includes technologies expressed as Java APIs. The file
may also be used to produce "compilation stubs," which allow
applications to be compiled and validated for such platforms.
<p>
Any work generated from this file, such as unified javadocs or compiled
stub files, must be accompanied by this notice in its entirety.
<p>
This work corresponds to the API signatures of JSR 217: Personal Basis
Profile 1.1. In the event of a discrepency between this work and the
JSR 217 specification, which is available at
http://www.jcp.org/en/jsr/detail?id=217, the latter takes precedence.
*/


  


package java.awt;

import java.io.*;

// import java.awt.font.FontRenderContext;
// import java.awt.font.GlyphVector;
// import java.awt.font.LineMetrics;
// import java.awt.font.TextAttribute;
// import java.awt.font.TextLayout;
// import java.awt.font.TransformAttribute;
// import java.awt.geom.AffineTransform;
// import java.awt.geom.Rectangle2D;
// import java.awt.peer.FontPeer;
import java.lang.ref.SoftReference;
import java.text.AttributedCharacterIterator.Attribute;
import java.text.CharacterIterator;
import java.text.StringCharacterIterator;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Locale;
import java.util.Map;
// import sun.awt.font.NativeFontWrapper;
// import sun.awt.font.StandardGlyphVector;
// import sun.java2d.FontSupport;
// import sun.java2d.SunGraphicsEnvironment;

// PBP/PP 6217602

/** 
 * The <code>Font</code> class represents fonts, which are used to
 * render text in a visible way.
 * A font provides the information needed to map sequences of
 * <em>characters</em> to sequences of <em>glyphs</em>
 * and to render sequences of glyphs on <code>Graphics</code> and
 * <code>Component</code> objects.
 * 
 * <h4>Characters and Glyphs</h4>
 * 
 * A <em>character</em> is a symbol that represents an item such as a letter,
 * a digit, or punctuation in an abstract way. For example, <code>'g'</code>,
 * <font size=-1>LATIN SMALL LETTER G</font>, is a character.
 * <p>
 * A <em>glyph</em> is a shape used to render a character or a sequence of
 * characters. In simple writing systems, such as Latin, typically one glyph
 * represents one character. In general, however, characters and glyphs do not
 * have one-to-one correspondence. For example, the character '&aacute;'
 * <font size=-1>LATIN SMALL LETTER A WITH ACUTE</font>, can be represented by
 * two glyphs: one for 'a' and one for '&acute;'. On the other hand, the
 * two-character string "fi" can be represented by a single glyph, an
 * "fi" ligature. In complex writing systems, such as Arabic or the South
 * and South-East Asian writing systems, the relationship between characters
 * and glyphs can be more complicated and involve context-dependent selection
 * of glyphs as well as glyph reordering.
 *
 * A font encapsulates the collection of glyphs needed to render a selected set
 * of characters as well as the tables needed to map sequences of characters to
 * corresponding sequences of glyphs.
 *
 * <h4>Physical and Logical Fonts</h4>
 *
 * The Java 2 platform distinguishes between two kinds of fonts:
 * <em>physical</em> fonts and <em>logical</em> fonts.
 * <p>
 * <em>Physical</em> fonts are the actual font libraries containing glyph data
 * and tables to map from character sequences to glyph sequences, using a font
 * technology such as TrueType or PostScript Type 1.
 * 
 * Physical fonts may use names such as Helvetica, Palatino, HonMincho, or
 * any number of other font names.
 * Typically, each physical font supports only a limited set of writing
 * systems, for example, only Latin characters or only Japanese and Basic
 * Latin.
 * The set of available physical fonts varies between configurations.
 * 
 * <p>
 * <em>Logical</em> fonts are the five font families defined by the Java
 * platform which must be supported by any Java runtime environment:
 * Serif, SansSerif, Monospaced, Dialog, and DialogInput.
 * These logical fonts are not actual font libraries. Instead, the logical
 * font names are mapped to physical fonts by the Java runtime environment.
 * The mapping is implementation and usually locale dependent, so the look
 * and the metrics provided by them vary.
 * Typically, each logical font name maps to several physical fonts in order to
 * cover a large range of characters.
 * <p>
 * 
 * <p>
 * For a discussion of the relative advantages and disadvantages of using
 * physical or logical fonts, see the
 * <a href="../../../guide/intl/faq.html#fonts">Internationalization FAQ</a>
 * document.
 *
 * <h4>Font Faces and Names</h4>
 * 
 * A <code>Font</code> 
 * can have many faces, such as heavy, medium, oblique, gothic and
 * regular. All of these faces have similar typographic design.
 * <p>
 * There are three different names that you can get from a 
 * <code>Font</code> object.  The <em>logical font name</em> is simply the
 * name that was used to construct the font.
 * The <em>font face name</em>, or just <em>font name</em> for
 * short, is the name of a particular font face, like Helvetica Bold. The
 * <em>family name</em> is the name of the font family that determines the
 * typographic design across several faces, like Helvetica.
 * <p>
 * The <code>Font</code> class represents an instance of a font face from
 * a collection of  font faces that are present in the system resources
 * of the host system.  As examples, Arial Bold and Courier Bold Italic
 * are font faces.  There can be several <code>Font</code> objects
 * associated with a font face, each differing in size, style
 *  and font features.  
 * 
 *
 * @version 	1.181, 01/23/03
 */
public class Font implements Serializable
{
    /** 
     * The plain style constant.
     */
    public static final int PLAIN = 0;

    /** 
     * The bold style constant.  This can be combined with the other style
     * constants (except PLAIN) for mixed styles.
     */
    public static final int BOLD = 1;

    /** 
     * The italicized style constant.  This can be combined with the other
     * style constants (except PLAIN) for mixed styles.
     */
    public static final int ITALIC = 2;

    // /** 
     // * The baseline used in most Roman scripts when laying out text.
     // */
    // public static final int ROMAN_BASELINE = 0;
// 
    // /** 
     // * The baseline used in ideographic scripts like Chinese, Japanese,
     // * and Korean when laying out text.
     // */
    // public static final int CENTER_BASELINE = 0;
// 
    // /** 
     // * The baseline used in Devanigiri and similar scripts when laying
     // * out text.
     // */
    // public static final int HANGING_BASELINE = 0;
// 
    // /** 
     // * Create a Font of type TRUETYPE.
     // * In future other types may be added to support other font types.
     // */
    // public static final int TRUETYPE_FONT = 0;

    /** 
     * The logical name of this <code>Font</code>, as passed to the
     * constructor.
     * @since JDK1.0
     *
     * @serial
     * @see #getName
     */
    protected String name;

    /** 
     * The style of this <code>Font</code>, as passed to the constructor.
     * This style can be PLAIN, BOLD, ITALIC, or BOLD+ITALIC.
     * @since JDK1.0
     *
     * @serial
     * @see #getStyle()
     */
    protected int style;

    /** 
     * The point size of this <code>Font</code>, rounded to integer.
     * @since JDK1.0
     *
     * @serial
     * @see #getSize()
     */
    protected int size;

    /** 
     // * The point size of this <code>Font</code> in <code>float</code>.
     // *
     // * @serial
     // * @see #getSize()
     // * @see #getSize2D()
     // */
    // protected float pointSize;
// 
    // /** 
     // * A flag to layoutGlyphVector indicating that text is left-to-right as
     // * determined by Bidi analysis.
     // */
    // public static final int LAYOUT_LEFT_TO_RIGHT = 0;
// 
    // /** 
     // * A flag to layoutGlyphVector indicating that text is right-to-left as
     // * determined by Bidi analysis.
     // */
    // public static final int LAYOUT_RIGHT_TO_LEFT = 0;
// 
    // /** 
     // * A flag to layoutGlyphVector indicating that text in the char array
     // * before the indicated start should not be examined.
     // */
    // public static final int LAYOUT_NO_START_CONTEXT = 0;
// 
    // /** 
     // * A flag to layoutGlyphVector indicating that text in the char array
     // * after the indicated limit should not be examined.
     // */
    // public static final int LAYOUT_NO_LIMIT_CONTEXT = 0;

    /** 
     * A map of font attributes available in this font.
     * Attributes include things like ligatures and glyph substitution.
     *
     * @serial
     * @see #getAttributes()
     */
    private Hashtable fRequestedAttributes;

    /** 
     * The <code>Font</code> Serializable Data Form.
     *
     * @serial
     */
    private int fontSerializedDataVersion;

    /*
     * JDK 1.1 serialVersionUID
     */
    private static final long serialVersionUID = -4206021311591459213L;

    /** 
     * Creates a new <code>Font</code> from the specified name, style and
     * point size.
     * @param name the font name.  This can be a logical font name or a
     * font face name. A logical name must be either: Dialog, DialogInput,
     * Monospaced, Serif, or SansSerif.  If <code>name</code> is
     * <code>null</code>, the <code>name</code> of the new
     * <code>Font</code> is set to the name "Default".
     * @param style the style constant for the <code>Font</code>
     * The style argument is an integer bitmask that may
     * be PLAIN, or a bitwise union of BOLD and/or ITALIC
     * (for example, ITALIC or BOLD|ITALIC).
     * If the style argument does not conform to one of the expected
     * integer bitmasks then the style is set to PLAIN.
     * @param size the point size of the <code>Font</code>
     * @see GraphicsEnvironment#getAvailableFontFamilyNames
     * @since JDK1.0
     */
    public Font(String name, int style, int size) { }

    /** 
     * Creates a new <code>Font</code> with the specified attributes.
     * This <code>Font</code> only recognizes keys defined in 
     * {@link TextAttribute} as attributes.  If <code>attributes</code>
     * is <code>null</code>, a new <code>Font</code> is initialized
     * with default attributes.
     * @param attributes the attributes to assign to the new
     *		<code>Font</code>, or <code>null</code>
     */
    public Font(Map attributes) { }

    // /** 
     // * Gets the peer of this <code>Font</code>.
     // * @return  the peer of the <code>Font</code>.
     // * @since JDK1.1
     // * @deprecated Font rendering is now platform independent.
     // */
    // public FontPeer getPeer() { }

    /** 
     * Returns a <code>Font</code> appropriate to this attribute set.
     *
     * @param attributes the attributes to assign to the new 
     *		<code>Font</code>
     * @return a new <code>Font</code> created with the specified
     * 		attributes
     * @since 1.2
     * @see java.awt.font.TextAttribute
     */
    public static Font getFont(Map attributes) { return null; }

    // /** 
     // * Returns a new <code>Font</code> with the specified font type
     // * and input data.  The new <code>Font</code> is
     // * created with a point size of 1 and style {@link #PLAIN PLAIN}.
     // * This base font can then be used with the <code>deriveFont</code>
     // * methods in this class to derive new <code>Font</code> objects with
     // * varying sizes, styles, transforms and font features.  This
     // * method does not close the {@link InputStream}.
     // * @param fontFormat the type of the <code>Font</code>, which is
     // * {@link #TRUETYPE_FONT TRUETYPE_FONT} if a TrueType is desired.  Other
     // * types might be provided in the future.
     // * @param fontStream an <code>InputStream</code> object representing the
     // * input data for the font.
     // * @return a new <code>Font</code> created with the specified font type.
     // * @throws IllegalArgumentException if <code>fontType</code> is not
     // *     <code>TRUETYPE_FONT</code>
     // * @throws FontFormatException if the <code>fontStream</code> data does
     // *     not contain the required Truetype font tables.
     // * @throws IOException if the <code>fontStream</code>
     // *     cannot be completely read.
     // * @since 1.3
     // */
    // public static Font createFont(int fontFormat, InputStream fontStream)
        // throws FontFormatException, IOException
    // { }

    // /** 
     // * Returns a copy of the transform associated with this 
     // * <code>Font</code>.
     // * @return an {@link AffineTransform} object representing the
     // *		transform attribute of this <code>Font</code> object.
     // */
    // public AffineTransform getTransform() { }

// PBP/PP
    /** 
     * Returns the family name of this <code>Font</code>.  
     * 
     * <p>The family name of a font is font specific. Two fonts such as 
     * Helvetica Italic and Helvetica Bold have the same family name, 
     * <i>Helvetica</i>, whereas their font face names are 
     * <i>Helvetica Bold</i> and <i>Helvetica Italic</i>. The list of 
     * available family names may be obtained by using the 
     * {@link GraphicsEnvironment#getAvailableFontFamilyNames()} method.
     * 
     * <p>Use <code>getName</code> to get the logical name of the font.
     * 
     * @return a <code>String</code> that is the family name of this
     *		<code>Font</code>.
     * 
     * @see #getName
     * @since JDK1.1
     */
    public String getFamily() { return null;}

    // /** 
     // * Returns the family name of this <code>Font</code>, localized for
     // * the specified locale.
     // * 
     // * <p>The family name of a font is font specific. Two fonts such as 
     // * Helvetica Italic and Helvetica Bold have the same family name, 
     // * <i>Helvetica</i>, whereas their font face names are 
     // * <i>Helvetica Bold</i> and <i>Helvetica Italic</i>. The list of 
     // * available family names may be obtained by using the 
     // * {@link GraphicsEnvironment#getAvailableFontFamilyNames()} method.
     // * 
     // * <p>Use <code>getFontName</code> to get the font face name of the font.
     // * @param l locale for which to get the family name
     // * @return a <code>String</code> representing the family name of the
     // *		font, localized for the specified locale.
     // * @see #getFontName
     // * @see java.util.Locale
     // * @since 1.2
     // */
    // public String getFamily(Locale l) { }

    // /** 
     // * Returns the postscript name of this <code>Font</code>.
     // * Use <code>getFamily</code> to get the family name of the font.
     // * Use <code>getFontName</code> to get the font face name of the font.
     // * @return a <code>String</code> representing the postscript name of
     // *		this <code>Font</code>.
     // * @since 1.2
     // */
    // public String getPSName() { }

// PBP/PP
    /** 
     * Returns the logical name of this <code>Font</code>.
     * Use <code>getFamily</code> to get the family name of the font.
     * 
     * @return a <code>String</code> representing the logical name of
     *		this <code>Font</code>.
     * @see #getFamily
     * @since JDK1.0
     */
    public String getName() { return null;}

    // /** 
     // * Returns the font face name of this <code>Font</code>.  For example,
     // * Helvetica Bold could be returned as a font face name.
     // * Use <code>getFamily</code> to get the family name of the font.
     // * Use <code>getName</code> to get the logical name of the font.
     // * @return a <code>String</code> representing the font face name of 
     // *		this <code>Font</code>.
     // * @see #getFamily
     // * @see #getName
     // * @since 1.2
     // */
    // public String getFontName() { }
// 
    // /** 
     // * Returns the font face name of the <code>Font</code>, localized
     // * for the specified locale. For example, Helvetica Fett could be
     // * returned as the font face name.
     // * Use <code>getFamily</code> to get the family name of the font.
     // * @param l a locale for which to get the font face name
     // * @return a <code>String</code> representing the font face name,
     // *		localized for the specified locale.
     // * @see #getFamily
     // * @see java.util.Locale
     // */
    // public String getFontName(Locale l) { }

    /** 
     * Returns the style of this <code>Font</code>.  The style can be
     * PLAIN, BOLD, ITALIC, or BOLD+ITALIC.
     * @return the style of this <code>Font</code>
     * @see #isPlain
     * @see #isBold
     * @see #isItalic
     * @since JDK1.0
     */
    public int getStyle() { return 0; }

// PBP/PP
// [6187222]

    /** 
     * Returns the point size of this <code>Font</code>, rounded to
     * an integer.
     * Most users are familiar with the idea of using <i>point size</i> to
     * specify the size of glyphs in a font. This point size defines a
     * measurement between the baseline of one line to the baseline of the
     * following line in a single spaced text document. The point size is
     * based on <i>typographic points</i>, approximately 1/72 of an inch.
     * <p>
     * The Java(tm)2D API adopts the convention that one point is
     * equivalent to one unit in user coordinates.  In this case one point
     * is 1/72 of an inch.
     * @return the point size of this <code>Font</code> in 1/72 of an 
     *		inch units.
     * @since JDK1.0
     */
    public int getSize() { return 0; }

    // /** 
     // * Returns the point size of this <code>Font</code> in
     // * <code>float</code> value.
     // * @return the point size of this <code>Font</code> as a
     // * <code>float</code> value.
     // * @see #getSize
     // * @since 1.2
     // */
    // public float getSize2D() { }

    /** 
     * Indicates whether or not this <code>Font</code> object's style is
     * PLAIN.
     * @return    <code>true</code> if this <code>Font</code> has a
     * 		  PLAIN sytle;
     *            <code>false</code> otherwise.
     * @see       java.awt.Font#getStyle
     * @since     JDK1.0
     */
    public boolean isPlain() { return false;}

    /** 
     * Indicates whether or not this <code>Font</code> object's style is
     * BOLD.
     * @return    <code>true</code> if this <code>Font</code> object's
     *		  style is BOLD;
     *            <code>false</code> otherwise.
     * @see       java.awt.Font#getStyle
     * @since     JDK1.0
     */
    public boolean isBold() { return false; }

    /** 
     * Indicates whether or not this <code>Font</code> object's style is
     * ITALIC.
     * @return    <code>true</code> if this <code>Font</code> object's
     *		  style is ITALIC;
     *            <code>false</code> otherwise.
     * @see       java.awt.Font#getStyle
     * @since     JDK1.0
     */
    public boolean isItalic() { return false; }

    // /** 
     // * Indicates whether or not this <code>Font</code> object has a
     // * transform that affects its size in addition to the Size
     // * attribute.
     // * @return	<code>true</code> if this <code>Font</code> object
     // *		has a non-identity AffineTransform attribute.
     // *		<code>false</code> otherwise.
     // * @see	java.awt.Font#getTransform
     // * @since	1.4
     // */
    // public boolean isTransformed() { }

    /** 
     * Returns a <code>Font</code> object from the system properties list.
     * @param nm the property name
     * @return a <code>Font</code> object that the property name
     *		describes.
     * @since 1.2
     */
    public static Font getFont(String nm) { return null;}

    /** 
     * Returns the <code>Font</code> that the <code>str</code> 
     * argument describes.
     * To ensure that this method returns the desired Font, 
     * format the <code>str</code> parameter in
     * one of two ways:
     * <p>
     * "fontfamilyname-style-pointsize" or <br>
     * "fontfamilyname style pointsize"<p>
     * in which <i>style</i> is one of the three 
     * case-insensitive strings:
     * <code>"BOLD"</code>, <code>"BOLDITALIC"</code>, or
     * <code>"ITALIC"</code>, and pointsize is a decimal
     * representation of the point size.
     * For example, if you want a font that is Arial, bold, and
     * a point size of 18, you would call this method with:
     * "Arial-BOLD-18".
     * <p>
     * The default size is 12 and the default style is PLAIN.
     * If you don't specify a valid size, the returned 
     * <code>Font</code> has a size of 12.  If you don't specify 
     * a valid style, the returned Font has a style of PLAIN.
     * If you do not provide a valid font family name in 
     * the <code>str</code> argument, this method still returns 
     * a valid font with a family name of "dialog".
     * To determine what font family names are available on
     * your system, use the
     * {@link GraphicsEnvironment#getAvailableFontFamilyNames()} method.
     * If <code>str</code> is <code>null</code>, a new <code>Font</code>
     * is returned with the family name "dialog", a size of 12 and a 
     * PLAIN style.
     *       If <code>str</code> is <code>null</code>, 
     * a new <code>Font</code> is returned with the name "dialog", a  
     * size of 12 and a PLAIN style.
     * @param str the name of the font, or <code>null</code>
     * @return the <code>Font</code> object that <code>str</code>
     *		describes, or a new default <code>Font</code> if 
     *          <code>str</code> is <code>null</code>.
     * @see #getFamily
     * @since JDK1.1
     */
    public static Font decode(String str) { return null;}

    /** 
     * Gets the specified <code>Font</code> from the system properties
     * list.  As in the <code>getProperty</code> method of 
     * <code>System</code>, the first
     * argument is treated as the name of a system property to be
     * obtained.  The <code>String</code> value of this property is then
     * interpreted as a <code>Font</code> object. 
     * <p>
     * The property value should be one of the following forms: 
     * <ul>
     * <li><em>fontname-style-pointsize</em>
     * <li><em>fontname-pointsize</em>
     * <li><em>fontname-style</em>
     * <li><em>fontname</em>
     * </ul>
     * where <i>style</i> is one of the three case-insensitive strings 
     * <code>"BOLD"</code>, <code>"BOLDITALIC"</code>, or 
     * <code>"ITALIC"</code>, and point size is a decimal 
     * representation of the point size. 
     * <p>
     * The default style is <code>PLAIN</code>. The default point size 
     * is 12. 
     * <p>
     * If the specified property is not found, the <code>font</code> 
     * argument is returned instead. 
     * @param nm the case-insensitive property name
     * @param font a default <code>Font</code> to return if property
     * 		<code>nm</code> is not defined
     * @return    the <code>Font</code> value of the property.
     * @see #decode(String)
     */
    public static Font getFont(String nm, Font font) { return null;}

    /** 
     * Returns a hashcode for this <code>Font</code>.
     * @return     a hashcode value for this <code>Font</code>.
     * @since      JDK1.0
     */
    public int hashCode() { return 0; }

    /** 
     * Compares this <code>Font</code> object to the specified 
     * <code>Object</code>.
     * @param obj the <code>Object</code> to compare
     * @return <code>true</code> if the objects are the same
     *          or if the argument is a <code>Font</code> object
     *          describing the same font as this object; 
     *		<code>false</code> otherwise.
     * @since JDK1.0
     */
    public boolean equals(Object obj) { return false; }

    /** 
     * Converts this <code>Font</code> object to a <code>String</code>
     * representation.
     * @return     a <code>String</code> representation of this 
     *		<code>Font</code> object.
     * @since      JDK1.0
     */
    public String toString() { return null;}
// 
    // /** 
     // * Returns the number of glyphs in this <code>Font</code>. Glyph codes
     // * for this <code>Font</code> range from 0 to 
     // * <code>getNumGlyphs()</code> - 1.
     // * @return the number of glyphs in this <code>Font</code>.
     // * @since 1.2
     // */
    // public int getNumGlyphs() { }
// 
    // /** 
     // * Returns the glyphCode which is used when this <code>Font</code> 
     // * does not have a glyph for a specified unicode.
     // * @return the glyphCode of this <code>Font</code>.
     // * @since 1.2
     // */
    // public int getMissingGlyphCode() { }
// 
    // /** 
     // * Returns the baseline appropriate for displaying this character.
     // * <p>
     // * Large fonts can support different writing systems, and each system can
     // * use a different baseline.
     // * The character argument determines the writing system to use. Clients
     // * should not assume all characters use the same baseline.
     // *
     // * @param c a character used to identify the writing system
     // * @return the baseline appropriate for the specified character.
     // * @see LineMetrics#getBaselineOffsets
     // * @see #ROMAN_BASELINE
     // * @see #CENTER_BASELINE
     // * @see #HANGING_BASELINE
     // * @since 1.2
     // */
    // public byte getBaselineFor(char c) { }

    /** 
     * Returns a map of font attributes available in this
     * <code>Font</code>.  Attributes include things like ligatures and
     * glyph substitution.
     * @return the attributes map of this <code>Font</code>.
     */
    public Map getAttributes() { return null; }

// PBP/PP
// [6187222]

    /** 
     * Returns the keys of all the attributes supported by this 
     * <code>Font</code>.  
     * @return an array containing the keys of all the attributes
     *		supported by this <code>Font</code>.
     * @since 1.2
     */
    public Attribute[] getAvailableAttributes() {
      return null;
    }

    // /** 
     // * Creates a new <code>Font</code> object by replicating this
     // * <code>Font</code> object and applying a new style and size.
     // * @param style the style for the new <code>Font</code>
     // * @param size the size for the new <code>Font</code>
     // * @return a new <code>Font</code> object.
     // * @since 1.2
     // */
    // public Font deriveFont(int style, float size) { }
// 
    // /** 
     // * Creates a new <code>Font</code> object by replicating this
     // * <code>Font</code> object and applying a new style and transform.
     // * @param style the style for the new <code>Font</code>
     // * @param trans the <code>AffineTransform</code> associated with the
     // * new <code>Font</code>
     // * @return a new <code>Font</code> object.
     // * @throws IllegalArgumentException if <code>trans</code> is
     // *         <code>null</code>
     // * @since 1.2
     // */
    // public Font deriveFont(int style, AffineTransform trans) { }
// 
    // /** 
     // * Creates a new <code>Font</code> object by replicating the current
     // * <code>Font</code> object and applying a new size to it.
     // * @param size the size for the new <code>Font</code>.
     // * @return a new <code>Font</code> object.
     // * @since 1.2
     // */
    // public Font deriveFont(float size) { }
// 
    // /** 
     // * Creates a new <code>Font</code> object by replicating the current
     // * <code>Font</code> object and applying a new transform to it.
     // * @param trans the <code>AffineTransform</code> associated with the
     // * new <code>Font</code>
     // * @return a new <code>Font</code> object.
     // * @throws IllegalArgumentException if <code>trans</code> is 
     // *         <code>null</code>
     // * @since 1.2
     // */
    // public Font deriveFont(AffineTransform trans) { }
// 
    // /** 
     // * Creates a new <code>Font</code> object by replicating the current
     // * <code>Font</code> object and applying a new style to it.
     // * @param style the style for the new <code>Font</code>
     // * @return a new <code>Font</code> object.
     // * @since 1.2
     // */
    // public Font deriveFont(int style) { }
// 
    // /** 
     // * Creates a new <code>Font</code> object by replicating the current
     // * <code>Font</code> object and applying a new set of font attributes
     // * to it.
     // * @param attributes a map of attributes enabled for the new 
     // * <code>Font</code>
     // * @return a new <code>Font</code> object.
     // * @since 1.2
     // */
    // public Font deriveFont(Map attributes) { }
// 
    // /** 
     // * Checks if this <code>Font</code> has a glyph for the specified
     // * character.
     // * @param c a unicode character code
     // * @return <code>true</code> if this <code>Font</code> can display the
     // *		character; <code>false</code> otherwise.
     // * @since 1.2
     // */
    // public boolean canDisplay(char c) { }
// 
    // /** 
     // * Indicates whether or not this <code>Font</code> can display a
     // * specified <code>String</code>.  For strings with Unicode encoding,
     // * it is important to know if a particular font can display the
     // * string. This method returns an offset into the <code>String</code> 
     // * <code>str</code> which is the first character this 
     // * <code>Font</code> cannot display without using the missing glyph
     // * code. If the <code>Font</code> can display all characters, -1 is
     // * returned.
     // * @param str a <code>String</code> object
     // * @return an offset into <code>str</code> that points
     // *		to the first character in <code>str</code> that this
     // *		<code>Font</code> cannot display; or <code>-1</code> if
     // *		this <code>Font</code> can display all characters in
     // *		<code>str</code>.
     // * @since 1.2
     // */
    // public int canDisplayUpTo(String str) { }
// 
    // /** 
     // * Indicates whether or not this <code>Font</code> can display
     // * the characters in the specified <code>text</code> 
     // * starting at <code>start</code> and ending at 
     // * <code>limit</code>.  This method is a convenience overload.
     // * @param text the specified array of characters
     // * @param start the specified starting offset into the specified array
     // *		of characters
     // * @param limit the specified ending offset into the specified
     // *		array of characters
     // * @return an offset into <code>text</code> that points
     // *		to the first character in <code>text</code> that this
     // *		<code>Font</code> cannot display; or <code>-1</code> if
     // *		this <code>Font</code> can display all characters in
     // *		<code>text</code>.
     // * @since 1.2
     // */
    // public int canDisplayUpTo(char[] text, int start, int limit) { }
// 
    // /** 
     // * Indicates whether or not this <code>Font</code> can display
     // * the specified <code>String</code>.  For strings with Unicode
     // * encoding, it is important to know if a particular font can display
     // * the string. This method returns an offset
     // * into the <code>String</code> <code>str</code> which is the first
     // * character this <code>Font</code> cannot display without using the
     // * missing glyph code . If this <code>Font</code> can display all
     // * characters, <code>-1</code> is returned.
     // * @param iter a {@link CharacterIterator} object
     // * @param start the specified starting offset into the specified array
     // *          of characters
     // * @param limit the specified ending offset into the specified
     // *          array of characters
     // * @return an offset into the <code>String</code> object that can be
     // * 		displayed by this <code>Font</code>.
     // * @since 1.2
     // */
    // public int canDisplayUpTo(CharacterIterator iter, int start, int limit) { }
// 
    // /** 
     // * Returns the italic angle of this <code>Font</code>.  The italic angle
     // * is the inverse slope of the caret which best matches the posture of this
     // * <code>Font</code>.
     // * @see TextAttribute#POSTURE
     // * @return the angle of the ITALIC style of this <code>Font</code>.
     // */
    // public float getItalicAngle() { }
// 
    // /** 
     // * Checks whether or not this <code>Font</code> has uniform 
     // * line metrics.  A logical <code>Font</code> might be a
     // * composite font, which means that it is composed of different
     // * physical fonts to cover different code ranges.  Each of these
     // * fonts might have different <code>LineMetrics</code>.  If the
     // * logical <code>Font</code> is a single
     // * font then the metrics would be uniform. 
     // * @return <code>true</code> if this <code>Font</code> has
     // * uniform line metrics; <code>false</code> otherwise.
     // */
    // public boolean hasUniformLineMetrics() { }
// 
    // /** 
     // * Returns a {@link LineMetrics} object created with the specified
     // * <code>String</code> and {@link FontRenderContext}.
     // * @param str the specified <code>String</code>
     // * @param frc the specified <code>FontRenderContext</code>
     // * @return a <code>LineMetrics</code> object created with the
     // * specified <code>String</code> and {@link FontRenderContext}.
     // */
    // public LineMetrics getLineMetrics(String str, FontRenderContext frc) { }
// 
    // /** 
     // * Returns a <code>LineMetrics</code> object created with the
     // * specified arguments.
     // * @param str the specified <code>String</code>
     // * @param beginIndex the initial offset of <code>str</code> 
     // * @param limit the end offset of <code>str</code>
     // * @param frc the specified <code>FontRenderContext</code>
     // * @return a <code>LineMetrics</code> object created with the
     // * specified arguments.
     // */
    // public LineMetrics getLineMetrics(String str, int beginIndex, int limit,
        // FontRenderContext frc)
    // { }
// 
    // /** 
     // * Returns a <code>LineMetrics</code> object created with the
     // * specified arguments.
     // * @param chars an array of characters
     // * @param beginIndex the initial offset of <code>chars</code>
     // * @param limit the end offset of <code>chars</code>
     // * @param frc the specified <code>FontRenderContext</code>
     // * @return a <code>LineMetrics</code> object created with the
     // * specified arguments.
     // */
    // public LineMetrics getLineMetrics(char[] chars, int beginIndex, int limit,
        // FontRenderContext frc)
    // { }
// 
    // /** 
     // * Returns a <code>LineMetrics</code> object created with the
     // * specified arguments.
     // * @param ci the specified <code>CharacterIterator</code>
     // * @param beginIndex the initial offset in <code>ci</code>
     // * @param limit the end offset of <code>ci</code>
     // * @param frc the specified <code>FontRenderContext</code>
     // * @return a <code>LineMetrics</code> object created with the
     // * specified arguments.
     // */
    // public LineMetrics getLineMetrics(CharacterIterator ci, int beginIndex, int
        // limit, FontRenderContext frc)
    // { }
// 
    // /** 
     // * Returns the logical bounds of the specified <code>String</code> in
     // * the specified <code>FontRenderContext</code>.  The logical bounds
     // * contains the origin, ascent, advance, and height, which includes 
     // * the leading.  The logical bounds does not always enclose all the
     // * text.  For example, in some languages and in some fonts, accent
     // * marks can be positioned above the ascent or below the descent.
     // * To obtain a visual bounding box, which encloses all the text,
     // * use the {@link TextLayout#getBounds() getBounds} method of
     // * <code>TextLayout</code>.
     // * @param str the specified <code>String</code>
     // * @param frc the specified <code>FontRenderContext</code>
     // * @return a {@link Rectangle2D} that is the bounding box of the
     // * specified <code>String</code> in the specified
     // * <code>FontRenderContext</code>.
     // * @see FontRenderContext
     // * @see Font#createGlyphVector
     // * @since 1.2
     // */
    // public Rectangle2D getStringBounds(String str, FontRenderContext frc) { }
// 
    // /** 
     // * Returns the logical bounds of the specified <code>String</code> in
     // * the specified <code>FontRenderContext</code>.  The logical bounds
     // * contains the origin, ascent, advance, and height, which includes   
     // * the leading.  The logical bounds does not always enclose all the
     // * text.  For example, in some languages and in some fonts, accent
     // * marks can be positioned above the ascent or below the descent.
     // * To obtain a visual bounding box, which encloses all the text,
     // * use the {@link TextLayout#getBounds() getBounds} method of 
     // * <code>TextLayout</code>.
     // * @param str the specified <code>String</code>
     // * @param beginIndex the initial offset of <code>str</code>
     // * @param limit the end offset of <code>str</code>
     // * @param frc the specified <code>FontRenderContext</code>   
     // * @return a <code>Rectangle2D</code> that is the bounding box of the
     // * specified <code>String</code> in the specified
     // * <code>FontRenderContext</code>.
     // * @throws IndexOutOfBoundsException if <code>beginIndex</code> is 
     // *         less than zero, or <code>limit</code> is greater than the
     // *         length of <code>str</code>, or <code>beginIndex</code>
     // *         is greater than <code>limit</code>.
     // * @see FontRenderContext
     // * @see Font#createGlyphVector
     // * @since 1.2
     // */
    // public Rectangle2D getStringBounds(String str, int beginIndex, int limit,
        // FontRenderContext frc)
    // { }
// 
    // /** 
     // * Returns the logical bounds of the specified array of characters
     // * in the specified <code>FontRenderContext</code>.  The logical
     // * bounds contains the origin, ascent, advance, and height, which
     // * includes the leading.  The logical bounds does not always enclose
     // * all the text.  For example, in some languages and in some fonts,
     // * accent marks can be positioned above the ascent or below the
     // * descent.  To obtain a visual bounding box, which encloses all the
     // * text, use the {@link TextLayout#getBounds() getBounds} method of 
     // * <code>TextLayout</code>.
     // * @param chars an array of characters
     // * @param beginIndex the initial offset in the array of
     // * characters
     // * @param limit the end offset in the array of characters
     // * @param frc the specified <code>FontRenderContext</code>   
     // * @return a <code>Rectangle2D</code> that is the bounding box of the
     // * specified array of characters in the specified
     // * <code>FontRenderContext</code>.
     // * @throws IndexOutOfBoundsException if <code>beginIndex</code> is 
     // *         less than zero, or <code>limit</code> is greater than the
     // *         length of <code>chars</code>, or <code>beginIndex</code>
     // *         is greater than <code>limit</code>.
     // * @see FontRenderContext
     // * @see Font#createGlyphVector
     // * @since 1.2
     // */
    // public Rectangle2D getStringBounds(char[] chars, int beginIndex, int limit,
        // FontRenderContext frc)
    // { }
// 
    // /** 
     // * Returns the logical bounds of the characters indexed in the
     // * specified {@link CharacterIterator} in the
     // * specified <code>FontRenderContext</code>.  The logical bounds
     // * contains the origin, ascent, advance, and height, which includes   
     // * the leading.  The logical bounds does not always enclose all the
     // * text.  For example, in some languages and in some fonts, accent
     // * marks can be positioned above the ascent or below the descent. 
     // * To obtain a visual bounding box, which encloses all the text, 
     // * use the {@link TextLayout#getBounds() getBounds} method of 
     // * <code>TextLayout</code>.
     // * @param ci the specified <code>CharacterIterator</code>
     // * @param beginIndex the initial offset in <code>ci</code>
     // * @param limit the end offset in <code>ci</code>
     // * @param frc the specified <code>FontRenderContext</code>   
     // * @return a <code>Rectangle2D</code> that is the bounding box of the
     // * characters indexed in the specified <code>CharacterIterator</code>
     // * in the specified <code>FontRenderContext</code>.
     // * @see FontRenderContext
     // * @see Font#createGlyphVector
     // * @since 1.2
     // * @throws IndexOutOfBoundsException if <code>beginIndex</code> is
     // *         less than the start index of <code>ci</code>, or 
     // *         <code>limit</code> is greater than the end index of 
     // *         <code>ci</code>, or <code>beginIndex</code> is greater 
     // *         than <code>limit</code>
     // */
    // public Rectangle2D getStringBounds(CharacterIterator ci, int beginIndex, int
        // limit, FontRenderContext frc)
    // { }
// 
    // /** 
     // * Returns the bounds for the character with the maximum
     // * bounds as defined in the specified <code>FontRenderContext</code>.
     // * @param frc the specified <code>FontRenderContext</code>
     // * @return a <code>Rectangle2D</code> that is the bounding box
     // * for the character with the maximum bounds.
     // */
    // public Rectangle2D getMaxCharBounds(FontRenderContext frc) { }
// 
    // /** 
     // * Creates a {@link java.awt.font.GlyphVector GlyphVector} by 
     // * mapping characters to glyphs one-to-one based on the 
     // * Unicode cmap in this <code>Font</code>.  This method does no other
     // * processing besides the mapping of glyphs to characters.  This
     // * means that this method is not useful for some scripts, such
     // * as Arabic, Hebrew, Thai, and Indic, that require reordering, 
     // * shaping, or ligature substitution.
     // * @param frc the specified <code>FontRenderContext</code>
     // * @param str the specified <code>String</code>
     // * @return a new <code>GlyphVector</code> created with the 
     // * specified <code>String</code> and the specified
     // * <code>FontRenderContext</code>.
     // */
    // public GlyphVector createGlyphVector(FontRenderContext frc, String str) { }
// 
    // /** 
     // * Creates a {@link java.awt.font.GlyphVector GlyphVector} by
     // * mapping characters to glyphs one-to-one based on the
     // * Unicode cmap in this <code>Font</code>.  This method does no other
     // * processing besides the mapping of glyphs to characters.  This
     // * means that this method is not useful for some scripts, such
     // * as Arabic, Hebrew, Thai, and Indic, that require reordering,   
     // * shaping, or ligature substitution. 
     // * @param frc the specified <code>FontRenderContext</code>
     // * @param chars the specified array of characters
     // * @return a new <code>GlyphVector</code> created with the
     // * specified array of characters and the specified
     // * <code>FontRenderContext</code>.
     // */
    // public GlyphVector createGlyphVector(FontRenderContext frc, char[] chars)
    // { }
// 
    // /** 
     // * Creates a {@link java.awt.font.GlyphVector GlyphVector} by
     // * mapping the specified characters to glyphs one-to-one based on the
     // * Unicode cmap in this <code>Font</code>.  This method does no other
     // * processing besides the mapping of glyphs to characters.  This
     // * means that this method is not useful for some scripts, such
     // * as Arabic, Hebrew, Thai, and Indic, that require reordering,   
     // * shaping, or ligature substitution. 
     // * @param frc the specified <code>FontRenderContext</code>
     // * @param ci the specified <code>CharacterIterator</code>
     // * @return a new <code>GlyphVector</code> created with the
     // * specified <code>CharacterIterator</code> and the specified
     // * <code>FontRenderContext</code>.
     // */
    // public GlyphVector createGlyphVector(FontRenderContext frc,
        // CharacterIterator ci)
    // { }
// 
    // /** 
     // * Creates a {@link java.awt.font.GlyphVector GlyphVector} by
     // * mapping characters to glyphs one-to-one based on the
     // * Unicode cmap in this <code>Font</code>.  This method does no other
     // * processing besides the mapping of glyphs to characters.  This
     // * means that this method is not useful for some scripts, such
     // * as Arabic, Hebrew, Thai, and Indic, that require reordering,   
     // * shaping, or ligature substitution.
     // * @param frc the specified <code>FontRenderContext</code>
     // * @param glyphCodes the specified integer array
     // * @return a new <code>GlyphVector</code> created with the
     // * specified integer array and the specified
     // * <code>FontRenderContext</code>.
     // */
    // public GlyphVector createGlyphVector(FontRenderContext frc, int[]
        // glyphCodes)
    // { }
// 
    // /** 
     // * Returns a new <code>GlyphVector</code> object, performing full
     // * layout of the text if possible.  Full layout is required for
     // * complex text, such as Arabic or Hindi.  Support for different
     // * scripts depends on the font and implementation.  
     // * <p
     // * Layout requires bidi analysis, as performed by 
     // * <code>Bidi</code>, and should only be performed on text that
     // * has a uniform direction.  The direction is indicated in the
     // * flags parameter,by using LAYOUT_RIGHT_TO_LEFT to indicate a
     // * right-to-left (Arabic and Hebrew) run direction, or 
     // * LAYOUT_LEFT_TO_RIGHT to indicate a left-to-right (English) 
     // * run direction.
     // * <p>
     // * In addition, some operations, such as Arabic shaping, require 
     // * context, so that the characters at the start and limit can have 
     // * the proper shapes.  Sometimes the data in the buffer outside
     // * the provided range does not have valid data.  The values
     // * LAYOUT_NO_START_CONTEXT and LAYOUT_NO_LIMIT_CONTEXT can be
     // * added to the flags parameter to indicate that the text before
     // * start, or after limit, respectively, should not be examined
     // * for context.
     // * <p>
     // * All other values for the flags parameter are reserved.
     // * 
     // * @param frc the specified <code>FontRenderContext</code>
     // * @param text the text to layout
     // * @param start the start of the text to use for the <code>GlyphVector</code>
     // * @param limit the limit of the text to use for the <code>GlyphVector</code>
     // * @param flags control flags as described above
     // * @return a new <code>GlyphVector</code> representing the text between
     // * start and limit, with glyphs chosen and positioned so as to best represent 
     // * the text
     // * @throws ArrayIndexOutOfBoundsException if start or limit is 
     // * out of bounds
     // * @see java.text.Bidi
     // * @see #LAYOUT_LEFT_TO_RIGHT
     // * @see #LAYOUT_RIGHT_TO_LEFT
     // * @see #LAYOUT_NO_START_CONTEXT
     // * @see #LAYOUT_NO_LIMIT_CONTEXT
     // */
    // public GlyphVector layoutGlyphVector(FontRenderContext frc, char[] text, int
        // start, int limit, int flags)
    // { }
// 
    // /** 
     // * Disposes the native <code>Font</code> object.
     // */
    // protected void finalize() throws Throwable { }

    /** 
     * Reads the <code>ObjectInputStream</code>.
     * Unrecognized keys or values will be ignored.
     *
     * @param s the <code>ObjectInputStream</code> to read
     * @serial
     * @see #writeObject(java.io.ObjectOutputStream)
     */
    private void readObject(ObjectInputStream s)
        throws ClassNotFoundException, IOException
    { }

    /** 
     * Writes default serializable fields to a stream.
     *
     * @param s the <code>ObjectOutputStream</code> to write
     * @see AWTEventMulticaster#save(ObjectOutputStream, String, EventListener)
     * @see #readObject(java.io.ObjectInputStream)
     */
    private void writeObject(ObjectOutputStream s)
        throws ClassNotFoundException, IOException
    { }
}
