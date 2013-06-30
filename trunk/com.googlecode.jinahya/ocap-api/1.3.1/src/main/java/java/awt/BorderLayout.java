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

import java.util.Hashtable;

/** 
 * A border layout lays out a container, arranging and resizing
 * its components to fit in five regions:
 * north, south, east, west, and center.
 * Each region may contain no more than one component, and 
 * is identified by a corresponding constant:
 * <code>NORTH</code>, <code>SOUTH</code>, <code>EAST</code>,
 * <code>WEST</code>, and <code>CENTER</code>.  When adding a
 * component to a container with a border layout, use one of these
 * five constants, for example:
 *
 * <em><p>Note: The following code examples include classes that do
 * not appear in this specification. Their inclusion is purely to
 * serve as a demonstration.</em>
 * 
 * <pre>
 *    Panel p = new Panel();
 *    p.setLayout(new BorderLayout());
 *    p.add(new Button("Okay"), BorderLayout.SOUTH);
 * </pre>
 * As a convenience, <code>BorderLayout</code> interprets the
 * absence of a string specification the same as the constant
 * <code>CENTER</code>:
 * <pre>
 *    Panel p2 = new Panel();
 *    p2.setLayout(new BorderLayout());
 *    p2.add(new TextArea());  // Same as p.add(new TextArea(), BorderLayout.CENTER);
 * </pre>
<!-- PBP/PP -->

 * The components are laid out according to their
 * preferred sizes and the constraints of the container's size.
 * The <code>NORTH</code> and <code>SOUTH</code> components may
 * be stretched horizontally; the <code>EAST</code> and
 * <code>WEST</code> components may be stretched vertically;
 * the <code>CENTER</code> component may stretch both horizontally
 * and vertically to fill any space left over.
 * <p>
 * Here is an example of five buttons in an applet laid out using
 * the <code>BorderLayout</code> layout manager:
 * <p>
 * <img src="doc-files/BorderLayout-1.gif" 
 * alt="Diagram of an applet demonstrating BorderLayout. 
 *      Each section of the BorderLayout contains a Button corresponding to its position in the layout, one of: 
 *      North, West, Center, East, or South."
 * ALIGN=center HSPACE=10 VSPACE=7>
 * <p>
 * The code for this applet is as follows:
 * <p>
 * <hr><blockquote><pre>
 * import java.awt.*;
 * import java.applet.Applet;
 *
 * public class buttonDir extends Applet {
 *   public void init() {
 *     setLayout(new BorderLayout());
 *     add(new Button("North"), BorderLayout.NORTH);
 *     add(new Button("South"), BorderLayout.SOUTH);
 *     add(new Button("East"), BorderLayout.EAST);
 *     add(new Button("West"), BorderLayout.WEST);
 *     add(new Button("Center"), BorderLayout.CENTER);
 *   }
 * }
 * </pre></blockquote><hr>
 * <p>
 * @version 	1.53, 01/23/03
 * @author 	Arthur van Hoff
 * @see         java.awt.Container#add(String, Component)
<!-- PBP/PP -->

 * @since       JDK1.0
 */
public class BorderLayout implements LayoutManager2, java.io.Serializable
{
    /** 
     * The north layout constraint (top of container).
     */
    public static final String NORTH = "North";

    /** 
     * The south layout constraint (bottom of container).
     */
    public static final String SOUTH = "South";

    /** 
     * The east layout constraint (right side of container).
     */
    public static final String EAST = "East";

    /** 
     * The west layout constraint (left side of container).
     */
    public static final String WEST = "West";

    /** 
     * The center layout constraint (middle of container).
     */
    public static final String CENTER = "Center";

    // /** 
     // * Synonym for PAGE_START.  Exists for compatibility with previous
     // * versions.  PAGE_START is preferred.
     // *
     // * @see #PAGE_START
     // * @since 1.2
     // */
    // public static final String BEFORE_FIRST_LINE = null;
// 
    // /** 
     // * Synonym for PAGE_END.  Exists for compatibility with previous
     // * versions.  PAGE_END is preferred.
     // *
     // * @see #PAGE_END
     // * @since 1.2
     // */
    // public static final String AFTER_LAST_LINE = null;
// 
    // /** 
     // * Synonym for LINE_START.  Exists for compatibility with previous
     // * versions.  LINE_START is preferred.
     // *
     // * @see #LINE_START
     // * @since 1.2
     // */
    // public static final String BEFORE_LINE_BEGINS = null;
// 
    // /** 
     // * Synonym for LINE_END.  Exists for compatibility with previous
     // * versions.  LINE_END is preferred.
     // *
     // * @see #LINE_END
     // * @since 1.2
     // */
    // public static final String AFTER_LINE_ENDS = null;
// 
    // /** 
     // * The component comes before the first line of the layout's content.
     // * For Western, left-to-right and top-to-bottom orientations, this is
     // * equivalent to NORTH.
     // *
     // * @see java.awt.Component#getComponentOrientation
     // * @since 1.4
     // */
    // public static final String PAGE_START = null;
// 
    // /** 
     // * The component comes after the last line of the layout's content.
     // * For Western, left-to-right and top-to-bottom orientations, this is
     // * equivalent to SOUTH.
     // *
     // * @see java.awt.Component#getComponentOrientation
     // * @since 1.4
     // */
    // public static final String PAGE_END = null;
// 
    // /** 
     // * The component goes at the beginning of the line direction for the
     // * layout. For Western, left-to-right and top-to-bottom orientations,
     // * this is equivalent to WEST.
     // *
     // * @see java.awt.Component#getComponentOrientation
     // * @since 1.4
     // */
    // public static final String LINE_START = null;
// 
    // /** 
     // * The component goes at the end of the line direction for the
     // * layout. For Western, left-to-right and top-to-bottom orientations,
     // * this is equivalent to EAST.
     // *
     // * @see java.awt.Component#getComponentOrientation
     // * @since 1.4
     // */
    // public static final String LINE_END = null;

    /*
     * JDK 1.1 serialVersionUID
     */
     private static final long serialVersionUID = -8658291919501921765L;

    /** 
     * Constructs a border layout with the horizontal gaps
     * between components.
     * The horizontal gap is specified by <code>hgap</code>.
     *
     * @see #getHgap()
     * @see #setHgap(int)
     *
     * @serial
     */
     int hgap;

    /** 
     * Constructs a border layout with the vertical gaps
     * between components.
     * The vertical gap is specified by <code>vgap</code>.
     *
     * @see #getVgap()
     * @see #setVgap(int)
     * @serial
     */
     int vgap;

    /** 
     * Constant to specify components location to be the
     *      north portion of the border layout.
     * @serial
     * @see #getChild(String, boolean)
     * @see #addLayoutComponent
     * @see #getLayoutAlignmentX
     * @see #getLayoutAlignmentY
     * @see #removeLayoutComponent
     */
     Component north;

    /** 
     * Constant to specify components location to be the
     *      west portion of the border layout.
     * @serial
     * @see #getChild(String, boolean)
     * @see #addLayoutComponent
     * @see #getLayoutAlignmentX
     * @see #getLayoutAlignmentY
     * @see #removeLayoutComponent
     */
     Component west;

    /** 
     * Constant to specify components location to be the
     *      east portion of the border layout.
     * @serial
     * @see #getChild(String, boolean)
     * @see #addLayoutComponent
     * @see #getLayoutAlignmentX
     * @see #getLayoutAlignmentY
     * @see #removeLayoutComponent
     */
     Component east;

    /** 
     * Constant to specify components location to be the
     *      south portion of the border layout.
     * @serial
     * @see #getChild(String, boolean)
     * @see #addLayoutComponent
     * @see #getLayoutAlignmentX
     * @see #getLayoutAlignmentY
     * @see #removeLayoutComponent
     */
     Component south;

    /** 
     * Constant to specify components location to be the
     *      center portion of the border layout.
     * @serial
     * @see #getChild(String, boolean)
     * @see #addLayoutComponent
     * @see #getLayoutAlignmentX
     * @see #getLayoutAlignmentY
     * @see #removeLayoutComponent
     */
     Component center;

    // PBP/PP 6203083
    /** 
     * A relative positioning constant, that can be used instead of
     * north, south, east, west or center.
     * mixing the two types of constants can lead to unpredicable results.  If
     * you use both types, the relative constants will take precedence.
     * For example, if you add components using both the <code>NORTH</code>
     * and <code>BEFORE_FIRST_LINE</code> constants in a container whose
     * orientation is <code>LEFT_TO_RIGHT</code>, only the
     * <code>BEFORE_FIRST_LINE</code> will be layed out.
     * This will be the same for lastLine, firstItem, lastItem.
     * @serial
     */
    // Component firstLine;

   // PBP/PP 6203083
    /** 
     * A relative positioning constant, that can be used instead of
     * north, south, east, west or center.
     * Please read Description for firstLine.
     * @serial
     */
    // Component lastLine;
   // PBP/PP 6203083
    /** 
     * A relative positioning constant, that can be used instead of
     * north, south, east, west or center.
     * Please read Description for firstLine.
     * @serial
     */
    // Component firstItem;
       // PBP/PP 6203083
    /** 
     * A relative positioning constant, that can be used instead of
     * north, south, east, west or center.
     * Please read Description for firstLine.
     * @serial
     */
    // Component lastItem;

    /** 
     * Constructs a new border layout with
     * no gaps between components.
     */
    public BorderLayout() { }

    /** 
     * Constructs a border layout with the specified gaps
     * between components.
     * The horizontal gap is specified by <code>hgap</code>
     * and the vertical gap is specified by <code>vgap</code>.
     * @param   hgap   the horizontal gap.
     * @param   vgap   the vertical gap.
     */
    public BorderLayout(int hgap, int vgap) { }

    /** 
     * Returns the horizontal gap between components.
     * @since   JDK1.1
     */
    public int getHgap() { return 0; }

    /** 
     * Sets the horizontal gap between components.
     * @param hgap the horizontal gap between components
     * @since   JDK1.1
     */
    public void setHgap(int hgap) { }

    /** 
     * Returns the vertical gap between components.
     * @since   JDK1.1
     */
    public int getVgap() { return 0; }

    /** 
     * Sets the vertical gap between components.
     * @param vgap the vertical gap between components
     * @since   JDK1.1
     */
    public void setVgap(int vgap) { }

    /** 
     * Adds the specified component to the layout, using the specified
     * constraint object.  For border layouts, the constraint must be
     * one of the following constants:  <code>NORTH</code>,
     * <code>SOUTH</code>, <code>EAST</code>,
     * <code>WEST</code>, or <code>CENTER</code>.
     * <p>
     * Most applications do not call this method directly. This method
     * is called when a component is added to a container using the
     * <code>Container.add</code> method with the same argument types.
     * @param   comp         the component to be added.
     * @param   constraints  an object that specifies how and where
     *                       the component is added to the layout.
     * @see     java.awt.Container#add(java.awt.Component, java.lang.Object)
     * @exception   IllegalArgumentException  if the constraint object is not
     *                 a string, or if it not one of the five specified
     *              constants.
     * @since   JDK1.1
     */
    public void addLayoutComponent(Component comp, Object constraints) { }

    /** 
     * @deprecated  replaced by <code>addLayoutComponent(Component, Object)</code>.
     */
    public void addLayoutComponent(String name, Component comp) { }

    /** 
     * Removes the specified component from this border layout. This
     * method is called when a container calls its <code>remove</code> or
     * <code>removeAll</code> methods. Most applications do not call this
     * method directly.
     * @param   comp   the component to be removed.
     * @see     java.awt.Container#remove(java.awt.Component)
     * @see     java.awt.Container#removeAll()
     */
    public void removeLayoutComponent(Component comp) { }

    /** 
     * Determines the minimum size of the <code>target</code> container
     * using this layout manager.
     * <p>
     * This method is called when a container calls its
     * <code>getMinimumSize</code> method. Most applications do not call
     * this method directly.
     * @param   target   the container in which to do the layout.
     * @return  the minimum dimensions needed to lay out the subcomponents
     *          of the specified container.
     * @see     java.awt.Container
     * @see     java.awt.BorderLayout#preferredLayoutSize
     * @see     java.awt.Container#getMinimumSize()
     */
    public Dimension minimumLayoutSize(Container target) { return null; }

    /** 
     * Determines the preferred size of the <code>target</code>
     * container using this layout manager, based on the components
     * in the container.
     * <p>
     * Most applications do not call this method directly. This method
     * is called when a container calls its <code>getPreferredSize</code>
     * method.
     * @param   target   the container in which to do the layout.
     * @return  the preferred dimensions to lay out the subcomponents
     *          of the specified container.
     * @see     java.awt.Container
     * @see     java.awt.BorderLayout#minimumLayoutSize
     * @see     java.awt.Container#getPreferredSize()
     */
    public Dimension preferredLayoutSize(Container target) { return null; }

    /** 
     * Returns the maximum dimensions for this layout given the components
     * in the specified target container.
     * @param target the component which needs to be laid out
     * @see Container
     * @see #minimumLayoutSize
     * @see #preferredLayoutSize
     */
    public Dimension maximumLayoutSize(Container target) { return null; }

    /** 
     * Returns the alignment along the x axis.  This specifies how
     * the component would like to be aligned relative to other
     * components.  The value should be a number between 0 and 1
     * where 0 represents alignment along the origin, 1 is aligned
     * the furthest away from the origin, 0.5 is centered, etc.
     */
    public float getLayoutAlignmentX(Container parent) { return 0; }

    /** 
     * Returns the alignment along the y axis.  This specifies how
     * the component would like to be aligned relative to other
     * components.  The value should be a number between 0 and 1
     * where 0 represents alignment along the origin, 1 is aligned
     * the furthest away from the origin, 0.5 is centered, etc.
     */
    public float getLayoutAlignmentY(Container parent) { return 0; }

    /** 
     * Invalidates the layout, indicating that if the layout manager
     * has cached information it should be discarded.
     */
    public void invalidateLayout(Container target) {  }

    /** 
     * Lays out the container argument using this border layout.
     * <p>
     * This method actually reshapes the components in the specified
     * container in order to satisfy the constraints of this
     * <code>BorderLayout</code> object. The <code>NORTH</code>
     * and <code>SOUTH</code> components, if any, are placed at
     * the top and bottom of the container, respectively. The
     * <code>WEST</code> and <code>EAST</code> components are
     * then placed on the left and right, respectively. Finally,
     * the <code>CENTER</code> object is placed in any remaining
     * space in the middle.
     * <p>
     * Most applications do not call this method directly. This method
     * is called when a container calls its <code>doLayout</code> method.
     * @param   target   the container in which to do the layout.
     * @see     java.awt.Container
     * @see     java.awt.Container#doLayout()
     */
    public void layoutContainer(Container target) { }

    /** 
     * Returns a string representation of the state of this border layout.
     * @return    a string representation of this border layout.
     */
    public String toString() { return null; }
}
