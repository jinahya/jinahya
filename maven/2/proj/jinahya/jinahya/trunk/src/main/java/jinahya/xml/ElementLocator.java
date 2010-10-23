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
 * @param <T>
 */
public interface ElementLocator<T> {


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
    ElementLocator<T> root();


    /**
     * Locate to the parent of the current element.
     *
     * @return self
     */
    ElementLocator<T> parent();


    /**
     * Locate to the given child element.
     *
     * @param name child element name
     * @param index child index
     * @return self
     */
    ElementLocator<T> child(String name, int index);


    /**
     *
     * @param namespace
     * @param name
     * @param index
     * @return self
     */
    ElementLocator<T> childNS(String namespace, String name, int index);


    /**
     * Append new child with value.
     *
     * @param name child element name
     * @param value child element text value
     * @return self
     */
    ElementLocator<T> child(String name, String value);


    /**
     * Append new child element with value.
     *
     * @param namespace child element namespace
     * @param name child element name
     * @param value child element text value
     * @return self
     */
    ElementLocator<T> childNS(String namespace, String name, String value);


    /**
     * Returns the text value.
     *
     * @return text value
     */
    String text();


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
    ElementLocator<T> attribute(String name, String value);


    /**
     * Set attribute.
     *
     * @param namespace attribute namespace
     * @param name attribute name
     * @param value attribute value
     * @return self
     */
    ElementLocator<T> attributeNS(String namespace, String name, String value);


    /**
     *
     * @return
     */
    String namespace();


    /**
     *
     * @return
     */
    String name();


    /**
     *
     * @return
     */
    T current();
}
