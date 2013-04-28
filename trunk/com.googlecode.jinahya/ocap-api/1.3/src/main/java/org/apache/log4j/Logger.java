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

package org.apache.log4j;

import org.apache.log4j.spi.LoggerFactory;

/**
  This is the central class in the log4j package. Most logging
  operations, except configuration, are done through this class.
 <p/>
 <b>NOTE: </b>This class is based on the standard log4j 1.2 Logger class with additional support for associating 
 Loggers in order to simplify configuration of groups of Loggers, as well as the string concatenation-avoidance helper methods from the LogSF utility class. 
 <p/>
 <b>Support for Logger 'groups'</b>
 <p/>
 The common practice of using a fully qualified class name as the argument to {@link #getLogger(String)} simplifies configuration, 
 as inherited Loggers share a Level threshold and appender configuration unless explicitly overridden.  However, Loggers may be functionally related 
 but not part of a common package structure, requiring multiple entries in the log4j configuration file to configure these related Loggers with the same Level threshold.
 <p/>
 To support the ability to easily configure the verbosity threshold of functionally-related Loggers, the {@link #getLogger} methods have been overloaded 
 to include versions which accept a group name parameter.  Groups act as an 'alias' for controlling the verbosity threshold of a number of related Loggers.  
 <p/>
 If a Logger is retrieved using one of the forms which accepts a group name, the group name and an associated level can be defined in the log4j configuration file.  
 All loggers associated with that group name will log at the specified level or greater severity.
 <p/>
 The root logger logging severity threshold can be overridden by a group definition, which can be overridden by an explicit logger definition.
 <p/>
 Groups support is implemented by setting the Logger's 'effective level' to the group level if applicable, causing is(Level)Enabled methods to return true based on the group severity threshold if applicable. 
 <p/>
 See {@link PropertyConfigurator} and {@link DOMConfigurator} for information on how to specify a group definition for properties-formatted and XML-formatted log4j configuration files.
 <p/>
 <b>Parameterized messages</b>
 <p/>
 The string concatenation-avoidance helper methods from the LogSF utility class have been incorporated into the Logger class, supporting parameterized messages and simplifying usage of the Logging API.
 <p/>
 These methods overload the level-specific logging methods with additional forms supporting a number of commonly-needed arguments, delaying string concatenation until after the level severity threshold check.  All level-specific logging methods also provide a form accepting an array of Objects, allowing any number of replacements.  
 <p/>
 <b>Format of parameterized messages</b>
 <p/>
 The first argument of the forms supporting parameterized messages is a String which can contain pairs of braces {} as placeholders where the additional method arguments will be placed, in order, after the level severity threshold check has passed.
 <p/>
 Example: log.info("Found {} matches", matchCount);
 
    @since log4j 1.2

  @author Ceki G&uuml;lc&uuml; */
public class Logger extends Category 
{

  protected Logger(String name)
  {
      super((String)null);
  }
    
  /**
Log a message object with the {@link Level#FINE FINE} level which
is just an alias for the {@link Level#DEBUG DEBUG} level.
<p>This method first checks if this category is <code>DEBUG</code>
enabled by comparing the level of this category with the {@link
Level#DEBUG DEBUG} level. If this category is
<code>DEBUG</code> enabled, then it converts the message object
(passed as parameter) to a string by invoking the appropriate
{@link org.apache.log4j.or.ObjectRenderer}. It then proceeds to call all the
registered appenders in this category and also higher in the
hierarchy depending on the value of the additivity flag.
<p><b>WARNING</b> Note that passing a {@link Throwable} to this
method will print the name of the <code>Throwable</code> but no
stack trace. To print a stack trace use the {@link #debug(Object,
Throwable)} form instead.
@param message the message object to log. */
  //public
  //void fine(Object message) {
  //  if(repository.isDisabled(Level.DEBUG_INT))
  //	return;
  //  if(Level.DEBUG.isGreaterOrEqual(this.getChainedLevel())) {
  //	forcedLog(FQCN, Level.DEBUG, message, null);
  //  }
  //}


  /**
   Log a message object with the <code>FINE</code> level including
   the stack trace of the {@link Throwable} <code>t</code> passed as
   parameter.
 <p>See {@link #fine(Object)} form for more detailed information.
 @param message the message object to log.
   @param t the exception to log, including its stack trace.  */
  //public
  //void fine(Object message, Throwable t) {
  //  if(repository.isDisabled(Level.DEBUG_INT))
  //	return;
  //  if(Level.DEBUG.isGreaterOrEqual(this.getChainedLevel()))
  //	forcedLog(FQCN, Level.FINE, message, t);
  //}

  /**
   * Retrieve a logger named according to the value of the
   * <code>name</code> parameter. If the named logger already exists,
   * then the existing instance will be returned. Otherwise, a new
   * instance is created.  
   *
   * <p>By default, loggers do not have a set level but inherit it
   * from their neareast ancestor with a set level. This is one of the
   * central features of log4j.
   *
   * @param name The name of the logger to retrieve.  
  */
  static public Logger getLogger(String name)
  {
    return null;
  }

  /**
   * Shorthand for <code>getLogger(clazz.getName())</code>.
   *
   * @param clazz The name of <code>clazz</code> will be used as the
   * name of the logger to retrieve.  See {@link #getLogger(String)}
   * for more detailed information.
   */
  static public Logger getLogger(Class clazz)
  {
    return null;
  }

  /**
   * Return the root logger for the current logger repository.
   * <p>
   * The {@link #getName Logger.getName()} method for the root logger always returns
   * string value: "root". However, calling
   * <code>Logger.getLogger("root")</code> does not retrieve the root
   * logger but a logger just under root named "root".
   * <p>
   * In other words, calling this method is the only way to retrieve the 
   * root logger.
   */
  public static Logger getRootLogger()
  {
    return null;
  }

  /**
     Like {@link #getLogger(String)} except that the type of logger
     instantiated depends on the type returned by the {@link
     LoggerFactory#makeNewLoggerInstance} method of the
     <code>factory</code> parameter.
   <p>This method is intended to be used by sub-classes.
   @param name The name of the logger to retrieve.
   @param factory A {@link LoggerFactory} implementation that will
     actually create a new Instance.
   @since 0.8.5 */
  public static Logger getLogger(String name, LoggerFactory factory)
  {
    return null;
  }


    /**
     * Retrieve a logger named according to the value of the
     * <code>name</code> parameter. If the named logger already exists,
     * then the existing instance will be returned. Otherwise, a new
     * instance is created.
     *
     * <p>By default, loggers do not have a set level but inherit it
     * from their neareast ancestor with a set level. This is one of the
     * central features of log4j.
     *
     * @param name The name of the logger to retrieve.
     * @param group The group this logger is associated with
    */
    static public Logger getLogger(String name, String group)
    {
      return null;
    }

    /**
     * Shorthand for <code>getLogger(clazz.getName())</code>.
     *
     * @param clazz The name of <code>clazz</code> will be used as the
     * name of the logger to retrieve.  See {@link #getLogger(String)}
     * for more detailed information.
     * @param group The group this logger is associated with
     */
    static public Logger getLogger(Class clazz, String group)
    {
      return null;
    }

    /**
       Like {@link #getLogger(String)} except that the type of logger
       instantiated depends on the type returned by the {@link
       LoggerFactory#makeNewLoggerInstance} method of the
       <code>factory</code> parameter.

       <p>This method is intended to be used by sub-classes.

       @param name The name of the logger to retrieve.

       @param group The group this logger is associated with

       @param factory A {@link LoggerFactory} implementation that will
       actually create a new Instance.

       @since 0.8.5 */
    public static Logger getLogger(String name, String group, LoggerFactory factory)
    {
      return null;
    }

    /**
     * Log a message object with the {@link org.apache.log4j.Level#TRACE TRACE} level.
     *
     * @param message the message object to log.
     * @see #debug(Object) for an explanation of the logic applied.
     * @since 1.2.12
     */
    public void trace(Object message)
    {
    }

    /**
     * Log a message object with the <code>TRACE</code> level including the
     * stack trace of the {@link Throwable}<code>t</code> passed as parameter.
     *
     * <p>
     * See {@link #debug(Object)} form for more detailed information.
     * </p>
     *
     * @param message the message object to log.
     * @param t the exception to log, including its stack trace.
     * @since 1.2.12
     */
    public void trace(Object message, Throwable t)
    {
    }

    /**
     * Check whether this category is enabled for the TRACE  Level.
     * @since 1.2.12
     *
     * @return boolean - <code>true</code> if this category is enabled for level
     *         TRACE, <code>false</code> otherwise.
     */
    public boolean isTraceEnabled()
    {
          return false;
    }

    /**
     * Log a parameterized message at trace level.
     * @param pattern pattern, may be null.
     * @param arguments an array of arguments to be
     *          formatted and substituted.
     */
    public void trace(final String pattern, final Object[] arguments)
    {
    }

    /**
     * Log a parameterized message at debug level.
     * @param pattern pattern, may be null.
     * @param arguments an array of arguments to be formatted and substituted.
     */
    public void debug(final String pattern, final Object[] arguments)
    {
    }

    /**
     * Log a parameterized message at info level.
     * @param pattern pattern, may be null.
     * @param arguments an array of arguments to be formatted and substituted.
     */
    public void info(final String pattern, final Object[] arguments)
    {
    }

    /**
     * Log a parameterized message at warn level.
     * @param pattern pattern, may be null.
     * @param arguments an array of arguments to be formatted and substituted.
     */
    public void warn(final String pattern, final Object[] arguments)
    {
    }

    /**
     * Log a parameterized message at error level.
     * @param pattern pattern, may be null.
     * @param arguments an array of arguments to be formatted and substituted.
     */
    public void error(final String pattern, final Object[] arguments)
    {
    }

    /**
     * Log a parameterized message at fatal level.
     * @param pattern pattern, may be null.
     * @param arguments an array of arguments to be formatted and substituted.
     */
    public void fatal(final String pattern, final Object[] arguments)
    {
    }

    /**
         * Log a parameterized message at trace level.
       * @param t throwable, may be null.
         * @param pattern pattern, may be null.
         * @param arguments an array of arguments to be
         *          formatted and substituted.
         */
    public void trace(final Throwable t,
                      final String pattern,
                      final Object[] arguments)
    {
    }

    /**
     * Log a parameterized message at debug level.
     * @param t throwable, may be null.
     * @param pattern pattern, may be null.
     * @param arguments an array of arguments to be formatted and substituted.
     */
    public void debug(final Throwable t,
                      final String pattern,
                      final Object[] arguments)
    {
    }

    /**
     * Log a parameterized message at info level.
     * @param t throwable, may be null.
     * @param pattern pattern, may be null.
     * @param arguments an array of arguments to be formatted and substituted.
     */
    public void info(final Throwable t,
                     final String pattern,
                     final Object[] arguments)
    {
    }

    /**
     * Log a parameterized message at warn level.
     * @param t throwable, may be null.
     * @param pattern pattern, may be null.
     * @param arguments an array of arguments to be formatted and substituted.
     */
    public void warn(final Throwable t,
                     final String pattern,
                     final Object[] arguments)
    {
    }

    /**
     * Log a parameterized message at error level.
     * @param t throwable, may be null.
     * @param pattern pattern, may be null.
     * @param arguments an array of arguments to be formatted and substituted.
     */
    public void error(final Throwable t,
                      final String pattern,
                      final Object[] arguments)
    {
    }

    /**
     * Log a parameterized message at fatal level.
     * @param t throwable, may be null.
     * @param pattern pattern, may be null.
     * @param arguments an array of arguments to be formatted and substituted.
     */
    public void fatal(final Throwable t,
                      final String pattern,
                      final Object[] arguments)
    {
    }



    /**
     * Log a parameterized message at trace level.
     * @param pattern pattern, may be null.
     * @param argument a value to be formatted and substituted.
     */
    public void trace(final String pattern,
                      final boolean argument)
    {
    }

    /**
     * Log a parameterized message at trace level.
     * @param pattern pattern, may be null.
     * @param argument a value to be formatted and substituted.
     */
    public void trace(final String pattern,
                      final char argument)
    {
    }

    /**
     * Log a parameterized message at trace level.
     * @param pattern pattern, may be null.
     * @param argument a value to be formatted and substituted.
     */
    public void trace(final String pattern,
                      final byte argument)
    {
    }

    /**
     * Log a parameterized message at trace level.
     * @param pattern pattern, may be null.
     * @param argument a value to be formatted and substituted.
     */
    public void trace(final String pattern,
                      final short argument)
    {
    }

    /**
     * Log a parameterized message at trace level.
     * @param pattern pattern, may be null.
     * @param argument a value to be formatted and substituted.
     */
    public void trace(final String pattern,
                      final int argument)
    {
    }

    /**
     * Log a parameterized message at trace level.
     * @param pattern pattern, may be null.
     * @param argument a value to be formatted and substituted.
     */
    public void trace(final String pattern,
                      final long argument)
    {
    }

    /**
     * Log a parameterized message at trace level.
     * @param pattern pattern, may be null.
     * @param argument a value to be formatted and substituted.
     */
    public void trace(final String pattern,
                      final float argument)
    {
    }

    /**
     * Log a parameterized message at trace level.
     * @param pattern pattern, may be null.
     * @param argument a value to be formatted and substituted.
     */
    public void trace(final String pattern,
                      final double argument)
    {
    }

    /**
     * Log a parameterized message at trace level.
     * @param pattern pattern, may be null.
     * @param argument a value to be formatted and substituted.
     */
    public void trace(final String pattern,
                      final Object argument)
    {
    }

    /**
     * Log a parameterized message at trace level.
     * @param pattern pattern, may be null.
     * @param arg0 a value to be formatted and substituted.
     * @param arg1 a value to be formatted and substituted.
     */
    public void trace(final String pattern,
                      final Object arg0,
                      final Object arg1)
    {
    }

    /**
     * Log a parameterized message at trace level.
     * @param pattern pattern, may be null.
     * @param arg0 a value to be formatted and substituted.
     * @param arg1 a value to be formatted and substituted.
     * @param arg2 a value to be formatted and substituted.
     */
    public void trace(final String pattern,
                      final Object arg0,
                      final Object arg1,
                      final Object arg2)
    {
    }

    /**
     * Log a parameterized message at trace level.
     * @param pattern pattern, may be null.
     * @param arg0 a value to be formatted and substituted.
     * @param arg1 a value to be formatted and substituted.
     * @param arg2 a value to be formatted and substituted.
     * @param arg3 a value to be formatted and substituted.
     */
    public void trace(final String pattern,
                      final Object arg0,
                      final Object arg1,
                      final Object arg2,
                      final Object arg3)
    {
    }

    /**
     * Log a parameterized message at debug level.
     * @param pattern pattern, may be null.
     * @param argument a value to be formatted and substituted.
     */
    public void debug(final String pattern,
                      final boolean argument)
    {
    }

    /**
     * Log a parameterized message at debug level.
     * @param pattern pattern, may be null.
     * @param argument a value to be formatted and substituted.
     */
    public void debug(final String pattern,
                      final char argument)
    {
    }

    /**
     * Log a parameterized message at debug level.
     * @param pattern pattern, may be null.
     * @param argument a value to be formatted and substituted.
     */
    public void debug(final String pattern,
                      final byte argument)
    {
    }

    /**
     * Log a parameterized message at debug level.
     * @param pattern pattern, may be null.
     * @param argument a value to be formatted and substituted.
     */
    public void debug(final String pattern,
                      final short argument)
    {
    }

    /**
     * Log a parameterized message at debug level.
     * @param pattern pattern, may be null.
     * @param argument a value to be formatted and substituted.
     */
    public void debug(final String pattern,
                      final int argument)
    {
    }

    /**
     * Log a parameterized message at debug level.
     * @param pattern pattern, may be null.
     * @param argument a value to be formatted and substituted.
     */
    public void debug(final String pattern,
                      final long argument)
    {
    }

    /**
     * Log a parameterized message at debug level.
     * @param pattern pattern, may be null.
     * @param argument a value to be formatted and substituted.
     */
    public void debug(final String pattern,
                      final float argument)
    {
    }

    /**
     * Log a parameterized message at debug level.
     * @param pattern pattern, may be null.
     * @param argument a value to be formatted and substituted.
     */
    public void debug(final String pattern,
                      final double argument)
    {
    }

    /**
     * Log a parameterized message at debug level.
     * @param pattern pattern, may be null.
     * @param argument a value to be formatted and substituted.
     * @param t a throwable, may be null.
     */
    public void debug(final String pattern,  
                      final Object argument,
                      Throwable t)
    {
    }

    /**
     * Log a parameterized message at debug level.
     * @param pattern pattern, may be null.
     * @param arg0 a value to be formatted and substituted.
     * @param arg1 a value to be formatted and substituted.
     */
    public void debug(final String pattern,
                      final Object arg0,
                      final Object arg1)
    {
    }

    /**
     * Log a parameterized message at debug level.
     * @param pattern pattern, may be null.
     * @param arg0 a value to be formatted and substituted.
     * @param arg1 a value to be formatted and substituted.
     * @param arg2 a value to be formatted and substituted.
     */
    public void debug(final String pattern,
                      final Object arg0,
                      final Object arg1,
                      final Object arg2)
    {
    }

    /**
     * Log a parameterized message at debug level.
     * @param pattern pattern, may be null.
     * @param arg0 a value to be formatted and substituted.
     * @param arg1 a value to be formatted and substituted.
     * @param arg2 a value to be formatted and substituted.
     * @param arg3 a value to be formatted and substituted.
     */
    public void debug(final String pattern,
                      final Object arg0,
                      final Object arg1,
                      final Object arg2,
                      final Object arg3)
    {
    }

    /**
     * Log a parameterized message at info level.
     * @param pattern pattern, may be null.
     * @param argument a value to be formatted and substituted.
     */
    public void info(final String pattern,
                     final boolean argument)
    {
    }

    /**
     * Log a parameterized message at info level.
     * @param pattern pattern, may be null.
     * @param argument a value to be formatted and substituted.
     */
    public void info(final String pattern,
                     final char argument)
    {
    }

    /**
     * Log a parameterized message at info level.
     * @param pattern pattern, may be null.
     * @param argument a value to be formatted and substituted.
     */
    public void info(final String pattern,
                     final byte argument)
    {
    }

    /**
     * Log a parameterized message at info level.
     * @param pattern pattern, may be null.
     * @param argument a value to be formatted and substituted.
     */
    public void info(final String pattern,
                     final short argument)
    {
    }

    /**
     * Log a parameterized message at info level.
     * @param pattern pattern, may be null.
     * @param argument a value to be formatted and substituted.
     */
    public void info(final String pattern,
                     final int argument)
    {
    }

    /**
     * Log a parameterized message at info level.
     * @param pattern pattern, may be null.
     * @param argument a value to be formatted and substituted.
     */
    public void info(final String pattern,
                     final long argument)
    {
    }

    /**
     * Log a parameterized message at info level.
     * @param pattern pattern, may be null.
     * @param argument a value to be formatted and substituted.
     */
    public void info(final String pattern,
                     final float argument)
    {
    }

    /**
     * Log a parameterized message at info level.
     * @param pattern pattern, may be null.
     * @param argument a value to be formatted and substituted.
     */
    public void info(final String pattern,
                     final double argument)
    {
    }

    /**
     * Log a parameterized message at info level.
     * @param pattern pattern, may be null.
     * @param argument a value to be formatted and substituted.
     * @param t a throwable, may be null.
     */
    public void info(final String pattern,  
                     final Object argument,
                     Throwable t)
    {
    }

    /**
     * Log a parameterized message at info level.
     * @param pattern pattern, may be null.
     * @param arg0 a value to be formatted and substituted.
     * @param arg1 a value to be formatted and substituted.
     */
    public void info(final String pattern,
                     final Object arg0,
                     final Object arg1)
    {
    }

    /**
     * Log a parameterized message at info level.
     * @param pattern pattern, may be null.
     * @param arg0 a value to be formatted and substituted.
     * @param arg1 a value to be formatted and substituted.
     * @param arg2 a value to be formatted and substituted.
     */
    public void info(final String pattern,
                     final Object arg0,
                     final Object arg1,
                     final Object arg2)
    {
    }

    /**
     * Log a parameterized message at info level.
     * @param pattern pattern, may be null.
     * @param arg0 a value to be formatted and substituted.
     * @param arg1 a value to be formatted and substituted.
     * @param arg2 a value to be formatted and substituted.
     * @param arg3 a value to be formatted and substituted.
     */
    public void info(final String pattern,
                     final Object arg0,
                     final Object arg1,
                     final Object arg2,
                     final Object arg3)
    {
    }

    /**
     * Log a parameterized message at warn level.
     * @param pattern pattern, may be null.
     * @param argument a value to be formatted and substituted.
     */
    public void warn(final String pattern,
                     final boolean argument)
    {
    }

    /**
     * Log a parameterized message at warn level.
     * @param pattern pattern, may be null.
     * @param argument a value to be formatted and substituted.
     */
    public void warn(final String pattern,
                     final char argument)
    {
    }

    /**
     * Log a parameterized message at warn level.
     * @param pattern pattern, may be null.
     * @param argument a value to be formatted and substituted.
     */
    public void warn(final String pattern,
                     final byte argument)
    {
    }

    /**
     * Log a parameterized message at warn level.
     * @param pattern pattern, may be null.
     * @param argument a value to be formatted and substituted.
     */
    public void warn(final String pattern,
                     final short argument)
    {
    }

    /**
     * Log a parameterized message at warn level.
     * @param pattern pattern, may be null.
     * @param argument a value to be formatted and substituted.
     */
    public void warn(final String pattern,
                     final int argument)
    {
    }

    /**
     * Log a parameterized message at warn level.
     * @param pattern pattern, may be null.
     * @param argument a value to be formatted and substituted.
     */
    public void warn(final String pattern,
                     final long argument)
    {
    }

    /**
     * Log a parameterized message at warn level.
     * @param pattern pattern, may be null.
     * @param argument a value to be formatted and substituted.
     */
    public void warn(final String pattern,
                     final float argument)
    {
    }

    /**
     * Log a parameterized message at warn level.
     * @param pattern pattern, may be null.
     * @param argument a value to be formatted and substituted.
     */
    public void warn(final String pattern,
                     final double argument)
    {
    }

    /**
     * Log a parameterized message at warn level.
     * @param pattern pattern, may be null.
     * @param argument a value to be formatted and substituted.
     * @param t a throwable, may be null.
     */
    public void warn(final String pattern,
                     final Object argument,
                     Throwable t)
    {
    }

    /**
     * Log a parameterized message at warn level.
     * @param pattern pattern, may be null.
     * @param arg0 a value to be formatted and substituted.
     * @param arg1 a value to be formatted and substituted.
     */
    public void warn(final String pattern,
                     final Object arg0,
                     final Object arg1)
    {
    }

    /**
     * Log a parameterized message at warn level.
     * @param pattern pattern, may be null.
     * @param arg0 a value to be formatted and substituted.
     * @param arg1 a value to be formatted and substituted.
     * @param arg2 a value to be formatted and substituted.
     */
    public void warn(final String pattern,
                     final Object arg0,
                     final Object arg1,
                     final Object arg2)
    {
    }

    /**
     * Log a parameterized message at warn level.
     * @param pattern pattern, may be null.
     * @param arg0 a value to be formatted and substituted.
     * @param arg1 a value to be formatted and substituted.
     * @param arg2 a value to be formatted and substituted.
     * @param arg3 a value to be formatted and substituted.
     */
    public void warn(final String pattern,
                     final Object arg0,
                     final Object arg1,
                     final Object arg2,
                     final Object arg3)
    {
    }

    /**
      * Log a parameterized message at specified level.
      * @param level level, may not be null.
      * @param pattern pattern, may be null.
      * @param parameters parameters to the log message.
      */
    public void log(final Level level,
                    final String pattern,
                    final Object[] parameters)
    {
    }

    /**
      * Log a parameterized message at specified level.
      * @param level level, may not be null.
      * @param t throwable, may be null.
      * @param pattern pattern, may be null.
      * @param parameters parameters to the log message.
      */
    public void log(final Level level,
                    final Throwable t,
                    final String pattern,
                    final Object[] parameters)
    {
    }

    /**
      * Log a parameterized message at specified level.
      * @param level level, may not be null.
      * @param pattern pattern, may be null.
      * @param param1 parameter to the log message.
      */
    public void log(final Level level,
                    final String pattern,
                    final Object param1)
    {
    }

    /**
      * Log a parameterized message at specified level.
      * @param level level, may not be null.
      * @param pattern pattern, may be null.
      * @param param1 parameter to the log message.
      */
    public void log(final Level level,
                    final String pattern,
                    final boolean param1)
    {
    }


    /**
      * Log a parameterized message at specified level.
      * @param level level, may not be null.
      * @param pattern pattern, may be null.
      * @param param1 parameter to the log message.
      */
    public void log(final Level level,
                    final String pattern,
                    final byte param1)
    {
    }


    /**
      * Log a parameterized message at specified level.
      * @param level level, may not be null.
      * @param pattern pattern, may be null.
      * @param param1 parameter to the log message.
      */
    public void log(final Level level,
                    final String pattern,
                    final char param1)
    {
    }

    /**
      * Log a parameterized message at specified level.
      * @param level level, may not be null.
      * @param pattern pattern, may be null.
      * @param param1 parameter to the log message.
      */
    public void log(final Level level,
                    final String pattern,
                    final short param1)
    {
    }

    /**
      * Log a parameterized message at specified level.
      * @param level level, may not be null.
      * @param pattern pattern, may be null.
      * @param param1 parameter to the log message.
      */
    public void log(final Level level,
                    final String pattern,
                    final int param1)
    {
    }


    /**
      * Log a parameterized message at specified level.
      * @param level level, may not be null.
      * @param pattern pattern, may be null.
      * @param param1 parameter to the log message.
      */
    public void log(final Level level,
                    final String pattern,
                    final long param1)
    {
    }


    /**
      * Log a parameterized message at specified level.
      * @param level level, may not be null.
      * @param pattern pattern, may be null.
      * @param param1 parameter to the log message.
      */
    public void log(final Level level,
                    final String pattern,
                    final float param1)
    {
    }


    /**
      * Log a parameterized message at specified level.
      * @param level level, may not be null.
      * @param pattern pattern, may be null.
      * @param param1 parameter to the log message.
      */
    public void log(final Level level,
                    final String pattern,
                    final double param1)
    {
    }


    /**
     * Log a parameterized message at specified level.
     * @param level level, may not be null.
     * @param pattern pattern, may be null.
     * @param arg0 a value to be formatted and substituted.
     * @param arg1 a value to be formatted and substituted.
     */
    public void log(final Level level,
                    final String pattern,
                    final Object arg0,
                    final Object arg1)
    {
    }

    /**
     * Log a parameterized message at specifed level.
     * @param level level, may not be null.
     * @param pattern pattern, may be null.
     * @param arg0 a value to be formatted and substituted.
     * @param arg1 a value to be formatted and substituted.
     * @param arg2 a value to be formatted and substituted.
     */
    public void log(final Level level,
                    final String pattern,
                    final Object arg0,
                    final Object arg1,
                    final Object arg2)
    {
    }

    /**
     * Log a parameterized message at specified level.
     * @param pattern pattern, may be null.
     * @param level level, may not be null.
     * @param arg0 a value to be formatted and substituted.
     * @param arg1 a value to be formatted and substituted.
     * @param arg2 a value to be formatted and substituted.
     * @param arg3 a value to be formatted and substituted.
     */
    public void log(final Level level,
                    final String pattern,
                    final Object arg0,
                    final Object arg1,
                    final Object arg2,
                    final Object arg3)
    {
    }


    /**
      * Log a parameterized message using a pattern from a resource bundle.
      * @param level level, may not be null.
      * @param bundleName resource bundle name, may be null.
      * @param key key, may be null.
      * @param parameters parameters to the log message.
      */
    public void logrb(final Level level,
                      final String bundleName,
                      final String key,
                      final Object[] parameters)
    {
    }

    /**
      * Log a parameterized message using a pattern from a resource bundle.
      * @param level level, may not be null.
      * @param t throwable, may be null.
      * @param bundleName resource bundle name, may be null.
      * @param key key, may be null.
      * @param parameters parameters to the log message.
      */
    public void logrb(final Level level,
                      final Throwable t,
                      final String bundleName,
                      final String key,
                      final Object[] parameters)
    {
    }

    /**
      * Log a parameterized message using a pattern from a resource bundle.
      * @param level level, may not be null.
      * @param bundleName resource bundle name, may be null.
      * @param key key, may be null.
      * @param param1 Parameter to the log message.
      */
    public void logrb(final Level level,
                      final String bundleName,
                      final String key,
                      final Object param1)
    {
    }

    /**
      * Log a parameterized message using a pattern from a resource bundle.
      * @param level level, may not be null.
      * @param bundleName resource bundle name, may be null.
      * @param key key, may be null.
      * @param param1 Parameter to the log message.
      */
    public void logrb(final Level level,
                      final String bundleName,
                      final String key,
                      final boolean param1)
    {
    }

    /**
      * Log a parameterized message using a pattern from a resource bundle.
      * @param level level, may not be null.
      * @param bundleName resource bundle name, may be null.
      * @param key key, may be null.
      * @param param1 Parameter to the log message.
      */
    public void logrb(final Level level,
                      final String bundleName,
                      final String key,
                      final char param1)
    {
    }

    /**
      * Log a parameterized message using a pattern from a resource bundle.
      * @param level level, may not be null.
      * @param bundleName resource bundle name, may be null.
      * @param key key, may be null.
      * @param param1 Parameter to the log message.
      */
    public void logrb(final Level level,
                      final String bundleName,
                      final String key,
                      final byte param1)
    {
    }

    /**
      * Log a parameterized message using a pattern from a resource bundle.
      * @param level level, may not be null.
      * @param bundleName resource bundle name, may be null.
      * @param key key, may be null.
      * @param param1 Parameter to the log message.
      */
    public void logrb(final Level level,
                      final String bundleName,
                      final String key,
                      final short param1)
    {
    }

    /**
      * Log a parameterized message using a pattern from a resource bundle.
      * @param level level, may not be null.
      * @param bundleName resource bundle name, may be null.
      * @param key key, may be null.
      * @param param1 Parameter to the log message.
      */
    public void logrb(final Level level,
                      final String bundleName,
                      final String key,
                      final int param1)
    {
    }

    /**
      * Log a parameterized message using a pattern from a resource bundle.
      * @param level level, may not be null.
      * @param bundleName resource bundle name, may be null.
      * @param key key, may be null.
      * @param param1 Parameter to the log message.
      */
    public void logrb(final Level level,
                      final String bundleName,
                      final String key,
                      final long param1)
    {
    }
    
    /**
      * Log a parameterized message using a pattern from a resource bundle.
      * @param level level, may not be null.
      * @param bundleName resource bundle name, may be null.
      * @param key key, may be null.
      * @param param1 Parameter to the log message.
      */
    public void logrb(final Level level,
                      final String bundleName,
                      final String key,
                      final float param1)
    {
    }


    /**
      * Log a parameterized message using a pattern from a resource bundle.
      * @param level level, may not be null.
      * @param bundleName resource bundle name, may be null.
      * @param key key, may be null.
      * @param param1 Parameter to the log message.
      */
    public void logrb(final Level level,
                      final String bundleName,
                      final String key,
                      final double param1)
    {
    }

    /**
      * Log a parameterized message using a pattern from a resource bundle.
      * @param level level, may not be null.
      * @param bundleName resource bundle name, may be null.
      * @param key key, may be null.
      * @param param0 Parameter to the log message.
      * @param param1 Parameter to the log message.
      */
    public void logrb(final Level level,
                      final String bundleName,
                      final String key,
                      final Object param0,
                      final Object param1)
    {
    }


    /**
      * Log a parameterized message using a pattern from a resource bundle.
      * @param level level, may not be null.
      * @param bundleName resource bundle name, may be null.
      * @param key key, may be null.
      * @param param0 Parameter to the log message.
      * @param param1 Parameter to the log message.
      * @param param2 Parameter to the log message.
      */
    public void logrb(final Level level,
                      final String bundleName,
                      final String key,
                      final Object param0,
                      final Object param1,
                      final Object param2)
    {
    }

    /**
      * Log a parameterized message using a pattern from a resource bundle.
      * @param level level, may not be null.
      * @param bundleName resource bundle name, may be null.
      * @param key key, may be null.
      * @param param0 Parameter to the log message.
      * @param param1 Parameter to the log message.
      * @param param2 Parameter to the log message.
      * @param param3 Parameter to the log message.
      */
    public void logrb(final Level level,
                      final String bundleName,
                      final String key,
                      final Object param0,
                      final Object param1,
                      final Object param2,
                      final Object param3)
    {
    }
}
