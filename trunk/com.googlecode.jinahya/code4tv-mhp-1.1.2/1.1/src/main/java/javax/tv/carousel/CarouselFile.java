/*
 * @(#)CarouselFile.java	1.27 00/08/27
 *
 * Copyright 1998-2000 by Sun Microsystems, Inc.,
 * 901 San Antonio Road, Palo Alto, California, 94303, U.S.A.
 * All rights reserved.
 * 
 * This software is the confidential and proprietary information
 * of Sun Microsystems, Inc. ("Confidential Information").  You
 * shall not disclose such Confidential Information and shall use
 * it only in accordance with the terms of the license agreement
 * you entered into with Sun.
 */

package javax.tv.carousel;

import java.io.File;
import java.io.FilenameFilter;
import javax.tv.locator.*;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.util.Vector;

/**
 * The <code>CarouselFile</code> class represents
 * file or directory data obtained from broadcast file systems.  An
 * instance of <code>CarouselFile</code> may be constructed from a
 * <code>Locator</code> instance or via constructors similar to those
 * of <code>java.io.File</code>.<p>
 *
 * Application classes implementing the
 * <code>CarouselFileListener</code> interface may subscribe with the
 * <code>CarouselFile</code> to receive notification of changes to the
 * file in the broadcast.  Upon the occurrence of a change, the
 * <code>CarouselFile</code> notifies subscribed
 * <code>CarouselFileListener</code> instances via
 * <code>CarouselFileChangeEvent</code> objects.<p>
 *
 * Successful instantiation of a <code>CarouselFile</code> object
 * causes its broadcast filesystem to be dynamically "mounted" in the
 * local filesystem.  The precise mount point can be determined by
 * calling <code>getCanonicalPath()</code> on the
 * <code>CarouselFile</code> instance representing the top-level
 * directory of the carousel.<p>
 *
 * Construction of a <code>CarouselFile</code> object causes its
 * contents to be loaded asynchronously from the broadcast stream.
 * Subsequent attempts to read the data of a <code>CarouselFile</code>
 * object will block until its contents are loaded.<p>
 *
 * Broadcast file data for which there are no remaining
 * <code>CarouselFile</code> instances or open file descriptors are
 * eligible for unloading from the cache.  Broadcast carousels for
 * which there are no remaining <code>CarouselFile</code> instances or
 * open file descriptors are eligible for unmounting from the local
 * filesystem.<p>
 *
 * Java TV API implementations that do not support broadcast
 * filesystem access will throw <code>IOException</code> upon any attempt
 * to construct a <code>CarouselFile</code> object.
 *
 * @see java.io.File#getCanonicalPath
 *
 */
public class CarouselFile extends File {
    /**
     * @serial The locator for this CarouselFile.
     */
	private Locator locator;
	private Vector listeners = new Vector();

	private FileWatcher fileWatcher = null;

//	private static final String CarouselFileDir = "\\tmp\\carousel0\\"; // Set as parameter
	private static final String CarouselProtocol = "carousel:/"; // Set as parameter
	
  /**
   * Creates a <code>CarouselFile</code> instance that represents the file
   * referenced by the given <code>Locator</code>.
   * <p>
   *
   * This constructor throws <code>java.io.IOException</code> if it
   * determines immediately that the requested carousel file cannot
   * be accessed.  Since this constructor may complete its work
   * asynchronously, absence of an <code>IOException</code> is not a
   * guarantee that the requested carousel file is accessible.
   * 
   * @param locator A <code>Locator</code> referencing the source of the
   * <code>CarouselFile</code>.
   *
   * @throws InvalidLocatorException If <code>locator</code> does not
   * refer to a carousel file.
   *
   * @throws IOException If the requested carousel file cannot be
   * accessed.  */
  public CarouselFile(Locator locator) throws InvalidLocatorException, IOException {
//	super(CarouselFileDir + CheckLocator(locator));
	super(CheckLocator(locator));

	this.locator = createLocator(locator.toExternalForm().substring(10));
  }


  /**
   * Creates a <code>CarouselFile</code> instance that represents the
   * file whose path name in the carousel is the given path argument.
   * <p>
   *
   * This constructor throws <code>java.io.IOException</code> if it
   * determines immediately that the requested carousel file cannot
   * be accessed.  Since this constructor may complete its work
   * asynchronously, absence of an <code>IOException</code> is not a
   * guarantee that the requested carousel file is accessible.
   *
   * @param path The file path name.
   *
   * @throws IOException If the requested carousel file cannot be
   * accessed.  */
  public CarouselFile(String path) throws IOException {
	//super(CarouselFileDir + path);
	super(path);

        if (path == null ) {
                throw new NullPointerException("Path is null");
        }

	if (!isAbsolute()) {
                throw new FileNotFoundException();
	}

	this.locator = createLocator(path);
  }

	
  /**
   * Creates a <code>CarouselFile</code> instance that represents the
   * file with the specified name in the specified carousel directory.
   * <p>
   *
   * This constructor throws <code>java.io.IOException</code> if it
   * determines immediately that the requested carousel file cannot
   * be accessed.  Since this constructor may complete its work
   * asynchronously, absence of an <code>IOException</code> is not a
   * guarantee that the requested carousel file is accessible.
   *
   * @param dir The directory.
   * @param name The file name.
   *
   * @throws IOException If the requested carousel file cannot be
   * accessed.  */
  public CarouselFile(CarouselFile dir, String name)  throws IOException {
    this(dir.getPath(), name);
  }
  

  /**
   * Creates a <code>CarouselFile</code> instance that represents the
   * file with the specified name in the specified carousel directory.
   * <p>
   *
   * This constructor throws <code>java.io.IOException</code> if it
   * determines immediately that the requested carousel file cannot
   * be accessed.  Since this constructor may complete its work
   * asynchronously, absence of an <code>IOException</code> is not a
   * guarantee that the requested carousel file is accessible.
   *
   * @param path The directory path name.
   * @param name The file name.
   *
   * @throws IOException If the requested carousel file cannot be
   * accessed.  */
  public CarouselFile(String path, String name)  throws IOException {
	//super(CarouselFileDir + path, name);
	super(path, name);
        if ( path == null || name == null ) {
                throw new NullPointerException("null String");
        }
	if (!isAbsolute()) {
                throw new FileNotFoundException();
	}

	this.locator = createLocator(path + "/" + name);
  }
  
  private Locator createLocator(String path) 
        throws IOException {
	try {
		if (path == null) { 
			throw new IOException("CarouselFile can't have an empty path");
		}

	   	if (!exists()) {
			throw new IOException("CarouselFile doesn't exist " + getPath());
		}

		if (!canRead()) {
			throw new IOException("CarouselFile can't be read " + getPath());
		}

		return LocatorFactory.getInstance().createLocator(
			CarouselProtocol + path.replace('\\', '/'));
	} catch (Exception e) {
		throw new IOException(e.toString());
	}
  }
       
        
  private static String CheckLocator(Locator locator)
	throws InvalidLocatorException, IOException {

	if (locator == null) {
		throw new NullPointerException();
	}

	String pathname = locator.toExternalForm();
	if (pathname == null) {
		throw new InvalidLocatorException(locator, "Invalid Carousel File");
	}

	if (pathname.toLowerCase().startsWith(CarouselProtocol) == false) {
		throw new InvalidLocatorException(locator, "Invalid Carousel File");
	}

	return pathname.substring(10).replace('/','\\');
  }

  /**
   * Lists the directory contents of this <code>CarouselFile</code> object.
   * This list does not include the current or parent directories.
   *
   * @return An array of file names contained in the directory
   * specified by this <code>CarouselFile</code> object.  If this
   * <code>CarouselFile</code> object does not refer to a directory,
   * this method returns <code>null</code>.
   *
   * @throws IOException If the directory cannot be accessed.
   *
   * @throws
   * SecurityException If a security manager exists and its
   * {@link java.lang.SecurityManager#checkRead(String)}
   * method denies read access to the file. */
  public String[] listDirectoryContents() throws IOException, SecurityException {
	SecurityManager security = System.getSecurityManager();
	if (security != null) {
	    security.checkRead(getPath());
	}
	return super.list();
  }

  /**
   * Subscribes a <code>CarouselFileListener</code> to receive
   * notifications of changes to this <code>CarouselFile</code>.  If
   * the specified listener is currently subscribed then no action is
   * performed.
   * 
   * @param listener The <code>CarouselFileListener</code> to be
   * notified.
   * 
   * @throws IOException If there are insufficient resources to
   * support this listener.
   *
   * @throws SecurityException If a security manager exists and its
   * <code>{@link
   * java.lang.SecurityManager#checkRead(java.lang.String)}</code>
   * method denies read access to the file.  */
  public void addListener(CarouselFileListener listener) 
	throws IOException, SecurityException {

	if (listener == null) {
		throw new NullPointerException();
	}

	SecurityManager security = System.getSecurityManager();
	if (security != null) {
	    security.checkRead(getPath());
	}

	if (!(listeners.contains(listener)))
	    listeners.addElement(listener);

	if (fileWatcher == null) {
		fileWatcher = new FileWatcher(this);
		fileWatcher.start();
	}
  }
  

  /**
   * Unsubscribes a <code>CarouselFileListener</code> from receiving
   * notifications of changes to this <code>CarouselFile</code>.  If
   * the given <code>CarouselFileListener</code> is not currently
   * subscribed for notification then no action is performed.
   * 
   * @param listener A currently registered
   * <code>CarouselFileListener</code>.
   * */
  public void removeListener(CarouselFileListener listener) {
	if (listener == null) {
		throw new NullPointerException();
	}

 	listeners.removeElement(listener);

	if (listeners.size() == 0 && fileWatcher != null) {
//		fileWatcher.stop();
		fileWatcher = null;
	}
  }
  
  /**
   * Returns a <code>Locator</code> identifying this
   * <code>CarouselFile</code>.
   * 
   * @return  A <code>Locator</code> identifying this
   *          <code>CarouselFile</code>.
   */
  public Locator getLocator() {
    return locator;
  }

  /**
   * Requests that the cached contents of this <code>CarouselFile</code> be
   * updated with the version currently in the broadcast stream.  If the
   * <code>CarouselFile</code> data does not currently reside in the broadcast
   * stream, subsequent attempts to access its contents will fail.
   *
   * @throws SecurityException If a security manager exists and its
   * <code>{@link
   * java.lang.SecurityManager#checkRead(java.lang.String)}</code>
   * method denies read access to the file.
   */
    // How does this affect already opened files?
  public void refreshCache() throws SecurityException {
	SecurityManager security = System.getSecurityManager();
	if (security != null) {
	    security.checkRead(getPath());
	}

	// TBD: implement
  }

  /**
   * Save the state of this object to a stream (i.e. serialize it).
   *
   * @serialData Write out our File superclass in the required section, 
   * followed by the Locator's external form in the optional section.
   */
  private void writeObject(java.io.ObjectOutputStream stream) 
    throws java.io.IOException {

    stream.defaultWriteObject();
    String externalForm = null;
    if (locator != null) {
      externalForm = locator.toExternalForm();
    }
    stream.writeObject(externalForm);
  }
  
  /**
   * Reconstitute this object from a stream (i.e. deserialize it).
   */
  private void readObject(java.io.ObjectInputStream stream) 
    throws java.io.IOException, ClassNotFoundException {

    stream.defaultReadObject();
    String externalForm = (String) stream.readObject();
    if (externalForm != null) {
      try {
	locator = LocatorFactory.getInstance().createLocator(externalForm);
      } catch (MalformedLocatorException ex) {
	throw new java.io.IOException(ex.toString());
      }
    }
  } 
  /**
   * Notify all the listeners of this carousel file object, that the
   * carousel file has been updated. 
   */
   void notifyListeners() {
	if (listeners == null) 
		return;

	CarouselFileChangeEvent event = new CarouselFileChangeEvent(this);

	for (int i = 0; i < listeners.size(); i++) {
		CarouselFileListener listener = (CarouselFileListener)listeners.elementAt(i);
		if (listener == null)
			continue;

		notifyAsyncListener(event, listener);
	}
   }

  private void notifyAsyncListener(
		CarouselFileChangeEvent event,
		CarouselFileListener listener) {

	if (listener == null || event == null)
		return;

	NotifyCarouselFileThread thread = new NotifyCarouselFileThread(event, listener);
	if (thread != null) 
		thread.start();
  }
}

/**
 * Executes a watcher thread to check the modification time of the
 * file periodically.  A delay interval of DELAY milliseconds is
 * used. If the file modification time changes from what it was at
 * at last notification time, then the listeners are notified.
 */

class FileWatcher extends Thread {
    private CarouselFile file = null;
    private long lastModified = -1;

    public FileWatcher(CarouselFile file) {
	super("Carousel File Watcher");
	this.file = file;
    }

   /**
    * Asynchronously check to see if the carousel file has been
    * updated, if so notify any and all listeners.
    */
    public void run() {
	int DELAY = 500;
	while (true) {
		try {
			if (lastModified != file.lastModified()) {
				lastModified = file.lastModified();
				file.notifyListeners();
			}
			Thread.sleep(DELAY);
		} catch (Exception e) {
			;
		}
	}
    }
}

class NotifyCarouselFileThread extends Thread {
	CarouselFileListener listener = null;
	CarouselFileChangeEvent event = null;

  public NotifyCarouselFileThread(
		CarouselFileChangeEvent event,
		CarouselFileListener listener) {

	super("NotifyCarouselFileThread");

	this.listener = listener;
	this.event = event;
  }

  public void run() {
	if (this.listener == null || this.event == null)
		return;

	listener.carouselFileChanged(event);
  }
}
