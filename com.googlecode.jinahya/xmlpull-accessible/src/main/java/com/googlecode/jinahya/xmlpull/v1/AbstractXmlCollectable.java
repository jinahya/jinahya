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


package com.googlecode.jinahya.xmlpull.v1;


import com.googlecode.jinahya.util.AbstractCollectable;

import javax.xml.bind.annotation.XmlTransient;


/**
 * Abstract XmlCollectable.
 *
 * @author Jin Kwon <jinahya at gmail.com>
 * @param <A> XmlAccessible type parameter
 */
@XmlTransient
public abstract class AbstractXmlCollectable<A extends XmlAccessible>
    extends AbstractCollectable<A>
    implements XmlCollectable<A> {


    /** GENERATED. */
    private static final long serialVersionUID = -8914164757188592435L;


    /**
     * Creates a new instance.
     *
     * @param accessibleType accessible type
     */
    public AbstractXmlCollectable(final Class<A> accessibleType) {
        super(accessibleType);
    }


}

