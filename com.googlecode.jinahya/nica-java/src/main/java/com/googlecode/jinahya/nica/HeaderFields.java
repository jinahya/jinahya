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


package com.googlecode.jinahya.nica;


/**
 *
 * @author Jin Kwon <jinahya at gmail.com>
 */
public final class HeaderFields {


    /**
     * HTTP header field name for names.
     */
    public static final String NAME = "Nica-Name";


    /**
     * HTTP header field name for initialization vector.
     */
    public static final String INIT = "Nica-Init";


    /**
     *
     */
    public static final String BASE = "Nica-Base";


    /**
     * HTTP Header field name for encrypted codes.
     */
    public static final String CODE = "Nica-Code";


    /**
     * HTTP header field name for authenticated message.
     */
    public static final String AUTH = "Nica-Auth";


    /**
     * Creates a new instance.
     */
    private HeaderFields() {
        super();
    }


}
