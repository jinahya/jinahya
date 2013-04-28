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


  


package java.awt;

import java.awt.AWTException;
import java.awt.Point;
import java.awt.Toolkit;
import java.io.File;
import java.io.FileInputStream;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Properties;
import java.util.StringTokenizer;
import java.security.AccessController;
// import sun.awt.DebugHelper;

/** 
 * A class to encapsulate the bitmap representation of the mouse cursor.
 *
 * @see Component#setCursor
 * @version 	1.39, 01/23/03
 * @author 	Amy Fowler
 */
public class Cursor implements java.io.Serializable
{
    /** 
     * The default cursor type (gets set if no cursor is defined).
     */
    public static final int DEFAULT_CURSOR = 0;

    /** 
     * The crosshair cursor type.
     */
    public static final int CROSSHAIR_CURSOR = 1;

    /** 
     * The text cursor type.
     */
    public static final int TEXT_CURSOR = 2;

    /** 
     * The wait cursor type.
     */
    public static final int WAIT_CURSOR = 3;

    /** 
     * The south-west-resize cursor type.
     */
    public static final int SW_RESIZE_CURSOR = 4;

    /** 
     * The south-east-resize cursor type.
     */
    public static final int SE_RESIZE_CURSOR = 5;

    /** 
     * The north-west-resize cursor type.
     */
    public static final int NW_RESIZE_CURSOR = 6;

    /** 
     * The north-east-resize cursor type.
     */
    public static final int NE_RESIZE_CURSOR = 7;

    /** 
     * The north-resize cursor type.
     */
    public static final int N_RESIZE_CURSOR = 8;

    /** 
     * The south-resize cursor type.
     */
    public static final int S_RESIZE_CURSOR = 9;

    /** 
     * The west-resize cursor type.
     */
    public static final int W_RESIZE_CURSOR = 10;

    /** 
     * The east-resize cursor type.
     */
    public static final int E_RESIZE_CURSOR = 11;

    /** 
     * The hand cursor type.
     */
    public static final int HAND_CURSOR = 12;

    /** 
     * The move cursor type.
     */
    public static final int MOVE_CURSOR = 13;

    protected static Cursor[] predefined;

//     /** 
//      * The type associated with all custom cursors.
//      */
//     public static final int CUSTOM_CURSOR = -1;

    /*
     * JDK 1.1 serialVersionUID
     */
    private static final long serialVersionUID = 8028237497568985504L;

    // /** 
     // * The user-visible name of the cursor.
     // *
     // * @serial
     // * @see #getName()
     // */
    // protected String name;

    /** 
     * The chosen cursor type initially set to
     * the <code>DEFAULT_CURSOR</code>.
     *
     * @serial
     * @see #getType()
     */
     int type;

    /** 
     * Creates a new cursor object with the specified type.
     * @param type the type of cursor
     * @throws IllegalArgumentException if the specified cursor type
     * is invalid
     */
    public Cursor(int type) { }

    // /** 
     // * Creates a new custom cursor object with the specified name.<p>
     // * Note:  this constructor should only be used by AWT implementations
     // * as part of their support for custom cursors.  Applications should
     // * use Toolkit.createCustomCursor().
     // * @param name the user-visible name of the cursor.
     // * @see java.awt.Toolkit#createCustomCursor
     // */
    // protected Cursor(String name) { }

    /** 
     * Returns a cursor object with the specified predefined type.
     * 
     * @param type the type of predefined cursor
     * @return the specified predefined cursor 
     * @throws IllegalArgumentException if the specified cursor type is
     *         invalid
     */
    public static Cursor getPredefinedCursor(int type) { return null; }

    // /** 
     // * Returns a system-specific custom cursor object matching the 
     // * specified name.  Cursor names are, for example: "Invalid.16x16"
     // * 
     // * @param name a string describing the desired system-specific custom cursor 
     // * @return the system specific custom cursor named
     // * @exception HeadlessException if
     // * <code>GraphicsEnvironment.isHeadless</code> returns true
     // */
    // public static Cursor getSystemCustomCursor(String name)
        // throws java.awt.AWTException, HeadlessException
    // { }

    /** 
     * Return the system default cursor.
     */
    public static Cursor getDefaultCursor() { return null;}

    /** 
     * Returns the type for this cursor.
     */
    public int getType() { return 0; }

    // /** 
     // * Returns the name of this cursor.
     // * @return    a localized description of this cursor.
     // * @since     1.2
     // */
    // public String getName() { }

    // /** 
     // * Returns a string representation of this cursor.
     // * @return    a string representation of this cursor.
     // * @since     1.2
     // */
    // public String toString() { }

    protected void finalize() throws Throwable { }
}
