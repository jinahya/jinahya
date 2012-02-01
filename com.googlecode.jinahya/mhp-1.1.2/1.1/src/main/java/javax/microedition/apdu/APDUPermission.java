package javax.microedition.apdu;


import javax.microedition.apdu.APDUConnection;

import java.security.Permission;

/**
* This class represents access to a smart card using the APDU protocol.
* An APDUPermission contains a name (also referred to as a "target name"
* but no actions list.
* The name is the symbolic name of the APDUPermission. The following list
* provides the currently defined APDUPermission target names and the
* descriptions of what each permission allows:
*
* Target Name: aid
* What the permission allows:
* the ability to communicate with a smart card application identified by an AID
* Target Name: sat
* What the permission allows:
* the ability to communicate with a (U)SAT application on channel 0
*/

public final class APDUPermission extends Permission {

	/**
	* Creates a new APDUPermission object with the specified name.
	* The name is the symbolic name of the APDUPermission, such as
	* "sat" or "aid".
	* @param name the name of the APDUPermission
	*/
	public APDUPermission(String n){
		super(n);
	}

	public boolean equals(Object arg0) {
		return false;
	}

	public String getActions() {
		return null;
	}

	public int hashCode() {
		return 0;
	}

	public boolean implies(Permission arg0) {
		return false;
	}

}
