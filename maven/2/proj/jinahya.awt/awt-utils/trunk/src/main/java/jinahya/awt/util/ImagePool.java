/*
 *  Copyright 2010 Jin Kwon.
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
 */

package jinahya.awt.util;


import java.awt.Image;
import java.awt.Toolkit;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;


/**
 *
 * @author <a href="mailto:jinahya@gmail.com">Jin Kwon</a>
 */
public class ImagePool {


    /**
     * Interface for image creation module.
     */
    public static interface ImageCreator {

        /**
         * Creates image from given <code>imagedata</code>.
         *
         * @param imagedata an array of bytes, representing image data in a
         *        supported image format.
         * @return an image or null.
         */
        Image createImage(byte[] imagedata);
    }


    /**
     * An ImageCreator implementation using the Toolkit.
     */
    private static final class ToolkitImageCreator implements ImageCreator {


        /** default toolkit. */
        private static final Toolkit TOOLKIT = Toolkit.getDefaultToolkit();


        @Override
        public Image createImage(final byte[] imagedata) {
            if (imagedata == null) {
                throw new IllegalArgumentException("null imagedata");
            }
            return TOOLKIT.createImage(imagedata);
        }


        /** private. */
        private ToolkitImageCreator() {
            super();
        }
    }


    /**
     * Returns a new toolkit imagepool.
     *
     * @return an instance of image pool.
     */
    public static ImagePool newToolkitImagePool() {
        return new ImagePool(new ToolkitImageCreator());
    }


    /**
     * Creates a new instace.
     *
     * @param imagecreator image creator
     */
    public ImagePool(final ImageCreator imagecreator) {
        super();

        creator = imagecreator;
        if (creator == null) {
            throw new IllegalArgumentException("null creator");
        }
    }


    /**
     * open.
     *
     * @return self
     */
    public final synchronized ImagePool open() {

        if (map != null) {
            throw new IllegalStateException("already open");
        }

        map = new HashMap<String, Image>();

        return this;
    }


    /**
     * reopen.
     *
     * @return self
     */
    public final synchronized ImagePool reopen() {
        return close().open();
    }


    /**
     * Close and flush all images.
     *
     * @return self
     */
    public final synchronized ImagePool close() {

        if (map != null) {
            for (Image image : map.values()) {
                image.flush();
            }
            map.clear();
            map = null;
        }

        return this;
    }


    public final synchronized void putImages(final ZipFile zipfile)
        throws IOException {

        if (zipfile == null) {
            throw new IllegalArgumentException("null zipfile");
        }

        if (map == null) {
            throw new IllegalStateException("closed");
        }

        final ByteArrayOutputStream baos = new ByteArrayOutputStream();
        final byte[] buffer = new byte[1024];
        int read = -1;

        final Enumeration<? extends ZipEntry> entries = zipfile.entries();
        while (entries.hasMoreElements()) {

            ZipEntry entry = entries.nextElement();
            if (entry.isDirectory()) {
                continue;
            }

            final InputStream imagestream = zipfile.getInputStream(entry);
            try {
                baos.reset();
                for (read = -1; (read = imagestream.read(buffer)) != -1;) {
                    baos.write(buffer, 0, read);
                }
                baos.flush();
                putImage(entry.getName(), baos.toByteArray());
            } finally {
                imagestream.close();
            }
        }
    }


    public final synchronized void putImages(final ZipInputStream zipstream)
        throws IOException {

        if (zipstream == null) {
            throw new IllegalArgumentException("null zipstream");
        }

        if (map == null) {
            throw new IllegalStateException("closed");
        }

        final ByteArrayOutputStream baos = new ByteArrayOutputStream();
        final byte[] buffer = new byte[1024];
        int read = -1;

        for (ZipEntry entry = null; (entry = zipstream.getNextEntry()) != null;
             zipstream.closeEntry()) {

            if (entry.isDirectory()) {
                continue;
            }

            if (entry.getName().equals("META-INF/MANIFEST.MF")) {
                continue;
            }

            baos.reset();
            for (read= -1; (read = zipstream.read(buffer)) != -1;) {
                baos.write(buffer, 0, read);
            }
            baos.toByteArray();

            putImage(entry.getName(), baos.toByteArray());
        }
    }


    public final synchronized void putImage(final String name,
                                            final URL imageurl)
        throws IOException {

        if (name == null) {
            throw new IllegalArgumentException("null name");
        }

        if (imageurl == null) {
            throw new IllegalArgumentException("null imageurl");
        }

        if (map == null) {
            throw new IllegalStateException("closed");
        }

        putImage(name, imageurl.openStream());
    }



    public final synchronized void putImage(final String name,
                                            final InputStream imagestream)
        throws IOException {

        if (name == null) {
            throw new IllegalArgumentException("null name");
        }

        if (imagestream == null) {
            throw new IllegalArgumentException("null imagestream");
        }

        if (map == null) {
            throw new IllegalStateException("closed");
        }

        final ByteArrayOutputStream baos = new ByteArrayOutputStream();
        final byte[] buffer = new byte[1024];
        for (int read = -1; (read = imagestream.read(buffer)) != -1;) {
            baos.write(buffer, 0, read);
        }
        baos.flush();

        putImage(name, baos.toByteArray());
    }


    /**
     *
     * @param name
     * @param imagedata
     * @throws WidgetException
     */
    public final synchronized void putImage(final String name,
                                            final byte[] imagedata) {

        if (name == null) {
            throw new IllegalArgumentException("null name");
        }

        if (imagedata == null) {
            throw new IllegalArgumentException("null imagedata");
        }

        if (map == null) {
            throw new IllegalStateException("closed");
        }

        final Image image = creator.createImage(imagedata);
        if (image != null) {
            putImage(name, image);
        }
    }


    /**
     * Put an entry.
     *
     * @param name entry name
     * @param image entry value
     */
    public final synchronized void putImage(final String name,
                                            final Image image) {

        if (name == null) {
            throw new IllegalArgumentException("null name");
        }

        if (image == null) {
            throw new IllegalArgumentException("null image");
        }

        if (map == null) {
            throw new IllegalStateException("closed");
        }

        final Image previous = map.put(name, image);
        if (previous != null) {
            previous.flush();
        }
    }


    /**
     * Returns the image mapped to specified <code>name</code>.
     *
     * @param name image name
     * @return the image mapped to given <code>name</code> or null.
     */
    public final synchronized Image getImage(final String name) {

        if (name == null) {
            throw new IllegalArgumentException("null name");
        }

        if (map == null) {
            throw new IllegalStateException("closed");
        }

        return map.get(name);
    }


    /**
     *
     * @param name the entry name
     * @return true if given <code>name</code>d entry exists, false otherwise.
     */
    public final synchronized boolean hasImage(final String name) {

        if (name == null) {
            throw new IllegalArgumentException("null name");
        }

        if (map == null) {
            throw new IllegalStateException("closed");
        }

        return map.containsKey(name);
    }    


    /**
     *
     * @param name entry name
     * @return true if given named entry exists and removed, false otherwise.
     */
    public final synchronized boolean ridImage(final String name) {

        if (name == null) {
            throw new IllegalArgumentException("null name");
        }

        if (map == null) {
            throw new IllegalStateException("closed");
        }

        final Image ridded = map.remove(name);

        if (ridded == null) {
            return false;
        }

        ridded.flush();
        return true;
   }


    private final ImageCreator creator;

    private volatile Map<String, Image> map = null;
}
