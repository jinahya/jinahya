package org.ocap.hn.content;

import java.io.IOException;
import java.io.File;
import java.util.Date;
import java.util.Enumeration;
import org.ocap.hn.content.navigation.ContentDatabaseFilter;
import org.ocap.hn.content.navigation.ContentList;
import org.ocap.storage.ExtendedFileAccessPermissions;

/**
 * This class represents a container that contains one or more content entries.
 * Can contain children containers.
 */

public interface ContentContainer extends ContentEntry
{
    /**
     * Represents the base container class.
     */
	public static final String CONTAINER = "object.container";

    /**
     * Represents the base album container.
     */
    public static final String ALBUM_CONTAINER = "object.container.album";

    /**
     * Represents a photo album container. In addition to being an  ALBUM_CONTAINER container may be a photo album.
     */
    public static final String ALBUM_CONTAINER_PHOTO = "object.container.album.photoAlbum";

    /**
     * Represents a music album container. In addition to being an 
     * ALBUM_CONTAINER container may be a music album.
     */
    public static final String ALBUM_CONTAINER_MUSIC = "object.container.album.musicAlbum";
    
    /**
     * Represents an unordered collection of 'objects' that "belong" to the 
     * genre.
     */
    public static final String GENRE_CONTAINER = "object.container.genre";
    
    /**
     * Represents a music genre container. In addition to being a 
     * GENRE_CONTAINER a container may be a music genre container
     */
    public static final String GENRE_CONTAINER_MUSIC = "object.container.genre.musicGenre";
    
    /**
     * Represents a movie genre container. In addition to being a 
     * GENRE_CONTAINER a container may be a movie genre container
     */
    public static final String GENRE_CONTAINER_MOVIE = "object.container.genre.movieGenre";
    
    /**
     * Represents a collection of objects.
     */
    public static final String PLAYLIST_CONTAINER = "object.container.playlistContainer";
    
    /**
     * Represents an unordered collection of 'objects' that "belong" 
     * to the people.
     */
    public static final String PERSON_CONTAINER = "object.container.person";
    
    /**
     * Represents a music artist person container. In addition to being a 
     * PERSON_CONTAINER a container may be a music artist.
     */
    public static final String PERSON_CONTAINER_MUSIC_ARTIST = "object.container.person.musicArtist";
    
    /**
     * Represents a potentially heterogeneous collection of storage media.
     */
    public static final String STORAGE_SYSTEM_CONTAINER = "object.container.storageSystem";
    
    /**
     * Represents all, or a partition of, some physical storage unit of a single type.
     */
    public static final String STORAGE_VOLUME_CONTAINER = "object.container.storageVolume";
    
    /**
     * Represents all, or a partition of some physical storage unit of a single type.
     */
    public static final String STORAGE_FOLDER_CONTAINER = "object.container.storageFolder";

    /**
     * Represents the (extended tuner) channel group container class.
     */
    public static final String CHANNEL_GROUP_CONTAINER = "object.container.channelGroup";

    /**
     * Returns the container class of this container.
     * 
     * @see #ALBUM_CONTAINER
     * @see #ALBUM_CONTAINER_MUSIC
     * @see #ALBUM_CONTAINER_PHOTO
     * @see #GENRE_CONTAINER
     * @see #GENRE_CONTAINER_MUSIC
     * @see #GENRE_CONTAINER_MOVIE
     * @see #PLAYLIST_CONTAINER
     * @see #PERSON_CONTAINER
     * @see #PERSON_CONTAINER_MUSIC_ARTIST
     * @see #STORAGE_SYSTEM_CONTAINER
     * @see #STORAGE_VOLUME_CONTAINER
     * @see #STORAGE_FOLDER_CONTAINER
     * @see #CHANNEL_GROUP_CONTAINER
     * 
     * @return The content class of this item.
     */
    public String getContainerClass();


    /** Returns an array of all {@link ContentEntry}
     * in this <code>ContentContainers</code> including other
     * <code>ContentContainers</code>.  Returns ContentEntry objects stored
     * in local cache only; does not cause network activity.
     * 
     * @return array containing all entries of this ContentContainers
     */
    public ContentEntry[] toArray();

    /**
     * Checks whether the given {@link ContentEntry} is in this ContentContainer
     * in local cache only.
     * 
     * @param entry To search for in this ContentEntry.
     * 
     * @return true if the ContentEntry is contained in this container, 
     *      otherwise returns false.
     */
    public boolean contains(ContentEntry entry);

    /**
     * Returns the ContentEntry associated with the given ID in this container,
     * or null if no entry is found.
     * 
     * This method SHALL recursively search this container and any sub-containers.
     * 
     * This method searches local cache only; does not cause network activity.
     * 
     * @param ID String ID of the ContentEntry to return
     * 
     * @return the associated ContentEntry.
     * 
     * @see org.ocap.hn.content.ContentEntry#getID
     */
    public ContentEntry getEntry(String ID);
    
    /**
     * Returns the n<sup>th</sup> ContentEntry in this container, from local
     * cache only; does not cause network activity.
     * 
     * @param n Index of the entry to get.
     * 
     * @return the n<sup>th</sup> ContentEntry.
     * 
     * @throws ArrayIndexOutOfBoundsException if the n<sup>th</sup> value does
     *      not exist. 
     */
    public ContentEntry getEntry(int n); 

    /**
     * Gets an Enumeration over all entries in this ContentContainer, from local
     * cache only; does not cause network activity.
     * 
     * @return Enumeration over all entries in this ContentContainers, or null
     *      if there are no entries.
     */
    public Enumeration getEntries();

    /**
     * Gets the index of the specified ContentEntry, from local
     * cache only; does not cause network activity. 
     * 
     * @param n The index of the ContentEntry to search for.
     * 
     * @return The index of the ContentEntry or -1 if it doesn't exist in this
     *      container.
     */
    public int getIndex(ContentEntry n);


    /**
     * Creates a new ContentItem representing a local file as a child of this 
     * ContentContainer. If this ContentContainer #isLocal method returns false 
     * this method will return false.
     * 
     * The resulting ContentItem will contain a single ContentResource containing
     * the content parameter passed to this method.
     * 
     * @param content The file containing the content to be represented
     * @param name The name of the new ContentItem.
     * @param permissions Access permissions of the new ContentContainer.
     * 
     * @return True if a new ContentItem has been created, otherwise
     *      return false; specifically, returns false if this container
     *      is a channel group container.
     * 
     * @throws SecurityException if the caller does not have 
     *         HomeNetPermission("contentmanagement"), or if the caller
     *         does not have write permission on this container.
     */
    public boolean createContentItem(
    							File content,
                                String name,
                                ExtendedFileAccessPermissions permissions);

    /**
     * Creates a new ContentContainer as a child of this ContentContainer. If this 
     * ContentContainer #isLocal method returns false this method will return false.
     *
     * This ContentContainer may not contain ChannelContentItem entries.
     * 
     * Can be used to create a directory structure.
     * 
     * @param name The name of the new ContentContainer.
     * @param permissions Access permissions of the new ContentContainer.
     * 
     * @return true if a new ContentContainer has been created, otherwise
     *      returns false.
     * 
     * @throws SecurityException if the caller does not have 
     *         HomeNetPermission("contentmanagement"), or if the caller
     *         does not have write permission on this container.
     */
    public boolean createContentContainer(
                                String name,
                                ExtendedFileAccessPermissions permissions);

    /**
     * Creates a new channel group <code>ContentContainer</code> as a child of this 
     * ContentContainer, when the host device is capable of supporting
     * tuner requests from the home network. This channel group only
     * contains <code>ChannelContentItem</code> instances representing 
     * broadcast channels that can be tuned by the host device.
     * 
     * If this ContentContainer #isLocal method 
     * returns false, this method will return null.
     * 
     * If the ContentServerNetModule that contains this ContentContainer
     * is not prepared to support tuners, this method will return null.
     *
     * @param name The name of the new ContentContainer.
     * @param permissions Access permissions of the new ContentContainer.
     * 
     * @return ContentContainer if a new ContentContainer has been created, 
     * otherwise returns null.  
     * 
     * @throws SecurityException if the caller does not have 
     *         HomeNetPermission("contentmanagement"), or if the caller
     *         does not have write permission on this container.
     */
    public ContentContainer createChannelGroupContainer(
                                String name,
                                ExtendedFileAccessPermissions permissions);

    /**
     * Returns a ContentList which contains the filtered ContentItems of this
     * ContentContainer.  If the traverse parameter is true the ContentItems
     * of all its children ContentContainers is included.  The list returned
     * is filtered by the filter parameter. If the filter is null all
     * items are returned.
     * 
     * @param filter A ContentDatabaseFilter to filter the ContentItems. If
     *      the filter is null all entries are returned
     * @param traverse If true entries in the sub-containers are returned, 
     *      otherwise only entries in this ContentContainer are returned.
     * 
     * @return a ContentList filtered by the {@link ContentDatabaseFilter}
     */
    public ContentList getEntries(ContentDatabaseFilter filter,
                                  boolean traverse);

    /**
     * Gets the name of this ContentContainer.
     *
     * @return The name of this ContentContainer.
     * 
     * @see ContentEntry#getID()
     */
    public String getName();

    /**
     * Deletes this ContentContainer if and only if it is empty.
     * This method removes the content container from its parent. 
     * This method returns false if this is a root container.
     * This method deletes a local ContentContainer only. If the #isLocal
     * method returns false an exception is thrown.
     * 
     * @return True if this ContentContainer was deleted, otherwise returns
     *      false.
     * 
     * @throws java.lang.SecurityException if the application is denied to
     *      perform the action
     * @throws java.io.IOException if this ContentContainer is not local.
     */
    public boolean delete() throws IOException;

    /**
     * Deletes all the ContentEntry objects in this container except for
     * ContentContainer entries. This method deletes local ContentEntry
     * instances only. If the #isLocal method returns false, an exception is 
     * thrown.
     * 
     * @return true if all of the ContentEntry objects required to be deleted are   
     * deleted, otherwise returns false (e.g. ContentContainer entries
     * are not required to be deleted).
     * 
     * @throws SecurityException if the caller does not have 
     *         HomeNetPermission("contentmanagement"), or if the caller
     *         does not have write permission on this container or
     *         any entries contained in this container (except for
     *         ContentContainer entries).
     *         
     * @throws java.io.IOException if this ContentContainer is not local.
     */
    public boolean deleteContents() throws IOException;

    /**
     * Deletes this ContentContainer and all of the ContentEntry objects in this
     * container.  Calls the {@link ContentEntry#deleteEntry} method on each
     * ContentEntry in a recursive manner.  This method deletes local
     * ContentEntry instances only.  If the #isLocal method returns false, 
     * an exception is thrown.
     * 
     * If a SecurityException is thrown due to insufficient write access permissions 
     * on any entry contained within this ContentContainer, this method MAY delete 
     * a partial subset of the entries contained within.
     * 
     * @return true if this ContentContainer and all of the ContentEntry objects in this container were
     *      deleted, otherwise returns false.     
     * 
     * @throws SecurityException if the caller does not have 
     *         HomeNetPermission("contentmanagement"), or if the caller
     *         does not have write permission on this container or
     *         any entries contained in this container.
     *         
     * @throws java.io.IOException if this ContentContainer is not local.
     *   
     **/
    public boolean deleteEntry() throws IOException;

    /**
     * If the recursive parameter is true, this method behaves in a manner
     * equivalent to {@link #deleteEntry}.  If the recursive parameter is false,
     * this method behaves in a manner equivalent to {@link #deleteContents}. 
     * This method deletes local ContentEntry instances only. If the #isLocal
     * method returns false, an exception is thrown.
     * 
     * If a SecurityException is thrown due to insufficient write access permissions 
     * on any entry contained within this ContentContainer, this method MAY delete 
     * a partial subset of the entries contained within.
     * 
     * @param recursive if true all entries and their entries are to be deleted.
     *
     * @return True if all ContentEntry objects that are required to be deleted are
     *      deleted, otherwise returns false.
     * 
     * @throws SecurityException if the caller does not have 
     *         HomeNetPermission("contentmanagement"), or if the caller
     *         does not have write permission on this container or
     *         any entries contained in this container.
     *         
     * @throws java.io.IOException if this ContentContainer is not local.
     *  
     * @see #deleteContents()
     * @see #delete() 
     **/
    public boolean deleteRecursive(boolean recursive)
                                    throws IOException;

    /**
     * Adds a ContentEntry to this ContentContainer.  Can only add local
     * ContentEntry objects to local ContentContainer. If this entry
     * is already has a parent ContentContainer, it will be removed
     * from that container.
     * 
     * @param entry the content entry to be added to this container
     * 
     * @return True if the entry was added.  Returns false if the isLocal
     *      method of this ContentContainer or the parameter ContentEntry
     *      returns false.  Returns false if this ContentContainer is a channel
     *      group container, and the entry is not a ChannelContentItem.     
     *
     * @throws IllegalStateException if this ContentContainer does not have
     *      a parentID property, i.e., this ContentContainer is not added 
     *      to the CDS.
     *      
     * @throws SecurityException if the caller does not have 
     *         HomeNetPermission("contentmanagement"), or if the caller
     *         does not have write permission on this container.
     */
    public boolean addContentEntry(ContentEntry entry);

    /**
     * Adds ContentEntry objects to this ContentContainer.  Can only add local
     * ContentEntry objects to local ContentContainer. If any entry passed to
     * this method already has a parent ContentContainer, it will be removed
     * from that container.
     * 
     * @param entries the content entries to be added to this container
     * 
     * @return True if the entries were added.  Returns false if the isLocal
     *      method of this ContentContainer or the parameter ContentEntry
     *      returns false.  Returns false if this container is a channel group 
     *      container and ANY entry in the input argument is NOT a 
     *      ChannelContentItem.
     *      
     * @throws IllegalStateException if this ContentContainer does not have
     *      a parentID property, i.e., this ContentContainer is not added 
     *      to the CDS.
     *      
     * @throws SecurityException if the caller does not have 
     *         HomeNetPermission("contentmanagement"), or if the caller
     *         does not have write permission on this container.
     */
    public boolean addContentEntries(ContentEntry[] entries);    
    
    /**
     * Removes a ContentEntry from this ContentContainer.  Can only remove
     * local ContentEntry objects from local ContentContainers.
     * When the ContentEntry parameter is a ContentContainer, all of its 
     * ContentEntry objects are removed from the parameter. 
     * For entries that are ContentContainer objects, a possible implementation
     * is a recursive traversal where these objects are removed in a bottom-up
     * fashion by calling this method on each one.
     *
     * @param entry the content entry to be removed from this container
     * 
     * @return True if the entry was removed.  Returns false if the isLocal
     *      method of this ContentContainer or the parameter ContentEntry
     *      is not contained in this container.
     *      
     * @throws IllegalArgumentException if the ContentEntry parameter is a NetRecordingEntry 
     *       which contains one or more RecordingContentItems.
     *
     * @throws SecurityException if the caller does not have 
     *         HomeNetPermission("contentmanagement"), or if the caller
     *         does not have write permission on this container.
     */
    public boolean removeContentEntry(ContentEntry entry);

    /**
     * Removes ContentEntry objects from this ContentContainer.  Can only 
     * remove local ContentEntry objects from local ContentContainer. If any
     * ContentEntry is not contained within this container, this method
     * will return false and no entries will be removed.
     * When the ContentEntry parameter is a ContentContainer, all of its 
     * ContentEntry objects are removed from the parameter. 
     * For entries that are ContentContainer objects, a possible implementation
     * is a recursive traversal where these objects are removed in a bottom-up
     * fashion by calling removeContentEntry method on each one.
     * 
     * @param entries the content entries to be removed from this container
     * 
     * @return True if the entries were removed.  Returns false if the isLocal
     *      method of this ContentContainer or if any of the ContentEntry
     *      objects are not contained in this container.
     *
     * @throws IllegalArgumentException if the parameter includes a NetRecordingEntry 
     *       which contains one or more RecordingContentItems.
     *
     * @throws SecurityException if the caller does not have 
     *         HomeNetPermission("contentmanagement"), or if the caller
     *         does not have write permission on this container.    
     */
    public boolean removeContentEntries(ContentEntry[] entries);
    
    /**
     * Gets the size of the ContentContainer and all its content including
     * all its contained ContentContainer objects.  Note that the size may
     * have changed during the call to this method.
     * 
     * @return The content size in bytes or -1 if the size is indeterminate. 
     */
    public long getContentSize();

    /**
     * Returns the creation date of this ContentContainer.
     * 
     * @return The Date the content was created or null if the creation date
     *      is indeterminate.
     */
    public Date getCreationDate();

    /**
     * Gets the ExtendedFileAccessPermissions of this ContentContainer.
     * 
     * @return The ExtendedFileAccessPermission.
     */
    public ExtendedFileAccessPermissions getExtendedFileAccessPermissions();

    /**
     * Gets the number of ContentEntry objects in this ContentContainer.  Does
     * not include component count of entries within ContentContainer objects
     * contained in this ContentContainer.
     * 
     * @return Number of entries.
     */
    public int getComponentCount();

    /**
     * Returns an empty indication.
     * 
     * @return True if this ContentContainer does not contain any
     *      ContentEntry objects, otherwise returns false.
     */
    public boolean isEmpty();    
    
}
