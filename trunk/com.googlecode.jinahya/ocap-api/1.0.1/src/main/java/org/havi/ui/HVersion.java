package org.havi.ui;

/*
 * Copyright 2000-2003 by HAVi, Inc. Java is a trademark of Sun
 * Microsystems, Inc. All rights reserved.  
 */

/**
 * The <code>HVersion</code> interface defines some
 * versioning constants that are accessible by using the
 * java.lang.System method getProperty, with the appropriate property
 * name.
 * <p>
 * Note that it is a valid implementation to return empty strings for
 * HAVI_IMPLEMENTATION_NAME, HAVI_IMPLEMENTATION_VENDOR and
 * HAVI_IMPLEMENTATION_VERSION strings.
 * <p>
 * In MHP, a call to <code>getProperty()</code> when referencing the
 * constants listed in column <i>Constant</i> in the table below
 * shall return a string as listed in column <i>Value</i>.
 * <table border>
 *   <tr><th>Constant</th><th>Value</th></tr>
 *   <tr><td>HAVI_SPECIFICATION_VENDOR</td>
 *       <td>&quot;DVB&quot;</td></tr>
 *   <tr><td>HAVI_SPECIFICATION_NAME</td>
 *       <td>&quot;MHP&quot;</td></tr>
 *   <tr><td>HAVI_SPECIFICATION_VERSION</td>
 *       <td>&quot;&lt;version&gt;&quot;</td></tr>
 * </table>
 * &quot;&lt;version&gt;&quot; shall be the MHP version to which this
 * HAVi implementation is conformant, e.g.: &quot;1.0&quot; or
 * &quot;1.0.1&quot; or &quot;1.0.2&quot; or &quot;1.0.3&quot;.
 */

public interface HVersion 
{
    /** 
     * A string constant describing the HAVi specification vendor, as
     * returned via
     * java.lang.System.getProperty(HVersion.HAVI_SPECIFICATION_VENDOR).  
     */
    public static final String HAVI_SPECIFICATION_VENDOR = 
      "havi.specification.vendor";

    /** 
     * A string constant describing the HAVi specification name, as
     * returned via
     * java.lang.System.getProperty(HVersion.HAVI_SPECIFICATION_NAME).
     */
    public static final String HAVI_SPECIFICATION_NAME = 
      "havi.specification.name";

        
    /** 
     * A string constant describing the HAVi specification version, as
     * returned via
     * java.lang.System.getProperty(HVersion.HAVI_SPECIFICATION_VERSION).
     */
    public static final String HAVI_SPECIFICATION_VERSION = 
      "havi.specification.version";

    /** 
     * A string constant describing the HAVi implementation vendor, as
     * returned via
     * java.lang.System.getProperty(HVersion.HAVI_IMPLEMENTATION_VENDOR).
     */
    public static final String HAVI_IMPLEMENTATION_VENDOR = 
      "havi.implementation.vendor"; 
        
    /** 
     * A string constant describing the HAVi implementation version,
     * as returned via
     * java.lang.System.getProperty(HVersion.HAVI_IMPLEMENTATION_VERSION).
     */
    public static final String HAVI_IMPLEMENTATION_VERSION = 
      "havi.implementation.version"; 
        
    /** 
     * A string constant describing the HAVi implementation name, as
     * returned via
     * java.lang.System.getProperty(HVersion.HAVI_IMPLEMENTATION_NAME).
     */
    public static final String HAVI_IMPLEMENTATION_NAME = 
      "havi.implementation.name"; 

}

