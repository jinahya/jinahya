/*
 * Copyright 2011 Jin Kwon.
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


package jinahya.xml;


import org.testng.Assert;
import org.testng.annotations.Test;


/**
 *
 * @author <a href="mailto:jinahya@gmail.com">Jin Kwon</a>
 */
public abstract class ElementLocatorTest<T extends ElementLocator> {


    protected static final String SIMPLE_XML =
        "<?xml version=\"1.0\"?>"
        + "<root a=\"b\" c=\"d\">"
        + "  <grandfather a=\"b\" c=\"d\">"
        + "    <father a=\"b\" c=\"d\">"
        + "      <child a=\"b\" c=\"d\">"
        + "        <grandchild a=\"b\" c=\"d\">"
        + "          <text  a=\"b\" c=\"d\">text</text>"
        + "        </grandchild>"
        + "      </child>"
        + "    </father>"
        + "  </grandfather>"
        + "</root>";


    protected void testForSimpleXML(final T locator) {

        Assert.assertEquals(locator.getAttribute("a"), "b");
        Assert.assertEquals(locator.getAttribute("c"), "d");

        final int grandfatherCount = locator.getCount("grandfather");
        Assert.assertEquals(grandfatherCount, 1);
        locator.locateChild("grandfather", 0);
        Assert.assertEquals(locator.getAttribute("a"), "b");
        Assert.assertEquals(locator.getAttribute("c"), "d");

        final int fatherCount = locator.getCount("father");
        Assert.assertEquals(fatherCount, 1);
        locator.locateChild("father", 0);
        Assert.assertEquals(locator.getAttribute("a"), "b");
        Assert.assertEquals(locator.getAttribute("c"), "d");

        final int childCount = locator.getCount("child");
        Assert.assertEquals(childCount, 1);
        locator.locateChild("child", 0);
        Assert.assertEquals(locator.getAttribute("a"), "b");
        Assert.assertEquals(locator.getAttribute("c"), "d");

        final int grandchildCount = locator.getCount("grandchild");
        Assert.assertEquals(grandchildCount, 1);
        locator.locateChild("grandchild", 0);
        Assert.assertEquals(locator.getAttribute("a"), "b");
        Assert.assertEquals(locator.getAttribute("c"), "d");

        final int textCount = locator.getCount("text");
        Assert.assertEquals(textCount, 1);
        locator.locateChild("text", 0);
        Assert.assertEquals(locator.getAttribute("a"), "b");
        Assert.assertEquals(locator.getAttribute("c"), "d");

        Assert.assertEquals(locator.getText(), "text");
    }
}
