/*
 * PODExtendedChannel.java
 *
 * Created on April 18, 2003, 11:06 AM
 */

package org.ocap.mpeg;

/**
 *
 * @author  shigeaki watanabe
 */

/**
 * <p>
 * This class represents an extended channel that provides access to private
 * section data flows.  The extended channel is defined in the Host-POD Interface
 * Standard (SCTE 28).
 * When this class is specified as the stream parameter of the 
 * org.davic.mpeg.sections.SectionFilterGroup.attach(TransportStream, 
 * ResourceClient, Object) method, the SectionFilterGroup is connected to the 
 * extended channel,
 * i.e., the filters in the SectionFilterGroup filter the private section data
 * via OOB.  The extended channel flow to be opened is specified by PID, when 
 * the org.davic.mpeg.sections.SectionFilter.startFiltering() method is called.
 * </p> <p>
 * The methods defined in the super class (org.davic.mpeg.TransportStream) 
 * shall behave as follows:
 * <ul>
 * <li> The getTransportStreamId() method returns -1.
 * <li> The retrieveService(int serviceId) method returns null.
 * <li> The retrieveServices() method returns null.
 * </ul>
 */
public abstract class PODExtendedChannel
        extends org.davic.mpeg.TransportStream {
            
        /** 
         * OCAP applications SHALL NOT use this method - it is provided for
         * internal use by the OCAP implementation.  The result of calling 
         * this method from an application is undefined, and valid 
         * implementations MAY throw any Error or RuntimeException.
         */
        protected PODExtendedChannel() {
            }
        /**
         * Gets a PODExtendedChannel instance.
         * The implementation MAY return the same instance each time, or
         * it MAY return different (but functionally identical) instances.
         * 
         * @return A PODExtendedChannel instance.
         */
        public static PODExtendedChannel getInstance() {
            return null;
        }
    
}
