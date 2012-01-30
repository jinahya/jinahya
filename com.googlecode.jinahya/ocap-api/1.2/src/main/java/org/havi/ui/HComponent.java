package org.havi.ui;

/*
 * Copyright 2000-2003 by HAVi, Inc. Java is a trademark of Sun
 * Microsystems, Inc. All rights reserved.  
 */

import java.awt.Color;
import java.awt.Image;

/**
  The <code>HComponent</code> class extends the
  java.awt.Component class by implementing the {@link
  org.havi.ui.HMatteLayer} interface.

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
  </table>

 */

public abstract class HComponent 
    extends java.awt.Component 
    implements HMatteLayer, org.dvb.ui.TestOpacity
{
    /**
     * Creates an HComponent object. See the class description for
     * details of constructor parameters and default values.  
     */
    public HComponent()
    {
    }
    
    /**
     * Creates an HComponent object. See the class description for
     * details of constructor parameters and default values.  
     */
    public HComponent(int x, int y, int width, int height)
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
     * Returns true if all the drawing done during the update and
     * paint methods for this specific
     * HComponent object is automatically double buffered.
     * 
     * @return <code>true</code> if all the drawing done during the update and
     * paint methods for this specific
     * HComponent object is automatically double buffered, or false
     * if drawing is not double buffered.  The default value for the
     * double buffering setting is platform-specific.  
     */
    public boolean isDoubleBuffered()
    {
	return (false);
    }
    
    /** 
     * Returns true if the entire
     * HComponent area, as given by the
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
      * Enables or disables this component, depending on the value of the
      * parameter b. HComponents which are 
      * disabled will still generate and respond to 
      * {@link org.havi.ui.event.HFocusEvent} if they implement 
      * {@link org.havi.ui.HNavigationInputPreferred}.  
      * They will not generate or respond to 
      * {@link org.havi.ui.event.HActionEvent}, 
      * {@link org.havi.ui.event.HAdjustmentEvent}, 
      * {@link org.havi.ui.event.HItemEvent}, 
      * {@link org.havi.ui.event.HKeyEvent} or 
      * {@link org.havi.ui.event.HTextEvent}. (This method should not 
      * invoke the superclass method.) HComponents
      * are enabled initially by default.
      * <p>
      * If a widget implementing 
      * {@link org.havi.ui.HKeyboardInputPreferred} is 
      * disabled while in edit mode, it will automatically set edit mode to 
      * false and generate an HTextEvent.TEXT_END_CHANGE. Calls to
      * setEditMode() should be ignored while being disabled.
      * <p>
      * If a widget implementing 
      * {@link org.havi.ui.HAdjustmentInputPreferred}
      * is disabled while in adjust mode, it will automatically set adjust mode 
      * to false and generate an HAdjustmentEvent.ADJUST_END_CHANGE. Calls to 
      * setAdjustMode() should be ignored while being disabled.
      * <p> 
      * If a widget implementing 
      * {@link org.havi.ui.HSelectionInputPreferred} is 
      * disabled while in selection mode, it will automatically set selection 
      * mode to false and generate an HItemEvent.ITEM_END_CHANGE. Calls to 
      * setSelectionMode() should be ignored while being disabled.          
      * 
      * @param b If true, this HComponent is enabled; otherwise this
      * HComponent is disabled.
      */
     public void setEnabled(boolean b) 
     {
     }
	

      /**
       * Determines whether this HComponent is 
       * enabled. An HComponent may be enabled or  
       * disabled by calling its setEnabled method.
       * 
       * @return <code>true</code> if the 
       * HComponent is enabled; <code>false</code>
       * otherwise.
       */
      public boolean isEnabled()
      {
        return (false);
      }



    /**
     * The implementation of the method
     * <code>HComponent.processEvent()</code> shall ensure
     * that key events which are translated to HAVi events shall not be
     * reported to <code>processKeyEvent()</code> or reported to
     * <code>KeyListeners</code>. Key events which are not translated
     * to HAVi events shall be reported to
     * <code>processKeyEvent()</code> and <code>KeyListeners</code> as
     * defined in the Java specification.
     * <p>
     * NOTE: If applications override <code>processEvent</code> they
     * may terminally disturb these processes. Applications should not
     * do this without extreme care, as the results may be very
     * implementation dependent.
     * 
     * @param evt the java.awt.AWTEvent to handle.  
     */
    protected void processEvent(java.awt.AWTEvent evt)
    {
    }
}

