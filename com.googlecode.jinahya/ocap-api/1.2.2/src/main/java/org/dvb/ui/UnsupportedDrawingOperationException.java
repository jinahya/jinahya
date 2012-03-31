package org.dvb.ui;

/**
 *
 * Copyright (c) 1996 Institut fuer Nachrichtentechnik, Inc. All Rights Reserved.
 * Written By: Immo Benjes
 * Date: 14 Oct 99
 * Description:
 *
 * Usage Notes:
 * Filename: UnsupportedDrawingOperationException.java
 *
 * @author Immo Benjes
	Last change:  IB   19 Jan 100   10:39 am
 */


/**
 * The <code>UnsupportedDrawingOperationException</code> class represents
 * an exception that is thrown if an drawing operation is not supported
 * on this platform. E.g. DVBGraphics.setComposite could throw an Exception
 * when setting the DST_IN rule on some devices while the SRC_OVER rule will always work.
 * @since MHP 1.0
 */

public class UnsupportedDrawingOperationException extends java.lang.Exception {
	/**
	 * Constructs an instance of
	 * <code>UnsupportedDrawingOperationException</code>
	 * with the specified detail message.
	 * @param   s     the detail message
	 * @since   MHP1.0
	 */
	public UnsupportedDrawingOperationException(String s) {
		super (s);
	}
}

