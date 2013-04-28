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



  


package javax.tv.service;

/** 
 * This class represents types of changes to SI elements.
 *
 * @see SIChangeEvent
 * @see SIElement
 */
public class SIChangeType
{
    /** 
     * <code>SIChangeType</code> indicating that an <code>SIElement</code>
     * has been added.
     */
    public static final SIChangeType ADD = null;

    /** 
     * <code>SIChangeType</code> indicating that an <code>SIElement</code>
     * has been removed.
     */
    public static final SIChangeType REMOVE = null;

    /** 
     * <code>SIChangeType</code> indicating that an <code>SIElement</code>
     * has been modified.
     */
    public static final SIChangeType MODIFY = null;

    /** 
     * Creates an <code>SIChangeType</code> object.
     *
     * @param name The string name of this type (e.g. "ADD").
     */
    protected SIChangeType(String name) { }

    /** 
     * Provides the string name of the type.  For the type objects
     * defined in this class, the string name will be identical to the
     * class variable name.
     *
     * @return The string name of the type.
     */
    public String toString() {
        return null;
    }
}
