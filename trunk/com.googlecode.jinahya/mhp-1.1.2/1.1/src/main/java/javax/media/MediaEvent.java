/*
 * @(#)MediaEvent.java	1.4 98/03/28
 *
 * Copyright 1996-1998 by Sun Microsystems, Inc.,
 * 901 San Antonio Road, Palo Alto, California, 94303, U.S.A.
 * All rights reserved.
 *
 * This software is the confidential and proprietary information
 * of Sun Microsystems, Inc. ("Confidential Information").  You
 * shall not disclose such Confidential Information and shall use
 * it only in accordance with the terms of the license agreement
 * you entered into with Sun.
 */

package javax.media;

/**
 * <code>MediaEvent</code> is the base interface for events supported
 * by the media framework.
 * <p>
 * <h2>Java Beans support </h2>
 *
 * In order to support the Java Beans event model an implementation
 * of MediaEvent is required to sub-class java.util.EventObject.
 * If an implementation is designed to support the 1.0.2 JDK then
 * it may alternatively sub-class sunw.util.EventObject to provide
 * the support appropriate support.
 * 
 * <b>Any class that subclasses <code>MediaEvent</code> must resolve
 * to either java.util.EventObject or sunw.util.EventObject.
 *
 * @see ControllerEvent
 * @see GainChangeEvent
 *
 **/
public interface MediaEvent {

    public Object getSource();
    
}
