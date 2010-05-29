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
 *  under the License.
 */

package jinahya.xml.xpath;


import javax.xml.xpath.XPath;

import org.w3c.dom.Document;


/**
 *
 * @author <a href="mailto:support@minigate.net">Jin Kwon</a>
 */
public class DocumentPath extends NodePath<Document> {


    /**
     *
     * @param document
     * @param path
     */
    public DocumentPath(final Document document, final XPath path) {
        super(document, path);
    }
}
