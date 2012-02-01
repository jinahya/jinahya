/*
 * @(#)SampleDataInterface.java	1.00 2000/04/05
 *
 * Copyright 2000 by Sun Microsystems, Inc.,
 * 901 San Antonio Road, Palo Alto, California, 94303, U.S.A.
 * All rights reserved.
 *
 * This software is the confidential and proprietary information
 * of Sun Microsystems, Inc. ("Confidential Information").  You
 * shall not disclose such Confidential Information and shall use
 * it only in accordance with the terms of the license agreement
 * you entered into with Sun.
 *
 */
package com.sun.tv.receiver;



/**
 * This interface is used by the <code>SIEmulator class
 * to populate the service information database.
 * A class inplementing this interface is specified in 
 * the properties file. 
 */
public interface SampleDataInterface {

    /**
     * This method is the first method called by the
     * SIEmulator. Implementations of this interface should load
     * their SI data via the programatic interfaces in 
     * SIEmulator.
     */
    public void play(SIEmulator emulator, String args[]);
    
    /**
     * This method is called from SIEmulator after the
     * play method is called. It is intended to block until
     * the SI has been loaded, or when the implementation of
     * this interface is satisfied that enough of the SI data
     * has been loaded to continue.
     */
    public void finish();
    
    /**
     * This method can be called to verify that all the data
     * has been loaded into the SI database. It is not
     * called SIEmulator.
     */
    public boolean verify();

}
