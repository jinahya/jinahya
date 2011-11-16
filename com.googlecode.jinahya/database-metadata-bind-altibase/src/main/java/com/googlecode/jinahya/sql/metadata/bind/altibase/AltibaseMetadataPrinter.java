/*
 * Copyright 2011 Jin Kwon <jinahya at gmail.com>.
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


package com.googlecode.jinahya.sql.metadata.bind.altibase;


import com.googlecode.jinahya.sql.metadata.bind.MetadataPrinter;

import java.io.IOException;

import java.sql.SQLException;

import javax.xml.bind.JAXBException;

import org.kohsuke.args4j.CmdLineException;
import org.kohsuke.args4j.CmdLineParser;


/**
 *
 * @author Jin Kwon <jinahya at gmail.com>
 */
public class AltibaseMetadataPrinter extends MetadataPrinter {


    protected static final String DRIVER_NAME =
        "Altibase.jdbc.driver.AltibaseDriver";


    /**
     * 
     * @param args command line arguments
     * @throws IOException
     * @throws ClassNotFoundException
     * @throws SQLException
     * @throws JAXBException 
     */
    public static void main(final String[] args)
        throws IOException, ClassNotFoundException, SQLException,
               JAXBException {

        final ConnectionOption option = new ConnectionOption();

        final CmdLineParser parser = new CmdLineParser(option);

        try {
            parser.parseArgument(args);
            if (option.getDriver() == null) {
                option.setDriver(DRIVER_NAME);
            }
        } catch (CmdLineException cle) {
            cle.printStackTrace(System.err);
            parser.printUsage(System.out);
            return;
        }

        printMetadata(option);
    }


}

