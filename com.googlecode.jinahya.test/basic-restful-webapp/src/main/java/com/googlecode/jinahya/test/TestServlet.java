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


package com.googlecode.jinahya.test;


import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 *
 * @author Jin Kwon <onacit at gmail.com>
 */
@WebServlet(urlPatterns = "/serv/test")
public class TestServlet extends HttpServlet {


    /**
     * generated.
     */
    private static final long serialVersionUID = -5783497140019630489L;


    @Override
    protected void doGet(final HttpServletRequest req,
                         final HttpServletResponse resp)
        throws ServletException, IOException {

        resp.sendError(HttpServletResponse.SC_OK);
    }


}
