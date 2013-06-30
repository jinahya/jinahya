/*
 * OCRcEvent.java
 *
 * Created on April 27, 2001, 12:54 AM
 */

package org.ocap.ui.event;

/**
 * <p>
 * The OCAP remote control event class. This class provides constants of 
 * key codes extended by OCAP. 
 * </p><p>
 * The presence or absence of these keys and their desired representation 
 * is provided by the org.havi.ui.event.HRcCapabilities and the 
 * org.havi.ui.event.HEventRepresentaion. 
 * </p><p>
 * Instances of OCRcEvent are reported through the normal java.awt event 
 * mechanism.
 * Note that the reception of these events by a java.awt.Component is 
 * dependent on it having java.awt.event.KeyEvent events enabled.
 * </p><p>
 * Note that it is an implementation option if remote control key events are 
 * repeated. All KEY PRESSED, KEY TYPED and KEY RELEASED events shall be 
 * generated.  An application is able to determine whether a key is being 
 * continuously pressed by containing logic to detect the KEY RELEASED event
 * after a KEY PRESSED event.
 * </p>
 *
 * @author  Ralph Brown (excite@Home) and Mark S. Millard (Vidiom Systems)
 * @author  Revisions by Shigeaki Watanabe (Panasonic), Kinney Bacon (SA)
 *          and Allen Gordon (CableLabs)
 * @version 1.0
 */
public class OCRcEvent extends org.havi.ui.event.HRcEvent
{
    /**
     * Marks the first integer id for the range of OCAP remote control key 
     * codes.
     */
public static final int OCRC_FIRST = 600;

    /**
     * The 'RF Bypass' key code.
     *
     * Indicates a user request to bypass the set-top by passing the RF 
     * input directly to the set-top RF output (toggle). 
     */
public static final int VK_RF_BYPASS = OCRC_FIRST;

    /**
     * The 'exit' key code.
     *
     * Indicates a user request to exit the current application.
     */
public static final int VK_EXIT = VK_RF_BYPASS + 1;

    /**
     * The 'menu' key code.
     *
     * Indicates a user request for an on-screen menu (toggle).
     */
public static final int VK_MENU = VK_EXIT + 1;

    /**
     * The guide 'next day' key code.
     *
     * Indicates a user request for the next day's worth of EPG data from 
     * the guide application.
     *
     * @see org.ocap.ui.event.OCRcEvent#VK_PREV_DAY
     */
public static final int VK_NEXT_DAY = VK_MENU + 1;

    /**
     * The guide 'previous day' key code.
     *
     * Indicates a user request for the previous day's worth of EPG data 
     * from the guide applications.
     *
     * @see org.ocap.ui.event.OCRcEvent#VK_NEXT_DAY
     */
public static final int VK_PREV_DAY = VK_NEXT_DAY + 1;

    /**
     * The 'apps' key code.
     *
     * Indicates a user request for applications.
     */
public static final int VK_APPS = VK_PREV_DAY + 1;


    /**
     * The 'link' key code.
     *
     * Indicates a user request for launching linked content.
     */
public static final int VK_LINK = VK_APPS + 1;


    /**
     * The 'last' key code.
     *
     * Indicates a user request for tuning to the last channel tuned.
     */
public static final int VK_LAST = VK_LINK + 1;

    /**
     * The 'back' key code.
     *
     * Indicates a user request moving to the previous URL or web page.
     *
     * @see org.ocap.ui.event.OCRcEvent#VK_FORWARD
     */
public static final int VK_BACK = VK_LAST + 1;

    /**
     * The 'forward' key code.
     *
     * Indicates a user request to move to the next URL or web page.
     *
     * @see org.ocap.ui.event.OCRcEvent#VK_BACK
     */
public static final int VK_FORWARD = VK_BACK + 1;

    /**
     * The 'zoom' key code.
     *
     * Indicates a user request to toggle from full-screen to scaled between 
     * TV and data pages.
     */
public static final int VK_ZOOM = VK_FORWARD + 1;

    /**
     * The 'settings' key code.
     *
     * Indicates a user request to access the settings (user id, email 
     * account, parental control, etc.).
     */
public static final int VK_SETTINGS = VK_ZOOM + 1;
    
    /**
     * The ’next favorite channel’ key code.	
     *
     * Indicates a user request to tune to the next favorite channel.
     */    
public static final int VK_NEXT_FAVORITE_CHANNEL = VK_SETTINGS + 1;		
        
    /**
     * The ’reserved’ key code number 1.
     *
     * Reserved for future use.
     */        
public static final int VK_RESERVE_1 = VK_NEXT_FAVORITE_CHANNEL + 1;
        	

    /**
     * The ’reserved’ key code number 2.	
     *
     * Reserved for future use.
     */
public static final int VK_RESERVE_2 = VK_RESERVE_1 + 1;		
        	

    /**
     * The ’reserved’ key code number 3.
     *
     * Reserved for future use.
     */
public static final int VK_RESERVE_3 = VK_RESERVE_2 + 1;		
        	
    /**
     * The ’reserved’ key code number 4.
     *
     * Reserved for future use. 
     */        
public static final int VK_RESERVE_4 = VK_RESERVE_3 + 1;		
        	
    /**
     * The ’reserved’ key code number 5.	
     *
     * Reserved for future use. 
     */        
public static final int VK_RESERVE_5 = VK_RESERVE_4 + 1;		
        	
    /**
     * The ’reserved’ key code number 6.	
     *
     * Reserved for future use.
     */        
public static final int VK_RESERVE_6 = VK_RESERVE_5 + 1;		
        	
    /**
     * The ’lock’ key code.		
     *
     * Indicates a user request to lock the current program.
     */        
public static final int VK_LOCK = VK_RESERVE_6 + 1;		
        
    /**
     * The ’skip’ key code.	
     *
     * Indicates a user request to skip the current program.
     */        
public static final int VK_SKIP = VK_LOCK + 1; 		
        
    /**
     * The ’list’ key code.	
     *
     * Indicates a user request to list the current program.
     */        
public static final int VK_LIST = VK_SKIP + 1;		
        	
    /**
     * The ’live’ key code.	
     *
     * Indicates a user request to view live programs.
     */        
public static final int VK_LIVE = VK_LIST + 1;		
        	
    /**
     * The ’on demand’ key code.	
     *
     * Indicates a user request to access on demand functions.
     */        
public static final int VK_ON_DEMAND = VK_LIVE + 1;		
        	
    /**
     * The ’picture-in-picture move’ key code.		
     *
     * Indicates a user request to move the picture-in-picture window.
     */        
public static final int VK_PINP_MOVE = VK_ON_DEMAND + 1;		
        
    /**
     * The ’picture-in-picture up’ key code.
     *
     * Indicates a user request to move the picture-in-picture window up.
     */        
public static final int VK_PINP_UP = VK_PINP_MOVE + 1;		
        	
    /**
     * The ’picture-in-picture down’ key code.
     *
     * Indicates a user request to move the picture-in-picture window down.
     */        
public static final int VK_PINP_DOWN = VK_PINP_UP + 1;

    /**
     * The 'instant replay' key code.
     * 
     * Indicates a user request to invoke the instant replay feature.  
     */

public static final int VK_INSTANT_REPLAY = VK_PINP_DOWN + 1;	

     /** 
     * The ‘RC low battery’ key code
     *
     * Indicates that the remote control battery is low.  Generated automatically by
     * the remote, there are no corresponding buttons on the remote.  
     * Informative Note: Dialog to the user should indicate this is for the STB
     * remote.
     */
public static final int VK_RC_LOW_BATTERY = VK_INSTANT_REPLAY + 1;

     /** 
     * The 'user' key code. 
     *     
     * Indicates a user request to switch to the next user profile if multiple user
     * profiles exist.      
     */
public static final int VK_USER = VK_RC_LOW_BATTERY + 1;

     /** 
     * The 'Closed Caption' key code. 
     * 
     * Indicates a user request to toggle closed caption on/off.  
     */
public static final int VK_CC = VK_USER + 1;

   /**
     * Marks the last integer id for the range of OCAP remote control key 
     * codes.
     */
public static final int OCRC_LAST = VK_CC;


    /**
     * Constructs an {@link org.ocap.ui.event.OCRcEvent OCRcEvent} object.
     *
     * @param source the object where the event originated.
     *
     * @param id the identifier in the range KEY_FIRST to KEY_LAST.
     *
     * @param when the time stamp for this event.
     *
     * @param modifiers indication of any modification keys that are active 
     *               for this event.
     *
     * @param keyCode the code of the key associated with this event.
     *
     * @param keyChar the character representation of the key associated 
     *               with this event.
     */
    public OCRcEvent(java.awt.Component source, int id, long when, 
                        int modifiers, int keyCode, char keyChar) {
        super(source, id, when, modifiers, keyCode, keyChar);
    }
}
