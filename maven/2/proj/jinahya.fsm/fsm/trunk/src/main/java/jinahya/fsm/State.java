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
public class State {


    public static final State UNKNOWN = new State("default", "unknown");


    /**
     * Creates an instnace.
     *
     * @param namespace namespace of this state
     * @param name name of this state
     */
    public State(String namespace, String name) {
        super();

        if (namespace == null && namespace.trim().length() == 0) {
            throw new IllegalArgumentException
                ("illegal namespace: " + namespace);
        }

        if (name == null && name.trim().length() == 0) {
            throw new IllegalArgumentException("illegal name: " + name);
        }

        this.namespace = namespace;
        this.name = name;
    }


    /**
     * Returns the namespace of this state.
     *
     * @return namespace
     */
    public String getNamespace() {
        return namespace;
    }


    /**
     * Returns the name of this state.
     *
     * @return name
     */
    public String getName() {
        return name;
    }


    //@Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof State)) {
            return false;
        }

        State state = (State) obj;

        return (namespace.equals(state.namespace) && name.equals(state.name));
    }


    //@Override
    public int hashCode() {
        int result = 17;
        result = 31 * result + namespace.hashCode();
        result = 31 * result + name.hashCode();

        return result;
    }


    //@Override
    public String toString() {
        return "STATE: " + name + "@" + namespace;
    }


    private String namespace;
    private String name;
}
