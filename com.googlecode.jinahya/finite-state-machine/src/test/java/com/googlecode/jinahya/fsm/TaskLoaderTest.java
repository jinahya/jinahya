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


package com.googlecode.jinahya.fsm;


import org.testng.annotations.Test;


/**
 *
 * @author Jin Kwon <jinahya at gmail.com>
 */
public class TaskLoaderTest {


    private static final ClassLoader CLASS_LOADER =
        TaskLoaderTest.class.getClassLoader();


    private static final ResourceLoader RESOURCE_LOADER =
        new ClassResourceLoader(CLASS_LOADER);


    @Test
    public static void testLoadTasks() throws FSMException {

        TaskLoader.loadTasks(
            "com.googlecode.jinahya.fsm", RESOURCE_LOADER, CLASS_LOADER);
    }


}

