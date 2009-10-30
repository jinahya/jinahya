/*
 *  Copyright 2009 onacit.
 * 
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 * 
 *       http://www.apache.org/licenses/LICENSE-2.0
 * 
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 *  under the License.
 */

package xlet;


import java.util.Vector;

import javax.tv.xlet.Xlet;
import javax.tv.xlet.XletContext;
import javax.tv.xlet.XletStateChangeException;

import jinahya.fsm.FSMException;
import jinahya.fsm.FSMSupport;
import jinahya.fsm.FSMTaskFactory;

import jinahya.fsm.xlet.XletSpec;
import jinahya.fsm.xlet.XletSupport;


/**
 *
 * @author <a href="mailto:jinahya@gmail.com">Jin Kwon</a>
 */
public class Impl implements Xlet {


    /**
     *
     */
    public Impl() {
        super();

        try {
            fsms = new XletSupport(new FSMTaskFactory() {
                public void createTasks(Vector tasks) throws FSMException {
                    tasks.addElement(new DefaultTask());
                    tasks.addElement(new SimpleLoadTask());
                    tasks.addElement(new SimpleInitTask());
                    tasks.addElement(new SimplePlayTask());
                }
            }, XletSpec.LOADED);
        } catch (FSMException fsme) {
            fsme.printStackTrace();
        }
    }


    //@Override
    public void initXlet(XletContext ctx) throws XletStateChangeException {
        try {
            fsms.setState(XletSpec.PAUSED);
        } catch (FSMException fsme) {
            fsme.printStackTrace();
        }
    }


    //@Override
    public void startXlet() throws XletStateChangeException {
        try {
            fsms.setState(XletSpec.STARTED);
        } catch (FSMException fsme) {
            fsme.printStackTrace();
        }
    }


    //@Override
    public void pauseXlet() {
        try {
            fsms.setState(XletSpec.PAUSED);
        } catch (FSMException fsme) {
            fsme.printStackTrace();
        }
    }


    //@Override
    public void destroyXlet(boolean unconditional)
        throws XletStateChangeException {

        try {
            fsms.setState(XletSpec.DESTROYED);
        } catch (FSMException fsme) {
            fsme.printStackTrace();
        }
    }


    private FSMSupport fsms;
}
