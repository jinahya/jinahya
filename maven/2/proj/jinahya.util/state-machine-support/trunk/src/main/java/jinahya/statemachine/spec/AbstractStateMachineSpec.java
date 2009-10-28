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

package jinahya.statemachine.spec;


import jinahya.statemachine.StateMachineSpec;
import java.io.UnsupportedEncodingException;


/**
 *
 * @author <a href="mailto:jinahya@gmail.com">Jin Kwon</a>
 */
public abstract class AbstractStateMachineSpec implements StateMachineSpec {


    public static final int UNKNOWN = 0xFF;


    protected static final boolean isAssignableTo(Object instance,
                                                  String[] classNames) {
        Class clazz = instance.getClass();
        for (int i = 0; i < classNames.length; i++) {
            try {
                if (!Class.forName(classNames[i]).isAssignableFrom(clazz)) {
                    return false;
                }
            } catch (ClassNotFoundException cnfe) {
                cnfe.printStackTrace();
                continue;
            }
        }
        return true;
    }


    protected static final boolean isAssignableTo(Object instance,
                                                  Class[] classes) {
        Class clazz = instance.getClass();
        for (int i = 0; i < classes.length; i++) {
            if (!classes[i].isAssignableFrom(clazz)) {
                return false;
            }
        }
        return true;
    }


    protected static final int createIdentifier(final String identifierString) {
        int identifier = 0x00;
        try {
            byte[] bytes = identifierString.getBytes("ASCII"); // US-ASCII
            identifier = 17;
            for (int i = 0; i < bytes.length; i++) {
                identifier = 37 * identifier + ((int)bytes[i]);
            }
        } catch (UnsupportedEncodingException uee) {
            uee.printStackTrace();
        }
        return identifier;
    }


    /**
     *
     * @param identifier
     */
    protected AbstractStateMachineSpec(final int identifier) {
        super();

        this.identifier = identifier;
    }


    //@Override
    public final int getIdentifier() {
        return identifier;
    }


    /**
     *
     * @param state
     * @return false
     */
    //@Override
    public boolean isFinishingState(int state) {
        return false;
    }


    private int identifier;
}
