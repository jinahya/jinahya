/*
 * @(#)ContentRatingAdvisory.java	1.31 00/10/09
 *
 * Copyright 1998-2000 by Sun Microsystems, Inc.,
 * 901 San Antonio Road, Palo Alto, California, 94303, U.S.A.
 * All rights reserved.
 * 
 * This software is the confidential and proprietary information
 * of Sun Microsystems, Inc. ("Confidential Information").  You
 * shall not disclose such Confidential Information and shall use
 * it only in accordance with the terms of the license agreement
 * you entered into with Sun.
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
 * @see javax.tv.service.guide.ProgramEvent */
public interface ContentRatingAdvisory {
	
  /**
   * Returns a list of names of all dimensions in this rating
   * region by which the <code>ProgramEvent</code> is rated.
   *
   * @return An array of strings representing all rated dimensions in this
   * rating region for the <code>ProgramEvent</code>.
   *
   * @see javax.tv.service.RatingDimension
   */
  public abstract String[] getDimensionNames();
  
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
   * @see javax.tv.service.RatingDimension#getDimensionName */
  public abstract short getRatingLevel(String dimensionName)
      throws SIException;
  
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
  public abstract String getRatingLevelText(String dimensionName)
      throws SIException;
  
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
   * system rating ceiling; <code>false</code> otherwise.  */
  public boolean exceeds();
}
