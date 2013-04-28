/**
<p>This is not an official specification document, and usage is restricted.
</p>
<a name="notice"><strong><center>
NOTICE
</center></strong><br>
<br>

(c) 2005-2008 Sun Microsystems, Inc. All Rights Reserved.
<p>

Neither this file nor any files generated from it describe a complete
specification, and they may only be used as described below.  
<p>
Sun Microsystems Inc. owns the copyright in this file and it is provided
to you for informative use only. For example, 
this file and any files generated from it may be used to generate other documentation, 
such as a unified set of documents of API signatures for a platform 
that includes technologies expressed as Java APIs. 
This file may also be used to produce "compilation stubs," 
which allow applications to be compiled and validated for such platforms. 
By contrast, no permission is given for you to incorporate this file, 
in whole or in part, in an implementation of a Java specification.
<p>
Any work generated from this file, such as unified javadocs or compiled
stub files, must be accompanied by this notice in its entirety.
<p>
This work corresponds to the API signatures of JSR 927: Java TV API 1.1.1.  
In the event of a discrepency between this work and the JSR 927 specification, 
which is available at http://www.jcp.org/en/jsr/detail?id=927, the latter takes precedence.
*/



  


package javax.media;

import java.util.*;

/** 
 * A <CODE>PackageManager</CODE> maintains a persistent store of
 * package-prefix lists. A package prefix specifies the
 * prefix for a complete class name. A factory uses
 * a package-prefix list to find a class that
 * might belong to any of the packages that are referenced
 * in the prefix list.<p>
 *
 * The <code>Manager</code> uses package-prefix lists
 * to find protocol handlers and content handlers for time-based
 * media.<p>
 *
 * The current version of a package-prefix list is obtained with
 * the <code>get&lt;package-prefix&gt;List</code> method. This method returns the prefix
 * list in use; any changes to the list take effect immediately.
 *
 * Unless it is made persistent with
 * <code>commit&lt;package-prefix&gt;List</code>, a package-prefix list is only valid 
 * while the <code>Manager</code> is referenced.
 * 
 * The <code>commit&lt;package-prefix&gt;List</code> method ensures that any changes made 
 * to a package-prefix list are still visible the next time that
 * the <code>Manager</code> is referenced.
 *
 * @see Manager
 * @version 1.11, 97/08/23.
 */
public class PackageManager
{

    public PackageManager() { }

    /** 
     * Get the current value of the protocol package-prefix list.
     * <p>
     * @return The protocol package-prefix list.
     */
    public static Vector getProtocolPrefixList() {
        return null;
    }

    /** 
     * Set the protocol package-prefix list.
     * This is required for changes to take effect.
     *
     * @param list The new package-prefix list to use.
     */
    public static void setProtocolPrefixList(Vector list) { }

    /** 
     * Make changes to the protocol package-prefix list persistent.
     * <p>
     * This method throws a <code>SecurityException</code> if the calling thread
     * does not have access to system properties.
     *
     */
    public static void commitProtocolPrefixList() { }

    /** 
     * Get the current value of the content package-prefix list.
     * Any changes made to this list take effect immediately.
     * <p>
     *
     * @return The content package-prefix list.
     */
    public static Vector getContentPrefixList() {
        return null;
    }

    /** 
     * Set the current value of the content package-prefix list.
     * This is required for changes to take effect.
     *
     * @param list The content package-prefix list to set.
     */
    public static void setContentPrefixList(Vector list) { }

    /** 
     * Make changes to the content prefix-list persistent.
     * <p>
     * This method throws a <code>SecurityException</code> if the calling thread
     * does not have access to system properties.
     *
     */
    public static void commitContentPrefixList() { }
}
