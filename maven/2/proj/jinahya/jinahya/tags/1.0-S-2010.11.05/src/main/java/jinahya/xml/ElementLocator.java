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
     * @param name child element's name
     * @return children count
     */
    int count(String name);


    /**
     * Counts child elements.
     *
     * @param namespace child element's namespace
     * @param name child element's name
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
     * Locate to a child.
     *
     * @param name child element's name
     * @param index child index
     * @return self
     */
    ElementLocator<E> child(String name, int index);


    /**
     * Locate to a child.
     *
     * @param namespace child element's namespace
     * @param name child element's name
     * @param index child element index
     * @return self
     */
    ElementLocator<E> childNS(String namespace, String name, int index);


    /**
     * Append a new child element and locate it.
     *
     * @param name child element's name
     * @return self
     */
    ElementLocator<E> child(String name);


    /**
     * Append a new child element and locate it.
     *
     * @param namespace child element's namespace
     * @param name child element's name
     * @return self
     */
    ElementLocator<E> childNS(String namespace, String name);


    /**
     * Returns current element's text value.
     *
     * @return text content or null
     */
    String text();


    /**
     * Returns current elements's text value. Locate to parent if
     * <code>parent</code> is true.
     *
     * @param parent flag for locating parent
     * @return text content or null
     */
    String text(boolean parent);


    /**
     * Sets current element's text content.
     *
     * @param value text content value.
     * @return self
     */
    ElementLocator<E> text(String value);


    /**
     * Sets current element's text content. Locate to parent if
     * <code>parent</code> is true.
     *
     * @param value text content value
     * @return self
     */
    ElementLocator<E> text(String value, boolean parent);


    /**
     * Returns attribute value.
     *
     * @param name attribute's name
     * @return attribute value
     */
    String attribute(String name);


    /**
     * Returns attribute value.
     *
     * @param namespace attribute's namespace
     * @param name attribute's name
     * @return attribute value
     */
    String attributeNS(String namespace, String name);


    /**
     * Sets attribute value.
     *
     * @param name attribute's name
     * @param value attribute value
     * @return self
     */
    ElementLocator<E> attribute(String name, String value);


    /**
     * Set attribute value.
     *
     * @param namespace attribute's namespace
     * @param name attribute's name
     * @param value attribute's value
     * @return self
     */
    ElementLocator<E> attributeNS(String namespace, String name, String value);


    /**
     * Returns current element's namespace.
     *
     * @return current element's namespace
     */
    String namespace();


    /**
     * Returns current element's name.
     *
     * @return current element's name
     */
    String name();


    /**
     * Returns current element.
     *
     * @return current element
     */
    E current();
}
