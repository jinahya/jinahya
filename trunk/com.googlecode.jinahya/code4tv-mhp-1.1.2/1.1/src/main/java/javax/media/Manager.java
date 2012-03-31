/*
 * @(#)Manager.java	1.64 98/03/28
 *
 * Copyright 1996-1998 by Sun Microsystems, Inc.,
 * 901 San Antonio Road, Palo Alto, California, 94303, U.S.A.
 * All rights reserved.
 *
 * This software is the confidential and proprietary information
 * of Sun Microsystems, Inc. ("Confidential Information").  You
 * shall not disclose such Confidential Information and shall use
 * it only in accordance with the terms of the license agreement
 * you entered into with Sun.
 */

package javax.media;

import java.io.*;
import java.util.*;
import java.net.URL;
import java.net.MalformedURLException;
import java.util.Enumeration;
import java.util.Vector;
import javax.media.protocol.DataSource;
import javax.media.protocol.URLDataSource;
import com.sun.tv.media.*;


/**
 * <code>Manager</code> is the access point for obtaining
 * system dependent resources such as <code>Players</code>,
 * <code>DataSources</code>, and the system <code>TimeBase</code>.
 * <p>
 *
 * A <code>Player</code> is an object used to
 * control and render multimedia data that
 * is specific to the content type of the data.
 * A <code>DataSource</code> is an object used to 
 * deliver time-based multimedia data that is specific
 * to a delivery protocol.
 * A <code>DataSource</code> provides
 * a <code>Player</code> with media data;
 * a <CODE>Player</CODE> must have a <CODE>DataSource</CODE>.
 * <code>Manager</code> provides access to a protocol and media independent
 * mechanism for constructing <code>Players</code> and
 * <code>DataSources</code>.
 *
 * <h2>Creating Players and DataSources</h2>
 *
 * <code>Manager</code> will create<code>Players</code> from a
 * <code>URL</code>, a <CODE>MediaLocator</CODE> or a <code>DataSource</code>.
 * Creating a <code>Player</code> requires the following:
 * <ul>
 * <li> Obtain the connected <code>DataSource</code> for the specified
 * protocol
 * <li> Obtain the <code>Player</code> for the content-type
 * specified by the <code>DataSource</code>
 * <li> Attach the <code>DataSource</code> to the <code>Player</code>
 * using the <code>setSource</code> method.
 * </ul>
 *
 * <h3>Finding DataSources by Protocol</h3>
 *
 * A <code>MediaLocator</code> defines a protocol for obtaining
 * content.
 * <code>DataSources</code> are identified by the protocol
 * that they support. <code>Manager</code> uses the protocol
 * name to find <code>DataSource</code> classes.
 * <p>
 * 
 * To find a <code>DataSource</code> using a <code>MediaLocator</code>,
 * <code>Manager</code> constructs a list of class names from the protocol
 * package-prefix list and the protocol name obtained
 * from the <code>MediaLocator</code>.
 * For each class name in the constructed list a new <code>DataSource</code>
 * is instanced, the <code>MediaLocator</code> is attached,
 * and the <code>DataSource</code> is connected.
 * If no errors have occurred, the procces is considered finished and the
 * connected <code>DataSource</code> is used by
 * <code>Manager</code> in any following operations.
 * If there was an error then the next class name in the list
 * is tried.
 * The exact details of the search algorithm is described in
 * the method documentation below.
 *
 * <h3>Finding Players by Content Type</h3>
 *
 * A <code>Player</code> is a <code>MediaHandler</code>.
 * A <code>MediaHandler</code> is a an object that reads
 * data from a <code>DataSource</code>. There are two types
 * of supported <code>MediaHandler</code>: <code>MediaProxy</code>,
 * and <code>Player</code>.
 * <p>
 *
 * <code>MediaHandlers</code> are identified by the content type that they
 * support. A <code>DataSource</code> identifies the content type
 * of the data it produces with the <code>getContentType</code> method.
 * <code>Manager</code> uses the content type name to
 * find instances of <code>MediaHandler</code>.
 * <p>
 *
 * To find a <code>MediaHandler</code> using a content type name,
 * <code>Manager</code> constructs a list of class names from
 * the content package-prefix list and the content type name.
 * For each class name in the constructed list a new <code>MediaHandler</code>
 * is instanced, and the <code>DataSource</code> is attached to
 * the <code>MediaHandler</code> using <coded>MediaHandler.setSource</code>.
 * <p>
 *
 * If the <code>MediaHandler</code> is a <code>Player</code> and the
 * <code>setSource</code> was successful the process is finished
 * and the <code>Player</code> is returned.
 * If the <code>setSource</code> failed, another name in the
 * list is tried.
 * <p>
 *
 * If the <code>MediaHandler</code> is a <code>MediaProxy</code>
 * then a new <code>DataSource</code> is obtained from the
 * <code>MediaProxy</code>, a new list is created for the
 * content type the <code>DataSource</code> supports and the
 * whole thing is tried again.
 * <p>
 * 
 * If a valid <code>Player</code>, is not found then the whole
 * procedure is repeated is repeated with "unknown" substituted
 * for the content-type name. The "unknown" content type is supported
 * by generic <code>Players</code> that are capable of handling
 * a large variety of media types, often in a platform dependent
 * way.
 *<p>
 *
 * The detailed creation algorithm is specified in the methods below.
 * <p>
 *
 * <h2>Player Threads</h2>
 *
 * <code>Players</code> render media data asynchronously from
 * the main program flow.
 * This implies that a <code>Player</code> must often manage one
 * or more threads.
 * The threads managed by the <code>Player</code> are not
 * in the thread group of the application that calls
 * <code>createPlayer</code>.
 *
 * <h2>System Time Base</h2>
 *
 * All <code>Players</code> need a <code>TimeBase</code>. Many  
 * use a system-wide <code>TimeBase</code>, often based on
 * a time-of-day clock.
 * <code>Manager</code> provides access to the system <code>TimeBase</code>
 * through <code>getSystemTimeBase</code>.
 * 
 *
 * @see java.net.URL
 * @see MediaLocator
 * @see PackageManager
 * @see javax.media.protocol.DataSource
 * @see javax.media.protocol.URLDataSource
 * @see MediaHandler
 * @see Player
 * @see MediaProxy
 * @see TimeBase
 *
 * @version 1.64, 98/03/28.
 */

public final class Manager {

    private static SystemTimeBase sysTimeBase = null;

    public final static String UNKNOWN_CONTENT_NAME = "unknown";

    private static Vector protocolPrefixList = null;
    private static Vector contentPrefixList = null;
    private static Properties jmfProperties = null;
    private static String propFileName = null;
    private static String cacheDirectory = null;

    /*************************************************************************
     * STATIC INITIALIZATION
     *************************************************************************/
    
    /**
     * This private constructor keeps anyone from actually
     * getting a <CODE>Manager</CODE>.
     */
    private Manager() {}
    
    /**
     * Create a <CODE>Player</CODE> for the specified media.
     * This creates a MediaLocator from the URL and then
     * calls <CODE>createPlayer</CODE>.
     *
     * @param sourceURL The <CODE>URL</CODE> that describes the media data.
     * @return A new <CODE>Player</CODE>.
     * @exception NoPlayerException Thrown if no <CODE>Player</CODE>
     * can be found.
     * @exception IOException Thrown if there was a problem connecting
     * with the source.
     */
    public static Player createPlayer(URL sourceURL)
	throws IOException, NoPlayerException {
	return createPlayer(new MediaLocator(sourceURL));
    }

    /**
     * Create a <code>Player</code> for the specified media.
     * <p>
     * The algorithm for creating a <CODE>Player</CODE> from
     * a <code>MediaLocator</code> is:
     * <ol>
     * <li>Get the protocol from the <code>MediaLocator</code>.
     * <li>Get a list of <code>DataSource</code> classes that
     * support the protocol, using the protocol package-prefix-list.
     * <li> For each source class in the list:
     * <ol>
     * <li>Instantiate a new <code>DataSource</code>,
     * <li>Call the <code>connect</code> method to connect the source.
     * <li>Get the media content-type-name (using <code>getContentType</code>)
     * from the source.
     * <li>Get a list of <code>MediaHandler</code> classes that support the
     * media-content-type-name, using the content package-prefix-list.
     * <li>For each <code>MediaHandler</code> class in the list:
     * <ol>
     * <li>Instantiate a new <code>MediaHandler</code>.
     * <li>Attach the source to the <code>MediaHandler</code> by calling
     * <code>MediaHandler.setSource</code>.
     * <li>If there are no failures, determine the type of
     * the <code>MediaHandler</code>; otherwise try the next
     * <coded>MediaHandler</code> in the list.
     * <li>If the <code>MediaHandler</code> is a <code>Player</code>,
     * return the new <code>Player</code>.
     * <li>If the <code>MediaHandler</code> is a <code>MediaProxy</code>,
     * obtain a new <code>DataSource</code> from the <code>MediaProxy</code>,
     * obtain the list of <code>MediaHandlers</code> that support the new
     * <code>DataSource</code>, and continue searching the new list.
     * </ol>
     * <li>If no <code>MediaHandler</code> is found for this source,
     * try the next source in the list.
     * </ol>
     * <li>If no <code>Player</code> is found after trying all of the sources,
     * reuse the source list.<br>
     * This time, for each source class in the list:
     * <ol>
     * <li>Instantiate the source.
     * <li>Call the <code>connect</code> method to connect to the source.
     * <li>Use the content package-prefix-list to create a list of 
     * <code>MediaHandler</code> classes that support the
     * "unknown" content-type-name.
     * <li>For each <code>MediaHandler</code> class in the list,
     * search for a <code>Player</code> as in the previous search.
     * <ol>
     * <li>If no <code>Player</code> is found after trying all of the sources,
     * a <CODE>NoPlayerException</CODE> is thrown.
     * </ol>
     * </ol>
     * @param sourceLocator A <CODE>MediaLocator</CODE> that describes
     * the media content.
     * @return A <CODE>Player</CODE> for the media described by the source.
     * @exception NoPlayerException Thrown if no <CODE>Player</CODE> can
     * be found.
     * @exception IOException Thrown if there was a problem connecting
     * with the source.
     */
    static public Player createPlayer(MediaLocator sourceLocator)
	throws IOException, NoPlayerException {

        Player newPlayer = null;
	
	try {
	    newPlayer = createPlayer(sourceLocator, false);
	} catch (NoPlayerException e) {
	    // ... and if that doesn't work, try finding
	    // a player for the UNKNOWN_CONTENT_NAME.
	    newPlayer = createPlayer(sourceLocator, true);
	}

	return newPlayer;
    }

    /**
     * Create a <code>Player</code> for the <code>DataSource</code>.
     * <p>
     * The algorithm for creating a <CODE>Player</CODE> from
     * a <code>DataSource</code> is:
     * <ol>
     * <li>Get the media content-type-name from the source by
     * calling <code>getContentType</code>.
     * <li>Use the content package-prefix-list to get a list of 
     * <code>Player</code> classes that support the media content-type name.
     * <li>For each <code>Player</code> class in the list:
     * <ol>
     * <li>Instantiate a new <code>Player</code>.
     * <li>Attach the source to the <code>Player</code> by calling
     * <code>setSource</code> on the <code>Player</code>.
     * <li>If there are no failures,  return the new <code>Player</code>;
     * otherwise,
     * try the next <code>Player</code> in the list.
      </ol>
     * <li>If no <code>Player</code> is found for this source:
     * <ol>
     * <li>Use the content package-prefix-list to create a list 
     * of <code>Player</code> classes that support the
     * "unknown" content-type-name.
     * <li>For each <code>Player</code> class in the list:
     * <ol>
     * <li>Instantiate a new <code>Player</code>.
     * <li>Attach the source to the <code>Player</code> by
     * calling <code>setSource</code> 
     * on the <code>Player</code>.
     * <li>If there are no failures, return the new <code>Player</code>;
     * otherwise, try the next <code>Player</code> in the list.
     * </ol>
     * </ol>
     * <li>If no <code>Player</code> can be created,
     * a <CODE>NoPlayerException</CODE> is thrown.
     * </ol>
     * @param DataSource The <CODE>DataSource</CODE> that describes
     * the media content.
     * @return A new <code>Player</code>.
     * @exception NoPlayerException Thrown if a <code>Player</code> can't
     * be created.
     * @exception IOException Thrown if there was a problem connecting
     * with the source.
     */
    static public Player createPlayer(DataSource source)
	throws IOException, NoPlayerException {

	Player newPlayer;
	try {
	    // First try and create one using the source
	    // as the content identifier ...
	    newPlayer = createPlayer(source, source.getContentType());
	} catch( NoPlayerException e) {
	    // ... if that doesn't work use the unknown-content type.
	    newPlayer = createPlayer(source, UNKNOWN_CONTENT_NAME);
	}

	return newPlayer;
    }


   /**
     * Create a <code>DataSource</code> for the specified media.
     *
     * @param sourceURL The <CODE>URL</CODE> that describes the media data.
     * @return A new <CODE>DataSource</CODE> for the media.
     * @exception NoDataSourceException Thrown if no <code>DataSource</code>
     * can be found.
     * @exception IOException Thrown if there was a problem connecting
     * with the source.
     */
    public static DataSource createDataSource(URL sourceURL)
	throws IOException, NoDataSourceException  {
	return
	    createDataSource(new MediaLocator(sourceURL));
    }
    
    /**
     * Create a <code>DataSource</code> for the specified media.
     * <p>
     *
     * Returns a data source for the protocol specified by
     * the <CODE>MediaLocator</CODE>. The returned data source
     * is <i>connected</i>; <code>DataSource.connect</code> has been
     * invoked.
     * <p>
     *
     * The algorithm for creating a <code>DataSource</code> from
     * a <code>MediaLocator</code> is:
     * <ol>
     * <li>Get the protocol from the <code>MediaLocator</code>.
     * <li>Use the protocol package-prefix list to get a list of 
     * <code>DataSource</code> classes that
     * support the protocol.
     * <li> For each source class in the list:
     * <ol>
     * <li>Instantiate a new <code>DataSource</code>.
     * <li>Call <code>connect</code> to connect the source.
     * <li>If there are no errors, return the connected
     * source; otherwise, try the next source in the list.
     * </ol>
     * <li>If no source has been found, obtain a <code>URL</code> from the
     * <code>MediaLocator</code> and use it to create
     * a <code>URLDataSource</code>
     * <li>If no source can be found, a <CODE>NoDataSourceException</CODE>
     * is thrown.
     * </ol>
     * 
     *
     * @param sourceLocator The source protocol for the media data.
     * @return A connected <CODE>DataSource</CODE>.
     * @exception NoDataSourceException Thrown if no <CODE>DataSource</CODE>
     * can be found.
     * @exception IOException Thrown if there was a problem connecting
     * with the source.
     */
    static public DataSource createDataSource(MediaLocator sourceLocator)
	throws IOException, NoDataSourceException {

	DataSource source = null;

	// For each DataSource that implements the protocol
        // that's specified in the source locator ....
	String protocol = sourceLocator.getProtocol();
	if (protocol.equals("")) {
	    throw new NoDataSourceException();
	}
	Enumeration protoList =
	    getDataSourceList(protocol).elements();
	while(protoList.hasMoreElements()) {
	    
	    String protoClassName = (String)protoList.nextElement();
	    try {

		// ... Try and instance a DataSource ....
		Class protoClass = Class.forName(protoClassName);
		source = (DataSource)protoClass.newInstance();

		// ... and get it connected ....
		source.setLocator(sourceLocator);
		source.connect();

		//  ... o.k. we've found one, so we're done.
		break;
		
	    } catch (ClassNotFoundException e) {
		// try another data source.
		source = null;
	    } catch (InstantiationException e) {
		// try another data source.
		source = null;
	    } catch (IllegalAccessException e) {
		// try another data source.
		source = null;
	    }
	}
	
	// If we haven't found one installed,
	// we'll try and create one from a URL/URLDataSource.
	if( source == null) {
	    try {
		source = new URLDataSource(sourceLocator.getURL());
		source.connect();
	    } catch(MalformedURLException me) {
		// Can't get a URL so we're done.
		source = null;
	    }
	}
	
        // If we still haven't found one, we're done
	// and we don't have a source.
	if( source == null) {
	    throw new NoDataSourceException();
	}

	return source;
    }
    
    /**
     * Get the time-base object for the system.
     * @return The system time base.
     */
    public static TimeBase getSystemTimeBase() {
	if (sysTimeBase == null) {
	    sysTimeBase = new SystemTimeBase();
	}
	return sysTimeBase;
    }

    /**
     * Create a player for the <CODE>MediaLocator</CODE>.
     * <p>
     * If <CODE>useUnknownContent</CODE> is <CODE>true</CODE>,
     * a <CODE>Player</CODE> for
     * content-type  <CODE>UNKNOWN_CONTENT_NAME</CODE> is created; otherwise,
     * the <CODE>DataSource</CODE> determines the content-type with the
     * <code>getContentType</code> method.
     *
     * @param sourceLocator Used to determine the protocol that
     * the <CODE>DataSource</CODE> will use.
     * @param useKnownContent Used to determine the content type used
     * to find a <CODE>Player</CODE>.
     * @returns A new <CODE>Player</CODE>.
     * @exception NoPlayerException Thrown if no <CODE>Player</CODE> can
     * be found.
     * @exception IOException Thrown if there was a problem connecting
     * with the source.
     */
    static Player createPlayer(MediaLocator sourceLocator,
			       boolean useUnknownContent)
	throws IOException, NoPlayerException {

	Player newPlayer = null;
	    
	// For each DataSource that implements the protocol
        // that's specified in the source ...
	String protocol = sourceLocator.getProtocol();
	if (protocol.equals("")) {
	    throw new NoPlayerException();
	}
	Enumeration protoList =
	    getDataSourceList(protocol).elements();
	while(protoList.hasMoreElements()) {
	    
	    String protoClassName = (String)protoList.nextElement();
	    DataSource source = null;
	    try {

		// ... Try an instance a DataSource ....
		Class protoClass = Class.forName(protoClassName);
		source = (DataSource)protoClass.newInstance();

		// ... and get it connected ....
		source.setLocator(sourceLocator);
		source.connect();

		// ... o.k. we've found one, so now try and get
		// a Player for it.
		try {
		    if( useUnknownContent) {
			// Either use the default content type ...
			newPlayer = createPlayer(source, UNKNOWN_CONTENT_NAME);
		    } else {
			
			// ... or let the source specify the content type.
			newPlayer =
			    createPlayer(source, source.getContentType());
		    }

		    // If we got one we're done.
		    break;
		    
		} catch (NoPlayerException e) {
		    // Go try another one.
		    newPlayer = null;
		}
		
		// No luck so try another source.
		source.disconnect();
		
	    } catch (ClassNotFoundException e) {
		// try another data source.
		source = null;
	    } catch (InstantiationException e) {
		// try another one.
		source = null;
	    } catch (IllegalAccessException e) {
		// try another one.
		source = null;
	    }
	}

	// If we don't have a Player yet, then try and create a Player
	// from the URL data source.
	DataSource source = null;
	if( newPlayer == null) {
	    try {
		source = new URLDataSource(sourceLocator.getURL());
		source.connect();

		// Got the data source so attach it to
		// a player.
		if( useUnknownContent) {
		    // Either use the default content type ...
		    newPlayer = createPlayer(source, UNKNOWN_CONTENT_NAME);
		} else {
		    // ... or let the source specify the content type.
		    newPlayer =
			createPlayer(source, source.getContentType());
		}

	    } catch(MalformedURLException me) {
		// Can't get a URL so we're done.
		source = null;
	    } finally {
		if(newPlayer == null && source != null) {
		     source.disconnect();
		}
	    }
	}
	if( newPlayer == null) {
	    throw new NoPlayerException();
	}
	return newPlayer;
    }


    /**
     * Create a <CODE>Player</CODE> for a particular content type
     * using the source.
     *
     * @param source The source of media for the <CODE>Player</CODE>.
     * @param contentTypeName The type of content the <CODE>Player</CODE>
     * should handle.
     * @return A new <CODE>Player</CODE>.
     * @exception NoPlayerException Thrown if no <CODE>Player</CODE> can
     * be found for the source and content-type.
     * @exception IOException Thrown if there was a problem connecting
     * with the source.
     */
    static Player createPlayer(DataSource source, String contentTypeName)
	throws IOException, NoPlayerException {
	
	Player newPlayer = null;

	// Try every handler we can find for this content type.
	Enumeration playerList =
	    getHandlerClassList(contentTypeName).elements();

	MediaHandler mHandler;
	DataSource newSource = null;
	while(playerList.hasMoreElements()) {
	    String handlerClassName = (String)playerList.nextElement();
	    
	    try {
		
		// ... try and instance the handler ...
		Class handlerClass = Class.forName(handlerClassName);

		mHandler = (MediaHandler)handlerClass.newInstance();
		// ... set the DataSource on it ...
		
		mHandler.setSource(source);

		// if this is a Player then we're done.
		if( mHandler instanceof Player) {
		    newPlayer = (Player)mHandler;
		    break;
		}
	    

		// Otherwise it must be a proxy.
		// Get a new data source, and content type  ...
		MediaProxy mProxy = (MediaProxy)mHandler;
		newSource = mProxy.getDataSource();
		String newContentType = newSource.getContentType();
		// .. recurse to try and create a Player with it.
		newPlayer = createPlayer(newSource,newContentType);
		
		break;
 
	    } catch (ClassNotFoundException e) {
		// Couldn't find the handler so try another.
		newPlayer = null;
	    } catch (InstantiationException e) {
		// Can't instance the handler so try another.
		newPlayer = null;
	    } catch (IllegalAccessException e) {
		// Can't get at the handler so try another.
		newPlayer = null;
	    } catch (IncompatibleSourceException e) {
		// The handler didn't know what to
		// do with the DataSource so try another handler.
		newPlayer = null;
	    } catch (NoDataSourceException e) {
		// Proxy failed to produce a new data source
		// see if there are other proxies out there.
		newPlayer = null;
	    }

	}// end of while

	if( newPlayer == null) {
	    throw new NoPlayerException();
	}
	
	return newPlayer;
    }
	
    /**
     * Build a list of <CODE>DataSource</CODE> class names from the
     * protocol prefix-list and a protocol name.
     * <p>
     * The first name in the list will always be:
     * <blockquote><pre>
     * media.protocol.&lt;protocol&gt;DataSource
     * </pre></blockquote>
     * <p>
     *
     * Each additional name looks like:
     * <blockquote><pre>
     * &lt;protocol-prefix&gt;.media.protocol.&lt;protocol&gt;.DataSource
     * </pre></blockquote>
     * for every <CODE>&lt;protocol-prefix&gt;</CODE> in the
     * protocol-prefix-list.
     *
     * @param protocol The name of the protocol the source must
     * support.
     * @return A vector of strings, where each string is
     * a <CODE>Player</CODE> class-name.
     */
    static public Vector getDataSourceList(String protocolName) {

	// The first element is the name of the protocol handler ...
	String sourceName =
	    "media.protocol." + protocolName + ".DataSource";

	return buildClassList(getProtocolPrefixList(), sourceName);
    }

    /**
     * Build a list of <CODE>Handler/CODE> classes from the
     * content-prefix-list and a content name.
     * <p>
     * The first name in the list will always be:
     * <blockquote><pre>
     * media.content.&lt;contentType&gt;.Handler
     * </pre></blockquote>
     * <p>
     *
     * Each additional name looks like:
     * <blockquote><pre>
     * &lt;content-prefix&gt;.media.content.&lt;contentName&gt;.Player
     * </pre></blockquote>
     * for every <CODE>&lt;content-prefix&gt;</CODE> in the
     * content-prefix-list.
     *
     * @param contentName The content type to use in the class name.
     * @return A vector of strings where each one is a <CODE>Player</CODE>
     * class-name.
     */
    static public Vector getHandlerClassList(String contentName) {

	// players are found by content type ....
	String handlerName = "media.content." + contentName + ".Handler";

	// ... build a list of classes using the content-prefix-list.
	return buildClassList(getContentPrefixList(), handlerName);
    } 

    /**
     * Build a list of complete class names.
     *<p>
     * 
     * For each element of the prefix-list
     * the following element is added to the list:
     * <blockquote><pre>
     *    &lt;prefix&gt;.&lt;name&gt;
     * </pre></blockquote>
     * These are added to the list in the same order as the prefixes appear
     * in the prefix-list.
     * </ol>
     * 
     * @param prefixList The list of prefixes to prepend to the class name.
     * @param name The name of the class to build the list for.
     * @return A vector of class name strings.
     */
    static Vector buildClassList(Vector prefixList, String name) {
	
	// New list which has the name as the first element ...
	Vector classList = new Vector();

	// Try and instance one directly from the classpath
	// if it's there.
	// $jdr: This has been objected to as confusing,
	// the argument for it's inclusion is that it
	// gives the user (via the classpath) a way
	// of modifying the search list at run time
	// for all applications.
	// classList.addElement(name);

	// ... for each prefix append the name and put it
	// in the class list ...

        // $csaito, for tv RI, to match the jmf spec 1.0
	classList.addElement(name);

	Enumeration prefix = prefixList.elements();
	while( prefix.hasMoreElements()) {
	    String prefixName = (String)prefix.nextElement();
	    classList.addElement(prefixName + "." + name);
	}

	// ... done
	return classList;
    }

    static Vector getContentPrefixList() {
	return (Vector)PackageManager.getContentPrefixList().clone();
    }
    static Vector getProtocolPrefixList() {
	return (Vector)PackageManager.getProtocolPrefixList().clone();
    }
    

    /*************************************************************************
     * PROPERTIES FILE READ/WRITE METHODS
     *************************************************************************/
    
    
    /**
     * Returns the protocol prefix list as read from the properties file
     */
    static Vector getPersistentProtocolPrefixList() {
	return protocolPrefixList;
    }

    /**
     * Writes the protocol prefix list to the properties file if possible.
     * Returns false if it could not write the file
     */
    static boolean setPersistentProtocolPrefixList(Vector list) {
	if (list == null)
	    return false;
	protocolPrefixList = list;
	jmfProperties.put("protocol.prefixes", listToString(list));
	return writeProperties();
    }

    /**
     * Returns the content prefix list as read from the properties file
     */
    static Vector getPersistentContentPrefixList() {
	return contentPrefixList;
    }

    /**
     * Writes the content prefix list to the properties file if possible.
     * Returns false if it could not write the file
     */
    static boolean setPersistentContentPrefixList(Vector list) {
	if (list == null)
	    return false;
	contentPrefixList = list;
	jmfProperties.put("content.prefixes", listToString(list));
	return writeProperties();
    }

    /**
     * May return null if the cache directory was not set, or the
     * properties could not be read.
     */
/** Not really part of JMF 1.1 spec.
 **    public static String getCacheDirectory() {
 **	return cacheDirectory;
 **    }
 **/

    static String getPropertiesFileName() {
	if (propFileName == null) {
	    String javaHome;
	    try {
		javaHome = System.getProperty("java.home");
	    } catch (Exception e) {
		return null;
	    }
	    propFileName = javaHome + File.separator + "lib" + File.separator +
		"jmf.properties";
	}
	return propFileName;
    }

    /* Should not be public, currently testing */
    private static boolean writeProperties() {
	String propFile = getPropertiesFileName();
	if (propFile == null)
	    return false;
	try {
	    System.err.println("File name = " + propFile);
	    FileOutputStream out = new FileOutputStream(propFile);
	    BufferedOutputStream bs;
	    jmfProperties.save(bs = new BufferedOutputStream(out), "JMF Properties");
	    bs.flush();
	    out.close();
	    System.err.println("Wrote properties file");
	} catch (Exception e) {
	    System.err.println("Error writing properties: " + e);
	    return false;
	}
	return true;
    }

    /**
     * Reads in the properties file <java.home>/lib/jmf.properties
     */
    static boolean readProperties() {
	String propFile = getPropertiesFileName();
	jmfProperties = new Properties();
	if (propFile == null)
	    return false;
	try {
	    FileInputStream in = new FileInputStream(propFile);
	    jmfProperties.load(new BufferedInputStream(in));
	    contentPrefixList = stringToList(
			       jmfProperties.getProperty("content.prefixes","java sun"));
	    protocolPrefixList = stringToList(
			       jmfProperties.getProperty("protocol.prefixes", "java sun"));
	    cacheDirectory = jmfProperties.getProperty("cache.directory", "");
	    in.close();
	} catch (Exception e) {
	    return false;
	}
	return true;
    }

    /**
     * Converts a vector of strings to a single string separated by spaces.
     */
    static String listToString(Vector list) {
	// amith: Should we check for duplicates here ?
	String s = "";
	for (int i=0; i < list.size(); i++)
	    s = s + (String) list.elementAt(i) + " ";
	return s;
    }

    /**
     * Converts a string containing package names separated by spaces to
     * a vector list of package names.
     */
    static Vector stringToList(String s) {
	// Inefficient under the assumption that its called very rarely
	Vector v = new Vector();
	if (s == null)
	    return v;
	s += " ";
	while (s.length() > 0) {
	    int spc = s.indexOf(" ");
	    if (spc > 0) {
		String pkg = s.substring(0, spc);
		// amith: Should we check for duplicates here ?
		v.addElement(pkg);
	    }
	    if (spc != -1)
		if (s.length() == spc + 1)
		    s = "";
		else
		    s = s.substring(spc + 1);
	}
	return v;
    }
}
