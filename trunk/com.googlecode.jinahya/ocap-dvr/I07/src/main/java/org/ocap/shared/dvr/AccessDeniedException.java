package org.ocap.shared.dvr;
/**
 * Exception thrown when an application is blocked from operating
 * on a RecordingRequest by security attributes associated with that
 * RecordingRequest.
 */
public class AccessDeniedException extends java.lang.Exception
{
	/**
	 * Constructs a AccessDeniedException with no detail message
	 */
	public AccessDeniedException(){}
}

