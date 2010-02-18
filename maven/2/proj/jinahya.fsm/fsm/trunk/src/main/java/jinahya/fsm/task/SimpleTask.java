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


import jinahya.fsm.Transition;
import jinahya.fsm.TransitionMatcher;


/**
 * An execution unit.
 *
 * @author <a href="mailto:jinahya@gmail.com">Jin Kwon</a>
 */
public abstract class SimpleTask extends Task {


    /**
     * Creates a new instance.
     *
     * @param matchers transition matchers
     */
    public SimpleTask(final TransitionMatcher[] matchers) {
        super();

        if (matchers == null) {
            throw new NullPointerException("matchers");
        }
        if (matchers.length == 0) {
            throw new IllegalArgumentException("matchers: empty");
        }

        this.matchers = new TransitionMatcher[matchers.length];
        System.arraycopy(matchers, 0, this.matchers, 0, this.matchers.length);
    }


    //@Override
    protected final boolean matches(final Transition transition) {
        for (int i = 0; i < matchers.length; i++) {
            if (matchers[i].matches(transition)) {
                return true;
            }
        }
        return false;
    }


    private TransitionMatcher[] matchers;
}
