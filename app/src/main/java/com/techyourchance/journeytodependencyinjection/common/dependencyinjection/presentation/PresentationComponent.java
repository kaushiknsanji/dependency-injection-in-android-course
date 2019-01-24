package com.techyourchance.journeytodependencyinjection.common.dependencyinjection.presentation;

import com.techyourchance.journeytodependencyinjection.questions.FetchQuestionDetailsUseCase;
import com.techyourchance.journeytodependencyinjection.questions.FetchQuestionsListUseCase;
import com.techyourchance.journeytodependencyinjection.screens.common.dialogs.DialogsManager;
import com.techyourchance.journeytodependencyinjection.screens.common.mvcviews.ViewMvcFactory;

import dagger.Component;

/**
 * Dagger Component for exposing services from the Module {@link PresentationModule}
 */
@Component(modules = PresentationModule.class)
public interface PresentationComponent {
    DialogsManager getDialogsManager();

    ViewMvcFactory getViewMvcFactory();

    FetchQuestionsListUseCase getFetchQuestionsListUseCase();

    FetchQuestionDetailsUseCase getFetchQuestionDetailsUseCase();
}