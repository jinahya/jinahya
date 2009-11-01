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

package jinahya.fsm.applet;


import jinahya.fsm.FSMSpec;


/**
 *
 * @author <a href="mailto:jinahya@gmail.com">Jin Kwon</a>
 */
public class AppletSpec implements FSMSpec {


    /**
     * 
     */
    public static final int LOADED = 0x01;


    /**
     * 
     */
    public static final int PAUSED = LOADED << 1;


    /**
     *
     */
    public static final int STARTED = PAUSED << 1;


    /**
     *
     */
    public static final int DESTROYED = STARTED << 1;


    //@Override
    public boolean isStartingTransition(final int sourceState,
                                        final int targetState) {

        return (sourceState == UNKNOWN_STATE && targetState == LOADED) ||
               (sourceState == LOADED && targetState == PAUSED);
    }


    //@Override
    public boolean isTransitionAllowed(final int sourceState,
                                       final int targetState) {

        boolean allowed = true;

        return allowed;
    }


    //@Override
    public boolean isFinishingTransition(final int sourceState,
                                         final int targetState) {

        return targetState == DESTROYED;
    }
}
