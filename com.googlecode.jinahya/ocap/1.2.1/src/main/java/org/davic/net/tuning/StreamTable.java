package org.davic.net.tuning;
import org.davic.mpeg.TransportStream;

/** A stream table containing information about transport streams
  * known to the receiver
  */

public class StreamTable {
  /* For javadoc to hide the non-public constructor. */
  StreamTable() {
  }

  /** Returns the transport streams that match the locator. <p>
    * The locator must uniquely identify the transport stream (i.e.
    * for DVB networks, it must specify the orig_network_id and the 
    * transport_stream_id). If the locator is more specific than
    * just identifying the transport stream, any more specific part of it 
    * will be disregarded. <p>
    * Since the same transport stream may be received
    * via multiple networks and via multiple network interfaces, 
    * this function returns an array of all the possible 
    * transport stream objects that can be 
    * used for receiving this transport stream.
    * @param locator A locator that points to a broadcast transport stream
    * @exception IncorrectLocatorException raised if the locator does not reference a 
    * broadcast transport stream
    *
    * @return array of transport streams
    */
  public static TransportStream[] getTransportStreams(org.davic.net.Locator locator) 
    throws NetworkInterfaceException {
      return null;
    }
	
  /** Returns all known transport streams on all network
    * interfaces as an array of Locators
    * @return array of Locators pointing to known transport streams
    */
  public static org.davic.net.Locator[] listTransportStreams() {
    return null;
  }
  
}

