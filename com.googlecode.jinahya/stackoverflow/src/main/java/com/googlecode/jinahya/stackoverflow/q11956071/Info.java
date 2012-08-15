/*
 * Copyright 2012 Jin Kwon <jinahya at gmail.com>.
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


package com.googlecode.jinahya.stackoverflow.q11956071;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;


/**
 *
 * @author Jin Kwon <jinahya at gmail.com>
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.NONE)
public class Info {


    @XmlElement(name = "item")
    private List<Item> getItems() {
        return new ArrayList<Item>(getMap().values());
    }


    private void setItems(final List<Item> items) {
        getMap().clear();
        for (Item item : items) {
            getMap().put(item.getKey(), item);
        }
    }


    public Map<Integer, Item> getMap() {

        if (map == null) {
            map = new HashMap<Integer, Item>();
        }

        return map;
    }


    private Map<Integer, Item> map;


}

