package org.ocap.shared.dvr;

/**
 * Controls access to recording features by an application.
 * The name can be one of the values shown in the following list;
 * <ul>
 * <li>"create" - schedule a RecordingRequest
 * <li>"read" - obtain the list of RecordingRequests
 * <li>"modify" - modify properties or application specific data for a
 * RecordingRequest
 * <li>"delete" - delete a RecordingRequest including recorded content
 * <li>"cancel" - cancel a pending RecordingRequest
 * <li>"*" - all of the above
 * </ul>
 * The action can be "own" and "*". The action "own" is intended for use by
 * normal applications. The action "*" is intended for use only by specially
 * privileged applications and permits the operation defined by the name to
 * be applied to all RecordingRequests regardless of any per-application 
 * restrictions associated with the RecordingRequest.
 * <p>
 * Granting of this permission shall include granting access to any 
 * storage devices required for the operations specified in the name 
 * parameter. No additional low permissions (e.g. FilePermission) 
 * are subsequently needed.
 */
public final class RecordingPermission extends java.security.BasicPermission {
	/**
	 * Creates a new RecordingPermission with the specified name and action.
	 * @param name "create", "read", "modify", "delete", "cancel" or "*"
	 * @param action "own"  or "*"
	 */
	public RecordingPermission(String name, String action){super(name);}
	/**
	 * Checks two RecordingPermission objects for equality
	 * @param obj the object to test for equality with this object.
	 * @return true if obj is a RecordingPermission with the same name 
	 * and action as this RecordingPermission object
	 */
	public boolean equals (Object obj){return true;}
	/**
	 * Returns the hash code value for this object.  This method follows the
	 * general contract of Object.hashCode() -- specifically, two distinct
	 * instances of RecordingPermission which satisfy equals(...) must return the
	 * same hash code value.
	 * @return a hash code value for this object.
	 */
	public int hashCode(){return 0;}
	/**
	 * Returns the actions as passed into the constructor.
	 * @return the actions as a String
	 */
	public String getActions() {return null;}
}


