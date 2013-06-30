/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.log4j.xml;

import java.io.InputStream;
import java.io.Reader;
import java.net.URL;
import java.util.Properties;

import javax.xml.parsers.FactoryConfigurationError;

import org.apache.log4j.Logger;
import org.apache.log4j.config.PropertySetter;
import org.apache.log4j.spi.Configurator;
import org.apache.log4j.spi.LoggerRepository;
import org.w3c.dom.Element;

// Contributors:   Mark Womack
//                 Arun Katkere 

/**
   Use this class to initialize the log4j environment using a DOM tree.

   <p>The DTD is specified in <a
   href="doc-files/log4j.dtd"><b>log4j.dtd</b></a>.

   <p>Sometimes it is useful to see how log4j is reading configuration
   files. You can enable log4j internal logging by defining the
   <b>log4j.debug</b> variable on the java command
   line. Alternatively, set the <code>debug</code> attribute in the
   <code>log4j:configuration</code> element. As in
<pre>
   &lt;log4j:configuration <b>debug="true"</b> xmlns:log4j="http://jakarta.apache.org/log4j/">
   ...
   &lt;/log4j:configuration>
</pre>

 * This version of DOMConfigurator has been modified to support the definition of groups as described in {@link Logger} by adding a 'group' element:
<pre>
   &lt;group name=&quot;GROUPNAME&quot; level=&quot;LEVEL&quot;/&gt;
</pre>
 
   All loggers retrieved via {@link Logger#getLogger(String, String)} or {@link Logger#getLogger(Class, String)} with the same group name will have their effective level set to the provided level.  
   <p/>
   This group-defined Level verbosity threshold can be overridden by setting an explicit level on the Logger itself.  
   <p>
   There are sample XML files included in the package.
   
   @author Christopher Taylor
   @author Ceki G&uuml;lc&uuml;
   @author Anders Kristensen

   @since 0.8.3 */
public class DOMConfigurator implements Configurator {

  /**
   * No argument constructor.
  */
  public
  DOMConfigurator ()
  { 
  }

  /**
     Configure log4j using a <code>configuration</code> element as
     defined in the log4j.dtd. 

    
     @throws SecurityException if the caller does not have
            MonitorAppPermission("logger.config").
  */
  static
  public
  void configure (Element element) {
  }

 /**
    Like {@link #configureAndWatch(String, long)} except that the
    default delay as defined by {@link FileWatchdog#DEFAULT_DELAY} is
    used. 
    
    @param configFilename A log4j configuration file in XML format.
    
     @throws SecurityException if the caller does not have
            MonitorAppPermission("logger.config").
  */
  static 
  public 
  void configureAndWatch(String configFilename)
  {
  }

  /**
     Read the configuration file <code>configFilename</code> if it
     exists. Moreover, a thread will be created that will periodically
     check if <code>configFilename</code> has been created or
     modified. The period is determined by the <code>delay</code>
     argument. If a change or file creation is detected, then
     <code>configFilename</code> is read to configure log4j.  

      @param configFilename A log4j configuration file in XML format.
      @param delay The delay in milliseconds to wait between each check.
    
     @throws SecurityException if the caller does not have
            MonitorAppPermission("logger.config").
  */
  static
  public
  void configureAndWatch(String configFilename, long delay) {
  }

  /**
     @throws SecurityException if the caller does not have
            MonitorAppPermission("logger.config").
   */
  public
  void doConfigure(final String filename, LoggerRepository repository) {
  }
  

  /**
     @throws SecurityException if the caller does not have
            MonitorAppPermission("logger.config").
   */
  public
  void doConfigure(final URL url, LoggerRepository repository) {
  }

  /**
     Configure log4j by reading in a log4j.dtd compliant XML
     configuration file.

     @throws SecurityException if the caller does not have
            MonitorAppPermission("logger.config").
  */
  public
  void doConfigure(final InputStream inputStream, LoggerRepository repository) 
                                          throws FactoryConfigurationError {
  }

  /**
     Configure log4j by reading in a log4j.dtd compliant XML
     configuration file.

     @throws SecurityException if the caller does not have
            MonitorAppPermission("logger.config").
  */
  public
  void doConfigure(final Reader reader, LoggerRepository repository) 
                                          throws FactoryConfigurationError {
  }

  /**
     Configure by taking in an DOM element. 

     @throws SecurityException if the caller does not have
            MonitorAppPermission("logger.config").
  */
  public void doConfigure(Element element, LoggerRepository repository) {
  }

  
  /**
     A static version of {@link #doConfigure(String, LoggerRepository)}.  
   
     @throws SecurityException if the caller does not have
            MonitorAppPermission("logger.config").
   */
  static
  public
  void configure(String filename) throws FactoryConfigurationError {
  }

  /**
     A static version of {@link #doConfigure(URL, LoggerRepository)}.

     @throws SecurityException if the caller does not have
            MonitorAppPermission("logger.config").
   */
  static
  public
  void configure(URL url) throws FactoryConfigurationError {
  }

    /**
     * Substitutes property value for any references in expression.
     *
     * @param value value from configuration file, may contain
     *              literal text, property references or both
     * @param props properties.
     * @return evaluated expression, may still contain expressions
     *         if unable to expand.
     *
     * 
     * @throws SecurityException if the caller does not have
     *       MonitorAppPermission("logger.config").
     * @since 1.2.15
     */
    public static String subst(final String value, final Properties props) {
        return null;
    }

    /**
     * Sets a parameter based from configuration file content.
     *
     * @param elem       param element, may not be null.
     * @param propSetter property setter, may not be null.
     * @param props      properties
     *      
     * @throws SecurityException if the caller does not have
     *       MonitorAppPermission("logger.config").
     * @since 1.2.15
     */
    public static void setParameter(final Element elem,
                                    final PropertySetter propSetter,
                                    final Properties props) {
    }

    /**
     * Creates an object and processes any nested param elements
     * but does not call activateOptions.  If the class also supports
     * UnrecognizedElementParser, the parseUnrecognizedElement method
     * will be call for any child elements other than param.
     *
     * @param element       element, may not be null.
     * @param props         properties
     * @param expectedClass interface or class expected to be implemented
     *                      by created class
     * @return created class or null.
     * @throws Exception thrown if the contain object should be abandoned.
     * @throws SecurityException if the caller does not have
     *       MonitorAppPermission("logger.config").
     * @since 1.2.15
     */
    public static Object parseElement(final Element element,
                                             final Properties props,
                                             final Class expectedClass) throws Exception {
        return null;
    }
}
