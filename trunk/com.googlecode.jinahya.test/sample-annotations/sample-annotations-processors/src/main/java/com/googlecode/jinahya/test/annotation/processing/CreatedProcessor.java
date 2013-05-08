

package com.googlecode.jinahya.test.annotation.processing;


import com.googlecode.jinahya.test.annotation.By;
import com.googlecode.jinahya.test.annotation.Created;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import javax.tools.Diagnostic;


/**
 *
 * @author Jin Kwon <onacit at gmail.com>
 */
@SupportedAnnotationTypes("com.googlecode.jinahya.test.annotation.Created")
@SupportedSourceVersion(SourceVersion.RELEASE_7)
public class CreatedProcessor extends AbstractProcessor {


    private static Logger LOGGER =
        Logger.getLogger(CreatedProcessor.class.getName());


    @Override
    public boolean process(final Set<? extends TypeElement> annotations,
                           final RoundEnvironment roundEnv) {

        LOGGER.log(Level.INFO, "process({0}, {1})",
                   new Object[]{annotations, roundEnv});

        final Set<? extends Element> elements =
            roundEnv.getElementsAnnotatedWith(Created.class);
        for (Element element : elements) {
            System.out.println("element: " + element);
            System.out.println("\tenclosedElements: " + element.getEnclosedElements());
            System.out.println("\tenclosingElement: " + element.getEnclosingElement());

            final Created created = element.getAnnotation(Created.class);
            for (By by : created.value()) {
                final StringBuilder message =
                    new StringBuilder("Created by " + by.name());
                if (by.comment() == null) {
                    message.append(" with no comment");
                } else {
                    message.append(" with following comment: ")
                        .append(by.comment());
                }
                System.out.println(message.toString());
                processingEnv.getMessager().printMessage(
                    Diagnostic.Kind.NOTE, message.toString());
            }
        }

        return true;
    }


}

