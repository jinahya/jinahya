package org.ocap.hardware;

/**
 * This interface represents the information on a 1394 node. 
 *
 *
 **/

public interface IEEE1394Node
{
    /**
     *
     * Returns the value of EUI-64 of the 1394 node. EUI-64 is defined in 
     * IEEE Std 1394-1995. 
     * 
     *
     * @return  an unsigned big endian 64-bits value of EUI-64 of the 1394 node. 
     *
     * @throws  SecurityException if the caller has not been granted 
     *          MonitorAppPermission("setVideoPort"). 
     *
     */
    public byte[] getEUI64() ;


    /**
     *
     * Returns the value of MODEL NAME TEXTUAL DESCRIPTOR of the 1394 node. 
     * MODEL NAME TEXTUAL DESCRIPTOR is defined in EIA-775-A. 
     * 
     * @return  the value of MODEL NAME TEXTUAL DESCRIPTOR of the 1394 node. 
     *          If the 1394 node does not have the MODEL NAME TEXTUAL 
     *          DESCRIPTOR, null is returned.
     *
     * @throws  SecurityException if the caller has not been granted 
     *          MonitorAppPermission("setVideoPort"). 
     *
     */
    public java.lang.String getModelName()  ;


    /**
     *
     * Returns the value of VENDOR NAME TEXTUAL DESCRIPTOR of the 1394 node. 
     * VENDOR NAME TEXTUAL DESCRIPTOR is defined in EIA-775-A. 
     *
     * @return  the value of VENDOR NAME TEXTUAL DESCRIPTOR of the 1394 node 
     *          If the 1394 node does not have the VENDOR NAME TEXTUAL 
     *          DESCRIPTOR, null is returned.
     *
     * @throws  SecurityException if the caller has not been granted 
     *          MonitorAppPermission("setVideoPort"). 
     *
     */
    public java.lang.String getVendorName();


    /**
     *
     * Returns the list of subunitTypes supported by the 1394 node. 
     *
     * @return  the list of subunitTypes supported by the 1394 node. 
     *          The subunit type is defined in EIA-775-A. 
     *
     * @throws  SecurityException if the caller has not been granted 
     *          MonitorAppPermission("setVideoPort")
     *
     */
    public short[] getSubunitType()  ;
}
