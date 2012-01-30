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

import java.io.ObjectInputStream;
import java.io.IOException;

/** 
 * A flow layout arranges components in a left-to-right flow, much
 * like lines of text in a paragraph. Flow layouts are typically used
 * to arrange buttons in a panel. It will arrange
 * buttons left to right until no more buttons fit on the same line.
 * Each line is centered.
 * <p>
 * For example, the following picture shows an applet using the flow
 * layout manager (its default layout manager) to position three buttons:
 * <p>
 * <img src="doc-files/FlowLayout-1.gif"
 * ALT="Graphic of Layout for Three Buttons"
 * ALIGN=center HSPACE=10 VSPACE=7>
 * <p>
 * Here is the code for this applet:
 * <p>
 *
 * <em>Note: The following code example includes classes that do not
 * appear in this specification. Their inclusion is purely to serve as
 * a demonstration.<p></em>
 *
 *
 * <hr><blockquote><pre> 
 * import java.awt.*; 
 * import java.applet.Applet;
 *
 * public class myButtons extends Applet {
 *     Button button1, button2, button3;
 *     public void init() {
 *         button1 = new Button("Ok");
 *         button2 = new Button("Open");
 *         button3 = new Button("Close");
 *         add(button1);
 *         add(button2);
 *         add(button3);
 *     }
 * }
 * </pre></blockquote><hr>
 * <p>
 * A flow layout lets each component assume its natural (preferred) size.
 *
 * @version 	1.46, 01/23/03
 * @author 	Arthur van Hoff
 * @author 	Sami Shaio
 * @since       JDK1.0
 */
public class FlowLayout implements LayoutManager, java.io.Serializable
{
    /** 
     * This value indicates that each row of components
     * should be left-justified.
     */
    public static final int LEFT = 0;

    /** 
     * This value indicates that each row of components
     * should be centered.
     */
    public static final int CENTER = 1;

    /** 
     * This value indicates that each row of components
     * should be right-justified.
     */
    public static final int RIGHT = 2;

//     /** 
//      * This value indicates that each row of components
//      * should be justified to the leading edge of the container's
//      * orientation, for example, to the left in left-to-right orientations.
//      *
//      * @see     java.awt.Component#getComponentOrientation
//      * @see     java.awt.ComponentOrientation
//      * @since   1.2
//      * Package-private pending API change approval
//      */
//     public static final int LEADING = 3;

//     /** 
//      * This value indicates that each row of components
//      * should be justified to the trailing edge of the container's
//      * orientation, for example, to the right in left-to-right orientations.
//      *
//      * @see     java.awt.Component#getComponentOrientation
//      * @see     java.awt.ComponentOrientation
//      * @since   1.2
//      * Package-private pending API change approval
//      */
//     public static final int TRAILING = 4;
    // PBP/PP 6203783
    /** 
     * <code>align</code> is the property that determines
     * how each row distributes empty space.
     * It can be one of the following values:
     * <ul>
     * <code>LEFT</code>
     * <code>RIGHT</code>
     * <code>CENTER</code>
     * 
     * </ul>
     *
     * @serial
     * @see #getAlignment
     * @see #setAlignment
     */
     int align;

  // PBP/PP
  // [6246075]
//     /** 
//      * <code>newAlign</code> is the property that determines
//      * how each row distributes empty space for the Java 2 platform,
//      * v1.2 and greater.
//      * It can be one of the following three values:
//      * <ul>
//      * <code>LEFT</code>
//      * <code>RIGHT</code>
//      * <code>CENTER</code>
//      * 
//      * </ul>
//      *
//      * @serial
//      * @since 1.2
//      * @see #getAlignment
//      * @see #setAlignment
//      */
//      int newAlign;

    /** 
     * The flow layout manager allows a seperation of
     * components with gaps.  The horizontal gap will
     * specify the space between components.
     *
     * @serial
     * @see #getHgap()
     * @see #setHgap(int)
     */
     int hgap;

    /** 
     * The flow layout manager allows a seperation of
     * components with gaps.  The vertical gap will
     * specify the space between rows.
     *
     * @serial
     * @see #getHgap()
     * @see #setHgap(int)
     */
     int vgap;

    /*
     * JDK 1.1 serialVersionUID
     */
     private static final long serialVersionUID = -7262534875583282631L;

  // PBP/PP
  // [6246075]
//     /** 
//      * This represent the <code>currentSerialVersion</code>
//      * which is bein used.  It will be one of two values :
//      * <code>0</code> versions before Java 2 platform v1.2..
//      * <code>1</code> versions after  Java 2 platform v1.2..
//      *
//      * @serial
//      * @since 1.2
//      */
//     private int serialVersionOnStream;

    /** 
     * Constructs a new <code>FlowLayout</code> with a centered alignment and a
     * default 5-unit horizontal and vertical gap.
     */
    public FlowLayout() { }

    /** 
     * Constructs a new <code>FlowLayout</code> with the specified
     * alignment and a default 5-unit horizontal and vertical gap.
     * The value of the alignment argument must be one of
     * <code>FlowLayout.LEFT</code>, <code>FlowLayout.RIGHT</code>,
     * or <code>FlowLayout.CENTER</code>.
     * @param align the alignment value
     */
    public FlowLayout(int align) { }

    /** 
     * Creates a new flow layout manager with the indicated alignment
     * and the indicated horizontal and vertical gaps.
     * <p>
     * The value of the alignment argument must be one of
     * <code>FlowLayout.LEFT</code>, <code>FlowLayout.RIGHT</code>,
     * or <code>FlowLayout.CENTER</code>.
     * @param      align   the alignment value
     * @param      hgap    the horizontal gap between components
     * @param      vgap    the vertical gap between components
     */
    public FlowLayout(int align, int hgap, int vgap) { }
    // PBP/PP 6203783
    /** 
     * Gets the alignment for this layout.
     * Possible values are <code>FlowLayout.LEFT</code>,
     * <code>FlowLayout.RIGHT</code>, <code>FlowLayout.CENTER</code>,
     * 
     * @return     the alignment value for this layout
     * @see        java.awt.FlowLayout#setAlignment
     * @since      JDK1.1
     */
    public int getAlignment() { return 0; }
    // PBP/PP 6203783
    /** 
     * Sets the alignment for this layout.
     * Possible values are
     * <ul>
     * <li><code>FlowLayout.LEFT</code>
     * <li><code>FlowLayout.RIGHT</code>
     * <li><code>FlowLayout.CENTER</code>
     * 
     * </ul>
     * @param      align one of the alignment values shown above
     * @see        #getAlignment()
     * @since      JDK1.1
     */
    public void setAlignment(int align) { }

    /** 
     * Gets the horizontal gap between components.
     * @return     the horizontal gap between components
     * @see        java.awt.FlowLayout#setHgap
     * @since      JDK1.1
     */
    public int getHgap() { return 0; }

    /** 
     * Sets the horizontal gap between components.
     * @param hgap the horizontal gap between components
     * @see        java.awt.FlowLayout#getHgap
     * @since      JDK1.1
     */
    public void setHgap(int hgap) { }

    /** 
     * Gets the vertical gap between components.
     * @return     the vertical gap between components
     * @see        java.awt.FlowLayout#setVgap
     * @since      JDK1.1
     */
    public int getVgap() { return 0; }

    /** 
     * Sets the vertical gap between components.
     * @param vgap the vertical gap between components
     * @see        java.awt.FlowLayout#getVgap
     * @since      JDK1.1
     */
    public void setVgap(int vgap) { }

    /** 
     * Adds the specified component to the layout. Not used by this class.
     * @param name the name of the component
     * @param comp the component to be added
     */
    public void addLayoutComponent(String name, Component comp) { }

    /** 
     * Removes the specified component from the layout. Not used by
     * this class.
     * @param comp the component to remove
     * @see       java.awt.Container#removeAll
     */
    public void removeLayoutComponent(Component comp) { }

    /** 
     * Returns the preferred dimensions for this layout given the 
     * <i>visible</i> components in the specified target container.
     * @param target the component which needs to be laid out
     * @return    the preferred dimensions to lay out the
     *            subcomponents of the specified container
     * @see Container
     * @see #minimumLayoutSize
     * @see       java.awt.Container#getPreferredSize
     */
    public Dimension preferredLayoutSize(Container target) { return null; }

    /** 
     * Returns the minimum dimensions needed to layout the <i>visible</i>
     * components contained in the specified target container.
     * @param target the component which needs to be laid out
     * @return    the minimum dimensions to lay out the
     *            subcomponents of the specified container
     * @see #preferredLayoutSize
     * @see       java.awt.Container
     * @see       java.awt.Container#doLayout
     */
    public Dimension minimumLayoutSize(Container target) { return null; }

    /** 
     * Lays out the container. This method lets each component take
     * its preferred size by reshaping the components in the
     * target container in order to satisfy the alignment of
     * this <code>FlowLayout</code> object.
     * @param target the specified component being laid out
     * @see Container
     * @see       java.awt.Container#doLayout
     */
    public void layoutContainer(Container target) { }

    /** 
     * Returns a string representation of this <code>FlowLayout</code>
     * object and its values.
     * @return     a string representation of this layout
     */
    public String toString() { return null; }

//     /** 
//      * Reads this object out of a serialization stream, handling
//      * objects written by older versions of the class that didn't contain all
//      * of the fields we use now..
//      */
//     private void readObject(ObjectInputStream stream)
//         throws IOException, ClassNotFoundException
//     { }
}
