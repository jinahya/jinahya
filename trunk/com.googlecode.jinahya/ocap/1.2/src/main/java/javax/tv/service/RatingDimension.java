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



  


package javax.tv.service;

import javax.tv.service.SIException;

/** 
 *  The <code>RatingDimension</code> interface represents an
 * individual content rating scheme against which program events are
 * rated.  Each rating region may support multiple rating
 * dimensions. One dimension in the U.S. rating region, for example,
 * is used to describe the MPAA list. The dimension name for such a
 * case may be defined as "MPAA".  Another example of a rating
 * dimension may be an age-based DVB rating.
 *
 * @see javax.tv.service.guide.ProgramEvent
 * @see javax.tv.service.guide.ContentRatingAdvisory
 */
public interface RatingDimension
{

    /** 
     * Returns a string which represents the dimension name being described by
     * this object. One dimension in the U.S. rating region, for example, is
     * used to describe the MPAA list. The dimension name for such a case may
     * be defined as "MPAA".
     *
     * @return A string representing the name of this rating dimension.
     */
    public String getDimensionName();

    /** 
     * Returns the number of levels defined for this dimension.
     *
     * @return The number of levels in this dimension.
     */
    public short getNumberOfLevels();

    /** 
     * Returns a pair of strings describing the specified rating level for
     * this dimension.
     *
     * @param ratingLevel The rating level for which to retrieve the
     * textual description.
     *
     * @return A pair of strings representing the names for the
     * specified rating level. The first string represents the abbreviated
     * name for the rating level. The second string represents the
     * full name for the rating level.
     *
     * @throws SIException If <code>ratingLevel</code> is not valid for
     * this <code>RatingDimension</code>.
     *
     * @see javax.tv.service.guide.ContentRatingAdvisory#getRatingLevel
     */
    public String[] getRatingLevelDescription(short ratingLevel)
        throws javax.tv.service.SIException;
}
