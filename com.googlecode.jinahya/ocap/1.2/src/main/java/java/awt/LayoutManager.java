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
 * Defines the interface for classes that know how to lay out 
 * <code>Container</code>s.
 *
 * @see Container
 *
 * @version	1.24, 01/23/03
 * @author 	Sami Shaio
 * @author 	Arthur van Hoff
 */
public interface LayoutManager
{

    /** 
     * If the layout manager uses a per-component string,
     * adds the component <code>comp</code> to the layout,
     * associating it 
     * with the string specified by <code>name</code>.
     * 
     * @param name the string to be associated with the component
     * @param comp the component to be added
     */
    public void addLayoutComponent(String name, Component comp);

    /** 
     * Removes the specified component from the layout.
     * @param comp the component to be removed
     */
    public void removeLayoutComponent(Component comp);

    /** 
     * Calculates the preferred size dimensions for the specified 
     * container, given the components it contains.
     * @param parent the container to be laid out
     *  
     * @see #minimumLayoutSize
     */
    public Dimension preferredLayoutSize(Container parent);

    /** 
     * Calculates the minimum size dimensions for the specified 
     * container, given the components it contains.
     * @param parent the component to be laid out
     * @see #preferredLayoutSize
     */
    public Dimension minimumLayoutSize(Container parent);

    /** 
     * Lays out the specified container.
     * @param parent the container to be laid out 
     */
    public void layoutContainer(Container parent);
}
