/**
<p>This is not an official specification document, and usage is restricted.
</p>
<a name="notice"><strong><center>
NOTICE
</center></strong><br>
<br>

(c) 2005-2008 Sun Microsystems, Inc. All Rights Reserved.
<p>

Neither this file nor any files generated from it describe a complete
specification, and they may only be used as described below.  
<p>
Sun Microsystems Inc. owns the copyright in this file and it is provided
to you for informative use only. For example, 
this file and any files generated from it may be used to generate other documentation, 
such as a unified set of documents of API signatures for a platform 
that includes technologies expressed as Java APIs. 
This file may also be used to produce "compilation stubs," 
which allow applications to be compiled and validated for such platforms. 
By contrast, no permission is given for you to incorporate this file, 
in whole or in part, in an implementation of a Java specification.
<p>
Any work generated from this file, such as unified javadocs or compiled
stub files, must be accompanied by this notice in its entirety.
<p>
This work corresponds to the API signatures of JSR 927: Java TV API 1.1.1.  
In the event of a discrepency between this work and the JSR 927 specification, 
which is available at http://www.jcp.org/en/jsr/detail?id=927, the latter takes precedence.
*/



  


package javax.media;

/** 
 * <code>Time</code> abstracts time in the Java Media framework.
 *
 * @see Clock
 * @see TimeBase
 *
 * @version 1.10, 97/08/28.
 */
public class Time
{
    public static final long ONE_SECOND = 1000000000L;

    /** 
     * Time is kept to a granularity of nanoseconds.
     * Converions to and from this value are done
     * to implement construction or query in seconds.
     */
    protected long nanoseconds;

    /** 
     * Construct a time in nanoseconds.
     *
     * @param nano Number of nanoseconds for this time.
     */
    public Time(long nano) { }

    /** 
     * Construct a time in seconds.
     *
     * @param seconds Time specified in seconds.
     */
    public Time(double seconds) { }

    /** 
     * Convert seconds to nanoseconds.
     */
    protected long secondsToNanoseconds(double seconds) {
        return -1;
    }

    /** 
     * Get the time value in nanoseconds.
     *
     * @return The time in nanoseconds.
     */
    public long getNanoseconds() {
        return -1;
    }

    /** 
     * Get the time value in seconds.
     *
     */
    public double getSeconds() {
        return 0.0d;
    }
}
