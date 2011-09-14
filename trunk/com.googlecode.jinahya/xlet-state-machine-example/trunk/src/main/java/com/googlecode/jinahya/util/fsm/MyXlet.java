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


import java.util.Map;

import javax.tv.xlet.XletContext;
import javax.tv.xlet.XletStateChangeException;


/**
 *
 * @author <a href="mailto:jinahya@gmail.com">Jin Kwon</a>
 */
public class MyXlet extends TVXlet {


    static final String CONTEXT_PATH = "com.googlecode.jinahya.util.fsm";


    static final ClassLoader CLASS_LOADER =
        Thread.currentThread().getContextClassLoader();


    static final ResourceLoader RESOURCE_LOADER =
        new ClassResourceLoader(CLASS_LOADER);


    static final Map<String, Task> TASKS;


    static {
        try {
            TASKS = TaskContext.loadTasks(
                CONTEXT_PATH, RESOURCE_LOADER, CLASS_LOADER);
        } catch (FSMException fsme) {
            throw new InstantiationError(fsme.getMessage());
        }
    }


    public MyXlet() {
        super(new XletMachine(TASKS));
    }


    @Override
    public void initXlet(final XletContext ctx)
        throws XletStateChangeException {

        System.out.println("[XLET] initXlet");

        super.initXlet(ctx);
    }


    @Override
    public void startXlet() throws XletStateChangeException {

        System.out.println("[XLET] startXlet");

        super.startXlet();
    }


    @Override
    public void pauseXlet() {

        System.out.println("[XLET] pauseXlet");

        super.pauseXlet();
    }


    @Override
    public void destroyXlet(final boolean unconditional)
        throws XletStateChangeException {

        System.out.println("[XLET] destroyXlet");

        super.destroyXlet(unconditional);
    }
}
