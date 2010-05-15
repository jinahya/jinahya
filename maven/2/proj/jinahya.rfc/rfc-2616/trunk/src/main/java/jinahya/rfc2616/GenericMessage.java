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
package jinahya.rfc2616;


import java.io.ByteArrayOutputStream;
import java.io.EOFException;
import java.io.InputStreamReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
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
            if (ch == 0x0D) { // CR
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
    public static class MessageHeaders {


        /**
         *
         * @param fieldName
         * @return
         */
        public final Set<String> getFieldValues(final String fieldName) {
            Set<String> fieldValues = fieldMap.get(fieldName);
            if (fieldValues == null) {
                fieldValues = new LinkedHashSet<String>();
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

            final List<String> lines = new ArrayList<String>();

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
                    final char c = lines.get(0).charAt(0);
                    if (c == 0x20 || c == 0x09) {
                        buffer.append(" " + lines.remove(0).trim());
                        size--;
                    } else {
                        break;
                    }
                }
                lines.add(buffer.toString());
            }

            // -------------------------------------------------------- TOKENIZE
            for (String line : lines) {
                int colon = line.indexOf(':');
                if (colon == -1) {
                    System.err.println("Unacceptable message-header: " + line);
                    continue;
                }
                final String fieldName = line.substring(0, colon);
                final Set<String> fieldValues = getFieldValues(fieldName);
                final String fieldValue = line.substring(colon + 1);
                StringTokenizer tokenizer = new StringTokenizer(fieldValue, ",");
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
            for (String fieldName : fieldMap.keySet()) {
                stream.write((fieldName + ":").getBytes());
                Iterator<String> fieldValues =
                    fieldMap.get(fieldName).iterator();
                if (fieldValues.hasNext()) {
                    stream.write(fieldValues.next().getBytes());
                }
                while (fieldValues.hasNext()) {
                    stream.write(("," + fieldValues.next()).getBytes());
                }
                stream.write(CRLF);
            }
            stream.write(CRLF);
        }


        private Map<String, Set<String>> fieldMap =
            new LinkedHashMap<String, Set<String>>();
    }


    /**
     *
     * @return
     */
    protected final String getStartLine() {
        return startLine;
    }


    /**
     *
     * @param startLine
     */
    protected final void setStartLine(final String startLine) {
        this.startLine = startLine;
    }


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
     * @return
     */
    public final byte[] getMessageBody() {
        return messageBody;
    }


    /**
     *
     * @param messageBody
     */
    public final void setMessageBody(final byte[] messageBody) {
        this.messageBody = messageBody;
    }


    /**
     *
     * @param messageBody
     * @throws IOException
     */
    public final void setMessageBody(final InputStream messageBody)
        throws IOException {

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        byte[] buffer = new byte[bufferSize];
        int read = -1;
        while ((read = messageBody.read(buffer)) != -1) {
            baos.write(buffer, 0, read);
        }
        this.messageBody = baos.toByteArray();
    }


    /**
     *
     * @param stream
     * @throws IOException
     */
    public void read(final InputStream stream) throws IOException {

        // ---------------------------------------------------------- START LINE
        startLine = readLine(stream);

        // ----------------------------------------------------- MESSAGE HEADERs
        getMessageHeaders().read(stream);

        // -------------------------------------------------------- MESSAGE BODY
        setMessageBody(stream);
    }


    /**
     *
     * @param stream
     * @throws IOException
     */
    public void write(final OutputStream stream) throws IOException {

        // ---------------------------------------------------------- START LINE
        stream.write(startLine.getBytes());
        stream.write(CRLF);

        // ----------------------------------------------------- MESSAGE HEADERs
        getMessageHeaders().write(stream);

        // -------------------------------------------------------- MESSAGE BODY
        stream.write(messageBody);
    }


    /**
     *
     * @return
     */
    public final int getBuffereSize() {
        return bufferSize;
    }


    /**
     *
     * @param bufferSize
     */
    public final void setBuffereSize(final int bufferSize) {
        if (bufferSize <= 0) {
            throw new IllegalArgumentException(
                "bufferSize(" + bufferSize + ") <= 0");
        }
        this.bufferSize = bufferSize;
    }


    @Override
    public String toString() {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try {
            write(baos);
            return new String(baos.toByteArray(), "US-ASCII");
        } catch (IOException ioe) {
            //ioe.printStackTrace();
        }

        return super.toString();
    }


    private String startLine;
    private MessageHeaders messageHeaders;
    private byte[] messageBody;

    private int bufferSize = 1024;
}
