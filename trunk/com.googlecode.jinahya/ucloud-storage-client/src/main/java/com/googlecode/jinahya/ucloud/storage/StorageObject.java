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
@XmlType(propOrder = {"objectName", "lastModified", "entityTag", "contentType",
                      "contentLength"})
public class StorageObject {


    public String getObjectName() {
        return objectName;
    }


    public void setObjectName(final String objectName) {
        this.objectName = objectName;
    }


    public String getEntityTag() {
        return entityTag;
    }


    public void setEntityTag(final String entityTag) {
        this.entityTag = entityTag;
    }


    public long getContentLength() {
        return contentLength;
    }


    public void setContentLength(final long contentLength) {
        this.contentLength = contentLength;
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
        return super.toString() + "?objectName=" + objectName + "&lastModified="
               + lastModified + "&entityTag" + entityTag + "&contentType"
               + contentType + "&contentLength" + contentLength;
    }


    @XmlElement(required = true)
    private String objectName;


    @XmlElement(required = true)
    private long lastModified;


    @XmlElement(required = true)
    private String entityTag;


    @XmlElement(required = true)
    private String contentType;


    @XmlElement(required = true)
    private long contentLength;


}
