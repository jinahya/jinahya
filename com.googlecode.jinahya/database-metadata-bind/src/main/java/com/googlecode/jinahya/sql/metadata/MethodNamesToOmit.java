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


package com.googlecode.jinahya.sql.metadata;


import java.util.ArrayList;
import java.util.Collection;


/**
 *
 * @author Jin Kwon <jinahya at gmail.com>
 */
public class MethodNamesToOmit {


    private static final class InstanceHolder {


        private static MethodNamesToOmit INSTANCE;


        static {
            INSTANCE = new MethodNamesToOmit();
        }


    }


    /**
     * Returns instance.
     *
     * @return instance.
     */
    public static MethodNamesToOmit getInstance() {

        return InstanceHolder.INSTANCE;
    }


    public static boolean instanceContainsName(final String name) {
        return getInstance().containsName(name);
    }


    public static void addNameToInstance(final String name) {
        getInstance().addName(name);
    }


    /**
     * Creates a new instance.
     */
    private MethodNamesToOmit() {
        super();
    }


    /**
     * Returns names.
     *
     * @return names.
     */
    protected Collection<String> getNames() {

        if (names == null) {
            names = new ArrayList<String>();
        }

        return names;
    }


    /**
     * Check if this instance contains given <code>name</code>.
     *
     * @param name method name to check
     * @return true if denoted; false otherwise.
     */
    public boolean containsName(final String name) {
        return getNames().contains(name);
    }


    public void addName(final String name) {
        if (!getNames().contains(name)) {
            getNames().add(name);
        }
    }


    /**
     * names.
     */
    private Collection<String> names;


}

