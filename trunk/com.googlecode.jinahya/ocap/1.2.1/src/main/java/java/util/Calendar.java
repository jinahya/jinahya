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

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.text.DateFormat;

/** 
 * <code>Calendar</code> is an abstract base class for converting between
 * a <code>Date</code> object and a set of integer fields such as
 * <code>YEAR</code>, <code>MONTH</code>, <code>DAY</code>, <code>HOUR</code>,
 * and so on. (A <code>Date</code> object represents a specific instant in
 * time with millisecond precision. See
 * {@link Date}
 * for information about the <code>Date</code> class.)
 *
 * <p>
 * Subclasses of <code>Calendar</code> interpret a <code>Date</code>
 * according to the rules of a specific calendar system. The platform
 * provides one concrete subclass of <code>Calendar</code>:
 * <code>GregorianCalendar</code>. Future subclasses could represent
 * the various types of lunar calendars in use in many parts of the world.
 *
 * <p>
 * Like other locale-sensitive classes, <code>Calendar</code> provides a
 * class method, <code>getInstance</code>, for getting a generally useful
 * object of this type. <code>Calendar</code>'s <code>getInstance</code> method
 * returns a <code>Calendar</code> object whose
 * time fields have been initialized with the current date and time:
 * <blockquote>
 * <pre>
 * Calendar rightNow = Calendar.getInstance();
 * </pre>
 * </blockquote>
 *
 * <p>A <code>Calendar</code> object can produce all the time field values
 * needed to implement the date-time formatting for a particular language and
 * calendar style (for example, Japanese-Gregorian, Japanese-Traditional).
 * <code>Calendar</code> defines the range of values returned by certain fields,
 * as well as their meaning.  For example, the first month of the year has value
 * <code>MONTH</code> == <code>JANUARY</code> for all calendars.  Other values
 * are defined by the concrete subclass, such as <code>ERA</code> and
 * <code>YEAR</code>.  See individual field documentation and subclass
 * documentation for details.
 *
 * <p>When a <code>Calendar</code> is <em>lenient</em>, it accepts a wider range
 * of field values than it produces.  For example, a lenient
 * <code>GregorianCalendar</code> interprets <code>MONTH</code> ==
 * <code>JANUARY</code>, <code>DAY_OF_MONTH</code> == 32 as February 1.  A
 * non-lenient <code>GregorianCalendar</code> throws an exception when given
 * out-of-range field settings.  When calendars recompute field values for
 * return by <code>get()</code>, they normalize them.  For example, a
 * <code>GregorianCalendar</code> always produces <code>DAY_OF_MONTH</code>
 * values between 1 and the length of the month.
 *
 * <p><code>Calendar</code> defines a locale-specific seven day week using two
 * parameters: the first day of the week and the minimal days in first week
 * (from 1 to 7).  These numbers are taken from the locale resource data when a
 * <code>Calendar</code> is constructed.  They may also be specified explicitly
 * through the API.
 *
 * <p>When setting or getting the <code>WEEK_OF_MONTH</code> or
 * <code>WEEK_OF_YEAR</code> fields, <code>Calendar</code> must determine the
 * first week of the month or year as a reference point.  The first week of a
 * month or year is defined as the earliest seven day period beginning on
 * <code>getFirstDayOfWeek()</code> and containing at least
 * <code>getMinimalDaysInFirstWeek()</code> days of that month or year.  Weeks
 * numbered ..., -1, 0 precede the first week; weeks numbered 2, 3,... follow
 * it.  Note that the normalized numbering returned by <code>get()</code> may be
 * different.  For example, a specific <code>Calendar</code> subclass may
 * designate the week before week 1 of a year as week <em>n</em> of the previous
 * year.
 *
 * <p> When computing a <code>Date</code> from time fields, two special
 * circumstances may arise: there may be insufficient information to compute the
 * <code>Date</code> (such as only year and month but no day in the month), or
 * there may be inconsistent information (such as "Tuesday, July 15, 1996" --
 * July 15, 1996 is actually a Monday).
 *
 * <p>
 * <strong>Insufficient information.</strong> The calendar will use default
 * information to specify the missing fields. This may vary by calendar; for
 * the Gregorian calendar, the default for a field is the same as that of the
 * start of the epoch: i.e., YEAR = 1970, MONTH = JANUARY, DATE = 1, etc.
 *
 * <p>
 * <strong>Inconsistent information.</strong> If fields conflict, the calendar
 * will give preference to fields set more recently. For example, when
 * determining the day, the calendar will look for one of the following
 * combinations of fields.  The most recent combination, as determined by the
 * most recently set single field, will be used.
 *
 * <blockquote>
 * <pre>
 * MONTH + DAY_OF_MONTH
 * MONTH + WEEK_OF_MONTH + DAY_OF_WEEK
 * MONTH + DAY_OF_WEEK_IN_MONTH + DAY_OF_WEEK
 * DAY_OF_YEAR
 * DAY_OF_WEEK + WEEK_OF_YEAR
 * </pre>
 * </blockquote>
 *
 * For the time of day:
 *
 * <blockquote>
 * <pre>
 * HOUR_OF_DAY
 * AM_PM + HOUR
 * </pre>
 * </blockquote>
 *
 * <p>
 * <strong>Note:</strong> for some non-Gregorian calendars, different
 * fields may be necessary for complete disambiguation. For example, a full
 * specification of the historical Arabic astronomical calendar requires year,
 * month, day-of-month <em>and</em> day-of-week in some cases.
 *
 * <p>
 * <strong>Note:</strong> There are certain possible ambiguities in
 * interpretation of certain singular times, which are resolved in the
 * following ways:
 * <ol>
 *     <li> 23:59 is the last minute of the day and 00:00 is the first minute of the
 *          next day. Thus, 23:59 on Dec 31, 1999 &lt; 00:00 on Jan 1, 2000 &lt; 00:01 on
 *          Jan 1, 2000.
 *
 *     <li> Although historically not precise, midnight also belongs to "am",
 *          and noon belongs to "pm", so on the same day,
 *          12:00 am (midnight) &lt; 12:01 am, and 12:00 pm (noon) &lt; 12:01 pm
 * </ol>
 *
 * <p>
 * The date or time format strings are not part of the definition of a
 * calendar, as those must be modifiable or overridable by the user at
 * runtime. Use {@link DateFormat}
 * to format dates.
 *
 * <p><strong>Field manipulation methods</strong></p>
 * 
 * <p><code>Calendar</code> fields can be changed using three methods:
 * <code>set()</code>, <code>add()</code>, and <code>roll()</code>.</p>
 * 
 * <p><strong><code>set(f, value)</code></strong> changes field
 * <code>f</code> to <code>value</code>.  In addition, it sets an
 * internal member variable to indicate that field <code>f</code> has
 * been changed. Although field <code>f</code> is changed immediately,
 * the calendar's milliseconds is not recomputed until the next call to
 * <code>get()</code>, <code>getTime()</code>, or
 * <code>getTimeInMillis()</code> is made. Thus, multiple calls to
 * <code>set()</code> do not trigger multiple, unnecessary
 * computations. As a result of changing a field using
 * <code>set()</code>, other fields may also change, depending on the
 * field, the field value, and the calendar system. In addition,
 * <code>get(f)</code> will not necessarily return <code>value</code>
 * after the fields have been recomputed. The specifics are determined by
 * the concrete calendar class.</p>
 * 
 * <p><em>Example</em>: Consider a <code>GregorianCalendar</code>
 * originally set to August 31, 1999. Calling <code>set(Calendar.MONTH,
 * Calendar.SEPTEMBER)</code> sets the calendar to September 31,
 * 1999. This is a temporary internal representation that resolves to
 * October 1, 1999 if <code>getTime()</code>is then called. However, a
 * call to <code>set(Calendar.DAY_OF_MONTH, 30)</code> before the call to
 * <code>getTime()</code> sets the calendar to September 30, 1999, since
 * no recomputation occurs after <code>set()</code> itself.</p>
 * 
 * <p><strong><code>add(f, delta)</code></strong> adds <code>delta</code>
 * to field <code>f</code>.  This is equivalent to calling <code>set(f,
 * get(f) + delta)</code> with two adjustments:</p>
 * 
 * <blockquote>
 *   <p><strong>Add rule 1</strong>. The value of field <code>f</code>
 *   after the call minus the value of field <code>f</code> before the
 *   call is <code>delta</code>, modulo any overflow that has occurred in
 *   field <code>f</code>. Overflow occurs when a field value exceeds its
 *   range and, as a result, the next larger field is incremented or
 *   decremented and the field value is adjusted back into its range.</p>
 * 
 *   <p><strong>Add rule 2</strong>. If a smaller field is expected to be
 *   invariant, but &nbsp; it is impossible for it to be equal to its
 *   prior value because of changes in its minimum or maximum after field
 *   <code>f</code> is changed, then its value is adjusted to be as close
 *   as possible to its expected value. A smaller field represents a
 *   smaller unit of time. <code>HOUR</code> is a smaller field than
 *   <code>DAY_OF_MONTH</code>. No adjustment is made to smaller fields
 *   that are not expected to be invariant. The calendar system
 *   determines what fields are expected to be invariant.</p>
 * </blockquote>
 * 
 * <p>In addition, unlike <code>set()</code>, <code>add()</code> forces
 * an immediate recomputation of the calendar's milliseconds and all
 * fields.</p>
 * 
 * <p><em>Example</em>: Consider a <code>GregorianCalendar</code>
 * originally set to August 31, 1999. Calling <code>add(Calendar.MONTH,
 * 13)</code> sets the calendar to September 30, 2000. <strong>Add rule
 * 1</strong> sets the <code>MONTH</code> field to September, since
 * adding 13 months to August gives September of the next year. Since
 * <code>DAY_OF_MONTH</code> cannot be 31 in September in a
 * <code>GregorianCalendar</code>, <strong>add rule 2</strong> sets the
 * <code>DAY_OF_MONTH</code> to 30, the closest possible value. Although
 * it is a smaller field, <code>DAY_OF_WEEK</code> is not adjusted by
 * rule 2, since it is expected to change when the month changes in a
 * <code>GregorianCalendar</code>.</p>
 * 
 * <p><strong><code>roll(f, delta)</code></strong> adds
 * <code>delta</code> to field <code>f</code> without changing larger
 * fields. This is equivalent to calling <code>add(f, delta)</code> with
 * the following adjustment:</p>
 * 
 * <blockquote>
 *   <p><strong>Roll rule</strong>. Larger fields are unchanged after the
 *   call. A larger field represents a larger unit of
 *   time. <code>DAY_OF_MONTH</code> is a larger field than
 *   <code>HOUR</code>.</p>
 * </blockquote>
 * 
 * <p><em>Example</em>: See {@link java.util.GregorianCalendar#roll(int, int)}.
 * 
 * <p><strong>Usage model</strong>. To motivate the behavior of
 * <code>add()</code> and <code>roll()</code>, consider a user interface
 * component with increment and decrement buttons for the month, day, and
 * year, and an underlying <code>GregorianCalendar</code>. If the
 * interface reads January 31, 1999 and the user presses the month
 * increment button, what should it read? If the underlying
 * implementation uses <code>set()</code>, it might read March 3, 1999. A
 * better result would be February 28, 1999. Furthermore, if the user
 * presses the month increment button again, it should read March 31,
 * 1999, not March 28, 1999. By saving the original date and using either
 * <code>add()</code> or <code>roll()</code>, depending on whether larger
 * fields should be affected, the user interface can behave as most users
 * will intuitively expect.</p>
 *
 * @see          Date
 * @see          GregorianCalendar
 * @see          TimeZone
 * @see          java.text.DateFormat
 * @version      1.51 10/17/00
 * @author Mark Davis, David Goldsmith, Chen-Lieh Huang, Alan Liu
 * @since JDK1.1
 */
public abstract class Calendar implements Serializable, Cloneable
{
    /** 
     * Field number for <code>get</code> and <code>set</code> indicating the
     * era, e.g., AD or BC in the Julian calendar. This is a calendar-specific
     * value; see subclass documentation.
     * @see GregorianCalendar#AD
     * @see GregorianCalendar#BC
     */
    public static final int ERA = 0;

    /** 
     * Field number for <code>get</code> and <code>set</code> indicating the
     * year. This is a calendar-specific value; see subclass documentation.
     */
    public static final int YEAR = 1;

    /** 
     * Field number for <code>get</code> and <code>set</code> indicating the
     * month. This is a calendar-specific value. The first month of the year is
     * <code>JANUARY</code> which is 0; the last depends on the number of months in a year.
     * @see #JANUARY
     * @see #FEBRUARY
     * @see #MARCH
     * @see #APRIL
     * @see #MAY
     * @see #JUNE
     * @see #JULY
     * @see #AUGUST
     * @see #SEPTEMBER
     * @see #OCTOBER
     * @see #NOVEMBER
     * @see #DECEMBER
     * @see #UNDECIMBER
     */
    public static final int MONTH = 2;

    /** 
     * Field number for <code>get</code> and <code>set</code> indicating the
     * week number within the current year.  The first week of the year, as
     * defined by <code>getFirstDayOfWeek()</code> and
     * <code>getMinimalDaysInFirstWeek()</code>, has value 1.  Subclasses define
     * the value of <code>WEEK_OF_YEAR</code> for days before the first week of
     * the year.
     * @see #getFirstDayOfWeek
     * @see #getMinimalDaysInFirstWeek
     */
    public static final int WEEK_OF_YEAR = 3;

    /** 
     * Field number for <code>get</code> and <code>set</code> indicating the
     * week number within the current month.  The first week of the month, as
     * defined by <code>getFirstDayOfWeek()</code> and
     * <code>getMinimalDaysInFirstWeek()</code>, has value 1.  Subclasses define
     * the value of <code>WEEK_OF_MONTH</code> for days before the first week of
     * the month.
     * @see #getFirstDayOfWeek
     * @see #getMinimalDaysInFirstWeek
     */
    public static final int WEEK_OF_MONTH = 4;

    /** 
     * Field number for <code>get</code> and <code>set</code> indicating the
     * day of the month. This is a synonym for <code>DAY_OF_MONTH</code>.
     * The first day of the month has value 1.
     * @see #DAY_OF_MONTH
     */
    public static final int DATE = 5;

    /** 
     * Field number for <code>get</code> and <code>set</code> indicating the
     * day of the month. This is a synonym for <code>DATE</code>.
     * The first day of the month has value 1.
     * @see #DATE
     */
    public static final int DAY_OF_MONTH = 5;

    /** 
     * Field number for <code>get</code> and <code>set</code> indicating the day
     * number within the current year.  The first day of the year has value 1.
     */
    public static final int DAY_OF_YEAR = 6;

    /** 
     * Field number for <code>get</code> and <code>set</code> indicating the day
     * of the week.  This field takes values <code>SUNDAY</code>,
     * <code>MONDAY</code>, <code>TUESDAY</code>, <code>WEDNESDAY</code>,
     * <code>THURSDAY</code>, <code>FRIDAY</code>, and <code>SATURDAY</code>.
     * @see #SUNDAY
     * @see #MONDAY
     * @see #TUESDAY
     * @see #WEDNESDAY
     * @see #THURSDAY
     * @see #FRIDAY
     * @see #SATURDAY
     */
    public static final int DAY_OF_WEEK = 7;

    /** 
     * Field number for <code>get</code> and <code>set</code> indicating the
     * ordinal number of the day of the week within the current month. Together
     * with the <code>DAY_OF_WEEK</code> field, this uniquely specifies a day
     * within a month.  Unlike <code>WEEK_OF_MONTH</code> and
     * <code>WEEK_OF_YEAR</code>, this field's value does <em>not</em> depend on
     * <code>getFirstDayOfWeek()</code> or
     * <code>getMinimalDaysInFirstWeek()</code>.  <code>DAY_OF_MONTH 1</code>
     * through <code>7</code> always correspond to <code>DAY_OF_WEEK_IN_MONTH
     * 1</code>; <code>8</code> through <code>14</code> correspond to
     * <code>DAY_OF_WEEK_IN_MONTH 2</code>, and so on.
     * <code>DAY_OF_WEEK_IN_MONTH 0</code> indicates the week before
     * <code>DAY_OF_WEEK_IN_MONTH 1</code>.  Negative values count back from the
     * end of the month, so the last Sunday of a month is specified as
     * <code>DAY_OF_WEEK = SUNDAY, DAY_OF_WEEK_IN_MONTH = -1</code>.  Because
     * negative values count backward they will usually be aligned differently
     * within the month than positive values.  For example, if a month has 31
     * days, <code>DAY_OF_WEEK_IN_MONTH -1</code> will overlap
     * <code>DAY_OF_WEEK_IN_MONTH 5</code> and the end of <code>4</code>.
     * @see #DAY_OF_WEEK
     * @see #WEEK_OF_MONTH
     */
    public static final int DAY_OF_WEEK_IN_MONTH = 8;

    /** 
     * Field number for <code>get</code> and <code>set</code> indicating
     * whether the <code>HOUR</code> is before or after noon.
     * E.g., at 10:04:15.250 PM the <code>AM_PM</code> is <code>PM</code>.
     * @see #AM
     * @see #PM
     * @see #HOUR
     */
    public static final int AM_PM = 9;

    /** 
     * Field number for <code>get</code> and <code>set</code> indicating the
     * hour of the morning or afternoon. <code>HOUR</code> is used for the 12-hour
     * clock.
     * E.g., at 10:04:15.250 PM the <code>HOUR</code> is 10.
     * @see #AM_PM
     * @see #HOUR_OF_DAY
     */
    public static final int HOUR = 10;

    /** 
     * Field number for <code>get</code> and <code>set</code> indicating the
     * hour of the day. <code>HOUR_OF_DAY</code> is used for the 24-hour clock.
     * E.g., at 10:04:15.250 PM the <code>HOUR_OF_DAY</code> is 22.
     * @see #HOUR
     */
    public static final int HOUR_OF_DAY = 11;

    /** 
     * Field number for <code>get</code> and <code>set</code> indicating the
     * minute within the hour.
     * E.g., at 10:04:15.250 PM the <code>MINUTE</code> is 4.
     */
    public static final int MINUTE = 12;

    /** 
     * Field number for <code>get</code> and <code>set</code> indicating the
     * second within the minute.
     * E.g., at 10:04:15.250 PM the <code>SECOND</code> is 15.
     */
    public static final int SECOND = 13;

    /** 
     * Field number for <code>get</code> and <code>set</code> indicating the
     * millisecond within the second.
     * E.g., at 10:04:15.250 PM the <code>MILLISECOND</code> is 250.
     */
    public static final int MILLISECOND = 14;

    /** 
     * Field number for <code>get</code> and <code>set</code>
     * indicating the raw offset from GMT in milliseconds.
     * <p>
     * This field reflects the correct GMT offset value of the time
     * zone of this <code>Calendar</code> if the
     * <code>TimeZone</code> implementation subclass supports
     * historical GMT offset changes.
     */
    public static final int ZONE_OFFSET = 15;

    /** 
     * Field number for <code>get</code> and <code>set</code> indicating the
     * daylight savings offset in milliseconds.
     * <p>
     * This field reflects the correct daylight saving offset value of
     * the time zone of this <code>Calendar</code> if the
     * <code>TimeZone</code> implementation subclass supports
     * historical Daylight Saving Time schedule changes.
     */
    public static final int DST_OFFSET = 16;

    /** 
     * The number of distinct fields recognized by <code>get</code> and <code>set</code>.
     * Field numbers range from <code>0..FIELD_COUNT-1</code>.
     */
    public static final int FIELD_COUNT = 17;

    /** 
     * Value of the <code>DAY_OF_WEEK</code> field indicating
     * Sunday.
     */
    public static final int SUNDAY = 1;

    /** 
     * Value of the <code>DAY_OF_WEEK</code> field indicating
     * Monday.
     */
    public static final int MONDAY = 2;

    /** 
     * Value of the <code>DAY_OF_WEEK</code> field indicating
     * Tuesday.
     */
    public static final int TUESDAY = 3;

    /** 
     * Value of the <code>DAY_OF_WEEK</code> field indicating
     * Wednesday.
     */
    public static final int WEDNESDAY = 4;

    /** 
     * Value of the <code>DAY_OF_WEEK</code> field indicating
     * Thursday.
     */
    public static final int THURSDAY = 5;

    /** 
     * Value of the <code>DAY_OF_WEEK</code> field indicating
     * Friday.
     */
    public static final int FRIDAY = 6;

    /** 
     * Value of the <code>DAY_OF_WEEK</code> field indicating
     * Saturday.
     */
    public static final int SATURDAY = 7;

    /** 
     * Value of the <code>MONTH</code> field indicating the
     * first month of the year.
     */
    public static final int JANUARY = 0;

    /** 
     * Value of the <code>MONTH</code> field indicating the
     * second month of the year.
     */
    public static final int FEBRUARY = 1;

    /** 
     * Value of the <code>MONTH</code> field indicating the
     * third month of the year.
     */
    public static final int MARCH = 2;

    /** 
     * Value of the <code>MONTH</code> field indicating the
     * fourth month of the year.
     */
    public static final int APRIL = 3;

    /** 
     * Value of the <code>MONTH</code> field indicating the
     * fifth month of the year.
     */
    public static final int MAY = 4;

    /** 
     * Value of the <code>MONTH</code> field indicating the
     * sixth month of the year.
     */
    public static final int JUNE = 5;

    /** 
     * Value of the <code>MONTH</code> field indicating the
     * seventh month of the year.
     */
    public static final int JULY = 6;

    /** 
     * Value of the <code>MONTH</code> field indicating the
     * eighth month of the year.
     */
    public static final int AUGUST = 7;

    /** 
     * Value of the <code>MONTH</code> field indicating the
     * ninth month of the year.
     */
    public static final int SEPTEMBER = 8;

    /** 
     * Value of the <code>MONTH</code> field indicating the
     * tenth month of the year.
     */
    public static final int OCTOBER = 9;

    /** 
     * Value of the <code>MONTH</code> field indicating the
     * eleventh month of the year.
     */
    public static final int NOVEMBER = 10;

    /** 
     * Value of the <code>MONTH</code> field indicating the
     * twelfth month of the year.
     */
    public static final int DECEMBER = 11;

    /** 
     * Value of the <code>MONTH</code> field indicating the
     * thirteenth month of the year. Although <code>GregorianCalendar</code>
     * does not use this value, lunar calendars do.
     */
    public static final int UNDECIMBER = 12;

    /** 
     * Value of the <code>AM_PM</code> field indicating the
     * period of the day from midnight to just before noon.
     */
    public static final int AM = 0;

    /** 
     * Value of the <code>AM_PM</code> field indicating the
     * period of the day from noon to just before midnight.
     */
    public static final int PM = 1;

    /** 
     * The field values for the currently set time for this calendar.
     * This is an array of <code>FIELD_COUNT</code> integers, with index values
     * <code>ERA</code> through <code>DST_OFFSET</code>.
     * @serial
     */
    protected int[] fields;

    /** 
     * The flags which tell if a specified time field for the calendar is set.
     * A new object has no fields set.  After the first call to a method
     * which generates the fields, they all remain set after that.
     * This is an array of <code>FIELD_COUNT</code> booleans, with index values
     * <code>ERA</code> through <code>DST_OFFSET</code>.
     * @serial
     */
    protected boolean[] isSet;

    /** 
     * The currently set time for this calendar, expressed in milliseconds after
     * January 1, 1970, 0:00:00 GMT.
     * @see #isTimeSet
     * @serial
     */
    protected long time;

    /** 
     * True if then the value of <code>time</code> is valid.
     * The time is made invalid by a change to an item of <code>field[]</code>.
     * @see #time
     * @serial
     */
    protected boolean isTimeSet;

    /** 
     * True if <code>fields[]</code> are in sync with the currently set time.
     * If false, then the next attempt to get the value of a field will
     * force a recomputation of all fields from the current value of
     * <code>time</code>.
     * @serial
     */
    protected boolean areFieldsSet;

    /** 
     * True if this calendar allows out-of-range field values during computation
     * of <code>time</code> from <code>fields[]</code>.
     * @see #setLenient
     * @serial
     */
    private boolean lenient;

    /** 
     * The <code>TimeZone</code> used by this calendar. </code>Calendar</code>
     * uses the time zone data to translate between locale and GMT time.
     * @serial
     */
    private TimeZone zone;

    /** 
     * The first day of the week, with possible values <code>SUNDAY</code>,
     * <code>MONDAY</code>, etc.  This is a locale-dependent value.
     * @serial
     */
    private int firstDayOfWeek;

    /** 
     * The number of days required for the first week in a month or year,
     * with possible values from 1 to 7.  This is a locale-dependent value.
     * @serial
     */
    private int minimalDaysInFirstWeek;

    /** 
     * The next available value for <code>stamp[]</code>, an internal array.
     * This actually should not be written out to the stream, and will probably
     * be removed from the stream in the near future.  In the meantime,
     * a value of <code>MINIMUM_USER_STAMP</code> should be used.
     * @serial
     */
    private int nextStamp;

    /** 
     * The version of the serialized data on the stream.  Possible values:
     * <dl>
     * <dt><b>0</b> or not present on stream</dt>
     * <dd>
     * JDK 1.1.5 or earlier.
     * </dd>
     * <dt><b>1</b></dt>
     * <dd>
     * JDK 1.1.6 or later.  Writes a correct 'time' value
     * as well as compatible values for other fields.  This is a
     * transitional format.
     * </dd>
     * </dl>
     * When streaming out this class, the most recent format
     * and the highest allowable <code>serialVersionOnStream</code>
     * is written.
     * @serial
     * @since JDK1.1.6
     */
    private int serialVersionOnStream;

    static final long       serialVersionUID = -1807547505821590642L;

    /** 
     * Constructs a Calendar with the default time zone
     * and locale.
     * @see     TimeZone#getDefault
     */
    protected Calendar() { }

    /** 
     * Constructs a calendar with the specified time zone and locale.
     * @param zone the time zone to use
     * @param aLocale the locale for the week data
     */
    protected Calendar(TimeZone zone, Locale aLocale) { }

    /** 
     * Gets a calendar using the default time zone and locale. The
     * <code>Calendar</code> returned is based on the current time
     * in the default time zone with the default locale.
     *
     * @return a Calendar.
     */
    public static Calendar getInstance() {
        return null;
    }

    /** 
     * Gets a calendar using the specified time zone and default locale.
     * The <code>Calendar</code> returned is based on the current time
     * in the given time zone with the default locale. 
     *
     * @param zone the time zone to use
     * @return a Calendar.
     */
    public static Calendar getInstance(TimeZone zone) {
        return null;
    }

    /** 
     * Gets a calendar using the default time zone and specified locale.
     * The <code>Calendar</code> returned is based on the current time
     * in the default time zone with the given locale.
     *
     * @param aLocale the locale for the week data
     * @return a Calendar.
     */
    public static Calendar getInstance(Locale aLocale) {
        return null;
    }

    /** 
     * Gets a calendar with the specified time zone and locale.
     * The <code>Calendar</code> returned is based on the current time
     * in the given time zone with the given locale.
     *
     * @param zone the time zone to use
     * @param aLocale the locale for the week data
     * @return a Calendar.
     */
    public static Calendar getInstance(TimeZone zone, Locale aLocale) {
        return null;
    }

    /** 
     * Gets the list of locales for which Calendars are installed.
     * @return the list of locales for which Calendars are installed.
     */
    public static synchronized Locale[] getAvailableLocales() {
        return null;
    }

    /** 
     * Converts the current field values in <code>fields[]</code>
     * to the millisecond time value
     * <code>time</code>.
     */
    protected abstract void computeTime();

    /** 
     * Converts 
     * the current millisecond time value
     * <code>time</code>
     * to field values in <code>fields[]</code>.
     * This allows you to sync up the time field values with
     * a new time that is set for the calendar.  The time is <em>not</em>
     * recomputed first; to recompute the time, then the fields, call the
     * <code>complete</code> method.
     * @see #complete
     */
    protected abstract void computeFields();

    /** 
     * Gets this Calendar's current time.
     * @return the current time.
     * @see #setTime
     * @see #getTimeInMillis
     */
    public final Date getTime() {
        return null;
    }

    /** 
     * Sets this Calendar's current time with the given Date.
     * <p>
     * Note: Calling <code>setTime()</code> with
     * <code>Date(Long.MAX_VALUE)</code> or <code>Date(Long.MIN_VALUE)</code>
     * may yield incorrect field values from <code>get()</code>.
     * @param date the given Date.
     * @see #getTime
     * @see #setTimeInMillis
     */
    public final void setTime(Date date) { }

    /** 
     * Gets this Calendar's current time as a long.
     * @return the current time as UTC milliseconds from the epoch.
     * @see #getTime
     * @see #setTimeInMillis
     */
    public long getTimeInMillis() {
        return -1;
    }

    /** 
     * Sets this Calendar's current time from the given long value.
     * @param millis the new time in UTC milliseconds from the epoch.
     * @see #setTime
     * @see #getTimeInMillis
     */
    public void setTimeInMillis(long millis) { }

    /** 
     * Gets the value for a given time field.
     * @param field the given time field.
     * @return the value for the given time field.
     * @throws ArrayIndexOutOfBoundsException if specified field is out of range
     *             (<tt>field &lt; 0 || field &gt;= FIELD_COUNT</tt>).
     */
    public int get(int field) {
        return 0;
    }

    /** 
     * Gets the value for a given time field. This is an internal
     * fast time field value getter for the subclasses.
     * @param field the given time field.
     * @return the value for the given time field.
     */
    protected final int internalGet(int field) {
        return 0;
    }

    /** 
     * Sets the time field with the given value.
     * @param field the given time field.
     * @param value the value to be set for the given time field.
     * @throws ArrayIndexOutOfBoundsException if specified field is out of range
     *             (<tt>field &lt; 0 || field &gt;= FIELD_COUNT</tt>).
     */
    public void set(int field, int value) { }

    /** 
     * Sets the values for the fields year, month, and date.
     * Previous values of other fields are retained.  If this is not desired,
     * call <code>clear</code> first.
     * @param year the value used to set the YEAR time field.
     * @param month the value used to set the MONTH time field.
     * Month value is 0-based. e.g., 0 for January.
     * @param date the value used to set the DATE time field.
     */
    public final void set(int year, int month, int date) { }

    /** 
     * Sets the values for the fields year, month, date, hour, and minute.
     * Previous values of other fields are retained.  If this is not desired,
     * call <code>clear</code> first.
     * @param year the value used to set the YEAR time field.
     * @param month the value used to set the MONTH time field.
     * Month value is 0-based. e.g., 0 for January.
     * @param date the value used to set the DATE time field.
     * @param hour the value used to set the HOUR_OF_DAY time field.
     * @param minute the value used to set the MINUTE time field.
     */
    public final void set(int year, int month, int date, int hour, int minute)
    { }

    /** 
     * Sets the values for the fields year, month, date, hour, minute, and second.
     * Previous values of other fields are retained.  If this is not desired,
     * call <code>clear</code> first.
     * @param year the value used to set the YEAR time field.
     * @param month the value used to set the MONTH time field.
     * Month value is 0-based. e.g., 0 for January.
     * @param date the value used to set the DATE time field.
     * @param hour the value used to set the HOUR_OF_DAY time field.
     * @param minute the value used to set the MINUTE time field.
     * @param second the value used to set the SECOND time field.
     */
    public final void set(int year, int month, int date, int hour, int minute,
        int second)
    { }

    /** 
     * Clears the values of all the time fields.
     */
    public final void clear() { }

    /** 
     * Clears the value in the given time field.
     * @param field the time field to be cleared.
     */
    public final void clear(int field) { }

    /** 
     * Determines if the given time field has a value set.
     * @return true if the given time field has a value set; false otherwise.
     */
    public final boolean isSet(int field) {
        return false;
    }

    /** 
     * Fills in any unset fields in the time field list.
     */
    protected void complete() { }

    /** 
     * Compares this calendar to the specified object.
     * The result is <code>true</code> if and only if the argument is
     * not <code>null</code> and is a <code>Calendar</code> object that
     * represents the same calendar as this object.
     * @param obj the object to compare with.
     * @return <code>true</code> if the objects are the same;
     * <code>false</code> otherwise.
     */
    public boolean equals(Object obj) {
        return false;
    }

    /** 
     * Returns a hash code for this calendar.
     * @return a hash code value for this object. 
     * @since 1.2
     */
    public int hashCode() {
        return 0;
    }

    /** 
     * Compares the time field records.
     * Equivalent to comparing result of conversion to UTC.
     * @param when the Calendar to be compared with this Calendar.
     * @return true if the current time of this Calendar is before
     * the time of Calendar when; false otherwise.
     */
    public boolean before(Object when) {
        return false;
    }

    /** 
     * Compares the time field records.
     * Equivalent to comparing result of conversion to UTC.
     * @param when the Calendar to be compared with this Calendar.
     * @return true if the current time of this Calendar is after
     * the time of Calendar when; false otherwise.
     */
    public boolean after(Object when) {
        return false;
    }

    /** 
     * Date Arithmetic function.
     * Adds the specified (signed) amount of time to the given time field,
     * based on the calendar's rules. For example, to subtract 5 days from
     * the current time of the calendar, you can achieve it by calling:
     * <p>add(Calendar.DATE, -5).
     * @param field the time field.
     * @param amount the amount of date or time to be added to the field.
     */
    public abstract void add(int field, int amount);

    /** 
     * Time Field Rolling function.
     * Adds or subtracts (up/down) a single unit of time on the given time 
     * field without changing larger fields. For example, to roll the current 
     * date up by one day, you can achieve it by calling:
     * <p>roll(Calendar.DATE, true).
     * When rolling on the year or Calendar.YEAR field, it will roll the year
     * value in the range between 1 and the value returned by calling
     * getMaximum(Calendar.YEAR).
     * When rolling on the month or Calendar.MONTH field, other fields like
     * date might conflict and, need to be changed. For instance,
     * rolling the month on the date 01/31/96 will result in 02/29/96.
     * When rolling on the hour-in-day or Calendar.HOUR_OF_DAY field, it will
     * roll the hour value in the range between 0 and 23, which is zero-based.
     * @param field the time field.
     * @param up indicates if the value of the specified time field is to be
     * rolled up or rolled down. Use true if rolling up, false otherwise.
     * @see Calendar#add
     * @see Calendar#set
     */
    public abstract void roll(int field, boolean up);

    /** 
     * Time Field Rolling function.
     * Add to field a signed amount without changing larger fields.
     * A negative roll amount means to roll down.
     * [NOTE:  This default implementation on Calendar just repeatedly calls the
     * version of roll() that takes a boolean and rolls by one unit.  This may not
     * always do the right thing.  For example, if the DAY_OF_MONTH field is 31,
     * rolling through February will leave it set to 28.  The GregorianCalendar
     * version of this function takes care of this problem.  Other subclasses
     * should also provide overrides of this function that do the right thing.
     * @param field the time field.
     * @param amount the signed amount to add to <code>field</code>.
     * @since 1.2
     * @see Calendar#add
     * @see Calendar#set
     */
    public void roll(int field, int amount) { }

    /** 
     * Sets the time zone with the given time zone value.
     * @param value the given time zone.
     */
    public void setTimeZone(TimeZone value) { }

    /** 
     * Gets the time zone.
     * @return the time zone object associated with this calendar.
     */
    public TimeZone getTimeZone() {
        return null;
    }

    /** 
     * Specify whether or not date/time interpretation is to be lenient.  With
     * lenient interpretation, a date such as "February 942, 1996" will be
     * treated as being equivalent to the 941st day after February 1, 1996.
     * With strict interpretation, such dates will cause an exception to be
     * thrown.
     *
     * @see java.text.DateFormat#setLenient
     */
    public void setLenient(boolean lenient) { }

    /** 
     * Tell whether date/time interpretation is to be lenient.
     */
    public boolean isLenient() {
        return false;
    }

    /** 
     * Sets what the first day of the week is; e.g., Sunday in US,
     * Monday in France.
     * @param value the given first day of the week.
     */
    public void setFirstDayOfWeek(int value) { }

    /** 
     * Gets what the first day of the week is; e.g., Sunday in US,
     * Monday in France.
     * @return the first day of the week.
     */
    public int getFirstDayOfWeek() {
        return 0;
    }

    /** 
     * Sets what the minimal days required in the first week of the year are;
     * For example, if the first week is defined as one that contains the first
     * day of the first month of a year, call the method with value 1. If it
     * must be a full week, use value 7.
     * @param value the given minimal days required in the first week
     * of the year.
     */
    public void setMinimalDaysInFirstWeek(int value) { }

    /** 
     * Gets what the minimal days required in the first week of the year are;
     * e.g., if the first week is defined as one that contains the first day
     * of the first month of a year, getMinimalDaysInFirstWeek returns 1. If
     * the minimal days required must be a full week, getMinimalDaysInFirstWeek
     * returns 7.
     * @return the minimal days required in the first week of the year.
     */
    public int getMinimalDaysInFirstWeek() {
        return 0;
    }

    /** 
     * Gets the minimum value for the given time field.
     * e.g., for Gregorian DAY_OF_MONTH, 1.
     * @param field the given time field.
     * @return the minimum value for the given time field.
     */
    public abstract int getMinimum(int field);

    /** 
     * Gets the maximum value for the given time field.
     * e.g. for Gregorian DAY_OF_MONTH, 31.
     * @param field the given time field.
     * @return the maximum value for the given time field.
     */
    public abstract int getMaximum(int field);

    /** 
     * Gets the highest minimum value for the given field if varies.
     * Otherwise same as getMinimum(). For Gregorian, no difference.
     * @param field the given time field.
     * @return the highest minimum value for the given time field.
     */
    public abstract int getGreatestMinimum(int field);

    /** 
     * Gets the lowest maximum value for the given field if varies.
     * Otherwise same as getMaximum(). e.g., for Gregorian DAY_OF_MONTH, 28.
     * @param field the given time field.
     * @return the lowest maximum value for the given time field.
     */
    public abstract int getLeastMaximum(int field);

    /** 
     * Return the minimum value that this field could have, given the current date.
     * For the Gregorian calendar, this is the same as getMinimum() and getGreatestMinimum().
     *
     * The version of this function on Calendar uses an iterative algorithm to determine the
     * actual minimum value for the field.  There is almost always a more efficient way to
     * accomplish this (in most cases, you can simply return getMinimum()).  GregorianCalendar
     * overrides this function with a more efficient implementation.
     *
     * @param field the field to determine the minimum of
     * @return the minimum of the given field for the current date of this Calendar
     * @since 1.2
     */
    public int getActualMinimum(int field) {
        return 0;
    }

    /** 
     * Return the maximum value that this field could have, given the current date.
     * For example, with the date "Feb 3, 1997" and the DAY_OF_MONTH field, the actual
     * maximum would be 28; for "Feb 3, 1996" it s 29.  Similarly for a Hebrew calendar,
     * for some years the actual maximum for MONTH is 12, and for others 13.
     *
     * The version of this function on Calendar uses an iterative algorithm to determine the
     * actual maximum value for the field.  There is almost always a more efficient way to
     * accomplish this (in most cases, you can simply return getMaximum()).  GregorianCalendar
     * overrides this function with a more efficient implementation.
     *
     * @param field the field to determine the maximum of
     * @return the maximum of the given field for the current date of this Calendar
     * @since 1.2
     */
    public int getActualMaximum(int field) {
        return 0;
    }

    /** 
     * Overrides Cloneable
     */
    public Object clone() {
        return null;
    }

    /** 
     * Return a string representation of this calendar. This method 
     * is intended to be used only for debugging purposes, and the 
     * format of the returned string may vary between implementations. 
     * The returned string may be empty but may not be <code>null</code>.
     * 
     * @return  a string representation of this calendar.
     */
    public String toString() {
        return null;
    }

    /** 
     * Reconstitute this object from a stream (i.e., deserialize it).
     */
    private void readObject(ObjectInputStream stream)
        throws IOException, ClassNotFoundException
    { }

    /** 
     * Save the state of this object to a stream (i.e., serialize it).
     *
     * Ideally, <code>Calendar</code> would only write out its state data and
     * the current time, and not write any field data out, such as
     * <code>fields[]</code>, <code>isTimeSet</code>, <code>areFieldsSet</code>,
     * and <code>isSet[]</code>.  <code>nextStamp</code> also should not be part
     * of the persistent state. Unfortunately, this didn't happen before JDK 1.1
     * shipped. To be compatible with JDK 1.1, we will always have to write out
     * the field values and state flags.  However, <code>nextStamp</code> can be
     * removed from the serialization stream; this will probably happen in the
     * near future.
     */
    private void writeObject(ObjectOutputStream stream) throws IOException { }
}
