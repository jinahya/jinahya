/*
 * @(#)CarouselFileListener.java	1.10 00/08/06
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

import java.util.EventListener;

/**
 *
 * The <code>CarouselFileListener</code> interface is implemented by
 * application classes which require notification of changes to
 * <code>CarouselFile</code> data.
 *
 * @author Jon Courtney courtney@eng.sun.com
 */
public interface CarouselFileListener extends EventListener {
	
    /**
     *
     * Notifies the <code>CarouselFileListener</code> that the
     * <code>CarouselFile</code> has changed in the broadcast.
     *
     * If the contents of a <code>CarouselFile</code> change while an
     * application is reading its data from the local cache, the
     * cached data shall either (a) remain entirely unchanged or (b)
     * be flushed from the cache.  If the data is flushed from the
     * cache, attempts to read from this <code>CarouselFile</code>
     * using pre-existing file reading objects
     * (e.g. <code>FileInputStream</code>, <code>FileReader</code>, or
     * <code>RandomAccessFile</code>) will fail.
     *
     * <p> To read the new data, the application must create a new
     * file reading object.  To ensure that this data is the most recent
     * version from the broadcast, the application should first invoke
     * the <code>CarouselFile.refreshCache()</code> method.
     *
     * <p> No guarantees are provided concerning the ability of the
     * receiver to detect changes to the broadcast
     * <code>CarouselFile</code> or the latency of event notification
     * if a change is detected.
     *
     * @param event Event indicating <code>CarouselFile</code> that
     * has changed.
     *
     * @see CarouselFile#refreshCache */
    public void carouselFileChanged(CarouselFileChangeEvent event);
}
