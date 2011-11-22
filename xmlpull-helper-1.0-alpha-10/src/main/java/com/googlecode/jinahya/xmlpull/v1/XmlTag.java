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


/**
 * Interface XML tag.
 *
 * @author Jin Kwon <jinahya at gmail.com>
 */
public interface XmlTag extends XmlAccessible {


    /**
     * Returns XML namespace URi of this tag.
     *
     * @return XML namespace URI
     */
    String getNamespaceURI();


    /**
     * Returns XML local name of this tag.
     *
     * @return XML local name
     */
    String getLocalName();


}

