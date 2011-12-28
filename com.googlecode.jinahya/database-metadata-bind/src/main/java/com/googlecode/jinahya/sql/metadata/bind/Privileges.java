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


package com.googlecode.jinahya.sql.metadata.bind;


import java.util.Collection;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;


/**
 * Identifier collection.
 *
 * @author Jin Kwon <jinahya at gmail.com>
 * @param <V> Privilege type parameter.
 * @param <P> parent type parameter
 */
@XmlRootElement
@XmlTransient
public abstract class Privileges<V extends Privilege<P>, P extends EntrySet>
    extends EntrySets<V> {


    /**
     * Creates a new instance.
     *
     * @param privilegeType 
     */
    public Privileges(final Class<V> privilegeType) {
        super(privilegeType);
    }


    /**
     * Returns properties.
     *
     * @return properties
     */
    @XmlElement(name = "privilege")
    public Collection<V> getPrivileges() {
        return super.getEntrySets();
    }


}

