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


package com.googlecode.jinahya.xml.bind;


import java.util.Collection;
import javax.xml.bind.annotation.XmlAnyElement;
import javax.xml.bind.annotation.XmlTransient;


/**
 * General class for wrapper classes.
 *
 * @author Jin Kwon <jinahya at gmail.com>
 * @param <S> singular type parameter
 */
@XmlTransient
public abstract class RelaxedPlural<S> extends AbstractPlural<S> {


    /**
     * Returns singular collection.
     *
     * @return singular collection
     */
    @XmlAnyElement(lax = true)
    @Override
    public Collection<S> getSingulars() {
        return super.getSingulars();
    }


    /**
     * singular collection.
     */
    private Collection<S> singulars;


}

