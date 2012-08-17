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


package com.googlecode.jinahya.stackoverflow.q11984683;


import java.util.Random;
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
public class Person {


    public static Person newInstance(final Random random) {
        final Person instance = new Person();
        if (random.nextBoolean()) {
            instance.name = "name";
        }
        if (random.nextBoolean()) {
            instance.age = random.nextInt(50) + 1;
        }
        if (random.nextBoolean()) {
            instance.nickName = "nick";
        }
        
        return instance;
    }


    @XmlElement(nillable = true)
    private String name;


    @XmlElement(nillable = true)
    private Integer age;


    @XmlElement(nillable = true)
    private String nickName;


    @XmlElement(nillable = true)
    private Integer id;


    @XmlElement(nillable = true)
    private Double saldo;


}

