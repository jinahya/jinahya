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


package myxlet;


import com.googlecode.jinahya.util.fsm.ClassResourceLoader;
import com.googlecode.jinahya.util.fsm.FSMException;
import com.googlecode.jinahya.util.fsm.ResourceLoader;
import com.googlecode.jinahya.util.fsm.TaskContext;


/**
 *
 * @author Jin Kwon <jinahya at gmail.com>
 */
final class MyTaskContextFactory {


    /** context path. */
    private static final String CONTEXT_PATH =
        "myxlet.task.initialization"
        + ":myxlet.task.initialization.switch_"
        + ":myxlet.task.activation"
        + ":myxlet.task.activation.switch_";


    /** class loader. */
    private static final ClassLoader CLASS_LOADER =
        Thread.currentThread().getContextClassLoader();


    static {
        if (CLASS_LOADER == null) {
            throw new InstantiationError("no ContextClassLoader");
        }
    }


    /** resource loader. */
    private static final ResourceLoader RESOURCE_LOADER =
        new ClassResourceLoader(CLASS_LOADER);


    /**
     * Instance holder for TaskContext.
     */
    private static final class TaskContextInstanceHolder {


        /** instance. */
        private static final TaskContext INSTANCE;


        static {
            try {
                INSTANCE = TaskContext.newInstance(
                    CONTEXT_PATH, RESOURCE_LOADER, CLASS_LOADER);
            } catch (FSMException fsme) {
                throw new InstantiationError(fsme.getMessage());
            }
        }


        /** PRIVATE. */
        private TaskContextInstanceHolder() {
            super();
        }
    }


    /**
     * Returns the tack context.
     *
     * @return task context
     */
    public static TaskContext getTaskContext() {
        return TaskContextInstanceHolder.INSTANCE;
    }


    /** PRIVATE. */
    private MyTaskContextFactory() {
        super();
    }
}
