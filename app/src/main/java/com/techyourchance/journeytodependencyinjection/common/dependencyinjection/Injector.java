package com.techyourchance.journeytodependencyinjection.common.dependencyinjection;

import com.techyourchance.journeytodependencyinjection.questions.FetchQuestionDetailsUseCase;
import com.techyourchance.journeytodependencyinjection.questions.FetchQuestionsListUseCase;
import com.techyourchance.journeytodependencyinjection.screens.common.dialogs.DialogsManager;
import com.techyourchance.journeytodependencyinjection.screens.common.mvcviews.ViewMvcFactory;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

/**
 * Wrapper Class of {@link PresentationCompositionRoot} to inject required dependencies into the clients.
 */
public class Injector {

    //Instance of PresentationCompositionRoot for getting the services
    private final PresentationCompositionRoot mPresentationCompositionRoot;

    /**
     * Constructor of {@link Injector}
     *
     * @param presentationCompositionRoot Instance of {@link PresentationCompositionRoot} for getting the services
     */
    public Injector(PresentationCompositionRoot presentationCompositionRoot) {
        mPresentationCompositionRoot = presentationCompositionRoot;
    }

    /**
     * Method that injects dependencies into the clients
     *
     * @param client An {@link android.support.v7.app.AppCompatActivity} that requires
     *               the services to be injected
     */
    public void inject(Object client) {
        Class<?> clazz = client.getClass();

        Field[] declaredFields = clazz.getDeclaredFields();

        for (Field field : declaredFields) {
            if (isAnnotatedForInjection(field)) {
                injectField(client, field);
            }
        }
    }

    /**
     * Method that reads the Annotations on the {@code field}
     * to see if it is annotated by {@link Service}, for injecting the corresponding service.
     *
     * @param field Class Field of the client being scanned for the {@link Service} annotation
     * @return <b>TRUE</b> if annotated by {@link Service}; <b>FALSE</b> otherwise
     */
    private boolean isAnnotatedForInjection(Field field) {
        Annotation[] declaredAnnotations = field.getDeclaredAnnotations();

        for (Annotation annotation : declaredAnnotations) {
            if (annotation instanceof Service) {
                return true;
            }
        }

        return false;
    }

    /**
     * Method that injects the service required for the {@code field}
     *
     * @param client An {@link android.support.v7.app.AppCompatActivity} that requires
     *               the services to be injected
     * @param field  Class Field of the {@code client} that needs to be initialized with the service
     */
    private void injectField(Object client, Field field) {
        try {
            boolean accessibility = field.isAccessible();
            field.setAccessible(true);
            field.set(client, getServiceForClass(field.getType()));
            field.setAccessible(accessibility);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Method that retrieves the Service for the class {@code type} passed.
     *
     * @param type Service required for the Class type
     * @return Object of the Service instantiated
     */
    private Object getServiceForClass(Class<?> type) {
        if (type.equals(DialogsManager.class)) {
            return mPresentationCompositionRoot.getDialogsManager();

        } else if (type.equals(ViewMvcFactory.class)) {
            return mPresentationCompositionRoot.getViewMvcFactory();

        } else if (type.equals(FetchQuestionsListUseCase.class)) {
            return mPresentationCompositionRoot.getFetchQuestionsListUseCase();

        } else if (type.equals(FetchQuestionDetailsUseCase.class)) {
            return mPresentationCompositionRoot.getFetchQuestionDetailsUseCase();

        } else {
            throw new RuntimeException("Unsupported Service type class: " + type);
        }
    }

}