

package com.googlecode.jinahya.test.annotation.processing;


import com.googlecode.jinahya.test.annotation.Final;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.Modifier;
import javax.lang.model.element.TypeElement;
import javax.tools.Diagnostic;


/**
 *
 * @author Jin Kwon <onacit at gmail.com>
 */
@SupportedAnnotationTypes("com.googlecode.jinahya.test.annotation.Final")
@SupportedSourceVersion(SourceVersion.RELEASE_7)
public class FinalProcessor extends AbstractProcessor {


    private static Logger LOGGER =
        Logger.getLogger(FinalProcessor.class.getName());


    @Override
    public boolean process(final Set<? extends TypeElement> annotations,
                           final RoundEnvironment roundEnv) {

        LOGGER.log(Level.INFO, "process({0}, {1})",
                   new Object[]{annotations, roundEnv});

        final Set<? extends Element> elements =
            roundEnv.getElementsAnnotatedWith(Final.class);
        for (Element element : elements) {
            if (!element.getModifiers().contains(Modifier.FINAL)) {
                processingEnv.getMessager().printMessage(
                    Diagnostic.Kind.ERROR, element.getSimpleName() + " annotated with " + Final.class.getSimpleName() + " yet not final");
            }
        }

        return true;
    }


}

