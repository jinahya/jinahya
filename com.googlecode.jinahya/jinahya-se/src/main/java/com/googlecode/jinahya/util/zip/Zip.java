/*
 * Copyright 2011 Jin Kwon <jinahya at gmail.com>.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */


package com.googlecode.jinahya.util.zip;


import com.googlecode.jinahya.io.IO;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;


/**
 * Zip Utilities.
 *
 * @author Jin Kwon <jinahya at gmail.com>
 */
public final class Zip {


    /** default buffer size. */
    private static final int DEFAULT_BUFFER_SIZE = 8192;


    /**
     * Makes directories for given file name.
     *
     * @param root root directory
     * @param child child pathname
     * @return the directory or the file
     * @throws IOException if an I/O error occurs.
     */
    private static File mkdirs(final File root, final String child)
        throws IOException {

        if (root == null) {
            throw new NullPointerException("null parent");
        }

        if (!root.isDirectory()) {
            throw new IllegalArgumentException(
                "parent is not an existing directory");
        }

        if (child == null) {
            throw new NullPointerException("null child");
        }

        final File file = new File(root, child);

        if (child.endsWith("/")) {
            if (!file.isDirectory() && !file.mkdirs()) {
                throw new IOException(
                    "failed to create a directory: " + file.getPath());
            }
        } else {
            final int index = child.lastIndexOf('/');
            if (index != -1) {
                final File parent = new File(root, child.substring(0, index));
                if (!parent.isDirectory() && !parent.mkdirs()) {
                    throw new IOException(
                        "failed to create a directory: " + parent.getPath());
                }
            }
        }

        return file;
    }


    /**
     * Compresses all files in <code>root</code> onto <code>stream</code>.
     *
     * @param directory source directory
     * @param stream target stream
     * @throws IOException if an i/O error occurs.
     */
    public static void zip(final File directory, final ZipOutputStream stream)
        throws IOException {

        if (directory == null) {
            throw new NullPointerException("null parent");
        }

        if (!directory.isDirectory()) {
            throw new IllegalArgumentException(
                "parent is not an existing directory");
        }

        if (stream == null) {
            throw new NullPointerException("null stream");
        }

        final StringBuilder path = new StringBuilder();
        final byte[] buffer = new byte[DEFAULT_BUFFER_SIZE];
        for (File file : directory.listFiles()) {
            zip(path, file, stream, buffer);
        }
    }


    /**
     * Compresses given <code>file</code> onto <code>stream</code>.
     *
     * @param path parent path
     * @param file source file
     * @param stream target stream
     * @param buffer buffer to use
     * @throws IOException if an I/O error occurs.
     */
    private static void zip(final StringBuilder path, final File file,
                            final ZipOutputStream stream, final byte[] buffer)
        throws IOException {

        if (path == null) {
            throw new NullPointerException("null path");
        }

        if (file == null) {
            throw new NullPointerException("null file");
        }

        if (!file.exists()) {
            throw new IllegalArgumentException("file does not exist");
        }

        if (stream == null) {
            throw new NullPointerException("null stream");
        }

        if (buffer == null) {
            throw new NullPointerException("null buffer");
        }

        if (buffer.length == 0) {
            throw new IllegalArgumentException("zero-length buffer");
        }

        if (file.isDirectory()) {
            stream.putNextEntry(new ZipEntry(path + file.getName() + "/"));
            stream.closeEntry();
            final int length = path.length();
            path.append(file.getName()).append("/");
            for (File child : file.listFiles()) {
                zip(path, child, stream, buffer);
            }
            path.delete(length, path.length());
        } else {
            stream.putNextEntry(new ZipEntry(path + file.getName()));
            IO.copy(file, stream, buffer, -1L);
            stream.closeEntry();
        }
    }


    /**
     * Decompresses entries in <code>stream</code> into <code>root</code>.
     *
     * @param stream source zip stream
     * @param directory target directory to which entries going to be
     * decompressed
     * @throws IOException if an I/O error occurs.
     */
    public static void unzip(final ZipInputStream stream, final File directory)
        throws IOException {

        if (stream == null) {
            throw new NullPointerException("null stream");
        }

        if (directory == null) {
            throw new NullPointerException("null directory");
        }

        if (!directory.isDirectory()) {
            throw new IllegalArgumentException("root doesn't exist");
        }

        final byte[] buffer = new byte[DEFAULT_BUFFER_SIZE];
        for (ZipEntry entry = null; (entry = stream.getNextEntry()) != null;
             stream.closeEntry()) {
            final File file = mkdirs(directory, entry.getName());
            if (entry.isDirectory()) {
                assert file.isDirectory();
            } else {
                IO.copy(stream, file, buffer, -1L);
            }
        }
    }


    /**
     * Decompress entries in <code>zipfile</code> into <code>root</code>.
     *
     * @param zipfile source zipfile
     * @param directory target directory to which entries going to be
     * decompressed.
     * @throws IOException if an I/O error occurs.
     */
    public static void unzip(final ZipFile zipfile, final File directory)
        throws IOException {

        if (zipfile == null) {
            throw new NullPointerException("null zipfile");
        }

        if (directory == null) {
            throw new NullPointerException("null directory");
        }

        if (!directory.isDirectory()) {
            throw new IllegalArgumentException(
                "directory is not an existing directory");
        }

        final byte[] buffer = new byte[DEFAULT_BUFFER_SIZE];
        for (final Enumeration<? extends ZipEntry> entries = zipfile.entries();
             entries.hasMoreElements();) {
            final ZipEntry entry = entries.nextElement();
            final File file = mkdirs(directory, entry.getName());
            if (entry.isDirectory()) {
                assert file.isDirectory();
            } else {
                final InputStream input = zipfile.getInputStream(entry);
                try {
                    IO.copy(input, file, buffer, -1L);
                } finally {
                    input.close();
                }
            }
        }
    }


    /**
     * Creates a new instance.
     */
    private Zip() {
        super();
    }


}

