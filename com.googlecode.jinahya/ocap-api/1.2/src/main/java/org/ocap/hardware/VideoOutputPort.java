package org.ocap.hardware;

/**
 * An object of this class represents an analog or digital video output of
 * the OCAP terminal. If the type is the analog video output, it is mapped
 * to a physical pin plug of the video output. If the type is the digital serial
 * output, it is mapped to a physical pin plug of the video output. If the type
 * is the digital bus output, it is mapped to a bus node that has several output
 * ports. For example, if the type is the 1394 bus, the VideoOutputPort instance
 * represents not the 1394 port (physical pin plug) but the 1394 node that has
 * several 1394 ports.
 * <p>
 * An application cannot construct an instance of this class directly.
 * Instead, the Host.getVideoOutputPorts() method is used to obtain a
 * java.util.Enumeration of references to VideoOutputPort instances.
 * The Enumeration.nextElement() method can be used to obtain references to
 * individual VideoOutputPort instances.
 * </p>
 * The video port is a scarce resource, but the resource management framework is
 * not applied. At most only one 1394 connection is available for a single OCAP
 * implementation. If some applications call VideoOutputPort.select1394sink(),
 * only the last call is effective. The other calls are ignored or disconnected
 * without any notification.
 */
public abstract class VideoOutputPort
{

    /**
     * OCAP applications SHALL NOT use this constructor - it is provided
     * for internal use by the OCAP implementation.  The result
     * of calling this method from an application is undefined, and
     * valid implementations MAY throw any Error or RuntimeException.
     */
    protected VideoOutputPort () {};

    /**
     * AV Output Port Type RF channel 3/4
     **/
    public final static int AV_OUTPUT_PORT_TYPE_RF = 0;
    /**
     * AV Output Port Type Baseband (RCA connector)
     **/
    public final static int AV_OUTPUT_PORT_TYPE_BB = 1;
    /**
     * AV Output Port Type S-Video
     **/
    public final static int AV_OUTPUT_PORT_TYPE_SVIDEO = 2;
    /**
     * AV Output Port Type 1394 (Firewire)
     **/
    public final static int AV_OUTPUT_PORT_TYPE_1394 = 3;
    /**
     * AV Output Port Type DVI (Panel Link, HDCP)
     **/
    public final static int AV_OUTPUT_PORT_TYPE_DVI = 4;
    /**
     * AV Output Port Type Component Video
     **/
    public final static int AV_OUTPUT_PORT_TYPE_COMPONENT_VIDEO = 5;
    /**
     * AV Output Port Type HDMI
     */
    public final static int AV_OUTPUT_PORT_TYPE_HDMI = 6;
    /**
     * AV Output Port Type Internal (integrated/internal display)
     */
    public final static int AV_OUTPUT_PORT_TYPE_INTERNAL = 7;
    /**
     * AV Output Port Capability Type DTCP
     **/
    public final static int CAPABILITY_TYPE_DTCP = 0;
    /**
     * AV Output Port Capability Type HDCP
     **/
    public final static int CAPABILITY_TYPE_HDCP = 1;
    /**
     * AV Output Port Capability Type Resolution Restriction for HD Video
     **/
    public final static int CAPABILITY_TYPE_RESOLUTION_RESTRICTION = 2;

    /**
     * Enable the video output port, that is, allow the video output from this port.
     * A stream connection is established and an AV stream is output. The status()
     * method is used to confirm the result of this method call.
     *
     * @throws  SecurityException if the caller has not been granted
     *		MonitorAppPermission("setVideoPort")
     *
     * @throws  java.lang.IllegalStateException if the host couldn’t enable
     *	      the port in cases where the Host is unable to start a signal from
     *	      the port, e.g. in the case where another 1394 port has a connection
     *	      that prevents a new connection.
     **/
    public abstract void enable ();
 
 
    /**
     * Disable the video output port, that is, prevent the video output from this
     * port. The stream connection is disconnected. If the port does not support
     * a disabling function, this method affects nothing. The status() method
     * is used to confirm the result of this method call.
     *
     * Note that the specific port types that support disabling are specified
     * elsewhere, for example, by the <tru2way> Host Device License Agreement [HDLA].
     * Note that FCC may provide rules for port disabling.
     *
     * @throws  SecurityException if the caller has not been granted
     *		MonitorAppPermission("setVideoPort")
     *
     * @throws  java.lang.IllegalStateException if this method is called for a
     *	      VideoOutputPort which does not support disabling, or the host couldn’t
     *	      disable the port in cases where the Host is unable to terminate a
     *	      signal from the port, e.g. in the case where a 1394 port has overlayed
     *	      connections.
     *
     **/
    public abstract void disable ();

    /**
     * This method returns a current status of this video output port.
     *
     * @return enable/disable status of video output port.  If true output port is
     * enabled, otherwise it is disabled.
     *
     **/
    public abstract boolean status ();
 
    /**
     *Query the value related to specified capabilityType.
     *
     *@param capabilityType The capability type to query the value
     *                      CAPABILITY_TYPE_xxx
     *
     *@return   The value related to the specified capabilityType will return as follows:
     *
     *          <ul>
     *          <li>CAPABILITY_TYPE_DTCP-java.lang.Boolean which indicates DTCP
     *          is available (TRUE).
     *          <li>CAPABILITY_TYPE_HDCP-java.lang.Boolean which indicates HDCP
     *          is available (TRUE).
     *          <li>CAPABILITY_TYPE_RESOLUTION_RESTRICTION-java.lang.Integer which
     *          indicates the restricted pixel resolution for HD video on the analog component video port
     *          Returns (-1) for any VideoOutputPort type other than
     *          AV_OUTPUT_PORT_TYPE_COMPONENT_VIDEO. For VideoOutputPort type
     *          AV_OUTPUT_PORT_TYPE_COMPONENT_VIDEO, returns the integer product of the
     *          horizontal resolution and the vertical resolution (h x v) used in the
     *          display of a "Constrained Image" which shall be no more than 520,000.
     *          </ul>
     **/

 
    public abstract java.lang.Object queryCapability (int capabilityType);

    /**
     * Get the list of IEEE1394Node corresponding to all the 1394 nodes that were
     * discovered by the OpenCable Host device. The 1394 node which does not
     * have EUI-64 is ignored.
     *
     * @return	An array of IEEE1394Node. The first IEEE1394Node in the array represents
     *          the 1394 node of the OCAP implementation itself.
     *
     * @throws	java.lang.IllegalStateException if this method is called for the
     *		VideoOutputPort which does not represent AV_OUTPUT_PORT_TYPE_1394.
     *
     * @throws  SecurityException if the caller has not been granted
     *		MonitorAppPermission("setVideoPort")
     **/
    public abstract IEEE1394Node[] getIEEE1394Node ();
 
    /**
     *
     * Select an IEEE1394 sink node which will establish a stream connection to the node
     * of the OCAP implementation and give a parameter to establish a point to point
     * AV connection. This method neither establishes a connection nor outputs a stream.
     * An application must call VideoOutputPort.enable() to establish a connection and
     * output a stream. The stream connection parameters which are not specified by this
     * method are assigned by the OCAP implementation automatically. For example, oPCR
     * is selected by the OCAP implementation. A source of an AV stream is a tuner of
     * the OCAP implementation.
     *
     * @param	eui64  an unsigned big endian 64-bits value of EUI-64 of a sink node.
     * @param	subunitType  type value of a sink AV subunit to be connected.
     *
     * @throws	java.lang.IllegalArgumentException if eui64 is not valid.
     *
     * @throws  java.lang.IllegalStateException if this method is called for the
     *		VideoOutputPort which does not represent AV_OUTPUT_PORT_TYPE_1394.
     *
     * @throws  SecurityException if the caller has not been granted
     *		MonitorAppPermission("setVideoPort")
     *
     */
    public abstract void selectIEEE1394Sink(byte[] eui64, short subunitType);
 
    /**
     * Get the type of this VideoOutputPort.
     *
     * @return  The integer representation of the VideoOutputPort type.
     *          That is, one of the AV_OUTPUT_PORT_TYPE constants.
     */
    public abstract int getType();

    /**	
     *
     *Query the number of horizontal pixels and vertical lines for the Output Type.
     *
     *@return   The Dimension object representing the number of horizontal pixels (width) and
     *                 vertical lines (height) the Output Type is currently displaying.
     *
     */
    public abstract java.awt.Dimension getResolution();

}
