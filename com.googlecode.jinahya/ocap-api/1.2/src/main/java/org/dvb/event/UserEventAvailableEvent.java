package org.dvb.event;

/**
 * This event is sent to the resource status event listeners when user input 
 * events which had been exclusively reserved by an application are no longer
 * exclusively reserved. Where one change in user input event reservation
 * results in instances of this event being sent to several applications, the
 * following shall apply.<p><ul>
 * <li>Each application shall receive its own instance of the <code>UserEventRepository</code>
 * object which forms the source to this event. Any changes made to that repository by
 * any one application shall not impact the instance seen by any other application.
 * <li>Any application receiving an instance of this event is allowed to attempt
 * to exclusively reserve some of the newly available user events. In this situation,
 * the normal resource management policy of the platform as described elsewhere in
 * the present document shall be obeyed.
 * <li>Any applications which have registered for shared access to any of these user
 * events shall start receiving those events following receipt of this event.
 * </ul>
 * @since MHP 1.0.2
 */

public class UserEventAvailableEvent extends org.davic.resources.ResourceStatusEvent {
        /**
         * Constructor for the event.
         *
         * @param source a <code>UserEventRepository</code> which contains the events
         * which stopped being exclusively reserved.
         * @since MHP 1.0.2
         */
        public UserEventAvailableEvent( Object source ) { super(source); }

        /**
         * Returns a <code>UserEventRepository</code> which contains the events which
         * were formerly exclusively reserved as passed into the constructor of the instance.
         *
         * @return a  <code>UserEventRepository</code>
         * @since MHP 1.0.2
         */
        public Object getSource(){return null;}
}


