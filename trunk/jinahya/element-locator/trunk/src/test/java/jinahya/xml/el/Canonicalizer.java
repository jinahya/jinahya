/*
 * Copyright 2011 <a href="mailto:jinahya@gmail.com">Jin Kwon</a>.
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


package jinahya.xml.el;


import java.io.ByteArrayInputStream;
import java.text.Normalizer;
import java.text.Normalizer.Form;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.modeshape.common.text.Jsr283Encoder;
import org.modeshape.common.text.XmlNameEncoder;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;


/**
 *
 * @author <a href="mailto:jinahya@gmail.com">Jin Kwon</a>
 */
public class Canonicalizer {


    private static final DocumentBuilderFactory DBF =
        DocumentBuilderFactory.newInstance();


    static {
        DBF.setNamespaceAware(true);
    }


    private static final DocumentBuilder DB;


    static {
        try {
            DB = DBF.newDocumentBuilder();
        } catch (ParserConfigurationException pce) {
            throw new InstantiationError(pce.getMessage());
        }
    }


    protected static String canonicalize(final byte[] bytes) throws Exception {

        return canonicalize(DB.parse(new ByteArrayInputStream(bytes)));
    }


    public static String canonicalize(final Document document)
        throws Exception {

        return canonicalize(document.getDocumentElement());
    }


    protected static String canonicalize(final Element element)
        throws Exception {

        String namespaceURI = element.getNamespaceURI();
        if (namespaceURI == null) {
            namespaceURI = "";
        }

        final String localName = element.getLocalName();

        final StringBuilder builder = new StringBuilder();

        final String elementName = new Jsr283Encoder().encode(
            new XmlNameEncoder().encode(
            "{" + namespaceURI + "}" + localName));

        builder.append(("<" + elementName));

        {
            final NamedNodeMap attributes = element.getAttributes();
            final int length = attributes.getLength();
            final Map<String, String> map = new TreeMap<String, String>();
            for (int i = 0; i < length; i++) {
                final Node attribute = attributes.item(i);
                String attributeNamespaceURI = attribute.getNamespaceURI();
                if (attributeNamespaceURI == null) {
                    attributeNamespaceURI = "";
                }
                if (XMLConstants.XMLNS_ATTRIBUTE_NS_URI.equals(
                    attributeNamespaceURI)) {
                    continue;
                }
                final String attributeLocalName = attribute.getLocalName();
                final String attributeValue = Normalizer.normalize(
                    attribute.getNodeValue(), Form.NFC);
                final String attributeName = new Jsr283Encoder().encode(
                    new XmlNameEncoder().encode(
                    "@{" + attributeNamespaceURI + "}" + attributeLocalName));
                map.put(attributeName, attributeValue);
                /*
                builder.append((" " + attributeName + "=\"" + attributeValue
                + "\""));
                 */
            }
            for (Entry<String, String> entry : map.entrySet()) {
                builder.append((" " + entry.getKey() + "=\"" + entry.getValue()
                                + "\""));
            }
        }

        boolean startElementClosed = false;

        final NodeList childNodes = element.getChildNodes();
        final int length = childNodes.getLength();
        outer:
        for (int i = 0; i < length; i++) {
            final Node childNode = childNodes.item(i);
            switch (childNode.getNodeType()) {
                case Node.CDATA_SECTION_NODE:
                case Node.TEXT_NODE:
                    if (startElementClosed) {
                        break;
                    }
                    builder.append(">");
                    startElementClosed = true;
                    builder.append(Normalizer.normalize(
                        childNode.getNodeValue(), Form.NFD));
                    break outer;
                case Node.ELEMENT_NODE:
                    if (!startElementClosed) {
                        builder.append(">");
                        startElementClosed = true;
                    }
                    builder.append(canonicalize((Element) childNode));
                    break;
                default:
                    break;
            }
        }

        if (startElementClosed) {
            builder.append(("</" + elementName + ">"));
        } else {
            builder.append("/>");
        }

        return builder.toString();
    }


    protected Canonicalizer() {
        super();
    }
}

