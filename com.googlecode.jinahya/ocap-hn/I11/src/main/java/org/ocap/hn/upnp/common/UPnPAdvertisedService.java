package org.ocap.hn.upnp.common;

import org.w3c.dom.Document;


/**
 * This interface represents a UPnP service as it is advertised on a particular
 * network.
 * It provides the data constituting a UPnP service, portions of
 * which depend on the network interface on which it is advertised.
 * Corresponds to the information carried in the UPnP service description
 * document plus service-specific
 * data from the UPnP device description document.
 */
public interface UPnPAdvertisedService extends UPnPService {

    /**
     * Gets the UPnP controlURL of this service. This value is taken
     * from the value of the {@code controlURL} element within the device
     * description.
     *
     * @return The URL used by a control point to invoke actions on
     * this service.
     */
    String getControlURL();

    /**
     * Gets the UPnP device that this service is a part of.
     *
     * @return The device that this service is a part of.
     */
    // UPnPAdvertisedDevice getAdvertisedDevice();

    /**
     * Gets the UPnP eventSubURL of this service. This value is
     * taken from the value of the {@code eventSubURL} element within a
     * device description. If this service does not have eventing,
     * the value returned is the empty string.
     *
     * @return The URL used by a control point to subscribe to
     * evented state variables.
     */
    String getEventSubURL();

    /**
     * Gets the UPnP SCPDURL of this service. This value is taken
     * from the value of the {@code SCPDURL} element within a device
     * description.
     *
     * @return The URL used to retrieve the service description of
     * this service.
     */
    String getSCPDURL();

    /**
     * Gets a UPnP state variable from the UPnP description of this
     * service.  Supported state variable names are provided by a UPnP device
     * in the name element of each stateVariable element in a device
     * service description.
     *
     * @param stateVariableName The name of the state variable to get.
     *
     * @return The state variable corresponding to the
     * <code>stateVariableName</code> parameter.
     *
     * @throws IllegalArgumentException if the <code>stateVariableName</code>
     * does not match a state variable name in this service.
     */
    UPnPAdvertisedStateVariable getAdvertisedStateVariable(String stateVariableName);

    /**
     * Gets all of the UPnP state variables supported by this
     * service.  UPnP state variable information is taken from the
     * {@code stateVariable} elements in the UPnP service description.
     *
     * @return  The UPnP state variables supported by this
     * service. If the service has no state variables, returns a zero-length
     * array.
     */
    UPnPAdvertisedStateVariable[] getAdvertisedStateVariables();

    /**
     * Gets the service description document (SCPD document) in XML.
     * The form of the document is defined by
     * the UPnP Device Architecture specification.
     *
     * @return The service description document.
     */
    Document getXML();

}
