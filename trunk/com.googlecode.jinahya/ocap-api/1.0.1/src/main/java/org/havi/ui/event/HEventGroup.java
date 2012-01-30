package org.havi.ui.event;

/*
 * Copyright 2000-2003 by HAVi, Inc. Java is a trademark of Sun
 * Microsystems, Inc. All rights reserved.  
 */

/**
 * Represents a group of key codes.
 * Groups do not keep a count of the number of times a particular key code is 
 * added or removed. Repeatedly adding an event to a group has no effect. Removing 
 * an event removes it regardless of the number of times it has been added.
 * Groups are resolved when they are passed into the methods of HScene.
 * Adding or removing events from the group after those method calls does not 
 * affect the subscription to those events.
 */
public class HEventGroup {
      
  /**
   * Constructor for an empty event group
   */
  public HEventGroup(){}

  /**
   * A shortcut to create a new key event type entry in the group.
   * If a key is already in the group, this method has no effect.
   * 
   * @param keycode the key code.
   */
  public void addKey (int keycode) {}

  /**
   * The method to remove a key from the group. Removing a key which
   * is not in the group has no effect.
   * 
   * @param keycode the key code.
   */
  public void removeKey (int keycode) {}

  /**
   * Adds the key codes for the numeric keys (VK_0, VK_1, VK_2, VK_3,
   * VK_4, VK_5, VK_6, VK_7, VK_8, VK_9). Any key codes already in the
   * group will not be added again.
   */
  public void addAllNumericKeys() {}
  
  /**
   * Adds the key codes for the colour keys (VK_COLORED_KEY_0,
   * VK_COLORED_KEY_1, VK_COLORED_KEY_2, VK_COLORED_KEY_3). Any key
   * codes already in the group will not be added again.
   */
  public void addAllColourKeys(){}
     
  /**
   * Adds the key codes for the arrow keys (VK_LEFT, VK_RIGHT, VK_UP,
   * VK_DOWN). Any key codes already in the group will not be
   * added again.
   */
  public void addAllArrowKeys(){}
  
  /**
   * Remove the key codes for the numeric keys (VK_0, VK_1, VK_2, VK_3,
   * VK_4, VK_5, VK_6, VK_7, VK_8, VK_9). Key codes from this set which
   * are not present in the group will be ignored.
   */
  public void removeAllNumericKeys(){}  
       
  /**
   * Removes the key codes for the colour keys (VK_COLORED_KEY_0,
   * VK_COLORED_KEY_1, VK_COLORED_KEY_2, VK_COLORED_KEY_3). Key codes from
   * this set which are not present in the group will be ignored.
   */
  public void removeAllColourKeys(){}
         
  /**
   * Removes the key codes for the arrow keys (VK_LEFT, VK_RIGHT, VK_UP,
   * VK_DOWN). Key codes from this set which are not present in the
   * group will be ignored.
   */
  public void removeAllArrowKeys(){}
     
  /**
   * Return the key codes contained in this event group.
   */
  public int[] getKeyEvents() {
    return null;
  }
}

