/*
 * Copyright 2012 Jin Kwon <jinahya at gmail.com>.
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


package com.googlecode.jinahya.nica.test;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;


/**
 *
 * @author Jin Kwon <jinahya at gmail.com>
 */
@XmlRootElement
public class IndexedKeys {


    public static IndexedKeys newInstance(final int size) {

        final IndexedKeys instance = new IndexedKeys();

        instance.indexedTestKeys = new ArrayList<IndexedKey>(size);
        instance.indexedTestKeys.addAll(
            Arrays.asList(IndexedKey.newInstances(size)));

        return instance;
    }


    @XmlElement(name = "indexedKey")
    private Collection<IndexedKey> indexedTestKeys;


}

