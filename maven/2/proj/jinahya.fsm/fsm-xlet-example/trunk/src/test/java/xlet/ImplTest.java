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
public class ImplTest {


    /**
     * @testng.test
     */
    public void launch() {

        System.out.println("-------------------------------------------------");
        System.out.println("Before Loading");
        Xlet impl = new Impl();
        System.out.println("After Loading");

        System.out.println("-------------------------------------------------");
        System.out.println("Before initXlet()");
        try {
            impl.initXlet(null);
        } catch (XletStateChangeException xsce) {
            xsce.printStackTrace();
        }
        System.out.println("After initXlet()");

        System.out.println("-------------------------------------------------");
        System.out.println("Before startXlet()");
        try {
            impl.startXlet();
        } catch (XletStateChangeException xsce) {
            xsce.printStackTrace();
        }
        System.out.println("After startXlet()");

        System.out.println("-------------------------------------------------");
        System.out.println("Before pauseXlet()");
        impl.pauseXlet();
        System.out.println("After pauseXlet()");

        System.out.println("-------------------------------------------------");
        try {
            impl.destroyXlet(true);
        } catch (XletStateChangeException xsce) {
            xsce.printStackTrace();
        }
        System.out.println("After destroyXlet()");
    }
}
