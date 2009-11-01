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

package jinahya.fsm.xlet;


import jinahya.fsm.FSMException;


/**
 *
 * @author <a href="mailto:jinahya@gmail.com">Jin Kwon</a>
 */
public abstract class PlayTask extends SwitchTask {


    //@Override
    public void perform(final int previousState, final int currentState,
                        final int priority)
        throws FSMException {

        if (currentState == XletSpec.STARTED) {
            turnOn(priority);
        }

        if (previousState == XletSpec.STARTED &&
            (currentState == XletSpec.PAUSED ||
             currentState == XletSpec.DESTROYED)) {

            turnOff(priority);
        }
    }
}
