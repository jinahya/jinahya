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

package jinahya.fsm.task;

import jinahya.fsm.MachineException;
import jinahya.fsm.Transition;
import jinahya.fsm.TransitionMatcher;


/**
 *
 * @author <a href="mailto:jinahya@gmail.com">Jin Kwon</a>
 */
public abstract class SwitchTask extends Task {


    /**
     * Creates a new instance.
     *
     * @param onMatchers conditions to be matched for switch-on.
     * @param offMatchers conditions to be matched for switch-off.
     */
    public SwitchTask(final TransitionMatcher[] onMatchers,
                      final TransitionMatcher[] offMatchers) {

        super();

        if (onMatchers == null) {
            throw new NullPointerException("onMatchers");
        }
        if (onMatchers.length == 0) {
            throw new IllegalArgumentException("onMatchers: empty");
        }

        if (offMatchers == null) {
            throw new NullPointerException("offMatchers");
        }
        if (offMatchers.length == 0) {
            throw new IllegalArgumentException("offMatchers: empty");
        }


        _onMatchers = new TransitionMatcher[onMatchers.length];
        System.arraycopy(onMatchers, 0, _onMatchers, 0, _onMatchers.length);

        _offMatchers = new TransitionMatcher[offMatchers.length];
        System.arraycopy(offMatchers, 0, _offMatchers, 0, _offMatchers.length);
    }


    //@Override
    public void perform(final Transition transition) throws MachineException {

        if (on) {
            for (int i = 0; i < _offMatchers.length; i++) {
                if (_offMatchers[i].matches(transition)) {
                    try {
                        off();
                    } finally {
                        synchronized (this) {
                            on = Boolean.FALSE.booleanValue();
                        }
                    }
                    return;
                }
            }
        } else { // off
            for (int i = 0; i < _onMatchers.length; i++) {
                if (_onMatchers[i].matches(transition)) {
                    try {
                        on();
                    } finally {
                        synchronized (this) {
                            on = Boolean.TRUE.booleanValue();
                        }
                    }
                    return;
                }
            }
        }
    }


    /**
     * Performes when this switch task is turned on.
     *
     * @throws MachineException if any error occurs.
     */
    protected abstract void on() throws MachineException;


    /**
     * Performes when this switch task is turned off.
     *
     * @throws MachineException if any error occurs.
     */
    protected abstract void off() throws MachineException;


    /**
     * Returns whether this task is currently turned on or off.
     *
     * @return true if this switch task is currently turned on, false otherwise.
     */
    public final boolean isOn() {
        synchronized (this) {
            return on;
        }
    }


    private TransitionMatcher[] _onMatchers;
    private TransitionMatcher[] _offMatchers;

    private volatile boolean on = Boolean.FALSE.booleanValue();
}
