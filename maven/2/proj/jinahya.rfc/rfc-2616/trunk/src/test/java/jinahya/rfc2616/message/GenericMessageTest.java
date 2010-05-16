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


import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.util.Iterator;

import jinahya.rfc2616.message.GenericMessage.MessageHeaders;


/**
 *
 * @author <a href="mailto:jinahya@gmail.com">Jin Kwon</a>
 */
public class GenericMessageTest {


    protected void print(final GenericMessage message)
        throws UnsupportedEncodingException {

        print(message, new PrintStream(System.out, true, "UTF-8"));
    }


    protected void print(final GenericMessage message, final PrintStream out) {

        out.println(message.getStartLine());

        final MessageHeaders headers = message.getMessageHeaders();
        final Object[] fieldNames = headers.getFieldNames();
        for (int i = 0; i < fieldNames.length; i++) {
        //for (String fieldName : headers.getFieldNames()) {
            out.print(fieldNames[i] + ":");
            boolean first = true;
            Iterator fieldValues =
                headers.getFieldValues(fieldNames[i]).iterator();
            while (fieldValues.hasNext()) {
            //for (String fieldValue : headers.getFieldValues(fieldName)) {
                if (!first) {
                    out.print(",");
                }
                out.print(fieldValues.next());
                if (first) {
                    first = false;
                }
            }
            out.println();
        }
    }
}
