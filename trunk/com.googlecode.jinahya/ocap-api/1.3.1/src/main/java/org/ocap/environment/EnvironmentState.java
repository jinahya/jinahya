package org.ocap.environment;

/**
 * Defines the set of available states for an environment.
 */
public class EnvironmentState {
	/**
	 * This protected constructor is provided to enable the set
	 * of states to be extended. It is not intended to be used
	 * by applications.
	 */
	protected EnvironmentState(String s)
	{
	}
	/**
	 * The environment is inactive. No applications are running.
	 */
	public static final EnvironmentState INACTIVE = new EnvironmentState("inactive");
	/**
	 * The environment is selected. Applications are running to
	 * the maximum extent possible and are able to interact with
	 * the end-user.
	 */
	public static final EnvironmentState SELECTED  = new EnvironmentState("selected");
	/**
	 * The environment is presenting. Applications are running and
	 * may be visible to the end user. They cannot receive user input
	 * from the remote control.
	 */
	public static final EnvironmentState PRESENTING = 
		new EnvironmentState("presenting");
	/**
	 * The environment is in the background. Any running applications
	 * cannot be in the normal mode.
	 */
	public static final EnvironmentState BACKGROUND = 
		new EnvironmentState("background");
}
