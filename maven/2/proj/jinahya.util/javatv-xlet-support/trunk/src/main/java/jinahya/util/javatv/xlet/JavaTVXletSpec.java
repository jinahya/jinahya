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

package jinahya.util.javatv.xlet;


import javax.tv.xlet.Xlet;

import jinahya.util.state.StateMachine;
import jinahya.util.state.StateMachineSpec;


/**
 *
 * @author <a href="mailto:jinahya@gmail.com">Jin Kwon</a>
 */
public class JavaTVXletSpec implements StateMachineSpec {


    /**
     * Xlet object has not even loaded yet.
     */
    public static final int NOT_LOADED = 0x01;


    /**
     * Instantiated.
     */
    public static final int LOADED = (NOT_LOADED << 1);


    /**
     * <code>initXlet()</code> or pauseXlet() called.
     */
    public static final int PAUSED = (LOADED << 1);


    /**
     * <code>startXlet()</code> called.
     */
    public static final int STARTED = (PAUSED << 1);


    /**
     * <code>destoryXlet()</code> called.
     */
    public static final int DESTROYED = (STARTED << 1);


    /**
     * DESTROYED but the Xlet object has not yet been garbage collected.
     */
    public static final int INVALID = (DESTROYED << 1);


    public static final int IDENTIFIER =
        "jinahya.util.state.javatv.xlet.JavaTVXletSpec".hashCode();


    //@Override
    public int getIdentifier() {
        return IDENTIFIER;
    }


    //@Override
    public int getStartingState(final StateMachine machine) {
        if (!(machine instanceof Xlet)) {
            throw new IllegalArgumentException
                (machine + " is not an instance of " + Xlet.class);
        }
        return LOADED;
    }


    //@Override
    public boolean isFinishingState(final int state) {
        return state == DESTROYED;
    }


    //@Override
    public boolean isTransitionAllowed(final int sourceState,
                                       final int targetState) {

        boolean allowed = true;

        switch (sourceState) {
            case LOADED:
                allowed = (targetState == PAUSED || targetState == DESTROYED);
                //allowed = targetState == PAUSED;
                break;
            case PAUSED:
                allowed = (targetState == STARTED || targetState == DESTROYED);
                break;
            case STARTED:
                allowed = (targetState == PAUSED || targetState == DESTROYED);
                break;
            default:
                break;
        }

        return allowed;
    }
}
