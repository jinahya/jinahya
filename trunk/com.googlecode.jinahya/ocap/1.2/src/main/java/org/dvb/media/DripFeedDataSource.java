package org.dvb.media;

import javax.media.Time;

import java.io.IOException;

/**
  * This class allows to create a source for a JMF player to be able 
  * to feed the decoder progressively with parts of a clip (e.g. I or P MPEG-2 
  * frame) according to the drip-fed mode format defined in the MHP content 
  * format clause.
  * <P>
  * To start using the drip-feed mode, the application needs to instantiate
  * a player representing a MPEG-2 video decoder and have its source be a 
  * DripFeedDataSource instance.
  * <P>
  * A DripFeedDataSource instance can be obtained by calling the default
  * constructor of the class.
  * <P>
  * A player that will be bound to a MPEG-2 video decoder (when realized) can 
  * be created with a locator of the following text representation: "dripfeed://". 
  * <P>
  * After having the DripFeedDataSource connected to a Player representing a 
  * MPEG-2 video decoder, the following rules applies: <BR>
  * - If the feed method is called when the player is in the "prefetched" state 
  * the image will be stored so that when the player goes in the "started" state
  * it will be automatically displayed. <BR>
  * - If the feed method is called when the player is in the "started" mode, the
  * frame shall be displayed immediately. In particular it is not required to 
  * feed a second frame to the decoder to display the first frame. <BR>
  * - If the feed method is called when the player is in any other state (or if 
  * the DripFeedDataSource is not connected to a player), it will be ignored by 
  * the platform implementation. <BR>

  */

public class DripFeedDataSource extends javax.media.protocol.DataSource
{
  /** 
    * Constructor. A call to the constructor will throw a security exception if 
    * the application is not granted the right to use the drip feed mode.
    * The constructor shall automatically set the MediaLocator for this 
    * DataSource to the only allowed value: dripfeed://. There is no need for
    * applications to later call setLocator.
    */
  public DripFeedDataSource() {}

  /**
    * This method allows an application to feed the decoder progressively 
    * with parts of a clip (e.g. I or P MPEG-2 frame) according to the drip-fed
    * mode format defined in the MHP content format clause.<BR>
    * The feed method shall not be called more often than every 500ms. If this 
    * rule is not respected, display is not guaranteed.<p>
    * While in the prefetch state the drip feed data source is only required to correctly 
    * process a single invocation of this method where the data consists only of a single I frame.
    * Possible additional invocations while in the prefetch state shall have implementation 
    * specific results. 
    *
    * @param clip_part Chunk of bytes compliant with the drip-fed mode format 
    * defined in the MHP content format clause (i.e. one MPEG-2 frame with
    * optional synctactic MPEG-2 elements).
    * 
    */
  public void feed(byte[] clip_part) {}

  /**
    * This method shall return the content type for mpeg-2 video "drips"
    *
    * @return the content type for MPEG-2 video drips
    */
  public java.lang.String getContentType() {return null;}

  /**
    * This method shall not be used and has no effect. This source is 
    * considered as always connected.
    *
    * @throws IOException never thrown in this sub-class
    */
  public void connect() throws IOException {}

  /**
    * This method shall not be used and has no effect. This source is 
    * considered as always connected.
    */
  public void disconnect() {}

  /**
    * This method shall not be used and has no effect. This source is 
    * considered as always started.
    *
    * @throws IOException never thrown in this sub-class
    */
  public void start() throws IOException {}

  /**
    * This method shall not be used and has no effect. This source is 
    * considered as always started.
    *
    * @throws IOException never thrown in this sub-class
    */
  public void stop() throws IOException {}

	/**
	 * This method shall not be used and has no effect.
	 *
	 * @return DURATION_UNKNOWN. 
	 */

	public  Time getDuration() {return DURATION_UNKNOWN;};

	/**
	 * Obtain the collection of objects that control this object.
	 * If no controls are supported, a zero length array is returned. 
	 *
	 * @return the collection of object controls 
	 */
	public Object[] getControls() {return null;}
	
	/**
	 * Obtain the object that implements the specified Class or Interface.
	 * The full class or interface name must be used. 
	 * If the control is not supported then null is returned. 
	 *
	 * @param controlType the full class or interface name of the requested control
	 * @return the object that implements the control, or null. 
	 */

	public Object getControl(String controlType) { return null;}

}


