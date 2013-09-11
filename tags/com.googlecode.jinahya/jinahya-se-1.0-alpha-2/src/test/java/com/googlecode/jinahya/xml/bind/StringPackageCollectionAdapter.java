/*
 * Copyright 2013 Jin Kwon <jinahya at gmail.com>.
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


import java.util.Collection;


/**
 *
 * @author Jin Kwon <jinahya at gmail.com>
 * @param <C>
 */
public abstract class StringPackageCollectionAdapter<C extends Collection<Package>>
    extends StringCollectionAdapter<C, Package> {


    public StringPackageCollectionAdapter() {
        super(":");
    }


    @Override
    public String marshal(C bound) throws Exception {

        System.out.println("marshal(" + bound + ")");

        return super.marshal(bound);
    }


    @Override
    protected Package element(final String token) {

        if ("null".equals(token)) {
            return null;
        }

        return Package.getPackage(token);
    }


    @Override
    protected String token(final Package element) {

        if (element == null) {
            return "null";
        }

        return element.getName();
    }


}
