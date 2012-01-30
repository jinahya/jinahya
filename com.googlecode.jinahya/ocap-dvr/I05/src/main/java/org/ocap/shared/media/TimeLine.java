package org.ocap.shared.media;

import javax.media.Time;

/**
 * Represents a transmitted time line. Transmitted time lines start at
 * one media time within a piece of content and finish at a later media time 
 * in that content. Transmitted time lines are valid at all media times
 * between these points. They are either increment linearly or are paused.
 * The value of a transmitted time line does not have any discontinuities.
 */

public interface TimeLine {
	/**
	 * Returns the first media time at which this time line is valid.
   	 * For a scheduled recording, this is the first point within the
	 * piece of content where the time line is valid.
	 * For a timeshift recording, if the time line
	 * starts within the time shift buffer then the media time 
	 * where it starts will be returned. If the time line starts before
	 * the start of the time shift buffer, the media time of the start
	 * of the time shift buffer will be returned. Note that if the
	 * time shift buffer is full and time shift recording is in progress, 
	 * the start of the buffer will be moving as newly written data
   	 * over-writes the former start of the buffer.
	 * 
	 * @return a media time
	 * @throws TimeLineInvalidException if the time line is no longer 
	 * valid in this piece of content. e.g. the piece of content is a 
	 * time shift recording and the end of the time line is no longer 
	 * within the buffer
	 */
	Time getFirstMediaTime() throws TimeLineInvalidException;
	/**
	 * Returns the last media time at which this time line is valid.
   	 * For a scheduled recording, this is the last point within the
	 * piece of content where the time line is valid.
	 * For a timeshift recording, if the time line
	 * ends within the time shift buffer then the media time 
	 * where it ends will be returned. If the time line ends after
	 * the end of the time shift buffer, the media time of the end
	 * of the time shift buffer will be returned. Note that if the
	 * time shift buffer is full and time shift recording is in progress, 
	 * the end of the buffer will be moving as newly written data
   	 * over-writes the former start of the buffer.
	 * @return a media time
	 * @throws TimeLineInvalidException if the time line is no longer 
	 * valid in this piece of content. e.g. the piece of content is a 
	 * time shift recording and the end of the time line is no longer 
	 * within the buffer
	 */
	Time getLastMediaTime() throws TimeLineInvalidException;
	/**
	 * Returns the first valid time in this time line.
   	 * For a scheduled recording, this is the first point within the
	 * piece of content where the time line is valid.
	 * For a timeshift recording, if the time line
	 * starts within the time shift buffer then the time 
	 * where it starts will be returned. If the time line starts before
	 * the start of the time shift buffer, the time of the start
	 * of the time shift buffer will be returned. Note that if the
	 * time shift buffer is full and time shift recording is in progress, 
	 * the start of the buffer will be moving as newly written data
   	 * over-writes the former start of the buffer.
	 * @return a time in this time line
	 * @throws TimeLineInvalidException if the time line is no longer
	 * valid in this piece of content. e.g. the piece of content is a 
	 * time shift recording and the end of the time line is no longer 
	 * within the buffer
	 */
	long getFirstTime() throws TimeLineInvalidException;
	/**
	 * Returns the last valid time in this time line.
   	 * For a scheduled recording, this is the last point within the
	 * piece of content where the time line is valid.
	 * For a timeshift recording, if the time line
	 * ends within the time shift buffer then the time 
	 * where it end will be returned. If the time line ends after
	 * the end of the time shift buffer, the time of the end
	 * of the time shift buffer will be returned. Note that if the
	 * time shift buffer is full and time shift recording is in progress, 
	 * the end of the buffer will be moving as newly written data
   	 * over-writes the former start of the buffer.
	 * @return a time in this time line
	 * @throws TimeLineInvalidException if the time line is no longer 
	 * valid in this piece of content. e.g. the piece of content is a 
	 * time shift recording and the end of the time line is no longer 
	 * within the buffer
	 */
	long getLastTime() throws TimeLineInvalidException;
	/**
	 * Translates a time in this time line into the corresponding
	 * media time. If the time is one where the time line pauses, the returned
	 * media time shall be the highest media time corresponding to the time
	 * specified.
	 * @param time a time in this time line
	 * @return the corresponding media time
	 * @throws TimeLineInvalidException if the time line is no longer 
	 * valid in this piece of content. e.g. the piece of content is a 
	 * time shift recording and the end of the time line is no longer 
	 * within the buffer
	 * @throws TimeOutOfRangeException if the time specified is not within 
	 * this timeline
	 */
	Time getMediaTime(long time) throws TimeLineInvalidException,
			TimeOutOfRangeException;
	/**
	 * Translates a media time into the corresponding time in this timeline
	 * @param mediatime a media time
	 * @return the corresponding time in this timeline
	 * @throws TimeLineInvalidException if the time line is no longer 
	 * valid in this piece of content. e.g. the piece of content is a 
	 * time shift recording and the end of the time line is no longer 
	 * within the buffer
	 * @throws TimeOutOfRangeException if the media time specified is not
	 * within this timeline
	 */
	long getTime(Time mediatime) throws TimeLineInvalidException,
			TimeOutOfRangeException;
}



