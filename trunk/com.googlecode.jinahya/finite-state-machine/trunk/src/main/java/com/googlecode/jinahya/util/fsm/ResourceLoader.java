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
 * Platform specific resource loader.
 */
/**
 *
 * @author <a href="mailto:jinahya@gmail.com">Jin Kwon</a>
 */
public interface ResourceLoader {


    /**
     * Loads resource denoted by given <code>resourceName</code>.
     *
     * @param resourceName resource name
     * @return loaded resource stream; never null
     * @throws IOException if an I/O error occurs
     * @throws FSMException if an error occurs
     */
    InputStream load(String resourceName) throws IOException, FSMException;
}

