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



  


package javax.tv.service.guide;

import javax.tv.service.SIException;

/** 
 * ContentRatingAdvisory indicates, for a given program event, ratings
 * for any or all of the rating dimensions defined in the content
 * rating system for the local rating region. A program event without
 * a content advisory indicates that the rating value for any rating
 * dimension is zero. The absence of ratings for a specific dimension
 * is equivalent to having a zero-valued rating for such a
 * dimension. The absence of ratings for a specific region implies the
 * absence of ratings for all the dimensions in the region. <P>
 *
 * For example, this information may be obtained in the ATSC Content
 * Advisory Descriptor or the DVB Parental Rating Descriptor. Note
 * that the DVB rating system is based on age only. It can be easily
 * mapped to this rating system as one of the dimensions.
 *
 * @see javax.tv.service.guide.ProgramEvent 
 */
public interface ContentRatingAdvisory
{

    /** 
     * Returns a list of names of all dimensions in this rating
     * region by which the <code>ProgramEvent</code> is rated.
     *
     * @return An array of strings representing all rated dimensions in this
     * rating region for the <code>ProgramEvent</code>.
     *
     * @see javax.tv.service.RatingDimension
     */
    public String[] getDimensionNames();

    /** 
     * Returns a number representing the rating level in the specified
     * <code>RatingDimension</code> associated with this rating region
     * for the related <code>ProgramEvent</code>.
     *
     * @param dimensionName The name of the <code>RatingDimension</code>
     * for which to obtain the rating level.
     *
     * @return A number representing the rating level. The meaning is
     * dependent on the associated rating dimension.
     *
     * @throws SIException If <code>dimensionName</code> is not a valid
     * name of a <code>RatingDimension</code> for the ProgramEvent.
     *
     * @see javax.tv.service.RatingDimension#getDimensionName 
     */
    public short getRatingLevel(String dimensionName) throws SIException;

    /** 
     * Returns the rating level display string for the specified
     * dimension.  The string is identical to
     * <code>d.getRatingLevelDescription(getRatingLevel(dimensionName))[1]</code>,
     * where <code>d</code> is the <code>RatingDimension</code> obtained
     * by
     * <code>javax.tv.service.SIManager.getRatingDimension(dimensionName)</code>.
     *
     * @param dimensionName The name of the <code>RatingDimension</code>
     * for which to obtain the rating level text.
     *
     * @return A string representing the textual value of this rating level.
     *
     * @throws SIException If dimensionName is not a valid
     * <code>RatingDimension</code> name for the <code>ProgramEvent</code>.
     *
     * @see javax.tv.service.RatingDimension#getDimensionName
     * @see javax.tv.service.RatingDimension#getRatingLevelDescription
     */
    public String getRatingLevelText(String dimensionName) throws SIException;

    /** 
     * Provides a single string representing textual rating values for all
     * dimensions in which the program event is rated.
     * The result will be a representation of the strings obtained via
     * <code>d.getRatingLevelDescription(getRatingLevel(d.getDimensionName()))[0]</code>,
     * for all dimensions <code>d</code> obtained through
     * <code>javax.tv.service.SIManager.getRatingDimension(n)</code>,
     * for all dimension names <code>n</code> obtained from
     * <code>getDimensionNames()</code>.
     * 
     * @return A string representing the rating level values for all
     * dimensions in which this program event is rated.  The format of
     * the string may be implementation-specific.
     *
     * @see #getDimensionNames
     * @see javax.tv.service.RatingDimension#getRatingLevelDescription
     */
    public String getDisplayText();

    /** 
     * Compares the current rating value with the system rating
     * ceiling.  The rating ceiling is set in a system-dependent manner.
     * Content that exceeds the rating ceiling cannot be displayed.
     *
     * @return <code>true</code> if the rating exceeds the current
     * system rating ceiling; <code>false</code> otherwise.  
     */
    public boolean exceeds();
}
