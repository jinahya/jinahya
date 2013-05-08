

package com.googlecode.jinahya.test.annotation.processing;


import com.googlecode.jinahya.test.annotation.By;
import com.googlecode.jinahya.test.annotation.Reviewed;
import java.util.Set;
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
@SupportedAnnotationTypes("com.googlecode.jinahya.test.annotation.Reviewed")
@SupportedSourceVersion(SourceVersion.RELEASE_5)
public class ReviewedProcessor extends AbstractProcessor {


    @Override
    public boolean process(final Set<? extends TypeElement> annotations,
                           final RoundEnvironment roundEnv) {

        final Set<? extends Element> elementsAnnotatedWithReviewed =
            roundEnv.getElementsAnnotatedWith(Reviewed.class);
        for (Element element : elementsAnnotatedWithReviewed) {
            final Reviewed reviewed = element.getAnnotation(Reviewed.class);
            for (By by : reviewed.value()) {
                final StringBuilder message =
                    new StringBuilder("Reviewed by " + by.name());
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

