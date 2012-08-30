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


package com.googlecode.jinahya.epost.openapi;


import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.ejb.Stateless;


/**
 *
 * @author Jin Kwon <jinahya at gmail.com>
 */
@Stateless
public class PostalCodesBean implements PostalCodesBusiness {


    private static final Logger LOGGER =
        Logger.getLogger(PostalCodesBean.class.getPackage().getName());


    @PostConstruct
    public void _PostConstruct() {
        client = new PostalCodesClient(regkey);
    }


    @Override
    public boolean list(final String query, final Map<String, String> results)
        throws Exception {

        LOGGER.log(Level.INFO, "PostalCodesBean.list({0}, {1})",
                               new Object[]{query, results});

        if (query == null) {
            throw new IllegalArgumentException("null query");
        }

        if (results == null) {
            throw new IllegalArgumentException("null results");
        }

        return client.query(query, results);
    }


    @Resource
    private String regkey;


    private transient PostalCodesClient client;


}

