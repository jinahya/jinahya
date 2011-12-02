/*
 * Copyright 2011 Jin Kwon <jinahya at gmail.com>.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */


package jinahyax.xml.bind.annotation.adapters;



import java.util.Map;

import javax.xml.bind.annotation.adapters.XmlAdapter;


/**
 *
 * @author Jin Kwon <jinahya at gmail.com>
 * @param <T> valueType type parameter
 * @param <M> map type parameter
 * @param <K> map key type parameter
 * @param <V> map value type parameter
 */
public abstract class XmlMapAdapter<T, M extends Map<K, V>, K, V>
    extends XmlAdapter<T, M> {


    /**
     * Creates a new map.
     *
     * @return a new map
     */
    protected abstract M newMap();


    /**
     * Returns the map key from given <code>value</code>.
     *
     * @param mapValue value
     * @return map key
     */
    protected abstract K getMapKey(V mapValue);


}

