package org.dvb.smartcard;

import java.util.EventObject;

/**
 * Represents an event generated by a change in the status of a smart card reader.
 *
 * All available constants are defined within this class.
 * @since MHP 1.1.3
 */
public class SmartCardReaderEvent extends EventObject {

    /**
     * A smart card is inserted into the reader and an ATR is correctly retrieved.
     * This means the insertion contact is active and the reader is also able to communicate
     * with the smart card.
     */
    public static int SMART_CARD_IN = 0;
    /**
     * Nothing is inserted into the reader, meaning the insertion contact is disabled.
     */
    public static int SMART_CARD_OUT = 1;
    /**
     * Smart card is inserted into the reader but no ATR is
     * retrieved because no electrical communication is established with the smart card.
     */
    public static int SMART_CARD_MUTED = 2;
    /**
     * Smart card is inserted into the reader, there is electrical communication with the smart card
     * but no ATR is retrieved.
     */
    public static int SMART_CARD_ERROR = 3;

    /**
     * Constructor for a smart card reader event notifying the slot has identified a change in its status.
     *
     * @param source Object the <code>SmartCardReader</code> who is generating the event
     * @param type int the event type
     */
    public SmartCardReaderEvent(java.lang.Object source, int type) {
      super(source);
    }

    /**
     * Retrieves the type of <code>SmartCardReaderEvent</code> that has been fired. It can be either <code>SMART_CARD_IN</code>,
     * <code>SMART_CARD_OUT</code>, <code>SMART_CARD_MUTED</code> or <code>SMART_CARD_ERROR</code>.
     * @return int type of event
     */
    public int getType() {
      return 0;
    }


}

