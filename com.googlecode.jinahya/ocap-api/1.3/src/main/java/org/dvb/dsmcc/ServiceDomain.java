   package org.dvb.dsmcc;

   import java.io.*;
   import org.davic.net.*;

 /**
  * A <code>ServiceDomain</code> represents a group of DSMCC objects. The objects 
  * are sent either using the object carousel for a broadcast network or with 
  * the DSM-CC User-to-User protocol for an interactive network.<p>
  * 
  * To access the objects of a <code>ServiceDomain</code>, it has to be attached to the 
  * file system name space of the MHP terminal.
  *
  * To access the content of an object, the application has four ways:
  * <ul>
  * <li>It can instantiate the class that is used to read the object
  *      (java.io.FileInputStream or java.io.RandomAccessFile for a File 
  *      or DSMCCStream for a stream) from its pathname. 
  *      The loading of the object is implicit but the application has 
  *      no way to abort it.
  *      NB: Obviously, for the Object Carousel, the write mode of
  *          java.io.RandomAccessFile is not allowed.
  * <li>It can instantiate a DSMCCObject and carry out a 
  *       Synchronous loading. The loading can be aborted by the abort method
  *       of the DSMCCObject class.
  *		  When the object is loaded, the application will instantiate the 
  *       class used to read the object.
  * <li>It can instantiate a DSMCCObject and carry out an Asynchronous 
  *       loading. So several loading can be started in parallel from the
  *		  same thread.
  * <li>It is also possible to create directly a java.io.File for a DSMCC
  *     object.
  * </ul>
  * Instances of <code>ServiceDomain</code> exist in two states, attached and
  * detached. Newly created instances are always in the detached state. They
  * become attached when a call to the <code>attach</code> method succeeds.
  * They become detached following a call to the <code>detach</code> method.<p>
  * When service domains in the attached state temporarily lose their network connection (e.g.
  * if the MHP terminal tunes away from the transport stream
  * where they are carried), 
  * the behaviour of DSMCC objects which are part of the
  * service domain is specified in the main body of the present document. If such
  * a network connection becomes available again then the service domain shall 
  * resume normal behaviour.<p>
  * A service domain which is temporarily lost its network connection may be
  * forced into the detached state by the implementation if the loss of the
  * network connection becomes irrecoverable. The precise details of when this 
  * happens are implementation dependent. This is the only situation when
  * shall be forced into the detached state. Once a ServiceDomain is detached, 
  * it will never be automatically attached. 
  */

   public class ServiceDomain {
   
   /**
    * Creates a ServiceDomain object.
    */
      public ServiceDomain() {
      }
   
   /**
    * This function is used to attach a ServiceDomain from an object carousel.
    * It loads the module which contains the service gateway object and 
    * mounts the <code>ServiceDomain</code> volume in the file system hierarchy. 
    * This call will block until the service gateway is loaded. 
    * It can be aborted by another thread with the method detach. In this case an
    * <code>InterruptedIOException</code> is thrown.
    * 
    * <p>Calling this method on a <code>ServiceDomain</code> object already in the attached 
    * state shall imply a detach of the <code>ServiceDomain</code> object before the attach 
    * operation unless the <code>ServiceDomain</code> is already attached to the correct location.
    * Hence if the attach operation fails, the appropriate exception for the failure mode shall 
    * be thrown and the <code>ServiceDomain</code> is left in a detached state and not attached 
    * to the former object carousel / service domain. If the <code>ServiceDomain</code> is 
    * already attached to the correct location then the method call shall have no effect.<p>
    * @param aDVBService The coordinates of the DVB service which contains the
    * object carousel. This locator has to point to a DVB service.
    * @param aCarouselId The identifier of the carousel.
    * @exception InterruptedIOException The attachment has been aborted.
    * @exception MPEGDeliveryException An MPEG error occurred (such as time-out).
    * @exception ServiceXFRException The service gateway cannot be loaded in the current service domain.
    * This exception shall not be thrown in this version of the specification.
    */
      public void attach(org.davic.net.Locator aDVBService, int aCarouselId)
      throws ServiceXFRException, InterruptedIOException, MPEGDeliveryException {
      }
   
   /**
    * This function is used to attach a <code>ServiceDomain</code> from an object carousel.
    * It loads the module which contains the service gateway object and 
    * mounts the <code>ServiceDomain</code> volume in the file system hierarchy. 
    * This call will block until the service gateway is loaded. It can be
    * aborted by another thread with the method detach. In this case an
    * <code>InterruptedIOException</code> is thrown.
    * <p>Calling this method on a <code>ServiceDomain</code> object already in the attached 
    * state shall imply a detach of the <code>ServiceDomain</code> object before the attach 
    * operation unless the <code>ServiceDomain</code> is already attached to the correct location.
    * Hence if the attach operation fails, the appropriate exception for the failure mode shall 
    * be thrown and the <code>ServiceDomain</code> is left in a detached state and not attached 
    * to the former object carousel / service domain. If the <code>ServiceDomain</code> is 
    * already attached to the correct location then the method call shall have no effect.<p>
    *
    * @param l The locator pointing to the elementary stream carrying the DSI
    *          of the object carousel, or to a DVB service that carries one and only
    *	       one object carousel. 
    *
    * @exception DSMCCException An error has occurred during the attachment. For
    *		 example, the locator does not point to a component carrying a DSI 
    *		 of an Object Carousel or to a service containing a single carousel
    * @exception InterruptedIOException The attachment has been aborted.
    * @exception MPEGDeliveryException attaching to this domain would require tuning.
    * 
    */
      public void attach (org.davic.net.Locator l) 
      throws DSMCCException, InterruptedIOException, MPEGDeliveryException  {
      }
   
   /**
    * This function is used to attach a <code>ServiceDomain</code> from either
    * an object carousel or from an interactive network.
    * This call will block until the attachment is done.
    * <p>Calling this method on a <code>ServiceDomain</code> object already in the attached 
    * state shall imply a detach of the <code>ServiceDomain</code> object before the attach 
    * operation unless the <code>ServiceDomain</code> is already attached to the correct location.
    * Hence if the attach operation fails, the appropriate exception for the failure mode shall 
    * be thrown and the <code>ServiceDomain</code> is left in a detached state and not attached 
    * to the former object carousel / service domain. If the <code>ServiceDomain</code> is 
    * already attached to the correct location then the method call shall have no effect.<p>
    *
    * @param NSAPAddress The NSAP Address of a ServiceDomain as defined in
    * in ISO/IEC 13818-6	
    * @exception InterruptedIOException The attachment has been aborted.
    * @exception InvalidAddressException The NSAP Address is invalid.
    * @exception DSMCCException An error has occurred during the attachment.  
    * @exception MPEGDeliveryException attaching to this domain would require tuning.
    */
      public void attach(byte[] NSAPAddress) 
      throws DSMCCException, InterruptedIOException, 
	     InvalidAddressException, MPEGDeliveryException  {
      }
   
	/**
	 * A call to this method is a hint that the applications gives to the 
	 * MHP to unmount the volume and delete the objects of the service
	 * domain. When another application is using objects of the same
	 * service domain the method has no effects. When there are no other
	 * application using objects of the service domain, a call to this 
	 * method is a hint that the MHP can free all the resources allocated to
	 * this service domain.<p>
	 * After this, the <code>ServiceDomain</code> will be in a non-attached state and will behave 
	 * as if it had just been constructed. Subsequent calls to <code>detach</code> 
	 * shall throw <code>NotLoadedException</code>.
	 * @exception NotLoadedException is thrown if the ServiceDomain
	 * is not attached or if there is no call to <code>attach</code> in progress.
	 */
      public void detach() throws NotLoadedException {
      }
   
   /**
    * This method returns the NSAP address of the <code>ServiceDomain</code>.
    *
    * @return the NSAP address of the <code>ServiceDomain</code>.
    * @exception NotLoadedException is thrown if the <code>ServiceDomain</code>
    * is not attached.
    */
      public byte[] getNSAPAddress() throws NotLoadedException {
		return null;
      }
   
	/**
	 * Obtain a java.net.URL corresponding to a 'dvb:' locator.
	 * If the service domain corresponding to the locator is attached
	 * and the file referenced in the locator exists then an instance of
	 * <code>java.net.URL</code> is returned which can be used to reference
	 * this file.
	 *
	 * @param l a locator object encapsulating a 'dvb:' locator which
	 * includes a 'dvb_abs_path' element.
	 * @return a <code>java.net.URL</code> which can be used to access the
	 * file referenced by the 'dvb:' locator
	 * @exception InvalidLocatorException if the locator is not a valid 'dvb:'
	 * locator or does not includes all elements including 'dvb_abs_path' element
	 * @exception NotLoadedException is thrown if the locator is valid and includes
 	 * enough information but it references a service domain which is not attached.
	 * @exception FileNotFoundException if the service domain is attached
	 * but the file referenced by the locator does not exist
	 */
	public static java.net.URL getURL( org.davic.net.Locator l) throws
		NotLoadedException, InvalidLocatorException, FileNotFoundException
		{ return null; }

	/**
	 * Returns a <code>DSMCCObject</code> object describing the top level directory
	 * of this <code>ServiceDomain</code>. If the ServiceDomain object is not attached
	 * then null is returned.
	 *
	 * @return an instance of org.dvb.dsmcc.DSMCCObject if attached or null otherwise
	 *
	 * @since MHP 1.0.1
	 */
	public DSMCCObject getMountPoint() { return null;}

	/**
	 * Return whether the network connection for this service domain is available.
	 * This return value is independent of whether the service domain is attached
	 * or not. If a service domain is distributed across multiple network connections
	 * (e.g. using the optional support for DSMCC over IIOP) then this will reflect
	 * the availability of the network connection carrying the object mounted to the
	 * mount point.
	 *
	 * @return true if the network connection for this service domain is available otherwise false
	 * @since MHP 1.0.1
	 */
	public boolean isNetworkConnectionAvailable() { return false; }

	/**
	 * Return whether this service domain is in the attached or detached state.
	 * @return true if this service domain is in the attached state, otherwise false
	 * @since MHP 1.0.1
	 */
	public boolean isAttached() { return false ;}

	/**
	 * Return the locator for this service domain.
         * If this ServiceDomain instance was last attached by specifying a locator 
	 * then an equivalent locator shall be returned except if the original locator 
	 * contained extra information that is not necessary to identify the service domain 
	 * in which case this extra information is removed.
	 * If the attach was done with the <code>attach(locator, int)</code> 
         * signature, the locator is complemented with the component_tag value that the 
         * platform has identified during attaching the ServiceDomain.
         * If this ServiceDomain instance was last attached by specifying
	 * an NSAP address then the locator shall be generated from that address. If
	 * this ServiceDomain has never been attached then null shall be returned.<p>
         * The syntax of the NSAP address is defined in section titled "LiteOptionsProfileBody"
         * in annex B of the MHP specification. It contains the same fields as the locator 
         * syntax specified in the System integration aspects clause. The locator is 
         * constructed by taking the fields out of the NSAP address and encoding them in the 
         * locator syntax together with the component_tag value that the platform has identified 
         * during attaching the ServiceDomain.
	 * @since MHP 1.0.1
	 *
	 * @return a locator for this service domain
	 */
	public Locator getLocator() { return null; }

}

