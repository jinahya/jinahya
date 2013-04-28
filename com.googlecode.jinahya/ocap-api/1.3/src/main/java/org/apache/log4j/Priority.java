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

// Contributors:  Kitching Simon <Simon.Kitching@orange.ch>

package org.apache.log4j;

/**
   <font color="#AA4444">Refrain from using this class directly, use
   the {@link Level} class instead</font>.

   @author Ceki G&uuml;lc&uuml; */
public class Priority {

  public final static int OFF_INT = Integer.MAX_VALUE;
  public final static int FATAL_INT = 50000;
  public final static int ERROR_INT = 40000;
  public final static int WARN_INT  = 30000;
  public final static int INFO_INT  = 20000;
  public final static int DEBUG_INT = 10000;
    //public final static int FINE_INT = DEBUG_INT;
  public final static int ALL_INT = Integer.MIN_VALUE;

  /**
   * @deprecated Use {@link Level#FATAL} instead.
   */
  final static public Priority FATAL = null;

  /**
   * @deprecated Use {@link Level#ERROR} instead.
   */
  final static public Priority ERROR = null;

  /**
   * @deprecated Use {@link Level#WARN} instead.
   */
  final static public Priority WARN  = null;

  /**
   * @deprecated Use {@link Level#INFO} instead.
   */
  final static public Priority INFO  = null;

  /**
   * @deprecated Use {@link Level#DEBUG} instead.
   */
  final static public Priority DEBUG = null;

  /**
   * Default constructor for deserialization.
   */
  protected Priority()
  {
  }

  /**
   *     Instantiate a level object.
   */
  protected Priority(int level, String levelStr, int syslogEquivalent)
  {
  }

  /**
     Two priorities are equal if their level fields are equal.
     @since 1.2
   */
  public
  boolean equals(Object o) {
      return false;
  }

  /**
     Return the syslog equivalent of this priority as an integer.
   */
  public
  final
  int getSyslogEquivalent() {
    return 0;
  }


   
  /**
     Returns <code>true</code> if this level has a higher or equal
     level than the level passed as argument, <code>false</code>
     otherwise.  
     
     <p>You should think twice before overriding the default
     implementation of <code>isGreaterOrEqual</code> method.

  */
  public
  boolean isGreaterOrEqual(Priority r) {
    return false;
  }

  /**
     Return all possible priorities as an array of Level objects in
     descending order.

     @deprecated This method will be removed with no replacement.
  */
  public
  static
  Priority[] getAllPossiblePriorities() {
    return null;
  }


  /**
     Returns the string representation of this priority.
   */
  final
  public
  String toString() {
    return null;
  }

  /**
     Returns the integer representation of this level.
   */
  public
  final
  int toInt() {
    return 0;
  }

  /**
   * @deprecated Please use the {@link Level#toLevel(String)} method instead.
  */
  public
  static
  Priority toPriority(String sArg) {
    return null;
  }

  /**
   * @deprecated Please use the {@link Level#toLevel(int)} method instead.   
   */
  public
  static
  Priority toPriority(int val) {
    return null;
  }

  /**
   * @deprecated Please use the {@link Level#toLevel(int, Level)} method instead.   
  */
  public
  static
  Priority toPriority(int val, Priority defaultPriority) {
    return null;
  }

  /**
   * @deprecated Please use the {@link Level#toLevel(String, Level)} method instead.   
   */
  public
  static
  Priority toPriority(String sArg, Priority defaultPriority) {                  
    return null;
  }
}
