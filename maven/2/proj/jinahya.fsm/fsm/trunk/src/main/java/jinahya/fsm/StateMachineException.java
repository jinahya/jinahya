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

package jinahya.fsm;


/**
 *
 * @author <a href="jinahya@gmail.com">Jin Kwon</a>
 */
public class StateMachineException extends Exception {


    private static final long serialVersionUID = -5925728381873724134L;


    /**
     *
     * @param message
     */
    public StateMachineException(String message) {
        this(message, null);
    }


    /**
     *
     * @param cause
     */
    public StateMachineException(Throwable cause) {
        this(cause.getMessage(), cause);
    }


    /**
     * 
     * @param message
     * @param cause
     */
    public StateMachineException(String message, Throwable cause) {
        super(message);
    }


    /**
     *
     * @return
     */
    public Throwable getCause() {
        return cause;
    }

    
    private Throwable cause;
}
