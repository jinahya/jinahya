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


package myxlet;


import com.googlecode.jinahya.util.fsm.ClassResourceLoader;
import com.googlecode.jinahya.util.fsm.FSMException;
import com.googlecode.jinahya.util.fsm.ResourceLoader;
import com.googlecode.jinahya.util.fsm.TVXlet;
import com.googlecode.jinahya.util.fsm.TaskContext;

import javax.tv.xlet.XletContext;
import javax.tv.xlet.XletStateChangeException;


/**
 *
 * @author <a href="mailto:jinahya@gmail.com">Jin Kwon</a>
 */
public class MyXlet extends TVXlet {


    /** context path. */
    static final String CONTEXT_PATH =
        "myxlet.task.initialization" + ":myxlet.task.initialization.switch_"
        + ":myxlet.task.activation" + ":myxlet.task.activation.switch_";


    /** class loader. */
    static final ClassLoader CLASS_LOADER =
        Thread.currentThread().getContextClassLoader();


    /** resource loader. */
    static final ResourceLoader RESOURCE_LOADER =
        new ClassResourceLoader(CLASS_LOADER);


    /** task context. */
    static final TaskContext TASK_CONTEXT;


    static {
        try {
            TASK_CONTEXT = TaskContext.newInstance(
                CONTEXT_PATH, RESOURCE_LOADER, CLASS_LOADER);
        } catch (FSMException fsme) {
            throw new InstantiationError(fsme.getMessage());
        }
    }


    public MyXlet() {
        super(TASK_CONTEXT);
    }


    @Override
    public void initXlet(final XletContext xletContext)
        throws XletStateChangeException {

        System.out.println("[XLET] initXlet");

        super.initXlet(xletContext);

        synchronized (this) {
            this.xletContext = xletContext;
        }
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

        synchronized (this) {
            xletContext = null;
        }

        super.destroyXlet(unconditional);
    }


    protected XletContext getXletContext() {
        synchronized (this) {
            return xletContext;
        }
    }


    /** xlet context. */
    private XletContext xletContext;
}
