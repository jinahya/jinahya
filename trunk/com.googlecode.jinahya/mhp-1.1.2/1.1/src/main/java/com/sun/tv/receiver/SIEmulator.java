/*
 * @(#)SIEmulator.java	1.14 00/01/13
 *
 * Copyright 1998-2000 by Sun Microsystems, Inc.,
 * All rights reserved.
 * 
 * This software is the confidential and proprietary information
 * of Sun Microsystems, Inc. ("Confidential Information").  You
 * shall not disclose such Confidential Information and shall use
 * it only in accordance with the terms of the license agreement
 * you entered into with Sun.
 */

package com.sun.tv.receiver;

import com.sun.tv.*;
import com.sun.tv.si.*;
import com.sun.tv.media.protocol.service.*;

import javax.tv.xlet.*;
import javax.tv.locator.*;
import javax.tv.service.*;
import javax.tv.service.guide.*;
import javax.tv.service.navigation.*;
import javax.tv.service.selection.*;
import javax.tv.service.transport.*;
import java.awt.Frame;
import java.util.*;

public class SIEmulator implements Runnable {

	private static Vector recvdQueue = new Vector();
	private static Thread emulatorThread = null;
	private static SIEmulator emulator = null;
	private static ReceiverListener listener = null;
	private static Hashtable services = new Hashtable();
	private static Hashtable programs = new Hashtable();
	private static SIManagerImpl siManager = null;
	private static SampleDataInterface dataInterface = null;
	
	public SIEmulator() {
		if (emulator == null) {
			emulator = this;

			siManager = (SIManagerImpl)SIManager.createInstance();
			emulator.listener = (ReceiverListener)siManager;
			emulatorThread = new Thread(this, "Emulator Thread");
			emulatorThread.start();

			com.sun.tv.media.protocol.service.PushSourceStream2Impl.registerServiceProtocol();
			com.sun.tv.media.protocol.component.PushSourceStream2Impl.registerComponentProtocol();

			runData(null);
		}
	}

	public static SIEmulator getInstance() {
		return emulator;
	}

	public boolean verify() {
		return dataInterface.verify();
	}

	public void runData(String args[]) {
		try {
			Object obj = Class.forName(Settings.SampleClass).newInstance();
			System.out.println("Running Data from Class = " + Settings.SampleClass);
			if (obj instanceof SampleDataInterface) {
				SampleDataInterface sd = (SampleDataInterface)obj;
				this.dataInterface = sd;
				sd.play(this, args);
				sd.finish();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

//	public static Frame getRootFrame() {
//		return DisplayManager.createInstance().getRootFrame();
//	}


	public static void putService(
				long playTimeRel,
				String name,
				boolean hasMultiples, 
				String sType,
				String siType,
				int serviceNumber,
				int minorNumber,
				String selectionFailedReason) {

		long playTime = System.currentTimeMillis() + playTimeRel * 1000;

		ServiceImpl service = new ServiceImpl(
				name,
				hasMultiples,
				toServiceType(sType),
				toServiceInformationType(siType),
				serviceNumber,
				minorNumber,
				new Date(playTime));

		service.setSelectionFailedReason(toSelectionFailedReason(selectionFailedReason));

		services.put(name, service);

		put(playTime, service);
	}

	public static void putServiceDetails(
				long playTimeRel,
				String name,
				String providerName,
				DeliverySystemType deliveryType,
				String longname,
				String caSystemIDs) {

		long playTime = System.currentTimeMillis() + playTimeRel * 1000;

		ServiceDetailsImpl serviceDetails = new ServiceDetailsImpl(
				(ServiceImpl)services.get(name),
				providerName,
				deliveryType,
				longname,
				toInts(caSystemIDs),
				new Date(playTime));

		put(playTime, serviceDetails);
	}

	public static void putProgramSchedule( 
				long playTimeRel,
				String servicename) {
		// This method is unneccessary: program schedule is automatically
		// part of service details.

	}

	public static void putProgramEvent(
				long playTimeRel,
				String programname,
				String servicename,
				long startTime,
				long duration,
				String siType,
				Vector dimensionNames,
				Vector ratingLongNames) {

		long playTime = System.currentTimeMillis() + playTimeRel * 1000;

//		Date startDate = new Date();
//		startDate.setHours((int)((startTime - (startTime % 100)) / 100));
//		startDate.setMinutes((int)(startTime % 100));
//		startDate.setSeconds(0);
		int h = (int)((startTime - (startTime % 100)) / 100);
		int m = (int)(startTime % 100);
		Date startDate = new Date( new Date().getTime()+ h * 60 * 60 * 1000 + m * 60 * 1000  );
		

		Date endDate = new Date(startDate.getTime() +
			(int)((duration - (duration % 100)) / 100) * 60 * 60 * 1000 +
			(int)(duration % 100) * 60 * 1000);

		ContentRatingAdvisoryImpl advisory = new ContentRatingAdvisoryImpl(
				dimensionNames, ratingLongNames);

		ProgramEventImpl program = new ProgramEventImpl(
				programname,
				servicename,
				startDate,
				endDate,
				toServiceInformationType(siType),
				advisory,
				new Date(playTime));

		programs.put(programname, program);

		put(playTime, program);
	}

	public static void putProgramEventAbs(
				long playTimeRel,
				String programname,
				String servicename,
				long startTime,
				long duration, // Minutes
				String siType,
				Vector dimensionNames,
				Vector ratingLongNames) {

		long playTime = System.currentTimeMillis() + playTimeRel * 1000;

		Date startDate = new Date(startTime);
		Date endDate = new Date(startTime + duration * 60 * 1000);
		ContentRatingAdvisoryImpl advisory = new ContentRatingAdvisoryImpl(
				dimensionNames, ratingLongNames);

		ProgramEventImpl program = new ProgramEventImpl(
				programname,
				servicename,
				startDate,
				endDate,
				toServiceInformationType(siType),
				advisory,
				new Date(playTime));

		programs.put(programname, program);

		put(playTime, program);
	}

	public static void putProgramEventDescription(
				long playTimeRel,
				String programname,
				String description) {

		long playTime = System.currentTimeMillis() + playTimeRel * 1000;

		ProgramEventDescriptionImpl program = new ProgramEventDescriptionImpl(
				(ProgramEvent)programs.get(programname),
				programname,
				description,
				new Date(playTime));

		put(playTime, program);
	}

	public static void putServiceDescription(
				long playTimeRel,
				String name,
				String description) {

		long playTime = System.currentTimeMillis() + playTimeRel * 1000;

		ServiceDescriptionImpl service = new ServiceDescriptionImpl(
				(ServiceImpl)services.get(name),
				name,
				description,
				new Date(playTime));

		put(playTime, service);
	}

	public static void putServiceComponent(
				long playTimeRel,
				String name,
				String language,
				StreamType streamType,
				String serviceName,
				String programName,
				String siType,
				boolean autorun,
				String selectionFailedReason) {

		long playTime = System.currentTimeMillis() + playTimeRel * 1000;

		ProgramEvent program = null;
		if (programName != null) {
			program = (ProgramEvent)programs.get(programName);
		}

		ServiceComponentImpl component = new ServiceComponentImpl(
				name,
				language,
				streamType,
				(ServiceImpl)services.get(serviceName),
				program,
				toServiceInformationType(siType),
				autorun,
				new Date(playTime));

		component.setSelectionFailedReason(toSelectionFailedReason(selectionFailedReason));

		put(playTime, component);
	}

	public static void putBouquet(
				long playTimeRel,
				String name,
				int bouquetID,
				String siType) {

		long playTime = System.currentTimeMillis() + playTimeRel * 1000;

		BouquetImpl bouquet = new BouquetImpl(
				name, 
				bouquetID,
				toServiceInformationType(siType),
				new Date(playTime));

		put(playTime, bouquet);
	}

	public static void putNetwork(
				long playTimeRel,
				String name,
				int networkID,
				String siType) {

		long playTime = System.currentTimeMillis() + playTimeRel * 1000;

		NetworkImpl network = new NetworkImpl(
				name,
				networkID,
				toServiceInformationType(siType),
				new Date(playTime));

		put(playTime, network);
	}

	public static void putTransportStream(
				long playTimeRel,
				String description,
				int transportStreamID,
				String siType,
                                int networkID) {

		long playTime = System.currentTimeMillis() + playTimeRel * 1000;

		TransportStreamImpl transportStream = new TransportStreamImpl(
				description,
				transportStreamID,
				toServiceInformationType(siType),
				new Date(playTime), 
                                networkID);

		put(playTime, transportStream);
	}

	public static void putServiceXlet(
			String xletName,
			String xletPath,
			String argStr) {

		XletManager xletManager = XletManager.createInstance();
		DisplayManager dispManager = DisplayManager.createInstance();

		String args[] = toStrings(argStr, ",");

		AppSignalEvent ase = new AppSignalEvent(dispManager.getRootFrame(),
			AppSignalEvent.STORE, null, xletPath, xletName, null, args);

		xletManager.signalReceived(ase);
	}

	public static boolean putXlet(
			long playTimeRel,
			String xletName,
			String xletPath,
			String args[]) {
		return SIEmulator.putResidentXlet(playTimeRel, xletName, xletPath, args);
	}

	public static boolean putResidentXlet(
			long playTimeRel,
			String xletName,
			String xletPath,
			String args[]) {

		long playTime = System.currentTimeMillis() + playTimeRel * 1000;

		DisplayManager dispManager = DisplayManager.createInstance();

		AppSignalEvent ase = new AppSignalEvent(dispManager.getRootFrame(),
			AppSignalEvent.AUTOSTART, null, xletPath, xletName, null, args);

		put(playTime, ase);

		return (ase != null);
	}

	public static void RemoveSIElement(long playTimeRel, Locator locator) {
		if (locator == null)
			return;

		CacheManager siCache = CacheManager.getSICache();
		if (siCache == null)
			return;

		SIElement element = (SIElement)siCache.get(locator);
		if (element == null)
			return;

		SIChangeEventImpl event = new SIChangeEventImpl(
			emulator, SIChangeType.REMOVE, element);
		if (event == null)
			return;

		listener.notifyChange(event);
	}

	public static void RemoveSIDatabase(long playTimeRel, boolean genEvents) {

		SIChangeEventImpl event = null;

		CacheManager siCache = CacheManager.getSICache();
		if (siCache == null)
			return;

		Enumeration list = siCache.elements();
		while (list.hasMoreElements()) {
			SIElement element = (SIElement)list.nextElement();
			if (element == null) {
				continue;
			}
			event = new SIChangeEventImpl(emulator, SIChangeType.REMOVE, element);
			listener.notifyChange(event);
		}
	}

	public static void removeService(long playTimeRel, String serviceName, String reason) {
		if (serviceName == null)
			return;

		removeServiceTree(playTimeRel, serviceName);

		CacheManager serviceCache = CacheManager.getServiceCache();
		if (serviceCache == null)
			return;

		Locator locator = null;

		try {

			LocatorFactory factory = LocatorFactory.getInstance();

			locator = factory.createLocator(
				LocatorImpl.ServiceProtocol + serviceName);

		} catch (Exception e) {
			System.out.println("removeService exception: " + e);
			return;
		}

		ServiceImpl service = (ServiceImpl)serviceCache.get(locator);

		service.setPresentationTerminatedReason(toPresentationTerminatedReason(reason));

		SIChangeEventImpl event = new SIChangeEventImpl(
			emulator, SIChangeType.REMOVE, service);

		listener.notifyChange(event);
	}

	public static void removeServiceTree(long playTimeRel, String serviceName) {
		if (serviceName == null)
			return;

		SIChangeEventImpl event = null;

		CacheManager siCache = CacheManager.getSICache();
		if (siCache == null)
			return;

		Enumeration list = siCache.elements();
		while (list.hasMoreElements()) {
			SIElement element = (SIElement)list.nextElement();
			SIElement toDelete = null;
			if (element instanceof ProgramEvent) {
				ProgramEvent pe = (ProgramEvent)element;
				if (pe != null) {
					Service service = pe.getService();
					if (service != null) {
						if (serviceName.equals(service.getName())) {
							toDelete = element;
						}
					} 
				}
			} else if (element instanceof ServiceComponent) {
				ServiceComponent sc = (ServiceComponent)element;
				if (sc != null) {
					Service service = sc.getService();
					if (service != null) {
						if (serviceName.equals(service.getName())) {
							toDelete = element;
						}
					} 
				}
			} else {
				element = null;
			}

			if (toDelete != null) {
				event = new SIChangeEventImpl(emulator, SIChangeType.REMOVE, toDelete);
				listener.notifyChange(event);
			}
		}
	}

	public static void removeProgramEvent(
		long playTimeRel, String serviceName, String programName) {

		if (serviceName == null || programName == null)
			return;

		SIChangeEventImpl event = null;

		CacheManager siCache = CacheManager.getSICache();
		if (siCache == null)
			return;

		Enumeration list = siCache.elements();
		while (list.hasMoreElements()) {
			SIElement element = (SIElement)list.nextElement();
			if (element == null || !(element instanceof ProgramEvent))
				continue;

			ProgramEvent pe = (ProgramEvent)element;
			if (programName.equals(pe.getName()) == false)
				continue;

			Service service = pe.getService();
			if (service == null || serviceName.equals(service.getName()) == false)
				continue;

			event = new SIChangeEventImpl(emulator, SIChangeType.REMOVE, element);
			listener.notifyChange(event);
		}
	}

	public static void removeBouquet(long playTimeRel, int bouquetID) {
		SIChangeEventImpl event = null;

		CacheManager siCache = CacheManager.getSICache();
		if (siCache == null)
			return;

		Enumeration list = siCache.elements();
		while (list.hasMoreElements()) {
			SIElement element = (SIElement)list.nextElement();
			if (element == null || !(element instanceof Bouquet))
				continue;

			Bouquet bouquet = (Bouquet)element;
			if (bouquetID != bouquet.getBouquetID())
				continue;

			event = new SIChangeEventImpl(emulator, SIChangeType.REMOVE, element);
			listener.notifyChange(event);
		}
	}

	public static void removeNetwork(long playTimeRel, int networkID) {
		SIChangeEventImpl event = null;

		CacheManager siCache = CacheManager.getSICache();
		if (siCache == null)
			return;

		Enumeration list = siCache.elements();
		while (list.hasMoreElements()) {
			SIElement element = (SIElement)list.nextElement();
			if (element == null || !(element instanceof Network))
				continue;

			Network network = (Network)element;
			if (networkID != network.getNetworkID())
				continue;

			event = new SIChangeEventImpl(emulator, SIChangeType.REMOVE, element);
			listener.notifyChange(event);
		}
	}

	public static void removeTransportStream(long playTimeRel, int transportStreamID) {
		SIChangeEventImpl event = null;

		CacheManager siCache = CacheManager.getSICache();
		if (siCache == null)
			return;

		Enumeration list = siCache.elements();
		while (list.hasMoreElements()) {
			SIElement element = (SIElement)list.nextElement();
			if (element == null || !(element instanceof TransportStream))
				continue;

			TransportStream transportStream = (TransportStream)element;
			if (transportStreamID != transportStream.getTransportStreamID())
				continue;

			event = new SIChangeEventImpl(emulator, SIChangeType.REMOVE, element);
			listener.notifyChange(event);
		}
	}

	public static void removeServiceComponent(long playTimeRel, String componentName) {
		if (componentName == null)
			return;

		SIChangeEventImpl event = null;

		CacheManager siCache = CacheManager.getSICache();
		if (siCache == null)
			return;

		Enumeration list = siCache.elements();
		while (list.hasMoreElements()) {
			SIElement element = (SIElement)list.nextElement();
			if (element == null || !(element instanceof ServiceComponent))
				continue;

			ServiceComponent component = (ServiceComponent)element;
			if (componentName.equals(component.getName()) == false)
				continue;

			event = new SIChangeEventImpl(emulator, SIChangeType.REMOVE, element);
			listener.notifyChange(event);
		}
	}

	public static void removeServiceDetails(long playTimeRel, String serviceName) {
		if (serviceName == null)
			return;

		SIChangeEventImpl event = null;

		CacheManager siCache = CacheManager.getSICache();
		if (siCache == null)
			return;

		Enumeration list = siCache.elements();
		while (list.hasMoreElements()) {
			SIElement element = (SIElement)list.nextElement();
			if (!(element instanceof ServiceDetails))
				continue;

			ServiceDetails details = (ServiceDetails)element;
			if (serviceName.equals(details.getService().getName()) == false)
				continue;

			event = new SIChangeEventImpl(emulator, SIChangeType.REMOVE, element);

			listener.notifyChange(event);
		}
	}

	public static void removeXlet(long playTimeRel, String xletName) {
		if (xletName == null)
			return;

		SIChangeEventImpl event = null;

		CacheManager siCache = CacheManager.getSICache();
		if (siCache == null)
			return;

		Enumeration list = siCache.elements();
		while (list.hasMoreElements()) {
			SIElement element = (SIElement)list.nextElement();
			if (!(element instanceof AppSignalEvent))
				continue;

			AppSignalEvent ase = (AppSignalEvent)element;
			if (xletName.equals(ase.getClassName()) == false)
				continue;

			event = new SIChangeEventImpl(emulator, SIChangeType.REMOVE, element);
			listener.notifyChange(event);
		}
	}

	public static DeliverySystemType toDeliverySystemType(String dstype) {
		if (dstype == null) {
			return DeliverySystemType.UNKNOWN;
		} else if (dstype.equalsIgnoreCase(DeliverySystemType.SATELLITE.toString())) {
			return DeliverySystemType.SATELLITE;
		} else if (dstype.equalsIgnoreCase(DeliverySystemType.CABLE.toString())) {
			return DeliverySystemType.CABLE;
		} else if (dstype.equalsIgnoreCase(DeliverySystemType.TERRESTRIAL.toString())) {
			return DeliverySystemType.TERRESTRIAL;
		}
		return DeliverySystemType.UNKNOWN;
	}

	public static StreamType toStreamType(String stype) {
		StreamType stypes[] = new StreamType[6];
		stypes[0] = StreamType.VIDEO;
    		stypes[1] = StreamType.AUDIO;
    		stypes[2] = StreamType.SUBTITLES;
    		stypes[3] = StreamType.DATA;
    		stypes[4] = StreamType.SECTIONS;
    		stypes[5] = StreamType.UNKNOWN;

		for (int i = 0; i < stypes.length; i++) {
			if (stype.equalsIgnoreCase(stypes[i].toString())) {
				return stypes[i];
			}
		}
		return StreamType.UNKNOWN;
	}

	private static ServiceType toServiceType(String stype) {
		if (stype == null) {
			return ServiceType.UNKNOWN;
		} else if (stype.equalsIgnoreCase(ServiceType.DIGITAL_TV.toString())) {
			return ServiceType.DIGITAL_TV;
		} else if (stype.equalsIgnoreCase(ServiceType.DIGITAL_RADIO.toString())) {
			return ServiceType.DIGITAL_RADIO;
		} else if (stype.equalsIgnoreCase(ServiceType.NVOD_REFERENCE.toString())) {
			return ServiceType.NVOD_REFERENCE;
		} else if (stype.equalsIgnoreCase(ServiceType.NVOD_TIME_SHIFTED.toString())) {
			return ServiceType.NVOD_TIME_SHIFTED;
		} else if (stype.equalsIgnoreCase(ServiceType.ANALOG_TV.toString())) {
			return ServiceType.ANALOG_TV;
		} else if (stype.equalsIgnoreCase(ServiceType.ANALOG_RADIO.toString())) {
			return ServiceType.ANALOG_RADIO;
		} else if (stype.equalsIgnoreCase(ServiceType.DATA_BROADCAST.toString())) {
			return ServiceType.DATA_BROADCAST;
		} else if (stype.equalsIgnoreCase(ServiceType.DATA_APPLICATION.toString())) {
			return ServiceType.DATA_APPLICATION;
		}
		return ServiceType.UNKNOWN;
	}

	private static ServiceInformationType toServiceInformationType(String sitype) {

		if (sitype == null) {
			return ServiceInformationType.UNKNOWN;
		} else if (sitype.equalsIgnoreCase(ServiceInformationType.ATSC_PSIP.toString())) {
			return ServiceInformationType.ATSC_PSIP;
		} else if (sitype.equalsIgnoreCase(ServiceInformationType.DVB_SI.toString())) {
			return ServiceInformationType.DVB_SI;
		} else if (sitype.equalsIgnoreCase(ServiceInformationType.SCTE_SI.toString())) {
			return ServiceInformationType.SCTE_SI;
		}
		return ServiceInformationType.UNKNOWN;
	}

	private static int toPresentationTerminatedReason(String reason) {
		if (reason == null) {
			return 0;
		} else if (reason.equalsIgnoreCase("service_vanished")) {
			return PresentationTerminatedEvent.SERVICE_VANISHED;
		} else if (reason.equalsIgnoreCase("tuned_away")) {
			return PresentationTerminatedEvent.TUNED_AWAY;
		} else if (reason.equalsIgnoreCase("resources_removed")) {
			return PresentationTerminatedEvent.RESOURCES_REMOVED;
		} else if (reason.equalsIgnoreCase("access_withdrawn")) {
			return PresentationTerminatedEvent.ACCESS_WITHDRAWN;
		}
		return 0;
	}

	private static int toSelectionFailedReason(String reason) {
		if (reason == null) {
			return 0;
		} else if (reason.equalsIgnoreCase("interrupted")) {
			return SelectionFailedEvent.INTERRUPTED;
		} else if (reason.equalsIgnoreCase("ca_refusal")) {
			return SelectionFailedEvent.CA_REFUSAL;
		} else if (reason.equalsIgnoreCase("content_not_found")) {
			return SelectionFailedEvent.CONTENT_NOT_FOUND;
		} else if (reason.equalsIgnoreCase("missing_handler")) {
			return SelectionFailedEvent.MISSING_HANDLER;
		} else if (reason.equalsIgnoreCase("tuning_failure")) {
			return SelectionFailedEvent.TUNING_FAILURE;
		} else if (reason.equalsIgnoreCase("insufficient_resources")) {
			return SelectionFailedEvent.INSUFFICIENT_RESOURCES;
		}
		return 0;
	}

	private static int toInt(String str) {
		if (str == null || str.length() == 0) {
			return 0;
		}
	
		try {
			return Integer.parseInt(str);
		} catch (Exception e) {
			;
	        }
		return 0;
	}

	/**
	 * The <em>toVector</em> method tokenizes a string into a vector
	 * based on the list of delimiters provided.
	 * @param str the string to be tokenized
	 * @param delims the list of charater delimiters to be used in
	 * the tokenizing processing.
	 * @param keep a list of delimiters to keep in the tokenized output
	 * Vector.
	 */
	private static int[] toInts(String str) {
		if (str == null)
			return null;

		int length = str.length();
		if (length == 0) 
			return null;

		int count = 1;	
		for (int i = 0; i < length; i++) { 
			if (str.charAt(i) == ',') {
				count++;
			}
		}

		int vals[] = new int[count];

		count = 0;
		int first = 0;
		for (int i = 0; i < length; i++) { 
			if (str.charAt(i) == ',') {
				if (first < i) {
					vals[count++] = toInt(str.substring(first, i));
				}
				first = i + 1;
			} 
		}

		if (first < length) {
			vals[count++] = toInt(str.substring(first).trim());
		}
		return vals;
	}

	/**
	 * The <em>toVector</em> method tokenizes a string into a vector
	 * based on the list of delimiters provided.
	 * @param str the string to be tokenized
	 * @param delims the list of charater delimiters to be used in
	 * the tokenizing processing.
	 * @param keep a list of delimiters to keep in the tokenized output
	 * Vector.
	 */
	public static String[] toStrings(String str, String delims) {
		if (str == null)
			return null;

		int length = str.length();
		if (length == 0) 
			return null;

		int count = 1;	
		for (int i = 0; i < length; i++) { 
			if (delims.indexOf(str.charAt(i)) != -1) {
				count++;
			}
		}

		String strs[] = new String[count];

		count = 0;
		int first = 0;
		for (int i = 0; i < length; i++) { 
			if (delims.indexOf(str.charAt(i)) != -1) {
				if (first < i) {
					strs[count++] = str.substring(first, i);
				}
				first = i + 1;
			} 
		}

		if (first < length) {
			strs[count++] = str.substring(first);
		}
		return strs;
	}

	public static void put(long time, Object obj) {
		if (System.currentTimeMillis() >= time) {
			Process(obj);
			return;
		}

		synchronized (recvdQueue) {
			TimedObject tobj = new TimedObject(time, obj);
			recvdQueue.insertElementAt(tobj, 0);
		}
	}

	public void isCaughtUp() {
		try {
			Thread.sleep(5000); // 5 Secs.
		} catch (Exception e) {
				;
		}

		synchronized (recvdQueue) {
			;
		}
	}

	private static void Process(Object object) {
		XletManager xletManager = XletManager.createInstance();

		try {
			if (object == null) { 
				; // TBD Error condition

			} else if (object instanceof SIElement) {
				SIElement element = (SIElement)object;
				SIChangeType ct = SIChangeType.ADD;

				if (siManager.SIElementExists(element) == true) {
					ct = SIChangeType.MODIFY;
				}

				SIChangeEventImpl event = new SIChangeEventImpl(
					emulator, ct, element);

				listener.notifyChange(event);

			} else if (object instanceof AppSignalEvent) {
				AppSignalEvent ase = (AppSignalEvent)object;
				xletManager.signalReceived(ase);

			} else {
System.out.println("***Error: can't ProcessRecvdObjects = " + object);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void ProcessRecvdObjects(long sysdate) {
		synchronized (recvdQueue) {
			for (int i = recvdQueue.size()-1; i >= 0; i--) {
				TimedObject tobj = (TimedObject)recvdQueue.elementAt(i);
				if (sysdate < tobj.getDate())
					continue;

				recvdQueue.removeElementAt(i);

				Process(tobj.getObject());
			}
		}
	}

	public void run() {
		while (true) {
			try {
				ProcessRecvdObjects(System.currentTimeMillis());
				Thread.sleep(1000); // 1 Second.
			} catch (Exception e) {
				;
			}
		}
	}


}

class TimedObject extends Object {
	long date = 0;
	Object obj = null;

	public TimedObject(long date, Object obj) {
		this.obj = obj;
		this.date = date;
	}

	public long getDate() {
		return date;
	}

	public Object getObject() {
		return obj;
	}

	public String toString() {
		return new Date(date) + ": " + obj;
	}
}
