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
import jinahya.fsm.FSMSupport;
import jinahya.fsm.FSMTaskFactory;


/**
 *
 * @author <a href="jinahya@gmail.com">Jin Kwon</a>
 */
public class XletSupport extends FSMSupport {


    /*
     *
     * @param factory
     * @return
     * @throws FSMException
     */
    /*
    public static XletSupport iAmInMyConstructor(FSMTaskFactory factory)
        throws FSMException {

        return new XletSupport(factory, XletSpec.LOADED);
    }
     */


    /**
     *
     * @param factory
     * @param state
     * @throws FSMException
     */
    public XletSupport(FSMTaskFactory factory, int state) throws FSMException {
        super(new XletSpec(), factory, state);
    }
}
