
package org.davic.net.dvb;

import org.davic.net.InvalidLocatorException;

/** DVB Locator that encapsulates a DVB URL into an object
  */

public class DvbLocator extends org.davic.net.Locator {

  /* To prevent javadoc from showing a default constructor */
  private DvbLocator() {
    super(null);
  }

  /** Constructor for the DVB locator 
    * @param url a URL string
    * @exception InvalidLocatorException when the parameters to construct
    *         the locator wouldn't specify a valid locator
    *         (e.g. a numeric identifier out of range) 
    */
  public DvbLocator(String url) 
    throws InvalidLocatorException {
    super(url);
  }

  /** Constructor for the DVB locator corresponding to the URL form "dvb://onid.tsid" 
    * @param onid original network identifier
    * @param tsid transport stream identifier
    * @exception InvalidLocatorException when the parameters to construct
    *         the locator wouldn't specify a valid locator
    *         (e.g. a numeric identifier out of range) 
    */
  public DvbLocator(int onid, int tsid) 
    throws InvalidLocatorException {
    super(null);
  }

  /** Constructor for the DVB locator corresponding to the URL form "dvb://onid.tsid.serviceid" 
    * @param onid original network identifier
    * @param tsid transport stream identifier (if -1, the locator does 
    *             not include a transport_stream_id)
    * @param serviceid service identifier
    * @exception InvalidLocatorException when the parameters to construct
    *         the locator wouldn't specify a valid locator
    *         (e.g. a numeric identifier out of range) 
    */
  public DvbLocator(int onid, int tsid, int serviceid) 
    throws InvalidLocatorException {
    super(null);
  }

  /** Constructor for the DVB locator corresponding to the URL form 
    * "dvb://onid.tsid.serviceid;eventid" 
    * @param onid original network identifier
    * @param tsid transport stream identifier (if -1, the locator does
    *             not include a transport_stream_id)
    * @param serviceid service identifier
    * @param eventid event identifier
    * @exception InvalidLocatorException when the parameters to construct
    *         the locator wouldn't specify a valid locator
    *         (e.g. a numeric identifier out of range) 
    */
  public DvbLocator(int onid, int tsid, int serviceid, int eventid) 
    throws InvalidLocatorException {
    super(null);
  }

  /** Constructor for the DVB locator corresponding to the URL form 
    * "dvb://onid.tsid.serviceid.componenttag;eventid"
    * or "dvb://onid.tsid.serviceid.componenttag" 
    * @param onid original network identifier
    * @param tsid transport stream identifier (if -1, the locator does
    *             not include a transport_stream_id)
    * @param serviceid service identifier
    * @param eventid event identifier (if -1, the locator does not 
    *                include an event id)
    * @param componenttag component tag
    * @exception InvalidLocatorException when the parameters to construct
    *         the locator wouldn't specify a valid locator
    *         (e.g. a numeric identifier out of range) 
    */
  public DvbLocator(int onid, int tsid, int serviceid, int eventid, int componenttag) 
    throws InvalidLocatorException {
    super(null);
  }

  /** Constructor for the DVB locator corresponding to the URL form 
    * "dvb://onid.tsid.serviceid.componenttag{&componenttag};eventid"
    * or "dvb://onid.tsid.serviceid.componenttag{&componenttag}" 
    * @param onid original network identifier
    * @param tsid transport stream identifier (if -1, the locator does
    *             not include a transport_stream_id)
    * @param serviceid service identifier
    * @param eventid event identifier (if -1, the locator does not include an event id)
    * @param componenttags an array of component tags
    * @exception InvalidLocatorException when the parameters to construct
    *         the locator wouldn't specify a valid locator
    *         (e.g. a numeric identifier out of range) 
    */
  public DvbLocator(int onid, int tsid, int serviceid, int eventid, 
                    int[] componenttags) 
    throws InvalidLocatorException {
    super(null);
  }

  /** Constructor for the DVB locator corresponding to the URL form 
    * "dvb://onid.tsid.serviceid.componenttag{&componenttag};eventid/filepath"
    * or "dvb://onid.tsid.serviceid.componenttag{&componenttag}/filepath" 
    * @param onid original network identifier
    * @param tsid transport stream identifier (if -1, the locator does
    *             not include a transport_stream_id)
    * @param serviceid service identifier
    * @param eventid event identifier (if -1, the locator does not include an event id)
    * @param componenttags array of component tags (if null, the locator
    *                      does not include any component tags)
    * @param filePath the file path string including the slash character
    *        in the beginning 
    * @exception InvalidLocatorException when the parameters to construct
    *         the locator wouldn't specify a valid locator
    *         (e.g. a numeric identifier out of range) 
    */
  public DvbLocator(int onid, int tsid, int serviceid, int eventid, 
                    int[] componenttags, String filePath) 
    throws InvalidLocatorException {
    super(null);
  }


  /** Returns the original_network_id 
    * @return original_network_id or -1 if not present
    */
  public int getOriginalNetworkId() {
    return 0;
  }

  /** Returns the transport_stream_id 
    * @return transport_stream_id, -1 if not present
    */
  public int getTransportStreamId() {
    return 0;
  }

  /** Returns the service_id 
    * @return service_id, -1 if not present
    */
  public int getServiceId() {
    return 0;
  }

  /** Returns an array of the component_tags
    * @return an array containing the component_tags, 
    *         the length of the array will be zero if the locator does
    *         not identify component_tags
    */
  public int[] getComponentTags() {
    return null;
  }

  /** Returns the event_id
    * @return event_id, -1 if not present
    */
  public int getEventId() {
    return 0;
  }
  
  /** Returns the file name path part of the locator
    * @return the path string, including the slash character in the beginning. 
    *         If the locator does not include a path string, this method will
    *         return null.
    */
  public String getFilePath() {
    return null;
  }

   /**
     * Returns the textual service identifier, if one was provided to the constructor.
     * @return the textual service identifier, null if not present 
     * @since MHP1.0.1 
     */
    public String getTextualServiceIdentifier() {
	return null;
	}
}







