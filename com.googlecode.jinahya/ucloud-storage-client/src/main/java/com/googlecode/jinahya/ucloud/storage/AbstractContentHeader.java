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


/**
 *
 * @author Jin Kwon <jinahya at gmail.com>
 */
public abstract class AbstractContentHeader {


    public AbstractContentHeader(final String type, final long length) {
        super();

        this.type = type;
        this.length = length;
    }


    /**
     * Returns type.
     *
     * @return type
     */
    public String getType() {
        return type;
    }


    /**
     * Sets type.
     *
     * @param type type
     */
    public void setType(final String type) {
        this.type = type;
    }


    /**
     * Returns length.
     *
     * @return length
     */
    public long getLength() {
        return length;
    }


    /**
     * Sets length.
     *
     * @param length length
     */
    public void setLength(final long length) {
        this.length = length;
    }


    /**
     * type.
     */
    private String type;


    /**
     * length.
     */
    private long length = -1L;
}

