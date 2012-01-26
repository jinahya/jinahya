package org.havi.ui;

/*
 * Copyright 2000-2003 by HAVi, Inc. Java is a trademark of Sun
 * Microsystems, Inc. All rights reserved.  
 */

import java.awt.Container;
import org.havi.ui.event.*;

/**
   {@link org.havi.ui.HVideoComponent HVideoComponent} is an opaque
   class encapsulating the presentation of a video source
   <i>within</i> an application, i.e. contained within a conventional
   AWT hierarchy.
   <p>
   An {@link org.havi.ui.HVideoComponent HVideoComponent} obeys all
   conventional java.awt.Component semantics, including being clipped
   by its parent container, etc. An {@link org.havi.ui.HVideoComponent
   HVideoComponent} also obeys all {@link org.havi.ui.HComponent
   HComponent} semantics including Z-ordering, etc.


  <hr>
  The parameters to the constructors are as follows, in cases where
  parameters are not used, then the constructor should use the default
  values.
  <p>
  <h3>Default parameter values exposed in the constructors</h3>
  <table border>
  <tr><th>Parameter</th><th>Description</th><th>Default value</th> 
  <th>Set method</th><th>Get method</th></tr>
  <tr><td colspan=5>None.</td></tr>
  </table>
  <h3>Default parameter values not exposed in the constructors</h3>
  <table border>
  <tr><th>Description</th><th>Default value</th><th>Set method</th>
  <th>Get method</th></tr>
  <tr><td colspan=4>None.</td></tr>
  </table>

*/

public class HVideoComponent
    extends HComponent
{    
    /**
     * It is not intended that applications should directly construct
     * {@link org.havi.ui.HVideoComponent HVideoComponent} objects.
     * <p>
     * Creates an {@link org.havi.ui.HVideoComponent HVideoComponent}
     * object. See the class description for details of constructor
     * parameters and default values.
     */
    protected HVideoComponent()
    {
    }

    /**
     * Returns the {@link org.havi.ui.HVideoDevice HVideoDevice} that
     * this {@link org.havi.ui.HVideoComponent HVideoComponent} is
     * associated with.
     *
     * @return the {@link org.havi.ui.HVideoDevice HVideoDevice} that
     * this {@link org.havi.ui.HVideoComponent HVideoComponent} is
     * associated with, or null if this cannot be determined.
     */
    public HVideoDevice getVideoDevice()
    {
        return (null);
    }

    /**
     * Register a listener to determine if the Component's on-screen
     * location is modified - irrespective of its relative location to
     * its parent Container. If the listener has already been added
     * further calls will add further references to the listener,
     * which will then receive multiple copies of a single event.
     *
     * @param slml listener to be notified when the on-screen location
     * of the component is modified.  
     */
    public void addOnScreenLocationModifiedListener(HScreenLocationModifiedListener slml)
    {
    }

    /**
     * Remove a listener that determines if the Component's on-screen
     * location is modified - irrespective of its relative location to
     * its parent Container. If the specified listener is not
     * registered, the method has no effect.  If multiple references to
     * a single listener have been registered it should be noted that
     * this method will only remove one reference per call.
     *
     * @param slml listener to be notified when the on-screen location
     * of the component is modified.
     */
    public void removeOnScreenLocationModifiedListener(HScreenLocationModifiedListener slml)
    {
    }
}

