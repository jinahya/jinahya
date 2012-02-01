/*
 * @(#)LocatorImpl.java	1.6 99/10/29
 *
 * Copyright 2000 by Sun Microsystems, Inc.,
 * 901 San Antonio Road, Palo Alto, California, 94303, U.S.A.
 * All rights reserved.
 *
 * This software is the confidential and proprietary information
 * of Sun Microsystems, Inc. ("Confidential Information").  You
 * shall not disclose such Confidential Information and shall use
 * it only in accordance with the terms of the license agreement
 * you entered into with Sun.
 */

package com.sun.tv;

/*
 * This class implements the JavaTV locator interface. Additionally,
 * this class adds methods for extra information regarding the
 * locator.
 */

import java.net.*;
import java.io.IOException;
import java.util.*;
import javax.tv.locator.*;

public class LocatorImpl implements Locator {

	/**
	 * The following are the forms of the various protocols, TBD yet
	 * to be implemented is a way of escaping the : and / characters 
	 * which might be imbedded into some the protocol arguments.
	 *
	 * Protocol				Format
	 * ========				======
	 * ServiceProtocol			service:/<servicename>
	 * AlternateProtocol			alternate:/<servicename>
	 * ServiceDescriptionProtocol		description:/<servicename>
	 * ServiceComponentProtocol		component:/<componentname>service:/<servicename>
	 * ServiceComponentProtocol		component:/<xletname>service:/<servicename>
	 *
	 * ProgramEventProtocol			event:/<eventname>service:/<servicename>
	 * AlternateEventProtocol		alternate:/<eventname>service:/<servicename>
	 * ProgramEventDescriptionProtocol	eventdescription:/<eventname>
	 *	
	 * TransportStreamProtocol		transport:/<transportname>
	 * NetworkProtocol			network:/<networkname>
	 * BouquetProtocol			bouquet:/<bouquetname>
	 *
	 * TransportStreamProtocol		transport:/*	// All transports
	 * NetworkProtocol			network:/*	// All networks
	 * BouquetProtocol			bouquet:/*	// All bouquets
	 * CarouselProtocol			carousel:/*	// All carousel
	 *	
	 */

	public static final String ServiceProtocol = "service:/";
	public static final String ServiceComponentProtocol = "component:/";
	public static final String ServiceDescriptionProtocol = "description:/";
	public static final String ProgramEventProtocol = "event:/";
	public static final String ProgramEventDescriptionProtocol = "eventdescription:/";
	public static final String TransportStreamProtocol = "transport:/";
	public static final String NetworkProtocol = "network:/";
	public static final String BouquetProtocol = "bouquet:/";
	public static final String CarouselProtocol = "carousel:/";
	public static final String AlternateProtocol = "alternate:/";
	public static final String TransportProtocol = "transport:/";

	String externalForm = null;

	private static Hashtable mediaFiles = new Hashtable();
	private static Hashtable transforms = new Hashtable();

	/**
	 *
	 */
	public LocatorImpl(String locatorStr) {
		this.externalForm = locatorStr;

		if (locatorStr == null) {
			;
		} else if (locatorStr.toLowerCase().startsWith(ServiceProtocol)) {
			;
		} else if (locatorStr.toLowerCase().startsWith(ServiceDescriptionProtocol)) {
			;
		} else if (locatorStr.toLowerCase().startsWith(ServiceComponentProtocol)) {
			;
		} else if (locatorStr.toLowerCase().startsWith(ProgramEventProtocol)) {
			;
		} else if (locatorStr.toLowerCase().startsWith(ProgramEventDescriptionProtocol)) {
			;
		} else if (locatorStr.toLowerCase().startsWith(TransportStreamProtocol)) {
			;
		} else if (locatorStr.toLowerCase().startsWith(NetworkProtocol)) {
			;
		} else if (locatorStr.toLowerCase().startsWith(BouquetProtocol)) {
			;
		} else if (locatorStr.toLowerCase().startsWith(CarouselProtocol)) {
			;
		} else if (locatorStr.toLowerCase().startsWith("rtp:/")) {
			;
		} else if (locatorStr.toLowerCase().startsWith("http:/")) {
			;
		} else if (locatorStr.toLowerCase().startsWith("file:/")) {
			;
		} else {
			this.externalForm = null;
		}
	}

	/**
	 * Creates a string-based representation of this Locator.<p>
	 * The result of this method may be used as an input to the
	 * constructor for the JMF MediaLocator.
	 *
	 * @return a string that can be used to construct other
         * forms of locators, including URLs.  If the locator has
         * no external form, an empty string is returned.
	 * 
	 * @see javax.media.MediaLocator#MediaLocator(String)
	 */
 	public String toExternalForm() {
		return this.externalForm;
	}

	public String toString() {
		return this.externalForm;
	}
		

  	/**
  	 * Indicates whether this <code>Locator</code> has a mapping
	 * to multiple networks.
	 *
	 * @return true if multiple transformations exist for this
	 * <code>Locator</code>, false otherwise.
	 */
	public boolean hasMultipleTransformations() {
		return (transforms.get(this.toExternalForm()) != null);
 	}

  /**
   * Compares this <code>Locator</code> with the specified object
   * for equality.  The result is <code>true</code> if and only if
   * the specified object is also a Locator and has an external
   * form equivalent to the external form of this <code>Locator</code>.
   * Two external forms are equivalent (and thus refer to the same
   * resource) if (a) they are identical, i.e. via string comparison,
   * or (b) they both can be transformed to canonical forms that
   * are identical.  The nature of canonicalized external forms is
   * entirely implementation dependent.
   *
   * @param o The object to compare this <code>Locator</code> against.
   *
   * @return <code>true</code> if the specified object is equal to this
   * <code>Locator</code>.
   *
   * @see java.lang.String#equals(Object)
   */
  	public boolean equals(Object o) {
		if (!(o instanceof Locator))
			return false;
		return (this.externalForm.equals(((Locator)o).toExternalForm()) );
	}


  /**
   * Generates a hash code value for this <code>Locator</code>.
   * Two <code>Locator</code> instances for which <code>Locator.equals()</code>
   * is <code>true</code> will have identical hash code values.
   *
   * @return The hash code value for this <code>Locator</code>.
   *
   * @see #equals(Object)
   */
  	public int hashCode() {
		return this.externalForm.hashCode();
	}

  /**
   * Perform a transform on this locator. This implementation does nothing
   * but return the locator this instance in an array of size 1.
   * @return a transformed array of locators.
   */
	public static Locator[] transformLocator(Locator source) {
		Locator locators[] = null;
		Vector list = (Vector)transforms.get(source.toExternalForm());
		if (list == null) {
			locators = new Locator[1];
			locators[0] = source;
		} else {
			locators = new Locator[list.size()];
			for (int i = 0; i < list.size(); i++) {
				locators[i] = (Locator)list.elementAt(i);
			}
		}

		return locators;
   	}

  /**
   * Setup the locator transform tables for a locator.
   */

   public static void setTransforms(Locator alias, Vector list) {
	if (list == null) {
		transforms.remove(alias.toExternalForm());
		return;
	}
	transforms.put(alias.toExternalForm(), list);
   }

  /**
   * Compare two locators for equality. Both locators must be valid
   * locator objects and there external forms must equal.
   * @param locator1 one of two locators to test for equality
   * @param locator2 the other of two locators to test for equality
   * @return a boolean result indicating the equality of the two sepcified
   * locators.
   */
	public static boolean equals(Locator locator1, Locator locator2) {
		if (validLocator(locator1) == false) 
			return false;

		if (validLocator(locator2) == false) 
			return false;

		return (locator1.toExternalForm().equals(locator2.toExternalForm()));
	}

  /**
   * Check for validity of a locator. In particular, tha locators external
   * form must be non-null.
   * @param the locator on which validity is to be checked.
   * @return a boolean result indicating the validity of the locator.
   */
	public static boolean validLocator(Locator locator) {
		LocatorImpl locatorImpl = new LocatorImpl(locator.toExternalForm());
		if (locatorImpl == null)
			return false;

		return (locatorImpl.toExternalForm() != null);
	}

	public static boolean isSameProtocol(Locator locator1, Locator locator2) {
		String str1 = locator1.toExternalForm();
		String str2 = locator2.toExternalForm();
		int indx = str1.indexOf("/");
		return (str2.startsWith(str1.substring(0, indx)));
	}

	public static boolean isService(Locator locator) {
		if (locator == null)
			return false;

		String externalForm = locator.toExternalForm();
		if (externalForm == null) {
			return false;
		}
		return externalForm.startsWith(ServiceProtocol);
	}

        public static boolean isTIService(Locator locator) {
          	return (isService(locator) && locator.hasMultipleTransformations());
   	}

        public static boolean isTDService(Locator locator) {
          	return (isService(locator) && !(locator.hasMultipleTransformations()));
   	}

	public static boolean isServiceComponent(Locator locator) {
		if (locator == null)
			return false;

		String externalForm = locator.toExternalForm();
		if (externalForm == null) {
			return false;
		}
		return externalForm.startsWith(ServiceComponentProtocol);
	}

	public static boolean isServiceDescription(Locator locator) {
		if (locator == null)
			return false;

		String externalForm = locator.toExternalForm();
		if (externalForm == null) {
			return false;
		}
		return externalForm.startsWith(ServiceDescriptionProtocol);
	}

	public static boolean isProgramEvent(Locator locator) {
		if (locator == null)
			return false;

		String externalForm = locator.toExternalForm();
		if (externalForm == null) {
			return false;
		}
		return externalForm.startsWith(ProgramEventProtocol);
	}

	public static boolean isProgramEventDescription(Locator locator) {
		if (locator == null)
			return false;

		String externalForm = locator.toExternalForm();
		if (externalForm == null) {
			return false;
		}
		return externalForm.startsWith(ProgramEventDescriptionProtocol);
	}

	public static boolean isTransportStream(Locator locator) {
		if (locator == null)
			return false;

		String externalForm = locator.toExternalForm();
		if (externalForm == null) {
			return false;
		}
		return externalForm.startsWith(TransportStreamProtocol);
	}

	public static boolean isNetwork(Locator locator) {
		if (locator == null)
			return false;

		String externalForm = locator.toExternalForm();
		if (externalForm == null) 
			return false;

		return externalForm.startsWith(NetworkProtocol);
	}

	public static boolean isBouquet(Locator locator) {
		if (locator == null)
			return false;

		String externalForm = locator.toExternalForm();
		if (externalForm == null) 
			return false;

		return externalForm.startsWith(BouquetProtocol);
	}

	public static boolean isAlternate(Locator locator) {
		if (locator == null)
			return false;

		String externalForm = locator.toExternalForm();
		if (externalForm == null) 
			return false;

		return externalForm.startsWith(AlternateProtocol);
	}

	public static boolean isSelectable(Locator locator) {
		return isService(locator) || isProgramEvent(locator);
	}


	/**
 	 * Indicates whether an locator can possible point to an SI Element,
	 * but does not indicate whether the corresponding si element exists.
	 * Note: Since service and service details, then a service locator
	 * can point to ServiceDetails, which is an SIElement.
 	 */
	public static boolean isSIElement(Locator locator) {
  		return ( isBouquet(locator) 
            		 || isNetwork(locator)
            		 || isTDService(locator) 
                         || isProgramEvent(locator)
      			 || isServiceComponent(locator)
 			 || isTransportStream(locator) );
	}

	public static String getServiceName(Locator locator) {
		if (locator == null)
			return null;

		String externalForm = locator.toExternalForm();
		if (externalForm == null)
			return null;

		if (isService(locator)) {
			return externalForm.substring(ServiceProtocol.length());

		} else if (isServiceDescription(locator)) {
			return externalForm.substring(ServiceDescriptionProtocol.length());

		} else if (isServiceComponent(locator)) {
			int i = externalForm.indexOf(ServiceProtocol);
			return externalForm.substring(i+ServiceProtocol.length());

		} else if (isProgramEvent(locator)) {
			int i = externalForm.indexOf(ServiceProtocol);
			return externalForm.substring(i+ServiceProtocol.length());
		}
		return null;
	}

	public static String getProgramEventName(Locator locator) {
		if (locator == null)
			return null;

		String externalForm = locator.toExternalForm();
		if (externalForm == null)
			return null;

		if (isProgramEvent(locator)) {
			int i = externalForm.indexOf(ServiceProtocol);
			return externalForm.substring(ProgramEventProtocol.length(), i);

		} else if (isProgramEventDescription(locator)) {
			return externalForm.substring(ProgramEventDescriptionProtocol.length());

		}
		return null;
	}

	public static String getServiceComponentName(Locator locator) {
		if (locator == null)
			return null;

		String externalForm = locator.toExternalForm();
		if (externalForm == null)
			return null;

		if (isServiceComponent(locator)) {
			int i = externalForm.indexOf(ServiceProtocol);
			//return externalForm.substring(ServiceComponentProtocol.length()ServiceComponentProtocol.length());
			return externalForm.substring(ServiceComponentProtocol.length(), i);
		}
		return null;
	}

	public static String getTransportStreamID(Locator locator) {
		if (locator == null)
			return null;

		String externalForm = locator.toExternalForm();
		if (externalForm == null)
			return null;

		if (isTransportStream(locator) == false)
			return null;

		return externalForm.substring(TransportStreamProtocol.length());
	}

	public static String getBouquetName(Locator locator) {
		if (locator == null)
			return null;

		String externalForm = locator.toExternalForm();
		if (externalForm == null)
			return null;

		if (isBouquet(locator) == false)
			return null;

		return externalForm.substring(BouquetProtocol.length());
	}

	public static String getNetworkName(Locator locator) {
		if (locator == null)
			return null;

		String externalForm = locator.toExternalForm();
		if (externalForm == null)
			return null;

		if (isNetwork(locator) == false)
			return null;

		return externalForm.substring(NetworkProtocol.length());
	}

	public static Locator transformToService(Locator locator) {
		if (locator == null)
			return null;

		String serviceName = getServiceName(locator);
		if (serviceName != null) {
			return new LocatorImpl(ServiceProtocol + serviceName);
		}
		return null;
	}

	public static Locator transformToProgramEvent(Locator locator) {
		if (locator == null)
			return null;

		String serviceName = getServiceName(locator);
		String programName = getProgramEventName(locator);
		if (serviceName != null && programName != null) {
			return new LocatorImpl(
				ProgramEventProtocol + programName +
				ServiceProtocol + serviceName);
		} else if (programName != null) {
			return new LocatorImpl(
				ProgramEventProtocol + programName);
		}
		return null;
	}

	public static Locator transformToServiceDescription(Locator locator) {
		if (locator == null)
			return null;

		String serviceName = getServiceName(locator);
		if (serviceName != null) {
			return new LocatorImpl(ServiceDescriptionProtocol + serviceName);
		}
		return null;
	}

	public static Locator transformToProgramEventDescription(Locator locator) {
		if (locator == null)
			return null;

		String programName = getProgramEventName(locator);
		if (programName != null) {
			return new LocatorImpl(ProgramEventDescriptionProtocol + programName);
		}
		return null;
	}

	public static Locator transformToTransportStream(Locator locator) {
		if (locator == null)
			return null;

		String name = getNetworkName(locator);
		if (name != null) {
			return new LocatorImpl(TransportStreamProtocol + name);
		}
		return null;
	}

	public static Locator transformToAlternate(Locator locator) {
		if (locator == null)
			return null;

		if (isAlternate(locator)) {
			return locator;
		} else if (isService(locator)) {
			return new LocatorImpl(
				AlternateProtocol + getServiceName(locator));
		} else if(isProgramEvent(locator)) {
			String serviceName = getServiceName(locator);
			String programName = getProgramEventName(locator);

			return new LocatorImpl(
				AlternateProtocol + programName +
				ServiceProtocol + serviceName);
		} else if (isServiceComponent(locator)) {
			return new LocatorImpl(
				ServiceComponentProtocol + getServiceComponentName(locator));
		}
		return null;
	}

	public static Locator createProgramEventLocator(String serviceName, String programName) {
		return new LocatorImpl(
			ProgramEventProtocol + programName +
			ServiceProtocol + serviceName);
	}

	public static void setMediaFile(String locatorStr, String urlStr) {
		mediaFiles.put(locatorStr, urlStr);
	}

	public static String getMediaFile(Locator locator) {
		return (String)mediaFiles.get(locator.toExternalForm());
	}

	public static String getMediaFile(String locatorStr) {
		return (String)mediaFiles.get(locatorStr);
	}
	
  /**
   * Reports the local IP address assigned to the given service
   * component.
   *
   * @param locator The service component for which the local IP
   * address mapping is required.
   *
   * @return The IP address assigned to this service component.
   *
   * @throws InvalidLocatorException If the given locator does not
   * refer to a valid source of IP data, or refers to a source of IP
   * data whose reception is not supported on this system.
   *
   * @throws IOException If a local IP address is not available to
   * be assigned to the source of IP data.
   *
   */
  public static InetAddress getLocalAddress(Locator locator) {

	if (locator == null) 
		return null;

	if (LocatorImpl.isServiceComponent(locator) == false)
		return null;

	URL url = null;
	String urlStr = LocatorImpl.getMediaFile(locator);

	try {
		url = new URL(urlStr);
		if (url == null) 
			return null;

		System.out.println("InterfaceMapImpl ---------------");
		System.out.println("protocol: " + url.getProtocol());
		System.out.println("host: " + url.getHost());
		System.out.println("port: " + url.getPort());
		System.out.println("file: " + url.getFile());
	} catch (Exception e) {
		return null;

	}

	InetAddress address = null;
	if (url.getHost() == null || url.getHost().length() == 0) {
		address = getHostAddress("localhost");
	} else {
		address = getHostAddress(url.getHost());
	}

	return address;
    }

    /**
     * Get the IP address of our host. An empty host field or a DNS failure
     * will result in a null return.
     */
    private static synchronized InetAddress getHostAddress(String host) {
        if (host == null || host.equals("")) {
            return null;
        }
        try {
                return InetAddress.getByName(host);
        } catch (Exception e) {
		;
        }
	return null;
    }
}
