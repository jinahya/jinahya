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

package jinahya.xml;


/**
 *
 * @author <a href="mailto:jinahya@gmail.com">Jin Kwon</a>
 * @param <R> input reader type
 * @param <W> output writer type
 */
public interface XMLParsable<R, W> {


    /**
     * Reads values from given <code>reader</code>.
     *
     * @param reader XML reader
     * @throws Exception if any error occurs
     */
    void read(R reader) throws Exception;


    /**
     * Writes values to given <code>writer</code>.
     *
     * @param writer XML writer
     * @throws Exception if any error occurs.
     */
    void write(W writer) throws Exception;
}
