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


import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 *
 * @author Jin Kwon <jinahya at gmail.com>
 */
@XmlRootElement
@XmlType(propOrder = {"name", "hash", "bytes", "contentType", "lastModified"})
public class StorageObject {


    public String getName() {
        return name;
    }


    public void setName(final String name) {
        this.name = name;
    }


    public String getHash() {
        return hash;
    }


    public void setHash(final String hash) {
        this.hash = hash;
    }


    public long getBytes() {
        return bytes;
    }


    public void setBytes(final long bytes) {
        this.bytes = bytes;
    }


    public String getContentType() {
        return contentType;
    }


    public void setContentType(final String contentType) {
        this.contentType = contentType;
    }


    public long getLastModified() {
        return lastModified;
    }


    public void setLastModified(final long lastModified) {
        this.lastModified = lastModified;
    }


    @Override
    public String toString() {
        return name + "|" + hash + "|" + bytes + "|" + contentType + "|"
               + lastModified;
    }


    @XmlElement(required = true)
    private String name;


    @XmlElement(required = true)
    private String hash;


    @XmlElement(required = true)
    private long bytes;


    @XmlElement(required = true)
    private String contentType;


    @XmlElement(required = true)
    private long lastModified;


}
