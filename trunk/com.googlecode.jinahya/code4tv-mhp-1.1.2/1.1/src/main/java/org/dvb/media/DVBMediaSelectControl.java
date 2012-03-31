package org.dvb.media;

import javax.tv.locator.InvalidLocatorException;

import javax.tv.service.selection.InvalidServiceComponentException;

import javax.tv.service.selection.InsufficientResourcesException;

import javax.tv.locator.Locator;

import javax.tv.service.navigation.StreamType;

/**
 * DVBMediaSelectControl extends <code>MediaSelectControl</code> allowing
 * the selection of different kinds of content in a running <code>Player</code>.
 * The extension is to allow the selection in a single operation of all the
 * media service components in a service without needing knowledge about which 
 * media service components are present in that service.
 * @see javax.tv.media.MediaSelectControl
 * @since MHP 1.0.2
 */

public interface DVBMediaSelectControl extends javax.tv.media.MediaSelectControl
{
        /**
         * Selects for presentation the media service components from a service.
         * If some content is currently playing, it is replaced in its entirety 
         * by the media service components from the specified service. 
         * This is an asynchronous operation that is completed upon receipt of a 
         * MediaSelectEvent. Note that for most selections that imply a different 
         * time base or otherwise change synchronization relationships, a 
         * RestartingEvent will be posted by the Player. The rules for deciding which
	 * media service components shall be presented are defined in the main body
	 * of the present document.
         * @param l the locator for a service
         * @throws InvalidLocatorException If the locator provided does not reference 
         *         a service.
         * @throws InvalidServiceComponentException If the locator provided does not
         *         reference a service which contains at least one media service component
         * @throws InsufficientResourcesException If the operation cannot be completed 
         *         due to a lack of system resources.
         */
        public void selectServiceMediaComponents(Locator l) throws
                        InvalidLocatorException,InvalidServiceComponentException,
                        InsufficientResourcesException;
            

	/**
	 * Adds a service component (for example, subtitles) to the presentation over-riding
	 * the stream type signalled for the component.
	 * This is an asynchronous operation that is completed on receipt of a MediaSelectEvent. 
	 * Components whose addition would require Player resynchronization are not permitted. 
	 * If the specified service component is already part of the presentation, this method does nothing.
	 *
	 * @since MHP 1.1.2
	 * @param component The locator representing an individual service component to add to the presentation.
 	 * @param type The stream type of the component
	 * @throws InvalidLocatorException If the specified locator does not reference a selectable service component.
	 * @throws InvalidServiceComponentException If the addition of the service component would require resynchronization of the Player, if the service component is not part of the Service to which the MediaSelectControl is restricted, or if the service component must be presented in conjunction with another service component that is not part of the current presentation.
	 * @throws InsufficientResourcesException - If the operation cannot be completed due to a lack of system resources.
	 * @throws java.lang.SecurityException - If the caller does not have MediaSelectPermission(component) permission.
	 */
	public void add(Locator component, StreamType type)
         throws InvalidLocatorException,
                InvalidServiceComponentException,
                InsufficientResourcesException,
                java.lang.SecurityException;

	/**
	 * Selects a new service component for presentation over-riding the stream type signalled for the component.
	 * If some content is currently playing, it is replaced in its entirety by the specified selection. 
	 * This is an asynchronous operation that is completed upon receipt of a MediaSelectEvent. 
	 * Note that for certain selections that imply a different time base or otherwise change synchronization 
	 * relationships, a RestartingEvent will be posted by the Player.
	 *
	 * @since MHP 1.1.2
	 * @param component A locator representing an individual service component to present.
	 * @param type The stream type of the component
	 * @throws InvalidLocatorException If the locator does not reference a selectable service component.
	 * @throws InvalidServiceComponentException If the specified service component is not part of the Service to which the MediaSelectControl is restricted, or if it cannot be presented alone.
	 * @throws InsufficientResourcesException If the operation cannot be completed due to a lack of system resources.
	 * @throws java.lang.SecurityException If the caller does not have MediaSelectPermission(component) permission.
	 */
	public void select(Locator component, StreamType type)
            throws InvalidLocatorException,
                   InvalidServiceComponentException,
                   InsufficientResourcesException,
                   java.lang.SecurityException;

	/**
	 * Selects one or more service components for presentation over-riding the stream type signalled for
	 * the component. If some content is currently playing, it is replaced in its entirety by the specified selection. 
	 * This is an asynchronous operation that is completed on receipt of a MediaSelectEvent. 
	 * Note that for certain selections that imply a different time base or otherwise change synchronization 
	 * relationships, a RestartingEvent will be posted by the Player.
	 * If any of the components are successfully presented then a MediaSelectSucceededEvent is generated 
	 * with the locator array containing only those components that were successfully presented.
	 * A MediaSelectFailedEvent is only generated if none of the components are successfully presented.
	 * 
	 * @since MHP 1.1.2
	 * @param components An array of locators representing a set of individual service components to present together.
 	 * @param types The stream type corresponding to each locator
	 * @throws InvalidLocatorException If a locator provided does not reference a selectable service component.
	 * @throws InvalidServiceComponentException If a specified service component is not part of the Service to which the MediaSelectControl is restricted, if a specified service component must be presented in conjunction with another service component not contained in components, if the specified set of service components cannot be presented as a coherent whole, or if the service components are not all available simultaneously.
	 * @throws InsufficientResourcesException If the operation cannot be completed due to a lack of system resources.
	 * @throws java.lang.SecurityException If the caller does not have MediaSelectPermission(components[i]) permission for any valid i.
         * @throws IllegalArgumentException if the two arrays are not the same size.
	 */
	public void select(Locator[] components, StreamType[] types)
            throws InvalidLocatorException,
                   InvalidServiceComponentException,
                   InsufficientResourcesException,
                   java.lang.SecurityException;


}


