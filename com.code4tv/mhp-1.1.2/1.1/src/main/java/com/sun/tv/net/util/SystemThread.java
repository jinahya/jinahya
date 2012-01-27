/*
 * @(#)SystemThread.java	1.10 97/07/01 SMI
 *
 * Copyright (c) 1996-1997 Sun Microsystems, Inc. All Rights Reserved.
 *
 * This software is the confidential and proprietary information of Sun
 * Microsystems, Inc. ("Confidential Information").  You shall not
 * disclose such Confidential Information and shall use it only in
 * accordance with the terms of the license agreement you entered into
 * with Sun.
 *
 * SUN MAKES NO REPRESENTATIONS OR WARRANTIES ABOUT THE SUITABILITY OF THE
 * SOFTWARE, EITHER EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR
 * PURPOSE, OR NON-INFRINGEMENT. SUN SHALL NOT BE LIABLE FOR ANY DAMAGES
 * SUFFERED BY LICENSEE AS A RESULT OF USING, MODIFYING OR DISTRIBUTING
 * THIS SOFTWARE OR ITS DERIVATIVES.
 */
package com.sun.tv.net.util;

/**
 * A SystemThread is allowed non-standard priority levels, has a specifiable
 * stack size, and is not counted as a user thread.
 */
public class SystemThread extends Thread {

    public SystemThread(String name, int priority, int stackSize) {
	super(name);
	setDaemon(true);
	this.systemPriority = priority;
	this.stackSize = stackSize;
    }

    public SystemThread(String name, int priority) {
	this(name, priority, 0);
    }


    public final int getSystemPriority() {
	return systemPriority;
    }

    private int systemPriority;
    private int stackSize;

    public static final int ClockThreadPriority = MAX_PRIORITY + 6;
    public static final int InterruptThreadPriority = MAX_PRIORITY + 5;
    public static final int MouseThreadPriority = MAX_PRIORITY + 4;
    public static final int NetworkThreadPriority = MAX_PRIORITY + 3;
    public static final int NetworkBackgroundThreadPriority = MAX_PRIORITY + 2;
    public static final int TimerThreadPriority = MAX_PRIORITY + 1;
    public static final int KeyboardThreadPriority = MAX_PRIORITY + 1;
    public static final int TTYDebugThreadPriority = MAX_PRIORITY + 0;
    public static final int ApplicationThreadPriority = MAX_PRIORITY - 1;
    public static final int BackgroundApplicationThreadPriority = MAX_PRIORITY - 2;
    public static final int IdleThreadPriority = MIN_PRIORITY - 1;

}
