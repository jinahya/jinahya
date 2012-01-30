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

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.TimeZone;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.ObjectInputStream;
import java.lang.ref.SoftReference;

/** 
 * The class <code>Date</code> represents a specific instant
 * in time, with millisecond precision.
 * <p>
 * Prior to JDK&nbsp;1.1, the class <code>Date</code> had two additional
 * functions.  It allowed the interpretation of dates as year, month, day, hour,
 * minute, and second values.  It also allowed the formatting and parsing
 * of date strings.  Unfortunately, the API for these functions was not
 * amenable to internationalization.  As of JDK&nbsp;1.1, the
 * <code>Calendar</code> class should be used to convert between dates and time
 * fields and the <code>DateFormat</code> class should be used to format and
 * parse date strings.
 * The corresponding methods in <code>Date</code> are deprecated.
 * <p>
 * Although the <code>Date</code> class is intended to reflect 
 * coordinated universal time (UTC), it may not do so exactly, 
 * depending on the host environment of the Java Virtual Machine. 
 * Nearly all modern operating systems assume that 1&nbsp;day&nbsp;=
 * 24&nbsp;&times;&nbsp;60&nbsp;&times;&nbsp;60&nbsp;= 86400 seconds 
 * in all cases. In UTC, however, about once every year or two there 
 * is an extra second, called a "leap second." The leap 
 * second is always added as the last second of the day, and always 
 * on December 31 or June 30. For example, the last minute of the 
 * year 1995 was 61 seconds long, thanks to an added leap second. 
 * Most computer clocks are not accurate enough to be able to reflect 
 * the leap-second distinction. 
 * <p>
 * Some computer standards are defined in terms of Greenwich mean 
 * time (GMT), which is equivalent to universal time (UT).  GMT is 
 * the "civil" name for the standard; UT is the 
 * "scientific" name for the same standard. The 
 * distinction between UTC and UT is that UTC is based on an atomic 
 * clock and UT is based on astronomical observations, which for all 
 * practical purposes is an invisibly fine hair to split. Because the 
 * earth's rotation is not uniform (it slows down and speeds up 
 * in complicated ways), UT does not always flow uniformly. Leap 
 * seconds are introduced as needed into UTC so as to keep UTC within 
 * 0.9 seconds of UT1, which is a version of UT with certain 
 * corrections applied. There are other time and date systems as 
 * well; for example, the time scale used by the satellite-based 
 * global positioning system (GPS) is synchronized to UTC but is 
 * <i>not</i> adjusted for leap seconds. An interesting source of 
 * further information is the U.S. Naval Observatory, particularly 
 * the Directorate of Time at:
 * <blockquote><pre>
 *     <a href=http://tycho.usno.navy.mil>http://tycho.usno.navy.mil</a>
 * </pre></blockquote>
 * <p>
 * and their definitions of "Systems of Time" at:
 * <blockquote><pre>
 *     <a href=http://tycho.usno.navy.mil/systime.html>http://tycho.usno.navy.mil/systime.html</a>
 * </pre></blockquote>
 * <p>
 * In all methods of class <code>Date</code> that accept or return 
 * year, month, date, hours, minutes, and seconds values, the 
 * following representations are used: 
 * <ul>
 * <li>A year <i>y</i> is represented by the integer 
 *     <i>y</i>&nbsp;<code>-&nbsp;1900</code>. 
 * <li>A month is represented by an integer from 0 to 11; 0 is January, 
 *     1 is February, and so forth; thus 11 is December. 
 * <li>A date (day of month) is represented by an integer from 1 to 31 
 *     in the usual manner. 
 * <li>An hour is represented by an integer from 0 to 23. Thus, the hour 
 *     from midnight to 1 a.m. is hour 0, and the hour from noon to 1 
 *     p.m. is hour 12. 
 * <li>A minute is represented by an integer from 0 to 59 in the usual manner.
 * <li>A second is represented by an integer from 0 to 61; the values 60 and 
 *     61 occur only for leap seconds and even then only in Java 
 *     implementations that actually track leap seconds correctly. Because 
 *     of the manner in which leap seconds are currently introduced, it is 
 *     extremely unlikely that two leap seconds will occur in the same 
 *     minute, but this specification follows the date and time conventions 
 *     for ISO C.
 * </ul>
 * <p>
 * In all cases, arguments given to methods for these purposes need 
 * not fall within the indicated ranges; for example, a date may be 
 * specified as January 32 and is interpreted as meaning February 1.
 *
 * @author  James Gosling
 * @author  Arthur van Hoff
 * @author  Alan Liu
 * @version 1.70 11/09/00
 * @see     java.text.DateFormat
 * @see     java.util.Calendar
 * @see     java.util.TimeZone
 * @since   JDK1.0
 */
public class Date implements java.io.Serializable, Cloneable, Comparable
{
    private static final long serialVersionUID = 7523967970034938905L;

    /** 
     * Allocates a <code>Date</code> object and initializes it so that 
     * it represents the time at which it was allocated, measured to the 
     * nearest millisecond. 
     *
     * @see     java.lang.System#currentTimeMillis()
     */
    public Date() { }

    /** 
     * Allocates a <code>Date</code> object and initializes it to 
     * represent the specified number of milliseconds since the 
     * standard base time known as "the epoch", namely January 1, 
     * 1970, 00:00:00 GMT. 
     *
     * @param   date   the milliseconds since January 1, 1970, 00:00:00 GMT.
     * @see     java.lang.System#currentTimeMillis()
     */
    public Date(long date) { }

    /** 
     * Return a copy of this object.
     */
    public Object clone() {
        return null;
    }

    /** 
     * Returns the number of milliseconds since January 1, 1970, 00:00:00 GMT
     * represented by this <tt>Date</tt> object.
     *
     * @return  the number of milliseconds since January 1, 1970, 00:00:00 GMT
     *          represented by this date.
     */
    public long getTime() {
        return -1;
    }

    /** 
     * Sets this <tt>Date</tt> object to represent a point in time that is 
     * <tt>time</tt> milliseconds after January 1, 1970 00:00:00 GMT. 
     *
     * @param   time   the number of milliseconds.
     */
    public void setTime(long time) { }

    /** 
     * Tests if this date is before the specified date.
     *
     * @param   when   a date.
     * @return  <code>true</code> if and only if the instant of time 
     *            represented by this <tt>Date</tt> object is strictly 
     *            earlier than the instant represented by <tt>when</tt>;
     *          <code>false</code> otherwise.
     */
    public boolean before(Date when) {
        return false;
    }

    /** 
     * Tests if this date is after the specified date.
     *
     * @param   when   a date.
     * @return  <code>true</code> if and only if the instant represented 
     *          by this <tt>Date</tt> object is strictly later than the 
     *          instant represented by <tt>when</tt>; 
     *          <code>false</code> otherwise.
     */
    public boolean after(Date when) {
        return false;
    }

    /** 
     * Compares two dates for equality.
     * The result is <code>true</code> if and only if the argument is 
     * not <code>null</code> and is a <code>Date</code> object that 
     * represents the same point in time, to the millisecond, as this object.
     * <p>
     * Thus, two <code>Date</code> objects are equal if and only if the 
     * <code>getTime</code> method returns the same <code>long</code> 
     * value for both. 
     *
     * @param   obj   the object to compare with.
     * @return  <code>true</code> if the objects are the same;
     *          <code>false</code> otherwise.
     * @see     java.util.Date#getTime()
     */
    public boolean equals(Object obj) {
        return false;
    }

    /** 
     * Compares two Dates for ordering.
     *
     * @param   anotherDate   the <code>Date</code> to be compared.
     * @return  the value <code>0</code> if the argument Date is equal to
     *          this Date; a value less than <code>0</code> if this Date
     *          is before the Date argument; and a value greater than
     *      <code>0</code> if this Date is after the Date argument.
     * @since   1.2
     */
    public int compareTo(Date anotherDate) {
        return 0;
    }

    /** 
     * Compares this Date to another Object.  If the Object is a Date,
     * this function behaves like <code>compareTo(Date)</code>.  Otherwise,
     * it throws a <code>ClassCastException</code> (as Dates are comparable
     * only to other Dates).
     *
     * @param   o the <code>Object</code> to be compared.
     * @return  the value <code>0</code> if the argument is a Date
     *      equal to this Date; a value less than <code>0</code> if the
     *      argument is a Date after this Date; and a value greater than
     *      <code>0</code> if the argument is a Date before this Date.
     * @exception ClassCastException if the argument is not a
     *        <code>Date</code>. 
     * @see     java.lang.Comparable
     * @since   1.2
     */
    public int compareTo(Object o) {
        return 0;
    }

    /** 
     * Returns a hash code value for this object. The result is the 
     * exclusive OR of the two halves of the primitive <tt>long</tt> 
     * value returned by the {@link Date#getTime} 
     * method. That is, the hash code is the value of the expression:
     * <blockquote><pre>
     * (int)(this.getTime()^(this.getTime() >>> 32))</pre></blockquote>
     *
     * @return  a hash code value for this object. 
     */
    public int hashCode() {
        return 0;
    }

    /** 
     * Converts this <code>Date</code> object to a <code>String</code> 
     * of the form:
     * <blockquote><pre>
     * dow mon dd hh:mm:ss zzz yyyy</pre></blockquote>
     * where:<ul>
     * <li><tt>dow</tt> is the day of the week (<tt>Sun, Mon, Tue, Wed, 
     *     Thu, Fri, Sat</tt>).
     * <li><tt>mon</tt> is the month (<tt>Jan, Feb, Mar, Apr, May, Jun, 
     *     Jul, Aug, Sep, Oct, Nov, Dec</tt>).
     * <li><tt>dd</tt> is the day of the month (<tt>01</tt> through 
     *     <tt>31</tt>), as two decimal digits.
     * <li><tt>hh</tt> is the hour of the day (<tt>00</tt> through 
     *     <tt>23</tt>), as two decimal digits.
     * <li><tt>mm</tt> is the minute within the hour (<tt>00</tt> through 
     *     <tt>59</tt>), as two decimal digits.
     * <li><tt>ss</tt> is the second within the minute (<tt>00</tt> through 
     *     <tt>61</tt>, as two decimal digits.
     * <li><tt>zzz</tt> is the time zone (and may reflect daylight saving 
     *     time). Standard time zone abbreviations include those 
     *     recognized by the method <tt>parse</tt>. If time zone 
     *     information is not available, then <tt>zzz</tt> is empty - 
     *     that is, it consists of no characters at all.
     * <li><tt>yyyy</tt> is the year, as four decimal digits.
     * </ul>
     *
     * @return  a string representation of this date. 
     */
    public String toString() {
        return null;
    }

    /** 
     * Reconstitute this object from a stream (i.e., deserialize it).
     */
    private void readObject(ObjectInputStream s)
        throws IOException, ClassNotFoundException
    { }

    /** 
     * Save the state of this object to a stream (i.e., serialize it).
     *
     * @serialData The value returned by <code>getTime()</code>
     *		   is emitted (long).  This represents the offset from
     *             January 1, 1970, 00:00:00 GMT in milliseconds.
     */
    private void writeObject(ObjectOutputStream s) throws IOException { }
}
