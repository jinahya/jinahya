package org.havi.ui;

/*
 * Copyright 2000-2003 by HAVi, Inc. Java is a trademark of Sun
 * Microsystems, Inc. All rights reserved.  
 */

import java.awt.Dimension;

/**
   The {@link org.havi.ui.HSceneFactory HSceneFactory} class provides
   a generic mechanism for an application to request {@link
   org.havi.ui.HScene HScene} resources from a (conceptual) window
   management system. The {@link org.havi.ui.HSceneFactory
   HSceneFactory} is the single entry to potentially multiple
   HGraphicsDevice centric window management policies.
   
   <p>
   The {@link org.havi.ui.HSceneFactory HSceneFactory} class provides
   an opaque interface between any application (or window) management
   scheme and the Java application, itself.

   <p> 
   Note that only one {@link org.havi.ui.HScene HScene} per {@link
   org.havi.ui.HGraphicsDevice HGraphicsDevice} can be acquired at any
   one time for each application.

   <p> 

   {@link org.havi.ui.HScene HScenes} follow the design pattern of the
   <code>java.awt.Window</code> class. They are not a scarce resource
   on the platform. On platforms which only support one {@link
   org.havi.ui.HScene HScene} being visible at one time the current
   {@link org.havi.ui.HScene HScene} both loses the input focus and is
   hidden (e.g. iconified) when another application successfully
   requests the input focus. Two
   <code>java.awt.event.WindowEvent</code> events, with ids
   <code>WINDOW_DEACTIVATED</code> and <code>WINDOW_ICONIFIED</code>,
   shall be generated and sent to the {@link org.havi.ui.HScene
   HScene} which has lost the focus and the {@link
   org.havi.ui.HScene#isShowing isShowing} method for that HScene
   shall return false.

   <p>
   The constraints on the sizing and positioning of the {@link
   org.havi.ui.HScene HScene} returned by the methods are dependent on
   the platform-specific {@link org.havi.ui.HScene HScene} support.
   The three scenarios are defined below:
   
   <h4>Platforms Supporting a Full Multi-Window System</h4>
   
   Platforms where windows may obscure each other shall allow
   applications to create {@link org.havi.ui.HScene HScene} objects
   which are fully within the area of the supporting {@link
   org.havi.ui.HGraphicsDevice HGraphicsDevice} without any restriction
   on size or location. Whether successful creation of HScenes which
   are wholly or partially outside the area of the HGraphicsDevice is
   supported is implementation dependent.
   
   <h4>Platforms Supporting a Single Window System</h4>
   
   Platforms supporting a simple &quot;full-screen&quot; view on a single
   application at any one time are required to allow applications to
   create HScenes which cover the full area of the supporting
   HGraphicsDevice. HScenes matching this description shall be returned
   as the default {@link org.havi.ui.HScene HScene} for the supporting
   {@link org.havi.ui.HGraphicsDevice HGraphicsDevice}. It is
   implementation dependent whether requests to create HScene
   objects which cover less than the full area of the supporting
   HGraphicsDevice succeed and if they succeed, what the consequences
   are of their being displayed.
   
   <h4>Platforms Supporting a Restricted Multi-Window System</h4>
   
   Platforms supporting a &quot;paned&quot; system where each application
   occupies an area on-screen that is always visible make fewer
   guarantees to applications.  When an {@link org.havi.ui.HScene
   HScene} is created on such a system, the platform shall return an
   HScene which, if visible at that time, would cover as much as
   possible of the requested area of the supporting HGraphicsDevice
   considering all other visible HScenes at that time. HScenes which
   are not visible at this time shall not be considered when fixing the
   location & size of the new HScene.  HScenes which are visible at
   this time shall not be effected by the creation of the new HScene.
   
   <p>
   When {@link org.havi.ui.HScene#setVisible setVisible(true)} is
   called on such an {@link org.havi.ui.HScene HScene}, the platform
   shall attempt to make the HScene visible using its currently set
   position and size. If this would conflict with HScenes which are
   already visible (e.g.  because of changes between when the HScene
   was created and when <code>setVisible</code> was called) then the
   call to <code>setVisible</code> shall fail silently. Applications
   are responsible for testing for failure using the {@link
   org.havi.ui.HScene#isVisible isVisible} method. The HScenes of
   already visible applications shall not be impacted by this method
   call.
   
   <p>
   The above text specifies the relationship between an {@link
   org.havi.ui.HScene HScene} and its supporting {@link
   org.havi.ui.HGraphicsDevice HGraphicsDevice} . This specification
   intentionally does not define minimum requirements for
   HGraphicsDevices or for their relationship with other HScreenDevices
   of any type.
   
   <p>
   Calling {@link org.havi.ui.HSceneFactory#resizeScene resizeScene}
   for an {@link org.havi.ui.HScene HScene} shall apply the same
   policies as described above for newly created HScenes when deciding
   whether the method call is possible.
   
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

public class HSceneFactory
    extends Object
{    
    /**
     * Hide the {@link org.havi.ui.HSceneFactory HSceneFactory} from
     * being constructed 
     */
    private HSceneFactory()
    {
    }

    /**
     * Returns an {@link org.havi.ui.HSceneFactory HSceneFactory}
     * object to an application.
     *
     * @return an {@link org.havi.ui.HSceneFactory HSceneFactory}
     * object to an application. Note that repeated invocations of
     * this method should return the same object (reference).
     */
    public static HSceneFactory getInstance()
    {
        return (null);
    }

    /**
     * Returns an {@link org.havi.ui.HSceneTemplate HSceneTemplate}
     * that is closest to to the input {@link
     * org.havi.ui.HSceneTemplate HSceneTemplate} and corresponds to
     * an HScene which could be successfully created on this platform
     * at the time that this method is called.
     * <p>
     * Note that since some platforms may support more than one
     * concurrent application there is no guarantee that the values
     * returned by this method would actually match those of a
     * subsequently requested {@link org.havi.ui.HScene HScene}, using
     * the same template.
     * <p>
     * Note that conflict may occur between properties in the {@link
     * org.havi.ui.HSceneTemplate HSceneTemplate} and the {@link
     * org.havi.ui.HGraphicsConfigTemplate HGraphicsConfigTemplate}
     * corresponding to the currently active {@link
     * org.havi.ui.HGraphicsConfiguration HGraphicsConfiguration}. In
     * the event of conflict between properties which are PREFERRED or
     * UNNECESSARY, the properties concerned shall be ignored and the
     * default will prevail. In the event of conflict between
     * properties which are REQUIRED, this method shall fail and
     * return null.
     *
     * @param hst The {@link org.havi.ui.HSceneTemplate
     * HSceneTemplate} properties that the {@link org.havi.ui.HScene
     * HScene} should satisfy.
     * @return an {@link org.havi.ui.HSceneTemplate HSceneTemplate}
     * that best corresponds to the input {@link
     * org.havi.ui.HSceneTemplate HSceneTemplate}.  
     */
    public HSceneTemplate getBestSceneTemplate(HSceneTemplate hst)
    {
        return (null);
    }

    /**
     * Returns an {@link org.havi.ui.HScene HScene} that best
     * corresponds to the input {@link org.havi.ui.HSceneTemplate
     * HSceneTemplate}, or null if such an {@link org.havi.ui.HScene
     * HScene} cannot be generated.
     * <p>
     * Note that conflict may occur between properties in the {@link
     * org.havi.ui.HSceneTemplate HSceneTemplate} and the {@link
     * org.havi.ui.HGraphicsConfigTemplate HGraphicsConfigTemplate}
     * corresponding to the currently active {@link
     * org.havi.ui.HGraphicsConfiguration HGraphicsConfiguration}. In
     * the event of conflict between properties which are PREFERRED or
     * UNNECESSARY, the properties concerned shall be ignored and the
     * default will prevail. In the event of conflict between
     * properties which are REQUIRED, this method shall fail and
     * return null.
     *
     * @param hst the HSceneTemplate to match against
     * @return the {@link org.havi.ui.HScene HScene} that matches the
     * properties as specified in the {@link
     * org.havi.ui.HSceneTemplate HSceneTemplate}, or null if they
     * cannot be satisfied, or if no further {@link org.havi.ui.HScene
     * HScenes} are available for this application. 
     */
    public HScene getBestScene(HSceneTemplate hst)
    {
        return (null);
    }

    /**
     * Resizes an {@link org.havi.ui.HScene HScene} so that it best
     * corresponds to the input {@link org.havi.ui.HSceneTemplate
     * HSceneTemplate}, or remains unchanged if it cannot be so
     * resized.
     *
     * @param hs the {@link org.havi.ui.HScene HScene} to be resized.
     * @param hst the {@link org.havi.ui.HSceneTemplate
     * HSceneTemplate} which denotes the new size / location. Only
     * size location options in the {@link org.havi.ui.HSceneTemplate
     * HSceneTemplate} will be considered.
     * @return an {@link org.havi.ui.HSceneTemplate HSceneTemplate}
     * that indicates the {@link org.havi.ui.HScene HScene} properties
     * after (possible) resizing.
     * @exception java.lang.IllegalStateException if the {@link
     * org.havi.ui.HScene HScene} had previously been disposed.
     */
    public HSceneTemplate resizeScene(HScene hs, HSceneTemplate hst)
	throws java.lang.IllegalStateException
    {
        return (null);
    }

    /**
     * Create the default {@link org.havi.ui.HScene HScene} for this
     * {@link org.havi.ui.HScreen HScreen}. This shall use the
     * {@link org.havi.ui.HGraphicsConfiguration
     * HGraphicsConfiguration} returned by calling
     * <pre>
     * screen.getDefaultHGraphicsDevice().getDefaultConfiguration()
     * </pre>
     *
     * @param screen the screen for which the {@link
     * org.havi.ui.HScene HScene} should be returned.
     * @return the default {@link org.havi.ui.HScene HScene} for this
     * {@link org.havi.ui.HScreen HScreen}. If the application has already
     * obtained an {@link org.havi.ui.HScene HScene} for this 
     * {@link org.havi.ui.HScreen HScreen}, then that HScene is returned.
     */
    public HScene getDefaultHScene(HScreen screen)
    {
	return (null);
    }

    /**
     * Create the default {@link org.havi.ui.HScene HScene} for the
     * default {@link org.havi.ui.HScreen HScreen} for this
     * application. This shall be identical to calling
     * <pre>
     * org.havi.ui.HSceneFactory.getDefaultHScene(org.havi.ui.HScreen.getDefaultHScreen())
     * </pre>
     *
     * @return the default {@link org.havi.ui.HScene HScene} for the
     * default {@link org.havi.ui.HScreen HScreen}. If the application has already
     * obtained an {@link org.havi.ui.HScene HScene} for the default 
     * {@link org.havi.ui.HScreen HScreen}, then that HScene is returned.
     */
    public HScene getDefaultHScene()
    {
	return (null);
    }

    /**
     * Create a full-screen {@link org.havi.ui.HScene HScene} on the
     * specified {@link org.havi.ui.HGraphicsDevice HGraphicsDevice}
     * or null if such an {@link org.havi.ui.HScene
     * HScene} cannot be generated.
     *
     * @param device the graphics device with which to create the
     * {@link org.havi.ui.HScene HScene}. This is obtained through a
     * {@link org.havi.ui.HGraphicsConfigTemplate
     * HGraphicsConfigTemplate} / {@link
     * org.havi.ui.HGraphicsConfiguration HGraphicsConfiguration}
     * sequence as described in the document for these classes.
     * @return a created full-screen {@link org.havi.ui.HScene
     * HScene} or null if this is not possible. 
     */
    public HScene getFullScreenScene(HGraphicsDevice device)
    {
        return (null);
    }

    /**
     * This method allows an application to dispose of its {@link
     * org.havi.ui.HScene HScene}, indicating that the application has
     * no further need for user interaction (i.e. its resources may be
     * released to the system, for future garbage collection). After
     * <code>dispose()</code> has been called the application may then
     * acquire another {@link org.havi.ui.HScene HScene}.
     * <p>
     * 
     * @param scene the {@link org.havi.ui.HScene HScene} to be
     * disposed of. 
     * @see HScene 
     * @see HScene#dispose
     */
    public void dispose(HScene scene)
    {
    }
}


