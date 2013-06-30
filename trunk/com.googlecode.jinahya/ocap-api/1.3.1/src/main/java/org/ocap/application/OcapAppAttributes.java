//
// OcapAppAttributes.java
//
package org.ocap.application;

import org.ocap.environment.Environment;
import org.ocap.environment.EnvironmentState;


/**
 * <P>
 * This interface represents various information about an application 
 * registered in the AppsDatabase. 
 * This interface extends the org.dvb.application.AppAttributes in the 
 * points of following: 
 * <BR>
 *  Defining the OCAP Application types.
 * <BR>
 *  Adding the getControlFlag method to get the application_control_code 
 *    flag as signaled in an AIT or an XAIT.
 * </P><P> 
 * For applications which are signaled in the AIT or the XAIT, the 
 * mapping between the values returned by methods 
 * in this interface and the fields and descriptors of the AIT or the 
 * XAIT shall be as specified in this specification. 
 * Instance of the class implementing this interface are immutable. 
 * </P><P>
 * org.dvb.application.AppsDatabase MUST return an instance of OcapAppAttributes by the 
 * getAppAttributes methods.
 * </P>
 */
public interface OcapAppAttributes
    extends org.dvb.application.AppAttributes
{
/**
 * The OCAP registered value for all OCAP-J applications. 
 */
static final int OCAP_J = 1;
    
/**
 * This represents the application control code "AUTOSTART" 
 * defined for the application_control_code in an AIT or a XAIT.
 *
 */
static final int AUTOSTART = 1;

/**
 * This represents the application control code "PRESENT" 
 * defined for the application_control_code in an AIT or a XAIT.
 */
static final int PRESENT = 2;

/**
 * This represents the application control code "DESTROY" 
 * defined for the application_control_code in an AIT or a XAIT.
 */
static final int DESTROY = 3;

/**
 * This represents the application control code "KILL" 
 * defined for the application_control_code in an AIT or a XAIT.
 */
static final int KILL = 4;

/**
 * The application control code "PREFETCH" is only used
 * for DVB-HTML applications.
 */
static final int PREFETCH = 5;

/**
 * This represents the application control code "REMOTE" 
 * defined for the application_control_code in an AIT.
 */
static final int REMOTE = 6;

/**
 * This method returns the application_control_code of the 
 * application represented by this interface. 
 *
 * @return int The application_control_code of the application 
 *             represented by this interface. 
 */
public int getApplicationControlCode();
    
/**
 * This method returns the currently set storage priority for the application.
 *
 * @return int  The storage priority for a currently stored application
 *              or zero if the application is not stored.
 */
public int getStoragePriority();
    
/**
 * Indicates that a new version of the application is stored that 
 * will replace the currently launched version when a new lifecycle
 * for this application starts.
 * <p>
 * This method SHALL return <code>true</code> if {@link #getStoragePriority}
 * would return non-zero for the newer version of the application after 
 * it replaces the currently launched version.
 * This method SHALL return <code>false</code> if {@link #isNewVersionSignaled()}
 * would return <code>false</code>.
 * 
 * @return  True, if the application is currently launched but a new
 *          version is signaled and stored.
 *          False, if the application is not currently launched, if
 *          the currently signaled version matches the currently launched
 *          version, or the new version is not yet stored.
 */
public boolean hasNewVersion();
    
/**
 * Indicates that a new version of the application is available and 
 * will replace the currently launched version when a new lifecycle
 * for this application starts.
 * 
 * @return  True, if the application is currently launched but a new
 *          version is signaled.
 *          False, if the application is not currently launched or if
 *          the currently signaled version matches the currently launched
 *          version.
 */
public boolean isNewVersionSignaled();

/**
 * This represents the "legacy" application mode defined for the
 * <code>application_mode_descriptor</code> in an AIT or XAIT.
 */ 
public static final int LEGACY_MODE = 0;

/**
 * This represents the "normal" application mode defined for the
 * <code>application_mode_descriptor</code> in an AIT or XAIT.
 */ 
public static final int NORMAL_MODE = 1;

/**
 * This represents the "cross-environment" application mode defined for the
 * <code>application_mode_descriptor</code> in an or XAIT.
 */ 
public static final int CROSSENVIRONMENT_MODE = 2;

/**
 * This represents the "background" application mode defined for the
 * <code>application_mode_descriptor</code> in an AIT or XAIT.
 */ 
public static final int BACKGROUND_MODE = 3;

/**
 * This represents the "paused" application mode defined for the
 * <code>application_mode_descriptor</code> in an AIT or XAIT.
 */ 
public static final int PAUSED_MODE = 4;

/**
 * Returns the signaled application mode for this application.
 * That is, the application mode that this application would run
 * in when the application's home environment is not 
 * {@link EnvironmentState#SELECTED selected} or
 * {@link EnvironmentState#PRESENTING presenting}.
 * <p>
 * A value of <code>NORMAL_MODE</code> indicates that the application
 * will be terminated when the home environment is not selected
 * or presenting.  Any other value indicates the mode that the
 * application would run in.
 * <p>
 * Where no <code>application_mode_descriptor</code> was signaled,
 * the default value of <code>LEGACY_MODE</code> SHALL be returned.
 * <p>
 * Where the host does not support the signaled
 * <code>application_mode_descriptor</code> and it
 * is therefore otherwise ignored, the int corresponding to the signaled
 * <code>application_mode_descriptor</code> SHALL be returned as specified
 * in the return values listed below.
 * <p>
 * The current application mode can be determined by consulting
 * {@link Environment#getState} in addition to considering the signaled
 * application mode.
 * 
 * @return one of {@link #LEGACY_MODE}, {@link #NORMAL_MODE},
 *                {@link #CROSSENVIRONMENT_MODE},
 *                {@link #BACKGROUND_MODE}, or
 *                {@link #PAUSED_MODE}
 */
public int getApplicationMode();

}
