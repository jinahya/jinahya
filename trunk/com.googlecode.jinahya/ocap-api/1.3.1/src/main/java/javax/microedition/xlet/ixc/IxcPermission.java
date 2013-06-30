/*
</p>
<a name="notice"><strong><center>
NOTICE
</center></strong><br>
<br>

(c) 2005-2007 Sun Microsystems, Inc. All Rights Reserved.
<p>
Neither this file nor any files generated from it describe a complete
specification, and they may only be used as described below. For
example, no permission is given for you to incorporate this file, in
whole or in part, in an implementation of a Java specification.
<p>
Sun Microsystems Inc. owns the copyright in this file and it is provided
to you for informative, as opposed to normative, use. The file and any
files generated from it may be used to generate other informative
documentation, such as a unified set of documents of API signatures for
a platform that includes technologies expressed as Java APIs. The file
may also be used to produce "compilation stubs," which allow
applications to be compiled and validated for such platforms.
<p>
Any work generated from this file, such as unified javadocs or compiled
stub files, must be accompanied by this notice in its entirety.
<p>
This work corresponds to the API signatures of JSR 217: Personal Basis
Profile 1.1. In the event of a discrepency between this work and the
JSR 217 specification, which is available at
http://www.jcp.org/en/jsr/detail?id=217, the latter takes precedence.
*/

package javax.microedition.xlet.ixc;

import java.security.Permission;

/**
 * This class represents access to the inter-xlet communication registry.
 * An IxcPermission consists of a name specification and a set
 * of actions specifying what can be done with those names.
 * <p>
 * The name specification is the name passed into the IxcRegistry
 * methods such as bind() and lookup().  The names are simple strings; this
 * specification does not impose any special meaning on these strings,
 * such as name canonicalization.
 * Limited wildcarding is
 * allowed at the end of a name string.  The name string of length one
 * "*" will match any name.  At the end of a string, the special sequence
 * "/*" will match forward-slash followed by any other characters.  Otherwise,
 * all characters are simply matched.  For example: <ul>
 * <li>"*" as the entire name string will match any other string
 * <li>"xlet:/1a/2b/*" ends in a wildcard, and will match
 *	any string that begins with "xlet:/1a/2b/", e.g.
 *	"xlet:/1a/2b/foo", "xlet:/1a/2b/3c/foo" or
 *	"xlet:/1a/2b/".  It will not match "xlet://1a/2b/"
 *      or "xlet:/1a/2b".
 * <li>"*foo" does not contain a wildcard, and will only match
 *	the string "*foo".
 * <li>"foo*" does not contain a wildcard, and will only match
 *	the string "foo*".
 * <li>"a*b" does not contain a wildcard, and will only match
 *	the string "a*b"
 * <li>"/a/*<!-- ignore -->/b" does not contain a wildcard, and will only match
 *	the string "/a/*<!-- ignore -->/b"
 * </ul>
 * <p>
 * The actions to be granted are passed to the constructor in a string 
 * containing a list of one or more comma-seperated keywords.  The
 * possible keywords are "bind" and "lookup".  See the methods
 * on IxcRegistry for a detailed explanation of the meaning of these.
 * <p>
 * The actions string is converted to lowercase before processing.
 *
 * @see 	IxcRegsitry
 *
 * @serial exclude
 **/
public final class IxcPermission extends Permission {

    //
    //  Most of the implementation of this, including
    //  newPermissionCollection(), can be copied from
    //  java.io.FilePermission.  Our wildcarding is a bit simpler,
    //  and we have fewer actions.


    /**
     * the actions string. 
     *
     * @serial
     */
    private String actions;

    /**
     * Creates a new IxcPermission object with the specified name and actions.
     * The name specification is the name passed into the IxcRegistry
     * methods such as bind() and lookup().  See the class description
     * for the specification of the name string.
     * <p>
     * The actions to be granted are passed to the constructor in a string 
     * containing a list of one or more comma-seperated keywords.  The
     * possible keywords are "bind" and "lookup".  See the methods
     * on IxcRegistry for a detailed explanation of the meaning of these.
     * <p>
     * The actions string is converted to lowercase before processing.
     *
     * @param name	The name specification for exported/imported objects
     * @param actions	The action string
     **/
    public IxcPermission(String name, String actions) {
      super(name);
    }

    /**
     * Checks if this IxcPermission "implies" the specified permission.
     * <p>
     * More specifically, this method returns true if:
     * <ul>
     *   <li> p is an instanceof IxcPermission
     *   <li> p's actions are a proper subset of this object's actions, and
     *   <li> p's pathname is implied by this object's pathname.  For
     *       example, "/1234/*" implies "/1234/5678", since "/*" is a
     *       wildcard for strings containing "/" followed by any other
     *       characters.
     *  </ul>
     *
     * @param p the permission to check against
     *
     * @return true if the specified permission is implied by this object,
     *		false if not.
     **/
    public boolean implies(Permission p) { return false; }

    /**
     * Checks two IxcPermission objects for equality.
     * Check that <i>other</i> is an IxcPermission, and has the same
     * name and actions as this object.
     *
     * @param other the object we are testing for equality with this object
     *
     * @return true if obj is an IxcPermission, and has the same name and
     *		actions as this IxcPermission object.
     **/
    public boolean equals(Object other) { return false;}

    /**
     * Returns the hash code value for this object.
     **/
    public int hashCode() { return 0;}

    /**
     * Returns the "canonical string representation" of the actions.  
     * That is, this method always returns present actions in the 
     * following order: bind, lookup.  
     * For example, if this IxcPermission object  allows both bind and
     * lookup actions, a call to getActions will return the string
     * "bind,lookup".
     * 
     * @return the canonical string representation of the actions
     **/
    public String getActions() { return null;}

    /** 
     * Returns a new PermissionCollection object for storing 
     * IxcPermission objects.
     * <p>
     * IxcPermission objects must be stored in a manner that allows 
     * them to be inserted into the collection in any order, but that 
     * also enables the PermissionCollection implies  method to be 
     * implemented in an efficient (and consistent) manner.
     * <p>
     * For example, if you have two IxcPermission objects: <br>
     *    &nbsp;&nbsp;&nbsp;&nbsp;"/1234/*", "lookup" <br>
     *    &nbsp;&nbsp;&nbsp;&nbsp;"/1234/5678/*", "bind" <br>
     * and you are calling the <code>implies</code> method with the
     * IxcPermission: <br>
     *    &nbsp;&nbsp;&nbsp;&nbsp;<code>"/1234/5678/foo", 
     * "bind,lookup"</code>, <br>
     * then the <code>implies</code> method must take into account both the
     * "/1234/*" and "/1234/5678/*" permissions, so the effective permission
     * is "bind,lookup"  and <code>implies</code> returns true.  The
     * "implies" semantics for IxcPermissions are handled properly by the
     * permission collection object returned by this 
     * <code>newPermissionCollection</code> method.
     * 
     * @return a new PermissionCollection object suitable for storing 
     *		IxcPermissions.
     **/
    public java.security.PermissionCollection newPermissionCollection() {
        return null;
    }

}
