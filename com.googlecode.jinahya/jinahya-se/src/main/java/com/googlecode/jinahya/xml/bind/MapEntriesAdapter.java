/*
 * Copyright 2013 Jin Kwon <onacit at gmail.com>.
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


package com.googlecode.jinahya.xml.bind;


import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import javax.xml.bind.annotation.adapters.XmlAdapter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * An {@code XmlAdapter} for {@link MapEntries} and {@link Map}.
 *
 * @author Jin Kwon <onacit at gmail.com>
 * @param <T> entries type parameter
 * @param <K> Map key type parameter
 * @param <V> Map value type parameter
 */
public abstract class MapEntriesAdapter<T extends MapEntries<?, K, V>, K, V>
    extends XmlAdapter<T, Map<K, V>> {


    /**
     * logger.
     */
    private static final Logger LOGGER =
        LoggerFactory.getLogger(MapEntriesAdapter.class);


    /**
     * Creates a new instance.
     *
     * @param entriesType entries type.
     */
    public MapEntriesAdapter(final Class<T> entriesType) {

        super();

        this.entriesType = Objects.requireNonNull(entriesType, "entriesType");
    }


    @Override
    public Map<K, V> unmarshal(final T value) throws Exception {

        if (value == null) {
            return null;
        }

        final List<? extends MapEntry<K, V>> ventries = value.getEntries();

        final Map<K, V> bound = new HashMap<K, V>(ventries.size());

        for (MapEntry<K, V> ventry : ventries) {
            bound.put(ventry.getKey(), ventry.getValue());
        }

        return bound;
    }


    @Override
    public T marshal(final Map<K, V> bound) throws Exception {

        if (bound == null) {
            return null;
        }

        final T value = entriesType.newInstance();

        value.addEntries(bound);

        return value;
    }


    /**
     * entries type.
     */
    private final Class<T> entriesType;


}
