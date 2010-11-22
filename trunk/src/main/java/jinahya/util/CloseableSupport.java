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

package jinahya.util;


/**
 *
 * @author <a href="mailto:jinahya@gmail.com">Jin Kwon</a>
 */
public class CloseableSupport<T extends Closeable> implements Closeable {


    /**
     *
     * @param closeable
     */
    public CloseableSupport(final T closeable) {
        super();

        if (closeable == null) {
            throw new IllegalArgumentException("null closeable");
        }

        this.closeable = closeable;
    }


    @Override
    public synchronized void open() {

        if (closed) {
            closed = false;
        }
    }


    @Override
    public synchronized void close() {

        if (!closed) {
            closed = true;
        }
    }


    @Override
    public synchronized boolean isClosed() {
        return closed;
    }


    /**
     * This method throws an IllegalStateException if this support is closed.
     */
    public synchronized void check() {
        if (closed) {
            throw new IllegalStateException("closed");
        }
    }


    private final T closeable;

    private volatile boolean closed = true;
}
