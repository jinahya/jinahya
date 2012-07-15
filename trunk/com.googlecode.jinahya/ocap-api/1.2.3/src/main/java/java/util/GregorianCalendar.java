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

/** 
 * <code>GregorianCalendar</code> is a concrete subclass of
 * {@link Calendar}
 * and provides the standard calendar used by most of the world.
 *
 * <p>
 * The standard (Gregorian) calendar has 2 eras, BC and AD.
 *
 * <p>
 * This implementation handles a single discontinuity, which corresponds by
 * default to the date the Gregorian calendar was instituted (October 15, 1582
 * in some countries, later in others).  The cutover date may be changed by the
 * caller by calling <code>setGregorianChange()</code>.
 *
 * <p>
 * Historically, in those countries which adopted the Gregorian calendar first,
 * October 4, 1582 was thus followed by October 15, 1582. This calendar models
 * this correctly.  Before the Gregorian cutover, <code>GregorianCalendar</code>
 * implements the Julian calendar.  The only difference between the Gregorian
 * and the Julian calendar is the leap year rule. The Julian calendar specifies
 * leap years every four years, whereas the Gregorian calendar omits century
 * years which are not divisible by 400.
 *
 * <p>
 * <code>GregorianCalendar</code> implements <em>proleptic</em> Gregorian and
 * Julian calendars. That is, dates are computed by extrapolating the current
 * rules indefinitely far backward and forward in time. As a result,
 * <code>GregorianCalendar</code> may be used for all years to generate
 * meaningful and consistent results. However, dates obtained using
 * <code>GregorianCalendar</code> are historically accurate only from March 1, 4
 * AD onward, when modern Julian calendar rules were adopted.  Before this date,
 * leap year rules were applied irregularly, and before 45 BC the Julian
 * calendar did not even exist.
 *
 * <p>
 * Prior to the institution of the Gregorian calendar, New Year's Day was
 * March 25. To avoid confusion, this calendar always uses January 1. A manual
 * adjustment may be made if desired for dates that are prior to the Gregorian
 * changeover and which fall between January 1 and March 24.
 *
 * <p>Values calculated for the <code>WEEK_OF_YEAR</code> field range from 1 to
 * 53.  Week 1 for a year is the earliest seven day period starting on
 * <code>getFirstDayOfWeek()</code> that contains at least
 * <code>getMinimalDaysInFirstWeek()</code> days from that year.  It thus
 * depends on the values of <code>getMinimalDaysInFirstWeek()</code>,
 * <code>getFirstDayOfWeek()</code>, and the day of the week of January 1.
 * Weeks between week 1 of one year and week 1 of the following year are
 * numbered sequentially from 2 to 52 or 53 (as needed).
 *
 * <p>For example, January 1, 1998 was a Thursday.  If
 * <code>getFirstDayOfWeek()</code> is <code>MONDAY</code> and
 * <code>getMinimalDaysInFirstWeek()</code> is 4 (these are the values
 * reflecting ISO 8601 and many national standards), then week 1 of 1998 starts
 * on December 29, 1997, and ends on January 4, 1998.  If, however,
 * <code>getFirstDayOfWeek()</code> is <code>SUNDAY</code>, then week 1 of 1998
 * starts on January 4, 1998, and ends on January 10, 1998; the first three days
 * of 1998 then are part of week 53 of 1997.
 *
 * <p>Values calculated for the <code>WEEK_OF_MONTH</code> field range from 0
 * to 6.  Week 1 of a month (the days with <code>WEEK_OF_MONTH =
 * 1</code>) is the earliest set of at least
 * <code>getMinimalDaysInFirstWeek()</code> contiguous days in that month,
 * ending on the day before <code>getFirstDayOfWeek()</code>.  Unlike
 * week 1 of a year, week 1 of a month may be shorter than 7 days, need
 * not start on <code>getFirstDayOfWeek()</code>, and will not include days of
 * the previous month.  Days of a month before week 1 have a
 * <code>WEEK_OF_MONTH</code> of 0.
 *
 * <p>For example, if <code>getFirstDayOfWeek()</code> is <code>SUNDAY</code>
 * and <code>getMinimalDaysInFirstWeek()</code> is 4, then the first week of
 * January 1998 is Sunday, January 4 through Saturday, January 10.  These days
 * have a <code>WEEK_OF_MONTH</code> of 1.  Thursday, January 1 through
 * Saturday, January 3 have a <code>WEEK_OF_MONTH</code> of 0.  If
 * <code>getMinimalDaysInFirstWeek()</code> is changed to 3, then January 1
 * through January 3 have a <code>WEEK_OF_MONTH</code> of 1.
 *
 * <p>
 * <strong>Example:</strong>
 * <blockquote>
 * <pre>
 * // get the supported ids for GMT-08:00 (Pacific Standard Time)
 * String[] ids = TimeZone.getAvailableIDs(-8 * 60 * 60 * 1000);
 * // if no ids were returned, something is wrong. get out.
 * if (ids.length == 0)
 *     System.exit(0);
 *
 *  // begin output
 * System.out.println("Current Time");
 *
 * // create a Pacific Standard Time time zone
 * SimpleTimeZone pdt = new SimpleTimeZone(-8 * 60 * 60 * 1000, ids[0]);
 *
 * // set up rules for daylight savings time
 * pdt.setStartRule(Calendar.APRIL, 1, Calendar.SUNDAY, 2 * 60 * 60 * 1000);
 * pdt.setEndRule(Calendar.OCTOBER, -1, Calendar.SUNDAY, 2 * 60 * 60 * 1000);
 *
 * // create a GregorianCalendar with the Pacific Daylight time zone
 * // and the current date and time
 * Calendar calendar = new GregorianCalendar(pdt);
 * Date trialTime = new Date();
 * calendar.setTime(trialTime);
 *
 * // print out a bunch of interesting things
 * System.out.println("ERA: " + calendar.get(Calendar.ERA));
 * System.out.println("YEAR: " + calendar.get(Calendar.YEAR));
 * System.out.println("MONTH: " + calendar.get(Calendar.MONTH));
 * System.out.println("WEEK_OF_YEAR: " + calendar.get(Calendar.WEEK_OF_YEAR));
 * System.out.println("WEEK_OF_MONTH: " + calendar.get(Calendar.WEEK_OF_MONTH));
 * System.out.println("DATE: " + calendar.get(Calendar.DATE));
 * System.out.println("DAY_OF_MONTH: " + calendar.get(Calendar.DAY_OF_MONTH));
 * System.out.println("DAY_OF_YEAR: " + calendar.get(Calendar.DAY_OF_YEAR));
 * System.out.println("DAY_OF_WEEK: " + calendar.get(Calendar.DAY_OF_WEEK));
 * System.out.println("DAY_OF_WEEK_IN_MONTH: "
 *                    + calendar.get(Calendar.DAY_OF_WEEK_IN_MONTH));
 * System.out.println("AM_PM: " + calendar.get(Calendar.AM_PM));
 * System.out.println("HOUR: " + calendar.get(Calendar.HOUR));
 * System.out.println("HOUR_OF_DAY: " + calendar.get(Calendar.HOUR_OF_DAY));
 * System.out.println("MINUTE: " + calendar.get(Calendar.MINUTE));
 * System.out.println("SECOND: " + calendar.get(Calendar.SECOND));
 * System.out.println("MILLISECOND: " + calendar.get(Calendar.MILLISECOND));
 * System.out.println("ZONE_OFFSET: "
 *                    + (calendar.get(Calendar.ZONE_OFFSET)/(60*60*1000)));
 * System.out.println("DST_OFFSET: "
 *                    + (calendar.get(Calendar.DST_OFFSET)/(60*60*1000)));
 *
 * System.out.println("Current Time, with hour reset to 3");
 * calendar.clear(Calendar.HOUR_OF_DAY); // so doesn't override
 * calendar.set(Calendar.HOUR, 3);
 * System.out.println("ERA: " + calendar.get(Calendar.ERA));
 * System.out.println("YEAR: " + calendar.get(Calendar.YEAR));
 * System.out.println("MONTH: " + calendar.get(Calendar.MONTH));
 * System.out.println("WEEK_OF_YEAR: " + calendar.get(Calendar.WEEK_OF_YEAR));
 * System.out.println("WEEK_OF_MONTH: " + calendar.get(Calendar.WEEK_OF_MONTH));
 * System.out.println("DATE: " + calendar.get(Calendar.DATE));
 * System.out.println("DAY_OF_MONTH: " + calendar.get(Calendar.DAY_OF_MONTH));
 * System.out.println("DAY_OF_YEAR: " + calendar.get(Calendar.DAY_OF_YEAR));
 * System.out.println("DAY_OF_WEEK: " + calendar.get(Calendar.DAY_OF_WEEK));
 * System.out.println("DAY_OF_WEEK_IN_MONTH: "
 *                    + calendar.get(Calendar.DAY_OF_WEEK_IN_MONTH));
 * System.out.println("AM_PM: " + calendar.get(Calendar.AM_PM));
 * System.out.println("HOUR: " + calendar.get(Calendar.HOUR));
 * System.out.println("HOUR_OF_DAY: " + calendar.get(Calendar.HOUR_OF_DAY));
 * System.out.println("MINUTE: " + calendar.get(Calendar.MINUTE));
 * System.out.println("SECOND: " + calendar.get(Calendar.SECOND));
 * System.out.println("MILLISECOND: " + calendar.get(Calendar.MILLISECOND));
 * System.out.println("ZONE_OFFSET: "
 *        + (calendar.get(Calendar.ZONE_OFFSET)/(60*60*1000))); // in hours
 * System.out.println("DST_OFFSET: "
 *        + (calendar.get(Calendar.DST_OFFSET)/(60*60*1000))); // in hours
 * </pre>
 * </blockquote>
 *
 * @see          Calendar
 * @see          TimeZone
 * @version      1.62, 10/25/05
 * @author David Goldsmith, Mark Davis, Chen-Lieh Huang, Alan Liu
 * @since JDK1.1
 */
public class GregorianCalendar extends Calendar
{
    /** 
     * Value of the <code>ERA</code> field indicating
     * the period before the common era (before Christ), also known as BCE.
     * The sequence of years at the transition from <code>BC</code> to <code>AD</code> is
     * ..., 2 BC, 1 BC, 1 AD, 2 AD,...
     * @see Calendar#ERA
     */
    public static final int BC = 0;

    /** 
     * Value of the <code>ERA</code> field indicating
     * the common era (Anno Domini), also known as CE.
     * The sequence of years at the transition from <code>BC</code> to <code>AD</code> is
     * ..., 2 BC, 1 BC, 1 AD, 2 AD,...
     * @see Calendar#ERA
     */
    public static final int AD = 1;

    /** 
     * The point at which the Gregorian calendar rules are used, measured in
     * milliseconds from the standard epoch.  Default is October 15, 1582
     * (Gregorian) 00:00:00 UTC or -12219292800000L.  For this value, October 4,
     * 1582 (Julian) is followed by October 15, 1582 (Gregorian).  This
     * corresponds to Julian day number 2299161.
     * @serial
     */
    private long gregorianCutover;

    static final long serialVersionUID = -8125100834729963327L;

    /** 
     * Constructs a default GregorianCalendar using the current time
     * in the default time zone with the default locale.
     */
    public GregorianCalendar() { }

    /** 
     * Constructs a GregorianCalendar based on the current time
     * in the given time zone with the default locale.
     * @param zone the given time zone.
     */
    public GregorianCalendar(TimeZone zone) { }

    /** 
     * Constructs a GregorianCalendar based on the current time
     * in the default time zone with the given locale.
     * @param aLocale the given locale.
     */
    public GregorianCalendar(Locale aLocale) { }

    /** 
     * Constructs a GregorianCalendar based on the current time
     * in the given time zone with the given locale.
     * @param zone the given time zone.
     * @param aLocale the given locale.
     */
    public GregorianCalendar(TimeZone zone, Locale aLocale) { }

    /** 
     * Constructs a GregorianCalendar with the given date set
     * in the default time zone with the default locale.
     * @param year the value used to set the YEAR time field in the calendar.
     * @param month the value used to set the MONTH time field in the calendar.
     * Month value is 0-based. e.g., 0 for January.
     * @param date the value used to set the DATE time field in the calendar.
     */
    public GregorianCalendar(int year, int month, int date) { }

    /** 
     * Constructs a GregorianCalendar with the given date
     * and time set for the default time zone with the default locale.
     * @param year the value used to set the YEAR time field in the calendar.
     * @param month the value used to set the MONTH time field in the calendar.
     * Month value is 0-based. e.g., 0 for January.
     * @param date the value used to set the DATE time field in the calendar.
     * @param hour the value used to set the HOUR_OF_DAY time field
     * in the calendar.
     * @param minute the value used to set the MINUTE time field
     * in the calendar.
     */
    public GregorianCalendar(int year, int month, int date, int hour, int
        minute)
    { }

    /** 
     * Constructs a GregorianCalendar with the given date
     * and time set for the default time zone with the default locale.
     * @param year the value used to set the YEAR time field in the calendar.
     * @param month the value used to set the MONTH time field in the calendar.
     * Month value is 0-based. e.g., 0 for January.
     * @param date the value used to set the DATE time field in the calendar.
     * @param hour the value used to set the HOUR_OF_DAY time field
     * in the calendar.
     * @param minute the value used to set the MINUTE time field
     * in the calendar.
     * @param second the value used to set the SECOND time field
     * in the calendar.
     */
    public GregorianCalendar(int year, int month, int date, int hour, int
        minute, int second)
    { }

    /** 
     * Sets the GregorianCalendar change date. This is the point when the switch
     * from Julian dates to Gregorian dates occurred. Default is October 15,
     * 1582. Previous to this, dates will be in the Julian calendar.
     * <p>
     * To obtain a pure Julian calendar, set the change date to
     * <code>Date(Long.MAX_VALUE)</code>.  To obtain a pure Gregorian calendar,
     * set the change date to <code>Date(Long.MIN_VALUE)</code>.
     *
     * @param date the given Gregorian cutover date.
     */
    public void setGregorianChange(Date date) { }

    /** 
     * Gets the Gregorian Calendar change date.  This is the point when the
     * switch from Julian dates to Gregorian dates occurred. Default is
     * October 15, 1582. Previous to this, dates will be in the Julian
     * calendar.
     * @return the Gregorian cutover date for this calendar.
     */
    public final Date getGregorianChange() {
        return null;
    }

    /** 
     * Determines if the given year is a leap year. Returns true if the
     * given year is a leap year.
     * @param year the given year.
     * @return true if the given year is a leap year; false otherwise.
     */
    public boolean isLeapYear(int year) {
        return false;
    }

    /** 
     * Compares this GregorianCalendar to an object reference.
     * @param obj the object reference with which to compare
     * @return true if this object is equal to <code>obj</code>; false otherwise
     */
    public boolean equals(Object obj) {
        return false;
    }

    /** 
     * Override hashCode.
     * Generates the hash code for the GregorianCalendar object
     */
    public int hashCode() {
        return 0;
    }

    /** 
     * Adds the specified (signed) amount of time to the given time field,
     * based on the calendar's rules.
     * <p><em>Add rule 1</em>. The value of <code>field</code>
     * after the call minus the value of <code>field</code> before the
     * call is <code>amount</code>, modulo any overflow that has occurred in
     * <code>field</code>. Overflow occurs when a field value exceeds its
     * range and, as a result, the next larger field is incremented or
     * decremented and the field value is adjusted back into its range.</p>
     *
     * <p><em>Add rule 2</em>. If a smaller field is expected to be
     * invariant, but it is impossible for it to be equal to its
     * prior value because of changes in its minimum or maximum after
     * <code>field</code> is changed, then its value is adjusted to be as close
     * as possible to its expected value. A smaller field represents a
     * smaller unit of time. <code>HOUR</code> is a smaller field than
     * <code>DAY_OF_MONTH</code>. No adjustment is made to smaller fields
     * that are not expected to be invariant. The calendar system
     * determines what fields are expected to be invariant.</p>
     * @param field the time field.
     * @param amount the amount of date or time to be added to the field.
     * @exception IllegalArgumentException if an unknown field is given.
     */
    public void add(int field, int amount) { }

    /** 
     * Adds or subtracts (up/down) a single unit of time on the given time
     * field without changing larger fields. 
     * <p>
     * <em>Example</em>: Consider a <code>GregorianCalendar</code>
     * originally set to December 31, 1999. Calling <code>roll(Calendar.MONTH, true)</code>
     * sets the calendar to January 31, 1999.  The <code>Year</code> field is unchanged
     * because it is a larger field than <code>MONTH</code>.</p>
     * @param up indicates if the value of the specified time field is to be
     * rolled up or rolled down. Use true if rolling up, false otherwise.
     * @exception IllegalArgumentException if an unknown field value is given.
     * @see GregorianCalendar#add
     * @see GregorianCalendar#set
     */
    public void roll(int field, boolean up) { }

    /** 
     * Add to field a signed amount without changing larger fields.
     * A negative roll amount means to subtract from field without changing 
     * larger fields.
     * <p>
     * <em>Example</em>: Consider a <code>GregorianCalendar</code>
     * originally set to August 31, 1999. Calling <code>roll(Calendar.MONTH,
     * 8)</code> sets the calendar to April 30, <strong>1999</strong>. Using a
     * <code>GregorianCalendar</code>, the <code>DAY_OF_MONTH</code> field cannot
     * be 31 in the month April. <code>DAY_OF_MONTH</code> is set to the closest possible
     * value, 30. The <code>YEAR</code> field maintains the value of 1999 because it
     * is a larger field than <code>MONTH</code>.
     * <p>
     * <em>Example</em>: Consider a <code>GregorianCalendar</code>
     * originally set to Sunday June 6, 1999. Calling
     * <code>roll(Calendar.WEEK_OF_MONTH, -1)</code> sets the calendar to
     * Tuesday June 1, 1999, whereas calling
     * <code>add(Calendar.WEEK_OF_MONTH, -1)</code> sets the calendar to
     * Sunday May 30, 1999. This is because the roll rule imposes an
     * additional constraint: The <code>MONTH</code> must not change when the
     * <code>WEEK_OF_MONTH</code> is rolled. Taken together with add rule 1,
     * the resultant date must be between Tuesday June 1 and Saturday June
     * 5. According to add rule 2, the <code>DAY_OF_WEEK</code>, an invariant
     * when changing the <code>WEEK_OF_MONTH</code>, is set to Tuesday, the
     * closest possible value to Sunday (where Sunday is the first day of the
     * week).</p>
     * @param field the time field.
     * @param amount the signed amount to add to <code>field</code>.
     * @since 1.2
     * @see GregorianCalendar#add
     * @see GregorianCalendar#set
     */
    public void roll(int field, int amount) { }

    /** 
     * Returns minimum value for the given field.
     * e.g. for Gregorian DAY_OF_MONTH, 1
     * Please see Calendar.getMinimum for descriptions on parameters and
     * the return value.
     */
    public int getMinimum(int field) {
        return 0;
    }

    /** 
     * Returns maximum value for the given field.
     * e.g. for Gregorian DAY_OF_MONTH, 31
     * Please see Calendar.getMaximum for descriptions on parameters and
     * the return value.
     */
    public int getMaximum(int field) {
        return 0;
    }

    /** 
     * Returns highest minimum value for the given field if varies.
     * Otherwise same as getMinimum(). For Gregorian, no difference.
     * Please see Calendar.getGreatestMinimum for descriptions on parameters
     * and the return value.
     */
    public int getGreatestMinimum(int field) {
        return 0;
    }

    /** 
     * Returns lowest maximum value for the given field if varies.
     * Otherwise same as getMaximum(). For Gregorian DAY_OF_MONTH, 28
     * Please see Calendar.getLeastMaximum for descriptions on parameters and
     * the return value.
     */
    public int getLeastMaximum(int field) {
        return 0;
    }

    /** 
     * Return the minimum value that this field could have, given the current date.
     * For the Gregorian calendar, this is the same as getMinimum() and getGreatestMinimum().
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
     * @since 1.2
     */
    public int getActualMaximum(int field) {
        return 0;
    }

    /** 
     * Converts UTC as milliseconds to time field values.
     * The time is <em>not</em>
     * recomputed first; to recompute the time, then the fields, call the
     * <code>complete</code> method.
     * @see Calendar#complete
     */
    protected void computeFields() { }

    /** 
     * Overrides Calendar
     * Converts time field values to UTC as milliseconds.
     * @exception IllegalArgumentException if any fields are invalid.
     */
    protected void computeTime() { }

    /** 
     * Updates internal state.
     */
    private void readObject(ObjectInputStream stream)
        throws IOException, ClassNotFoundException
    { }
}
