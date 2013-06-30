package org.ocap.hardware;
/**
 * This class represents the copy control information on the analog
 * and digital A/V outputs of the OCAP terminal.
 **/
public class CopyControl
{

    /**
     * Do not use.  This is to prevent a public constructor being generated.
     */
    protected CopyControl() {
    }

    /**
     * Provides an OCAP Application with the ability to query the OpenCable 
     * Host Device for the current value of the CCI bits, which the OpenCable 
     * Host Device is currently using for Copy Protection.  
     *
     * Note (informative) OCAP Applications that have access to and are 
     * processing video content should call this function at a periodic rate
     * of no less than once every minute.
     *
     * @param service indicates the service to which the returned CCI value 
     *       applies. CCI values are passed from a CableCARD to a Host 
     *       associated with a program number. The implementation SHALL use 
     *       the service to identify the program number.
     *
     * @return The CCI values currently in use by the OpenCable Host Device 
     *       for the indicated service.
     */
    public static final int getCCIBits (javax.tv.service.Service service) {
        return 0;
    }
}
