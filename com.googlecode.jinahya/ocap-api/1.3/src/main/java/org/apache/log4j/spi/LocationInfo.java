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

// Contributors: Mathias Rupprecht <mmathias.rupprecht@fja.com>

package org.apache.log4j.spi;

/**
   The internal representation of caller location information.

   @since 0.8.3
*/
public class LocationInfo implements java.io.Serializable {

  /**
   *     All available caller information, in the format
   *     <code>fully.qualified.classname.of.caller.methodName(Filename.java:line)</code>
   */
  public String fullInfo;

  /**
   *     When location information is not available the constant
   *     <code>NA</code> is returned. Current value of this string
   *     constant is <b>?</b>.  
   */
  public static final String NA = "?";

  /**
   * NA_LOCATION_INFO is provided for compatibility with log4j 1.3.
   * @since 1.2.15
   */
  public static final LocationInfo NA_LOCATION_INFO = null;

  /**
     Instantiate location information based on a Throwable. We
     expect the Throwable <code>t</code>, to be in the format

       <pre>
        java.lang.Throwable
        ...
          at org.apache.log4j.PatternLayout.format(PatternLayout.java:413)
          at org.apache.log4j.FileAppender.doAppend(FileAppender.java:183)
        at org.apache.log4j.Category.callAppenders(Category.java:131)
        at org.apache.log4j.Category.log(Category.java:512)
        at callers.fully.qualified.className.methodName(FileName.java:74)
	...
       </pre>

       <p>However, we can also deal with JIT compilers that "lose" the
       location information, especially between the parentheses.
        @param t throwable used to determine location, may be null.
        @param fqnOfCallingClass class name of first class considered part of
           the logging framework.  Location will be site that calls a method on this class.

    */
    public LocationInfo(Throwable t, String fqnOfCallingClass) {
    }

    /**
     * Create new instance.
     * @param file source file name
     * @param classname class name
     * @param method method
     * @param line source line number
     *
     * @since 1.2.15
     */
    public LocationInfo(
      final String file,
      final String classname,
      final String method,
      final String line) {
    }

    /**
       Return the fully qualified class name of the caller making the
       logging request.
    */
    public
    String getClassName() {
        return null;
    }

    /**
       Return the file name of the caller.

       <p>This information is not always available.
    */
    public
    String getFileName() {
      return null;
    }

    /**
       Returns the line number of the caller.

       <p>This information is not always available.
    */
    public
    String getLineNumber() {
      return null;
    }

    /**
       Returns the method name of the caller.
    */
    public
    String getMethodName() {
      return null;
    }
}
