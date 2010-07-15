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
public abstract class ElementFilter {


    /**
     *
     * @param document
     * @throws XmlPullParserException
     */
    public void filter(final Document document) throws XmlPullParserException {

        if (document == null) {
            throw new IllegalArgumentException(
                "param:0:" + Document.class + ": is null");
        }

        filter(document.getRootElement());
    }


    /**
     * Pass given <code>element</code> through given <code>filter</code>.
     *
     * @param element element to be filtered
     * @throws XmlPullParserException if any error occurs
     */
    private void filter(final Element element) throws XmlPullParserException {

        if (element == null) {
            throw new IllegalArgumentException(
                "param:0:" + Element.class + ": is null");
        }

        if (!startFiltering(element)) {
            final int childCount = element.getChildCount();
            for (int i = 0; i < childCount; i++) {
                if (Node.ELEMENT == element.getType(i)) {
                    filter(element.getElement(i));
                }
            }
        }

        finishFiltering(element);
    }


    /**
     * Starts filtering for given <code>element</code>.
     *
     * @param element element to be filtered.
     * @return true if child elements have to be filtered. false otherwise.
     * @throws XmlPullParserException if any error occurs.
     */
    protected abstract boolean startFiltering(Element element)
        throws XmlPullParserException;


    /**
     * Finishes filtering for given <code>element</code>.
     *
     * @param element the element to be filtered.
     * @throws XmlPullParserException if any error occurs.
     */
    protected abstract void finishFiltering(Element element)
        throws XmlPullParserException;
}
