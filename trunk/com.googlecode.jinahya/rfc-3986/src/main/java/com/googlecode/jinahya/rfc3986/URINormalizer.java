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


package com.googlecode.jinahya.rfc3986;


import java.net.URI;


/**
 *
 * @author Jin Kwon <jinahya at gmail.com>
 */
public class URINormalizer {


    public URINormalizer(final URI destabilized) {
        super();

        if (destabilized == null) {
            throw new IllegalArgumentException("null destabilized");
        }

        this.destabilized = destabilized;
    }


    private final URI destabilized;


}

