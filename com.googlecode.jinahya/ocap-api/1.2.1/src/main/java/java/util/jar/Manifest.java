/*
<p>This is not an official specification document, and usage is restricted.
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
This work corresponds to the API signatures of JSR 219: Foundation
Profile 1.1. In the event of a discrepency between this work and the
JSR 219 specification, which is available at
http://www.jcp.org/en/jsr/detail?id=219, the latter takes precedence.
*/


  


package java.util.jar;

import java.io.FilterInputStream;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.IOException;
import java.util.Map;
import java.util.HashMap;
import java.util.Iterator;

/** 
 * The Manifest class is used to maintain Manifest entry names and their
 * associated Attributes. There are main Manifest Attributes as well as
 * per-entry Attributes. For information on the Manifest format, please
 * see the 
 * <a href="../../../../guide/jar/jar.html">
 * Manifest format specification</a>.
 *
 * @author  David Connelly
 * @version 1.33, 05/03/00
 * @see	    Attributes
 * @since   1.2
 */
public class Manifest implements Cloneable
{

    /** 
     * Constructs a new, empty Manifest.
     */
    public Manifest() { }

    /** 
     * Constructs a new Manifest from the specified input stream.
     *
     * @param is the input stream containing manifest data
     * @throws IOException if an I/O error has occured
     */
    public Manifest(InputStream is) throws IOException { }

    /** 
     * Constructs a new Manifest that is a copy of the specified Manifest.
     *
     * @param man the Manifest to copy
     */
    public Manifest(Manifest man) { }

    /** 
     * Returns the main Attributes for the Manifest.
     * @return the main Attributes for the Manifest
     */
    public Attributes getMainAttributes() {
        return null;
    }

    /** 
     * Returns a Map of the entries contained in this Manifest. Each entry
     * is represented by a String name (key) and associated Attributes (value).
     *
     * @return a Map of the entries contained in this Manifest
     */
    public Map getEntries() {
        return null;
    }

    /** 
     * Returns the Attributes for the specified entry name.
     * This method is defined as:
     * <pre>
     *	    return (Attributes)getEntries().get(name)
     * </pre>
     *
     * @param name entry name
     * @return the Attributes for the specified entry name
     */
    public Attributes getAttributes(String name) {
        return null;
    }

    /** 
     * Clears the main Attributes as well as the entries in this Manifest.
     */
    public void clear() { }

    /** 
     * Writes the Manifest to the specified OutputStream. 
     * Attributes.Name.MANIFEST_VERSION must be set in 
     * MainAttributes prior to invoking this method.
     *
     * @param out the output stream
     * @exception IOException if an I/O error has occurred
     * @see #getMainAttributes
     */
    public void write(OutputStream out) throws IOException { }

    /** 
     * Reads the Manifest from the specified InputStream. The entry
     * names and attributes read will be merged in with the current
     * manifest entries.
     *
     * @param is the input stream
     * @exception IOException if an I/O error has occurred
     */
    public void read(InputStream is) throws IOException { }

    /** 
     * Returns true if the specified Object is also a Manifest and has
     * the same main Attributes and entries.
     *
     * @param o the object to be compared
     * @return true if the specified Object is also a Manifest and has
     * the same main Attributes and entries
     */
    public boolean equals(Object o) {
        return false;
    }

    /** 
     * Returns the hash code for this Manifest.
     */
    public int hashCode() {
        return 0;
    }

    /** 
     * Returns a shallow copy of this Manifest.  The shallow copy is
     * implemented as follows:
     * <pre>
     *     public Object clone() { return new Manifest(this); }
     * </pre>
     * @return a shallow copy of this Manifest
     */
    public Object clone() {
        return null;
    }
}
