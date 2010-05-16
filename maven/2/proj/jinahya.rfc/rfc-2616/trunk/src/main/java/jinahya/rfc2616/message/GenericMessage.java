/*
 *  Copyright 2010 onacit.
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
package jinahya.rfc2616.message;


import java.io.ByteArrayOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;


/**
 *
 * @author <a href="mailto:jinahya@gmail.com">Jin Kwon</a>
 */
public abstract class GenericMessage {


    /** NEW LINE. */
    private static final byte[] CRLF = new byte[] {0x0D, 0x0A};


    /** CHARACTER BUFFER. */
    private static final StringBuffer BUFFER = new StringBuffer();


    /**
     * .
     * @param stream sdfdsaf
     * @return sdfds
     * @throws IOException safasdf
     */
    private static final String readLine(final InputStream stream)
        throws IOException {

        BUFFER.delete(0, BUFFER.length());
        while (true) {
            int ch = stream.read();
            if (ch == CRLF[0]) { // CR
                stream.read(); // LF
                break;
            }
            BUFFER.append((char) ch);
        }
        return BUFFER.toString();
    }


    /**
     *
     */
    public static final class MessageHeaders {


        /**
         *
         * @return
         */
        public Object[] getFieldNames() {
            return fieldMap.keySet().toArray();
        }


        /**
         *
         * @param fieldName
         * @return
         */
        public Set getFieldValues(final Object fieldName) {
            Set fieldValues = (Set) fieldMap.get(fieldName);
            if (fieldValues == null) {
                fieldValues = new LinkedHashSet();
                fieldMap.put(fieldName, fieldValues);
            }
            return fieldValues;
        }


        /**
         *
         * @param stream
         * @throws IOException
         */
        private void read(final InputStream stream) throws IOException {

            fieldMap.clear();

            final List lines = new ArrayList();

            // ----------------------------------------------------------- LINES
            while (true) {
                String line = readLine(stream);
                if (line == null) {
                    throw new EOFException("EOF in headers");
                }
                if (line.length() == 0) {
                    break;
                }
                lines.add(line);
            }

            // ------------------------------------------------------- UNFOLDING
            final StringBuffer buffer = new StringBuffer();
            for (int size = lines.size(); size > 0;) {
                buffer.delete(0, buffer.length());
                buffer.append(lines.remove(0));
                size--;
                while (size > 0) {
                    final char c = ((String) lines.get(0)).charAt(0);
                    if (c == 0x20 || c == 0x09) {
                        buffer.append(" " + ((String) lines.remove(0)).trim());
                        size--;
                    } else {
                        break;
                    }
                }
                lines.add(buffer.toString());
            }

            // -------------------------------------------------------- TOKENIZE
            for (int i = 0; i < lines.size(); i++) {
                String line = (String) lines.get(i);
                int colon = line.indexOf(':');
                if (colon == -1) {
                    System.err.println("Unacceptable message-header: " + line);
                    continue;
                }
                final String fieldName = line.substring(0, colon);
                final Set fieldValues = getFieldValues(fieldName);
                final String fieldValue = line.substring(colon + 1);
                StringTokenizer tokenizer =
                    new StringTokenizer(fieldValue, ",");
                while (tokenizer.hasMoreTokens()) {
                    fieldValues.add(tokenizer.nextToken().trim());
                }
            }
        }


        /**
         *
         * @param stream
         * @throws IOException
         */
        private void write(final OutputStream stream) throws IOException {
            Iterator fieldNames = fieldMap.keySet().iterator();
            while (fieldNames.hasNext()) {
                String fieldName = (String) fieldNames.next();
                stream.write((fieldName + ":").getBytes());
                Iterator fieldValues =
                    ((Set) fieldMap.get(fieldName)).iterator();
                if (fieldValues.hasNext()) {
                    stream.write(((String) fieldValues.next()).getBytes());
                }
                while (fieldValues.hasNext()) {
                    stream.write(("," + fieldValues.next()).getBytes());
                }
                stream.write(CRLF);
            }
            stream.write(CRLF);
        }


        /**
         * 
         * @param fieldName
         * @return
         */
        public final boolean containsMessageHeader(final Object fieldName) {
            return fieldMap.containsKey(fieldName);
        }


        // <String, Set<String>>
        private Map fieldMap = new LinkedHashMap();
    }


    /**
     *
     * @return
     */
    public abstract String getStartLine();


    /**
     *
     * @param startLine
     */
    protected abstract void setStartLine(final String startLine);


    /**
     *
     * @return
     */
    public final MessageHeaders getMessageHeaders() {
        if (messageHeaders == null) {
            messageHeaders = new MessageHeaders();
        }
        return messageHeaders;
    }


    /**
     *
     * @param messageHeaders
     */
    public final void setMessageHeaders(final MessageHeaders messageHeaders) {
        this.messageHeaders = messageHeaders;
    }


    /**
     *
     * @return
     */
    public final MessageBody getMessageBody() {
        return messageBody;
    }


    /**
     *
     * @param messageBody
     */
    public final void setMessageBody(final MessageBody messageBody) {
        if (messageBody == null) {
            throw new NullPointerException("messageBody");
        }
        this.messageBody = messageBody;
    }


    /**
     *
     * @param stream
     * @return
     * @throws IOException
     */
    public GenericMessage read(final InputStream stream) throws IOException {

        // ---------------------------------------------------------- START LINE
        setStartLine(readLine(stream));

        // ----------------------------------------------------- MESSAGE HEADERs
        getMessageHeaders().read(stream);

        // -------------------------------------------------------- MESSAGE BODY
        messageBody.read(this, stream);

        return this;
    }


    /**
     *
     * @param stream
     * @return
     * @throws IOException
     */
    public GenericMessage write(final OutputStream stream) throws IOException {

        // ---------------------------------------------------------- START LINE
        stream.write(getStartLine().getBytes());
        stream.write(CRLF);

        // ----------------------------------------------------- MESSAGE HEADERs
        getMessageHeaders().write(stream);

        // -------------------------------------------------------- MESSAGE BODY
        messageBody.write(this, stream);

        return this;
    }


    private MessageHeaders messageHeaders;
    private MessageBody messageBody = new BufferedMessageBody();
}
