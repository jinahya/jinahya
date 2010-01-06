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
public final class XletState {


    /**
     * Xlet object has not even loaded yet.
     * INFORMATIVE
     */
    public static final int NOT_LOADED = State.UNKNOWN << 1;


    /**
     * Xlet instance created.
     */
    public static final int LOADED = NOT_LOADED << 1;


    /**
     * <code>initXlet()</code> or pauseXlet() invoked.
     */
    public static final int PAUSED = LOADED << 1;


    /**
     * <code>startXlet()</code> invoked.
     */
    public static final int STARTED = PAUSED << 1;


    /**
     * <code>destroyXlet()</code> invoked.
     */
    public static final int DESTROYED = STARTED << 1;


    /**
     * Destroyed but the Xlet object has not yet been garbage collected.
     * INFORMATIVE
     */
    public static final int INVALID = DESTROYED << 1;


    private XletState() {
        super();
    }
}
