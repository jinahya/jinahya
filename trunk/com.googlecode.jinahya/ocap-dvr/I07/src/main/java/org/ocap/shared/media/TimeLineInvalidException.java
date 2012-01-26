package org.ocap.shared.media;
/**
 * This exception is returned when a time line is no longer valid in a
 * the piece of content for which it was obtained. For example, the piece of content
 * is a time shift recording and the end of the time line is no longer 
 * within the buffer.
 */
public class TimeLineInvalidException extends java.lang.Exception
{
	/**
	 * Constructs a TimeLineInvalidException with no detail message
	 */
	public TimeLineInvalidException(){} 
}

