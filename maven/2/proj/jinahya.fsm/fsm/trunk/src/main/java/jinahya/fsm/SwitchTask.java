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

package jinahya.fsm;


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

        if (onMatchers.length == 0) {
            throw new IllegalArgumentException("onMatchers: empty");
        }

        if (offMatchers.length == 0) {
            throw new IllegalArgumentException("offMatchers: empty");
        }


        this.onMatchers = new TransitionMatcher[onMatchers.length];
        System.arraycopy(onMatchers, 0, this.onMatchers, 0,
                         this.onMatchers.length);

        this.offMatchers = new TransitionMatcher[offMatchers.length];
        System.arraycopy(offMatchers, 0, this.offMatchers, 0,
                         this.offMatchers.length);
    }


    //@Override
    public void perform(final Transition transition, final int precedence)
        throws MachineException {

        if (on) {
            for (int i = 0; i < offMatchers.length; i++) {
                if (offMatchers[i].matches(transition)) {
                    try {
                        off(precedence);
                    } finally {
                        synchronized (this) {
                            on = Boolean.FALSE.booleanValue();
                        }
                    }
                    break;
                }
            }
        } else { // off
            for (int i = 0; i < onMatchers.length; i++) {
                if (onMatchers[i].matches(transition)) {
                    try {
                        on(precedence);
                    } finally {
                        synchronized (this) {
                            on = Boolean.TRUE.booleanValue();
                        }
                    }
                    break;
                }
            }
        }
    }


    /**
     * Performes when this switch task is on.
     *
     * @param precedence task precedence
     * @throws MachineException if any error occurs.
     */
    protected abstract void on(int precedence) throws MachineException;


    /**
     * Performes when this switch task is off.
     *
     * @param precedence task precedence
     * @throws MachineException if any error occurs.
     */
    protected abstract void off(int precedence) throws MachineException;


    /**
     * Returns whether this task is currently turned on or not.
     *
     * @return true if this switch task is currently turned on, false otherwise.
     */
    public boolean isOn() {
        synchronized (this) {
            return on;
        }
    }


    private TransitionMatcher[] onMatchers;
    private TransitionMatcher[] offMatchers;

    private volatile boolean on = false;
}
