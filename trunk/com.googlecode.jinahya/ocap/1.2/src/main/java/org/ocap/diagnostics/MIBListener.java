package org.ocap.diagnostics;

/**
 * This interface represents a listener that can be registered with the
 * <code>MIBManager</code> in order to listen for requests to MIB object
 * encodings.
 */

public interface MIBListener extends java.util.EventListener
{
    /**
     * Notifies a listener when a MIB object it registered control over
     * has been requested.
     * 
     * @param request The request object containing the request type and
     *      OID.
     * 
     * @return A response with the requested MIB encoding or an error
     *      status.
     */
   public SNMPResponse notifySNMPRequest(SNMPRequest request);
}
