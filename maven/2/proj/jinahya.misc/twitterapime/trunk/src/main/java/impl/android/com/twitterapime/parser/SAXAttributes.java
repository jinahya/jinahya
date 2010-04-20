/*
 * Attributes.java
 * 21/11/2009
 * Twitter API Micro Edition
 * Copyright(c) Ernandes Mourao Junior (ernandes@gmail.com)
 * All rights reserved
 */
package impl.android.com.twitterapime.parser;

import com.twitterapime.parser.Attributes;

/**
 * <p>
 * This class defines a list of SAX attributes.
 * </p>
 * 
 * @author Ernandes Mourao Junior (ernandes@gmail.com)
 * @version 1.0
 * @since 1.1
 */
public final class SAXAttributes implements Attributes {
	/**
	 * <p>
	 * List of attributes.
	 * </p>
	 */
	private org.xml.sax.Attributes attrs;

	/**
	 * <p>
	 * Create an instance of SAXAttributes class.
	 * </p>
	 */
	public SAXAttributes() {
	}

	/**
	 * <p>
	 * Create an instance of SAXAttributes class.
	 * </p>
	 * @param attrs Attributes.
	 */
	public SAXAttributes(org.xml.sax.Attributes attrs) {
		loadAttributes(attrs);
	}
	
	/**
	 * <p>
	 * Load all the attributes from the tag being read.
	 * </p>
	 * @param attrs Attributes.
	 */
	public void loadAttributes(org.xml.sax.Attributes attrs) {
		this.attrs = attrs;
	}

	/**
	 * @see com.twitterapime.parser.Attributes#getIndex(java.lang.String)
	 */
	public int getIndex(String qName) {
		return attrs.getIndex(qName);
	}

	/**
	 * @see com.twitterapime.parser.Attributes#getLength()
	 */
	public int getLength() {
		return attrs.getLength();
	}

	/**
	 * @see com.twitterapime.parser.Attributes#getQName(int)
	 */
	public String getQName(int index) {
		return attrs.getQName(index);
	}

	/**
	 * @see com.twitterapime.parser.Attributes#getType(int)
	 */
	public String getType(int index) {
		return attrs.getType(index);
	}

	/**
	 * @see com.twitterapime.parser.Attributes#getValue(java.lang.String)
	 */
	public String getValue(String qName) {
		return attrs.getValue(qName);
	}

	/**
	 * @see com.twitterapime.parser.Attributes#getValue(int)
	 */
	public String getValue(int index) {
		return attrs.getValue(index);
	}
}