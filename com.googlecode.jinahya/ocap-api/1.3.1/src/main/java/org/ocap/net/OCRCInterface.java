/*
 * OCRCInterface.java
 *
 * Created on May 21, 2001, 5:35 PM
 */

package org.ocap.net;

import org.dvb.net.rc.RCInterface;


/**
 * This class models a return channel interface for use in receiving and
 * transmitting IP packets over an OCAP-compliant return channel.
 * This class does not model any concept of connection. Hence interfaces
 * represented by this class are permanently connected.
 *
 * The getType() method inherited from org.dvb.net.rc.RCInterface
 * SHALL return TYPE_CATV when called on an instance of this class.
 *
 * @author  Mark S. Millard (Vidiom Systems) (version 1.0)
 * @author Shigeaki Watanabe (Panasonic) (version I10)
 */
public class OCRCInterface extends RCInterface {

    protected OCRCInterface()
    {
    }

    /**
     * Constant to indicate a DOCSIS return channel.
     **/
    public static final int SUBTYPE_CATV_DOCSIS = 1;

    /**
     * Constant to indicate an OOB return channel, either SCTE 55-2 or
     * SCTE 55-1, accessed through the CableCARD interface as specified in
     * CCIF 2.0.
     */
    public static final int SUBTYPE_CATV_OOB = SUBTYPE_CATV_DOCSIS + 1;

    /**
     * Return the type of cable return channel.
     *
     * @return the type of return channel represented by this object encoded as
     * one of the constants (SUBTYPE_CATV_DOCSIS or SUBTYPE_CATV_OOB) defined 
     * in this class.
     **/
    public int getSubType() {
        return 0;
    }
}
