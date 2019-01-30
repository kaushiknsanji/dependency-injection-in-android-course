package com.techyourchance.journeytodependencyinjection.common.dependencyinjection.presentation;

import com.techyourchance.journeytodependencyinjection.questions.FetchQuestionDetailsUseCase;
import com.techyourchance.journeytodependencyinjection.screens.common.viewmodel.ViewModelFactory;

import dagger.Module;
import dagger.Provides;

/**
 * Dagger Module for creating and exposing services related to ViewModels
 */
@Module
public class ViewModelModule {

    /**
     * Method that creates and returns an instance of {@link ViewModelFactory}
     *
     * @param fetchQuestionDetailsUseCase Use Case instance for Question Details
     * @return Instance of {@link ViewModelFactory} required for instantiating ViewModels
     */
    @Provides
    ViewModelFactory getViewModelFactory(FetchQuestionDetailsUseCase fetchQuestionDetailsUseCase) {
        return new ViewModelFactory(fetchQuestionDetailsUseCase);
    }
}
