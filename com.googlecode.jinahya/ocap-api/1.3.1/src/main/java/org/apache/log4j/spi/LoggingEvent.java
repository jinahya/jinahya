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

package org.apache.log4j.spi;

import java.util.Map;
import java.util.Set;

import org.apache.log4j.Category;
import org.apache.log4j.Level;
import org.apache.log4j.Priority;

// Contributors:   Nelson Minar <nelson@monkey.org>
//                 Wolf Siberski
//                 Anders Kristensen <akristensen@dynamicsoft.com>

/**
   The internal representation of logging events. When an affirmative
   decision is made to log then a <code>LoggingEvent</code> instance
   is created. This instance is passed around to the different log4j
   components.

   <p>This class is of concern to those wishing to extend log4j.

   @author Ceki G&uuml;lc&uuml;
   @author James P. Cakalic

   @since 0.8.2 */
public class LoggingEvent implements java.io.Serializable {

  /**
   *Fully qualified name of the calling category class. 
   */
  public final transient String fqnOfCategoryClass = null;

  /**
   * <p>The category (logger) name.
   *
   * @deprecated This field will be marked as private in future
   * releases. Please do not access it directly. Use the {@link
   * #getLoggerName} method instead.
   *
   */
  public final String categoryName = null;

  /**
   * Level of logging event. Level cannot be serializable because it
   * is a flyweight.  Due to its special seralization it cannot be
   * declared final either.
   *
   * <p> This field should not be accessed directly. You shoud use the
   * {@link #getLevel} method instead.
   * @deprecated This field will be marked as private in future
   * releases. Please do not access it directly. Use the {@link
   * #getLevel} method instead.
   *
   */
  public transient Priority level;

  /**
   *The number of milliseconds elapsed from 1/1/1970 until logging event
   *      was created. 
   */
  public final long timeStamp = 0L;

  /**
     Instantiate a LoggingEvent from the supplied parameters.

     <p>Except {@link #timeStamp} all the other fields of
     <code>LoggingEvent</code> are filled when actually needed.
     <p>
     @param logger The logger generating this event.
     @param level The level of this event.
     @param message  The message of this event.
     @param throwable The throwable of this event.  */
  public LoggingEvent(String fqnOfCategoryClass, Category logger,
		      Priority level, Object message, Throwable throwable) {
  }

  /**
     Instantiate a LoggingEvent from the supplied parameters.

     <p>Except {@link #timeStamp} all the other fields of
     <code>LoggingEvent</code> are filled when actually needed.
     <p>
     @param logger The logger generating this event.
     @param timeStamp the timestamp of this logging event
     @param level The level of this event.
     @param message  The message of this event.
     @param throwable The throwable of this event.  */
  public LoggingEvent(String fqnOfCategoryClass, Category logger,
		      long timeStamp, Priority level, Object message,
		      Throwable throwable) {
  }

    /**
       Create new instance.
       @since 1.2.15
       @param fqnOfCategoryClass Fully qualified class name
                 of Logger implementation.
       @param logger The logger generating this event.
       @param timeStamp the timestamp of this logging event
       @param level The level of this event.
       @param message  The message of this event.
       @param threadName thread name
       @param throwable The throwable of this event.
       @param ndc Nested diagnostic context
       @param info Location info
       @param properties MDC properties
     */
    public LoggingEvent(final String fqnOfCategoryClass,
                        final Category logger,
                        final long timeStamp,
                        final Level level,
                        final Object message,
                        final String threadName,
                        final ThrowableInformation throwable,
                        final String ndc,
                        final LocationInfo info,
                        final java.util.Map properties) {
    }


  /**
     Set the location information for this logging event. The collected
     information is cached for future use.
   */
  public LocationInfo getLocationInformation() {
      return null;
  }

  /**
   * Return the level of this event. Use this form instead of directly
   * accessing the <code>level</code> field.  */
  public Level getLevel() {
    return null;
  }

  /**
   * Return the name of the logger. Use this form instead of directly
   * accessing the <code>categoryName</code> field.  
   */
  public String getLoggerName() {
    return null;
  }

    /**
     * Gets the logger of the event.
     * Use should be restricted to cloning events.
     * @since 1.2.15
     */
    public Category getLogger() {
      return null;
    }

  /**
     Return the message for this logging event.

     <p>Before serialization, the returned object is the message
     passed by the user to generate the logging event. After
     serialization, the returned value equals the String form of the
     message possibly after object rendering.

     @since 1.1 */
  public
  Object getMessage() {
    return null;
  }

  /**
   * This method returns the NDC for this event. It will return the
   * correct content even if the event was generated in a different
   * thread or even on a different machine. The {@link NDC#get} method
   * should <em>never</em> be called directly.  */
  public
  String getNDC() {
    return null;
  }


  /**
      Returns the the context corresponding to the <code>key</code>
      parameter. If there is a local MDC copy, possibly because we are
      in a logging server or running inside AsyncAppender, then we
      search for the key in MDC copy, if a value is found it is
      returned. Otherwise, if the search in MDC copy returns a null
      result, then the current thread's <code>MDC</code> is used.
      
      <p>Note that <em>both</em> the local MDC copy and the current
      thread's MDC are searched.

  */
  public
  Object getMDC(String key) {
    return null;
  }

  /**
     Obtain a copy of this thread's MDC prior to serialization or
     asynchronous logging.  
  */
  public
  void getMDCCopy() {
  }

  public
  String getRenderedMessage() {
     return null;
  }

  /**
     Returns the time when the application started, in milliseconds
     elapsed since 01.01.1970.  */
  public static long getStartTime() {
    return 0L;
  }

  public
  String getThreadName() {
    return null;
  }

  /**
     Returns the throwable information contained within this
     event. May be <code>null</code> if there is no such information.

     <p>Note that the {@link Throwable} object contained within a
     {@link ThrowableInformation} does not survive serialization.

     @since 1.1 */
  public
  ThrowableInformation getThrowableInformation() {
    return null;
  }

  /**
     Return this event's throwable's string[] representaion.
  */
  public
  String[] getThrowableStrRep() {
    return null;
  }

    /**
     * Set value for MDC property.
     * This adds the specified MDC property to the event.
     * Access to the MDC is not synchronized, so this
     * method should only be called when it is known that
     * no other threads are accessing the MDC.
     * @since 1.2.15
     * @param propName
     * @param propValue
     */
  public final void setProperty(final String propName,
                          final String propValue) {
  }

    /**
     * Return a property for this event. The return value can be null.
     *
     * Equivalent to getMDC(String) in log4j 1.2.  Provided
     * for compatibility with log4j 1.3.
     *
     * @param key property name
     * @return property value or null if property not set
     * @since 1.2.15
     */
    public final String getProperty(final String key) {
        return null;
    }

    /**
     * Check for the existence of location information without creating it
     * (a byproduct of calling getLocationInformation).
     * @return true if location information has been extracted.
     * @since 1.2.15
     */
    public final boolean locationInformationExists() {
      return false;
    }

    /**
     * Getter for the event's time stamp. The time stamp is calculated starting
     * from 1970-01-01 GMT.
     * @return timestamp
     *
     * @since 1.2.15
     */
    public final long getTimeStamp() {
      return 0L;
    }

    /**
     * Returns the set of the key values in the properties
     * for the event.
     *
     * The returned set is unmodifiable by the caller.
     *
     * Provided for compatibility with log4j 1.3
     *
     * @return Set an unmodifiable set of the property keys.
     * @since 1.2.15
     */
    public Set getPropertyKeySet() {
      return null;
    }

    /**
     * Returns the set of properties
     * for the event.
     *
     * The returned set is unmodifiable by the caller.
     *
     * Provided for compatibility with log4j 1.3
     *
     * @return Set an unmodifiable map of the properties.
     * @since 1.2.15
     */
    public Map getProperties() {
      return null;
    }

    /**
     * Get the fully qualified name of the calling logger sub-class/wrapper.
     * Provided for compatibility with log4j 1.3
     * @return fully qualified class name, may be null.
     * @since 1.2.15
     */
    public String getFQNOfLoggerClass() {
      return null;
    }


    /**
     * This removes the specified MDC property from the event.
     * Access to the MDC is not synchronized, so this
     * method should only be called when it is known that
     * no other threads are accessing the MDC.
     * @param propName the property name to remove
     * @since 1.2.16
     */
    public Object removeProperty(String propName) {
        return null;
    }
}
