/*
 * @(#)CarouselFileChangeEvent.java	1.11 00/10/09
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

package javax.tv.carousel;

import java.util.EventObject;

/**
 * The <code>CarouselFileChangeEvent</code> provides notification of
 * a change to <code>CarouselFile</code> data.
 *
 * @author Jon Courtney courtney@eng.sun.com
 */
public class CarouselFileChangeEvent extends EventObject {
	
  /**
   * Creates a <code>CarouselFileChangeEvent</code> indicating that
   * the specified <code>CarouselFile</code> has changed.

   * @param source The <code>CarouselFile</code> whose contents have
   * changed.
   */
  public CarouselFileChangeEvent(CarouselFile source) {
    super(source);
  }

  /**
   * Reports the <code>CarouselFile</code> whose contents have changed.
   *
   * @return The <code>CarouselFile</code> whose contents have changed.
   */
  public CarouselFile getCarouselFile() {
    return (CarouselFile)getSource();
  }
}
