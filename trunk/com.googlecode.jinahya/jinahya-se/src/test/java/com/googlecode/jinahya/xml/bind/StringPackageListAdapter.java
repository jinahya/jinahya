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


import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 *
 * @author Jin Kwon <jinahya at gmail.com>
 */
public class StringPackageListAdapter
    extends StringPackageCollectionAdapter<List<Package>> {


    /**
     * logger.
     */
    private static final Logger LOGGER =
        LoggerFactory.getLogger(StringPackageListAdapter.class);


    @Override
    protected List<Package> bound(final String value) {

        return new ArrayList<Package>();
    }


}
