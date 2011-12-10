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


package com.googlecode.jinahya.jvms.classfile.constant;


import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlTransient;


/**
 *
 * @author Jin Kwon <jinahya at gmail.com>
 */
@XmlTransient
public abstract class AbstractConstant extends Constant {


    protected AbstractConstant(final ConstantTag tag) {
        super();

        if (tag == null) {
            throw new NullPointerException("null tag");
        }

        this.tag = tag;
    }


    @Override
    public final ConstantTag getTag() {
        return tag;
    }


    @XmlAttribute(required = true)
    private final ConstantTag tag;


}

