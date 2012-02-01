package org.dvb.dsmcc;

import java.lang.*;
import java.io.*;

import org.davic.net.*;


import javax.media.Time;
import javax.media.Clock;


/**
* The DSMCCStream class is used to manage DSMCC Stream Objects.
* The BIOP::Stream message shall be read from the network once only, before the constructor 
* of this class returns. Hence methods which return information from that message shall not 
* be affected by any subsequent changes to that information.<p>

* The methods in this class relating to the NPT mechanism shall also be
* mapped onto the DVB timeline as defined in the main part of this specification.

* NOTE: The NPT mechanism and scheduled stream events that depend on it are 
* known to be vulnerable to disruption in many digital TV distribution 
* networks. Existing deployed network equipment that re-generates the STC is 
* unlikely to be aware of NPT and hence will not make the necessary corresponding 
* modification to STC values inside NPT reference descriptors.  Applications 
* should only use NPT where they are confident that the network where they are
* to be used does not have this problem.
* @see org.dvb.dsmcc.DSMCCObject
*/

public class DSMCCStream {


  /**
	* Creates a Stream Object from a DSMCC Object. The BIOP message 
	* referenced by the DSMCCObject has to be a Stream or StreamEvent BIOP message.
	* @param aDSMCCObject the DSMCC object which describes the stream
	* @exception NotLoadedException the DSMCCObject is not loaded.
	* @exception IllegalObjectTypeException the DSMCCObject is 
			neither a DSMCC Stream nor a DSMCCStreamEvent
	*/
  public DSMCCStream(DSMCCObject aDSMCCObject)
		throws NotLoadedException, IllegalObjectTypeException
	{
	}
  
  /**
	* Create a Stream Object from its pathname. For an object Carousel, this
	* method will block until the module which contains the object is loaded.
	* The BIOP message referenced by the DSMCCObject pointed to by the parameter
	* path has to be a Stream or StreamEvent BIOP message.
	* @param path the pathname of the DSMCCStream Object.
	* @exception IOException If an IO error occurred.
	* @exception IllegalObjectTypeException the DSMCCObject is 
			neither a DSMCC Stream nor a DSMCCStreamEvent
	*/
  public DSMCCStream(String path) throws IOException, IllegalObjectTypeException
	{
	}
  
  /**
	* Create a DSMCCStream from its pathname. For an object Carousel, this
	* method will block until the module which contains the object is loaded. 
        * The BIOP message referenced by the DSMCCObject pointed to be the
	* parameters path and name has to be a Stream or StreamEvent BIOP message.
	* @param path the directory path.
	* @param name the name of the DSMCCStream Object.
	* @exception IOException If an IO error occurred.
	* @exception IllegalObjectTypeException the DSMCCObject is 
			neither a DSMCC Stream nor a DSMCCStreamEvent
	*/
  public DSMCCStream(String path, String name)
		throws IOException, IllegalObjectTypeException
	{
	}

	  
   /**
	 * This function returns the duration in milliseconds of the DSMCC Stream.
	 * If the DSMCCStream BIOP message does not specify duration, zero will be returned.
	 * @return The duration in milliseconds of the DSMCC Stream.
	*/
  public long getDuration()
	{ return (long)0;
	}
  
  
  
  /**

	* This function is used to get value of the current timeline in milliseconds.
        * Implementations are not required to continuously monitor for 
	* the timeline descriptors. 
	* In implementations which do not continuously monitor, this method will block while 
	* the current timeline is retrieved from the stream.
	* @return the current time in milliseconds or zero if the DSMCC Stream object BIOP 
	* message does not contain any taps pointing to a timeline.
	* @exception MPEGDeliveryException if there is an error in retrieving the timeline descriptors

	*/
  public long getNPT() throws MPEGDeliveryException
	{ return (long)0;
	}
  
  /**
	* This function returned a Locator referencing the streams of 
	* this collection. The interpretation of the return value
	* is determined by the <code>isMPEGProgram</code> method.
	* @return a locator.
	*/
  public org.davic.net.Locator getStreamLocator()
	{return null;
	}

/**
	* This method will return true if the Stream(Event) BIOP message contains a 
	* tap with use field BIOP_PROGRAM_USE, otherwise it will return false.
      	* @return true only if the Stream(Event) BIOP message is as described above
	*/
  public boolean isMPEGProgram()
    {return true;
    }

  /**
	* This function returns a boolean indicating if the Stream Object
	* refers to an audio stream. This is the case if the audio field in the Stream(Event) 
	* BIOP message has a value different from zero.
	* @return true only if the Stream object refers to an audio stream
	*/
  public boolean isAudio()
    {return false ;
    }
  /**
	* This function returns a boolean indicating if the Stream Object
	* refers to an video stream.
	* This is the case if the `video' field in the Stream(Event) BIOP message 
	* has a value different from zero otherwise false is returned.
	* @return true only if the Stream object refers to an video stream
	*/
  public boolean isVideo()
    {return false;
    }
  /**
	* This function returns a boolean indicating if the Stream Object
	* refers to a data stream. This is the case if the data field in the 
	* Stream(Event) BIOP message has a value different from zero.
	* @return true only if the Stream object refers to a data stream
	*/
	public boolean isData() {return true; }

	/**

	 * Get the rate for the timeline referenced in the DSMCCStream object.
	 * Where the timeline is NPT, the NPT rate is returned.
	 * Where the timeline is the DVB timeline, 0/1 is returned if it is paused
	 * and 1/1 is returned if it is advancing.
	 * The method returns null if no timeline is referenced by the DSMCCStream
	 * object, (i.e. for NPT, no STR_NPT_USE tap in the list

	 * of taps).
	 * @return the NPT rate or null
         * @exception throws MPEGDeliveryException if there is an error in retrieving NPT reference descriptors
	 * @since MHP 1.0.1
	 */
	public NPTRate getNPTRate() throws MPEGDeliveryException { return null; }

	/**

	 * Add a listener for timeline events on the <code>DSMCCStream</code> object.

	 * Adding the same listener a second time has no effect.
	 * @param l the listener
	 * @since MHP 1.0.1
	 */
	public void addNPTListener(NPTListener l){};

	/**

	 * Remove a listener for timeline events on the <code>DSMCCStream</code> object.

	 * Removing a non-subscribed listener has no effect.
 	 * @param l the listener to remove
	 * @since MHP 1.0.1
	 */
	public void removeNPTListener(NPTListener l) {};


	/**
	 * Convert a time measured according to a timeline in the stream
         * into a JMF media time measured in a specified clock.
	 * @param time the time measured according to the timeline in the stream
	 * @param c the clock to express the media time
	 * @return the JMF media time corresponding to the specified time
	 * @throws IllegalArgumentException if the Clock is not a Player which is
	 * presenting at least one of the streams of this collection
	 * (as returned by getStreamLocator).
	 */
	public Time getMediaTimeFromStream(long time, Clock c) {return null;}


}

