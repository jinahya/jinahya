/*
 *  Copyright 2009 Jin Kwon.
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

package jinahya.fsm.xlet;


import jinahya.fsm.State;


/**
 *
 * @author <a href="mailto:jinahya@gmail.com">Jin Kwon</a>
 */
public class XletState {


    private static final String NAMESPACE = "javax.tv.xlet.Xlet";


    /**
     * Xlet object has not even loaded yet.
     */
    public static final State NOT_LOADED = new State(NAMESPACE, "NOT_LOADED");


    /**
     * Xlet instance created.
     */
    public static final State LOADED = new State(NAMESPACE, "LOADED");


    /**
     * <code>initXlet()</code> or pauseXlet() invoked.
     */
    public static final State PAUSED = new State(NAMESPACE, "PAUSED");


    /**
     * <code>startXlet()</code> invoked.
     */
    public static final State STARTED = new State(NAMESPACE, "STARTED");


    /**
     * <code>destroyXlet()</code> invoked.
     */
    public static final State DESTROYED = new State(NAMESPACE, "DESTROYED");


    /**
     * Destroyed but the Xlet object has not yet been garbage collected.
     */
    public static final State INVALID = new State(NAMESPACE, "INVALIED");

}
