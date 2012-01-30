package org.havi.ui;

/*
 * Copyright 2000-2003 by HAVi, Inc. Java is a trademark of Sun
 * Microsystems, Inc. All rights reserved.  
 */

/**
   The {@link org.havi.ui.HVideoConfiguration}
   class describes the characteristics (settings) of an {@link
   org.havi.ui.HVideoDevice}.  There can be many {@link
   org.havi.ui.HVideoConfiguration} objects
   associated with a single {@link org.havi.ui.HVideoDevice}.

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

 @see HVideoDevice

*/

public class HVideoConfiguration
    extends HScreenConfiguration
{    
    /**
     * It is not intended that applications should directly construct
     * {@link org.havi.ui.HVideoConfiguration}
     * objects.
     * <p>
     * Creates an {@link org.havi.ui.HVideoConfiguration}
     * object.  See the class description for
     * details of constructor parameters and default values.
     */
    protected HVideoConfiguration()
    {
    }

    /**
     * Returns the {@link org.havi.ui.HVideoDevice}
     * associated with this {@link org.havi.ui.HVideoConfiguration}.
     *
     * @return the {@link org.havi.ui.HVideoDevice}
     * object that is associated with this {@link
     * org.havi.ui.HVideoConfiguration},
     */
    public HVideoDevice getDevice()
    {
        return (null);
    }

    /**
     * Returns an {@link org.havi.ui.HVideoConfigTemplate}
     * object that describes and uniquely
     * identifies this {@link org.havi.ui.HVideoConfiguration}.
     * <p>
     * Hence, the following sequence should return the original {@link
     * org.havi.ui.HVideoConfiguration}.
     * <pre>
     * HVideoDevice.getBestMatch({@link
     * org.havi.ui.HVideoConfiguration}.getConfigTemplate())
     * </pre>
     * <p>
     * Features that are implemented in the {@link
     * org.havi.ui.HVideoConfiguration} will
     * return {@link HScreenConfigTemplate#REQUIRED}
     * priority. Features that are not implemented in the {@link
     * org.havi.ui.HVideoConfiguration HVideoConfiguration}
     * will return {@link HScreenConfigTemplate#REQUIRED_NOT}
     * priority. Preferences that are not filled in by
     * the platform will return {@link
     * HScreenConfigTemplate#DONT_CARE} priority.
     *
     * @return an {@link org.havi.ui.HVideoConfigTemplate}
     * object which both describes and uniquely
     * identifies this {@link org.havi.ui.HVideoConfiguration}.
     * */
    public HVideoConfigTemplate getConfigTemplate()
    {
        return (null);
    }
}

