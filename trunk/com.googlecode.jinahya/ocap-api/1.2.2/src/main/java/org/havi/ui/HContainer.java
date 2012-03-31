package org.havi.ui;

/*
 * Copyright 2000-2003 by HAVi, Inc. Java is a trademark of Sun
 * Microsystems, Inc. All rights reserved.  
 */

import java.awt.Color;
import java.awt.Image;


/**  
     The {@link org.havi.ui.HContainer HContainer} class extends the
     <code>java.awt.Container</code> class by implementing the {@link
     org.havi.ui.HMatteLayer HMatteLayer} interface and providing
     additional Z-ordering capabilities, which are required since
     components in the HAVi user-interface are explicitly allowed to
     overlap each other.
     <p>
     Note that these Z-ordering capabilities (<code>addBefore,
     addAfter, pop, popInFrontOf, popToFront, push, pushBehind and
     pushToBack</code>) must be implemented by (implicitly) reordering
     the child Components within the {@link org.havi.ui.HContainer
     HContainer}, so that the standard AWT convention that the Z-order
     is defined as the order in which Components are added to a given
     Container is maintained. For example, one implementation of
     <code>popToFront</code> might be to make the specified Component
     become the first Component added to the parent Container by
     removing all Components from that Container, adding the specified
     Container first, and then adding the remaining Components in
     their current relative order to that Container.

  <hr>
  The parameters to the constructors are as follows, in cases where
  parameters are not used, then the constructor should use the default
  values.
  <p>
  <h3>Default parameter values exposed in the constructors</h3>
  <table border>
  <tr><th>Parameter</th><th>Description</th><th>Default value</th> 
  <th>Set method</th><th>Get method</th></tr>
  <tr>
  <td>x</td>
  <td>x-coordinate of top left hand corner of this component in pixels, 
  relative to its parent container (subject to layout management).</td>
  <td>---</td>
  <td>java.awt.Component#setBounds</td>
  <td>java.awt.Component#getBounds</td>
  </tr>
  <tr>
  <td>y</td>
  <td>y-coordinate of top left hand corner of this component in pixels,
  relative to its parent container (subject to layout management).</td>
  <td>---</td>
  <td>java.awt.Component#setBounds</td>
  <td>java.awt.Component#getBounds</td>
  </tr>
  <tr>
  <td>width</td>
  <td>width of this component in pixels (subject to layout management). 
  </td>
  <td>---</td>
  <td>java.awt.Component#setBounds</td>
  <td>java.awt.Component#getBounds</td>
  </tr>
  <tr>
  <td>height</td>
  <td>height of this component in pixels (subject to layout management). 
  </td>
  <td>---</td>
  <td>java.awt.Component#setBounds</td>
  <td>java.awt.Component#getBounds</td>
  </tr>
  
  </table>

  <h3>Default parameter values not exposed in the constructors</h3>
  <table border>
  <tr><th>Description</th><th>Default value</th><th>Set method</th>
  <th>Get method</th></tr>
  <tr> 
  <td>Associated matte ({@link org.havi.ui.HMatte HMatte}).</td> 
  <td>none (i.e. getMatte() returns <code>null</code>)</td> 
  <td>{@link org.havi.ui.HComponent#setMatte setMatte}</td> 
  <td>{@link org.havi.ui.HComponent#getMatte getMatte}</td> 
  </tr>
  <tr> 
  <td>LayoutManager</td> 
  <td><code>null</code> (in contrast to java.awt.Container)</td> 
  <td>java.awt.Container#setLayout</td> 
  <td>java.awt.Container#getLayout</td>
  </tr>
  </table>

*/

public class HContainer 
    extends java.awt.Container 
    implements HMatteLayer, HComponentOrdering, org.dvb.ui.TestOpacity
{
    /**
     * Creates an HContainer object. See the class description for
     * details of constructor parameters and default values.  
     */
    public HContainer()
    {
    }
    
    /**
     * Creates an HContainer object. See the class description for
     * details of constructor parameters and default values.  
     */
    public HContainer(int x, int y, int width, int height)
    {
    }

    /**	
     * Applies an {@link org.havi.ui.HMatte HMatte} to this component,
     * for matte compositing. Any existing animated matte must be
     * stopped before this method is called or an HMatteException will
     * be thrown.
     * 
     * @param m The {@link org.havi.ui.HMatte HMatte} to be applied to
     * this component -- note that only one matte may be associated
     * with the component, thus any previous matte will be replaced.
     * If m is null, then any matte associated with the component is
     * removed and further calls to getMatte() shall return null. The
     * component shall behave as if it had a fully opaque {@link
     * org.havi.ui.HFlatMatte HFlatMatte} associated with it (i.e an
     * HFlatMatte with the default value of 1.0.)
     * @exception HMatteException if the {@link org.havi.ui.HMatte
     * HMatte} cannot be associated with the component. This can occur:
     * <ul>
     * <li> if the specific matte type is not supported
     * <li> if the platform does not support any matte type
     * <li> if the component is associated with an already running
     * {@link org.havi.ui.HFlatEffectMatte HFlatEffectMatte} or {@link
     * org.havi.ui.HImageEffectMatte HImageEffectMatte}. The exception
     * is thrown even if m is null.
     * </ul>
     * @see HMatte 
     */
    public void setMatte(HMatte m) throws HMatteException
    {
        return;
    }

    /**
     * Get any {@link org.havi.ui.HMatte HMatte} currently associated
     * with this component.
     * 
     * @return the {@link org.havi.ui.HMatte HMatte} currently
     * associated with this component or null if there is no
     * associated matte.  
     */
    public HMatte getMatte()
    {
        return(null);
    }

    /**
     * Returns <code>true</code> if all the drawing done during the update and
     * paint methods for this specific {@link org.havi.ui.HContainer
     * HContainer} object is automatically double buffered.
     * 
     * @return <code>true</code> if all the drawing done during the
     * update and paint methods for this specific {@link
     * org.havi.ui.HComponent HComponent} object is automatically
     * double buffered, or <code>false</code> if drawing is not double
     * buffered.  The default value for the double buffering setting
     * is platform-specific.  
     */
    public boolean isDoubleBuffered()
    {
	return(false);
    }
    
    /** 
     * Returns true if the entire {@link org.havi.ui.HContainer
     * HContainer} area, as given by the
     * <code>java.awt.Component#getBounds</code> method, is fully
     * opaque, i.e. its paint method (or surrogate methods) guarantee
     * that all pixels are painted in an opaque <code>Color</code>.
     * <p>
     * By default, the return value is <code>false</code>. The return
     * value should be overridden by subclasses that can guarantee
     * full opacity. The consequences of an invalid overridden value
     * are implementation specific.
     * 
     * @return <code>true</code> if all the pixels within the area
     * given by the <code>java.awt.Component#getBounds</code> method
     * are fully opaque, i.e. its paint method (or surrogate methods)
     * guarantee that all pixels are painted in an opaque Color,
     * otherwise <code>false</code>.  
     */
    public boolean isOpaque()
    {
	return (false);
    }

    /**      
     * Adds a <code>java.awt.Component</code> to this {@link
     * org.havi.ui.HContainer HContainer} directly in front of a
     * previously added <code>java.awt.Component</code>.
     * <p>
     * If <code>component</code> has already been added to this
     * container, then <code>addBefore</code> moves
     * <code>component</code> in front of <code>behind</code>.  If
     * <code>behind</code> and <code>component</code> are the same
     * component which was already added to this container,
     * <code>addBefore</code> does not change the ordering of the
     * components and returns <code>component</code>.
     * <p>
     * This method affects the Z-order of the
     * <code>java.awt.Component</code> children within the {@link
     * org.havi.ui.HContainer HContainer}, and may also implicitly
     * change the numeric ordering of those children.
     * 
     * @param component is the <code>java.awt.Component</code> to be
     * added to the {@link org.havi.ui.HContainer HContainer}
     * @param behind is the <code>java.awt.Component</code>, which
     * <code>component</code> will be placed in front of, i.e.
     * <code>behind</code> will be directly behind the added
     * <code>java.awt.Component</code>
     * @return If the <code>java.awt.Component</code> is successfully
     * added or was already present, then it will be returned from
     * this call. If the
     * <code>java.awt.Component</code> is not successfully added,
     * e.g. <code>behind</code> is not a
     * <code>java.awt.Component</code> currently added to the {@link
     * org.havi.ui.HContainer HContainer}, then <code>null</code> will
     * be returned.  <p> This method must be implemented in a thread
     * safe manner.
     */
    public java.awt.Component addBefore(java.awt.Component component, java.awt.Component behind)
    {
        return(null);
    }

    /**
     * Adds a <code>java.awt.Component</code> to this {@link
     * org.havi.ui.HContainer HContainer} directly behind a previously
     * added <code>java.awt.Component</code>.     
     * <p>
     * If <code>component</code> has already been added to this
     * container, then addAfter moves <code>component</code> behind
     * <code>front</code>.  If <code>front</code> and
     * <code>component</code> are the same component which was already
     * added to this container, <code>addAfter</code> does not change
     * the ordering of the components and returns
     * <code>component</code>.
     * <p> 
     * This method affects the Z-order of the
     * <code>java.awt.Component</code> children within the {@link
     * org.havi.ui.HContainer HContainer}, and may also implicitly
     * change the numeric ordering of those children.
     *     
     * @param component is the <code>java.awt.Component</code> to be
     * added to the {@link org.havi.ui.HContainer HContainer}
     * @param front is the <code>java.awt.Component</code>, which
     * <code>component</code> will be placed behind, i.e.
     * <code>front</code> will be directly in front of the added
     * <code>java.awt.Component</code>
     * @return If the <code>java.awt.Component</code> is successfully
     * added or was already present, then it will be returned from
     * this call. If the
     * <code>java.awt.Component</code> is not successfully added,
     * e.g. front is not a <code>java.awt.Component</code> currently
     * added to the {@link org.havi.ui.HContainer HContainer}, then
     * <code>null</code> will be returned. 
     * <p> 
     * This method must be implemented in a thread safe manner. 
     */
    public java.awt.Component addAfter(java.awt.Component component, java.awt.Component front)
    {
        return(null);
    }
    
    /**
     * Brings the specified <code>java.awt.Component</code> to the
     * &quot;front&quot; of the Z-order in this {@link
     * org.havi.ui.HContainer HContainer}.
     * <p>
     * If <code>component</code> is already at the front of the
     * Z-order, the order is unchanged and <code>popToFront</code>
     * returns <code>true</code>.
     * 
     * @param component The <code>java.awt.Component</code> to bring to the
     * &quot;front&quot; of the Z-order of this {@link org.havi.ui.HContainer
     * HContainer}.
     * 
     * @return returns <code>true</code> on success,
     * <code>false</code> on failure, for example when the
     * <code>java.awt.Component</code> has yet to be added to the
     * {@link org.havi.ui.HContainer HContainer}. If this method
     * fails, the Z-order is unchanged.  
     */
    public boolean popToFront(java.awt.Component component)
    {
        return(true);
    }
    
    /**
     * Place the specified <code>java.awt.Component</code> at the
     * &quot;back&quot; of the Z-order in this {@link
     * org.havi.ui.HContainer HContainer}.
     * <p>
     * If <code>component</code> is already at the back the Z-order is
     * unchanged and <code>pushToBack</code> returns <code>true</code>.
     * 
     * @param component The <code>java.awt.Component</code> to place
     * at the &quot;back&quot; of the Z-order of this {@link org.havi.ui.HContainer
     * HContainer}.
     * @return returns <code>true</code> on success,
     * <code>false</code> on failure, for example when the
     * <code>java.awt.Component</code> has yet to be added to the
     * {@link org.havi.ui.HContainer HContainer}. If the component was
     * not added to the container <code>pushToBack</code> does not change the
     * Z-order.  
     */
    public boolean pushToBack(java.awt.Component component)
    {
        return(true);
    }
    
    /**
     * Moves the specified <code>java.awt.Component</code> one
     * component nearer in the Z-order, i.e. swapping it with the
     * <code>java.awt.Component</code> that was directly in front of
     * it.     
     * <p>
     * If <code>component</code> is already at the front of the
     * Z-order, the order is unchanged and <code>pop</code> returns
     * <code>true</code>.
     * 
     * @param component The <code>java.awt.Component</code> to be moved.
     * @return returns <code>true</code> on success, <code>false</code> on failure, for example
     * if the <code>java.awt.Component</code> has yet to be added to
     * the {@link org.havi.ui.HContainer HContainer}.  
     */
    public boolean pop(java.awt.Component component)
    {
        return(true);
    }
    
    /**
     * Moves the specified <code>java.awt.Component</code> one
     * component further away in the Z-order, i.e. swapping it with
     * the <code>java.awt.Component</code> that was directly behind
     * it.
     * <p>
     * If <code>component</code> is already at the back of the
     * Z-order, the order is unchanged and <code>push</code> returns
     * <code>true</code>.
     * 
     * @param component The <code>java.awt.Component</code> to be
     * moved.
     * @return returns <code>true</code> on success,
     * <code>false</code> on failure, for example if the
     * <code>java.awt.Component</code> has yet to be added to the
     * {@link org.havi.ui.HContainer HContainer}.  
     */
    public boolean push(java.awt.Component component)
    {
        return(true);
    }
    
    /**       
     * Puts the specified <code>java.awt.Component</code> in front of
     * another <code>java.awt.Component</code> in the Z-order of this
     * {@link org.havi.ui.HContainer HContainer}.
     * <p>
     * If <code>move</code> and <code>behind</code> are the same
     * component which has been added to the container
     * <code>popInFront</code> does not change the Z-order and returns
     * <code>true</code>.
     * 
     * @param move The <code>java.awt.Component</code> to be moved
     * directly in front of the &quot;behind&quot; Component in the
     * Z-order of this {@link org.havi.ui.HContainer HContainer}.
     * @param behind The <code>java.awt.Component</code> which the
     * &quot;move&quot; Component should be placed directly in front
     * of.
     * @return returns <code>true</code> on success,
     * <code>false</code> on failure, for example when either
     * <code>java.awt.Component</code> has yet to be added to the
     * {@link org.havi.ui.HContainer HContainer}. If this method
     * fails, the Z-order is unchanged.  
     */
    public boolean popInFrontOf(java.awt.Component move, java.awt.Component behind)
    {
        return(true);
    }
    
    /**       
     * Puts the specified <code>java.awt.Component</code> behind
     * another <code>java.awt.Component</code> in the Z-order of this
     * {@link org.havi.ui.HContainer HContainer}. 
     * <p>
     * If <code>move</code> and <code>front</code> are the same
     * component which has been added to the container
     * <code>pushBehind</code> does not change the Z-order and returns
     * <code>true</code>.
     * 
     * @param move The <code>java.awt.Component</code> to be moved
     * directly behind the &quot;front&quot; Component in the Z-order
     * of this {@link org.havi.ui.HContainer HContainer}.
     * @param front The <code>java.awt.Component</code> which the
     * &quot;move&quot; Component should be placed directly behind.
     * @return returns <code>true</code> on success,
     * <code>false</code> on failure, for example when either
     * <code>java.awt.Component</code> has yet to be added to the
     * {@link org.havi.ui.HContainer HContainer}.  
     */
    public boolean pushBehind(java.awt.Component move, java.awt.Component front)
    {
        return(true);
    }
    
    /**
     * Groups the HContainer and its components.  If the container is
     * already grouped this method has no effect
     * 
     * @see org.havi.ui.HContainer#ungroup 
     * @see org.havi.ui.HContainer#isGrouped
     */
    public void group()
    {
    }
    
    /**       
     * Ungroups the HContainer and its components. If the container is
     * already ungrouped, this method has no effect.
     * 
     * @see org.havi.ui.HContainer#group 
     * @see org.havi.ui.HContainer#isGrouped
     */
    public void ungroup()
    {
    }
    
    /**       
     * Tests whether the HContainer and its components are grouped.
     * By default the container is not grouped with its components. 
     * 
     * @return returns <code>true</code> if the HContainer and its
     * components are grouped, <code>false</code> otherwise.
     * 
     * @see org.havi.ui.HContainer#group 
     * @see org.havi.ui.HContainer#ungroup 
     */
    public boolean isGrouped()
    {
	return (false);
    }    
}




