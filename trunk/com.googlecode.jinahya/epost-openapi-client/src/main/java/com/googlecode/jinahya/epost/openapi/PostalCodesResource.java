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


import java.util.LinkedHashMap;
import java.util.Map;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;


/**
 *
 * @author Jin Kwon <jinahya at gmail.com>
 */
@Path("/postalCodes")
@RequestScoped
public class PostalCodesResource {


    @GET
    @Path("/{query: .+}")
    public Response reads(
        @PathParam("query") final String query) throws Exception {

        final Map<String, String> results = new LinkedHashMap<String, String>();
        postalCodeBusiness.list(query, results);

        return Response.ok(PostalCodes.newInstance(results)).build();
    }


    @Inject
    private PostalCodesBusiness postalCodeBusiness;


}

