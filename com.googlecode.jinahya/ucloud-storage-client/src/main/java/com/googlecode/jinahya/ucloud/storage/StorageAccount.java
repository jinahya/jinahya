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
@XmlType(propOrder = {"containerCount", "objectCount", "bytesUsed"})
public class StorageAccount {


    public long getContainerCount() {

        return containerCount;
    }


    public void setContainerCount(final long containerCount) {

        this.containerCount = containerCount;
    }


    public long getObjectCount() {

        return objectCount;
    }


    public void setObjectCount(final long objectCount) {

        this.objectCount = objectCount;
    }


    public long getBytesUsed() {

        return bytesUsed;
    }


    public void setBytesUsed(final long bytesUsed) {

        this.bytesUsed = bytesUsed;
    }


    @Override
    public String toString() {

        return super.toString()
               + "?containerCount=" + containerCount
               + "&objectCount=" + objectCount
               + "&bytesUsed=" + bytesUsed;
    }


    @XmlElement(required = true)
    private long containerCount;


    @XmlElement(required = true)
    private long objectCount;


    @XmlElement(required = true)
    private long bytesUsed;


}
