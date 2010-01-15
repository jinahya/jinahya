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
 */

package jinahya.fsm.android.activity;


import static jinahya.fsm.State.UNKNOWN;


/**
 *
 * @author <a href="mailto:jinahya@gmail.com">Jin Kwon</a>
 */
public final class ActivityState {


    /**
     * <ul>
     * <li> {@link jinahya.fsm.State#UNKNOWN} &#8594; <code>onCreate()<code></li>
     * <li> {@link ActivityState#PAUSED} &#8594; <code>onRestart()</code></li>
     * </ul>
     */
    public static final int SUSPENDED = UNKNOWN >>> 1;


    /**
     * <code>STOPPED -> onStart() | ACTIVE -> onPause()</code>.
     */
    public static final int PAUSED = SUSPENDED >> 1;


    /**
     * <code>PAUSED -> onResume()</code>.
     */
    public static final int ACTIVE = PAUSED >> 1;


    /**
     * <code>STOPPED -> onDestroy()</code>.
     */
    public static final int DESTROYED = ACTIVE;


    private ActivityState() {
        super();
    }
}
