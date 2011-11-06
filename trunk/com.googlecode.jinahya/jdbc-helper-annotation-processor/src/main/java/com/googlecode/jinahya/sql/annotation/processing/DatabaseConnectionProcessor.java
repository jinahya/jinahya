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


package com.googlecode.jinahya.sql.annotation.processing;


import com.googlecode.jinahya.sql.annotation.DatabaseConnection;

import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;

import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;

import javax.tools.Diagnostic;


/**
 *
 * @author Jin Kwon <jinahya at gmail.com>
 */
@SupportedAnnotationTypes("com.googlecode.jinahya.sql.annotation.DBConnection")
public class DatabaseConnectionProcessor extends AbstractProcessor {


    @Override
    public boolean process(final Set<? extends TypeElement> annotations,
                           final RoundEnvironment roundEnv) {

        final Set<? extends Element> elements =
            roundEnv.getElementsAnnotatedWith(DatabaseConnection.class);
        for (Element element : elements) {
            final DatabaseConnection databaseConnection =
                element.getAnnotation(DatabaseConnection.class);
            String message = "annotation found in " + element.getSimpleName();
            processingEnv.getMessager().printMessage(
                Diagnostic.Kind.NOTE, message);
        }

        return true;
    }


}

