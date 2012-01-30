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

// Contibutors:  Aaron Greenhouse <aarong@cs.cmu.edu>
//               Thomas Tuft Muller <ttm@online.no>
package org.apache.log4j;

import org.apache.log4j.spi.AppenderAttachable;
import org.apache.log4j.spi.LoggingEvent;

import java.util.Enumeration;


/**
 * The AsyncAppender lets users log events asynchronously.
 * <p/>
 * <p/>
 * The AsyncAppender will collect the events sent to it and then dispatch them
 * to all the appenders that are attached to it. You can attach multiple
 * appenders to an AsyncAppender.
 * </p>
 * <p/>
 * <p/>
 * The AsyncAppender uses a separate thread to serve the events in its buffer.
 * </p>
 * <p/>
 * <b>Important note:</b> The <code>AsyncAppender</code> can only be script
 * configured using the {@link org.apache.log4j.xml.DOMConfigurator}.
 * </p>
 *
 * @author Ceki G&uuml;lc&uuml;
 * @author Curt Arnold
 * @since 0.9.1
 */
public class AsyncAppender extends AppenderSkeleton
  implements AppenderAttachable {
  /**
   * The default buffer size is set to 128 events.
   */
  public static final int DEFAULT_BUFFER_SIZE = 128;

  /**
   * Create new instance.
   */
  public AsyncAppender() {
  }

  /**
   * Add appender.
   *
   * @param newAppender appender to add, may not be null.
   */
  public void addAppender(final Appender newAppender) {
  }

  /**
   * {@inheritDoc}
   */
  public void append(final LoggingEvent event) {
  }

  /**
   * Close this <code>AsyncAppender</code> by interrupting the dispatcher
   * thread which will process all pending events before exiting.
   */
  public void close() {
  }

  /**
   * Get iterator over attached appenders.
   * @return iterator or null if no attached appenders.
   */
  public Enumeration getAllAppenders() {
      return null;
  }

  /**
   * Get appender by name.
   *
   * @param name name, may not be null.
   * @return matching appender or null.
   */
  public Appender getAppender(final String name) {
      return null;
  }

  /**
   * Gets whether the location of the logging request call
   * should be captured.
   *
   * @return the current value of the <b>LocationInfo</b> option.
   */
  public boolean getLocationInfo() {
    return false;
  }

  /**
   * Determines if specified appender is attached.
   * @param appender appender.
   * @return true if attached.
   */
  public boolean isAttached(final Appender appender) {
      return false;
  }

  /**
   * {@inheritDoc}
   */
  public boolean requiresLayout() {
    return false;
  }

  /**
   * Removes and closes all attached appenders.
   */
  public void removeAllAppenders() {
  }

  /**
   * Removes an appender.
   * @param appender appender to remove.
   */
  public void removeAppender(final Appender appender) {
  }

  /**
   * Remove appender by name.
   * @param name name.
   */
  public void removeAppender(final String name) {
  }

  /**
   * The <b>LocationInfo</b> option takes a boolean value. By default, it is
   * set to false which means there will be no effort to extract the location
   * information related to the event. As a result, the event that will be
   * ultimately logged will likely to contain the wrong location information
   * (if present in the log format).
   * <p/>
   * <p/>
   * Location information extraction is comparatively very slow and should be
   * avoided unless performance is not a concern.
   * </p>
   * @param flag true if location information should be extracted.
   */
  public void setLocationInfo(final boolean flag) {
  }

  /**
   * Sets the number of messages allowed in the event buffer
   * before the calling thread is blocked (if blocking is true)
   * or until messages are summarized and discarded.  Changing
   * the size will not affect messages already in the buffer.
   *
   * @param size buffer size, must be positive.
   */
  public void setBufferSize(final int size) {
  }

  /**
   * Gets the current buffer size.
   * @return the current value of the <b>BufferSize</b> option.
   */
  public int getBufferSize() {
    return 0;
  }

  /**
   * Sets whether appender should wait if there is no
   * space available in the event buffer or immediately return.
   *
   * @since 1.2.14
   * @param value true if appender should wait until available space in buffer.
   */
  public void setBlocking(final boolean value) {
  }

  /**
   * Gets whether appender should block calling thread when buffer is full.
   * If false, messages will be counted by logger and a summary
   * message appended after the contents of the buffer have been appended.
   *
   * @since 1.2.14
   * @return true if calling thread will be blocked when buffer is full.
   */
  public boolean getBlocking() {
    return false;
  }
}
