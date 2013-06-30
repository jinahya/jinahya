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

import org.apache.log4j.Category;

/**
  * ThrowableInformation is log4j's internal representation of
  * throwables. It essentially consists of a string array, called
  * 'rep', where the first element, that is rep[0], represents the
  * string representation of the throwable (i.e. the value you get
  * when you do throwable.toString()) and subsequent elements
  * correspond the stack trace with the top most entry of the stack
  * corresponding to the second entry of the 'rep' array that is
  * rep[1].
  *
  * @author Ceki G&uuml;lc&uuml;
  *
  * */
public class ThrowableInformation implements java.io.Serializable {

  public
  ThrowableInformation(Throwable throwable) {
  }

    /**
     * Create a new instance.
     * @param throwable throwable, may not be null.
     * @param category category used to obtain ThrowableRenderer, may be null.
     * @since 1.2.16
     */
  public ThrowableInformation(Throwable throwable, Category category) {
  }

    /**
     * Create new instance.
     * @since 1.2.15
     * @param r String representation of throwable.
     */
  public ThrowableInformation(final String[] r) {
  }


  public
  Throwable getThrowable() {
    return null;
  }

  public synchronized String[] getThrowableStrRep() {
    return null;
  }
}


