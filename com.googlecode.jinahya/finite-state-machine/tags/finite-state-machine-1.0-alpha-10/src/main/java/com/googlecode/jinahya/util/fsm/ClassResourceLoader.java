/*
 * Copyright 2011 <a href="mailto:jinahya@gmail.com">Jin Kwon</a>.
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


package com.googlecode.jinahya.util.fsm;


import java.io.IOException;
import java.io.InputStream;


/**
 * A ResourceLoader implementation using a ClassLoader.
 *
 * @author <a href="mailto:jinahya@gmail.com">Jin Kwon</a>
 */
public class ClassResourceLoader implements ResourceLoader {


    /**
     * Creates a new instance.
     *
     * @param classLoader
     */
    public ClassResourceLoader(final ClassLoader classLoader) {

        super();

        if (classLoader == null) {
            throw new NullPointerException("null classLoader");
        }

        this.classLoader = classLoader;
    }


    @Override
    public InputStream load(final String resourceName)
        throws IOException, FSMException {

        if (resourceName == null) {
            throw new NullPointerException("null resourceName");
        }

        if (resourceName.trim().length() == 0) {
            throw new IllegalArgumentException("empty resourceName");
        }

        final InputStream resourceStream =
            classLoader.getResourceAsStream(resourceName);

        if (resourceName == null) {
            throw new FSMException("failed to load resource: " + resourceName);
        }

        return resourceStream;
    }


    /** class loader. */
    final ClassLoader classLoader;
}

