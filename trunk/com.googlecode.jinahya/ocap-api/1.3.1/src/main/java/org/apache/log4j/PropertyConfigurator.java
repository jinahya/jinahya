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


// Contibutors: "Luke Blanshard" <Luke@quiq.com>
//              "Mark DONSZELMANN" <Mark.Donszelmann@cern.ch>
//               Anders Kristensen <akristensen@dynamicsoft.com>

package org.apache.log4j;

import java.util.Properties;

import org.apache.log4j.spi.Configurator;
import org.apache.log4j.spi.Filter;
import org.apache.log4j.spi.LoggerRepository;

/**
   Allows the configuration of log4j from an external file.  See
   <b>{@link #doConfigure(String, LoggerRepository)}</b> for the
   expected format.

   <p>It is sometimes useful to see how log4j is reading configuration
   files. You can enable log4j internal logging by defining the
   <b>log4j.debug</b> variable.

   <P>As of log4j version 0.8.5, at class initialization time class,
   the file <b>log4j.properties</b> will be searched from the search
   path used to load classes. If the file can be found, then it will
   be fed to the {@link PropertyConfigurator#configure(java.net.URL)}
   method.

   <p>The <code>PropertyConfigurator</code> does not handle the
   advanced configuration features supported by the {@link
   org.apache.log4j.xml.DOMConfigurator DOMConfigurator} such as
   support custom {@link org.apache.log4j.spi.ErrorHandler ErrorHandlers},
   nested appenders such as the {@link org.apache.log4j.AsyncAppender
   AsyncAppender}, etc.

   This version of PropertyConfigurator has been modified to support the definition of groups as described in {@link Logger} by adding a 'group' entry using this format:
   <p/>
   log4j.group.NAME=LEVEL
   <p/>
   All loggers retrieved via {@link Logger#getLogger(String, String)} or {@link Logger#getLogger(Class, String)} with the same group name will have their effective level set to the provided level.  
   <p/>
   This group-defined Level verbosity threshold can be overridden by setting an explicit level on the Logger itself.  
   <p>
   All option <em>values</em> admit variable substitution. The
   syntax of variable substitution is similar to that of Unix
   shells. The string between an opening <b>&quot;${&quot;</b> and
   closing <b>&quot;}&quot;</b> is interpreted as a key. The value of
   the substituted variable can be defined as a system property or in
   the configuration file itself. The value of the key is first
   searched in the system properties, and if not found there, it is
   then searched in the configuration file being parsed.  The
   corresponding value replaces the ${variableName} sequence. For
   example, if <code>java.home</code> system property is set to
   <code>/home/xyz</code>, then every occurrence of the sequence
   <code>${java.home}</code> will be interpreted as
   <code>/home/xyz</code>.


   @author Ceki G&uuml;lc&uuml;
   @author Anders Kristensen
   @since 0.8.1 */
public class PropertyConfigurator implements Configurator {

  /** Key for specifying the {@link org.apache.log4j.spi.LoggerFactory
  LoggerFactory}.  Currently set to "<code>log4j.loggerFactory</code>".  */
  public static final String LOGGER_FACTORY_KEY = "log4j.loggerFactory";

  /**
    Read configuration from a file. <b>The existing configuration is
    not cleared nor reset.</b> If you require a different behavior,
    then call {@link  LogManager#resetConfiguration
    resetConfiguration} method before calling
    <code>doConfigure</code>.

    <p>The configuration file consists of statements in the format
    <code>key=value</code>. The syntax of different configuration
    elements are discussed below.

    <h3>Repository-wide threshold</h3>

    <p>The repository-wide threshold filters logging requests by level
    regardless of logger. The syntax is:

    <pre>
    log4j.threshold=[level]
    </pre>

    <p>The level value can consist of the string values OFF, FATAL,
    ERROR, WARN, INFO, DEBUG, ALL or a <em>custom level</em> value. A
    custom level value can be specified in the form
    level#classname. By default the repository-wide threshold is set
    to the lowest possible value, namely the level <code>ALL</code>.
    </p>


    <h3>Appender configuration</h3>

    <p>Appender configuration syntax is:
    <pre>
    # For appender named <i>appenderName</i>, set its class.
    # Note: The appender name can contain dots.
    log4j.appender.appenderName=fully.qualified.name.of.appender.class

    # Set appender specific options.
    log4j.appender.appenderName.option1=value1
    ...
    log4j.appender.appenderName.optionN=valueN
    </pre>

    For each named appender you can configure its {@link Layout}. The
    syntax for configuring an appender's layout is:
    <pre>
    log4j.appender.appenderName.layout=fully.qualified.name.of.layout.class
    log4j.appender.appenderName.layout.option1=value1
    ....
    log4j.appender.appenderName.layout.optionN=valueN
    </pre>

    The syntax for adding {@link Filter}s to an appender is:
    <pre>
    log4j.appender.appenderName.filter.ID=fully.qualified.name.of.filter.class
    log4j.appender.appenderName.filter.ID.option1=value1
    ...
    log4j.appender.appenderName.filter.ID.optionN=valueN
    </pre>
    The first line defines the class name of the filter identified by ID;
    subsequent lines with the same ID specify filter option - value
    paris. Multiple filters are added to the appender in the lexicographic
    order of IDs.

    The syntax for adding an {@link ErrorHandler} to an appender is:
    <pre>
    log4j.appender.appenderName.errorhandler=fully.qualified.name.of.filter.class
    log4j.appender.appenderName.errorhandler.root-ref={true|false}
    log4j.appender.appenderName.errorhandler.logger-ref=loggerName
    log4j.appender.appenderName.errorhandler.appender-ref=appenderName
    log4j.appender.appenderName.errorhandler.option1=value1
    ...
    log4j.appender.appenderName.errorhandler.optionN=valueN
    </pre>

    <h3>Configuring loggers</h3>

    <p>The syntax for configuring the root logger is:
    <pre>
      log4j.rootLogger=[level], appenderName, appenderName, ...
    </pre>

    <p>This syntax means that an optional <em>level</em> can be
    supplied followed by appender names separated by commas.

    <p>The level value can consist of the string values OFF, FATAL,
    ERROR, WARN, INFO, DEBUG, ALL or a <em>custom level</em> value. A
    custom level value can be specified in the form
    <code>level#classname</code>.

    <p>If a level value is specified, then the root level is set
    to the corresponding level.  If no level value is specified,
    then the root level remains untouched.

    <p>The root logger can be assigned multiple appenders.

    <p>Each <i>appenderName</i> (separated by commas) will be added to
    the root logger. The named appender is defined using the
    appender syntax defined above.

    <p>For non-root categories the syntax is almost the same:
    <pre>
    log4j.logger.logger_name=[level|INHERITED|NULL], appenderName, appenderName, ...
    </pre>

    <p>The meaning of the optional level value is discussed above
    in relation to the root logger. In addition however, the value
    INHERITED can be specified meaning that the named logger should
    inherit its level from the logger hierarchy.

    <p>If no level value is supplied, then the level of the
    named logger remains untouched.

    <p>By default categories inherit their level from the
    hierarchy. However, if you set the level of a logger and later
    decide that that logger should inherit its level, then you should
    specify INHERITED as the value for the level value. NULL is a
    synonym for INHERITED.

    <p>Similar to the root logger syntax, each <i>appenderName</i>
    (separated by commas) will be attached to the named logger.

    <p>See the <a href="../../../../manual.html#additivity">appender
    additivity rule</a> in the user manual for the meaning of the
    <code>additivity</code> flag.

    <h3>ObjectRenderers</h3>

    You can customize the way message objects of a given type are
    converted to String before being logged. This is done by
    specifying an {@link org.apache.log4j.or.ObjectRenderer ObjectRenderer}
    for the object type would like to customize.

    <p>The syntax is:

    <pre>
    log4j.renderer.fully.qualified.name.of.rendered.class=fully.qualified.name.of.rendering.class
    </pre>

    As in,
    <pre>
    log4j.renderer.my.Fruit=my.FruitRenderer
    </pre>

   <h3>ThrowableRenderer</h3>

   You can customize the way an instance of Throwable is
   converted to String before being logged. This is done by
   specifying an {@link org.apache.log4j.spi.ThrowableRenderer ThrowableRenderer}.

   <p>The syntax is:

   <pre>
   log4j.throwableRenderer=fully.qualified.name.of.rendering.class
   log4j.throwableRenderer.paramName=paramValue
   </pre>

   As in,
   <pre>
   log4j.throwableRenderer=org.apache.log4j.EnhancedThrowableRenderer
   </pre>

    <h3>Logger Factories</h3>

    The usage of custom logger factories is discouraged and no longer
    documented.

    <h3>Resetting Hierarchy</h3>

    The hierarchy will be reset before configuration when
    log4j.reset=true is present in the properties file.

    <h3>Example</h3>

    <p>An example configuration is given below. Other configuration
    file examples are given in the <code>examples</code> folder.

    <pre>

    # Set options for appender named "A1".
    # Appender "A1" will be a SyslogAppender
    log4j.appender.A1=org.apache.log4j.net.SyslogAppender

    # The syslog daemon resides on www.abc.net
    log4j.appender.A1.SyslogHost=www.abc.net

    # A1's layout is a PatternLayout, using the conversion pattern
    # <b>%r %-5p %c{2} %M.%L %x - %m\n</b>. Thus, the log output will
    # include # the relative time since the start of the application in
    # milliseconds, followed by the level of the log request,
    # followed by the two rightmost components of the logger name,
    # followed by the callers method name, followed by the line number,
    # the nested disgnostic context and finally the message itself.
    # Refer to the documentation of {@link PatternLayout} for further information
    # on the syntax of the ConversionPattern key.
    log4j.appender.A1.layout=org.apache.log4j.PatternLayout
    log4j.appender.A1.layout.ConversionPattern=%-4r %-5p %c{2} %M.%L %x - %m\n

    # Set options for appender named "A2"
    # A2 should be a RollingFileAppender, with maximum file size of 10 MB
    # using at most one backup file. A2's layout is TTCC, using the
    # ISO8061 date format with context printing enabled.
    log4j.appender.A2=org.apache.log4j.RollingFileAppender
    log4j.appender.A2.MaxFileSize=10MB
    log4j.appender.A2.MaxBackupIndex=1
    log4j.appender.A2.layout=org.apache.log4j.TTCCLayout
    log4j.appender.A2.layout.ContextPrinting=enabled
    log4j.appender.A2.layout.DateFormat=ISO8601

    # Root logger set to DEBUG using the A2 appender defined above.
    log4j.rootLogger=DEBUG, A2

    # Logger definitions:
    # The SECURITY logger inherits is level from root. However, it's output
    # will go to A1 appender defined above. It's additivity is non-cumulative.
    log4j.logger.SECURITY=INHERIT, A1
    log4j.additivity.SECURITY=false

    # Only warnings or above will be logged for the logger "SECURITY.access".
    # Output will go to A1.
    log4j.logger.SECURITY.access=WARN


    # The logger "class.of.the.day" inherits its level from the
    # logger hierarchy.  Output will go to the appender's of the root
    # logger, A2 in this case.
    log4j.logger.class.of.the.day=INHERIT
    </pre>

    <p>Refer to the <b>setOption</b> method in each Appender and
    Layout for class specific options.

    <p>Use the <code>#</code> or <code>!</code> characters at the
    beginning of a line for comments.

   <p>
   @param configFileName The name of the configuration file where the
   configuration information is stored.

    
     @throws SecurityException if the caller does not have
            MonitorAppPermission("logger.config").
     */
  public
  void doConfigure(String configFileName, LoggerRepository hierarchy) {

  }

  /**
      
     @throws SecurityException if the caller does not have
            MonitorAppPermission("logger.config").
    */
  static
  public
  void configure(String configFilename) {
  }

  /**
     Read configuration options from url <code>configURL</code>.

     @since 0.8.2
    
     @throws SecurityException if the caller does not have
            MonitorAppPermission("logger.config").
   */
  public
  static
  void configure(java.net.URL configURL) {
  }


  /**
     Read configuration options from <code>properties</code>.

     See {@link #doConfigure(String, LoggerRepository)} for the expected format.
    
     @throws SecurityException if the caller does not have
            MonitorAppPermission("logger.config").
  */
  static
  public
  void configure(Properties properties) {
  }

  /**
     Like {@link #configureAndWatch(String, long)} except that the
     default delay as defined by {@link FileWatchdog#DEFAULT_DELAY} is
     used.

     @param configFilename A file in key=value format.

    
     @throws SecurityException if the caller does not have
            MonitorAppPermission("logger.config").
     */
  static
  public
  void configureAndWatch(String configFilename) {
  }


  /**
     Read the configuration file <code>configFilename</code> if it
     exists. Moreover, a thread will be created that will periodically
     check if <code>configFilename</code> has been created or
     modified. The period is determined by the <code>delay</code>
     argument. If a change or file creation is detected, then
     <code>configFilename</code> is read to configure log4j.

      @param configFilename A file in key=value format.
      @param delay The delay in milliseconds to wait between each check.
    
     @throws SecurityException if the caller does not have
            MonitorAppPermission("logger.config").
     */
  static
  public
  void configureAndWatch(String configFilename, long delay) {
  }


  /**
     Read configuration options from <code>properties</code>.

     See {@link #doConfigure(String, LoggerRepository)} for the expected format.
    
     @throws SecurityException if the caller does not have
            MonitorAppPermission("logger.config").
     */
  public
  void doConfigure(Properties properties, LoggerRepository hierarchy) {
  }

  /**
     Read configuration options from url <code>configURL</code>.
      
     @throws SecurityException if the caller does not have
            MonitorAppPermission("logger.config").
    */
  public
  void doConfigure(java.net.URL configURL, LoggerRepository hierarchy) {
  }

}
