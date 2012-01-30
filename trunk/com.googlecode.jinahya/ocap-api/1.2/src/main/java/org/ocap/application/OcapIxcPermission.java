package org.ocap.application;

import java.security.BasicPermission;
import java.security.Permission;
import java.security.PermissionCollection;

import javax.microedition.xlet.ixc.IxcPermission;

import org.dvb.application.AppID;
import org.dvb.io.ixc.IxcRegistry;

/**
 * This class represents access to the inter-xlet communication registry. 
 * An OcapIxcPermission consists of a name specification and a action specifying 
 * what can be done with those names.
 * <p>
 * The name specification is a superset of the name passed into the {@link IxcRegistry}
 * methods such as {@link IxcRegistry#bind} and {@link IxcRegistry#lookup}.
 * Valid names are composed of fields delimited by "/" characters, with each field 
 * specifying a particular value (e.g., OID).  The following grammar defines the name
 * format:
 * <pre>
 * <i>NAME</i>      = "*" | "/" <i>SCOPE</i> "/" <i>SIGNED</i> "/" <i>OID</i> "/" <i>AID</i> "/" <i>BINDNAME</i>
 * <i>SCOPE</i>     = "*" | "global" | "ixc" | "service-" <i>CONTEXT</i>
 * <i>CONTEXT</i>   = "*" | <i>context-id</i>
 * <i>SIGNED</i>    = "*" | "signed" | "unsigned"
 * <i>OID</i>       = "*" | <i>oid</i>
 * <i>AID</i>       = "*" | <i>aid</i>
 * <i>BINDNAME</i>  = "*" | <i>bindname</i> | <i>bindname</i> "*"
 * </pre>
 * Where <code>"*"</code> specifies a wildcard character.
 * Where <i>context-id</i> is a platform-specific unique identifier for a service
 * context; 
 * <i>oid</i> and <i>aid</i> are the {@link AppID#getOID() organization} and
 * {@link AppID#getAID() application} identifiers of the binding application as
 * converted by {@link Integer#toHexString}; 
 * and <i>bindname</i> is the application-defined name given at bind-time.
 * <p>
 * <ul>
 * <li> "*" as the entire name string will match any other name
 * <li> "/&#42;/&#42;/&#42;/&#42;/* is equivalent to "*"
 * <li> "/&#42;/&#42;/1a/4abc/*" will match names in any scope, 
 *      published by an application with an OID of <code>1a</code> and AID of <code>4abc</code>.
 * <li> "/&#42;/signed/&#42;/VODApi" will match any object bound by a signed application 
 *      with an ixcname of "VODApi".
 * </ul>
 *
 * The actions specification is comprised of a single action specified by one of two
 * keywords: "bind" or "lookup".  These correspond to the <code>bind</code> and 
 * <code>lookup</code> methods of <code>IxcRegistry</code>. 
 * The actions string is converted to lowercase before processing.
 * <p>
 *  
 * @author Aaron Kamienski
 */
public final class OcapIxcPermission extends BasicPermission
{
    /**
     * Creates a new OcapIxcPermission object with the specified name and actions.
     * The name specification is a superset of the name passed into the {@link IxcRegistry}
     * methods such as {@link IxcRegistry#bind} and {@link IxcRegistry#lookup}.
     * See the {@link OcapIxcPermission class description} for the specification of the name string.
     * <p>
     * The actions specification is comprised of a single action specified by one of two
     * keywords: "bind" or "lookup".  These correspond to the <code>bind</code> and 
     * <code>lookup</code> methods of <code>IxcRegistry</code>. 
     * The actions string is converted to lowercase before processing.
     *      
     * @param name The name specification for exported/imported objects
     * @param actions The action string
     */
    public OcapIxcPermission(String name, String actions)
    {
        super(name, actions);
    }

    /**
     * Checks two OcapIxcPermission objects for equality. 
     * Check that other is an OcapIxcPermission, and has the same name and actions as this object.
     *
     * @param obj the object we are testing for equality with this object
     * @return true if obj is an OcapIxcPermission, and has the same name and actions as this OcapIxcPermission object.
     */
    public boolean equals(Object obj)
    {
        return false;
    }

    /**
     * Returns the "canonical string representation" of the actions. 
     * That is, this method always returns present actions in the following order: bind, lookup. 
     * For example, if this OcapIxcPermission object allows both bind and lookup actions, a call 
     * to getActions will return the string "bind,lookup".
     * 
     * @return the canonical string representation of the actions
     */
    public String getActions()
    {
        return null;
    }

    /**
     * Returns the hash code value for this object.
     * 
     * @return a hash code value for this object. 
     */
    public int hashCode()
    {
        return 0;
    }

    /**
     * Checks if this OcapIxcPermission "implies" the specified permission.
     * <p>
     * More specifically, this method returns true if:
     * <ul>
     * <li> p is an instanceof OcapIxcPermission
     * <li> p's actions are a proper subset of this object's actions, and
     * <li> p's name is implied by this object's name. 
     * </ul>
     * <p>
     * The rules for determining if this object's name implies p's name are
     * as follows:
     * <ul>
     * <li> Where p's name is exactly the same as this object's name, 
     * then it is implied.
     * <li> The name <code>"*"</code> and <code>"/&#42;/&#42;/&#42;/&#42;/*"</code> both imply
     * all possible names.
     * <li> Where this object's name includes a wildcard for a field (<code>"*"</code>),
     * then all possible values for that field are implied.
     * <li> Where this object's name includes a field that ends in a wildcard (e.g.,
     * <code>service-*</code>) then all possible values for that field 
     * starting with the non-wildcard portion are implied.
     * </ul> 
     * <p>
     * For example, <code>"/service-&#42;/signed/abc/4001/*"</code> implies
     * <code>"/service-1234/signed/abc/4001/VODObject"</code>.
     * 
     * <p>
     * An <code>OcapIxcPermission</code> may also imply an {@link IxcPermission}.
     * That is, this method will also return true if:
     * <ul>
     * <li> p is an instanceof IxcPermission
     * <li> p's actions are a proper subset of this object's actions, and
     * <li> p's name is implied by this object's name.
     * </ul>
     * <p>
     * The rules for determining if this object's name implies an <code>IxcPermission</code>
     * name are the same as detailed above except that a translation of the <code>IxcPermission</code>
     * name to the <code>OcapIxcPermission</code> is applied first.  The following table
     * shows how such a mapping SHALL be applied:
     * <p>
     * <table border>
     * <tr> <th>IxcPermission name</th> <th>OcapIxcPermission name</th> </tr>
     * <tr> <td>"*"</td> <td>"*"</td> </tr>
     * <tr> <td>"dvb:/*"</td> <td>"*"</td> </tr>
     * <tr> <td>"dvb:/signed/*"</td> <td>"/global/signed/&#42;/&#42;/*</td> </tr>
     * <tr> <td>"dvb:/service/<i>id</i>/signed/*"</td> <td>"/service-<i>id</i>/signed/&#42;/&#42;/*</td> </tr>
     * <tr> <td>"dvb:/ixc/*"</td> <td>"/ixc/&#42;/&#42;/&#42;/*</td> </tr>
     * <tr> <td>"dvb:/signed/<i>OID</i>/*"</td> <td>"/global/signed/<i>OID</i>/&#42;/*</td> </tr>
     * <tr> <td>"dvb:/service/<i>id</i>/signed/<i>OID</i>/*"</td> <td>"/service-<i>id</i>/signed/<i>OID</i>/&#42;/*</td> </tr>
     * <tr> <td>"dvb:/ixc/<i>OID</i>/*"</td> <td>"/ixc/&#42;/<i>OID</i>/&#42;/*</td> </tr>
     * <tr> <td>"dvb:/signed/<i>OID</i>/<i>AID</i>/*"</td> <td>"/global/signed/<i>OID</i>/<i>AID</i>/*</td> </tr>
     * <tr> <td>"dvb:/service/<i>id</i>/signed/<i>OID</i>/<i>AID</i>/*"</td> <td>"/service-<i>id</i>/signed/<i>OID</i>/<i>AID</i>/*</td> </tr>
     * <tr> <td>"dvb:/ixc/<i>OID</i>/<i>AID</i>/*"</td> <td>"/ixc/&#42;/<i>OID</i>/<i>AID</i>/*</td> </tr>
     * <tr> <td>"dvb:/signed/<i>OID</i>/<i>AID</i>/<i>name</i>*"</td> <td>"/global/signed/<i>OID</i>/<i>AID</i>/<i>name</i>*</td> </tr>
     * <tr> <td>"dvb:/service/<i>id</i>/signed/<i>OID</i>/<i>AID</i>/<i>name</i>*"</td> <td>"/service-<i>id</i>/signed/<i>OID</i>/<i>AID</i>/<i>name</i>*</td> </tr>
     * <tr> <td>"dvb:/ixc/<i>OID</i>/<i>AID</i>/<i>name</i>*"</td> <td>"/ixc/&#42;/<i>OID</i>/<i>AID</i>/<i>name</i>*</td> </tr>
     * </table>
     * <p>
     * Any <code>IxcPermission</code> name that cannot be mapped cannot be implied.
     * 
     * @param p the permission to check against
     * @return true if the specified permission is implied by this object, false if not.
     */
    public boolean implies(Permission p)
    {
        return false;
    }

    /**
     * Returns a new PermissionCollection object for storing OcapIxcPermission objects.
     * <p>
     * OcapIxcPermission objects must be stored in a manner that allows them to be inserted 
     * into the collection in any order, but that also enables the PermissionCollection 
     * implies method to be implemented in an efficient (and consistent) manner.
     * 
     * @return a new PermissionCollection object suitable for storing OcapIxcPermissions.
     */
    public PermissionCollection newPermissionCollection()
    {
        return null;
    }

}
