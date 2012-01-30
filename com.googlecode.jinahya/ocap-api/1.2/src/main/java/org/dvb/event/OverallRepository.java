package org.dvb.event ;

/**
 * This class defines a repository which initially contains all the user events which can be 
 * delivered to an application. This includes all keycodes for which KEY_PRESSED and 
 * KEY_RELEASED events can be generated and all keychars for which KEY_TYPED events can be 
 * generated. Note that the set of keycodes and keychars which can be generated is dependent 
 * on the input devices of the MHP terminal. For example, this pre-defined repository
 * could be used by an application, which requires a pin code from the user,
 * in order to prevent another applications from receiving events. 
 *
 * @see UserEvent
 * @see org.havi.ui.event.HKeyCapabilities
 */
public class OverallRepository extends UserEventRepository {
	/**
	 * The constructor for the repository. The name of the constructed instance 
	 * (as returned by getName()) is implementation dependent.
	 */
    public OverallRepository () {super(null); }
	/**
	 * The constructor for the repository with a name.
	 * @param name the name to use for the repository
	 */
    public OverallRepository (String name) {super(name); }
}

