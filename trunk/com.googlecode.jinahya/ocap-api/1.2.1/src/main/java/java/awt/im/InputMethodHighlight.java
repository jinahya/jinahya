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


  


package java.awt.im;

import java.util.Map;

// PBP/PP
// [6178495] Struck references to concrete style descriptions and
//           mapInputMethodHighlight.

/** 
 * An InputMethodHighlight is used to describe the highlight
 * attributes of text being composed.

 * <p>
 * The abstract description consists of three fields: <code>selected</code>,
 * <code>state</code>, and <code>variation</code>.
 * <code>selected</code> indicates whether the text range is the one that the
 * input method is currently working on, for example, the segment for which
 * conversion candidates are currently shown in a menu.
 * <code>state</code> represents the conversion state. State values are defined
 * by the input method framework and should be distinguished in all
 * mappings from abstract to concrete styles. Currently defined state values
 * are raw (unconverted) and converted.
 * These state values are recommended for use before and after the
 * main conversion step of text composition, say, before and after kana->kanji
 * or pinyin->hanzi conversion.
 * The <code>variation</code> field allows input methods to express additional
 * information about the conversion results. 
 * <p>
 * 
 * InputMethodHighlight instances are typically used as attribute values
 * returned from AttributedCharacterIterator for the INPUT_METHOD_HIGHLIGHT
 * attribute. 
 *
 * @version 	1.19, 01/23/03
 * @see java.text.AttributedCharacterIterator
 * @since 1.2
 */
public class InputMethodHighlight
{
    /** 
     * Constant for the raw text state.
     */
    public static final int RAW_TEXT = 0;

    /** 
     * Constant for the converted text state.
     */
    public static final int CONVERTED_TEXT = 1;

    /** 
     * Constant for the default highlight for unselected raw text.
     */
    public static final InputMethodHighlight UNSELECTED_RAW_TEXT_HIGHLIGHT =
        null;

    /** 
     * Constant for the default highlight for selected raw text.
     */
    public static final InputMethodHighlight SELECTED_RAW_TEXT_HIGHLIGHT = null;

    /** 
     * Constant for the default highlight for unselected converted text.
     */
    public static final InputMethodHighlight UNSELECTED_CONVERTED_TEXT_HIGHLIGHT
        = null;

    /** 
     * Constant for the default highlight for selected converted text.
     */
    public static final InputMethodHighlight SELECTED_CONVERTED_TEXT_HIGHLIGHT =
        null;

    /** 
     * Constructs an input method highlight record.
     * The variation is set to 0, the style to null.
     * @param selected Whether the text range is selected
     * @param state The conversion state for the text range - RAW_TEXT or CONVERTED_TEXT
     * @see InputMethodHighlight#RAW_TEXT
     * @see InputMethodHighlight#CONVERTED_TEXT
     * @exception IllegalArgumentException if a state other than RAW_TEXT or CONVERTED_TEXT is given
     */
    public InputMethodHighlight(boolean selected, int state) { }

    /** 
     * Constructs an input method highlight record.
     * The style is set to null.
     * @param selected Whether the text range is selected
     * @param state The conversion state for the text range - RAW_TEXT or CONVERTED_TEXT
     * @param variation The style variation for the text range
     * @see InputMethodHighlight#RAW_TEXT
     * @see InputMethodHighlight#CONVERTED_TEXT
     * @exception IllegalArgumentException if a state other than RAW_TEXT or CONVERTED_TEXT is given
     */
    public InputMethodHighlight(boolean selected, int state, int variation) { }

    /** 
     * Constructs an input method highlight record.
     * The style attributes map provided must be unmodifiable.
     * @param selected whether the text range is selected
     * @param state the conversion state for the text range - RAW_TEXT or CONVERTED_TEXT
     * @param variation the variation for the text range
     * @param style the rendering style attributes for the text range, or null
     * @see InputMethodHighlight#RAW_TEXT
     * @see InputMethodHighlight#CONVERTED_TEXT
     * @exception IllegalArgumentException if a state other than RAW_TEXT or CONVERTED_TEXT is given
     * @since 1.3
     */
//     public InputMethodHighlight(boolean selected, int state, int variation, Map
//         style)
//     { }

    /** 
     * Returns whether the text range is selected.
     */
    public boolean isSelected() {
        return false;
    }

    /** 
     * Returns the conversion state of the text range.
     * @return The conversion state for the text range - RAW_TEXT or CONVERTED_TEXT.
     * @see InputMethodHighlight#RAW_TEXT
     * @see InputMethodHighlight#CONVERTED_TEXT
     */
    public int getState() {
        return 0;
    }

    /** 
     * Returns the variation of the text range.
     */
    public int getVariation() {
        return 0;
    }

    /** 
     * Returns the rendering style attributes for the text range, or null.
     * @since 1.3
     */
//     public Map getStyle() {
//         return null;
//     }
}
