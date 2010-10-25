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

package jinahya.xml;


/**
 *
 * @author <a href="mailto:jinahya@gmail.com">Jin Kwon</a>
 * @param <E>
 */
public interface ElementLocator<E> {


    /**
     * Counts child elements.
     *
     * @param name child element name
     * @return children count
     */
    int count(String name);


    /**
     * Counts child elements.
     *
     * @param namespace child element namespace
     * @param name child element name
     * @return children count
     */
    int countNS(String namespace, String name);


    /**
     * Locate to the root.
     *
     * @return self
     */
    ElementLocator<E> root();


    /**
     * Locate to the parent of the current element.
     *
     * @return self
     */
    ElementLocator<E> parent();


    /**
     * Locate to the given child element.
     *
     * @param name child element name
     * @param index child index
     * @return self
     */
    ElementLocator<E> child(String name, int index);


    /**
     *
     * @param namespace child element namespace
     * @param name child element's name
     * @param index child element index
     * @return self
     */
    ElementLocator<E> childNS(String namespace, String name, int index);


    /**
     * Append new child with value.
     *
     * @param name child element name
     * @return self
     */
    ElementLocator<E> child(String name);


    /**
     * Append new child element with value.
     *
     * @param namespace child element namespace
     * @param name child element name
     * @param value child element text value maybe null
     * @return self
     */
    ElementLocator<E> childNS(String namespace, String name);


    /**
     * Returns the text value.
     *
     * @return text content or null
     */
    String text();


    /**
     *
     * @param parent
     * @return text content or null
     */
    String text(boolean parent);


    /**
     *
     * @param value
     * @return
     */
    ElementLocator<E> text(String value);


    /**
     *
     * @param value
     * @return
     */
    ElementLocator<E> text(String value, boolean parent);


    /**
     * Gets attribute value.
     *
     * @param name attribute name
     * @return attribute value
     */
    String attribute(String name);


    /**
     * Gets attribute value.
     *
     * @param namespace attribute namespace
     * @param name attribute name
     * @return attribute value
     */
    String attributeNS(String namespace, String name);


    /**
     * Set attribute value.
     *
     * @param name attribute name
     * @param value attribute value
     * @return self
     */
    ElementLocator<E> attribute(String name, String value);


    /**
     * Set attribute.
     *
     * @param namespace attribute's namespace
     * @param name attribute's name
     * @param value attribute's value
     * @return self
     */
    ElementLocator<E> attributeNS(String namespace, String name, String value);


    /**
     * Return the current element's namespace.
     *
     * @return current element's namespace
     */
    String namespace();


    /**
     * Returns the current element's name.
     *
     * @return current element's name
     */
    String name();


    /**
     * Returns the current element.
     *
     * @return current element
     */
    E current();
}
