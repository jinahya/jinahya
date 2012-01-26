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
 * The <code>GridBagConstraints</code> class specifies constraints 
 * for components that are laid out using the 
 * <code>GridBagLayout</code> class.
 *
 * @version 	1.31, 01/23/03
 * @author Doug Stein
 * @see java.awt.GridBagLayout
 * @since JDK1.0
 */
public class GridBagConstraints implements Cloneable, java.io.Serializable
{
    /** 
     * Specifies that this component is the next-to-last component in its 
     * column or row (<code>gridwidth</code>, <code>gridheight</code>), 
     * or that this component be placed next to the previously added 
     * component (<code>gridx</code>, <code>gridy</code>). 
     * @see      java.awt.GridBagConstraints#gridwidth
     * @see      java.awt.GridBagConstraints#gridheight
     * @see      java.awt.GridBagConstraints#gridx
     * @see      java.awt.GridBagConstraints#gridy
     */
    public static final int RELATIVE = -1;

    /** 
     * Specifies that this component is the 
     * last component in its column or row. 
     */
    public static final int REMAINDER = 0;

    /** 
     * Do not resize the component. 
     */
    public static final int NONE = 0;

    /** 
     * Resize the component both horizontally and vertically. 
     */
    public static final int BOTH = 1;

    /** 
     * Resize the component horizontally but not vertically. 
     */
    public static final int HORIZONTAL = 2;

    /** 
     * Resize the component vertically but not horizontally. 
     */
    public static final int VERTICAL = 3;

    /** 
     * Put the component in the center of its display area.
     */
    public static final int CENTER = 10;

    /** 
     * Put the component at the top of its display area,
     * centered horizontally. 
     */
    public static final int NORTH = 11;

    /** 
     * Put the component at the top-right corner of its display area. 
     */
    public static final int NORTHEAST = 12;

    /** 
     * Put the component on the right side of its display area, 
     * centered vertically.
     */
    public static final int EAST = 13;

    /** 
     * Put the component at the bottom-right corner of its display area. 
     */
    public static final int SOUTHEAST = 14;

    /** 
     * Put the component at the bottom of its display area, centered 
     * horizontally. 
     */
    public static final int SOUTH = 15;

    /** 
     * Put the component at the bottom-left corner of its display area. 
     */
    public static final int SOUTHWEST = 16;

    /** 
     * Put the component on the left side of its display area, 
     * centered vertically.
     */
    public static final int WEST = 17;

    /** 
     * Put the component at the top-left corner of its display area. 
     */
    public static final int NORTHWEST = 18;

    // /** 
     // * Place the component centered along the edge of its display area
     // * associated with the start of a page for the current
     // * <code>ComponentOrienation</code>.  Equal to NORTH for horizontal
     // * orientations. 
     // */
    // public static final int PAGE_START = 0;
// 
    // /** 
     // * Place the component centered along the edge of its display area  
     // * associated with the end of a page for the current
     // * <code>ComponentOrienation</code>.  Equal to SOUTH for horizontal
     // * orientations.
     // */
    // public static final int PAGE_END = 0;
// 
    // /** 
     // * Place the component centered along the edge of its display area where 
     // * lines of text would normally begin for the current 
     // * <code>ComponentOrienation</code>.  Equal to WEST for horizontal,
     // * left-to-right orientations and EAST for horizontal, right-to-left 
     // * orientations.
     // */
    // public static final int LINE_START = 0;
// 
    // /** 
     // * Place the component centered along the edge of its display area where 
     // * lines of text would normally end for the current 
     // * <code>ComponentOrienation</code>.  Equal to EAST for horizontal,
     // * left-to-right orientations and WEST for horizontal, right-to-left 
     // * orientations.
     // */
    // public static final int LINE_END = 0;
// 
    // /** 
     // * Place the component in the corner of its display area where 
     // * the first line of text on a page would normally begin for the current 
     // * <code>ComponentOrienation</code>.  Equal to NORTHWEST for horizontal,
     // * left-to-right orientations and NORTHEAST for horizontal, right-to-left 
     // * orientations.
     // */
    // public static final int FIRST_LINE_START = 0;
// 
    // /** 
     // * Place the component in the corner of its display area where 
     // * the first line of text on a page would normally end for the current 
     // * <code>ComponentOrienation</code>.  Equal to NORTHEAST for horizontal,
     // * left-to-right orientations and NORTHWEST for horizontal, right-to-left 
     // * orientations.
     // */
    // public static final int FIRST_LINE_END = 0;
// 
    // /** 
     // * Place the component in the corner of its display area where 
     // * the last line of text on a page would normally start for the current 
     // * <code>ComponentOrienation</code>.  Equal to SOUTHWEST for horizontal,
     // * left-to-right orientations and SOUTHEAST for horizontal, right-to-left 
     // * orientations.
     // */
    // public static final int LAST_LINE_START = 0;
// 
    // /** 
     // * Place the component in the corner of its display area where 
     // * the last line of text on a page would normally end for the current 
     // * <code>ComponentOrienation</code>.  Equal to SOUTHEAST for horizontal,
     // * left-to-right orientations and SOUTHWEST for horizontal, right-to-left 
     // * orientations.
     // */
    // public static final int LAST_LINE_END = 0;


    // PBP/PP 6187232
    /** 
     * Specifies the cell  <em>at the left</em> of the component's 
     * display area, where the first cell in a row has <code>gridx=0</code>. 
     * 
     * The value 
     * <code>RELATIVE</code> specifies that the component be placed 
     * immediately following the component that was added to the container 
     * just before this component was added. 
     * <p>
     * The default value is <code>RELATIVE</code>. 
     * <code>gridx</code> should be a non-negative value.
     * @serial
     * @see #clone()
     * @see java.awt.GridBagConstraints#gridy
     */
// PBP/PP
//     * @see java.awt.ComponentOrientation
    public int gridx;

    /** 
     * Specifies the cell at the top of the component's display area, 
     * where the topmost cell has <code>gridy=0</code>. The value 
     * <code>RELATIVE</code> specifies that the component be placed just 
     * below the component that was added to the container just before 
     * this component was added. 
     * <p>
     * The default value is <code>RELATIVE</code>.
     * <code>gridy</code> should be a non-negative value.
     * @serial
     * @see #clone() 
     * @see java.awt.GridBagConstraints#gridx
     */
    public int gridy;

    /** 
     * Specifies the number of cells in a row for the component's 
     * display area. 
     * <p>
     * Use <code>REMAINDER</code> to specify that the component be the 
     * last one in its row. Use <code>RELATIVE</code> to specify that the 
     * component be the next-to-last one in its row. 
     * <p>
     * <code>gridwidth</code> should be non-negative and the default
     * value is 1.
     * @serial
     * @see #clone() 
     * @see java.awt.GridBagConstraints#gridheight
     */
    public int gridwidth;

    /** 
     * Specifies the number of cells in a column for the component's 
     * display area. 
     * <p>
     * Use <code>REMAINDER</code> to specify that the component be the 
     * last one in its column. Use <code>RELATIVE</code> to specify that 
     * the component be the next-to-last one in its column. 
     * <p>
     * <code>gridheight</code> should be a non-negative value and the
     * default value is 1.
     * @serial
     * @see #clone()
     * @see java.awt.GridBagConstraints#gridwidth
     */
    public int gridheight;

    /** 
     * Specifies how to distribute extra horizontal space. 
     * <p>
     * The grid bag layout manager calculates the weight of a column to 
     * be the maximum <code>weightx</code> of all the components in a 
     * column. If the resulting layout is smaller horizontally than the area 
     * it needs to fill, the extra space is distributed to each column in 
     * proportion to its weight. A column that has a weight of zero receives 
     * no extra space. 
     * <p>
     * If all the weights are zero, all the extra space appears between 
     * the grids of the cell and the left and right edges. 
     * <p>
     * The default value of this field is <code>0</code>.
     * <code>weightx</code> should be a non-negative value.
     * @serial
     * @see #clone() 
     * @see java.awt.GridBagConstraints#weighty
     */
    public double weightx;

    /** 
     * Specifies how to distribute extra vertical space. 
     * <p>
     * The grid bag layout manager calculates the weight of a row to be 
     * the maximum <code>weighty</code> of all the components in a row. 
     * If the resulting layout is smaller vertically than the area it 
     * needs to fill, the extra space is distributed to each row in 
     * proportion to its weight. A row that has a weight of zero receives no 
     * extra space. 
     * <p>
     * If all the weights are zero, all the extra space appears between 
     * the grids of the cell and the top and bottom edges. 
     * <p>
     * The default value of this field is <code>0</code>. 
     * <code>weighty</code> should be a non-negative value.
     * @serial
     * @see #clone()
     * @see java.awt.GridBagConstraints#weightx
     */
    public double weighty;

// PBP/PP
    /** 
     * This field is used when the component is smaller than its display
     * area. It determines where, within the display area, to place the
     * component. 
     * <p>
     * 
     * 
     * <em>Possible values are:</em>
     * <code>CENTER</code>, <code>NORTH</code>, <code>NORTHEAST</code>,
     * <code>EAST</code>, <code>SOUTHEAST</code>, <code>SOUTH</code>,
     * <code>SOUTHWEST</code>, <code>WEST</code>, and <code>NORTHWEST</code>.
     * <!- PBP/PP 6187232 ->
     * 
     * The default value is <code>CENTER</code>. 
     * @serial
     * @see #clone() 
     */
    // PBP/PP     * @see java.awt.ComponentOrientation
    public int anchor;

    /** 
     * This field is used when the component's display area is larger 
     * than the component's requested size. It determines whether to 
     * resize the component, and if so, how. 
     * <p>
     * The following values are valid for <code>fill</code>: 
     * <p>
     * <ul>
     * <li>
     * <code>NONE</code>: Do not resize the component. 
     * <li>
     * <code>HORIZONTAL</code>: Make the component wide enough to fill 
     *         its display area horizontally, but do not change its height. 
     * <li>
     * <code>VERTICAL</code>: Make the component tall enough to fill its 
     *         display area vertically, but do not change its width. 
     * <li>
     * <code>BOTH</code>: Make the component fill its display area 
     *         entirely. 
     * </ul>
     * <p>
     * The default value is <code>NONE</code>. 
     * @serial
     * @see #clone()
     */
    public int fill;

    /** 
     * This field specifies the external padding of the component, the 
     * minimum amount of space between the component and the edges of its 
     * display area. 
     * <p>
     * The default value is <code>new Insets(0, 0, 0, 0)</code>. 
     * @serial
     * @see #clone()
     */
    public Insets insets;

    /** 
     * This field specifies the internal padding of the component, how much 
     * space to add to the minimum width of the component. The width of 
     * the component is at least its minimum width plus 
     * <code>(ipadx&nbsp;*&nbsp;2)</code> pixels. 
     * <p>
     * The default value is <code>0</code>. 
     * @serial
     * @see #clone()
     * @see java.awt.GridBagConstraints#ipady
     */
    public int ipadx;

    /** 
     * This field specifies the internal padding, that is, how much 
     * space to add to the minimum height of the component. The height of 
     * the component is at least its minimum height plus 
     * <code>(ipady&nbsp;*&nbsp;2)</code> pixels. 
     * <p>
     * The default value is 0. 
     * @serial
     * @see #clone()
     * @see java.awt.GridBagConstraints#ipadx
     */
    public int ipady;

    /** 
     * Temporary place holder for the x coordinate.
     * @serial
     */
     int tempX;

    /** 
     * Temporary place holder for the y coordinate.
     * @serial
     */
     int tempY;

    /** 
     * Temporary place holder for the Width of the component.
     * @serial
     */
     int tempWidth;

    /** 
     * Temporary place holder for the Height of the component.
     * @serial
     */
     int tempHeight;

    /** 
     * The minimum width of the component.  It is used to calculate
     * <code>ipady</code>, where the default will be 0.
     * @serial
     * @see #ipady
     */
     int minWidth;

    /** 
     * The minimum height of the component. It is used to calculate
     * <code>ipadx</code>, where the default will be 0.
     * @serial
     * @see #ipadx
     */
     int minHeight;

  /*
   * JDK 1.1 serialVersionUID 
   */
  private static final long serialVersionUID = -1000070633030801713L;

    /** 
     * Creates a <code>GridBagConstraint</code> object with 
     * all of its fields set to their default value. 
     */
    public GridBagConstraints() { }

    // /** 
     // * Creates a <code>GridBagConstraints</code> object with
     // * all of its fields set to the passed-in arguments.
     // * 
     // * Note: Because the use of this constructor hinders readability
     // * of source code, this constructor should only be used by
     // * automatic source code generation tools.
     // * 
     // * @param gridx	The initial gridx value.
     // * @param gridy	The initial gridy value.
     // * @param gridwidth	The initial gridwidth value.
     // * @param gridheight	The initial gridheight value.
     // * @param weightx	The initial weightx value.
     // * @param weighty	The initial weighty value.
     // * @param anchor	The initial anchor value.
     // * @param fill	The initial fill value.
     // * @param insets	The initial insets value.
     // * @param ipadx	The initial ipadx value.
     // * @param ipady	The initial ipady value.
     // * 
     // * @see java.awt.GridBagConstraints#gridx
     // * @see java.awt.GridBagConstraints#gridy
     // * @see java.awt.GridBagConstraints#gridwidth
     // * @see java.awt.GridBagConstraints#gridheight
     // * @see java.awt.GridBagConstraints#weightx
     // * @see java.awt.GridBagConstraints#weighty
     // * @see java.awt.GridBagConstraints#anchor
     // * @see java.awt.GridBagConstraints#fill
     // * @see java.awt.GridBagConstraints#insets
     // * @see java.awt.GridBagConstraints#ipadx
     // * @see java.awt.GridBagConstraints#ipady
     // * 
     // * @since 1.2
     // */
    // public GridBagConstraints(int gridx, int gridy, int gridwidth, int
        // gridheight, double weightx, double weighty, int anchor, int fill, Insets
        // insets, int ipadx, int ipady)
    // { }

    /** 
     * Creates a copy of this grid bag constraint.
     * @return     a copy of this grid bag constraint
     */
    public Object clone() { return null; }
}
