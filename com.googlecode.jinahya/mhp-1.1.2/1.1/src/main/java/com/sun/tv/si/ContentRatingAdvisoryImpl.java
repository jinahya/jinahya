/*
 * @(#)ContentRatingAdvisoryImpl.java	1.15 00/01/19
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

package com.sun.tv.si;

import java.util.*;
import javax.tv.service.*;
import javax.tv.service.guide.*;
import javax.tv.service.navigation.*;
import com.sun.tv.receiver.*;

/** TBD
 * @note bascially done, check the exceeds method that,
 * its less then and less then equal to.
 */

/**
 * The Content Advisory is used to indicate, for a given program event,
 * ratings for any or all of the rating dimensions defined in the Content
 * Rating System for the local rating region. An Event without content
 * advisory indicates that the rating value for any rating dimension is
 * zero. The absence of ratings for a specific dimension is completely
 * equivalent to having a zero-valued rating for such a dimension. The
 * absence of ratings for a specific region implies the absence of ratings
 * for all the dimensions in the region. <P>
 *
 * This information may be obtained in the ATSC Content Advisory Descriptor
 * or the DVB Parental Rating Descriptor. Note that the DVB rating system
 * is based on age only. It can be easily mapped to this rating system as
 * one of the dimensions.
 */

public class ContentRatingAdvisoryImpl implements ContentRatingAdvisory {

private Vector dimensionNames;
private Vector longNames;

public ContentRatingAdvisoryImpl(Vector dimensionNames, Vector longNames) {
	if (dimensionNames != null) {
		this.dimensionNames = dimensionNames;
	} else {
		this.dimensionNames = new Vector();
	}
	if (longNames != null) {
		this.longNames = longNames;
	} else {
		this.longNames = new Vector();
	}
}

/**
 * Returns a list of names of all dimensions rated for this Rating
 * Region.
 *
 * @return An array of strings representing all rated dimensions in this
 * rating region for this Service or Program Event.
 *
 * @see javax.tv.service.navigation.RatingDimension
 */
public String[] getDimensionNames() {

	Vector temp = new Vector();
	for (int i = 0; i < dimensionNames.size(); i++) {
		// filter out RatingDimension that's not supported locally..
	        RatingDimensionImpl rating = (RatingDimensionImpl)RatingDimensionImpl.getRatingDimension((String)this.dimensionNames.elementAt(i));
		if (rating != null)  {
			temp.addElement(dimensionNames.elementAt(i));
	}
	}

	String names[] = new String[temp.size()];
	for (int i = 0; i < temp.size(); i++ ) { 
		names[i] = (String)temp.elementAt(i);
	}
	return names;
}

/**
 *
 * Returns a number representing the rating level in the specified
 * <code>RatingDimension</code> associated with this rating region
 * for the related Service or Program Event.
 *
 * @param dimensionName The name of the <code>RatingDimension</code>
 * for which to obtain the rating level.
 *
 * @return A number representing the rating level. The meaning is
 * dependent on the associated rating dimension.
 *
 * @throws SIException If dimensionName is not a valid
 * <code>RatingDimension</code> name.
 *
 * @see javax.tv.service.navigation.RatingDimension#getDimensionName
 */
public short getRatingLevel(String ratedDimension) throws SIException {
	if (ratedDimension == null) {
		throw new NullPointerException("ratedDimension == null");
	}

	String longName = this.getRatingLevelText(ratedDimension);
	if (longName == null) {
		throw new SIException("Invalid ratedDimension " + ratedDimension);
	}

	short level = this.findRatingLevel(ratedDimension, longName);
	if (level == -1) {
		throw new SIException("Invalid ratedDimension " + ratedDimension);
	}
	return level;
}

/**
 * Returns the rating description display string for the specified
 * dimension.
 *
 * @param dimensionName The name of the <code>RatingDimension</code>
 * for which to obtain the description.
 *
 * @return A string representing the textual value of this rating.
 *
 * @throws SIException If dimensionName is not a valid
 * <code>RatingDimension</code> name.
 *
 * @see javax.tv.service.navigation.RatingDimension#getDimensionName
 */
public String getRatingLevelText(String dimensionName) throws SIException {
	if (dimensionName == null) {
		throw new NullPointerException("dimensionName == null");
	}

	for (int i = 0; i < dimensionNames.size(); i++) {
		String name = (String)dimensionNames.elementAt(i);
		if (name != null && dimensionName.equalsIgnoreCase(name)) {
			return (String)longNames.elementAt(i);
		}
	}
	throw new SIException("Invalid dimensionName " + dimensionName);
}

/**
 * Provides a single string representing textual rating values for all
 * dimensions.
 *
 * @return String concatenating the text labels for all dimensions into a
 * single label.
 */
public String getDisplayText() {
	StringBuffer buf = new StringBuffer();
	for (int i = 0; i < dimensionNames.size(); i++) {
		String name = (String)dimensionNames.elementAt(i);
		try {
			buf.append(getRatingLevelText(name));
			if (i < (dimensionNames.size() + 1)) {
				buf.append(",");
			}
		} catch (Exception e) {
			;
		}
	}
	return buf.toString();
}

/**
 * Compares the current rating value with the user specified
 * rating ceiling.
 *
 * @return True if the rating exceeds the current receiver setting;
 * False if the rating does not exceed the current receiver setting (and
 * the content can thus be displayed).
 */
public boolean exceeds() {
	for (int i = 0; i < dimensionNames.size(); i++) {
		String name = (String)dimensionNames.elementAt(i);
		try {
			short eventLevel = getRatingLevel(name);
			short receiverLevel = getReceiverRatingLevel(name);
			if (receiverLevel == -1) {
				;
			} else if (receiverLevel < eventLevel) {
				return true;
			}
		} catch (Exception e) {
			;
		}
	}
	return false;
}

/**
 * This method gets the rating level to which this receiver
 * has been set. The rating level is the maximum level of
 * content which can be displyed be this receiver.  
 * @param dimensionName is the name of the rating system
 * for which the receiver rating level is to be retrieved.
 * @return the rating level for the dimension name specified,
 */
private short getReceiverRatingLevel(String dimensionName) {
	for (int i = 0; i < Settings.ReceiverRatingNames.length; i++) {
		if (dimensionName.equals(Settings.ReceiverRatingNames[i])) {
			String longName = Settings.ReceiverRatingLevels[i];
			return findRatingLevel(dimensionName, longName);
		}
	}
	return (short)-1;
}

/**
 * This method gets the rating level, given the dimension name
 * and the short name for te rating level.
 * @param dimensionName is the name of the rating system
 * for which the rating level is to be retrieved.
 * @param longName long name for the rating level
 * @return the rating level for the dimension name specified,
 * a returned value of -1, indicates no match in dimension name or
 * in the short name.
 *
 * @note As an example, the dimensionName might be "MPAA" and
 * the longName might be "PG-13", therefore the returned rating
 * level might be 3. Implementations, will vary.
 */
private short findRatingLevel(String dimensionName, String longName) {
	RatingDimensionImpl rating = (RatingDimensionImpl)RatingDimensionImpl.getRatingDimension(dimensionName);
	if (rating == null) {
		return (short)-1;
	}
	return rating.findRatingLevel(longName);
}
}
