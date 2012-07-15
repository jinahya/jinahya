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
import java.util.Vector;

/** 
 * The <code>GridBagLayout</code> class is a flexible layout
 * manager that aligns components vertically and horizontally,
 * without requiring that the components be of the same size.
 * Each <code>GridBagLayout</code> object maintains a dynamic,
 * rectangular grid of cells, with each component occupying
 * one or more cells, called its <em>display area</em>.
 * <p>
 * Each component managed by a <code>GridBagLayout</code> is associated with
 * an instance of {@link GridBagConstraints}.  The constraints object
 * specifies where a component's display area should be located on the grid
 * and how the component should be positioned within its display area.  In
 * addition to its constraints object, the <code>GridBagLayout</code> also
 * considers each component's minimum and preferred sizes in order to
 * determine a component's size.
 * <p>
 * <!-- PBP/PP -->
 * <!-- 6187232 -->
 *  Grid coordinate (0,0) is in the upper left corner of the
 * container with x increasing to the right and y increasing downward. 
 * <p>
 * To use a grid bag layout effectively, you must customize one or more
 * of the <code>GridBagConstraints</code> objects that are associated
 * with its components. You customize a <code>GridBagConstraints</code>
 * object by setting one or more of its instance variables:
 * <p>
 * <!-- PBP/PP -->
 * <!-- 6187232 -->
 * <dl>
 * <dt>{@link GridBagConstraints#gridx},
 * {@link GridBagConstraints#gridy}
 * <dd>Specifies the cell  <em>at the upper left</em> of the component's 
 * display area, where the cell at the origin of the grid has address
 * <code>gridx&nbsp;=&nbsp;0</code>,
 * <code>gridy&nbsp;=&nbsp;0</code>.  
 * Use <code>GridBagConstraints.RELATIVE</code> (the default value)
 * to specify that the component be placed immediately following
 * (along the x axis for <code>gridx</code> or the y axis for 
 * <code>gridy</code>) the component that was added to the container
 * just before this component was added.
 * <dt>{@link GridBagConstraints#gridwidth},
 * {@link GridBagConstraints#gridheight}
 * <dd>Specifies the number of cells in a row (for <code>gridwidth</code>)
 * or column (for <code>gridheight</code>)
 * in the component's display area.
 * The default value is 1.
 * Use <code>GridBagConstraints.REMAINDER</code> to specify
 * that the component be the last one in its row (for <code>gridwidth</code>)
 * or column (for <code>gridheight</code>).
 * Use <code>GridBagConstraints.RELATIVE</code> to specify
 * that the component be the next to last one
 * in its row (for <code>gridwidth</code>)
 * or column (for <code>gridheight</code>).
 * <dt>{@link GridBagConstraints#fill}
 * <dd>Used when the component's display area
 * is larger than the component's requested size
 * to determine whether (and how) to resize the component.
 * Possible values are
 * <code>GridBagConstraints.NONE</code> (the default),
 * <code>GridBagConstraints.HORIZONTAL</code>
 * (make the component wide enough to fill its display area
 * horizontally, but don't change its height),
 * <code>GridBagConstraints.VERTICAL</code>
 * (make the component tall enough to fill its display area
 * vertically, but don't change its width), and
 * <code>GridBagConstraints.BOTH</code>
 * (make the component fill its display area entirely).
 * <dt>{@link GridBagConstraints#ipadx},
 * {@link GridBagConstraints#ipady}
 * <dd>Specifies the component's internal padding within the layout,
 * how much to add to the minimum size of the component.
 * The width of the component will be at least its minimum width
 * plus <code>(ipadx&nbsp;*&nbsp;2)</code> pixels (since the padding
 * applies to both sides of the component). Similarly, the height of
 * the component will be at least the minimum height plus
 * <code>(ipady&nbsp;*&nbsp;2)</code> pixels.
 * <dt>{@link GridBagConstraints#insets}
 * <dd>Specifies the component's external padding, the minimum
 * amount of space between the component and the edges of its display area.
 *
 * <!-- PBP/PP-->
 * <dt>{@link GridBagConstraints#anchor}
 * <dd>Used when the component is smaller than its display area
 * to determine where (within the display area) to place the component.
 *  
 * <p>
 * Valid values are:</dd>
 * <p>
 * <center><table BORDER=0 COLS=2 WIDTH=800 SUMMARY="absolute and relative values as described above">
 * <!- PBP/PP 6187232 ->
 * 
 * <tr>
 * <td>
 * <li><code>GridBagConstraints.NORTH</code></li>
 * <li><code>GridBagConstraints.SOUTH</code></li>
 * <li><code>GridBagConstraints.WEST</code></li>
 * <li><code>GridBagConstraints.EAST</code></li>
 * <li><code>GridBagConstraints.NORTHWEST</code></li>
 * <li><code>GridBagConstraints.NORTHEAST</code></li>
 * <li><code>GridBagConstraints.SOUTHWEST</code></li>
 * <li><code>GridBagConstraints.SOUTHEAST</code></li>
 * <li><code>GridBagConstraints.CENTER</code> (the default)</li>
 * </td>
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * </tr>
 * </table></center><p>
 * <dt>{@link GridBagConstraints#weightx},
 * {@link GridBagConstraints#weighty}
 * <dd>Used to determine how to distribute space, which is
 * important for specifying resizing behavior.
 * Unless you specify a weight for at least one component
 * in a row (<code>weightx</code>) and column (<code>weighty</code>),
 * all the components clump together in the center of their container.
 * This is because when the weight is zero (the default),
 * the <code>GridBagLayout</code> object puts any extra space
 * between its grid of cells and the edges of the container.
 * </dl>
 * <p>
 * <!-- PBP/PP 6187225 -->
 * 
 *
 *  The following figure shows ten components (all buttons) managed by a grid bag layout:
 * <p>
 * <center><table COLS=2 WIDTH=600 summary="layout">
 * <tr ALIGN=CENTER>
 * <td>
 * <img src="doc-files/GridBagLayout-1.gif" alt="The preceeding text describes this graphic (Figure 1)." ALIGN=center HSPACE=10 VSPACE=7>
 * </td>
 *
 * 
 * </table></center>
 * <p>
 * Each of the ten components has the <code>fill</code> field
 * of its associated <code>GridBagConstraints</code> object
 * set to <code>GridBagConstraints.BOTH</code>.
 * In addition, the components have the following non-default constraints:
 * <p>
 * <ul>
 * <li>Button1, Button2, Button3: <code>weightx&nbsp;=&nbsp;1.0</code>
 * <li>Button4: <code>weightx&nbsp;=&nbsp;1.0</code>,
 * <code>gridwidth&nbsp;=&nbsp;GridBagConstraints.REMAINDER</code>
 * <li>Button5: <code>gridwidth&nbsp;=&nbsp;GridBagConstraints.REMAINDER</code>
 * <li>Button6: <code>gridwidth&nbsp;=&nbsp;GridBagConstraints.RELATIVE</code>
 * <li>Button7: <code>gridwidth&nbsp;=&nbsp;GridBagConstraints.REMAINDER</code>
 * <li>Button8: <code>gridheight&nbsp;=&nbsp;2</code>,
 * <code>weighty&nbsp;=&nbsp;1.0</code>
 * <li>Button9, Button 10:
 * <code>gridwidth&nbsp;=&nbsp;GridBagConstraints.REMAINDER</code>
 * </ul>
 * <p>
 *
 * <em><p>Note: The following code example includes classes that do
 * not appear in this specification. Their inclusion is purely to
 * serve as a demonstration.<p></em>
 *
 *
 * Here is the code that implements the example shown above:
 * <p>
 * <hr><blockquote><pre>
 * import java.awt.*;
 * import java.util.*;
 * import java.applet.Applet;
 *
 * public class GridBagEx1 extends Applet {
 *
 *     protected void makebutton(String name,
 *                               GridBagLayout gridbag,
 *                               GridBagConstraints c) {
 *         Button button = new Button(name);
 *         gridbag.setConstraints(button, c);
 *         add(button);
 *     }
 *
 *     public void init() {
 *         GridBagLayout gridbag = new GridBagLayout();
 *         GridBagConstraints c = new GridBagConstraints();
 *
 *         setFont(new Font("Helvetica", Font.PLAIN, 14));
 *         setLayout(gridbag);
 *
 *         c.fill = GridBagConstraints.BOTH;
 *         c.weightx = 1.0;
 *         makebutton("Button1", gridbag, c);
 *         makebutton("Button2", gridbag, c);
 *         makebutton("Button3", gridbag, c);
 *
 *     	   c.gridwidth = GridBagConstraints.REMAINDER; //end row
 *         makebutton("Button4", gridbag, c);
 *
 *         c.weightx = 0.0;		   //reset to the default
 *         makebutton("Button5", gridbag, c); //another row
 *
 * 	   c.gridwidth = GridBagConstraints.RELATIVE; //next-to-last in row
 *         makebutton("Button6", gridbag, c);
 *
 * 	   c.gridwidth = GridBagConstraints.REMAINDER; //end row
 *         makebutton("Button7", gridbag, c);
 *
 * 	   c.gridwidth = 1;	   	   //reset to the default
 * 	   c.gridheight = 2;
 *         c.weighty = 1.0;
 *         makebutton("Button8", gridbag, c);
 *
 *         c.weighty = 0.0;		   //reset to the default
 * 	   c.gridwidth = GridBagConstraints.REMAINDER; //end row
 * 	   c.gridheight = 1;		   //reset to the default
 *         makebutton("Button9", gridbag, c);
 *         makebutton("Button10", gridbag, c);
 *
 *         setSize(300, 100);
 *     }
 *
 *     public static void main(String args[]) {
 * 	   Frame f = new Frame("GridBag Layout Example");
 * 	   GridBagEx1 ex1 = new GridBagEx1();
 *
 * 	   ex1.init();
 *
 * 	   f.add("Center", ex1);
 * 	   f.pack();
 * 	   f.setSize(f.getPreferredSize());
 * 	   f.show();
 *     }
 * }
 * </pre></blockquote><hr>
 * <p>
 * @version 1.5, 16 Nov 1995
 * @author Doug Stein
 * @see       java.awt.GridBagConstraints
 * @since JDK1.0 
 */
// PBP/PP: 6187232
// * @see       java.awt.ComponentOrientation


public class GridBagLayout implements LayoutManager2, java.io.Serializable
{
    /** 
     * The maximum number of grid positions (both horizontally and
     * vertically) that can be laid out by the grid bag layout.
     */
    protected static final int MAXGRIDSIZE = 512;

    /** 
     * The smallest grid that can be laid out by the grid bag layout.
     */
    protected static final int MINSIZE = 1;

    /** 
     * The preferred grid size that can be laid out by the grid bag layout.
     */
    protected static final int PREFERREDSIZE = 2;

    /** 
     * This hashtable maintains the association between
     * a component and its gridbag constraints.
     * The Keys in <code>comptable</code> are the components and the
     * values are the instances of <code>GridBagConstraints</code>.
     *
     * @serial
     * @see java.awt.GridBagConstraints
     */
    protected Hashtable comptable;

    /** 
     * This field holds a gridbag constraints instance
     * containing the default values, so if a component
     * does not have gridbag constraints associated with
     * it, then the component will be assigned a
     * copy of the <code>defaultConstraints</code>.
     *
     * @serial
     * @see #getConstraints(Component)
     * @see #setConstraints(Component, GridBagConstraints)
     * @see #lookupConstraints(Component)
     */
    protected GridBagConstraints defaultConstraints;

    // /** 
     // * This field holds the layout information
     // * for the gridbag.  The information in this field
     // * is based on the most recent validation of the
     // * gridbag.
     // * If <code>layoutInfo</code> is <code>null</code>
     // * this indicates that there are no components in
     // * the gridbag or if there are components, they have
     // * not yet been validated.
     // *
     // * @serial
     // * @see #getLayoutInfo(Container, int)
     // */
    // protected GridBagLayoutInfo layoutInfo;

    /** 
     * This field holds the overrides to the column minimum
     * width.  If this field is non-<code>null</code> the values are
     * applied to the gridbag after all of the minimum columns
     * widths have been calculated.
     * If columnWidths has more elements than the number of
     * columns, columns are added to the gridbag to match
     * the number of elements in columnWidth.
     *
     * @serial
     * @see #getLayoutDimensions()
     */
    public int[] columnWidths;

    /** 
     * This field holds the overrides to the row minimum
     * heights.  If this field is non-</code>null</code> the values are
     * applied to the gridbag after all of the minimum row
     * heights have been calculated.
     * If <code>rowHeights</code> has more elements than the number of
     * rows, rowa are added to the gridbag to match
     * the number of elements in <code>rowHeights</code>.
     *
     * @serial
     * @see #getLayoutDimensions()
     */
    public int[] rowHeights;

    /** 
     * This field holds the overrides to the column weights.
     * If this field is non-<code>null</code> the values are
     * applied to the gridbag after all of the columns
     * weights have been calculated.
     * If <code>columnWeights[i]</code> &gt; weight for column i, then
     * column i is assigned the weight in <code>columnWeights[i]</code>.
     * If <code>columnWeights</code> has more elements than the number
     * of columns, the excess elements are ignored - they do
     * not cause more columns to be created.
     *
     * @serial
     */
    public double[] columnWeights;

    /** 
     * This field holds the overrides to the row weights.
     * If this field is non-</code>null</code> the values are
     * applied to the gridbag after all of the rows
     * weights have been calculated.
     * If <code>rowWeights[i]</code> &gt; weight for row i, then
     * row i is assigned the weight in <code>rowWeights[i]</code>.
     * If <code>rowWeights</code> has more elements than the number
     * of rows, the excess elements are ignored - they do
     * not cause more rows to be created.
     *
     * @serial
     */
    public double[] rowWeights;

    /** 
     * Creates a grid bag layout manager.
     */
    public GridBagLayout() { }

    /** 
     * Sets the constraints for the specified component in this layout.
     * @param       comp the component to be modified
     * @param       constraints the constraints to be applied
     */
    public void setConstraints(Component comp, GridBagConstraints constraints)
    { }

    /** 
     * Gets the constraints for the specified component.  A copy of
     * the actual <code>GridBagConstraints</code> object is returned.
     * @param       comp the component to be queried
     * @return      the constraint for the specified component in this
     *                  grid bag layout; a copy of the actual constraint
     *                  object is returned
     */
    public GridBagConstraints getConstraints(Component comp) { return null; }

    /** 
     * Retrieves the constraints for the specified component.
     * The return value is not a copy, but is the actual
     * <code>GridBagConstraints</code> object used by the layout mechanism.
     * @param       comp the component to be queried
     * @return      the contraints for the specified component
     */
    protected GridBagConstraints lookupConstraints(Component comp) { return null; }

    // PBP/PP
    /** 
     * Determines the origin of the layout area, in the graphics coordinate 
     * space of the target container.  This 
     * is distinct from the grid origin given by the cell coordinates (0,0).
     * Most applications do not call this method directly.
     * @return     the graphics origin of the cell in the top-left
     *             corner of the layout grid
     * @since      JDK1.1
     */
    //     * @see        java.awt.ComponentOrientation
    public Point getLayoutOrigin() { return null; }

    /** 
     * Determines column widths and row heights for the layout grid.
     * <p>
     * Most applications do not call this method directly.
     * @return     an array of two arrays, containing the widths
     *                       of the layout columns and
     *                       the heights of the layout rows
     * @since      JDK1.1
     */
    public int[][] getLayoutDimensions() { return null; }

    /** 
     * Determines the weights of the layout grid's columns and rows.
     * Weights are used to calculate how much a given column or row
     * stretches beyond its preferred size, if the layout has extra
     * room to fill.
     * <p>
     * Most applications do not call this method directly.
     * @return      an array of two arrays, representing the
     *                    horizontal weights of the layout columns
     *                    and the vertical weights of the layout rows
     * @since       JDK1.1
     */
    public double[][] getLayoutWeights() { return null; }
    // PBP/PP 6187232
    /** 
     * Determines which cell in the layout grid contains the point
     * specified by <code>(x,&nbsp;y)</code>. Each cell is identified
     * by its column index (ranging from 0 to the number of columns
     * minus 1) and its row index (ranging from 0 to the number of
     * rows minus 1).
     * <p>
     * If the <code>(x,&nbsp;y)</code> point lies
     * outside the grid, the following rules are used.
     * The column index is returned as zero if <code>x</code> lies to the
     * left of the layout, and as the number of columns if <code>x</code> lies
     * to the right of the layout. The row index is returned as zero
     * if <code>y</code> lies above the layout,
     * and as the number of rows if <code>y</code> lies
     * below the layout.
     * @param      x    the <i>x</i> coordinate of a point
     * @param      y    the <i>y</i> coordinate of a point
     * @return     an ordered pair of indexes that indicate which cell
     *             in the layout grid contains the point
     *             (<i>x</i>,&nbsp;<i>y</i>).
     * @since      JDK1.1
     */
    //     * @see        java.awt.ComponentOrientation
    public Point location(int x, int y) {return null;  }

    /** 
     * Adds the specified component with the specified name to the layout.
     * @param      name         the name of the component
     * @param      comp         the component to be added
     */
    public void addLayoutComponent(String name, Component comp) { }

    /** 
     * Adds the specified component to the layout, using the specified
     * <code>constraints</code> object.  Note that constraints
     * are mutable and are, therefore, cloned when cached.
     *
     * @param      comp         the component to be added
     * @param      constraints  an object that determines how
     *                          the component is added to the layout
     * @exception IllegalArgumentException if <code>constraints</code>
     *		  is not a <code>GridBagConstraint</code>
     */
    public void addLayoutComponent(Component comp, Object constraints) { }

    /** 
     * Removes the specified component from this layout.
     * <p>
     * Most applications do not call this method directly.
     * @param    comp   the component to be removed.
     * @see      java.awt.Container#remove(java.awt.Component)
     * @see      java.awt.Container#removeAll()
     */
    public void removeLayoutComponent(Component comp) { }

    /** 
     * Determines the preferred size of the <code>parent</code>
     * container using this grid bag layout.
     * <p>
     * Most applications do not call this method directly.
     *
     * @param     parent   the container in which to do the layout
     * @see       java.awt.Container#getPreferredSize
     * @return the preferred size of the <code>parent</code>
     *  container
     */
    public Dimension preferredLayoutSize(Container parent) { return null; }

    /** 
     * Determines the minimum size of the <code>parent</code> container
     * using this grid bag layout.
     * <p>
     * Most applications do not call this method directly.
     * @param     parent   the container in which to do the layout
     * @see       java.awt.Container#doLayout
     * @return the minimum size of the <code>parent</code> container
     */
    public Dimension minimumLayoutSize(Container parent) { return null; }

    /** 
     * Returns the maximum dimensions for this layout given the components
     * in the specified target container.
     * @param target the container which needs to be laid out
     * @see Container
     * @see #minimumLayoutSize(Container)
     * @see #preferredLayoutSize(Container)
     * @return the maximum dimensions for this layout
     */
    public Dimension maximumLayoutSize(Container target) { return null; }

    /** 
     * Returns the alignment along the x axis.  This specifies how
     * the component would like to be aligned relative to other
     * components.  The value should be a number between 0 and 1
     * where 0 represents alignment along the origin, 1 is aligned
     * the furthest away from the origin, 0.5 is centered, etc.
     * <p>
     * @return the value <code>0.5f</code> to indicate centered
     */
    public float getLayoutAlignmentX(Container parent) { return 0; }

    /** 
     * Returns the alignment along the y axis.  This specifies how
     * the component would like to be aligned relative to other
     * components.  The value should be a number between 0 and 1
     * where 0 represents alignment along the origin, 1 is aligned
     * the furthest away from the origin, 0.5 is centered, etc.
     * <p>
     * @return the value <code>0.5f</code> to indicate centered
     */
    public float getLayoutAlignmentY(Container parent) { return 0; }

    /** 
     * Invalidates the layout, indicating that if the layout manager
     * has cached information it should be discarded.
     */
    public void invalidateLayout(Container target) { }

    /** 
     * Lays out the specified container using this grid bag layout.
     * This method reshapes components in the specified container in
     * order to satisfy the contraints of this <code>GridBagLayout</code>
     * object.
     * <p>
     * Most applications do not call this method directly.
     * @param parent the container in which to do the layout
     * @see java.awt.Container
     * @see java.awt.Container#doLayout
     */
    public void layoutContainer(Container parent) { }

    /** 
     * Returns a string representation of this grid bag layout's values.
     * @return     a string representation of this grid bag layout.
     */
    public String toString() { return null; }

    // /** 
     // * Fills in an instance of <code>GridBagLayoutInfo</code> for the
     // * current set of managed children. This requires three passes through the
     // * set of children:
     // *
     // * <ol>
     // * <li>Figure out the dimensions of the layout grid.
     // * <li>Determine which cells the components occupy.
     // * <li>Distribute the weights and min sizes amoung the rows/columns.
     // * </ol>
     // *
     // * This also caches the minsizes for all the children when they are
     // * first encountered (so subsequent loops don't need to ask again).
     // * @param parent  the layout container 
     // * @param sizeflag either <code>PREFERREDSIZE</code> or
     // *   <code>MINSIZE</code>
     // * @return the <code>GridBagLayoutInfo</code> for the set of children
     // * @since 1.4
     // */
    // protected GridBagLayoutInfo getLayoutInfo(Container parent, int sizeflag)
    // { }
// 
    // /** 
     // * This method is obsolete and supplied for backwards
     // * compatability only; new code should call {@link
     // * #getLayoutInfo(Container, int) getLayoutInfo} instead.
     // */
    // protected GridBagLayoutInfo GetLayoutInfo(Container parent, int sizeflag)
    // { }

     /** 
      * Adjusts the x, y, width, and height fields to the correct
      * values depending on the constraint geometry and pads.
      * @param constraints the constraints to be applied
      * @param r the <code>Rectangle</code> to be adjusted
      * @since 1.4
      */
     protected void adjustForGravity(GridBagConstraints constraints, Rectangle r)
    { }

    /** 
     * This method is obsolete and supplied for backwards
     * compatability only; new code should call {@link
     * #adjustForGravity(GridBagConstraints, Rectangle)
     * adjustForGravity} instead.
     */
    protected void AdjustForGravity(GridBagConstraints constraints, Rectangle r)
    { }

    // /** 
     // * Figures out the minimum size of the
     // * master based on the information from getLayoutInfo().
     // * @param parent the layout container 
     // * @param info the layout info for this parent
     // * @return a <code>Dimension</code> object containing the
     // *   minimum size
     // * @since 1.4
     // */
    // protected Dimension getMinSize(Container parent, GridBagLayoutInfo info) { }
// 
    // /** 
     // * This method is obsolete and supplied for backwards
     // * compatability only; new code should call {@link
     // * #getMinSize(Container, GridBagLayoutInfo) getMinSize} instead.
     // */
    // protected Dimension GetMinSize(Container parent, GridBagLayoutInfo info) { }
// 
     /** 
      * Lays out the grid.
      * @param parent the layout container
      * @since 1.4
      */
     protected void arrangeGrid(Container parent) { }

    /** 
     * This method is obsolete and supplied for backwards
     * compatability only; new code should call {@link
     * #arrangeGrid(Container) arrangeGrid} instead.
     */
    protected void ArrangeGrid(Container parent) { }

   // Added for serial backwards compatability (4348425)
   static final long serialVersionUID = 8838754796412211005L;
}
