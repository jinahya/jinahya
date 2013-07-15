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
 * Constants for HTTP header field-names.
 *
 * @author Jin Kwon <jinahya at gmail.com>
 */
public enum Header {


    /**
     * See {@link HeaderFieldNames#NAME}.
     */
    NAME(HeaderNames.NAME),
    /**
     * See {@link HeaderFieldNames#INIT}.
     */
    INIT(HeaderNames.INIT),
    /**
     * See {@link HeaderFieldNames#CODE}.
     */
    CODE(HeaderNames.CODE),
    /**
     * See {@link HeaderFieldNames#AUTH}.
     */
    AUTH(HeaderNames.AUTH);


    /**
     * Finds value whose name matched with given {@code name}.
     *
     * @param name HTTP header field-name.
     *
     * @return matched value.
     */
    public static Header fromName(final String name) {

        for (Header value : values()) {
            if (value.name.equals(name)) {
                return value;
            }
        }

        throw new IllegalArgumentException("unknown name: " + name);
    }


    /**
     * Creates a new instance.
     *
     * @param name HTTP header field-name.
     */
    private Header(final String name) {

        if (name == null) {
            throw new IllegalArgumentException("null name");
        }

        if (name.trim().isEmpty()) {
            throw new IllegalArgumentException("empty name");
        }

        this.name = name;
    }


    /**
     * Returns name.
     *
     * @return name
     */
    public String getName() {
        return name;
    }


    /**
     * name.
     */
    private final String name;


}

