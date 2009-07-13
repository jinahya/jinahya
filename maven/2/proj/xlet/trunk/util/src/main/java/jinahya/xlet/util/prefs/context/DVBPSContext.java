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

package jinahya.xlet.util.prefs.context;


import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;


/**
 * Context for DVB Persistent Storage.
 *
 * @author <a href="mailto:jkwon@tlab.co.kr">Jin Kwon</a>
 */
public class DVBPSContext extends FSContext {


    public static Context newInstance(Object xletContext)
        throws ClassNotFoundException, NoSuchMethodException,
               IllegalAccessException, InvocationTargetException {

        Class xletContextClass = Class.forName("javax.tv.xlet.XletContext");
        if (!xletContextClass.isInstance(xletContext)) {
            throw new IllegalArgumentException
                (String.valueOf(xletContext) + " is not an instance of " +
                 xletContextClass.getName());
        }
        String fileSeparator = System.getProperty("file.separator");
        Method getXletProperty = xletContextClass.getMethod
            ("getXletProperty", new Class[]{String.class});
        File baseDir = new File(System.getProperty("dvb.persistent.root") +
            fileSeparator +
            getXletProperty.invoke(xletContext, new Object[] {"dvb.org.id"}) +
            fileSeparator +
            getXletProperty.invoke(xletContext, new Object[] {"dvb.app.id"}));

        return new DVBPSContext(baseDir);
    }


    private DVBPSContext(File baseDir) {
        super(baseDir);
    }
}