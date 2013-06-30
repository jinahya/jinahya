package org.ocap.hn.content;

/**
 * This abstract class represents access to standardized metadata identifiers.
 * Each identifier, e.g. "title", can be used to search for corresponding
 * metadata in a ContentList.  The set of identifiers returned by the 
 * #getIdentifiers method SHALL contain the <code>PROPRIETARY_DATA</code>
 * identifier and MAY contain identifiers defined in other OCAP HN profiles,
 * e.g. UPnP.
 */
public abstract class MetadataIdentifiers
{

    /**
     * This identifies proprietary data. The Object returned when using this
     * as a Metadata identifier is defined by the application creating the
     * metadata.
     * <p>
     * The value of this field is an OCAP defined string
     * <code>"ocap:proprietaryData"</code>. If the proprietary data is an
     * array of bytes the data should be transported as a base 64 String.
     * </p>
     */
    public static final String PROPRIETARY_DATA = "ocap:proprietaryData";

    /**
     * Gets all metadata identifiers for all HN profiles supported by this
     * Host device.
     * 
     * @return Array of Metadata identifiers.
     */
    public static String [] getIdentifiers()
    {
        return null;
    }

    /**
     * Gets the number of identifiers in the set of supported identifiers
     * returned by the #getIdentifiers method.
     * 
     * @return Number of supported metadata identifiers.
     */
    public static int getNumberOfIdentifiers()
    {
        return 0;
    }

    /**
     * Indicates if the parameter identifier is contained within the set of
     * supported identifiers.
     * 
     * @param identifier Name of the identifier to search for.
     * 
     * @return True if the identifier is supported, otherwise returns false.
     */
     public static boolean contains(String identifier)
     {
         return false;
     }

 }
