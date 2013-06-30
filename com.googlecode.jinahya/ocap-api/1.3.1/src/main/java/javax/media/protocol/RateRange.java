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



  


package javax.media.protocol;

/** 
 * Describes the speed at which data flows.
 *
 * @version 1.6, 97/08/23.
 * 
 */
public class RateRange
{

    /** 
     * Copy constructor.
     *
     */
    public RateRange(RateRange r) { }

    /** 
     * Constructor using required values.
     *
     * @param init The initial value for this rate.
     * @param min The minimum value that this rate can take.
     * @param max The maximum value that this rate can take.
     * @param isExact Set to <CODE>true</CODE> if the source rate does not vary when using this 
     * rate range.
     */
    public RateRange(float init, float min, float max, boolean isExact) { }

    /** 
     * Set the current rate. Returns the rate that was actually set.
     * This implementation just returns the specified rate, 
     * subclasses should return the rate that was actually
     * set.
     * @param rate The new rate.
     */
    public float setCurrentRate(float rate) {
        return 0.0f;
    }

    /** 
     * Get the current rate.
     *
     * @return The current rate.
     *
     */
    public float getCurrentRate() {
        return 0.0f;
    }

    /** 
     * Get the minimum rate supported by this range.
     *
     * @return The minimum rate.
     */
    public float getMinimumRate() {
        return 0.0f;
    }

    /** 
     * Get the maximum rate supported by this range.
     *
     * @return The maximum rate.
     */
    public float getMaximumRate() {
        return 0.0f;
    }

    /** 
     * Determine whether or not the source will maintain a constant
     * speed when using this rate. If the
     * rate varies, synchronization is usually impractical.
     *
     * @return Returns <CODE>true</CODE> if the source will maintain a constant speed at this rate.
     */
    public boolean isExact() {
        return false;
    }
}
