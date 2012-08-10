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


package com.googlecode.jinahya.stackoverflow.q11887278;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.xml.bind.annotation.adapters.XmlAdapter;


/**
 *
 * @author Jin Kwon <jinahya at gmail.com>
 */
public class SpecifierAdpater extends XmlAdapter<String, List<Specifier>> {


    @Override
    public List<Specifier> unmarshal(final String value) throws Exception {

        if (value == null) {
            return null;
        }

        final List<Specifier> bound = new ArrayList<Specifier>();
        for (String split : value.split(":")) {
            final Specifier specifier = new Specifier();
            specifier.fromString(split);
            bound.add(specifier);
        }
        return bound;
    }


    @Override
    public String marshal(final List<Specifier> bound) throws Exception {

        if (bound == null) {
            return null;
        }

        final StringBuilder builder = new StringBuilder();
        final Iterator<Specifier> specifiers = bound.iterator();
        if (specifiers.hasNext()) {
            builder.append(specifiers.next().toString());
        }
        while (specifiers.hasNext()) {
            builder.append(":");
            builder.append(specifiers.next().toString());
        }
        return builder.toString();
    }


}

