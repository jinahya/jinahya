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
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.StringTokenizer;

import jinahya.xlet.util.prefs.Preferences;


/**
 * Context for general file systems.
 *
 * @author <a href="mailto:jinahya@gmail.com">Jin Kwon</a>
 */
public class FSContext implements Context {


    public static boolean delete(File file) {
        if (file.isFile()) {
            return file.delete();
        } else if (file.isDirectory()) {
            File[] files = file.listFiles();
            for (int i = 0; i < files.length; i++) {
                if (!delete(files[i])) {
                    return false;
                }
            }
            return file.delete();
        } else {
            return false;
        }
    }


    public FSContext(File baseDir) {
        super();

        this.baseDir = baseDir;
    }


    private File fileNode(Preferences prefs) {
        File parent = baseDir;
        StringTokenizer tokenizer =
            new StringTokenizer(prefs.getPackageName(), ".");
        while (tokenizer.hasMoreTokens()) {
            File child = new File(parent, tokenizer.nextToken());
            if (!child.isDirectory() & !child.mkdir()) {
                throw new RuntimeException
                    ("failed to locate dir: " + child.getPath());
            }
            parent = child;
        }
        return new File(parent, "prefs.bin");
    }


    public synchronized void deleteNode(Preferences prefs) throws Exception {
        fileNode(prefs).delete();
    }


    public synchronized void importNode(Preferences prefs) throws Exception {
        prefs.importNode(new FileInputStream(fileNode(prefs)));
    }


    public synchronized void exportNode(Preferences prefs) throws Exception {
        prefs.exportNode(new FileOutputStream(fileNode(prefs)));
    }


    private File baseDir;
}
