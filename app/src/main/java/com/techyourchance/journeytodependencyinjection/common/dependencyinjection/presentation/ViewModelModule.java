package com.techyourchance.journeytodependencyinjection.common.dependencyinjection.presentation;

import com.techyourchance.journeytodependencyinjection.questions.FetchQuestionDetailsUseCase;
import com.techyourchance.journeytodependencyinjection.screens.common.viewmodel.ViewModelFactory;
import com.techyourchance.journeytodependencyinjection.screens.questiondetails.QuestionDetailsViewModel;
import com.techyourchance.journeytodependencyinjection.screens.questionslist.QuestionsListViewModel;

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
     * @param questionsListViewModel Instance of {@link QuestionsListViewModel}
     * @param questionDetailsViewModel Instance of {@link QuestionDetailsViewModel}
     *
     * @return Instance of {@link ViewModelFactory} required for instantiating ViewModels
     */
    @Provides
    ViewModelFactory getViewModelFactory(QuestionsListViewModel questionsListViewModel, QuestionDetailsViewModel questionDetailsViewModel) {
        return new ViewModelFactory(questionsListViewModel, questionDetailsViewModel);
    }

    /**
     * Method that creates and returns an instance of {@link QuestionDetailsViewModel}
     *
     * @param fetchQuestionDetailsUseCase {@link FetchQuestionDetailsUseCase} instance for Question Details
     * @return Instance of {@link QuestionDetailsViewModel}
     */
    @Provides
    QuestionDetailsViewModel getQuestionDetailsViewModel(FetchQuestionDetailsUseCase fetchQuestionDetailsUseCase) {
        return new QuestionDetailsViewModel(fetchQuestionDetailsUseCase);
    }

    /**
     * Method that creates and returns an instance of {@link QuestionsListViewModel}
     *
     * @return Instance of {@link QuestionsListViewModel}
     */
    @Provides
    QuestionsListViewModel getQuestionsListViewModel() {
        return new QuestionsListViewModel();
    }
}