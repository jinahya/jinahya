   package org.dvb.dsmcc ;

   import java.lang.*;
   import java.io.*;
   import org.davic.net.*;

   import java.security.cert.X509Certificate;



/**
  * A DSMCCObject is an object which belongs to a DSMCC ServiceDomain. As
  * soon as a ServiceDomain has been attached to the file system hierarchy,
  * DSMCCObject objects can be created to access the ServiceDomain objects.
  * <p>
  * A DSMCCObject is specified by a pathname, which can either be an absolute 
  * pathname or a relative pathname. Relative paths shall work as defined in 
  * "Broadcast Transport Protocol Access API" in the main body of the specification. 
  * Path names must follow the naming conventions of the host platform. The 
  * constructors of this class shall accept the absolute paths returned by 
  * java.io.File.getAbsolutePath().<p>
  * To access the content of the object:<ul>
  * <li>For a Directory, the method list of the java.io.File class has to
  *     be used to get the entries of the directory.
  * <li>For a Stream object, the class DSMCCStream has to be used.
  *	<li>For a File,  the java.io.FileInputStream class or the 
  *     java.io.RandomAccessFile has to be used.
  * </ul>
  * NB :
  * <ul>
  * <li>Obviously, for the Object Carousel, the write mode of
  *     java.io.RandomAccessFile is not allowed.
  * </ul><p>
  * DSMCCObjects exist in two states, loaded and unloaded as returned by the isLoaded
  * method. Transitions from unloaded to loaded are triggered by applications calling 
  * the asynchronousLoad or synchronousLoad or getSigners(boolean) methods. Transitions 
  * from loaded to 
  * unloaded are triggered by applications calling the unload method. Attempting to 
  * load an already loaded object does not cause it to be re-loaded.<p>
  * The only state transitions for a DSMCCObject shall be only in response to these 
  * method calls. There shall be no implicit state transitions in either direction.
  * When the application no longer has any references to an object in the loaded state,
  * the system resources allocated should be freed by the system.<p>
  * The state machine of DSMCCObject is disconnected from any state model of the cache 
  * of an MHP receiver's DSMCC client. Objects may appear in that cache without any 
  * corresponding DSMCCObject being in the loaded state. Objects which are in that 
  * cache and where any corresponding DSMCCObject is not in the loaded state may 
  * disappear from that cache at any time.
  * The contents of a object may be accessible to applications from the cache without 
  * the DSMCCObject ever being in the loaded state.<p>
  *
  * NOTE: DSMCCObjects in the loaded state will consume memory in the MHP receiver. 
  * If memory in the MHP receiver is short, this memory can only be recovered by the 
  * receiver killing the MHP application. Applications which can accept weaker guarantees 
  * about the data of a DSMCCObject being available should use the prefetch methods.
  *
  * @see org.dvb.dsmcc.ServiceDomain
  */

   public class DSMCCObject extends java.io.File {
   
   /**
    * Create a DSMCCObject object.
    *
    * @param path the path to the file.
    */
      public DSMCCObject (String path) {super("toto");
      }
   
   /**
    * Create a DSMCCObject object.
    *
    * @param path the directory Path.
    * @param name the file pathname.
    */
      public DSMCCObject (String path, String name) {super("toto");
      }
   
   /**
    * Create a DSMCCObject object.
    *
    * @param dir the directory object.
    * @param name the file pathname.
    */
      public DSMCCObject (DSMCCObject dir, String name) {super("toto");
      }
   
   /** 
    * Returns a boolean indicating whether or not the DSMCCObject has been loaded.
    *
    * @return true if the file is already loaded, false otherwise.
    */
      public boolean isLoaded() { return true;
      }

   /**
   * Returns a boolean indicating whether or not the DSMCCObject is a DSMCC
   * Stream object.
   * @return true if the file is a stream, false if the object is not a 
   * stream or if the object kind is unknown.
   */
      public boolean  isStream() {return true;}
   
   /**
   * Returns a boolean indicating whether or not the DSMCCObject is a DSMCC
   * StreamEvent object.
   * NB: If isStreamEvent is true then isStream is true also.
   * @return true if the file is a stream event, false if the object is
   * not a stream event or if the object kind is unknown.
   */
      public boolean  isStreamEvent() {return true;}
   
   /**
   * Returns a boolean indicating if the kind of the object is known.
   * (The kind of an object is known if the directory containing it is loaded).
   * @return true if the type of the object is known, false otherwise.
   */
      public boolean isObjectKindKnown() {return true;}

   
   /**
    * This method is used to load a DSMCCObject.
    * This method blocks until the file is loaded. It can be aborted from another thread
    * with the abort method. In this case the InterruptedIOException is thrown.
    * If the IOR of the object itself or one of its parent directories is a 
    * Lite Option Profile Body, the MHP implementation
    * will not attempt to resolve it : a ServiceXFRException is thrown to indicate
    * to the application where the DSMCCObject is actually located.
    *
    * @exception InterruptedIOException the loading has been aborted.
    * @exception InvalidPathNameException the Object can not be found, or the serviceDomain isn't in a attached state.
    * @exception NotEntitledException the stream carrying the object is scrambled
    *		 and the user has no entitlements to descramble the stream.
    * @exception ServiceXFRException the IOR of the object or one of its parent
    *				directories is a Lite Option Profile Body.
    * @exception InvalidFormatException an inconsistent DSMCC message has been received. 
    * @exception MPEGDeliveryException an error has occurred while loading data from MPEG stream
    * 	 	 such as a timeout 
    * @exception ServerDeliveryException when an MHP terminal cannot communicate with the
    *		 server for files delivered over a bi-directional IP connection.
    * @excpetion InsufficientResourcesException there is not enough memory to load the object
    */
      public void synchronousLoad()
      throws InvalidFormatException, InterruptedIOException, MPEGDeliveryException, ServerDeliveryException,
		InvalidPathNameException, NotEntitledException, ServiceXFRException,
		InsufficientResourcesException {
      }
   
   /**
    * This method is used to asynchronously load a carousel object. 
    * This method can fail either asynchronously with an event or synchronously with an 
    * exception. When it fails synchronously with an exception, no event shall be sent to the 
    * listener.
    * For each call to this
    * method which returns without throwing an exception, one of the
    * following events will be sent to the application (by a listener mechanism) as
    * soon as the loading is done or if an error has occurred: 
    * SuccessEvent, InvalidFormatEvent, InvalidPathNameEvent, MPEGDeliveryErrorEvent, 
    * ServerDeliveryErrorEvent, ServiceXFRErrorEvent, NotEntitledEvent, LoadingAbortedEvent,
    * InsufficientResourcesEvent.
    *
    * @param l an AsynchronousLoadingEventListener to receive events related to asynchronous loading.
    *
    * @exception InvalidPathNameException the Object can not be found, or the serviceDomain isn't in a attached state.
    */
      public void asynchronousLoad (AsynchronousLoadingEventListener l) 
      throws InvalidPathNameException {
      }
   
   /**
    * This method is used to abort a load in progress. It can be used
    * to abort either a synchronousLoad or an asynchronousLoad.
    *
    * @exception NothingToAbortException There is no loading in progress.
    */
      public void abort() throws NothingToAbortException {
      }

   /**
    * 
    * Calling this method will issue a hint to the MHP for pre-fetching the object data
    * for that DSMCC object into cache.
    *
    * @param path the absolute pathname of the object to pre-fetch. 
    * @param priority the relative priority of this pre-fetch request (higher = more important)
    * @return true if the MHP supports pre-fetching (i.e. will try
    * to process the request) and false otherwise. Note that a return value of 
    * 'true' is only an indication that the MHP receiver supports pre-fetching. 
    * It is not a guarantee that the requested data will actually be loaded into cache
    * as the receiver may decide to drop the request in order to make resources 
    * available for regular load requests.
    */
      public static boolean prefetch(String path, byte priority) {
	return true;
      }

   /**
    * 
    * Calling this method will issue a hint to the MHP for pre-fetching the object data
    * for that DSMCC object into cache. 
    *
    * @param dir the directory object in which to pre-fetch the data.
    * @param path the relative path name of object to pre-fetch, starting from the 
    *             directory object passes as parameter.
    * @param priority the relative priority of this pre-fetch request 
    * (higher = more important)
    * @return true if the MHP supports pre-fetching (i.e. will try
    * to process the request) and false otherwise. Note that a return value of 
    * 'true' is only an indication that the MHP receiver supports pre-fetching. 
    * It is not a guarantee that the requested data will actually be loaded into cache
    * as the receiver may decide to drop the request in order to make resources 
    * available for regular load requests.
    */
      public static boolean prefetch(DSMCCObject dir, String path, byte priority) {
	return true;
      }
   
    /**
     * When calling this method, the applications gives a hint to the
     * MHP that if this object is not consumed by another application/thread, 
     * the system can free all the resources allocated to this object. It is
     * worth noting that if other clients use this object (e.g. a file input
     * stream is opened on this object or if the corresponding stream or stream
     * event is being consumed) the system resources allocated to this object
     * will not be freed. This method puts the DSMCCObject into the unloaded state.
     *
     * @exception NotLoadedException the carousel object is not loaded.
     */
      public void unload() throws NotLoadedException {
      }
   
    /**
     * Returns a URL identifying this carousel object. If the directory entry for the
     * object has not been loaded then null shall be returned.
     * 
     * @since MHP 1.0.1
     * @return a URL identifying the carousel object or null
     */
      public java.net.URL  getURL() {return null;
      }
   
   /**
    * Subscribes an ObjectChangeEventListener to receive notifications
    * of version changes of DSMCCObject.<p>
    *
    * This listener shall never be fired until after the object has successfully
    * entered the loaded state for the first time. Hence objects which never successfully 
    * enter the loaded state (e.g. because the object cannot be found) shall never have this 
    * listener fire. Once an object has successfully entered the loaded state once, this event 
    * shall continue to be fired when changes are detected by the MHP regardless of further 
    * transitions in or out of the loaded state.<p>
    * NOTE: The algorithm used for this change monitoring is implementation dependent.
    * In some implementations, this exception will always be thrown. In other implementations,
    * it will never be thrown. In other implementations, whether it is thrown or not will
    * depend on the complexity and design of the object carousel in which the object is
    * carried. Even where no exception is thrown, implementations are not required to
    * detect all possible forms in which an object may change.
    *
    * @param listener the ObjectChangeEventListener to be notified .
    * @throws InsufficientResourcesException if there are not sufficient resources to
    * monitor the object for changes.
    */
      public void addObjectChangeEventListener(ObjectChangeEventListener listener) 
	throws InsufficientResourcesException {
      }
   
   /**
    * Unsubscribes an ObjectChangeEventListener to receive notifications
    * of version changes of DSMCCObject.
    *
    * @param listener a previously registered ObjectChangeEventListener.
    */
      public void removeObjectChangeEventListener(ObjectChangeEventListener listener) {
      } 
   
   /**
    * Asynchronous loading of the directory entry information. 
    * Calling this is equivalent of calling the method <code>asynchronousLoad</code>
    * on the parent directory of a <code>DSMCCObject</code>.
    * This method can fail either asynchronously with an event or synchronously with an 
    * exception. When it fails synchronously with an exception, no event shall be sent to 
    * the listener.
    * @param l a listener which will be called when the loading is done.
    *
    * @exception InvalidPathNameException if the object cannot be found. 
    */
      public void loadDirectoryEntry (AsynchronousLoadingEventListener l) 
      throws InvalidPathNameException {
      }

	/**
	 * Set the retrieval mode for a <code>DSMCCObject</code>. The default
	 * retrieval mode is FROM_CACHE_OR_STREAM.
	 * The retrieval mode state is sampled when the object is loaded (whether
	 * explicitly or as described in "Constraints on the java.io.File methods
	 * for broadcast carousels").
	 * Changing the retrieval mode for a loaded object has no effect until the 
	 * object is unloaded and loaded again.
	 *
	 * @param retrieval_mode the retrieval mode to be used for the object
	 * specified as one of the public static final constants in this class.
	 * @throws IllegalArgumentException if the retrieval_mode specified is not 
	 * one listed defined for use with this method.
	 *
	 * @since MHP 1.0.1
	 */
	public void setRetrievalMode( int retrieval_mode ) {}

	/**
	 * Constant to indicate that the data for an object shall only be
	 * retrieved where it is already in cache and meets the requirements
	 * of cache priority signaling. 
	 * 
         * Where data is not in the cache, or the contents don't meet the requirements 
         * of the of cache priority signaling (i.e. cache priority signalling indicates
         * that an object re-acquisition is required), attempts to load a DSMCCObject 
	 * shall fail with <code>MPEGDeliveryException</code> or <code>MPEGDeliveryErrorEvent</code>
	 * for synchronousLoad and asynchronousLoad respectively.
	 *
	 * @since MHP 1.0.1
	 */
	public static final int FROM_CACHE =1;
	/**
	 * Constant to indicate that the data for an object shall be
	 * automatically be retrieved from the network where the data is
	 * not already cached.
	 * 
	 * Note that this method does not modify the caching policy 
	 * controlled by the signaling in the OC. So, if the data is signalled
	 * as requiring transparent caching then data will be retrieved from 
	 * the network if required.
	 *
	 * @since MHP 1.0.1
	 */

	public static final int FROM_CACHE_OR_STREAM=2;

	/**
	 * Constant to indicate that the data for an object shall always
	 * be retrieved from the network even if the data has already
	 * been cached.
	 *
	 * @since MHP 1.0.1
	 */
	public static final int FROM_STREAM_ONLY=3;

   /**
    * This method shall attempt to validate all certificate chains found
    * for this file in the network. Valid chains do not need to originate
    * from root certificates known to the MHP terminal, e.g. self signing
    * of data files. Applications should note that calls to this method
    * may take some time.
    * If the <code>DSMCCObject</code> is not loaded, this method will return null. 
    * If the <code>DSMCCObject</code> is loaded but not authenticated this method will 
    * return an outer array of size zero.
    * If the <code>DSMCCObject</code> is loaded, this method returns the same as 
    * getSigners(false), except if getSigners(false) would throw an exception, 
    * this method will return an outer array of size zero.
    *
    * <p>NOTE: If the file in the network changes between when it was loaded and
    * when the hash file(s), signature & certificate files are read and those
    * files have been updated to match the new version of the file then the 
    * hash value of the data which was loaded will not match the hash value in 
    * the hash file in the network and hence no certificate chains will be valid.
    *
    * @return a two-dimensional array of X.509 certificates, where the first 
    * index of the array determines a certificate chain and the second index 
    * identifies the certificate within the chain. Within one certificate chain 
    * the leaf certificate is first followed by any intermediate certificate 
    * authorities in the order of the chain with the root CA certificate as the 
    * last item.
    *
    * @since MHP 1.0.1
    */

   public X509Certificate[][] getSigners() { return null; }

   /** 
    * This method shall attempt to validate all certificate chains found
    * for this file in the network. The known_root parameter to the method
    * defines whether the MHP terminal shall check if the root certificate in 
    * each chain is known to it or not. If the root certificate is checked
    * then chains with unknown root certificates shall not be considered to
    * be valid. If root certificates are not checked then the MHP application
    * is responsible for comparing them with some certificate which it provides
    * (e.g. for self signing of data files). The hash file(s),
    * signature & certificate files shall be shall be fetched from the network 
    * in compliance with the caching priority defined in the main body of
    * this specification. If the object is in the loaded state then the
    * data of the file which was loaded shall be used and no new file 
    * contents loaded. If the object is not in the loaded state then this
    * method shall attempt to load it as if synchronousLoad had been called.
    * Applications should note that calls to this method may take some time.
    *
    * <p>NOTE: If the file in the network changes between when it was loaded and
    * when the hash file(s), signature & certificate files are read and those
    * files have been updated to match the new version of the file then the 
    * hash value of the data which was loaded will not match the hash value in 
    * the hash file in the network and hence no certificate chains will be valid.
    * 
    * @return a two-dimensional array of X.509 certificates, where the first 
    * index of the array determines a certificate chain and the second index 
    * identifies the certificate within the chain. Within one certificate chain 
    * the leaf certificate is first followed by any intermediate certificate 
    * authorities in the order of the chain with the root CA certificate as the 
    * last item. If no certificate chains are found to be valid then 
    * an outer array of size zero shall be returned.
    *
    * @param known_root if true then valid certificate chains are only those where
    * the root is known to the MHP terminal. If false, the validity of the chain shall
    * be determined without considering whether the root is known to the MHP terminal
    * or not.
    * @since MHP 1.0.3
    * @exception InterruptedIOException the loading has been aborted.
    * @exception InvalidPathNameException the Object can not be found, or the serviceDomain isn't in a attached state.
    * @exception NotEntitledException the stream carrying the object is scrambled
    *               and the user has no entitlements to descramble the stream.
    * @exception ServiceXFRException the IOR of the object or one of its parent
    *                              directories is a Lite Option Profile Body.
    * @exception InvalidFormatException an inconsistent DSMCC message has been received. 
    * @exception MPEGDeliveryException an error has occurred while loading data from MPEG stream
    *               such as a timeout 
    * @exception ServerDeliveryException when an MHP terminal cannot communicate with the
    *               server for files delivered over a bi-directional IP connection.
    * @exception InsufficientResourcesException there is not enough memory to load the object
    */

 public X509Certificate[][] getSigners(boolean known_root ) throws
     InvalidFormatException, InterruptedIOException, MPEGDeliveryException, ServerDeliveryException,
                InvalidPathNameException, NotEntitledException, ServiceXFRException,
                InsufficientResourcesException { return null; }

   }


