/*
 *  Copyright 2010 Jin Kwon.
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 *  under the License.
 */

package jinahya.xml.kxml2.kdom;


import org.kxml2.kdom.Document;
import org.kxml2.kdom.Element;
import org.kxml2.kdom.Node;

import org.xmlpull.v1.XmlPullParserException;


/**
 *
 * @author <a href="mailto:jinahya@gmail.com">Jin Kwon</a>
 */
public class ExtendedDocument extends Document {


    /**
     * The default encoding. UTF-8.
     */
    public static final String DEFAULT_ENCODING = "UTF-8";


    /**
     * Creates a new 'UTF-8-encoded instance.
     */
    public ExtendedDocument() {
        this(DEFAULT_ENCODING);
    }


    /**
     * Creates a new 'UTF-8-encoded instance.
     */
    public ExtendedDocument(final String encoding) {
        super();

        setEncoding(encoding);
    }


    @Override
    public void setEncoding(final String encoding) {
        super.setEncoding(encoding == null ? DEFAULT_ENCODING : encoding);
    }


    /**
     *
     * @param filter
     * @throws XmlPullParserException
     */
    public void filter(final ElementFilter filter)
        throws XmlPullParserException {

        if (filter == null) {
            throw new IllegalArgumentException("'filter' is null");
        }

        filter(filter, getRootElement()); // NullPointerException
    }


    /**
     * Pass given <code>element</code> through given <code>filter</code>.
     *
     * @param filter the filter
     * @param element element to be filtered
     * @throws XmlPullParserException if any error occurs
     */
    private void filter(final ElementFilter filter, final Element element)
        throws XmlPullParserException {

        filter.filter(element);

        final int childCount = element.getChildCount();
        for (int i = 0; i < childCount; i++) {
            if (Node.ELEMENT != element.getType(i)) {
                continue;
            }
            filter(filter, element.getElement(i));
        }
    }
}
