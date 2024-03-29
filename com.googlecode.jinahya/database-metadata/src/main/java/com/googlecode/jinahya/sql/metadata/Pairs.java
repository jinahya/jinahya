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


package com.googlecode.jinahya.sql.metadata;


import java.util.ArrayList;
import java.util.Collection;

import javax.xml.bind.annotation.XmlElement;


/**
 * Entries.
 *
 * @author Jin Kwon <jinahya at gmail.com>
 */
public class Pairs {


    /**
     * Returns entries.
     *
     * @return entries
     */
    protected final Collection<Pair> getEntries() {

        if (pairs == null) {
            pairs = new ArrayList<Pair>();
        }

        return pairs;
    }


    /**
     * entries.
     */
    @XmlElement(name = "pair", nillable = true)
    private Collection<Pair> pairs;


}

