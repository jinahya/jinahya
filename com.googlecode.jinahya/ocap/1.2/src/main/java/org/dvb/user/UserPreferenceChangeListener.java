package org.dvb.user ;

/**
 * An application wishing to be informed of any change to a user 
 * preference implements this interface.
 */
public interface UserPreferenceChangeListener extends java.util.EventListener {

  /**
    * This method is called when a user preference changes.
    *
    * @param e the event notifying this event.
    */
  public abstract void receiveUserPreferenceChangeEvent (UserPreferenceChangeEvent e);
}

