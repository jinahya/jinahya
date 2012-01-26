package org.ocap.environment;

import org.dvb.application.AppID;

/**
 * The <code>EnvironmentStateChangedEvent</code> class indicates 
 * the completion of a state transition of an environment.
 * The following steps SHALL happen before a change of selected 
 * environment is reported as completed:
 * <ul>
 * <li>the resource policy SHALL be changed to that of the new selected environment
 * <li>applications in the environment not allowed to run in its new state
 * SHALL have been killed
 * <li>applications in the environment allowed to run but not allowed to show a user
 * interface in its new state SHALL have that UI hidden, for OCAP-J applications this
 * means the HScene SHALL be hidden with the same result as a call to setVisible(false).
 * <li>reservations of application exclusive events for the old applications which are 
 * allowed to run in the new selected environment SHALL have been cancelled
 * <li>if the newly selected environment has previously been in the selected or
 * presenting state then all previously terminated applications 
 * have been re-started as {@link Environment described} as part of the transition
 * to the selected state (where re-started for Xlets 
 * mean the call to the initXlet method has completed)
 * <li>returning to normal mode any running applications which are in cross-environment 
 * mode, background mode or paused mode
 * <li>returning to the active state any pauseable applications which were 
 * paused when this environment last stopped being selected and which are still paused
 * </ul>
 * Reporting a change of selected environment as having been completed SHALL NOT wait 
 * for the following steps:
 * <ul>
 * <li>completion of the re-starting of applications (where completion
 * for Xlets means completion of calls to the startXlet method)
 * <li>requesting HScenes be visible
 * <li>requesting focus
 * </ul>
 * When any screen re-draws happen is implementation dependent and may be deferred 
 * until the new applications are ready to redraw themselves.
 */
public class EnvironmentStateChangedEvent extends EnvironmentEvent {
    
	/**
	 * Create an EnvironmentStateChangedEvent object.
	 *
	 * @param source the <code>Environment</code> where the state transition happened
	 * @param fromstate the state the environment was in before the state transition 	 * was requested
	 * @param tostate the state the environment is in after the completion of the
	 * state transition
	 */
	public EnvironmentStateChangedEvent( Environment source,
		EnvironmentState fromstate, EnvironmentState tostate)
	{
		super (source) ;
	}

	/**
	 * Return the state the environment was in before the state transition 
	 * was requested as passed to the constructor of this event.
	 *
	 * @return     the old state
	 */
	public EnvironmentState getFromState () {
		return null ;
	}
	/**
	 * Return the state the environment is in after the completion of
	 *  the state transition as passed to the constructor of this event.
	 *
	 * @return     the new state
	 */
	public EnvironmentState getToState () {
		return null ;
	}
}
