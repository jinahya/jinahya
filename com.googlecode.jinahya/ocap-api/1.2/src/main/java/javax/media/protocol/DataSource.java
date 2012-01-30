/**
<p>This is not an official specification document, and usage is restricted.
</p>
<a name="notice"><strong><center>
NOTICE
</center></strong><br>
<br>

(c) 2005-2008 Sun Microsystems, Inc. All Rights Reserved.
<p>

Neither this file nor any files generated from it describe a complete
specification, and they may only be used as described below.  
<p>
Sun Microsystems Inc. owns the copyright in this file and it is provided
to you for informative use only. For example, 
this file and any files generated from it may be used to generate other documentation, 
such as a unified set of documents of API signatures for a platform 
that includes technologies expressed as Java APIs. 
This file may also be used to produce "compilation stubs," 
which allow applications to be compiled and validated for such platforms. 
By contrast, no permission is given for you to incorporate this file, 
in whole or in part, in an implementation of a Java specification.
<p>
Any work generated from this file, such as unified javadocs or compiled
stub files, must be accompanied by this notice in its entirety.
<p>
This work corresponds to the API signatures of JSR 927: Java TV API 1.1.1.  
In the event of a discrepency between this work and the JSR 927 specification, 
which is available at http://www.jcp.org/en/jsr/detail?id=927, the latter takes precedence.
*/



  


package javax.media.protocol;

import javax.media.*;
import java.net.*;

import javax.media.Duration;
import java.io.IOException;

/** 
 * A <CODE>DataSource</CODE> is an abstraction for media protocol-handlers.
 * <CODE>DataSource</CODE> manages the life-cycle of the media source
 * by providing a simple connection protocol.
 *
 * <h2>Source Controls</h2>
 * 
 * A <code>DataSource</code> might support an operation
 * that is not part of the <code>DataSource</code>
 * class definition. For example a source could support
 * positioning its media to a particular time.
 * Some operations are dependent on the data stream that the
 * source is managing, and support cannot be determined
 * until after the source has been connected.
 * <p>
 *
 * To obtain all of the objects that provide control
 * over a <code>DataSource</code>, use <code>getControls</code>
 * which returns an array of <code>Object</code>
 * To determine if a particular kind of control
 * is available and obtain the object that implements
 * it, use <code>getControl</code> which takes
 * the name of the Class or Interface that of the
 * desired control.
 *
 *
 * @see Manager
 * @see DefaultPlayerFactory
 * @see Positionable
 * @see RateConfigureable
 *
 * @version 1.17, 07/09/19
 */
public abstract class DataSource implements Controls, javax.media.Duration
{

    /** 
     * A no-argument constructor required by pre 1.1 implementations
     * so that this class can be instantiated by
     * calling <CODE>Class.newInstance</CODE>.
     *
     */
    public DataSource() { }

    /** 
     * Construct a <CODE>DataSource</CODE> from a <CODE>MediaLocator</CODE>.
     * This method should be overloaded by subclasses;
     * the default implementation just keeps track of
     * the <CODE>MediaLocator</CODE>.
     *
     *
     * @param source The <CODE>MediaLocator</CODE> that describes
     * the <CODE>DataSource</CODE>.
     */
    public DataSource(MediaLocator source) { }

    /** 
     * Set the connection <CODE>source</CODE> for this <CODE>DataSource</CODE>.
     * This method should only be called once; an error is thrown if 
     * the locator has already been set.
     *
     * @param source The <CODE>MediaLocator</CODE> that describes the
     * media source.
     */
    public void setLocator(MediaLocator source) { }

    /** 
     * Get the <CODE>MediaLocator</CODE> that describes this source.
     * Returns <CODE>null</CODE> if the locator hasn't been set. 
     * (Very unlikely.)
     * @return The <CODE>MediaLocator</CODE> for this source.
     */
    public MediaLocator getLocator() {
        return null;
    }

    /** 
     * Check to see if this connection has been
     * initialized with a <CODE>MediaLocator</CODE>.
     * If the connection hasn't been initialized,
     * <CODE>initCheck</CODE> throws an <CODE>Error</CODE>.
     * Most methods should call <CODE>initCheck</CODE> on entry.
     *
     * @throws java.lang.Error if the connection has not been initialized.
     */
    protected void initCheck() { }

    /** 
     * Get a string that describes the content-type of the media
     * that the source is providing.
     * <p>
     * It is an error to call <CODE>getContentType</CODE> if the source is
     * not connected.
     *
     * @return The name that describes the media content.
     */
    public abstract String getContentType();

    /** 
     * Open a connection to the source described by
     * the <CODE>MediaLocator</CODE>.
     * <p>
     *
     * The <CODE>connect</CODE> method initiates communication with the source.
     *
     * @exception IOException Thrown if there are IO problems
     * when <CODE>connect</CODE> is called.
     */
    public abstract void connect() throws IOException;

    /** 
     * Close the connection to the source described by the locator.
     * <p>
     * The <CODE>disconnect</CODE> method frees resources used to maintain a
     * connection to the source.
     * If no resources are in use, <CODE>disconnect</CODE> is ignored.
     * If <CODE>stop</CODE> hasn't already been called,
     * calling <CODE>disconnect</CODE> implies a stop.
     *
     */
    public abstract void disconnect();

    /** 
     * Initiate data-transfer. The <CODE>start</CODE> method must be
     * called before data is available.
     *(You must call <CODE>connect</CODE> before calling <CODE>start</CODE>.)
     *
     * @exception IOException Thrown if there are IO problems with the source
     * when <CODE>start</CODE> is called.
     */
    public abstract void start() throws IOException;

    /** 
     * Stop the data-transfer.
     * If the source has not been connected and started,
     * <CODE>stop</CODE> does nothing.
     */
    public abstract void stop() throws IOException;
}
