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


package com.googlecode.jinahya.nica;


/**
 *
 * @author Jin Kwon <onacit at gmail.com>
 */
public abstract class NicaBuilder {


    /**
     * Creates a new instance.
     */
    protected NicaBuilder() {

        super();
    }


    /**
     * Puts a {@code constant} entry. The entry will be persistent in all
     * {@code NicaBuilder} instances created from the originated
     * {@code NicaBuilderFactory}. An {@code IllegalStateException} will be
     * thrown if the entry for {@code key} is already occupied.
     *
     * @param key entry key; must not be null.
     * @param value entry value; must not be null.
     *
     * @return this instance.
     */
    public abstract NicaBuilder putConstantCode(String key, String value);


    /**
     * Puts an {@code variable} entry. The entry will be persistent in all
     * {@code NicaBuilder} instances created from the originated
     * {@code NicaBuilderFactory}.
     *
     * @param key entry key; must not be null.
     * @param value entry value; must not be null.
     *
     * @return this instance.
     */
    public abstract NicaBuilder putVariableCode(String key, String value);


    /**
     * Puts a {@code volatile} entry on this builder. Any {@code volatile}
     * entries will be purged when {@link #build(java.lang.Object)} is invoked.
     *
     * @param key entry key; must not be null.
     * @param value entry value; must not be null.
     *
     * @return this instance.
     */
    public abstract NicaBuilder putVolatileCode(String key, String value);


    /**
     * Builds and attaches request properties on given {@code object}.
     *
     * @param object connection object
     */
    public abstract void build(Object object);


}
