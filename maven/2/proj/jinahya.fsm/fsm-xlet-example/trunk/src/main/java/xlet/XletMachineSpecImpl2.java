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

package xlet;


import jinahya.fsm.MachineSpec.TransitionSpec;
import jinahya.fsm.Transition;

import jinahya.fsm.xlet.XletMachineSpec;


/**
 *
 * @author <a href="mailto:jinahya@gmail.com">Jin Kwon</a>
 */
public class XletMachineSpecImpl2 extends XletMachineSpec {


    //@Override
    public TransitionSpec getTransitionSpec(Transition transition) {

        return new TransitionSpec() {
            //@Override
            public int getPoolSize() {
                return 10;
            }

            //@Override
            public long getPoolSleep() {
                return 0L;
            }

            //@Override
            public boolean getImmediateReturnFlag() {
                return Boolean.TRUE.booleanValue();
            }

            //@Override
            public int getMinimumPrecedence() {
                return 9;
            }
        };
    }
}
