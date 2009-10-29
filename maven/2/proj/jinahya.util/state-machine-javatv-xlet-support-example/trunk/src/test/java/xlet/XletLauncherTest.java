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

package xlet;


import javax.tv.xlet.Xlet;
import javax.tv.xlet.XletStateChangeException;


/**
 *
 * @author <a href="mailto:jinahya@gmail.com">Jin Kwon</a>
 */
public class XletLauncherTest {

    /**
     * @throws XletStateChangeException
     * @testng.test
     */
    public void launch() throws XletStateChangeException {
        Xlet xlet = new Impl();
        xlet.initXlet(null);
        for (int i = 0; i < 5; i++) {
            xlet.startXlet();
            xlet.pauseXlet();
        }
        xlet.destroyXlet(true);
    }
}
