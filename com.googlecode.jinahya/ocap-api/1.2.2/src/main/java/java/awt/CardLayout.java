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
import java.util.Enumeration;
import java.io.Serializable;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.ObjectStreamField;
import java.io.IOException;

/** 
 * A <code>CardLayout</code> object is a layout manager for a
 * container. It treats each component in the container as a card.
 * Only one card is visible at a time, and the container acts as
 * a stack of cards. The first component added to a 
 * <code>CardLayout</code> object is the visible component when the 
 * container is first displayed.
 * <p>
 * The ordering of cards is determined by the container's own internal
 * ordering of its component objects. <code>CardLayout</code>
 * defines a set of methods that allow an application to flip
 * through these cards sequentially, or to show a specified card.
 * The {@link CardLayout#addLayoutComponent}
 * method can be used to associate a string identifier with a given card
 * for fast random access.
 *
 * @version 	1.37 01/23/03
 * @author 	Arthur van Hoff
 * @see         java.awt.Container
 * @since       JDK1.0
 */
public class CardLayout implements LayoutManager2, Serializable
{

    private static final long serialVersionUID = -4328196481005934313L;

    /** 
     * @serialField tab	        Hashtable
     *      deprectated, for forward compatibility only
     * @serialField hgap        int
     * @serialField vgap        int
     * @serialField vector      Vector
     * @serialField currentCard int
     */
    private static final ObjectStreamField[] serialPersistentFields = null;

    /** 
     * Creates a new card layout with gaps of size zero.
     */
    public CardLayout() { }

    /** 
     * Creates a new card layout with the specified horizontal and
     * vertical gaps. The horizontal gaps are placed at the left and
     * right edges. The vertical gaps are placed at the top and bottom
     * edges.
     * @param     hgap   the horizontal gap.
     * @param     vgap   the vertical gap.
     */
    public CardLayout(int hgap, int vgap) { }

    /** 
     * Gets the horizontal gap between components.
     * @return    the horizontal gap between components.
     * @see       java.awt.CardLayout#setHgap(int)
     * @see       java.awt.CardLayout#getVgap()
     * @since     JDK1.1
     */
    public int getHgap() {return 0;  }

    /** 
     * Sets the horizontal gap between components.
     * @param hgap the horizontal gap between components.
     * @see       java.awt.CardLayout#getHgap()
     * @see       java.awt.CardLayout#setVgap(int)
     * @since     JDK1.1
     */
    public void setHgap(int hgap) { }

    /** 
     * Gets the vertical gap between components.
     * @return the vertical gap between components.
     * @see       java.awt.CardLayout#setVgap(int)
     * @see       java.awt.CardLayout#getHgap()
     */
    public int getVgap() {return 0;  }

    /** 
     * Sets the vertical gap between components.
     * @param     vgap the vertical gap between components.
     * @see       java.awt.CardLayout#getVgap()
     * @see       java.awt.CardLayout#setHgap(int)
     * @since     JDK1.1
     */
    public void setVgap(int vgap) { }

    /** 
     * Adds the specified component to this card layout's internal
     * table of names. The object specified by <code>constraints</code>
     * must be a string. The card layout stores this string as a key-value
     * pair that can be used for random access to a particular card.
     * By calling the <code>show</code> method, an application can
     * display the component with the specified name.
     * @param     comp          the component to be added.
     * @param     constraints   a tag that identifies a particular
     *                                        card in the layout.
     * @see       java.awt.CardLayout#show(java.awt.Container, java.lang.String)
     * @exception  IllegalArgumentException  if the constraint is not a string.
     */
    public void addLayoutComponent(Component comp, Object constraints) { }

    /** 
     * @deprecated   replaced by
     *      <code>addLayoutComponent(Component, Object)</code>.
     */
    public void addLayoutComponent(String name, Component comp) { }

    /** 
     * Removes the specified component from the layout.
     * @param   comp   the component to be removed.
     * @see     java.awt.Container#remove(java.awt.Component)
     * @see     java.awt.Container#removeAll()
     */
    public void removeLayoutComponent(Component comp) { }

    /** 
     * Determines the preferred size of the container argument using
     * this card layout.
     * @param   parent the name of the parent container.
     * @return  the preferred dimensions to lay out the subcomponents
     *                of the specified container.
     * @see     java.awt.Container#getPreferredSize
     * @see     java.awt.CardLayout#minimumLayoutSize
     */
    public Dimension preferredLayoutSize(Container parent) { return null; }

    /** 
     * Calculates the minimum size for the specified panel.
     * @param     parent the name of the parent container
     *                in which to do the layout.
     * @return    the minimum dimensions required to lay out the
     *                subcomponents of the specified container.
     * @see       java.awt.Container#doLayout
     * @see       java.awt.CardLayout#preferredLayoutSize
     */
    public Dimension minimumLayoutSize(Container parent) { return null; }

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
    public void invalidateLayout(Container target) { }

    /** 
     * Lays out the specified container using this card layout.
     * <p>
     * Each component in the <code>parent</code> container is reshaped
     * to be the size of the container, minus space for surrounding
     * insets, horizontal gaps, and vertical gaps.
     *
     * @param     parent the name of the parent container
     *                             in which to do the layout.
     * @see       java.awt.Container#doLayout
     */
    public void layoutContainer(Container parent) { }

    /** 
     * Flips to the first card of the container.
     * @param     parent   the name of the parent container
     *                          in which to do the layout.
     * @see       java.awt.CardLayout#last
     */
    public void first(Container parent) { }

    /** 
     * Flips to the next card of the specified container. If the
     * currently visible card is the last one, this method flips to the
     * first card in the layout.
     * @param     parent   the name of the parent container
     *                          in which to do the layout.
     * @see       java.awt.CardLayout#previous
     */
    public void next(Container parent) { }

    /** 
     * Flips to the previous card of the specified container. If the
     * currently visible card is the first one, this method flips to the
     * last card in the layout.
     * @param     parent   the name of the parent container
     *                          in which to do the layout.
     * @see       java.awt.CardLayout#next
     */
    public void previous(Container parent) { }

    /** 
     * Flips to the last card of the container.
     * @param     parent   the name of the parent container
     *                          in which to do the layout.
     * @see       java.awt.CardLayout#first
     */
    public void last(Container parent) { }

    /** 
     * Flips to the component that was added to this layout with the
     * specified <code>name</code>, using <code>addLayoutComponent</code>.
     * If no such component exists, then nothing happens.
     * @param     parent   the name of the parent container
     *                     in which to do the layout.
     * @param     name     the component name.
     * @see       java.awt.CardLayout#addLayoutComponent(java.awt.Component, java.lang.Object)
     */
    public void show(Container parent, String name) { }

    /** 
     * Returns a string representation of the state of this card layout.
     * @return    a string representation of this card layout.
     */
    public String toString() { return null; }

    /** 
     * Reads serializable fields from stream.
     */
    private void readObject(ObjectInputStream s)
        throws ClassNotFoundException, IOException
    { }

    /** 
     * Writes serializable fields to stream.
     */
    private void writeObject(ObjectOutputStream s) throws IOException { }
}
