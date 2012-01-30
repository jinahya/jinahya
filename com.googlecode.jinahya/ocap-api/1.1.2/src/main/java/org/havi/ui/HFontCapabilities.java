package org.havi.ui;

/*
 * Copyright 2000-2003 by HAVi, Inc. Java is a trademark of Sun
 * Microsystems, Inc. All rights reserved.  
 */

/**

   The {@link org.havi.ui.HFontCapabilities HFontCapabilities} class
   allows applications to query the rendering support for various
   character ranges and individual characters within specified fonts.

*/

public class HFontCapabilities
{
    /**
     * This corresponds to the character range (U+0020..U+007E) as defined
     * in ISO 10646-1(E) normative Annex A 
     */
    public static final int BASIC_LATIN                            = 1;

    /**
     * This corresponds to the character range (U+00A0..U+00FF) as defined
     * in ISO 10646-1(E) normative Annex A 
     */
    public static final int LATIN_1_SUPPLEMENT                     = 2;

    /**
     * This corresponds to the character range (U+0100..U+017F) as defined
     * in ISO 10646-1(E) normative Annex A 
     */
    public static final int LATIN_EXTENDED_A                       = 3;

    /**
     * This corresponds to the character range (U+0180..U+024F) as defined
     * in ISO 10646-1(E) normative Annex A 
     */
    public static final int LATIN_EXTENDED_B                       = 4;

    /**
     * This corresponds to the character range (U+0250..U+02AF) as defined
     * in ISO 10646-1(E) normative Annex A 
     */
    public static final int IPA_EXTENSIONS                         = 5;

    /**
     * This corresponds to the character range (U+02B0..U+02FF) as defined
     * in ISO 10646-1(E) normative Annex A 
     */
    public static final int SPACING_MODIFIER_LETTERS               = 6;

    /**
     * This corresponds to the character range (U+0300..U+036F) as defined
     * in ISO 10646-1(E) normative Annex A 
     */
    public static final int COMBINING_DIACRITICAL_MARKS            = 7;

    /**
     * This corresponds to the character range (U+0370..U+03CF) as defined
     * in ISO 10646-1(E) normative Annex A 
     */
    public static final int BASIC_GREEK                            = 8;

    /**
     * This corresponds to the character range (U+3D0..U+3FF) as defined
     * in ISO 10646-1(E) normative Annex A 
     */
    public static final int GREEK_SYMBOLS_AND_COPTIC               = 9;

    /**
     * This corresponds to the character range (U+400..U+4FF) as defined
     * in ISO 10646-1(E) normative Annex A 
     */
    public static final int CYRILLIC                               = 10;

    /**
     * This corresponds to the character range (U+530..U+58F) as defined
     * in ISO 10646-1(E) normative Annex A 
     */
    public static final int ARMENIAN                               = 11;

    /**
     * This corresponds to the character range (U+5D0..U+5EA) as defined
     * in ISO 10646-1(E) normative Annex A 
     */
    public static final int BASIC_HEBREW                           = 12;

    /**
     * This corresponds to the character ranges (U+590..U+5CF) and
     * (U+5EB..U+5FF) as defined in ISO 10646-1(E) normative
     * Annex A 
     */
    public static final int HEBREW_EXTENDED                        = 13;

    /**
     * This corresponds to the character range (U+600..U+652) as defined
     * in ISO 10646-1(E) normative Annex A 
     */
    public static final int BASIC_ARABIC                           = 14;

    /**
     * This corresponds to the character range (U+653..U+6FF) as defined
     * in ISO 10646-1(E) normative Annex A 
     */
    public static final int ARABIC_EXTENDED                        = 15;

    /**
     * This corresponds to the character ranges (U+0900..U+097F) and
     * (U+200C..U+200D) as defined in ISO 10646-1(E)
     * normative Annex A 
     */
    public static final int DEVANAGARI                             = 16;

    /**
     * This corresponds to the character ranges (U+980..U+9FF) and
     * (U+200C..U+200D) as defined in ISO 10646-1(E)
     * normative Annex A 
     */
    public static final int BENGALI                                = 17;

    /**
     * This corresponds to the character ranges (U+0A00..U+0A7F) and
     * (U+200C..U+200D) as defined in ISO 10646-1(E)
     * normative Annex A 
     */
    public static final int GURMUKHI                               = 18;

    /**
     * This corresponds to the character ranges (U+0A80..U+0AFF) and
     * (U+200C..U+200D) as defined in ISO 10646-1(E)
     * normative Annex A */
    public static final int GUJARATI                               = 19;

    /**
     * This corresponds to the character ranges (U+0B00..U+0B7F) and
     * (U+200C..U+200D) as defined in ISO 10646-1(E)
     * normative Annex A 
     */
    public static final int ORIYA                                  = 20;

    /**
     * This corresponds to the character ranges (U+0B80..U+0BFF) and
     * (U+200C..U+200D) as defined in ISO 10646-1(E)
     * normative Annex A
     */
    public static final int TAMIL                                  = 21;

    /**
     * This corresponds to the character ranges (U+0C00..U+0C7F) and
     * (U+200C..U+200D) as defined in ISO 10646-1(E)
     * normative Annex A 
     */
    public static final int TELUGU                                 = 22;

    /**
     * This corresponds to the character ranges (U+0C80..U+0CFF) and
     * (U+200C..U+200D) as defined in ISO 10646-1(E)
     * normative Annex A 
     */
    public static final int KANNADA                                = 23;

    /**
     * This corresponds to the character ranges (U+0D00..U+0D7F) and
     * (U+200C..U+200D) as defined in ISO 10646-1(E)
     * normative Annex A 
     */
    public static final int MALAYALAM                                = 24;

    /**
     * This corresponds to the character range (U+0E00..U+0E7F) as defined
     * in ISO 10646-1(E) normative Annex A 
     */
    public static final int THAI                                   = 25;

    /**
     * This corresponds to the character range (U+0E80..U+0EFF) as defined
     * in ISO 10646-1(E) normative Annex A 
     */
    public static final int LAO                                    = 26;

    /**
     * This corresponds to the character range (U+10D0..U+10FF) as defined
     * in ISO 10646-1(E) normative Annex A 
     */
    public static final int BASIC_GEORGIAN                         = 27;

    /**
     * This corresponds to the character range (U+10A0..U+10CF) as defined
     * in ISO 10646-1(E) normative Annex A 
     */
    public static final int GEORGIAN_EXTENDED                      = 28;

    /**
     * This corresponds to the character range (U+1100..U+11FF) as defined
     * in ISO 10646-1(E) normative Annex A 
     */
    public static final int HANGUL_JAMO                            = 29;

    /**
     * This corresponds to the character range (U+1E00..U+1EFF) as defined
     * in ISO 10646-1(E) normative Annex A 
     */
    public static final int LATIN_EXTENDED_ADDITIONAL              = 30;

    /**
     * This corresponds to the character range (U+1F00..U+1FFF) as defined
     * in ISO 10646-1(E) normative Annex A 
     */
    public static final int GREEK_EXTENDED                         = 31;

    /**
     * This corresponds to the character range (U+2000..U+206F) as defined
     * in ISO 10646-1(E) normative Annex A 
     */
    public static final int GENERAL_PUNCTUATION                    = 32;

    /**
     * This corresponds to the character range (U+2070..U+209F) as defined
     * in ISO 10646-1(E) normative Annex A 
     */
    public static final int SUPERSCRIPTS_AND_SUBSCRIPTS            = 33;

    /**
     * This corresponds to the character range (U+20A0..U+20CF) as defined
     * in ISO 10646-1(E) normative Annex A 
     */
    public static final int CURRENCY_SYMBOLS                       = 34;

    /**
     * This corresponds to the character range (U+20D0..U+20FF) as defined
     * in ISO 10646-1(E) normative Annex A 
     */
    public static final int COMBINING_DIACTRICAL_MARKS_FOR_SYMBOLS = 35;

    /**
     * This corresponds to the character range (U+2100..U+214F) as defined
     * in ISO 10646-1(E) normative Annex A 
     */
    public static final int LETTERLIKE_SYMBOLS                     = 36;

    /**
     * This corresponds to the character range (U+2150..U+218F) as defined
     * in ISO 10646-1(E) normative Annex A 
     */
    public static final int NUMBER_FORMS                           = 37;

    /**
     * This corresponds to the character range (U+2190..U+21FF) as defined
     * in ISO 10646-1(E) normative Annex A 
     */
    public static final int ARROWS                                 = 38;

    /**
     * This corresponds to the character range (U+2200..U+22FF) as defined
     * in ISO 10646-1(E) normative Annex A 
     */
    public static final int MATHEMATICAL_OPERATORS                 = 39;

    /**
     * This corresponds to the character range (U+2300..U+23FF) as defined
     * in ISO 10646-1(E) normative Annex A 
     */
    public static final int MISCELLANEOUS_TECHNICAL                = 40;

    /**
     * This corresponds to the character range (U+2400..U+243F) as defined
     * in ISO 10646-1(E) normative Annex A 
     */
    public static final int CONTROL_PICTURES                       = 41;

    /**
     * This corresponds to the character range (U+2440..U+245F) as defined
     * in ISO 10646-1(E) normative Annex A 
     */
    public static final int OPTICAL_CHARACTER_RECOGNITION          = 42;

    /**
     * This corresponds to the character range (U+2460..U+24FF) as defined
     * in ISO 10646-1(E) normative Annex A 
     */
    public static final int ENCLOSED_ALPHANUMERICS                 = 43;

    /**
     * This corresponds to the character range (U+2500..U+257F) as defined
     * in ISO 10646-1(E) normative Annex A 
     */
    public static final int BOX_DRAWING                            = 44;

    /**
     * This corresponds to the character range (U+2580..U+259F) as defined
     * in ISO 10646-1(E) normative Annex A 
     */
    public static final int BLOCK_ELEMENTS                         = 45;

    /**
     * This corresponds to the character range (U+25A0..U+25FF) as defined
     * in ISO 10646-1(E) normative Annex A 
     */
    public static final int GEOMETRICAL_SHAPES                     = 46;

    /**
     * This corresponds to the character range (U+2600..U+26FF) as defined
     * in ISO 10646-1(E) normative Annex A 
     */
    public static final int MISCELLANEOUS_SYMBOLS                  = 47;

    /**
     * This corresponds to the character range (U+2700..U+27BF) as defined
     * in ISO 10646-1(E) normative Annex A 
     */
    public static final int DINGBATS                               = 48;

    /**
     * This corresponds to the character range (U+3000..U+303F) as defined
     * in ISO 10646-1(E) normative Annex A 
     */
    public static final int CJK_SYMBOLS_AND_PUNCTUATION            = 49;

    /**
     * This corresponds to the character range (U+3040..U+309F) as defined
     * in ISO 10646-1(E) normative Annex A 
     */
    public static final int HIRAGANA                               = 50;

    /**
     * This corresponds to the character range (U+30A0..U+30FF) as defined
     * in ISO 10646-1(E) normative Annex A 
     */
    public static final int KATAKANA                               = 51;

    /**
     * This corresponds to the character range (U+3100..U+312F) as defined
     * in ISO 10646-1(E) normative Annex A 
     */
    public static final int BOPOMOFO                               = 52;

    /**
     * This corresponds to the character range (U+3130..U+318F) as defined
     * in ISO 10646-1(E) normative Annex A 
     */
    public static final int HANGUL_COMPATIBILITY_JAMO              = 53;

    /**
     * This corresponds to the character range (U+3190..U+319F) as defined
     * in ISO 10646-1(E) normative Annex A 
     */
    public static final int CJK_MISCELLANEOUS                      = 54;

    /**
     * This corresponds to the character range (U+3200..U+32FF) as defined
     * in ISO 10646-1(E) normative Annex A 
     */
    public static final int ENCLOSED_CJK_LETTERS_AND_MONTHS        = 55;

    /**
     * This corresponds to the character range (U+3300..U+33FF) as defined
     * in ISO 10646-1(E) normative Annex A 
     */
    public static final int CJK_COMPATIBILITY                      = 56;

    /**
     * This corresponds to the character range (U+3400..U+3D2D) as defined
     * in ISO 10646-1(E) normative Annex A 
     */
    public static final int HANGUL                                 = 57;

    /**
     * This corresponds to the character range (U+3D2E..U+44B7) as defined
     * in ISO 10646-1(E) normative Annex A 
     */
    public static final int HANGUL_SUPPLEMENTARY_A                 = 58;

    /**
     * This corresponds to the character range (U+44B8..U+4DFF) as defined
     * in ISO 10646-1(E) normative Annex A 
     */
    public static final int HANGUL_SUPPLEMENTARY_B                 = 59;

    /**
     * This corresponds to the character range (U+4E00..U+9FFF) as defined
     * in ISO 10646-1(E) normative Annex A 
     */
    public static final int CJK_UNIFIED_IDEOGRAPHS                 = 60;

    /**
     * This corresponds to the character range (U+E000..U+F8FF) as defined
     * in ISO 10646-1(E) normative Annex A 
     */
    public static final int PRIVATE_USE_AREA                       = 61;

    /**
     * This corresponds to the character range (U+F900..U+FAFF) as defined
     * in ISO 10646-1(E) normative Annex A 
     */
    public static final int CJK_COMPATIBILITY_IDEOGRAPHS           = 62;

    /**
     * This corresponds to the character range (U+FB00..U+FB4F) as defined
     * in ISO 10646-1(E) normative Annex A 
     */
    public static final int ALPHABETIC_PRESENTATION_FORMS_A        = 63;

    /**
     * This corresponds to the character range (U+FB50..U+FDFF) as defined
     * in ISO 10646-1(E) normative Annex A 
     */
    public static final int ARABIC_PRESENTATION_FORMS_A            = 64;

    /**
     * This corresponds to the character range (U+FE20..U+FE2F) as defined
     * in ISO 10646-1(E) normative Annex A 
     */
    public static final int COMBINING_HALF_MARKS                   = 65;

    /**
     * This corresponds to the character range (U+FE30..U+FE4F) as defined
     * in ISO 10646-1(E) normative Annex A 
     */
    public static final int CJK_COMPATIBILITY_FORMS                = 66;

    /**
     * This corresponds to the character range (U+FE50..U+FE6F) as defined
     * in ISO 10646-1(E) normative Annex A 
     */
    public static final int SMALL_FORM_VARIANTS                    = 67;

    /**
     * This corresponds to the character range (U+FE70..U+FEFE) as defined
     * in ISO 10646-1(E) normative Annex A 
     */
    public static final int ARABIC_PRESENTATION_FORMS_B            = 68;

    /**
     * This corresponds to the character range (U+FF00..U+FFEF) as defined
     * in ISO 10646-1(E) normative Annex A 
     */
    public static final int HALFWIDTH_AND_FULLWIDTH_FORMS          = 69;

    /**
     * This corresponds to the character range (U+FFF0..U+FFFD) as defined
     * in ISO 10646-1(E) normative Annex A 
     */
    public static final int SPECIALS                               = 70;

    /**
     * It is not intended that applications should directly construct
     * {@link org.havi.ui.HFontCapabilities HFontCapabilities}
     * objects.  
     * <p> 
     * This method is protected to allow the platform to override it
     * in a different package scope.  
     */
    protected HFontCapabilities()
    {
    }

    /**
     * Returns the set of character ranges as defined in ISO
     * 10646-1(E) normative Annex A that this font supports, or a
     * null array if the capabilities of the font are unknown.
     * <p>
     * Support for a character range does not imply that ALL
     * characters within that range  are available in the specified
     * font.
     * <p>
     * When deciding whether a particular character range
     * (U+XXXX..U+YYYY) is supported, characters which are in the
     * <code>GENERAL PUNCTUATION</code> range shall not be considered
     * for character ranges other than <code>GENERAL
     * PUNCTUATION</code>.
     *
     * @param font The font to query for its support for character
     * ranges as specified by ISO 10646-1.
     * @return An array of integer values, as defined in ISO
     * 10646-1(E) normative Annex A that this font supports, or
     * null including where the capabilities of the font are unknown.
     */
    public static int[] getSupportedCharacterRanges(java.awt.Font font)
    {
        return (null);
    }

    /**
     *
     * Returns whether a specific character is available within the
     * specified font, and can be used as defined in ISO
     * 10646-1(E) specification by the rendering system, e.g. if
     * rendering of bi-directional text, using
     * <code>BI-DIRECTIONAL_FORMAT_MARKS</code> is supported
     *
     * @param font The font to query for its support for the specified
     * character.
     * @param c The character whose presence should be tested.
     * @return true is the character is available within the font and
     * can be rendered as defined in the ISO 10646-1(E)
     * specification, false otherwise.
     */
    public static boolean isCharAvailable(java.awt.Font font, char c)
    {
        return (false);
    }
}


