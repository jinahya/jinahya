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
package jinahya.rfc2616.message;


//import java.io.ByteArrayOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.StringWriter;
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


    private static String LINE_SEPARATOR;

    static {
        LINE_SEPARATOR = System.getProperty("line.separator");
        if (LINE_SEPARATOR == null) {
            LINE_SEPARATOR = new String(CRLF);
        }
    }


    /**
     * .
     * @param stream sdfdsaf
     * @return sdfds
     * @throws IOException safasdf
     */
    private static final String readLine(final InputStream stream)
        throws IOException {

        final StringBuffer buffer = new StringBuffer();
        while (true) {
            int ch = stream.read();
            if (ch == CRLF[0]) { // CR
                stream.read(); // LF
                break;
            }
            buffer.append((char) ch);
        }
        return buffer.toString();
    }


    /**
     *
     */
    public static final class MessageHeaders {


        /**
         *
         */
        public void clear() {
            fields.clear();
        }


        /**
         *
         * @return
         */
        public String[] getFieldNames() {
            return (String[]) fields.keySet().toArray();
        }


        /**
         *
         * @param name
         * @return
         */
        public Set<String> getFieldValues(final String name) {
            Set<String> values = fields.get(name);
            if (values == null) {
                values = new LinkedHashSet<String>();
                fields.put(name, values);
            }
            return values;
        }


        /**
         *
         * @param stream
         * @throws IOException
         */
        private void read(final InputStream stream) throws IOException {

            clear();

            final List<String> lines = new ArrayList<String>();

            // ----------------------------------------------------------- LINES
            while (true) {
                final String line = readLine(stream);
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
                final int colon = line.indexOf(':');
                if (colon == -1) {
                    System.err.println("Unacceptable message-header: " + line);
                    continue;
                }
                final String name = line.substring(0, colon);
                final Set<String> fieldValues = getFieldValues(name);
                final String fieldValue = line.substring(colon + 1);
                final StringTokenizer tokenizer =
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
            final Iterator<String> nameIter = fields.keySet().iterator();
            while (nameIter.hasNext()) {
                final String name = nameIter.next();
                final Set<String> values = fields.get(name);
                if (values.isEmpty()) {
                    continue;
                }
                stream.write((name + ":").getBytes());

                final Iterator<String> valueIter = values.iterator();

                if (valueIter.hasNext()) {
                    stream.write((valueIter.next()).getBytes());
                }
                while (valueIter.hasNext()) {
                    stream.write(("," + valueIter.next()).getBytes());
                }
                stream.write(CRLF);
            }
            stream.write(CRLF);
        }


        @Override
        public String toString() {
            final StringBuffer buffer = new StringBuffer();
            final Iterator<String> nameIter = fields.keySet().iterator();
            while (nameIter.hasNext()) {
                final String name = nameIter.next();
                final Set<String> values = fields.get(name);
                if (values.isEmpty()) {
                    continue;
                }
                buffer.append(name + ":");

                final Iterator<String> valueIter = values.iterator();

                if (valueIter.hasNext()) {
                    buffer.append(valueIter.next());
                }
                while (valueIter.hasNext()) {
                    buffer.append("," + valueIter.next());
                }
                buffer.append(LINE_SEPARATOR);
            }
            buffer.append(LINE_SEPARATOR);

            return buffer.toString();
        }


        /**
         * 
         * @param name
         * @return
         */
        public final boolean containsField(final String name) {
            return fields.containsKey(name);
        }


        // <String, Set<String>>
        private Map<String, Set<String>> fields =
            new LinkedHashMap<String, Set<String>>();
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
        if (messageBody != null) {
            messageBody.read(this, stream);
        }

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
        if (messageBody != null) {
            messageBody.write(this, stream);
        }

        return this;
    }


    @Override
    public String toString() {
        return (getStartLine() + LINE_SEPARATOR + getMessageHeaders().toString()
                + String.valueOf(messageBody));
    }


    private MessageHeaders messageHeaders;
    private MessageBody messageBody;
}
