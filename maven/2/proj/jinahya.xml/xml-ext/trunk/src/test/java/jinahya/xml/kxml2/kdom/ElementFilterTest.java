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

package jinahya.xml.kxml2.kdom;


import java.io.IOException;
import java.io.StringReader;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Vector;

import org.kxml2.io.KXmlParser;
import org.kxml2.kdom.Document;

import org.testng.annotations.Test;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;


/**
 *
 * @author <a href="mailto:jinahya@gmail.com">Jin Kwon</a>
 */
public class ElementFilterTest {


    public static class ElementPrinter extends ElementFilter {

        private static final StringBuffer BUFFER = new StringBuffer();


        @Override
        public boolean startFiltering(final LinkedElement element) {

            System.out.print("<" + element.getName());

            final Hashtable<String, Vector<String>> attributes =
                element.attributes();
            final Enumeration<String> namespaces = attributes.keys();
            while (namespaces.hasMoreElements()) {
                final String namespace = namespaces.nextElement();
                if (XmlPullParser.NO_NAMESPACE.equals(namespace)) {
                }
                final Vector<String> names = attributes.get(namespace);
                for (int i = 0; i < names.size(); i++) {
                    final String name = names.elementAt(i);
                    System.out.print(" " + name + "=\""
                                     + element.attribute(namespace, name)
                                     + "\"");
                }
            }
            System.out.print(">");

            element.text(BUFFER.delete(0, BUFFER.length()));
            System.out.print(BUFFER.toString());

            return true;
        }


        @Override
        public void finishFiltering(final LinkedElement element) {
            System.out.print("</" + element.getName() + ">");
        }
    }


    public static final String XML =
        "<?xml version=\"1.0\"?>" +
        "<root a=\"b\">" +
        "  <c>d</c>" +
        "</root>";


    @Test
    public void print() throws XmlPullParserException, IOException {
        final Document document = new Document();
        final XmlPullParser parser = new KXmlParser();
        parser.setInput(new StringReader(XML));
        document.parse(parser);

        new ElementPrinter().filter(document);
    }
}
