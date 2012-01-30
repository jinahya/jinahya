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

/** 
 * The <code>GridLayout</code> class is a layout manager that 
 * lays out a container's components in a rectangular grid. 
 * The container is divided into equal-sized rectangles, 
 * and one component is placed in each rectangle. 
 * For example, the following is an applet that lays out six buttons 
 * into three rows and two columns: 
 *
 * <em><p>Note: The following code example includes classes that do
 * not appear in this specification. Their inclusion is purely to
 * serve as a demonstration.</em>
 *
 * <p>
 * <hr><blockquote>
 * <pre>
 * import java.awt.*;
 * import java.applet.Applet;
 * public class ButtonGrid extends Applet {
 *     public void init() {
 *         setLayout(new GridLayout(3,2));
 *         add(new Button("1"));
 *         add(new Button("2"));
 *         add(new Button("3"));
 *         add(new Button("4"));
 *         add(new Button("5"));
 *         add(new Button("6"));
 *     }
 * }
 * </pre></blockquote><hr>     
 * <p>
 * It produces the following output:
 * <p>
 * <center><table COLS=2 WIDTH=600 summary="layout">
 * <tr ALIGN=CENTER>
 * <td><img SRC="doc-files/GridLayout-1.gif"
 *      alt="Shows 6 buttons in rows of 2. Row 1 shows buttons 1 then 2.
 * Row 2 shows buttons 3 then 4. Row 3 shows buttons 5 then 6.">
 * </td>
 * 
 * </tr>
 * <!-- PBP/PP 6187225 --!>
 * 
 * </table></center>
 * <p>
 * When both the number of rows and the number of columns have 
 * been set to non-zero values, either by a constructor or 
 * by the <tt>setRows</tt> and <tt>setColumns</tt> methods, the number of 
 * columns specified is ignored.  Instead, the number of 
 * columns is determined from the specified number or rows 
 * and the total number of components in the layout. So, for 
 * example, if three rows and two columns have been specified 
 * and nine components are added to the layout, they will 
 * be displayed as three rows of three columns.  Specifying 
 * the number of columns affects the layout only when the 
 * number of rows is set to zero.
 *
 * @version 1.35, 01/23/03
 * @author  Arthur van Hoff
 * @since   JDK1.0
 */
public class GridLayout implements LayoutManager, java.io.Serializable
{
    /** 
     * This is the horizontal gap (in pixels) which specifies the space
     * between columns.  They can be changed at any time.
     * This should be a non-negative integer.
     *
     * @serial
     * @see #getHgap()
     * @see #setHgap(int)
     */
     int hgap;

    /** 
     * This is the vertical gap (in pixels) which specifies the space
     * between rows.  They can be changed at any time.
     * This should be a non negative integer.
     *
     * @serial
     * @see #getVgap()
     * @see #setVgap(int)
     */
     int vgap;

    /** 
     * This is the number of rows specified for the grid.  The number
     * of rows can be changed at any time.
     * This should be a non negative integer, where '0' means
     * 'any number' meaning that the number of Rows in that
     * dimension depends on the other dimension.
     *
     * @serial
     * @see #getRows()
     * @see #setRows(int)
     */
     int rows;

    /** 
     * This is the number of columns specified for the grid.  The number
     * of columns can be changed at any time.
     * This should be a non negative integer, where '0' means
     * 'any number' meaning that the number of Columns in that
     * dimension depends on the other dimension.
     *
     * @serial
     * @see #getColumns()
     * @see #setColumns(int)
     */
     int cols;

    /** 
     * Creates a grid layout with a default of one column per component,
     * in a single row.
     * @since JDK1.1
     */
    public GridLayout() { }

    /** 
     * Creates a grid layout with the specified number of rows and 
     * columns. All components in the layout are given equal size. 
     * <p>
     * One, but not both, of <code>rows</code> and <code>cols</code> can 
     * be zero, which means that any number of objects can be placed in a 
     * row or in a column. 
     * @param     rows   the rows, with the value zero meaning 
     *                   any number of rows.
     * @param     cols   the columns, with the value zero meaning 
     *                   any number of columns.
     */
    public GridLayout(int rows, int cols) { }

    /** 
     * Creates a grid layout with the specified number of rows and 
     * columns. All components in the layout are given equal size. 
     * <p>
     * In addition, the horizontal and vertical gaps are set to the 
     * specified values. Horizontal gaps are placed at the left and 
     * right edges, and between each of the columns. Vertical gaps are 
     * placed at the top and bottom edges, and between each of the rows. 
     * <p>
     * One, but not both, of <code>rows</code> and <code>cols</code> can 
     * be zero, which means that any number of objects can be placed in a 
     * row or in a column. 
     * <p>
     * All <code>GridLayout</code> constructors defer to this one.
     * @param     rows   the rows, with the value zero meaning 
     *                   any number of rows
     * @param     cols   the columns, with the value zero meaning 
     *                   any number of columns
     * @param     hgap   the horizontal gap
     * @param     vgap   the vertical gap
     * @exception   IllegalArgumentException  if the value of both
     *			<code>rows</code> and <code>cols</code> is 
     *			set to zero
     */
    public GridLayout(int rows, int cols, int hgap, int vgap) { }

    /** 
     * Gets the number of rows in this layout.
     * @return    the number of rows in this layout
     * @since     JDK1.1
     */
    public int getRows() {return 0;  }

    /** 
     * Sets the number of rows in this layout to the specified value.
     * @param        rows   the number of rows in this layout
     * @exception    IllegalArgumentException  if the value of both 
     *               <code>rows</code> and <code>cols</code> is set to zero
     * @since        JDK1.1
     */
    public void setRows(int rows) { }

    /** 
     * Gets the number of columns in this layout.
     * @return     the number of columns in this layout
     * @since      JDK1.1
     */
    public int getColumns() {return 0;  }

    /** 
     * Sets the number of columns in this layout to the specified value. 
     * Setting the number of columns has no affect on the layout 
     * if the number of rows specified by a constructor or by 
     * the <tt>setRows</tt> method is non-zero. In that case, the number 
     * of columns displayed in the layout is determined by the total 
     * number of components and the number of rows specified.
     * @param        cols   the number of columns in this layout
     * @exception    IllegalArgumentException  if the value of both 
     *               <code>rows</code> and <code>cols</code> is set to zero
     * @since        JDK1.1
     */
    public void setColumns(int cols) { }

    /** 
     * Gets the horizontal gap between components.
     * @return       the horizontal gap between components
     * @since        JDK1.1
     */
    public int getHgap() {return 0;  }

    /** 
     * Sets the horizontal gap between components to the specified value.
     * @param        hgap   the horizontal gap between components
     * @since        JDK1.1
     */
    public void setHgap(int hgap) { }

    /** 
     * Gets the vertical gap between components.
     * @return       the vertical gap between components
     * @since        JDK1.1
     */
    public int getVgap() {return 0;  }

    /** 
     * Sets the vertical gap between components to the specified value.
     * @param         vgap  the vertical gap between components
     * @since        JDK1.1
     */
    public void setVgap(int vgap) { }

    /** 
     * Adds the specified component with the specified name to the layout.
     * @param name the name of the component
     * @param comp the component to be added
     */
    public void addLayoutComponent(String name, Component comp) { }

    /** 
     * Removes the specified component from the layout. 
     * @param comp the component to be removed
     */
    public void removeLayoutComponent(Component comp) { }

    /** 
     * Determines the preferred size of the container argument using 
     * this grid layout. 
     * <p>
     * The preferred width of a grid layout is the largest preferred 
     * width of any of the components in the container times the number of 
     * columns, plus the horizontal padding times the number of columns 
     * plus one, plus the left and right insets of the target container. 
     * <p>
     * The preferred height of a grid layout is the largest preferred 
     * height of any of the components in the container times the number of 
     * rows, plus the vertical padding times the number of rows plus one, 
     * plus the top and bottom insets of the target container. 
     * 
     * @param     parent   the container in which to do the layout
     * @return    the preferred dimensions to lay out the 
     *                      subcomponents of the specified container
     * @see       java.awt.GridLayout#minimumLayoutSize 
     * @see       java.awt.Container#getPreferredSize()
     */
    public Dimension preferredLayoutSize(Container parent) {return null;  }

    /** 
     * Determines the minimum size of the container argument using this 
     * grid layout. 
     * <p>
     * The minimum width of a grid layout is the largest minimum width 
     * of any of the components in the container times the number of columns, 
     * plus the horizontal padding times the number of columns plus one, 
     * plus the left and right insets of the target container. 
     * <p>
     * The minimum height of a grid layout is the largest minimum height 
     * of any of the components in the container times the number of rows, 
     * plus the vertical padding times the number of rows plus one, plus 
     * the top and bottom insets of the target container. 
     *  
     * @param       parent   the container in which to do the layout
     * @return      the minimum dimensions needed to lay out the 
     *                      subcomponents of the specified container
     * @see         java.awt.GridLayout#preferredLayoutSize
     * @see         java.awt.Container#doLayout
     */
    public Dimension minimumLayoutSize(Container parent) { return null; }

    /** 
     * Lays out the specified container using this layout. 
     * <p>
     * This method reshapes the components in the specified target 
     * container in order to satisfy the constraints of the 
     * <code>GridLayout</code> object. 
     * <p>
     * The grid layout manager determines the size of individual 
     * components by dividing the free space in the container into 
     * equal-sized portions according to the number of rows and columns 
     * in the layout. The container's free space equals the container's 
     * size minus any insets and any specified horizontal or vertical 
     * gap. All components in a grid layout are given the same size. 
     *  
     * @param      parent   the container in which to do the layout
     * @see        java.awt.Container
     * @see        java.awt.Container#doLayout
     */
    public void layoutContainer(Container parent) { }

    /** 
     * Returns the string representation of this grid layout's values.
     * @return     a string representation of this grid layout
     */
    public String toString() {return null;  }
}
