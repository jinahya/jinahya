/*
 * Copyright 2013 Jin Kwon <onacit at gmail.com>.
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


package com.googlecode.jinahya.nica.client;


/**
 * Defines a factory API that enables applications to obtain a builder.
 *
 * @author Jin Kwon <onacit at gmail.com>
 */
public abstract class NicaBuilderFactory {


    /**
     * Protected constructor to prevent instantiation.
     */
    protected NicaBuilderFactory() {

        super();
    }


    /**
     * Creates a new instance of {@link NicaBuilder}.
     *
     * @return a new instance of {@link NicaBuilder}.
     *
     * @throws NicaClientException
     */
    public abstract NicaBuilder newNicaBuilder() throws NicaClientException;


}
