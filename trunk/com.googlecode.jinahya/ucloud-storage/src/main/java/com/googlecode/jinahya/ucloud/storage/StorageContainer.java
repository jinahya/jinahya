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


import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 *
 * @author Jin Kwon <jinahya at gmail.com>
 */
@XmlRootElement
@XmlType(propOrder = {"name", "count", "bytes"})
public class StorageContainer {


    public String getName() {
        return name;
    }


    public void setName(final String name) {
        this.name = name;
    }


    public long getCount() {
        return count;
    }


    public void setCount(final long count) {
        this.count = count;
    }


    public long getBytes() {
        return bytes;
    }


    public void setBytes(final long bytes) {
        this.bytes = bytes;
    }


    @Override
    public String toString() {
        return name + "|" + count + "|" + bytes;
    }


    @NotNull
    @Size(min = 1)
    @XmlElement(required = true)
    private String name;


    @Min(0L)
    @XmlElement(required = true)
    private long count;


    @Min(0L)
    @XmlElement(required = true)
    private long bytes;


}

