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

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;


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
     * @return the directory
     * @throws IOException if an I/O error occurs.
     */
    private static File mkdirs(final File root, final String child)
        throws IOException {

        return mkdirs(root, child, child.endsWith("/"));
    }


    /**
     * Makes directories for given file name.
     *
     * @param root root directory
     * @param child child pathname
     * @param directory directory flag
     * @return the directory
     * @throws IOException if an I/O error occurs.
     */
    private static File mkdirs(final File root, final String child,
                               final boolean directory)
        throws IOException {

        if (root == null) {
            throw new NullPointerException("null root");
        }

        if (!root.isDirectory()) {
            throw new IllegalArgumentException("root is not a directory");
        }

        if (child == null) {
            throw new NullPointerException("null child");
        }

        final File file = new File(root, child);

        if (directory) {
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
     * Decompresses entries in <code>stream</code> into <code>root</code>.
     *
     * @param stream source zip stream
     * @param root target directory to which entries going to be decompressed
     * @throws IOException if an I/O error occurs.
     */
    public static void unzip(final InputStream stream, final File root)
        throws IOException {

        unzip(stream, StandardCharsets.UTF_8, root);
    }


    /**
     * Decompresses entries in <code>stream</code> into <code>root</code>.
     *
     * @param stream source zip stream
     * @param charset zip charset
     * @param root target directory to which entries going to be decompressed
     * @throws IOException if an I/O error occurs.
     */
    public static void unzip(final InputStream stream, final Charset charset,
                             final File root)
        throws IOException {

        if (stream == null) {
            throw new NullPointerException("null stream");
        }

        if (charset == null) {
            throw new NullPointerException("null charset");
        }

        unzip(new ZipInputStream(stream, charset), root);
    }


    /**
     * Decompresses entries in <code>stream</code> into <code>root</code>.
     *
     * @param stream source zip stream
     * @param root target directory to which entries going to be decompressed
     * @throws IOException if an I/O error occurs.
     */
    public static void unzip(final ZipInputStream stream, final File root)
        throws IOException {

        if (stream == null) {
            throw new NullPointerException("null stream");
        }

        if (root == null) {
            throw new NullPointerException("null directory");
        }

        if (!root.isDirectory()) {
            throw new IllegalArgumentException("root doesn't exist");
        }

        final byte[] buffer = new byte[DEFAULT_BUFFER_SIZE];
        for (ZipEntry entry; (entry = stream.getNextEntry()) != null;) {
            final boolean directory = entry.isDirectory();
            final File file = mkdirs(root, entry.getName(), directory);
            if (!directory) {
                IO.copy(stream, file, buffer);
            }
        }
    }


    /**
     * Decompresses entries in <code>file</code> into <code>root</code>.
     *
     * @param file source zip file
     * @param root target directory to which entries going to be decompressed
     * @throws IOException if an I/O error occurs.
     */
    public static void unzip(final File file, final File root)
        throws IOException {

        unzip(file, StandardCharsets.UTF_8, root);
    }


    /**
     * Decompresses entries in <code>file</code> into <code>root</code>.
     *
     * @param file source zip file
     * @param charset zip charset
     * @param root target directory to which entries going to be decompressed
     * @throws IOException if an I/O error occurs.
     */
    public static void unzip(final File file, final Charset charset,
                             final File root)
        throws IOException {

        if (file == null) {
            throw new NullPointerException("null file");
        }

        if (!file.isFile()) {
            throw new IllegalArgumentException("file doesn't exist");
        }

        if (charset == null) {
            throw new NullPointerException("null charset");
        }

        final ZipFile zipfile = new ZipFile(file, ZipFile.OPEN_READ, charset);
        try {
            unzip(zipfile, root);
        } finally {
            zipfile.close();
        }
    }


    /**
     * Decompress entries in <code>zipfile</code> into <code>root</code>.
     *
     * @param zipfile source zipfile
     * @param root target directory to which entries going to be decompressed.
     * @throws IOException if an I/O error occurs.
     */
    public static void unzip(final ZipFile zipfile, final File root)
        throws IOException {

        if (zipfile == null) {
            throw new NullPointerException("null zipfile");
        }

        if (root == null) {
            throw new NullPointerException("null directory");
        }

        if (!root.isDirectory()) {
            throw new IllegalArgumentException("root doesn't exist");
        }

        final byte[] buffer = new byte[DEFAULT_BUFFER_SIZE];
        final Enumeration<? extends ZipEntry> entries = zipfile.entries();
        while (entries.hasMoreElements()) {
            final ZipEntry entry = entries.nextElement();
            final boolean directory = entry.isDirectory();
            final File file = mkdirs(root, entry.getName(), directory);
            if (!directory) {
                final InputStream input = zipfile.getInputStream(entry);
                try {
                    IO.copy(input, file, buffer);
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

