/*
 * @(#)StreamType.java	1.16 00/08/26
 *
 * Copyright 1998-2000 by Sun Microsystems, Inc.,
 * 901 San Antonio Road, Palo Alto, California, 94303, U.S.A.
 * All rights reserved.
 * 
 * This software is the confidential and proprietary information
 * of Sun Microsystems, Inc. ("Confidential Information").  You
 * shall not disclose such Confidential Information and shall use
 * it only in accordance with the terms of the license agreement
 * you entered into with Sun.
 */

package javax.tv.service.navigation;


/**
 * This class represents values of <code>ServiceComponent</code>
 * stream types (e.g., "video", "audio", "subtitles", "data",
 * "sections", etc.).
 *
 * @see ServiceComponent */
public class StreamType {

  /**
   * Creates a stream type object.
   *
   * @param name The string name of this type (e.g., "VIDEO").
   */
  protected StreamType(String name) { 
  }
  
  /**
   * Provides the string name of the type.  For the type objects
   * defined in this class, the string name will be identical to the
   * class variable name.
   */
  public String toString() {
	  return null; 
  }


  /**
   * Video component.
   */
  public static final StreamType VIDEO=null;
  
  /**
   * Audio component.
   */
  public static final StreamType AUDIO=null;
  
  /**
   * Subtitles component.
   */
  public static final StreamType SUBTITLES=null;
  
  /**
   * Data component.
   */
  public static final StreamType DATA=null;
  
  /**
   * MPEG sections component.
   */
  public static final StreamType SECTIONS=null;
  
  /**
   * Unknown component.
   */
  public static final StreamType UNKNOWN=null;
}
