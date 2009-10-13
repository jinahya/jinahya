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

package jinahya.awt;


import java.awt.Component;
import java.awt.Image;
import java.awt.MediaTracker;
import java.awt.Toolkit;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Hashtable;
import java.util.Vector;


/**
 *
 * @author <a href="mailto:jinahya@gmail.com">Jin Kwon</a>
 */
public final class ImageSupport {



    public ImageSupport(Component component) {
        super();

        tracker = new MediaTracker(component);
    }


    public boolean retain(final Object source, final Object owner,
                          final boolean load, final boolean block)
        throws Exception {

        synchronized (sourceImageTable) {
            Image image = (Image) sourceImageTable.get(source);
            if (image == null) {
                image = get(source);
                sourceImageTable.put(source, image);
            }

            if (load) {
                load(image, block);
            }

            synchronized (sourceOwnerTable) {
                if (!sourceOwnerTable.contains(source)) {
                    sourceOwnerTable.put(source, new Vector());
                }
                final Vector owners = (Vector) sourceOwnerTable.get(source);
                synchronized (owners) {
                    if (owners.contains(owner)) {
                        return false;
                    } else {
                        owners.addElement(owner);
                        return true;
                    }
                }
            }
        }
    }


    public boolean release(final Object source, final Object owner) {
        synchronized (sourceImageTable) {
            if (!sourceImageTable.contains(source)) {
                return false;
            }
            synchronized (sourceOwnerTable) {
                final Vector owners = (Vector) sourceOwnerTable.get(source);
                synchronized (owners) {
                    if (owners.removeElement(owner)) {
                        if (owners.isEmpty()) {
                            sourceOwnerTable.remove(source);
                            ((Image) sourceImageTable.remove(source)).flush();
                        }
                        return true;
                    } else {
                        return false;
                    }
                }
            }
        }
    }


    private Image get(final Object source) throws IOException {
        if (source instanceof String) {
            return get((String) source);
        } else if (source instanceof File) {
            return get((File) source);
        } else if (source instanceof URL) {
            return get((URL) source);
        } else {
            throw new IllegalArgumentException
                ("UNKNOWN IMAGE SOURCE: " + source.getClass());
        }
    }


    private Image get(String name) throws IOException {
        InputStream in = getClass().getResourceAsStream(name);
        try {
            return get(in);
        } finally {
            in.close();
        }
    }


    private Image get(File file) throws IOException {
        InputStream in = new FileInputStream(file);
        try {
            return get(in);
        } finally {
            in.close();
        }
    }


    private Image get(URL url) throws IOException {
        InputStream in = url.openStream();
        try {
            return get(in);
        } finally {
            in.close();
        }
    }


    private Image get(InputStream in) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try {
            synchronized (buffer) {
                for (int read = -1; (read = in.read(buffer)) != -1;) {
                    baos.write(buffer, 0, read);
                }
            }
            baos.flush();
            return Toolkit.getDefaultToolkit().createImage(baos.toByteArray());
        } finally {
            baos.close();
        }
    }


    private void load(final Image image, final boolean block) {
        Thread t = new Thread() {
            //@Override
            public void run() {
                int id = image.hashCode();
                tracker.addImage(image, id);
                try {
                    try {
                        tracker.waitForID(id);
                    } catch (InterruptedException ie) {
                        ie.printStackTrace();
                    }
                } finally {
                    tracker.removeImage(image, id);
                }
            }
        };
        t.start();

        if (block) {
            try {
                t.join();
            } catch (InterruptedException ie) {
                ie.printStackTrace();
            }
        }
    }


    private MediaTracker tracker;

    // <java.lang.Object, java.awt.Image>
    private final Hashtable sourceImageTable = new Hashtable();

    // <java.awt.Image, java.util.Vector<Object>>
    private final Hashtable sourceOwnerTable = new Hashtable();

    private final transient byte[] buffer = new byte[1024];
}
