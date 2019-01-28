package com.techyourchance.journeytodependencyinjection.common.dependencyinjection.application;

import com.techyourchance.journeytodependencyinjection.networking.StackoverflowApi;
import com.techyourchance.journeytodependencyinjection.questions.FetchQuestionDetailsUseCase;
import com.techyourchance.journeytodependencyinjection.questions.FetchQuestionsListUseCase;

import dagger.Module;
import dagger.Provides;

/**
 * Dagger Module for creating and exposing services, tied to the Application Lifecycle.
 */
@Module
public class ApplicationModule {

    /**
     * Method that creates and returns a {@link FetchQuestionsListUseCase} instance
     *
     * @param stackoverflowApi Instance of {@link StackoverflowApi} provided by Dagger.
     * @return A {@link FetchQuestionsListUseCase} instance
     */
    @Provides
    FetchQuestionsListUseCase getFetchQuestionsListUseCase(StackoverflowApi stackoverflowApi) {
        return new FetchQuestionsListUseCase(stackoverflowApi);
    }

    /**
     * Method that creates and returns a {@link FetchQuestionDetailsUseCase} instance
     *
     * @param stackoverflowApi Instance of {@link StackoverflowApi} provided by Dagger.
     * @return A {@link FetchQuestionDetailsUseCase} instance
     */
    @Provides
    FetchQuestionDetailsUseCase getFetchQuestionDetailsUseCase(StackoverflowApi stackoverflowApi) {
        return new FetchQuestionDetailsUseCase(stackoverflowApi);
    }
}
