package org.dvb.si; 
/**
 * An interface that can be implemented by objects representing DVB services. 
 * Allows applications to obtain the textual service identifiers related to 
 * a service. 
 * @since MHP1.0.1 
 */ 
public interface TextualServiceIdentifierQuery
{ 
	/**
	 * Returns the textual service identifiers related to this object. 
	 * @return an array of String objects containing the textual service 
	 * identifiers or null if none are present.
	 * @since MHP1.0.1 
	 */
	public String[] getTextualServiceIdentifiers(); 
}


