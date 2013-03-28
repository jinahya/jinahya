/*
 * Copyright 2013 Jin Kwon <jinahya at gmail.com>.
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


package com.googlecode.jinahya.persistence;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlTransient;


/**
 *
 * @author Jin Kwon <jinahya at gmail.com>
 */
@Entity
@Table(name = "MORTON")
public class Morton extends MappedMorton {


    protected static final int DENSITY = MAPPED_DENSITY + 1;


    protected static final int SODIUM_LENGTH = MAPPED_SODIUM_LENGTH << 1;


    protected Morton() {
        super(DENSITY, sodium(SODIUM_LENGTH));
    }


    @Id
    @GeneratedValue(generator = "LOG_ID_GENERATOR",
                    strategy = GenerationType.TABLE)
    @TableGenerator(initialValue = Pkv.INITIAL_VALUE,
                    name = "MORTON_ID_GENERATOR",
                    pkColumnName = Pkv.PK_COLUMN_NAME,
                    pkColumnValue = "MORTON_ID",
                    table = Pkv.TABLE,
                    valueColumnName = Pkv.VALUE_COLUMN_NAME)
    @NotNull
    @XmlTransient
    private Long id;


}

