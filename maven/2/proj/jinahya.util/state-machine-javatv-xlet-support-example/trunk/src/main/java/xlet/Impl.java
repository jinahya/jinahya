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

import jinahya.util.state.StateMachineException;
import jinahya.util.state.StateMachineTaskManager;
import jinahya.util.state.javatv.xlet.AbstractXlet;


/**
 *
 * @author <a href="mailto:jinahya@gmail.com">Jin Kwon</a>
 */
public class Impl extends AbstractXlet {

    // "xlet.Impl".hashCode();
    // -91910485

    public static final int IDENTIFIER = 0xFA858EAB;


    /*
    public Impl() {
        super(IDENTIFIER, null);
    }
    */


    public Impl() {
        super(IDENTIFIER, new StateMachineTaskManager() {
            public void loadTasks(Vector tasks) throws StateMachineException {
                tasks.addElement(new DefaultTask());
                tasks.addElement(new LoadTask());
                tasks.addElement(new InitTask());
                tasks.addElement(new PlayTask());
            }
        });
    }
}
