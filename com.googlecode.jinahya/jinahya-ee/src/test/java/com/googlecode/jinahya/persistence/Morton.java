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
import javax.persistence.PrePersist;
import javax.persistence.PreRemove;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlTransient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 *
 * @author Jin Kwon <jinahya at gmail.com>
 */
@Entity
@Table(name = "MORTON")
public class Morton extends MappedMorton {


    /**
     * generated.
     */
    private static final long serialVersionUID = -1421400640928751890L;


    /**
     * logger.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(Morton.class);


    /**
     * default density.
     */
    protected static final int DENSITY = MAPPED_DENSITY + 1;


    /**
     * default sodium length.
     */
    protected static final int SODIUM_LENGTH = MAPPED_SODIUM_LENGTH << 1;


    /**
     * Creates a new instance.
     */
    protected Morton() {
        
        super(DENSITY, sodium(SODIUM_LENGTH));
    }


    @Override
    public String toString() {
        return getClass().getSimpleName() + "@" + hashCode()
               + "&id=" + id;
    }


    @PrePersist
    protected void _PrePersist() {

        LOGGER.debug("_PrePersist()");
    }


    @PreRemove
    protected void _PreRemove() {

        LOGGER.debug("_PreRemove()");
    }


    @Id
    @GeneratedValue(generator = "MORTON_ID_GENERATOR",
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
