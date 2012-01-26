/*
<p>This is not an official specification document, and usage is restricted.
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
This work corresponds to the API signatures of JSR 219: Foundation
Profile 1.1. In the event of a discrepency between this work and the
JSR 219 specification, which is available at
http://www.jcp.org/en/jsr/detail?id=219, the latter takes precedence.
*/


  


package java.text;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.Currency;
import java.util.Hashtable;
import java.util.Locale;
import java.util.ResourceBundle;

/** 
 * This class represents the set of symbols (such as the decimal separator,
 * the grouping separator, and so on) needed by <code>DecimalFormat</code>
 * to format numbers. <code>DecimalFormat</code> creates for itself an instance of
 * <code>DecimalFormatSymbols</code> from its locale data.  If you need to change any
 * of these symbols, you can get the <code>DecimalFormatSymbols</code> object from
 * your <code>DecimalFormat</code> and modify it.
 *
 * @see          java.util.Locale
 * @see          DecimalFormat
 * @version      1.39, 01/23/03
 * @author       Mark Davis
 * @author       Alan Liu
 */
public final class DecimalFormatSymbols implements Cloneable, Serializable
{
    /** 
     * Character used for zero.
     *
     * @serial
     * @see #getZeroDigit
     */
    private char zeroDigit;

    /** 
     * Character used for thousands separator.
     *
     * @serial
     * @see #getGroupingSeparator
     */
    private char groupingSeparator;

    /** 
     * Character used for decimal sign.
     *
     * @serial
     * @see #getDecimalSeparator
     */
    private char decimalSeparator;

    /** 
     * Character used for mille percent sign.
     *
     * @serial
     * @see #getPerMill
     */
    private char perMill;

    /** 
     * Character used for percent sign.
     * @serial
     * @see #getPercent
     */
    private char percent;

    /** 
     * Character used for a digit in a pattern.
     *
     * @serial
     * @see #getDigit
     */
    private char digit;

    /** 
     * Character used to separate positive and negative subpatterns
     * in a pattern.
     *
     * @serial
     * @see #getPatternSeparator
     */
    private char patternSeparator;

    /** 
     * String used to represent infinity.
     * @serial
     * @see #getInfinity
     */
    private String infinity;

    /** 
     * String used to represent "not a number".
     * @serial
     * @see #getNaN
     */
    private String NaN;

    /** 
     * Character used to represent minus sign.
     * @serial
     * @see #getMinusSign
     */
    private char minusSign;

    /** 
     * String denoting the local currency, e.g. "$".
     * @serial
     * @see #getCurrencySymbol
     */
    private String currencySymbol;

    /** 
     * ISO 4217 currency code denoting the local currency, e.g. "USD".
     * @serial
     * @see #getInternationalCurrencySymbol
     */
    private String intlCurrencySymbol;

    /** 
     * The decimal separator used when formatting currency values.
     * @serial
     * @since JDK 1.1.6
     * @see #getMonetaryDecimalSeparator
     */
    private char monetarySeparator;

    /** 
     * The character used to distinguish the exponent in a number formatted
     * in exponential notation, e.g. 'E' for a number such as "1.23E45".
     * <p>
     * Note that the public API provides no way to set this field,
     * even though it is supported by the implementation and the stream format.
     * The intent is that this will be added to the API in the future.
     *
     * @serial
     * @since JDK 1.1.6
     */
    private char exponential;

    /** 
     * The locale of these currency format symbols.
     *
     * @serial
     * @since 1.4
     */
    private Locale locale;

    /** 
     * Describes the version of <code>DecimalFormatSymbols</code> present on the stream.
     * Possible values are:
     * <ul>
     * <li><b>0</b> (or uninitialized): versions prior to JDK 1.1.6.
     *
     * <li><b>1</b>: Versions written by JDK 1.1.6 or later, which include
     *      two new fields: <code>monetarySeparator</code> and <code>exponential</code>.
     * <li><b>2</b>: Versions written by J2SE 1.4 or later, which include a
     *      new <code>locale</code> field.
     * </ul>
     * When streaming out a <code>DecimalFormatSymbols</code>, the most recent format
     * (corresponding to the highest allowable <code>serialVersionOnStream</code>)
     * is always written.
     *
     * @serial
     * @since JDK 1.1.6
     */
    private int serialVersionOnStream;

    /** 
     * Create a DecimalFormatSymbols object for the default locale.
     */
    public DecimalFormatSymbols() { }

    /** 
     * Create a DecimalFormatSymbols object for the given locale.
     *
     * @exception NullPointerException if <code>locale</code> is null
     */
    public DecimalFormatSymbols(Locale locale) { }

    /** 
     * Gets the character used for zero. Different for Arabic, etc.
     */
    public char getZeroDigit() {
        return ' ';
    }

    /** 
     * Sets the character used for zero. Different for Arabic, etc.
     */
    public void setZeroDigit(char zeroDigit) { }

    /** 
     * Gets the character used for thousands separator. Different for French, etc.
     */
    public char getGroupingSeparator() {
        return ' ';
    }

    /** 
     * Sets the character used for thousands separator. Different for French, etc.
     */
    public void setGroupingSeparator(char groupingSeparator) { }

    /** 
     * Gets the character used for decimal sign. Different for French, etc.
     */
    public char getDecimalSeparator() {
        return ' ';
    }

    /** 
     * Sets the character used for decimal sign. Different for French, etc.
     */
    public void setDecimalSeparator(char decimalSeparator) { }

    /** 
     * Gets the character used for mille percent sign. Different for Arabic, etc.
     */
    public char getPerMill() {
        return ' ';
    }

    /** 
     * Sets the character used for mille percent sign. Different for Arabic, etc.
     */
    public void setPerMill(char perMill) { }

    /** 
     * Gets the character used for percent sign. Different for Arabic, etc.
     */
    public char getPercent() {
        return ' ';
    }

    /** 
     * Sets the character used for percent sign. Different for Arabic, etc.
     */
    public void setPercent(char percent) { }

    /** 
     * Gets the character used for a digit in a pattern.
     */
    public char getDigit() {
        return ' ';
    }

    /** 
     * Sets the character used for a digit in a pattern.
     */
    public void setDigit(char digit) { }

    /** 
     * Gets the character used to separate positive and negative subpatterns
     * in a pattern.
     */
    public char getPatternSeparator() {
        return ' ';
    }

    /** 
     * Sets the character used to separate positive and negative subpatterns
     * in a pattern.
     */
    public void setPatternSeparator(char patternSeparator) { }

    /** 
     * Gets the string used to represent infinity. Almost always left
     * unchanged.
     */
    public String getInfinity() {
        return null;
    }

    /** 
     * Sets the string used to represent infinity. Almost always left
     * unchanged.
     */
    public void setInfinity(String infinity) { }

    /** 
     * Gets the string used to represent "not a number". Almost always left
     * unchanged.
     */
    public String getNaN() {
        return null;
    }

    /** 
     * Sets the string used to represent "not a number". Almost always left
     * unchanged.
     */
    public void setNaN(String NaN) { }

    /** 
     * Gets the character used to represent minus sign. If no explicit
     * negative format is specified, one is formed by prefixing
     * minusSign to the positive format.
     */
    public char getMinusSign() {
        return ' ';
    }

    /** 
     * Sets the character used to represent minus sign. If no explicit
     * negative format is specified, one is formed by prefixing
     * minusSign to the positive format.
     */
    public void setMinusSign(char minusSign) { }

    /** 
     * Returns the currency symbol for the currency of these
     * DecimalFormatSymbols in their locale.
     * @since 1.2
     */
    public String getCurrencySymbol() {
        return null;
    }

    /** 
     * Sets the currency symbol for the currency of these
     * DecimalFormatSymbols in their locale.
     * @since 1.2
     */
    public void setCurrencySymbol(String currency) { }

    /** 
     * Returns the ISO 4217 currency code of the currency of these
     * DecimalFormatSymbols.
     * @since 1.2
     */
    public String getInternationalCurrencySymbol() {
        return null;
    }

    /** 
     * Sets the ISO 4217 currency code of the currency of these
     * DecimalFormatSymbols.
     * If the currency code is valid (as defined by
     * {@link java.util.Currency#getInstance(java.lang.String) Currency.getInstance}),
     * this also sets the currency attribute to the corresponding Currency
     * instance and the currency symbol attribute to the currency's symbol
     * in the DecimalFormatSymbols' locale. If the currency code is not valid,
     * then the currency attribute is set to null and the currency symbol
     * attribute is not modified.
     *
     * @see #setCurrency
     * @see #setCurrencySymbol
     * @since 1.2
     */
    public void setInternationalCurrencySymbol(String currencyCode) { }

    /** 
     * Gets the currency of these DecimalFormatSymbols. May be null if the
     * currency symbol attribute was previously set to a value that's not
     * a valid ISO 4217 currency code.
     *
     * @return the currency used, or null
     * @since 1.4
     */
    public Currency getCurrency() {
        return null;
    }

    /** 
     * Sets the currency of these DecimalFormatSymbols.
     * This also sets the currency symbol attribute to the currency's symbol
     * in the DecimalFormatSymbols' locale, and the international currency
     * symbol attribute to the currency's ISO 4217 currency code.
     *
     * @param currency the new currency to be used
     * @exception NullPointerException if <code>currency</code> is null
     * @since 1.4
     * @see #setCurrencySymbol
     * @see #setInternationalCurrencySymbol
     */
    public void setCurrency(Currency currency) { }

    /** 
     * Returns the monetary decimal separator.
     * @since 1.2
     */
    public char getMonetaryDecimalSeparator() {
        return ' ';
    }

    /** 
     * Sets the monetary decimal separator.
     * @since 1.2
     */
    public void setMonetaryDecimalSeparator(char sep) { }

    /** 
     * Standard override.
     */
    public Object clone() {
        return null;
    }

    /** 
     * Override equals.
     */
    public boolean equals(Object obj) {
        return false;
    }

    /** 
     * Override hashCode.
     */
    public int hashCode() {
        return 0;
    }

    /** 
     * Reads the default serializable fields, provides default values for objects
     * in older serial versions, and initializes non-serializable fields.
     * If <code>serialVersionOnStream</code>
     * is less than 1, initializes <code>monetarySeparator</code> to be
     * the same as <code>decimalSeparator</code> and <code>exponential</code>
     * to be 'E'.
     * If <code>serialVersionOnStream</code> is less then 2,
     * initializes <code>locale</code>to the root locale.
     * Sets <code>serialVersionOnStream</code> back to the maximum allowed value so that
     * default serialization will work properly if this object is streamed out again.
     * Initializes the currency from the intlCurrencySymbol field.
     *
     * @since JDK 1.1.6
     */
    private void readObject(ObjectInputStream stream)
        throws IOException, ClassNotFoundException
    { }

    static final long serialVersionUID = 5772796243397350300L;
}
