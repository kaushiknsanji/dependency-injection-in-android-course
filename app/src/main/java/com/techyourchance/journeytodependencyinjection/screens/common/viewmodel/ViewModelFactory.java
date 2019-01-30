package com.techyourchance.journeytodependencyinjection.screens.common.viewmodel;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;

import com.techyourchance.journeytodependencyinjection.questions.FetchQuestionDetailsUseCase;
import com.techyourchance.journeytodependencyinjection.screens.questiondetails.QuestionDetailsViewModel;
import com.techyourchance.journeytodependencyinjection.screens.questionslist.QuestionsListViewModel;

/**
 * Factory class for instantiating {@link ViewModel}
 */
public class ViewModelFactory implements ViewModelProvider.Factory {

    //Use Case instance for Question Details
    private final FetchQuestionDetailsUseCase mFetchQuestionDetailsUseCase;

    /**
     * Constructor of {@link ViewModelFactory}
     *
     * @param fetchQuestionDetailsUseCase Use Case instance for Question Details
     */
    public ViewModelFactory(FetchQuestionDetailsUseCase fetchQuestionDetailsUseCase) {
        mFetchQuestionDetailsUseCase = fetchQuestionDetailsUseCase;
    }

    /**
     * Creates a new instance of the given {@code Class}.
     *
     * @param modelClass a {@code Class} whose instance is requested
     * @return a newly created ViewModel
     */
    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        ViewModel viewModel;
        if (modelClass.isAssignableFrom(QuestionDetailsViewModel.class)) {
            viewModel = new QuestionDetailsViewModel(mFetchQuestionDetailsUseCase);
        } else if (modelClass.isAssignableFrom(QuestionsListViewModel.class)) {
            viewModel = new QuestionsListViewModel();
        } else {
            throw new RuntimeException("Invalid ViewModel Class: " + modelClass);
        }
        //noinspection unchecked
        return (T) viewModel;
    }
}
