/*
 *  Copyright 2010 Jin Kwon.
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

package jinahya.fsm.event;


import java.util.EventObject;

import jinahya.fsm.Machine;
import jinahya.fsm.Transition;


/**
 *
 * @author <a href="mailto:jinahya@gmail.com">Jin Kwon</a>
 */
public class TransitionEvent extends EventObject {


    private static final long serialVersionUID = 9136728627536178799L;


    /**
     * Creates a new instance.
     *
     * @param source source machine
     * @param transition transition
     */
    public TransitionEvent(final Machine source, final Transition transition) {
        super(source);

        this.transition = transition;
    }


    /**
     * Returns the source machine which this state change event occured.
     *
     * @return source machine
     */
    public final Machine getMachine() {
        return (Machine) getSource();
    }


    /**
     * Returns the transtion.
     *
     * @return transition
     */
    public final Transition getTransition() {
        return transition;
    }


    private Transition transition;
}
