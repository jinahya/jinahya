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
     * 
     * @param onMatchers
     * @param offMatchers
     */
    public SwitchTask(final TransitionMatcher[] onMatchers,
                      final TransitionMatcher[] offMatchers) {

        super();

        this.onMatchers = new TransitionMatcher[onMatchers.length];
        System.arraycopy(onMatchers, 0, this.onMatchers, 0,
                         this.onMatchers.length);

        this.offMatchers = new TransitionMatcher[offMatchers.length];
        System.arraycopy(offMatchers, 0, this.offMatchers, 0,
                         this.offMatchers.length);
    }


    //@Override
    public synchronized final void perform(final Transition transition,
                                           final int priority)
        throws MachineException {

        if (on) {
            for (int i = 0; i < offMatchers.length; i++) {
                if (offMatchers[i].matches(transition)) {
                    off(priority);
                    on = Boolean.FALSE.booleanValue();
                    break;
                }
            }
        } else { // off
            for (int i = 0; i < onMatchers.length; i++) {
                if (onMatchers[i].matches(transition)) {
                    on(priority);
                    on = Boolean.TRUE.booleanValue();
                    break;
                }
            }
        }
    }


    /**
     *
     * @param priority
     * @throws MachineException
     */
    protected abstract void on(int priority) throws MachineException;


    /**
     *
     * @param priority
     * @throws MachineException
     */
    protected abstract void off(int priority) throws MachineException;


    /**
     * Returns whether this task is currently turn on or not.
     *
     * @return true if this switch task is currently turned on, false otherwise
     */
    public synchronized final boolean isOn() {
        return on;
    }


    private TransitionMatcher[] onMatchers;
    private TransitionMatcher[] offMatchers;

    private volatile boolean on = false;
}
