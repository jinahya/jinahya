package org.dvb.net.rc;

/**
 * This class models a return channel network interface for use in receiving
 * and transmitting IP packets over a logical return channel. This can include
 * real analog modems, cable return channel and all the other options allowed
 * by the relevant DVB specification. This class does not model any concept of
 * connection. Hence interfaces represented by this class and not by a sub-class 
 * of it are permanently connected.
 */

public class RCInterface {
	/**
	 * Constant to indicate a PSTN return channel.
	 */
	public static final int TYPE_PSTN=1;

	/**
	 * Constant to indicate an ISDN return channel.
	 */
	public static final int TYPE_ISDN=2;

	/**
	 * Constant to indicate a DECT return channel.
	 */
	public static final int TYPE_DECT=3;

	/**
	 * Constant to indicate a CATV return channel.
	 */
	public static final int TYPE_CATV=4;

	/**
	 * Constant to indicate a LMDS return channel.
	 */
	public static final int TYPE_LMDS=5;

	/**
	 * Constant to indicate a MATV return channel.
	 */
	public static final int TYPE_MATV=6;

	/**
	 * Constant to indicate a DVB-RCS return channel.
	 */
	public static final int TYPE_RCS=7;
	
	/**
	 * Constant to indicate an unknown return channel technology. 
	 * There is an intermediate physical interface between the MHP terminal 
	 * and the return channel device. This return value gives no information 
	 * about whether the return channel is connection oriented or connectionless.
	 */
	public static final int TYPE_UNKNOWN=8;

	/**
	 * Constant to indicate all other return channel technologies not 
	 * having a suitable defined constant in this class.<p>
	 * NOTE: DVB does not intend to add future constants to this list for future 
	 * return channel technologies. These should be represented as TYPE_OTHER.
	 */
	public static final int TYPE_OTHER=9;

	/**
	 * Return the type of return channel represented by this object.
         * Note, applications wishing to discover whether a return channel interface is 
         * connection oriented or not are recommended to test whether an object is an 
         * instance of <code>ConnectionRCInterface</code> or not. A non-connection oriented 
         * interface really means a permanently connected return channel.
	 *
	 * @return the type of return channel represented by this object encoded 
	 * as one of the constants defined in this class
	 */

	public int getType()
	{
		return 0;
	}

	/**
	 * Constructor for instances of this class. This constructor is provided for
         * the use of implementations and specifications which extend the present document.
         * Applications shall not define sub-classes of this class. Implementations are not
         * required to behave correctly if any such application defined sub-classes are used.
	 */
	protected RCInterface()
	{
	}
	/**
	 * Return the maximum data rate of the connection over the immediate access 
	 * network to which this network interface is connected. For asymmetric
	 * connections, the data rate coming into the MHP terminal shall be returned.
	 * For connection oriented interfaces which are not currently connected,
	 * the value returned shall be that of the last connection established where
	 * that information is available. Where that information is not available,
	 * (e.g. where no connection has been established since an MHP terminal was
 	 * power cycled), -1 shall be returned.
	 * 
	 * @return a data rate in KBaud or -1 where this is not available
	 * @since MHP 1.0.1
	 */
	public int getDataRate() 
	{
		return 0;	
	}
}


