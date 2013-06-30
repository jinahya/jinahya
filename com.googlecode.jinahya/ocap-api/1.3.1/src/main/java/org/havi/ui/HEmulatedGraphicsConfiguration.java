package org.havi.ui;

/*
 * Copyright 2000-2003 by HAVi, Inc. Java is a trademark of Sun
 * Microsystems, Inc. All rights reserved.  
 */

/**
   An {@link org.havi.ui.HEmulatedGraphicsConfiguration
   HEmulatedGraphicsConfiguration} is a configuration for a
   &quot;virtual&quot; graphics device that may perform one or more
   emulations, e.g. in the ATSC context an {@link
   org.havi.ui.HEmulatedGraphicsDevice HEmulatedGraphicsDevice} might
   implement multiple {@link
   org.havi.ui.HEmulatedGraphicsConfiguration
   HEmulatedGraphicsConfigurations}, corresponding to each of the
   possible relationships to the high-definition display modes. The
   {@link org.havi.ui.HEmulatedGraphicsConfiguration
   HEmulatedGraphicsConfiguration} would be used to configure a device
   appropriately for rendering into, whilst mapping the emulated
   device onto the &quot;true&quot; physical display, e.g. by
   down-sampling to standard-definition display.
   
   <p>
   In essence the {@link org.havi.ui.HEmulatedGraphicsConfiguration
   HEmulatedGraphicsConfiguration} may be considered as a pair of
   {@link org.havi.ui.HGraphicsConfiguration HGraphicsConfiguration}
   objects: one describing the configuration of the emulation and the
   second describing the corresponding configuration of the
   implementation.

   <p>
   Hence, an {@link org.havi.ui.HGraphicsConfiguration
   HGraphicsConfiguration} may be considered as a special case of the
   {@link org.havi.ui.HEmulatedGraphicsConfiguration
   HEmulatedGraphicsConfiguration} class, where the emulation and
   implementation are equivalent.

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

 @see HEmulatedGraphicsDevice
 @see HGraphicsConfiguration

*/

public class HEmulatedGraphicsConfiguration
    extends HGraphicsConfiguration
{
    
    /**
     * It is not intended that applications should directly construct
     * {@link org.havi.ui.HEmulatedGraphicsConfiguration
     * HEmulatedGraphicsConfiguration} objects.
     * <p>
     * Creates an {@link org.havi.ui.HEmulatedGraphicsConfiguration
     * HEmulatedGraphicsConfiguration} object. See the class
     * description for details of constructor parameters and default
     * values.
     */
    protected HEmulatedGraphicsConfiguration()
    {
    }

    /**
     * Returns an {@link org.havi.ui.HGraphicsConfigTemplate
     * HGraphicsConfigTemplate} describing the virtual (emulation)
     * characteristics of the {@link
     * org.havi.ui.HEmulatedGraphicsDevice HEmulatedGraphicsDevice}.
     *
     * <p>
     * Overridden method from {@link
     * org.havi.ui.HGraphicsConfiguration HGraphicsConfiguration} --
     * for an {@link org.havi.ui.HEmulatedGraphicsConfiguration
     * HEmulatedGraphicsConfiguration} this returns a description of
     * the emulation characteristics.
     *
     * @return an {@link org.havi.ui.HGraphicsConfigTemplate
     * HGraphicsConfigTemplate} describing the virtual (emulation)
     * characteristics of the {@link
     * org.havi.ui.HEmulatedGraphicsDevice HEmulatedGraphicsDevice}.
     *
     * @see HGraphicsConfigTemplate
     * @see HGraphicsConfiguration
     * @see HEmulatedGraphicsDevice
     */
    public HGraphicsConfigTemplate getConfigTemplate()
    {
        return (null);
    }

    /**
     * Returns an {@link org.havi.ui.HGraphicsConfigTemplate
     * HGraphicsConfigTemplate} describing the virtual (emulation)
     * characteristics of the {@link
     * org.havi.ui.HEmulatedGraphicsDevice HEmulatedGraphicsDevice}.
     *
     * @return an {@link org.havi.ui.HGraphicsConfigTemplate
     * HGraphicsConfigTemplate} describing the virtual (emulation)
     * characteristics of the {@link
     * org.havi.ui.HEmulatedGraphicsDevice HEmulatedGraphicsDevice}.
     *
     * @see HGraphicsConfigTemplate
     * @see HEmulatedGraphicsDevice
     */
    public HGraphicsConfigTemplate getEmulation()
    {
        return (null);
    }

    /**
     * Returns an {@link org.havi.ui.HGraphicsConfigTemplate
     * HGraphicsConfigTemplate} describing the physical
     * (implementation) characteristics of the {@link
     * org.havi.ui.HEmulatedGraphicsDevice HEmulatedGraphicsDevice}.
     *
     * @return an {@link org.havi.ui.HGraphicsConfigTemplate
     * HGraphicsConfigTemplate} describing the physical
     * (implementation) characteristics of the {@link
     * org.havi.ui.HEmulatedGraphicsDevice HEmulatedGraphicsDevice}.
     *
     * @see HGraphicsConfigTemplate
     * @see HEmulatedGraphicsDevice 
     */
    public HGraphicsConfigTemplate getImplementation()
    {
        return (null);
    }
}

