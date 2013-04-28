package org.ocap.hn.upnp.common;


/**
 * This interface represents a UPnP state variable as it is described on a
 * particular network.
 * Corresponds to the {@code stateVariable} entry in the UPnP service
 * description {@code serviceStateTable} element.
 */
public interface UPnPAdvertisedStateVariable extends UPnPStateVariable {

    /**
     * Gets the UPnP service that this state variable is a member of.
     *
     * @return The service that this state variable is part of.
     */
    // public UPnPAdvertisedService getAdvertisedService();
}
