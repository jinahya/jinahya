package org.havi.ui.event;

import java.util.EventObject;

/*
 * Copyright 2000-2003 by HAVi, Inc. Java is a trademark of Sun
 * Microsystems, Inc. All rights reserved.  
 */

/** 
    This event informs an application that a loading operation for an
    {@link org.havi.ui.HBackgroundImage HBackgroundImage} has
    finished.

  <hr>
  The parameters to the constructors are as follows, in cases where
  parameters are not used, then the constructor should use the default
  values.
  <p>
  <h3>Default parameter values exposed in the constructors</h3>
  <table border>
  <tr><th>Parameter</th><th>Description</th><th>Default value</th> 
  <th>Set method</th><th>Get method</th></tr>
  <tr><td colspan=5>None.</td></tr>
  </table>
  <h3>Default parameter values not exposed in the constructors</h3>
  <table border>
  <tr><th>Description</th><th>Default value</th><th>Set method</th>
  <th>Get method</th></tr>
  <tr><td colspan=4>None.</td></tr>
  </table>

  */

public class HBackgroundImageEvent extends java.util.EventObject
{
    /**
     * Marks the first integer for the range of background image
     * events
     */
    public static final int BACKGROUNDIMAGE_FIRST          = 1;
        
    /**
     * The loading succeeded
     */
    public static final int BACKGROUNDIMAGE_LOADED         = 1;
    
    /**
     * The loading failed before attempting to load any data from 
     * the file. e.g. the file not existing or due to a badly formed 
     * or otherwise broken filename
     */
    public static final int BACKGROUNDIMAGE_FILE_NOT_FOUND = 2;
    
    /**
     * The loading failed due to an error while loading the data. 
     * e.g. the file is not accessible or loading of it was interrupted
     */
    public static final int BACKGROUNDIMAGE_IOERROR        = 3;
    
    /**
     * The loading failed because the data loaded is not valid. e.g.
     * not a supported coding format for background images.
     */
    public static final int BACKGROUNDIMAGE_INVALID        = 4;
    
    /**
     * Marks the last integer for the range of background image events 
     */
    public static final int BACKGROUNDIMAGE_LAST           = 4;

    /**
     * Constructs a new {@link org.havi.ui.event.HBackgroundImageEvent
     * HBackgroundImageEvent}. 
     *
     * @param source the {@link org.havi.ui.HBackgroundImage
     * HBackgroundImage} which has been loaded.  
     * @param id the type of event (one of {@link
     * org.havi.ui.event.HBackgroundImageEvent#BACKGROUNDIMAGE_LOADED
     * BACKGROUNDIMAGE_LOADED}, {@link
     * org.havi.ui.event.HBackgroundImageEvent#BACKGROUNDIMAGE_FILE_NOT_FOUND
     * BACKGROUNDIMAGE_FILE_NOT_FOUND}, {@link
     * org.havi.ui.event.HBackgroundImageEvent#BACKGROUNDIMAGE_IOERROR
     * BACKGROUNDIMAGE_IOERROR} or {@link
     * org.havi.ui.event.HBackgroundImageEvent#BACKGROUNDIMAGE_INVALID
     * BACKGROUNDIMAGE_INVALID}).
     */
    public HBackgroundImageEvent(Object source, int id)
    {
	super(source);
    }

    /**
     * Returns the {@link org.havi.ui.HBackgroundImage
     * HBackgroundImage} for which the data has been loaded.
     *
     * @return the object which has been loaded.  
     */
    public Object getSource()
    {
	return (null);
    }

    /**
     * Returns the type for this event.
     *
     * @return the event type (one of {@link
     * org.havi.ui.event.HBackgroundImageEvent#BACKGROUNDIMAGE_LOADED
     * BACKGROUNDIMAGE_LOADED}, {@link
     * org.havi.ui.event.HBackgroundImageEvent#BACKGROUNDIMAGE_FILE_NOT_FOUND
     * BACKGROUNDIMAGE_FILE_NOT_FOUND}, {@link
     * org.havi.ui.event.HBackgroundImageEvent#BACKGROUNDIMAGE_IOERROR
     * BACKGROUNDIMAGE_IOERROR} or {@link
     * org.havi.ui.event.HBackgroundImageEvent#BACKGROUNDIMAGE_INVALID
     * BACKGROUNDIMAGE_INVALID}).
     */
    public int getID()
    {
	return (0);
    }
}








