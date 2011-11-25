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


package com.googlecode.jinahya.sql;


import com.googlecode.jinahya.util.AbstractCollectable;

import javax.xml.bind.annotation.XmlTransient;


/**
 * Abstract implementation of DatabaseCollection.
 *
 * @author Jin Kwon <jinahya at gmail.com>
 * @param <A> accessible type parameter
 */
@XmlTransient
public abstract class AbstractDbCollectable<A extends DbAccessible<?>>
    extends AbstractCollectable<A>
    implements DbCollectable<A> {


    /** GENERATED. */
    private static final long serialVersionUID = -4134427247884877603L;


    /**
     * Creates a new instance.
     *
     * @param accessibleType accessible type
     */
    public AbstractDbCollectable(final Class<A> accessibleType) {
        super(accessibleType);
    }


}

