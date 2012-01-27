package org.dvb.smartcard;

/**
 * The listener interface to receive smart card reader status changes.
 * @since MHP 1.1.3
 */

public interface SmartCardReaderListener {

    /**
     * Called by the terminal when a change in the status of the smart card reader
     * is notified.
     *
     * @param event SmartCardReaderEvent the event that was fired
     */
    public void smartCardReaderEventReceived(SmartCardReaderEvent event);

}

