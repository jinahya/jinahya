

package com.googlecode.jinahya.test.annotation.processing;


import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Completion;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.AnnotationMirror;
import javax.lang.model.element.Element;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.TypeElement;


/**
 *
 * @author Jin Kwon <onacit at gmail.com>
 */
@SupportedAnnotationTypes("*")
@SupportedSourceVersion(SourceVersion.RELEASE_5)
public class LoggingProcessor extends AbstractProcessor {


    private static final Logger LOGGER =
        Logger.getLogger(LoggingProcessor.class.getName());


    static {
        LOGGER.setLevel(Level.INFO);
    }


    @Override
    protected synchronized boolean isInitialized() {

        final boolean initialized = super.isInitialized();

        LOGGER.log(Level.INFO, "isInitialized() - > {0}", isInitialized());

        return initialized;
    }


    @Override
    public Set<String> getSupportedOptions() {

        final Set<String> supportedOptions = super.getSupportedOptions();

        LOGGER.log(Level.INFO, "getSupportedOptions() -> {0}",
                   supportedOptions);

        return supportedOptions;
    }


    @Override
    public Set<String> getSupportedAnnotationTypes() {

        final Set<String> supportedAnnotationTypes =
            super.getSupportedAnnotationTypes();

        LOGGER.log(Level.INFO, "getSupportedAnnotationTypes() -> {0}",
                   supportedAnnotationTypes);

        return supportedAnnotationTypes;
    }


    @Override
    public SourceVersion getSupportedSourceVersion() {

        final SourceVersion sourceVersion = super.getSupportedSourceVersion();

        LOGGER.log(Level.INFO, "getSupportedSourceVersion() -> {0}",
                   sourceVersion);

        return sourceVersion;
    }


    @Override
    public synchronized void init(final ProcessingEnvironment processingEnv) {

        LOGGER.log(Level.INFO, "init({0})", processingEnv);

        super.init(processingEnv);
    }


    @Override
    public Iterable<? extends Completion> getCompletions(
        final Element element, final AnnotationMirror annotation,
        final ExecutableElement member, final String userText) {

        final Iterable<? extends Completion> completions =
            super.getCompletions(element, annotation, member, userText);

        LOGGER.log(Level.INFO, "getCompletions({0}, {1}, {2}, {3})",
                   new Object[]{element, annotation, member, userText});

        return completions;
    }


    @Override
    public boolean process(final Set<? extends TypeElement> annotations,
                           final RoundEnvironment roundEnv) {

        LOGGER.log(Level.INFO, "process({0}, {1})",
                   new Object[]{annotations, roundEnv});

        for (TypeElement annotation : annotations) {
        }

        return false;
    }


}

