package org.ocap.hn.profiles.upnp;

import org.ocap.hn.content.MetadataNode;

/**
 * This interface contains constants that are specific to UPnP and used in conjunction
 * with the <code>org.ocap.hn.content.MetadataNode</code> interface.
 */
public interface UPnPConstants
{
  /**
   * An identifier for the object. The value of each object id property must
   * be unique with respect to the server hosting this content.
   * <p>
   * The value is <code>didl-lite:</code>(object)<code>@"id"</code>
   * </p>
   */
    public static final String ID = "id";

    /**
     * The identifier for the title of an item. This could be the title
     * of a song, a recording, a photo etc. This identifier is valid for all 
     * kinds of content.<br> Queries for <code>TITLE</code> should always return a String.
     * <br>
     * <p>The value of this key is "dc:title".</p>
     */
    public static final String TITLE = "dc:title";

    /**
     * This identifies the <code>CREATOR</code> of a piece of content.
     * In the case of e.g., MP3s, this maps to the 'Artist' ID3 tag, In case of a 
     * recording/live broadcast, this is the Broadcaster e.g., BBC1.
     * <br> Queries for <code>CREATOR</code> should always return a String.
     * <p>
     * The value of this key is "dc:creator".
     * </p>
     */
    public static final String CREATOR = "dc:creator";

    /**
     * Name of an artist. 
     * <p>The value of this field is <code>"upnp:artist"</code>.</p>
     */
    public static final String ARTIST = "upnp:artist";

    /**
     * Role of an artist in the work. 
     * <p>
     * The value of this field is <code>"upnp:artist@role"</code></p> 
     */
    public static final String ARTIST_AT_ROLE = "upnp:artist@role";

    /**
     * Name of an actor appearing in a video item. 
     * <p>
     * The value of this field is <code>"upnp:actor"</code>.
     * </p>
     */
    public static final String ACTOR = "upnp:actor";

    /**
     * Role of an actor in the work. 
     * <p>
     * The value of this field is <code>"upnp:actor@role"</code>
     * </p> 
     * <br>getMetadata returns a String.
     * @see MetadataNode#getMetadata(String)
     */
    public static final String ACTOR_AT_ROLE = "upnp:actor@role";

    /**
     * Name of an author. 
     * <p>
     * The value of this field is <code>"upnp:author"</code>.
     * </p>
     * <br>getMetadata() will return an array of Strings.
     * @see MetadataNode#getMetadata(String)
     */
    public static final String AUTHOR = "upnp:author";

    /**
     * Role of an author in the work (e&#x2E;g&#x2E; lyrics, music). 
     * <p>
     * The value of this field is <code>"upnp:author@role"</code>
     * </p> 
     * <br>getMetadata returns a String.
     * @see MetadataNode#getMetadata(String)
     */
    public static final String AUTHOR_AT_ROLE = "upnp:author@role";

    /**
     * Name of a producer. 
     * <p>
     * The value of this field is <code>"upnp:producer"</code>.
     * </p>
     * <br>getMetadata() will return an array of Strings.
     * @see MetadataNode#getMetadata(String)
     */
    public static final String PRODUCER = "upnp:producer";

    /**
     * Name of a director. 
     * <p>
     * The value of this field is <code>"upnp:director"</code>.
     * </p>
     * <br>getMetadata() will return an array of Strings.
     * @see MetadataNode#getMetadata(String)
     */
    public static final String DIRECTOR = "upnp:director";

    /**
     * Name of a publisher. 
     * <p>The value of this field is <code>"dc:publisher"</code>.</p>
     * <br>getMetadata() will return an array of Strings.
     * @see MetadataNode#getMetadata(String)
     */
    public static final String PUBLISHER = "dc:publisher";

    /**
     * Name of a contributor. It is recommended that CONTRIBUTOR includes the
     * name of the primary content creator (see Dublin Core 'creator' property)
     * <p>
     * The value of this field is <code>"dc:contributor"</code>.
     * </p>
     * <br>getMetadata() will return an array of Strings.
     * @see MetadataNode#getMetadata(String)
     */
    public static final String CONTRIBUTOR = "dc:contributor";


    /**
     * Name of the genre to which an object belongs. Can be more than one. 
     * <p>
     * The value of this field is <code>"upnp:genre"</code>.
     * </p>
     * <br>getMetadata() will return an array of Strings.
     * @see MetadataNode#getMetadata(String)
     */
    public static final String GENRE = "upnp:genre";

    /**
     * This identifies a <code>ALBUM</code> this piece of content belongs to.
     * For example, in MP3 files this maps to the 'Album' ID3 tag, In case of a
     * recording/live broadcast this could be the series to which it belongs (e.g., Buffy).
     * <p>
     * The value of this field is <code>"upnp:album"</code>
     * </p>
     * <br>getMetadata() will return a String.
     * @see MetadataNode#getMetadata(String)
     * 
     */
    public static final String ALBUM = "upnp:album";

    /**
     * Name of a playlist this object belongs to. Can be more than one. 
     * <p>
     * The value of this field is <code>"upnp:playlist"</code>.
     * </p>
     * <br>getMetadata() will return an array of Strings.
     * @see MetadataNode#getMetadata(String)
     */
    public static final String PLAYLIST = "upnp:playlist";

    /**
     * Reference to album art. Can be more than one. 
     * <p>
     * The value of this field is <code>"upnp:albumArtURI"</code>.
     * </p>
     * Values must be properly escaped URIs as described in
     * [<a href="http://www.ietf.org/rfc/rfc2396.txt">RFC 2396</a>].
     * <br>getMetadata() will return an array of Strings.
     * @see MetadataNode#getMetadata(String)
     */
    public static final String ALBUM_ART = "upnp:albumArtURI";

    /**
     * Reference to artist discography. 
     * <p>
     * The value of this field is <code>"upnp:artistDiscographyURI"</code>.
     * </p>
     * Values must be properly escaped URIs as described in
     * [<a href="http://www.ietf.org/rfc/rfc2396.txt">RFC 2396</a>].
     * <br>getMetadata() will return a String.
     * @see MetadataNode#getMetadata(String)
     */
    public static final String ARTIST_DISCOGRAPHY = "upnp:artistDiscographyURI";

    /**
     * Reference to lyrics of a track or album. 
     * <p>
     * The value of this field is <code>"upnp:lyricsURI"</code>.
     * </p>
     * Values must be properly escaped URIs as described in
     * [<a href="http://www.ietf.org/rfc/rfc2396.txt">RFC 2396</a>].
     * <br>getMetadata() will return an array of Strings.
     * @see MetadataNode#getMetadata(String)
     */
    public static final String LYRICS_REF = "upnp:lyricsURI";

    /**
     * Reference to related resources. 
     * <p>
     * The value of this field is <code>"dc:relation.
     * </p>
     * Values must be properly escaped URIs as described in
     * [<a href="http://www.ietf.org/rfc/rfc2396.txt">RFC 2396</a>].
     * <br>getMetadata() will return an array of Strings.
     * @see MetadataNode#getMetadata(String)
     */
    public static final String RELATION = "dc:relation";

    /**
     * Indicates the type of storage medium used for the content. Potentially useful
     * for user-interface purposes. Allowed values are defined by UPnP and include:
     * <ul><li>"UNKNOWN"</li> 
<li>"DV"</li>
<li>"MINI-DV"</li>
<li>"VHS"</li>
<li>"W-VHS"</li> 
<li>"S-VHS"</li> 
<li>"D-VHS"</li> 
<li>"VHSC"</li> 
<li>"VIDEO8"</li> 
<li>"HI8"</li> 
<li>"CD-ROM"</li> 
<li>"CD-DA"</li>
<li>"CD-R"</li> 
<li>"CD-RW"</li> 
<li>"VIDEO-CD"</li> 
<li>"SACD"</li> 
<li>"MD-AUDIO"</li> 
<li>"MD-PICTURE"</li> 
<li>"DVD-ROM"</li> 
<li>"DVD-VIDEO"</li>
<li>"DVD-R"</li> 
<li>"DVD+RW"</li>
<li>"DVD-RW"</li>
<li>"DVD-RAM"</li> 
<li>"DVD-AUDIO"</li> 
<li>"DAT"</li> 
<li>"LD"</li> 
<li>"HDD"</li>
<li>"SD"</li>
<li>"PC-CARD"</li>
<li>"MMC"</li>
<li>"CF"</li>
<li>"BD"</li>
<li>"MS"</li>
</ul>
<p>
     * The value of this field is <code>"upnp:storageMedium"</code>.
     * </p>
 */
    public static final String STORAGE_MEDIUM = "upnp:storageMedium";

    /**
     * A brief description of the content item. 
     * <p>The value of this field is <code>"dc:description"</code>.</p>
     * @see MetadataNode#getMetadata(String)
     */
    public static final String DESCRIPTION = "dc:description";

    /**
     * A long description of the content item. 
     * <p>The value of this field is <code>"upnp:longDescription"</code>.</p>
     * @see MetadataNode#getMetadata(String)
     */
    public static final String LONG_DESCRIPTION = "upnp:longDescription";

    /**
     * Reference to an icon which can be used to represent the content. 
     * <p>
     * The value of this field is <code>"upnp:icon"</code>.
     * </p>
     * Values must be properly escaped URIs as described in
     * [<a href="http://www.ietf.org/rfc/rfc2396.txt">RFC 2396</a>].
     * @see MetadataNode#getMetadata(String)
     */
    public static final String ICON_REF = "upnp:icon";


    /**
     * Some identification of the region, associated with the 'source' of the object,
     * e&#x2E;g&#x2E; "US", "Latin America", "Seattle".
     * <p>The value of this field is <code>"upnp:region"</code></p>
     * @see MetadataNode#getMetadata(String)
     */
    public static final String REGION = "upnp:region";

    /**
     * Rating of the object's resource, for 'parental control' filtering purposes,
     * such as "R", "PG-13", "X".
     * <p>
     * The value of this field is <code>"upnp:rating"</code>
     * </p>
     * @see MetadataNode#getMetadata(String)
     */
    public static final String RATING = "upnp:rating";



    /**
     * Element Description: Information about rights held in and over the resource.
     * Typically a Rights element will contain a rights management statement for the
     * resource, or reference a service providing such information. Rights information
     * often encompasses Intellectual Property Rights (IPR), Copyright, and various
     * Property Rights. If the rights element is absent, no assumptions can be made
     * about the status of these and other rights with respect to the resource.
     * Guidelines for content creation:
     * The Rights element may be used for either a textual statement or a URL pointing 
     * to a rights statement, or a combination, when a brief statement and a more
     * lengthy one are available.
     * Examples:<br>
     * Rights="Access limited to members"<br>
     * Rights="http://cs-tr.cs.cornell.edu/Dienst/Repository/2.0/Terms"<br>
     * <p>
     * The value of this field is <code>"dc:rights"</code>
     * </p>
     * getMetadata() returns an array of Strings.
     * @see MetadataNode#getMetadata(String)
     */
    public static final String RIGHTS = "dc:rights";

    /**
     * This identifies the <code>CREATION_DATE</code> of a piece of content.
     * In the case of e.g., MP3's this maps to the 'Year' ID3 tag, In case of a
     * recording/live broadcast this is when the content was created. For Images
     * this is the date the photo was made.
     * <br> Queries for <code>CREATION_DATE</code> should always return a java.util.Date.
     * Only the year of the Date might actually be valid (e.g., for MP3s).
     * <p>
     * The value of this field is <code>"dc:date"</code>
     * </p>
     * @see MetadataNode#getMetadata(String)
     */
    public static final String CREATION_DATE = "dc:date";


    /**
     * Language as defined by RFC 3066, e&#x2E;g&#x2E; "en-US".
     * <p>
     * The value of this field is <code>"dc:language"</code>
     * </p>
     * <br>getMetadata() will return an array of Strings.
     * @see MetadataNode#getMetadata(String)
     */
    public static final String LANGUAGE = "dc:language";


    /**
     * Radio station call sign, e&#x2E;g&#x2E; "KSJO".
     * <p>
     * The value of this field is <code>"upnp:radioCallSign"</code>
     * </p>
     * @see MetadataNode#getMetadata(String)
     */
    public static final String RADIO_CALL_SIGN = "upnp:radioCallSign";

    /**
     * Some identification, e&#x2E;g&#x2E; "107&#x2E;7", broadcast frequency of the
     * radio station.
     * <p>
     * The value of this field is <code>"upnp:radioStationID"</code></p>
     * @see MetadataNode#getMetadata(String)
     */
    public static final String RADIO_STATION_ID = "upnp:radioStationID";

    /**
     * Radio station frequency band. Recommended values are "AM", "FM", "Shortwave",
     * "Internet", "Satellite". Vendor's may extend this. 
     * <p>
     * The value of this field is <code>"upnp:radioBand"</code>
     * </p>
     * @see MetadataNode#getMetadata(String)
     */
    public static final String RADIO_BAND = "upnp:radioBand";

    /**
     * Used for identification of tuner channels themselves or information associated
     * with a piece of recorded content.
     * <p>
     * The value of this field is <code>"upnp:channelNr"</code>
     * </p>
     * <b>getMetadata() returns an Integer.
     * @see MetadataNode#getMetadata(String)
     */
    public static final String CHANNEL_NUMBER = "upnp:channelNr";

    /**
     * Used for identification of channels themselves, or information associated with a
     * piece of recorded content.
     * <p>
     * The value of this field is <code>"upnp:channelName"</code>
     * </p>
     * @see MetadataNode#getMetadata(String)
     */
    public static final String CHANNEL_NAME = "upnp:channelName";

    /**
     * Start time of a scheduled program.
     * <p>
     * The value of this field is <code>"upnp:scheduledStartTime"</code>
     * </p>
     * getMetadata() returns java.util.Date.
     * @see MetadataNode#getMetadata(String)
     */
    public static final String SCHEDULED_START_TIME = "upnp:scheduledStartTime";

    /**
     * End time of a scheduled program.
     * <p>
     * The value of this field is <code>"upnp:scheduledEndTime"</code>
     * </p>
     * getMetadata() returns java.util.Date.
     * @see MetadataNode#getMetadata(String)
     */
    public static final String SCHEDULED_END_TIME = "upnp:scheduledEndTime";

    /**
     * DVD region code.
     * <p>
     * The value of this field is <code>"upnp:DVDRegionCode"</code>
     * </p>
     * <b>getMetadata() returns an Integer.
     * @see MetadataNode#getMetadata(String)
     */
    public static final String DVD_REGION_CODE = "upnp:DVDRegionCode";

    /**
     * Original track number on a CD or other medium.
     * <p>
     * The value of this field is <code>"upnp:originalTrackNumber"</code>
     * </p>
     * <b>getMetadata() returns an Integer.
     * @see MetadataNode#getMetadata(String)
     */
    public static final String TRACK_NUMBER = "upnp:originalTrackNumber";

    /**
     * Unique identifier of an audio CD (e&#x2E;g&#x2E; freedb or cddb id).
     * <p>
     * The value of this field is <code>"upnp:toc"</code>
     * </p>
     * @see MetadataNode#getMetadata(String)
     */
    public static final String MEDIA_ID = "upnp:toc";


    /**
     * General-purpose tag where a user can annotate an object with some user-specific
     * information.
     * <p>
     * The value of this field is <code>"upnp:userAnnotation"</code>
     * </p>
     * <br>getMetadata() will return an array of Strings.
     * @see MetadataNode#getMetadata(String)
     */
    public static final String COMMENTS = "upnp:userAnnotation";

    /**
     * Property indicating total storage on a storage container.
     * <p>
     * The value of this field is <code>"upnp:storageTotal"</code>
     * </p>
     * <br>getMetadata() will return a Long.
     * @see MetadataNode#getMetadata(String)
     */
    public static final String PROP_STORAGE_TOTAL = "upnp:storageTotal";

    /**
     * Property indicating current storage space available on a storage container.
     * <p>
     * The value of this field is <code>"upnp:storageFree"</code>
     * </p>
     * <br>getMetadata() will return a Long.
     * @see MetadataNode#getMetadata(String)
     */    
    public static final String PROP_STORAGE_FREE = "upnp:storageFree";
    
    /**
     * An identifier for the parent of this object.
     *  
     * <p>
     * The value is <code>didl-lite:</code>(object)<code>@"parentID"</code>
     * </p>
     */
      public static final String PARENT_ID = "parentID";
}
