/*
 * Copyright 2013 Jin Kwon <onacit at gmail.com>.
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


import java.util.Date;
import javax.persistence.PostLoad;
import javax.persistence.PrePersist;
import javax.persistence.PreRemove;
import javax.persistence.PreUpdate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 *
 * @author Jin Kwon <onacit at gmail.com>
 */
public class CludListener {


    /**
     * logger.
     */
    private static final Logger LOGGER =
        LoggerFactory.getLogger(CludListener.class);


    /**
     *
     * @param setLocatedAtOnPostLoad
     * @param setDeletedAtOnPreRemove
     */
    protected CludListener(final boolean setLocatedAtOnPostLoad,
                           final boolean setDeletedAtOnPreRemove) {

        this.setLocatedAtOnPostLoad = setLocatedAtOnPostLoad;
        this.setDeletedAtOnPreRemove = setDeletedAtOnPreRemove;
    }


    /**
     * Creates a new instance.
     */
    public CludListener() {

        this(false, false);
    }


    @PrePersist
    protected void setCreatedAt(final Clud clud) {

        clud.setCreatedAt(new Date());
    }


    @PostLoad
    protected void setLocatedAt(final Clud clud) {

        if (!setLocatedAtOnPostLoad) {
            return;
        }

        clud.setLocatedAt(new Date());
    }


    @PreUpdate
    protected void setUpdatedAt(final Clud clud) {

        clud.setUpdatedAt(new Date());
    }


    @PreRemove
    protected void setDeletedAt(final Clud clud) {

        if (!setDeletedAtOnPreRemove) {
            return;
        }

        clud.setDeletedAt(new Date());
    }


    private final boolean setLocatedAtOnPostLoad;


    private final boolean setDeletedAtOnPreRemove;


}
