package com.techyourchance.journeytodependencyinjection.common.dependencyinjection.application;

import com.techyourchance.journeytodependencyinjection.questions.FetchQuestionDetailsUseCase;
import com.techyourchance.journeytodependencyinjection.questions.FetchQuestionsListUseCase;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Dagger Component for exposing services from the Module {@link ApplicationModule}
 */
@Singleton
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {
    FetchQuestionsListUseCase getFetchQuestionsListUseCase();

    FetchQuestionDetailsUseCase getFetchQuestionDetailsUseCase();
}
