

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
//        System.out.println(elements.size());
        for (Element element : elements) {
//            System.out.println("element.simpleName: " + element.getSimpleName());
//            final ElementKind elementKind = element.getKind();
//            System.out.println("element.kind: " + elementKind);
//            final TypeMirror typeMirror = element.asType();
//            System.out.println("element.type: " + typeMirror);
//            final TypeKind typeKind = typeMirror.getKind();
//            System.out.println("element.type.kind: " + typeKind);
            if (!element.getModifiers().contains(Modifier.FINAL)) {
                processingEnv.getMessager().printMessage(
                    Diagnostic.Kind.MANDATORY_WARNING, element.getKind() + " " + element.getSimpleName() + " annotated with " + Final.class.getSimpleName() + " yet not final");
            }
        }

        return true;
    }


}

