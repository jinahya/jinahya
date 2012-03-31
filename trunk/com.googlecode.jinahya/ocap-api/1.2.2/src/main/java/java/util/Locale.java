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


  


package java.util;

import java.io.*;

import java.security.AccessController;
import java.text.MessageFormat;

/** 
 * A <code>Locale</code> object represents a specific geographical, political,
 * or cultural region. An operation that requires a <code>Locale</code> to perform
 * its task is called <em>locale-sensitive</em> and uses the <code>Locale</code>
 * to tailor information for the user. For example, displaying a number
 * is a locale-sensitive operation--the number should be formatted
 * according to the customs/conventions of the user's native country,
 * region, or culture.
 *
 * <P>
 * Create a <code>Locale</code> object using the constructors in this class:
 * <blockquote>
 * <pre>
 * Locale(String language)
 * Locale(String language, String country)
 * Locale(String language, String country, String variant)
 * </pre>
 * </blockquote>
 * The language argument is a valid <STRONG>ISO Language Code.</STRONG> 
 * These codes are the lower-case, two-letter codes as defined by ISO-639.
 * You can find a full list of these codes at a number of sites, such as:
 * <BR><a href ="http://www.ics.uci.edu/pub/ietf/http/related/iso639.txt">
 * <code>http://www.ics.uci.edu/pub/ietf/http/related/iso639.txt</code></a>
 *
 * <P>
 * The country argument is a valid <STRONG>ISO Country Code.</STRONG> These 
 * codes are the upper-case, two-letter codes as defined by ISO-3166.
 * You can find a full list of these codes at a number of sites, such as:
 * <BR><a href="http://www.chemie.fu-berlin.de/diverse/doc/ISO_3166.html">
 * <code>http://www.chemie.fu-berlin.de/diverse/doc/ISO_3166.html</code></a>
 *
 * <P>
 * The variant argument is a vendor or browser-specific code.
 * For example, use WIN for Windows, MAC for Macintosh, and POSIX for POSIX.
 * Where there are two variants, separate them with an underscore, and
 * put the most important one first. For example, a Traditional Spanish collation 
 * might construct a locale with parameters for language, country and variant as: 
 * "es", "ES", "Traditional_WIN".
 *
 * <P>
 * Because a <code>Locale</code> object is just an identifier for a region,
 * no validity check is performed when you construct a <code>Locale</code>.
 * If you want to see whether particular resources are available for the
 * <code>Locale</code> you construct, you must query those resources. For
 * example, ask the <code>NumberFormat</code> for the locales it supports
 * using its <code>getAvailableLocales</code> method.
 * <BR><STRONG>Note:</STRONG> When you ask for a resource for a particular
 * locale, you get back the best available match, not necessarily
 * precisely what you asked for. For more information, look at
 * {@link ResourceBundle}.
 *
 * <P>
 * The <code>Locale</code> class provides a number of convenient constants
 * that you can use to create <code>Locale</code> objects for commonly used
 * locales. For example, the following creates a <code>Locale</code> object
 * for the United States:
 * <blockquote>
 * <pre>
 * Locale.US
 * </pre>
 * </blockquote>
 *
 * <P>
 * Once you've created a <code>Locale</code> you can query it for information about
 * itself. Use <code>getCountry</code> to get the ISO Country Code and
 * <code>getLanguage</code> to get the ISO Language Code. You can
 * use <code>getDisplayCountry</code> to get the
 * name of the country suitable for displaying to the user. Similarly,
 * you can use <code>getDisplayLanguage</code> to get the name of
 * the language suitable for displaying to the user. Interestingly,
 * the <code>getDisplayXXX</code> methods are themselves locale-sensitive
 * and have two versions: one that uses the default locale and one
 * that uses the locale specified as an argument.
 *
 * <P>
 * The Java 2 platform provides a number of classes that perform locale-sensitive
 * operations. For example, the <code>NumberFormat</code> class formats
 * numbers, currency, or percentages in a locale-sensitive manner. Classes
 * such as <code>NumberFormat</code> have a number of convenience methods
 * for creating a default object of that type. For example, the
 * <code>NumberFormat</code> class provides these three convenience methods
 * for creating a default <code>NumberFormat</code> object:
 * <blockquote>
 * <pre>
 * NumberFormat.getInstance()
 * NumberFormat.getCurrencyInstance()
 * NumberFormat.getPercentInstance()
 * </pre>
 * </blockquote>
 * These methods have two variants; one with an explicit locale
 * and one without; the latter using the default locale.
 * <blockquote>
 * <pre>
 * NumberFormat.getInstance(myLocale)
 * NumberFormat.getCurrencyInstance(myLocale)
 * NumberFormat.getPercentInstance(myLocale)
 * </pre>
 * </blockquote>
 * A <code>Locale</code> is the mechanism for identifying the kind of object
 * (<code>NumberFormat</code>) that you would like to get. The locale is
 * <STRONG>just</STRONG> a mechanism for identifying objects,
 * <STRONG>not</STRONG> a container for the objects themselves.
 *
 * <P>
 * Each class that performs locale-sensitive operations allows you
 * to get all the available objects of that type. You can sift
 * through these objects by language, country, or variant,
 * and use the display names to present a menu to the user.
 * For example, you can create a menu of all the collation objects
 * suitable for a given language. Such classes must implement these
 * three class methods:
 * <blockquote>
 * <pre>
 * public static Locale[] getAvailableLocales()
 * public static String getDisplayName(Locale objectLocale,
 *                                     Locale displayLocale)
 * public static final String getDisplayName(Locale objectLocale)
 *     // getDisplayName will throw MissingResourceException if the locale
 *     // is not one of the available locales.
 * </pre>
 * </blockquote>
 *
 * @see         ResourceBundle
 * @see         java.text.Format
 * @see         java.text.NumberFormat
 * @see         java.text.Collator
 * @version     1.55, 01/19/00
 * @author      Mark Davis
 * @since       1.1
 */
public final class Locale implements Cloneable, Serializable
{
    /** 
     *Useful constant for language.
     */
    public static final Locale ENGLISH = null;

    /** 
     *Useful constant for language.
     */
    public static final Locale FRENCH = null;

    /** 
     *Useful constant for language.
     */
    public static final Locale GERMAN = null;

    /** 
     *Useful constant for language.
     */
    public static final Locale ITALIAN = null;

    /** 
     *Useful constant for language.
     */
    public static final Locale JAPANESE = null;

    /** 
     *Useful constant for language.
     */
    public static final Locale KOREAN = null;

    /** 
     *Useful constant for language.
     */
    public static final Locale CHINESE = null;

    /** 
     *Useful constant for language.
     */
    public static final Locale SIMPLIFIED_CHINESE = null;

    /** 
     *Useful constant for language.
     */
    public static final Locale TRADITIONAL_CHINESE = null;

    /** 
     *Useful constant for country.
     */
    public static final Locale FRANCE = null;

    /** 
     *Useful constant for country.
     */
    public static final Locale GERMANY = null;

    /** 
     *Useful constant for country.
     */
    public static final Locale ITALY = null;

    /** 
     *Useful constant for country.
     */
    public static final Locale JAPAN = null;

    /** 
     *Useful constant for country.
     */
    public static final Locale KOREA = null;

    /** 
     *Useful constant for country.
     */
    public static final Locale CHINA = null;

    /** 
     *Useful constant for country.
     */
    public static final Locale PRC = null;

    /** 
     *Useful constant for country.
     */
    public static final Locale TAIWAN = null;

    /** 
     *Useful constant for country.
     */
    public static final Locale UK = null;

    /** 
     *Useful constant for country.
     */
    public static final Locale US = null;

    /** 
     *Useful constant for country.
     */
    public static final Locale CANADA = null;

    /** 
     *Useful constant for country.
     */
    public static final Locale CANADA_FRENCH = null;

    /** 
     * @serial
     * @see #getLanguage
     */
    private String language;

    /** 
     * @serial
     * @see #getCountry
     */
    private String country;

    /** 
     * @serial
     * @see #getVariant
     */
    private String variant;

    /** 
     * Placeholder for the object's hash code.  Always -1.
     * @serial
     */
    private int hashcode;

    static final long serialVersionUID = 9149081749638150636L;

    /** 
     * Construct a locale from language, country, variant.
     * NOTE:  ISO 639 is not a stable standard; some of the language codes it defines
     * (specifically iw, ji, and in) have changed.  This constructor accepts both the
     * old codes (iw, ji, and in) and the new codes (he, yi, and id), but all other
     * API on Locale will return only the OLD codes.
     * @param language lowercase two-letter ISO-639 code.
     * @param country uppercase two-letter ISO-3166 code.
     * @param variant vendor and browser specific code. See class description.
     * @exception NullPointerException thrown if any argument is null.
     */
    public Locale(String language, String country, String variant) { }

    /** 
     * Construct a locale from language, country.
     * NOTE:  ISO 639 is not a stable standard; some of the language codes it defines
     * (specifically iw, ji, and in) have changed.  This constructor accepts both the
     * old codes (iw, ji, and in) and the new codes (he, yi, and id), but all other
     * API on Locale will return only the OLD codes.
     * @param language lowercase two-letter ISO-639 code.
     * @param country uppercase two-letter ISO-3166 code.
     * @exception NullPointerException thrown if either argument is null.
     */
    public Locale(String language, String country) { }

    /** 
     * Construct a locale from a language code.
     * NOTE:  ISO 639 is not a stable standard; some of the language codes it defines
     * (specifically iw, ji, and in) have changed.  This constructor accepts both the
     * old codes (iw, ji, and in) and the new codes (he, yi, and id), but all other
     * API on Locale will return only the OLD codes.
     * @param language lowercase two-letter ISO-639 code.
     * @exception NullPointerException thrown if argument is null.
     * @since 1.4
     */
    public Locale(String language) { }

    /** 
     * Gets the current value of the default locale for this instance
     * of the Java Virtual Machine.
     * <p>
     * The Java Virtual Machine sets the default locale during startup
     * based on the host environment. It is used by many locale-sensitive
     * methods if no locale is explicitly specified.
     * It can be changed using the
     * {@link #setDefault(java.util.Locale) setDefault} method.
     *
     * @return the default locale for this instance of the Java Virtual Machine
     */
    public static Locale getDefault() {
        return null;
    }

    /** 
     * Sets the default locale for this instance of the Java Virtual Machine.
     * This does not affect the host locale.
     * <p>
     * If there is a security manager, its <code>checkPermission</code>
     * method is called with a <code>PropertyPermission("user.language", "write")</code>
     * permission before the default locale is changed.
     * <p>
     * The Java Virtual Machine sets the default locale during startup
     * based on the host environment. It is used by many locale-sensitive
     * methods if no locale is explicitly specified.
     * <p>
     * Since changing the default locale may affect many different areas
     * of functionality, this method should only be used if the caller
     * is prepared to reinitialize locale-sensitive code running
     * within the same Java Virtual Machine, such as the user interface.
     *
     * @throws SecurityException
     *        if a security manager exists and its
     *        <code>checkPermission</code> method doesn't allow the operation.
     * @throws NullPointerException if <code>newLocale</code> is null
     * @param newLocale the new default locale
     * @see SecurityManager#checkPermission
     * @see java.util.PropertyPermission
     */
    public static synchronized void setDefault(Locale newLocale) { }

    /** 
     * Returns a list of all installed locales.
     */
    public static Locale[] getAvailableLocales() {
        return null;
    }

    /** 
     * Returns a list of all 2-letter country codes defined in ISO 3166.
     * Can be used to create Locales.
     */
    public static String[] getISOCountries() {
        return null;
    }

    /** 
     * Returns a list of all 2-letter language codes defined in ISO 639.
     * Can be used to create Locales.
     * [NOTE:  ISO 639 is not a stable standard-- some languages' codes have changed.
     * The list this function returns includes both the new and the old codes for the
     * languages whose codes have changed.]
     */
    public static String[] getISOLanguages() {
        return null;
    }

    /** 
     * Returns the language code for this locale, which will either be the empty string
     * or a lowercase ISO 639 code.
     * <p>NOTE:  ISO 639 is not a stable standard-- some languages' codes have changed.
     * Locale's constructor recognizes both the new and the old codes for the languages
     * whose codes have changed, but this function always returns the old code.  If you
     * want to check for a specific language whose code has changed, don't do <pre>
     * if (locale.getLanguage().equals("he")
     *    ...
     * </pre>Instead, do<pre>
     * if (locale.getLanguage().equals(new Locale("he", "", "").getLanguage())
     *    ...</pre>
     * @see #getDisplayLanguage
     */
    public String getLanguage() {
        return null;
    }

    /** 
     * Returns the country/region code for this locale, which will either be the empty string
     * or an upercase ISO 3166 2-letter code.
     * @see #getDisplayCountry
     */
    public String getCountry() {
        return null;
    }

    /** 
     * Returns the variant code for this locale.
     * @see #getDisplayVariant
     */
    public String getVariant() {
        return null;
    }

    /** 
     * Getter for the programmatic name of the entire locale,
     * with the language, country and variant separated by underbars.
     * Language is always lower case, and country is always upper case.
     * If the language is missing, the string will begin with an underbar.
     * If both the language and country fields are missing, this function
     * will return the empty string, even if the variant field is filled in
     * (you can't have a locale with just a variant-- the variant must accompany
     * a valid language or country code).
     * Examples: "en", "de_DE", "_GB", "en_US_WIN", "de__POSIX", "fr__MAC"
     * @see #getDisplayName
     */
    public final String toString() {
        return null;
    }

    /** 
     * Returns a three-letter abbreviation for this locale's language.  If the locale
     * doesn't specify a language, this will be the empty string.  Otherwise, this will
     * be a lowercase ISO 639-2/T language code.
     * The ISO 639-2 language codes can be found on-line at
     *   <a href="ftp://dkuug.dk/i18n/iso-639-2.txt"><code>ftp://dkuug.dk/i18n/iso-639-2.txt</code></a>
     * @exception MissingResourceException Throws MissingResourceException if the
     * three-letter language abbreviation is not available for this locale.
     */
    public String getISO3Language() throws MissingResourceException {
        return null;
    }

    /** 
     * Returns a three-letter abbreviation for this locale's country.  If the locale
     * doesn't specify a country, this will be tbe the empty string.  Otherwise, this will
     * be an uppercase ISO 3166 3-letter country code.
     * @exception MissingResourceException Throws MissingResourceException if the
     * three-letter country abbreviation is not available for this locale.
     */
    public String getISO3Country() throws MissingResourceException {
        return null;
    }

    /** 
     * Returns a name for the locale's language that is appropriate for display to the
     * user.
     * If possible, the name returned will be localized for the default locale.
     * For example, if the locale is fr_FR and the default locale
     * is en_US, getDisplayLanguage() will return "French"; if the locale is en_US and
     * the default locale is fr_FR, getDisplayLanguage() will return "anglais".
     * If the name returned cannot be localized for the default locale,
     * (say, we don't have a Japanese name for Croatian),
     * this function falls back on the English name, and uses the ISO code as a last-resort
     * value.  If the locale doesn't specify a language, this function returns the empty string.
     */
    public final String getDisplayLanguage() {
        return null;
    }

    /** 
     * Returns a name for the locale's language that is appropriate for display to the
     * user.
     * If possible, the name returned will be localized according to inLocale.
     * For example, if the locale is fr_FR and inLocale
     * is en_US, getDisplayLanguage() will return "French"; if the locale is en_US and
     * inLocale is fr_FR, getDisplayLanguage() will return "anglais".
     * If the name returned cannot be localized according to inLocale,
     * (say, we don't have a Japanese name for Croatian),
     * this function falls back on the default locale, on the English name, and finally
     * on the ISO code as a last-resort value.  If the locale doesn't specify a language,
     * this function returns the empty string.
     */
    public String getDisplayLanguage(Locale inLocale) {
        return null;
    }

    /** 
     * Returns a name for the locale's country that is appropriate for display to the
     * user.
     * If possible, the name returned will be localized for the default locale.
     * For example, if the locale is fr_FR and the default locale
     * is en_US, getDisplayCountry() will return "France"; if the locale is en_US and
     * the default locale is fr_FR, getDisplayLanguage() will return "Etats-Unis".
     * If the name returned cannot be localized for the default locale,
     * (say, we don't have a Japanese name for Croatia),
     * this function falls back on the English name, and uses the ISO code as a last-resort
     * value.  If the locale doesn't specify a country, this function returns the empty string.
     */
    public final String getDisplayCountry() {
        return null;
    }

    /** 
     * Returns a name for the locale's country that is appropriate for display to the
     * user.
     * If possible, the name returned will be localized according to inLocale.
     * For example, if the locale is fr_FR and inLocale
     * is en_US, getDisplayCountry() will return "France"; if the locale is en_US and
     * inLocale is fr_FR, getDisplayLanguage() will return "Etats-Unis".
     * If the name returned cannot be localized according to inLocale.
     * (say, we don't have a Japanese name for Croatia),
     * this function falls back on the default locale, on the English name, and finally
     * on the ISO code as a last-resort value.  If the locale doesn't specify a country,
     * this function returns the empty string.
     */
    public String getDisplayCountry(Locale inLocale) {
        return null;
    }

    /** 
     * Returns a name for the locale's variant code that is appropriate for display to the
     * user.  If possible, the name will be localized for the default locale.  If the locale
     * doesn't specify a variant code, this function returns the empty string.
     */
    public final String getDisplayVariant() {
        return null;
    }

    /** 
     * Returns a name for the locale's variant code that is appropriate for display to the
     * user.  If possible, the name will be localized for inLocale.  If the locale
     * doesn't specify a variant code, this function returns the empty string.
     */
    public String getDisplayVariant(Locale inLocale) {
        return null;
    }

    /** 
     * Returns a name for the locale that is appropriate for display to the
     * user.  This will be the values returned by getDisplayLanguage(), getDisplayCountry(),
     * and getDisplayVariant() assembled into a single string.  The display name will have
     * one of the following forms:<p><blockquote>
     * language (country, variant)<p>
     * language (country)<p>
     * language (variant)<p>
     * country (variant)<p>
     * language<p>
     * country<p>
     * variant<p></blockquote>
     * depending on which fields are specified in the locale.  If the language, country,
     * and variant fields are all empty, this function returns the empty string.
     */
    public final String getDisplayName() {
        return null;
    }

    /** 
     * Returns a name for the locale that is appropriate for display to the
     * user.  This will be the values returned by getDisplayLanguage(), getDisplayCountry(),
     * and getDisplayVariant() assembled into a single string.  The display name will have
     * one of the following forms:<p><blockquote>
     * language (country, variant)<p>
     * language (country)<p>
     * language (variant)<p>
     * country (variant)<p>
     * language<p>
     * country<p>
     * variant<p></blockquote>
     * depending on which fields are specified in the locale.  If the language, country,
     * and variant fields are all empty, this function returns the empty string.
     */
    public String getDisplayName(Locale inLocale) {
        return null;
    }

    /** 
     * Overrides Cloneable
     */
    public Object clone() {
        return null;
    }

    /** 
     * Override hashCode.
     * Since Locales are often used in hashtables, caches the value
     * for speed.
     */
    public synchronized int hashCode() {
        return 0;
    }

    /** 
     * Returns true if this Locale is equal to another object.  A Locale is
     * deemed equal to another Locale with identical language, country,
     * and variant, and unequal to all other objects.
     *
     * @return true if this Locale is equal to the specified object.
     */
    public boolean equals(Object obj) {
        return false;
    }

    /** 
     * @serialData The first three fields are three <code>String</code> objects:
     * the first is a 2-letter ISO 639 code representing the locale's language,
     * the second is a 2-letter ISO 3166 code representing the locale's region or
     * country, and the third is an optional chain of variant codes defined by this
     * library.  Any of the fields may be the empty string.  The fourth field is an
     * <code>int</code>representing the locale's hash code, but is ignored by
     * <code>readObject()</code>.  Whatever this field's value, the hash code is
     * initialized to -1, a sentinel value that indicates the hash code must be
     * recomputed.
     */
    private void readObject(ObjectInputStream in)
        throws IOException, ClassNotFoundException
    { }

    /** 
     * @serialData The first three fields are three <code>String</code> objects:
     * the first is a 2-letter ISO 639 code representing the locale's language,
     * the second is a 2-letter ISO 3166 code representing the locale's region or
     * country, and the third is an optional chain of variant codes defined by this
     * library.  Any of the fields may be the empty string.  The fourth field is an
     * <code>int</code> whose value is always -1.  This is a sentinel value indicating
     * the <code>Locale</code>'s hash code must be recomputed.
     */
    private void writeObject(ObjectOutputStream out) throws IOException { }
}
