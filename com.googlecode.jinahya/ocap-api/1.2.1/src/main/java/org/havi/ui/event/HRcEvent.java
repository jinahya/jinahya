
package org.havi.ui.event;

/*
 * Copyright 2000-2003 by HAVi, Inc. Java is a trademark of Sun
 * Microsystems, Inc. All rights reserved.  
 */

import java.awt.Component;
import java.awt.event.KeyEvent;

/**
   The remote control event class. 

   <p> 
   The presence or absence of these keys and their desired
   representation is provided by the {@link
   org.havi.ui.event.HRcCapabilities HRcCapabilities} class.

   <p> 
   Note that it is an implementation option if remote control key
   events are repeated.
   
   <p> 
   Instances of {@link org.havi.ui.event.HRcEvent HRcEvent} are
   reported through the normal <code>java.awt</code> event mechanism
   Note that the reception of these events by a
   <code>java.awt.Component</code> is dependent on it having
   <code>java.awt.event.KeyEvent</code> events enabled.
   
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

public class HRcEvent 
    extends KeyEvent
{
    /**
     * Marks the first integer id for the range of remote control
     * key codes.
     * @deprecated The value of this field is useless, since it mixes
     * event ids and key codes. It does <b>not</b> reflect any of the
     * remote control key codes listed in this class.
     */
    public static final int RC_FIRST = 400;

    /**
     * Colored key 0 key code. <p> Up to six colored soft keys can
     * be included on a remote control.  These are optional, and must
     * be identified with a color. If implemented, these keys are to
     * be oriented from left to right, or from top to bottom in
     * ascending order.  <p> The application can determine how many
     * colored keys are implemented, and what colors are to be used,
     * so that the application can match the controls, by using the
     * getRepresentation method in the HRcCapabilities class.
     * 
     * @see org.havi.ui.event.HRcEvent#VK_COLORED_KEY_1
     * @see org.havi.ui.event.HRcEvent#VK_COLORED_KEY_2
     * @see org.havi.ui.event.HRcEvent#VK_COLORED_KEY_3
     * @see org.havi.ui.event.HRcEvent#VK_COLORED_KEY_4
     * @see org.havi.ui.event.HRcEvent#VK_COLORED_KEY_5 
     */
    public static final int VK_COLORED_KEY_0 = 403;

    /**
     * Colored key 1 key code.
     *
     * @see org.havi.ui.event.HRcEvent#VK_COLORED_KEY_0
     */
    public static final int VK_COLORED_KEY_1 = VK_COLORED_KEY_0 + 1;

    /**
     * Colored key 2 key code.
     *
     * @see org.havi.ui.event.HRcEvent#VK_COLORED_KEY_0
     */
    public static final int VK_COLORED_KEY_2 = VK_COLORED_KEY_1 + 1;

    /**
     * Colored key 3 key code.
     *
     * @see org.havi.ui.event.HRcEvent#VK_COLORED_KEY_0
     */
    public static final int VK_COLORED_KEY_3 = VK_COLORED_KEY_2 + 1;

    /**
     * Colored key 4 key code.
     *
     * @see org.havi.ui.event.HRcEvent#VK_COLORED_KEY_0
     */
    public static final int VK_COLORED_KEY_4 = VK_COLORED_KEY_3 + 1;

    /**
     * Colored key 5 key code.
     *
     * @see org.havi.ui.event.HRcEvent#VK_COLORED_KEY_0
     */
    public static final int VK_COLORED_KEY_5 = VK_COLORED_KEY_4 + 1;

    /**
     * The 'device power' key code turns on or off the delegated
     * device.  
     */
    public static final int VK_POWER = VK_COLORED_KEY_5 + 1;

    /**
     * The 'device dimmer' key code adjusts illumination of the
     * device.  <p> This may be a toggle between two states, or a
     * sequence through multiple states.  
     */
    public static final int VK_DIMMER = VK_POWER + 1;

    /**
     * The 'device wink' key code is used to indicated that the
     * device should identify itself in some manner, for example,
     * audibly or visually.  
     */
    public static final int VK_WINK = VK_DIMMER + 1;

    /**
     * The 'rewind (media)' key code.
     */
    public static final int VK_REWIND = VK_WINK + 1;
   
    /**
     * The 'stop (media)' key code.
     */
    public static final int VK_STOP = VK_REWIND + 1;
      
    /**
     * The 'eject / insert media' key code.
     */
    public static final int VK_EJECT_TOGGLE = VK_STOP + 1;
   
    /**
     * The 'play (media)' key code.
     */
    public static final int VK_PLAY = VK_EJECT_TOGGLE + 1;
   
    /**
     * The 'record (to media)' key code.
     */
    public static final int VK_RECORD = VK_PLAY + 1;
   
    /**
     * The 'fast forward (media)' key code.
     */
    public static final int VK_FAST_FWD = VK_RECORD + 1;          
   
     /**
     * The 'increase (media) play speed' key code.
     */
    public static final int VK_PLAY_SPEED_UP = VK_FAST_FWD + 1;
   
    /**
     * The 'decrease (media) play speed' key code.
     */
    public static final int VK_PLAY_SPEED_DOWN = VK_PLAY_SPEED_UP + 1;
   
    /**
     * The 'set (media) play speed to normal' key code.
     */
    public static final int VK_PLAY_SPEED_RESET = VK_PLAY_SPEED_DOWN + 1;
   
    /**
     * The 'select next (media) record speed' key code.
     */
    public static final int VK_RECORD_SPEED_NEXT = VK_PLAY_SPEED_RESET + 1;
   
    /**
     * The 'go (send media) to start position' key code.
     */
    public static final int VK_GO_TO_START = VK_RECORD_SPEED_NEXT + 1;
   
    /**
     * The '(send media) to end position' key code.
     */
    public static final int VK_GO_TO_END = VK_GO_TO_START + 1;
   
    /**
     * The '(send media) to previous track' key code.
     */
    public static final int VK_TRACK_PREV = VK_GO_TO_END + 1;
   
    /**
     * The '(send media) to next track' key code.
     */
    public static final int VK_TRACK_NEXT = VK_TRACK_PREV + 1;
   
    /**
     * The 'toggle random (media) play' key code.
     */
    public static final int VK_RANDOM_TOGGLE = VK_TRACK_NEXT + 1;
   
    /**
     * The 'channel up' key code.
     */
    public static final int VK_CHANNEL_UP = VK_RANDOM_TOGGLE + 1;
   
    /**
     * The 'channel down' key code.
     */
    public static final int VK_CHANNEL_DOWN = VK_CHANNEL_UP + 1;
   
    /**
     * The 'store current setting as favorite 0' key code.
     */
    public static final int VK_STORE_FAVORITE_0 = VK_CHANNEL_DOWN + 1;
   
    /**
     * The 'store current setting as favorite 1' key code.
     */
    public static final int VK_STORE_FAVORITE_1 = VK_STORE_FAVORITE_0 + 1;
   
    /**
     * The 'store current setting as favorite 2' key code.
     */
    public static final int VK_STORE_FAVORITE_2 = VK_STORE_FAVORITE_1 + 1;
   
    /**
     * The 'store current setting as favorite 3' key code.
     */
    public static final int VK_STORE_FAVORITE_3 = VK_STORE_FAVORITE_2 + 1;
   
    /**
     * The 'recall favorite 0' key code.
     */
    public static final int VK_RECALL_FAVORITE_0 = VK_STORE_FAVORITE_3 + 1;
   
    /**
     * The 'recall favorite 1' key code.
     */
    public static final int VK_RECALL_FAVORITE_1 = VK_RECALL_FAVORITE_0 + 1;
   
    /**
     * The 'recall favorite 2' key code.
     */
    public static final int VK_RECALL_FAVORITE_2 = VK_RECALL_FAVORITE_1 + 1;
   
    /**
     * The 'recall favorite 3' key code.
     */
    public static final int VK_RECALL_FAVORITE_3 = VK_RECALL_FAVORITE_2 + 1;
   
    /**
     * The 'clear favorite 0' key code.
     */
    public static final int VK_CLEAR_FAVORITE_0 = VK_RECALL_FAVORITE_3 + 1;
   
    /**
     * The 'clear favorite 1' key code.
     */
    public static final int VK_CLEAR_FAVORITE_1 = VK_CLEAR_FAVORITE_0 + 1;
   
    /**
     * The 'clear favorite 2' key code.
     */
    public static final int VK_CLEAR_FAVORITE_2 = VK_CLEAR_FAVORITE_1 + 1;
   
    /**
     * The 'clear favorite 3' key code.
     */
    public static final int VK_CLEAR_FAVORITE_3 = VK_CLEAR_FAVORITE_2 + 1;
   
    /**
     * The 'scan channels toggle' key code - turns channel scanning on or off.
     */
    public static final int VK_SCAN_CHANNELS_TOGGLE = VK_CLEAR_FAVORITE_3 + 1;
   
    /**
     * The 'picture in picture toggle' key code - turns picture in
     * picture mode on or off.  
     */
    public static final int VK_PINP_TOGGLE = VK_SCAN_CHANNELS_TOGGLE + 1;
   
    /**
     * The 'split screen toggle' key code - turns split screen on or
     * off.
     */
    public static final int VK_SPLIT_SCREEN_TOGGLE = VK_PINP_TOGGLE + 1;
   
    /**
     * The 'display swap' key code - swaps displayed video sources.
     */
    public static final int VK_DISPLAY_SWAP = VK_SPLIT_SCREEN_TOGGLE + 1;
   
    /**
     * The 'screen mode next' key code - advances the display screen
     * mode.
     */
    public static final int VK_SCREEN_MODE_NEXT = VK_DISPLAY_SWAP + 1;
   
    /**
     * The 'video mode next' key code - advances the display video
     * mode.
     */
    public static final int VK_VIDEO_MODE_NEXT = VK_SCREEN_MODE_NEXT + 1;

    /**
     * The 'volume up' key code - increases audio amplifier volume.
     */
    public static final int VK_VOLUME_UP = VK_VIDEO_MODE_NEXT + 1;
   
    /**
     * The 'volume down' key code - decreases audio amplifier volume.
     */
    public static final int VK_VOLUME_DOWN = VK_VOLUME_UP + 1;
   
    /**
     * The 'mute' key code - mute audio output
     */
    public static final int VK_MUTE = VK_VOLUME_DOWN + 1;
   
    /**
     * The 'surround mode next' key code - advances audio amplifier
     * surround mode.  
     */
    public static final int VK_SURROUND_MODE_NEXT = VK_MUTE + 1;
   
    /**
     * The 'balance right' key code - moves the audio balance to the
     * right.  
     */
    public static final int VK_BALANCE_RIGHT = VK_SURROUND_MODE_NEXT + 1;
   
    /**
     * The 'balance left' key code - moves the audio balance to the left.
     */
    public static final int VK_BALANCE_LEFT = VK_BALANCE_RIGHT + 1;
   
    /**
     * The 'fader front' key code - moves the audio fader to the front.
     */
    public static final int VK_FADER_FRONT = VK_BALANCE_LEFT + 1;
   
    /**
     * The 'fader rear' key code - moves the audio fader to the rear.
     */
    public static final int VK_FADER_REAR = VK_FADER_FRONT + 1;
   
    /**
     * The 'bass boost up' key code - increases the audio amplifier
     * bass boost.
     */
    public static final int VK_BASS_BOOST_UP = VK_FADER_REAR + 1;
   
    /**
     * The 'bass boost down' key code - decreases the audio amplifier
     * bass boost.
     */
    public static final int VK_BASS_BOOST_DOWN = VK_BASS_BOOST_UP + 1;
      
    /**
     * The 'info' key code - indicates that the user has requested
     * additional information (toggle).  
     */
    public static final int VK_INFO = VK_BASS_BOOST_DOWN + 1;
   
    /**
     * The 'guide' key code - indicates a user request for a
     * program guide (toggle).  
     */
    public static final int VK_GUIDE = VK_INFO + 1;
   
    /**
     * The 'teletext' key code - indicates a user request for a
     * teletext service (toggle).  
     */
    public static final int VK_TELETEXT = VK_GUIDE + 1;
   
    /**
     * The 'subtitle' key code - indicates a user request for
     * subtitling (toggle).  
     */
    public static final int VK_SUBTITLE = VK_TELETEXT + 1;
    
    /**
     * Marks the last integer id for the range of remote control key
     * codes.
     * @deprecated
     */
    public static final int RC_LAST = VK_SUBTITLE;
   
    /**
     * Constructs an {@link org.havi.ui.event.HRcEvent HRcEvent}
     * object with the specified source component, type, modifiers and
     * key.
     *
     * @param source the object where the event originated.
     * @param id the identifier in the range <code>KEY_FIRST</code> to
     * <code>KEY_LAST</code>.
     * @param when the time stamp for this event.
     * @param modifiers indication of any modification keys that are
     * active for this event.
     * @param keyCode the code of the key associated with this event.
     * @deprecated See explanation in java.awt.event.KeyEvent.  
     */
//    public HRcEvent(Component source, int id, long when,
//                     int modifiers, int keyCode)
//    {
//	super(source, id, when, modifiers, keyCode);
//    }
   
    /**
     * Constructs an {@link org.havi.ui.event.HRcEvent HRcEvent}
     * object with the specified source component, type, modifiers and
     * key.
     *
     * @param source the object where the event originated.
     * @param id the identifier in the range <code>KEY_FIRST</code> to
     * <code>KEY_LAST</code>.
     * @param when the time stamp for this event.
     * @param modifiers indication of any modification keys that are
     * active for this event.
     * @param keyCode the code of the key associated with this event.
     * @param keyChar the character representation of the key
     * associated with this event.
     */
    public HRcEvent(Component source, int id, long when,
                     int modifiers, int keyCode, char keyChar)
    {
	super(source, id, when, modifiers, keyCode, keyChar);
    }
}



