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

import org.kxml2.io.KXmlSerializer;
import org.kxml2.kdom.Document;
import org.kxml2.kdom.Node;

import org.testng.Assert;
import org.testng.annotations.Test;

import org.xmlpull.v1.XmlSerializer;


/**
 *
 * @author <a href="mailto:jinahya@gmail.com">Jin Kwon</a>
 */
public class LinkedElementTest {


    private static LinkedElement newInstance(final String namespace,
                                             final String name) {

        final Document document = new Document();
        document.addChild(
            Node.ELEMENT, document.createElement(namespace, name));
        return LinkedElement.newInstance(document);
    }


    private static void print(final LinkedElement element) {
        final XmlSerializer serializer = new KXmlSerializer();
        try {
            serializer.setOutput(System.out, "UTF-8");
            element.document().write(serializer);
            System.out.println();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }


    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testNewInstanceWithEmptyDocument() {
        LinkedElement.newInstance(new Document());
    }


    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testNewInstanceWithNullDocument() {
        LinkedElement.newInstance(null);
    }


    @Test
    public void testAttributeSet() throws IOException {
        final LinkedElement element = newInstance("a", "b");
        element.attributeNS("c", "d", "v");
        print(element);
    }


    @Test
    public void testAttributeGet() throws IOException {

        final LinkedElement element = newInstance("a", "b");

        Assert.assertNull(element.attributeNS("a", "b"));

        element.attributeNS("a", "b", "c");

        Assert.assertEquals(element.attributeNS("a", "b"), "c");
    }


    @Test
    public void testHashCode() {

        final LinkedElement parent = newInstance("a", "b");
        System.out.println("parent.hashCode: " + parent.hashCode());

        final LinkedElement child = parent.childNS("a", "b");
        System.out.println("child.hashCode: " + child.hashCode());
    }


    @Test
    public void testEquals() {

        final LinkedElement parent = newInstance("a", "b");

        final LinkedElement child = parent.childNS("a", "b");

        Assert.assertEquals(parent.childNS("a", "b", 0), child);
    }
}
