package org.ocap.hn.content;

import java.io.IOException;
import java.util.Date;
import org.ocap.hn.ContentServerNetModule;
import org.ocap.storage.ExtendedFileAccessPermissions;

/**
 * This interface represents a basic content entry. Each ContentEntry instance
 * can only be contained in one ContentContainer and the implementation SHALL
 * create a new ContentEntry for equal entries placed in multiple
 * ContentContainer instances.
 */
public interface ContentEntry {

    /**
     * Returns the ID of this ContentEntry. The format of this string ID
     * is implementation and protocol mapping dependent.
     * 
     * @return The ID of content entry.
     */
    public String getID();

    /**
     * Gets the server where this ContentEntry is located.
     * 
     * @return The server housing this container.
     */
    public ContentServerNetModule getServer();

    /**
     * Deletes this ContentEntry. This is a local delete only. If the #isLocal
     * method returns false, this method SHALL throw an exception. This method
     * does not delete any content associated with this content entry.
     * 
     * @return True if the ContentEntry was deleted, otherwise returns false.
     * 
     * @throws SecurityException
     *             if the calling application does not have write
     *             ExtendedFileAccessPermission for this entry.
     * @throws IOException
     *             if the entry is not local.
     */
    public boolean deleteEntry() throws IOException;

    /**
     * Returns the {@link ContentContainer} this ContentEntry belongs to.
     * 
     * This method SHALL return null if this ContentEntry represents a
     * root container.
     * 
     * If it is determined that this ContentEntry has a parent container, 
     * but the implementation does not have sufficient local cached 
     * information to construct the ContentContainer, this method SHALL
     * throw an IOException.
     * 
     * @return The parent ContentContainer.
     * 
     * @throws IOException
     *          if the implementation does not have sufficient local
     *          cached information to construct the parent ContentContainer
     */
    public ContentContainer getEntryParent() throws IOException;

    /**
     * Returns the ID of {@link ContentContainer} this ContentEntry belongs to.
     * 
     * This method SHALL return "-1" if this ContentEntry represents a root
     * container. This method SHALL return null if the parent ID is unknown.
     * 
     * @see org.ocap.hn.content.ContentEntry#getID
     * @see org.ocap.hn.content.ContentEntry#getEntryParent
     * 
     * @return the ID of this entry's parent container
     */
    public String getParentID();
    
    /**
     * Gets the size of the content associated with this ContentEntry..
     * 
     * @return The content size in bytes or -1 if unknown.
     */
    public long getContentSize();

    /**
     * Gets the creation date of the content associated with this ContentEntry.
     * 
     * @return The Date the content was created or null if unknown.
     */
    public Date getCreationDate();

    /**
     * Gets the file permissions of this ContentEntry, or null if unknown.
     * 
     * @return The extended file access permissions of this ContentEntry or 
     *         null if unknown.
     */
    public ExtendedFileAccessPermissions getExtendedFileAccessPermissions();

    /**
     * Gets the metadata for this ContentEntry.
     * 
     * @return Root MetadataNode.
     */
    public MetadataNode getRootMetadataNode();

    /**
     * Returns true if this content entry is on the local device, false if it is
     * hosted by another device on the network.
     * 
     * @return true if the content is local, false otherwise
     */
    public boolean isLocal();

}
