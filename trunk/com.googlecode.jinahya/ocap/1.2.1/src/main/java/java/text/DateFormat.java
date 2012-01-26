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

import java.io.InvalidObjectException;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.MissingResourceException;
import java.util.TimeZone;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Date;

/** 
 * DateFormat is an abstract class for date/time formatting subclasses which
 * formats and parses dates or time in a language-independent manner.
 * The date/time formatting subclass, such as SimpleDateFormat, allows for
 * formatting (i.e., date -> text), parsing (text -> date), and
 * normalization.  The date is represented as a <code>Date</code> object or
 * as the milliseconds since January 1, 1970, 00:00:00 GMT.
 *
 * <p>DateFormat provides many class methods for obtaining default date/time
 * formatters based on the default or a given locale and a number of formatting
 * styles. The formatting styles include FULL, LONG, MEDIUM, and SHORT. More
 * detail and examples of using these styles are provided in the method
 * descriptions.
 *
 * <p>DateFormat helps you to format and parse dates for any locale.
 * Your code can be completely independent of the locale conventions for
 * months, days of the week, or even the calendar format: lunar vs. solar.
 *
 * <p>To format a date for the current Locale, use one of the
 * static factory methods:
 * <pre>
 *  myString = DateFormat.getDateInstance().format(myDate);
 * </pre>
 * <p>If you are formatting multiple dates, it is
 * more efficient to get the format and use it multiple times so that
 * the system doesn't have to fetch the information about the local
 * language and country conventions multiple times.
 * <pre>
 *  DateFormat df = DateFormat.getDateInstance();
 *  for (int i = 0; i < a.length; ++i) {
 *    output.println(df.format(myDate[i]) + "; ");
 *  }
 * </pre>
 * <p>To format a date for a different Locale, specify it in the
 * call to getDateInstance().
 * <pre>
 *  DateFormat df = DateFormat.getDateInstance(DateFormat.LONG, Locale.FRANCE);
 * </pre>
 * <p>You can use a DateFormat to parse also.
 * <pre>
 *  myDate = df.parse(myString);
 * </pre>
 * <p>Use getDateInstance to get the normal date format for that country.
 * There are other static factory methods available.
 * Use getTimeInstance to get the time format for that country.
 * Use getDateTimeInstance to get a date and time format. You can pass in 
 * different options to these factory methods to control the length of the
 * result; from SHORT to MEDIUM to LONG to FULL. The exact result depends
 * on the locale, but generally:
 * <ul><li>SHORT is completely numeric, such as 12.13.52 or 3:30pm
 * <li>MEDIUM is longer, such as Jan 12, 1952
 * <li>LONG is longer, such as January 12, 1952 or 3:30:32pm
 * <li>FULL is pretty completely specified, such as
 * Tuesday, April 12, 1952 AD or 3:30:42pm PST.
 * </ul>
 *
 * <p>You can also set the time zone on the format if you wish.
 * If you want even more control over the format or parsing,
 * (or want to give your users more control),
 * you can try casting the DateFormat you get from the factory methods
 * to a SimpleDateFormat. This will work for the majority
 * of countries; just remember to put it in a try block in case you
 * encounter an unusual one.
 *
 * <p>You can also use forms of the parse and format methods with
 * ParsePosition and FieldPosition to
 * allow you to
 * <ul><li>progressively parse through pieces of a string.
 * <li>align any particular field, or find out where it is for selection
 * on the screen.
 * </ul>
 *
 * <h4><a name="synchronization">Synchronization</a></h4>
 *
 * <p>
 * Date formats are not synchronized.
 * It is recommended to create separate format instances for each thread.
 * If multiple threads access a format concurrently, it must be synchronized
 * externally.
 *
 * @see          Format
 * @see          NumberFormat
 * @see          SimpleDateFormat
 * @see          java.util.Calendar
 * @see          java.util.GregorianCalendar
 * @see          java.util.TimeZone
 * @version      1.44, 10/25/05
 * @author       Mark Davis, Chen-Lieh Huang, Alan Liu
 */
public abstract class DateFormat extends Format
{
    /** 
     * The calendar that <code>DateFormat</code> uses to produce the time field
     * values needed to implement date and time formatting.  Subclasses should
     * initialize this to a calendar appropriate for the locale associated with
     * this <code>DateFormat</code>.
     * @serial
     */
    protected Calendar calendar;

    /** 
     * The number formatter that <code>DateFormat</code> uses to format numbers
     * in dates and times.  Subclasses should initialize this to a number format
     * appropriate for the locale associated with this <code>DateFormat</code>.
     * @serial
     */
    protected NumberFormat numberFormat;

    /** 
     * Useful constant for ERA field alignment.
     * Used in FieldPosition of date/time formatting.
     */
    public static final int ERA_FIELD = 0;

    /** 
     * Useful constant for YEAR field alignment.
     * Used in FieldPosition of date/time formatting.
     */
    public static final int YEAR_FIELD = 1;

    /** 
     * Useful constant for MONTH field alignment.
     * Used in FieldPosition of date/time formatting.
     */
    public static final int MONTH_FIELD = 2;

    /** 
     * Useful constant for DATE field alignment.
     * Used in FieldPosition of date/time formatting.
     */
    public static final int DATE_FIELD = 3;

    /** 
     * Useful constant for one-based HOUR_OF_DAY field alignment.
     * Used in FieldPosition of date/time formatting.
     * HOUR_OF_DAY1_FIELD is used for the one-based 24-hour clock.
     * For example, 23:59 + 01:00 results in 24:59.
     */
    public static final int HOUR_OF_DAY1_FIELD = 4;

    /** 
     * Useful constant for zero-based HOUR_OF_DAY field alignment.
     * Used in FieldPosition of date/time formatting.
     * HOUR_OF_DAY0_FIELD is used for the zero-based 24-hour clock.
     * For example, 23:59 + 01:00 results in 00:59.
     */
    public static final int HOUR_OF_DAY0_FIELD = 5;

    /** 
     * Useful constant for MINUTE field alignment.
     * Used in FieldPosition of date/time formatting.
     */
    public static final int MINUTE_FIELD = 6;

    /** 
     * Useful constant for SECOND field alignment.
     * Used in FieldPosition of date/time formatting.
     */
    public static final int SECOND_FIELD = 7;

    /** 
     * Useful constant for MILLISECOND field alignment.
     * Used in FieldPosition of date/time formatting.
     */
    public static final int MILLISECOND_FIELD = 8;

    /** 
     * Useful constant for DAY_OF_WEEK field alignment.
     * Used in FieldPosition of date/time formatting.
     */
    public static final int DAY_OF_WEEK_FIELD = 9;

    /** 
     * Useful constant for DAY_OF_YEAR field alignment.
     * Used in FieldPosition of date/time formatting.
     */
    public static final int DAY_OF_YEAR_FIELD = 10;

    /** 
     * Useful constant for DAY_OF_WEEK_IN_MONTH field alignment.
     * Used in FieldPosition of date/time formatting.
     */
    public static final int DAY_OF_WEEK_IN_MONTH_FIELD = 11;

    /** 
     * Useful constant for WEEK_OF_YEAR field alignment.
     * Used in FieldPosition of date/time formatting.
     */
    public static final int WEEK_OF_YEAR_FIELD = 12;

    /** 
     * Useful constant for WEEK_OF_MONTH field alignment.
     * Used in FieldPosition of date/time formatting.
     */
    public static final int WEEK_OF_MONTH_FIELD = 13;

    /** 
     * Useful constant for AM_PM field alignment.
     * Used in FieldPosition of date/time formatting.
     */
    public static final int AM_PM_FIELD = 14;

    /** 
     * Useful constant for one-based HOUR field alignment.
     * Used in FieldPosition of date/time formatting.
     * HOUR1_FIELD is used for the one-based 12-hour clock.
     * For example, 11:30 PM + 1 hour results in 12:30 AM.
     */
    public static final int HOUR1_FIELD = 15;

    /** 
     * Useful constant for zero-based HOUR field alignment.
     * Used in FieldPosition of date/time formatting.
     * HOUR0_FIELD is used for the zero-based 12-hour clock.
     * For example, 11:30 PM + 1 hour results in 00:30 AM.
     */
    public static final int HOUR0_FIELD = 16;

    /** 
     * Useful constant for TIMEZONE field alignment.
     * Used in FieldPosition of date/time formatting.
     */
    public static final int TIMEZONE_FIELD = 17;

    /** 
     * Constant for full style pattern.
     */
    public static final int FULL = 0;

    /** 
     * Constant for long style pattern.
     */
    public static final int LONG = 1;

    /** 
     * Constant for medium style pattern.
     */
    public static final int MEDIUM = 2;

    /** 
     * Constant for short style pattern.
     */
    public static final int SHORT = 3;

    /** 
     * Constant for default style pattern.  Its value is MEDIUM.
     */
    public static final int DEFAULT = 2;

    private static final long serialVersionUID = 7218322306649953788L;

    /** 
     * Create a new date format.
     */
    protected DateFormat() { }

    /** 
     * Overrides Format.
     * Formats a time object into a time string. Examples of time objects
     * are a time value expressed in milliseconds and a Date object.
     * @param obj must be a Number or a Date.
     * @param toAppendTo the string buffer for the returning time string.
     * @return the formatted time string.
     * @param fieldPosition keeps track of the position of the field
     * within the returned string.
     * On input: an alignment field,
     * if desired. On output: the offsets of the alignment field. For
     * example, given a time text "1996.07.10 AD at 15:08:56 PDT",
     * if the given fieldPosition is DateFormat.YEAR_FIELD, the
     * begin index and end index of fieldPosition will be set to
     * 0 and 4, respectively.
     * Notice that if the same time field appears
     * more than once in a pattern, the fieldPosition will be set for the first
     * occurrence of that time field. For instance, formatting a Date to
     * the time string "1 PM PDT (Pacific Daylight Time)" using the pattern
     * "h a z (zzzz)" and the alignment field DateFormat.TIMEZONE_FIELD,
     * the begin index and end index of fieldPosition will be set to
     * 5 and 8, respectively, for the first occurrence of the timezone
     * pattern character 'z'.
     * @see java.text.Format
     */
    public final StringBuffer format(Object obj, StringBuffer toAppendTo,
        FieldPosition fieldPosition)
    {
        return null;
    }

    /** 
     * Formats a Date into a date/time string.
     * @param date a Date to be formatted into a date/time string.
     * @param toAppendTo the string buffer for the returning date/time string.
     * @param fieldPosition keeps track of the position of the field
     * within the returned string.
     * On input: an alignment field,
     * if desired. On output: the offsets of the alignment field. For
     * example, given a time text "1996.07.10 AD at 15:08:56 PDT",
     * if the given fieldPosition is DateFormat.YEAR_FIELD, the
     * begin index and end index of fieldPosition will be set to
     * 0 and 4, respectively.
     * Notice that if the same time field appears
     * more than once in a pattern, the fieldPosition will be set for the first
     * occurrence of that time field. For instance, formatting a Date to
     * the time string "1 PM PDT (Pacific Daylight Time)" using the pattern
     * "h a z (zzzz)" and the alignment field DateFormat.TIMEZONE_FIELD,
     * the begin index and end index of fieldPosition will be set to
     * 5 and 8, respectively, for the first occurrence of the timezone
     * pattern character 'z'.
     * @return the formatted date/time string.
     */
    public abstract StringBuffer format(Date date, StringBuffer toAppendTo,
        FieldPosition fieldPosition);

    /** 
     * Formats a Date into a date/time string.
     * @param date the time value to be formatted into a time string.
     * @return the formatted time string.
     */
    public final String format(Date date) {
        return null;
    }

    /** 
     * Parses text from the beginning of the given string to produce a date.
     * The method may not use the entire text of the given string.
     * <p>
     * See the {@link #parse(String, ParsePosition)} method for more information
     * on date parsing.
     *
     * @param source A <code>String</code> whose beginning should be parsed.
     * @return A <code>Date</code> parsed from the string.
     * @exception ParseException if the beginning of the specified string
     *            cannot be parsed.
     */
    public Date parse(String source) throws ParseException {
        return null;
    }

    /** 
     * Parse a date/time string according to the given parse position.  For
     * example, a time text "07/10/96 4:5 PM, PDT" will be parsed into a Date
     * that is equivalent to Date(837039928046).
     *
     * <p> By default, parsing is lenient: If the input is not in the form used
     * by this object's format method but can still be parsed as a date, then
     * the parse succeeds.  Clients may insist on strict adherence to the
     * format by calling setLenient(false).
     *
     * @see java.text.DateFormat#setLenient(boolean)
     *
     * @param source  The date/time string to be parsed
     *
     * @param pos   On input, the position at which to start parsing; on
     *              output, the position at which parsing terminated, or the
     *              start position if the parse failed.
     *
     * @return      A Date, or null if the input could not be parsed
     */
    public abstract Date parse(String source, ParsePosition pos);

    /** 
     * Parses text from a string to produce a <code>Date</code>.
     * <p>
     * The method attempts to parse text starting at the index given by
     * <code>pos</code>.
     * If parsing succeeds, then the index of <code>pos</code> is updated
     * to the index after the last character used (parsing does not necessarily
     * use all characters up to the end of the string), and the parsed
     * date is returned. The updated <code>pos</code> can be used to
     * indicate the starting point for the next call to this method.
     * If an error occurs, then the index of <code>pos</code> is not
     * changed, the error index of <code>pos</code> is set to the index of
     * the character where the error occurred, and null is returned.
     * <p>
     * See the {@link #parse(String, ParsePosition)} method for more information
     * on date parsing.
     *
     * @param source A <code>String</code>, part of which should be parsed.
     * @param pos A <code>ParsePosition</code> object with index and error
     *            index information as described above.
     * @return A <code>Date</code> parsed from the string. In case of
     *         error, returns null.
     * @exception NullPointerException if <code>pos</code> is null.
     */
    public Object parseObject(String source, ParsePosition pos) {
        return null;
    }

    /** 
     * Gets the time formatter with the default formatting style
     * for the default locale.
     * @return a time formatter.
     */
    public static final DateFormat getTimeInstance() {
        return null;
    }

    /** 
     * Gets the time formatter with the given formatting style
     * for the default locale.
     * @param style the given formatting style. For example,
     * SHORT for "h:mm a" in the US locale.
     * @return a time formatter.
     */
    public static final DateFormat getTimeInstance(int style) {
        return null;
    }

    /** 
     * Gets the time formatter with the given formatting style
     * for the given locale.
     * @param style the given formatting style. For example,
     * SHORT for "h:mm a" in the US locale.
     * @param aLocale the given locale.
     * @return a time formatter.
     */
    public static final DateFormat getTimeInstance(int style, Locale aLocale) {
        return null;
    }

    /** 
     * Gets the date formatter with the default formatting style
     * for the default locale.
     * @return a date formatter.
     */
    public static final DateFormat getDateInstance() {
        return null;
    }

    /** 
     * Gets the date formatter with the given formatting style
     * for the default locale.
     * @param style the given formatting style. For example,
     * SHORT for "M/d/yy" in the US locale.
     * @return a date formatter.
     */
    public static final DateFormat getDateInstance(int style) {
        return null;
    }

    /** 
     * Gets the date formatter with the given formatting style
     * for the given locale.
     * @param style the given formatting style. For example,
     * SHORT for "M/d/yy" in the US locale.
     * @param aLocale the given locale.
     * @return a date formatter.
     */
    public static final DateFormat getDateInstance(int style, Locale aLocale) {
        return null;
    }

    /** 
     * Gets the date/time formatter with the default formatting style
     * for the default locale.
     * @return a date/time formatter.
     */
    public static final DateFormat getDateTimeInstance() {
        return null;
    }

    /** 
     * Gets the date/time formatter with the given date and time
     * formatting styles for the default locale.
     * @param dateStyle the given date formatting style. For example,
     * SHORT for "M/d/yy" in the US locale.
     * @param timeStyle the given time formatting style. For example,
     * SHORT for "h:mm a" in the US locale.
     * @return a date/time formatter.
     */
    public static final DateFormat getDateTimeInstance(int dateStyle, int
        timeStyle)
    {
        return null;
    }

    /** 
     * Gets the date/time formatter with the given formatting styles
     * for the given locale.
     * @param dateStyle the given date formatting style.
     * @param timeStyle the given time formatting style.
     * @param aLocale the given locale.
     * @return a date/time formatter.
     */
    public static final DateFormat getDateTimeInstance(int dateStyle, int
        timeStyle, Locale aLocale)
    {
        return null;
    }

    /** 
     * Get a default date/time formatter that uses the SHORT style for both the
     * date and the time.
     */
    public static final DateFormat getInstance() {
        return null;
    }

    /** 
     * Gets the set of locales for which DateFormats are installed.
     * @return the set of locales for which DateFormats are installed.
     */
    public static Locale[] getAvailableLocales() {
        return null;
    }

    /** 
     * Set the calendar to be used by this date format.  Initially, the default
     * calendar for the specified or default locale is used.
     * @param newCalendar the new Calendar to be used by the date format
     */
    public void setCalendar(Calendar newCalendar) { }

    /** 
     * Gets the calendar associated with this date/time formatter.
     * @return the calendar associated with this date/time formatter.
     */
    public Calendar getCalendar() {
        return null;
    }

    /** 
     * Allows you to set the number formatter.
     * @param newNumberFormat the given new NumberFormat.
     */
    public void setNumberFormat(NumberFormat newNumberFormat) { }

    /** 
     * Gets the number formatter which this date/time formatter uses to
     * format and parse a time.
     * @return the number formatter which this date/time formatter uses.
     */
    public NumberFormat getNumberFormat() {
        return null;
    }

    /** 
     * Sets the time zone for the calendar of this DateFormat object.
     * @param zone the given new time zone.
     */
    public void setTimeZone(TimeZone zone) { }

    /** 
     * Gets the time zone.
     * @return the time zone associated with the calendar of DateFormat.
     */
    public TimeZone getTimeZone() {
        return null;
    }

    /** 
     * Specify whether or not date/time parsing is to be lenient.  With
     * lenient parsing, the parser may use heuristics to interpret inputs that
     * do not precisely match this object's format.  With strict parsing,
     * inputs must match this object's format.
     * @param lenient when true, parsing is lenient
     * @see java.util.Calendar#setLenient
     */
    public void setLenient(boolean lenient) { }

    /** 
     * Tell whether date/time parsing is to be lenient.
     */
    public boolean isLenient() {
        return false;
    }

    /** 
     * Overrides hashCode
     */
    public int hashCode() {
        return 0;
    }

    /** 
     * Overrides equals
     */
    public boolean equals(Object obj) {
        return false;
    }

    /** 
     * Overrides Cloneable
     */
    public Object clone() {
        return null;
    }

    /** 
     * Defines constants that are used as attribute keys in the
     * <code>AttributedCharacterIterator</code> returned
     * from <code>DateFormat.formatToCharacterIterator</code> and as
     * field identifiers in <code>FieldPosition</code>.
     * <p>
     * The class also provides two methods to map
     * between its constants and the corresponding Calendar constants.
     *
     * @since 1.4
     * @see java.util.Calendar
     */
    public static class Field extends Format.Field
    {
        /** 
         * Constant identifying the era field.
         */
        public static final java.text.DateFormat.Field ERA = null;

        /** 
         * Constant identifying the year field.
         */
        public static final java.text.DateFormat.Field YEAR = null;

        /** 
         * Constant identifying the month field.
         */
        public static final java.text.DateFormat.Field MONTH = null;

        /** 
         * Constant identifying the day of month field.
         */
        public static final java.text.DateFormat.Field DAY_OF_MONTH = null;

        /** 
         * Constant identifying the hour of day field, where the legal values
         * are 1 to 24.
         */
        public static final java.text.DateFormat.Field HOUR_OF_DAY1 = null;

        /** 
         * Constant identifying the hour of day field, where the legal values
         * are 0 to 23.
         */
        public static final java.text.DateFormat.Field HOUR_OF_DAY0 = null;

        /** 
         * Constant identifying the minute field.
         */
        public static final java.text.DateFormat.Field MINUTE = null;

        /** 
         * Constant identifying the second field.
         */
        public static final java.text.DateFormat.Field SECOND = null;

        /** 
         * Constant identifying the millisecond field.
         */
        public static final java.text.DateFormat.Field MILLISECOND = null;

        /** 
         * Constant identifying the day of week field.
         */
        public static final java.text.DateFormat.Field DAY_OF_WEEK = null;

        /** 
         * Constant identifying the day of year field.
         */
        public static final java.text.DateFormat.Field DAY_OF_YEAR = null;

        /** 
         * Constant identifying the day of week field.
         */
        public static final java.text.DateFormat.Field DAY_OF_WEEK_IN_MONTH =
            null;

        /** 
         * Constant identifying the week of year field.
         */
        public static final java.text.DateFormat.Field WEEK_OF_YEAR = null;

        /** 
         * Constant identifying the week of month field.
         */
        public static final java.text.DateFormat.Field WEEK_OF_MONTH = null;

        /** 
         * Constant identifying the time of day indicator
         * (e.g. "a.m." or "p.m.") field.
         */
        public static final java.text.DateFormat.Field AM_PM = null;

        /** 
         * Constant identifying the hour field, where the legal values are
         * 1 to 12.
         */
        public static final java.text.DateFormat.Field HOUR1 = null;

        /** 
         * Constant identifying the hour field, where the legal values are
         * 0 to 11.
         */
        public static final java.text.DateFormat.Field HOUR0 = null;

        /** 
         * Constant identifying the time zone field.
         */
        public static final java.text.DateFormat.Field TIME_ZONE = null;

        /** Calendar field.  */
        private int calendarField;

        /** 
         * Creates a Field with the specified name.
         * calendarField is used to identify the <code>Calendar</code>
         * field this attribute represents. Use -1 if this field does
         * not have a corresponding <code>Calendar</code> value.
         *
         * @param name Name of the attribute
         * @param calendarField Calendar constant
         */
        protected Field(String name, int calendarField) { 
       	    super(name);
        }

        /** 
         * Returns the <code>Field</code> constant that corresponds to
         * the <code>Calendar</code> constant <code>calendarField</code>.
         * If there is no direct mapping between the <code>Calendar</code>
         * constant and a <code>Field</code>, null is returned.
         *
         * @throws IllegalArgumentException if <code>calendarField</code> is
         *         not the value of a <code>Calendar</code> field constant.
         * @param calendarField Calendar field constant
         * @return Field instance representing calendarField.
         * @see java.util.Calendar
         */
        public static java.text.DateFormat.Field ofCalendarField(int
            calendarField)
        {
            return null;
        }

        /** 
         * Returns the <code>Calendar</code> field associated with this
         * attribute. For example, if this represents the hours field of
         * a <code>Calendar</code>, this would return
         * <code>Calendar.HOUR</code>. If there is no corresponding
         * <code>Calendar</code> constant, this will return -1.
         *
         * @return Calendar constant for this field
         * @see java.util.Calendar
         */
        public int getCalendarField() {
            return 0;
        }

        /** 
         * Resolves instances being deserialized to the predefined constants.
         *
         * @throws InvalidObjectException if the constant could not be
         *         resolved.
         * @return resolved DateFormat.Field constant
         */
        protected Object readResolve() throws InvalidObjectException {
            return null;
        }

    }
}
