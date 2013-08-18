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


import com.googlecode.jinahya.io.Streams;
import java.io.File;
import java.io.FileFilter;
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
public class Zips {


    protected static File file(final File root, final ZipEntry entry)
        throws IOException {

        if (root == null) {
            throw new NullPointerException("root");
        }

        if (!root.isDirectory()) {
            throw new IllegalArgumentException(
                "root is not an existing directory");
        }

        if (entry == null) {
            throw new NullPointerException("entry");
        }

        final File file = new File(root, entry.getName());

        File parent = file;
        if (!entry.isDirectory()) {
            final String name = entry.getName();
            final int index = name.lastIndexOf('/');
            if (index != -1) {
                parent = new File(root, name.substring(0, index));
            }
        }
        if (parent != null && !parent.isDirectory() && !parent.mkdirs()) {
            throw new IOException(
                "failed to create a directory: " + parent.getPath());
        }


        return file;
    }


    protected static void zip(final StringBuilder path, final File file,
                              final FileFilter filter,
                              final ZipOutputStream stream, final byte[] buffer)
        throws IOException {

        if (path == null) {
            throw new NullPointerException("path");
        }

        if (file == null) {
            throw new NullPointerException("file");
        }

        if (!file.exists()) {
            throw new IllegalArgumentException("file does not exist");
        }

        if (filter == null) {
            //throw new NullPointerException("filter");
        }

        if (stream == null) {
            throw new NullPointerException("stream");
        }

        if (buffer == null) {
            throw new NullPointerException("buffer");
        }

        if (buffer.length == 0) {
            throw new IllegalArgumentException(
                "buffer.length(" + buffer.length + ") == 0");
        }

        if (file.isDirectory()) {
            stream.putNextEntry(new ZipEntry(path + file.getName() + "/"));
            stream.closeEntry();
            final int start = path.length();
            path.append(file.getName()).append("/");
            for (File child : file.listFiles()) {
                if (filter != null && !filter.accept(child)) {
                    continue;
                }
                zip(path, child, filter, stream, buffer);
            }
            path.delete(start, path.length());
        } else {
            stream.putNextEntry(new ZipEntry(path + file.getName()));
            Streams.copy(file, stream, buffer, -1L);
            stream.closeEntry();
        }
    }


    public static void zip(final File directory, final FileFilter filter,
                           final ZipOutputStream stream, final byte[] buffer)
        throws IOException {

        if (directory == null) {
            throw new NullPointerException("directory");
        }

        if (!directory.isDirectory()) {
            throw new IllegalArgumentException("directory doesn't exist");
        }

        if (filter == null) {
            //throw new NullPointerException("filter");
        }

        if (stream == null) {
            throw new NullPointerException("stream");
        }

        if (buffer == null) {
            throw new NullPointerException("buffer");
        }

        if (buffer.length == 0) {
            throw new IllegalArgumentException(
                "buffer.length(" + buffer.length + ") == 0");
        }

        final StringBuilder path = new StringBuilder();
        for (File file : directory.listFiles()) {
            if (filter != null && !filter.accept(file)) {
                continue;
            }
            zip(path, file, filter, stream, buffer);
        }
    }


    /**
     * Unzips entries from {@code stream} into {@code directory}.
     *
     * @param stream source zip stream
     * @param directory target directory to which entries going to be unzipped.
     * @param buffer the buffer to use
     *
     * @throws IOException if an I/O error occurs.
     */
    public static void unzip(final ZipInputStream stream, final File directory,
                             final byte[] buffer)
        throws IOException {

        if (stream == null) {
            throw new NullPointerException("stream");
        }

        if (directory == null) {
            throw new NullPointerException("directory");
        }

        if (!directory.isDirectory()) {
            throw new IllegalArgumentException("directory doesn't exist");
        }

        if (buffer == null) {
            throw new NullPointerException("buffer");
        }

        if (buffer.length == 0) {
            throw new IllegalArgumentException(
                "buffer.length(" + buffer.length + ") == 0");
        }

        ZipEntry entry;
        while ((entry = stream.getNextEntry()) != null) {
            final File file = file(directory, entry);
            if (!entry.isDirectory()) {
                Streams.copy(stream, file, buffer, -1L);
            }
            stream.closeEntry();
        }
    }


    /**
     * Unzips entries from {@code zipfile} into {@code directory}.
     *
     * @param zipfile source zip file
     * @param directory target directory to which entries going to be unzipped
     * @param buffer
     *
     * @throws IOException if an I/O error occurs.
     */
    public static void unzip(final ZipFile zipfile, final File directory,
                             final byte[] buffer)
        throws IOException {

        if (zipfile == null) {
            throw new NullPointerException("zipfile");
        }

        if (directory == null) {
            throw new NullPointerException("directory");
        }

        if (!directory.isDirectory()) {
            throw new IllegalArgumentException("directory doesn't exist");
        }

        if (buffer == null) {
            throw new NullPointerException("buffer");
        }

        if (buffer.length == 0) {
            throw new IllegalArgumentException(
                "buffer.length(" + buffer.length + ") == 0");
        }

        final Enumeration<? extends ZipEntry> entries = zipfile.entries();
        while (entries.hasMoreElements()) {
            final ZipEntry entry = entries.nextElement();
            final File file = file(directory, entry);
            if (entry.isDirectory()) {
                continue;
            }
            final InputStream input = zipfile.getInputStream(entry);
            try {
                Streams.copy(input, file, buffer, -1L);
            } finally {
                input.close();
            }
        }
    }


    /**
     * Creates a new instance.
     */
    protected Zips() {

        super();
    }


}

