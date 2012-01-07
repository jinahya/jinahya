/*
 * Copyright 2011 onacit.
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


import java.util.Map;

import org.testng.annotations.Test;


/**
 *
 * @author onacit
 */
public class TaskContextTest {


    @Test
    public static Map<String, Task> testLoadTasks() throws FSMException {

        final String contextPath =
            "com.googlecode.jinahya.fsm.a"
            + ":com.googlecode.jinahya.fsm.b";

        final ResourceLoader resourceLoader = new ClassResourceLoader(
            TaskContextTest.class.getClassLoader());

        final ClassLoader classLoader = TaskContextTest.class.getClassLoader();

        return TaskLoader.loadTasks(contextPath, resourceLoader, classLoader);
    }


}

