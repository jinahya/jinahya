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


package com.googlecode.jinahya.ucloud.storage;


import sun.nio.fs.WindowsFileAttributeViews.Basic;



/**
 *
 * @author Jin Kwon <jinahya at gmail.com>
 */
public class ObjectLocatorEmbeddable {


    private static final int OBJECT_INDEX_BITS = 20;


    private static final long MAXIMUM_OBJECT_IN_A_CONTAINER = 1048576L;


    public static ObjectLocatorEmbeddable newInstance(String containerPrefix, final long id) {

        if (containerPrefix == null) {
            containerPrefix = "";
        }

        containerPrefix = containerPrefix.trim();

        final ObjectLocatorEmbeddable instance = new ObjectLocatorEmbeddable();

        instance.containerName =
            containerPrefix + Long.toString(id >>> OBJECT_INDEX_BITS);

        instance.objectName =
            Long.toString(id & MAXIMUM_OBJECT_IN_A_CONTAINER);

        return instance;
    }


    //@Basic
    //@Column()
    private String containerName;


    private String objectName;


}

