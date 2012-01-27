/*
 * @(#)RatingDimension.java	1.18 00/08/29
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
public interface RatingDimension {
  
  /**
   * Returns a string which represents the dimension name being described by
   * this object. One dimension in the U.S. rating region, for example, is
   * used to describe the MPAA list. The dimension name for such a case may
   * be defined as "MPAA".
   *
   * @return A string representing the name of this rating dimension.
   */
  public abstract String getDimensionName();
  
  /**
   * Returns the number of levels defined for this dimension.
   *
   * @return The number of levels in this dimension.
   */
  public abstract short getNumberOfLevels();
  
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
  public abstract String[] getRatingLevelDescription(short ratingLevel) throws SIException;
}
