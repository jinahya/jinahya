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


import javax.tv.xlet.XletContext;
import javax.tv.xlet.XletStateChangeException;


/**
 *
 * @author <a href="mailto:jinahya@gmail.com">Jin Kwon</a>
 */
public class MyXlet extends TVXlet {


    public MyXlet() {
        super(new XletMachine(new MachineContext(
            MyXletTaskContext.newInstance())));
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

