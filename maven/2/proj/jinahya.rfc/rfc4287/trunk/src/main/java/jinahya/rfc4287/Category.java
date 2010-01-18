/*
 *  Copyright 2009 onacit.
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

package jinahya.rfc4287;


import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;


/**
 *
 * @author onacit
 */
public class Category {


    @XmlAttribute(required = true)
    public String getTerm() {
        return term;
    }


    public void setTerm(String term) {
        this.term = term;
    }


    @XmlAttribute
    public String getScheme() {
        return scheme;
    }


    public void setScheme(String scheme) {
        this.scheme = scheme;
    }


    @XmlElement
    public String getLabel() {
        return label;
    }


    public void setLabel(String label) {
        this.label = label;
    }


    private String term;
    private String scheme;
    private String label;
}
