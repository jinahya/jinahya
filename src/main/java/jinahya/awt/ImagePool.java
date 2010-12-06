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
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;

import jinahya.util.Closeable;
import jinahya.util.CloseableSupport;


/**
 *
 * @author <a href="mailto:jinahya@gmail.com">Jin Kwon</a>
 */
public class ImagePool implements Closeable {


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


        /**
         * 
         * @param imagedata
         * @param imageoffset
         * @param imagelength
         * @return
         */
        Image createImage(byte[] imagedata, int imageoffset, int imagelength);
    }


    /**
     * An ImageCreator implementation using the Toolkit.
     */
    private static final class TC implements ImageCreator {


        /** default toolkit. */
        private static final Toolkit TOOLKIT = Toolkit.getDefaultToolkit();


        /** private. */
        private TC() {
            super();
        }


        @Override
        public Image createImage(final byte[] imagedata) {

            return createImage(imagedata, 0, imagedata.length);
        }


        @Override
        public Image createImage(final byte[] imagedata, final int imageoffset,
                                 final int imagelength) {

            if (imagedata == null) {
                throw new IllegalArgumentException("null imagedata");
            }

            return TOOLKIT.createImage(imagedata, imageoffset, imagelength);
        }
    }


    /**
     * Returns a new toolkit imagepool.
     *
     * @return an instance of image pool.
     */
    public static ImagePool newToolkitImagePool() {

        return new ImagePool(new TC()) {


            @Override
            public ImagePool put(final String name, final URL imageurl)
                throws IOException {

                if (name == null) {
                    throw new IllegalArgumentException("null name");
                }

                if (imageurl == null) {
                    throw new IllegalArgumentException("null imageurl");
                }

                synchronized (super.support) {

                    super.support.check();

                    return put(name, TC.TOOLKIT.createImage(imageurl));
                }
            }
        };
    }


    /**
     * Creates a new instace.
     *
     * @param creator image creator
     */
    public ImagePool(final ImageCreator creator) {
        super();

        if (creator == null) {
            throw new IllegalArgumentException("null creator");
        }

        this.creator = creator;

        support = new CloseableSupport<ImagePool>(this);
        map = new HashMap<String, Image>();
    }


    /**
     * open.
     *
     * @return self
     */
    @Override
    public void open() {

        synchronized (support) {

            support.open();
        }
    }


    /**
     * reopen.
     *
     * @return self
     */
    public void reopen() {

        synchronized (support) {

            close();
            open();
        }
    }


    public void clear() {
        synchronized (map) {
            for (Image image : map.values()) {
                image.flush();
            }
            map.clear();
        }
    }


    /**
     * Close and flush all images.
     *
     * @return self
     */
    @Override
    public void close() {

        synchronized (support) {

            if (!isClosed()) {

                for (Image image : map.values()) {
                    image.flush();
                }
                map.clear();

                support.close();
            }
        }
    }


    @Override
    public boolean isClosed() {

        synchronized (support) {
            return support.isClosed();
        }
    }


    /**
     *
     * @param name
     * @param file
     * @return self
     * @throws IOException
     */
    public ImagePool put(final String name, final File file)
        throws IOException {

        if (name == null) {
            throw new IllegalArgumentException("null name");
        }

        if (file == null) {
            throw new IllegalArgumentException("null file");
        }

        if (!file.isFile()) {
            throw new IllegalArgumentException("file not exist");
        }

        synchronized (support) {

            support.check();

            final InputStream imagestream = new FileInputStream(file);
            try {
                return put(name, imagestream);
            } finally {
                imagestream.close();
            }
        }
    }


    /**
     *
     * @param zipfile
     * @return self
     * @throws IOException
     */
    public ImagePool put(final ZipFile zipfile) throws IOException {

        if (zipfile == null) {
            throw new IllegalArgumentException("null zipfile");
        }

        synchronized (support) {

            support.check();

            final Enumeration<? extends ZipEntry> entries = zipfile.entries();
            while (entries.hasMoreElements()) {

                final ZipEntry entry = entries.nextElement();

                if (entry.isDirectory()
                    || entry.getName().equals("META-INF/MANIFEST.MF")) {
                    continue;
                }

                final InputStream imagestream = zipfile.getInputStream(entry);
                try {
                    put(entry.getName(), imagestream);
                } finally {
                    imagestream.close();
                }
            }

            return this;
        }
    }


    /**
     *
     * @param zipstream
     * @return self
     * @throws IOException
     */
    public ImagePool put(final ZipInputStream zipstream) throws IOException {

        if (zipstream == null) {
            throw new IllegalArgumentException("null zipstream");
        }

        synchronized (support) {

            support.check();

            for (ZipEntry entry = null;
                 (entry = zipstream.getNextEntry()) != null;
                 zipstream.closeEntry()) {

                if (entry.isDirectory()
                    || entry.getName().equals("META-INF/MANIFEST.MF")) {
                    continue;
                }

                put(entry.getName(), zipstream);
            }

            return this;
        }
    }


    /**
     *
     * @param name
     * @param imageurl
     * @self
     * @throws IOException
     */
    public ImagePool put(final String name, final URL imageurl)
        throws IOException {

        if (name == null) {
            throw new IllegalArgumentException("null name");
        }

        if (imageurl == null) {
            throw new IllegalArgumentException("null imageurl");
        }

        synchronized (support) {

            support.check();

            return put(name, imageurl.openStream());
        }
    }


    /**
     *
     * @param name
     * @param imagestream
     * @return self
     * @throws IOException
     */
    public ImagePool put(final String name, final InputStream imagestream)
        throws IOException {

        if (name == null) {
            throw new IllegalArgumentException("null name");
        }

        if (imagestream == null) {
            throw new IllegalArgumentException("null imagestream");
        }

        synchronized (support) {

            support.check();

            final ByteArrayOutputStream baos = new ByteArrayOutputStream();
            final byte[] buffer = new byte[8192];
            for (int read = -1; (read = imagestream.read(buffer)) != -1;) {
                baos.write(buffer, 0, read);
            }
            baos.flush();

            return put(name, baos.toByteArray());
        }
    }


    /**
     *
     * @param name
     * @param imagedata
     * @return self
     * @throws WidgetException
     */
    public ImagePool put(final String name, final byte[] imagedata) {

        return put(name, imagedata, 0, imagedata.length);
    }


    /**
     * 
     * @param name
     * @param imagedata
     * @param imageoffset
     * @param imagelength
     * @return
     */
    public ImagePool put(final String name, final byte[] imagedata,
                         final int imageoffset, final int imagelength) {

        if (name == null) {
            throw new IllegalArgumentException("null name");
        }

        if (imagedata == null) {
            throw new IllegalArgumentException("null imagedata");
        }

        synchronized (support) {

            support.check();

            final Image image = creator.createImage(
                imagedata, imageoffset, imagelength);

            if (image != null) {
                put(name, image);
            }

            return this;
        }
    }


    /**
     * Put an entry.
     *
     * @param name entry name
     * @param image entry value
     * @return self
     */
    public ImagePool put(final String name, final Image image) {

        if (name == null) {
            throw new IllegalArgumentException("null name");
        }

        if (image == null) {
            throw new IllegalArgumentException("null image");
        }

        synchronized (support) {

            support.check();

            final Image previous = map.put(name, image);

            if (previous != null) {
                previous.flush();
            }

            return this;
        }
    }


    /**
     * Returns the image mapped to specified <code>name</code>.
     *
     * @param name image name
     * @return the image mapped to given <code>name</code> or null.
     */
    public Image get(final String name) {

        if (name == null) {
            throw new IllegalArgumentException("null name");
        }

        synchronized (support) {

            support.check();

            return map.get(name);
        }
    }


    /**
     *
     * @param name the entry name
     * @return true if given <code>name</code>d entry exists, false otherwise.
     */
    public boolean has(final String name) {

        if (name == null) {
            throw new IllegalArgumentException("null name");
        }

        synchronized (support) {

            support.check();

            return map.containsKey(name);
        }
    }


    /**
     * Remove image mapped to given <code>name</code>.
     *
     * @param name entry name
     * @return true if given named entry exists and removed, false otherwise.
     */
    public boolean rid(final String name) {

        if (name == null) {
            throw new IllegalArgumentException("null name");
        }

        synchronized (support) {

            support.check();

            if (!map.containsKey(name)) {
                return false;
            }

            map.remove(name).flush();

            return true;
        }
    }


    /**
     *
     * @return
     * @deprecated use {@link #names()} instead.
     */
    public String[] getNames() {

        return names();
    }


    /**
     * Returns all image names.
     *
     * @return image names.
     */
    public String[] names() {

        synchronized (support) {

            support.check();

            return map.keySet().toArray(new String[map.size()]);
        }
    }


    /**
     *
     * @param tracker
     * @param id
     * @return self
     */
    public ImagePool load(final MediaTracker tracker, final int id)
        throws InterruptedException {

        if (tracker == null) {
            throw new IllegalArgumentException("null tracker");
        }

        synchronized (support) {

            support.check();

            for (String name : names()) {
                tracker.addImage(map.get(name), id);
            }

            try {
                tracker.waitForID(id);
            } finally {
                for (String name : names()) {
                    tracker.removeImage(map.get(name), id);
                }
            }

            return this;
        }
    }


    /**
     *
     * @param component
     * @return self
     * @throws InterruptedException
     */
    public ImagePool load(final Component component)
        throws InterruptedException {

        if (component == null) {
            throw new IllegalArgumentException("null component");
        }

        synchronized (support) {

            support.check();

            return load(new MediaTracker(component), 0);
        }
    }


    private final ImageCreator creator;


    private final CloseableSupport<ImagePool> support;


    private final Map<String, Image> map;
}
