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


import java.util.Vector;

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
    public void filter(final Document document) {

        if (document == null) {
            throw new IllegalArgumentException(
                "param:0:" + Document.class + ": is null");
        }

        filter(LinkedElement.newInstance(document));
    }


    /**
     * Pass given <code>element</code> through given <code>filter</code>.
     *
     * @param element element to be filtered
     * @throws XmlPullParserException if any error occurs
     */
    private void filter(final LinkedElement element) {

        if (element == null) {
            throw new IllegalArgumentException(
                "param:0:" + Element.class + ": is null");
        }

        if (startFiltering(element)) {
            final Vector<LinkedElement> children = element.children();
            for (int i = 0; i < children.size(); i++) {
                filter(children.elementAt(i));
            }
        }

        finishFiltering(element);
    }


    /**
     * Starts filtering for given <code>element</code>.
     *
     * @param element element to be filtered.
     * @return true if child elements have to be filtered. false otherwise.
     */
    protected abstract boolean startFiltering(LinkedElement element);


    /**
     * Finishes filtering for given <code>element</code>.
     *
     * @param element the element to be filtered.
     */
    protected abstract void finishFiltering(LinkedElement element);
}
