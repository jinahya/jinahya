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


import javax.xml.bind.annotation.XmlElement;


/**
 *
 * @author onacit
 */
public class PersonConstruct {


    @XmlElement(required = true)
    public String getName() {
        return name;
    }


    public void setName(String name) {
        this.name = name;
    }


    @XmlElement
    public String getUri() {
        return uri;
    }


    public void setUri(String uri) {
        this.uri = uri;
    }


    @XmlElement
    public String getEmail() {
        return email;
    }        


    public void setEmail(String email) {
        this.email = email;
    }

    
    private String name;
    private String uri;
    private String email;
}
