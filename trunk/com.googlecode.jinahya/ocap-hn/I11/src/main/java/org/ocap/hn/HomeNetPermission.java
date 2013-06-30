package org.ocap.hn;

/**
 * The HomeNetPermission class represents permission to execute privileged
 * home networking operations only signed applications MAY be granted.
 * <p>
 * A HomeNetPermission consists of a permission name, representing
 * a single privileged operation.
 * The name given in the constructor may end in "*" to represent
 * all permissions beginning with the given string, such as <code>"*"</code>
 * to allow all HomeNetPermission operations.
 * <p>
 * The following table lists all HomeNetPermission permission names.
 * <table border=1 cellpadding=5>
 * <tr>
 *   <th>Permission Name</th>
 *   <th>What the Permission Allows</th>
 *   <th>Description</th>
 * </tr>
 *
 * <tr>
 *   <td>contentmanagement</td>
 *   <td>Provides management of local or remote content</td>
 *   <td>Applications with this permission can copy, move, delete content
 *      as well as allocate and delete logical volumes on a local network
 *      device.</td>
 * </tr>
 *
 * <tr>
 *  <td>contentlisting</td>
 *  <td>Provides listing of content on remote devices</td>
 *  <td>Applications with this permission can discover and query lists of
 *      content stored on or streamable from remote devices.</td>
 * </tr>
 * 
 * <tr>
 *  <td>recording</td>
 *  <td>Provides recording operations on remote devices</td>
 *  <td>Applications with this permission can request that recordings
 *      be scheduled, prioritized, and deleted on remote devices.</td>
 * </tr>
 *
 * <tr>
 *  <td>recordinghandler</td>
 *  <td>Provides recording request handler functionality on the local device</td>
 *  <td>Applications with this permission can manage network recording
 *      requests for the local device.</td>
 * </tr>
 * 
 * </table>
 *
 * Other permissions may be added as necessary.
 */
public final class HomeNetPermission extends java.security.BasicPermission
{

    /**
     * Constructor for the HomeNetPermission
     *
     * @param name The name of this permission (see table in class
     *      description).
     */
    public HomeNetPermission (String name)
    {
        super(name);
    }
}
