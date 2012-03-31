/*
 * @(#)ContentDescriptor.java	1.13 98/03/28
 *
 * Copyright 1996-1998 by Sun Microsystems, Inc.,
 * 901 San Antonio Road, Palo Alto, California, 94303, U.S.A.
 * All rights reserved.
 *
 * This software is the confidential and proprietary information
 * of Sun Microsystems, Inc. ("Confidential Information").  You
 * shall not disclose such Confidential Information and shall use
 * it only in accordance with the terms of the license agreement
 * you entered into with Sun.
 */

package javax.media.protocol;

/**
 * A <CODE>ContentDescriptor</CODE> identifies media data containers.
 *
 * @see SourceStream
 * @version 1.13, 98/03/28.
 */

public class ContentDescriptor {

    static public final String CONTENT_UNKNOWN = "UnknownContent";

    /**
     * Obtain a string that represents the content-name
     * for this descriptor.
     *
     * @return The content-type name.
     */
    public String getContentType() {
	return typeName;
    }

    protected String typeName;

    /** 
     * Create a content descriptor with the specified name.
     * <p>
     * To create a <CODE>ContentDescriptor</CODE> from a MIME type, use
     * the <code>mimeTypeToPackageName</code> static member.
     *
     * @param cdName The name of the content-type.
     */
    public ContentDescriptor(String cdName) {
	typeName = cdName;
    }

    /**
     * Map a MIME content-type to an equivalent string
     * of class-name components.
     * <p>
     * The MIME type is mapped to a string by:
     * <ol>
     * <li>Replacing all slashes with a period.
     * <li>Converting all alphabetic characters to lower case.
     * <li>Converting all non-alpha-numeric characters other than periods
     * to underscores (_).
     * </ol>
     * <p>
     * For example, "text/html" would
     * be converted to "text.html"
     *
     * @param mimeType The MIME type to map to a string.
     */
    static final public  String mimeTypeToPackageName(String mimeType) {

	// All to lower case ...
	mimeType = mimeType.toLowerCase();

	// ... run through each char and convert
	//            '/'                ->  '.'
	//             !([A-Za-z0--9])   ->  '_'
	int len = mimeType.length();
	char nm[] = new char[len];
	mimeType.getChars(0, len, nm, 0);
	for (int i = 0; i < len; i++) {
	    char c = nm[i];
	    if (c == '/') {
		nm[i] = '.';
	    } else if (!('A' <= c && c <= 'Z' ||
		       'a' <= c && c <= 'z' ||
		       '0' <= c && c <= '9')) {
		nm[i] = '_';
	    }
	}
	
	return new String(nm);
    }

}
