package com.techyourchance.journeytodependencyinjection.screens.common.viewmodel;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;

import com.techyourchance.journeytodependencyinjection.screens.questiondetails.QuestionDetailsViewModel;
import com.techyourchance.journeytodependencyinjection.screens.questionslist.QuestionsListViewModel;

/**
 * Factory class for instantiating {@link ViewModel}
 */
public class ViewModelFactory implements ViewModelProvider.Factory {

    //ViewModel Instances
    private final QuestionsListViewModel mQuestionsListViewModel;
    private final QuestionDetailsViewModel mQuestionDetailsViewModel;

    /**
     * Constructor of {@link ViewModelFactory}
     *
     * @param questionsListViewModel Instance of {@link QuestionsListViewModel}
     * @param questionDetailsViewModel Instance of {@link QuestionDetailsViewModel}
     */
    public ViewModelFactory(QuestionsListViewModel questionsListViewModel, QuestionDetailsViewModel questionDetailsViewModel) {
        mQuestionsListViewModel = questionsListViewModel;
        mQuestionDetailsViewModel = questionDetailsViewModel;
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
            viewModel = mQuestionDetailsViewModel;
        } else if (modelClass.isAssignableFrom(QuestionsListViewModel.class)) {
            viewModel = mQuestionsListViewModel;
        } else {
            throw new RuntimeException("Invalid ViewModel Class: " + modelClass);
        }
        //noinspection unchecked
        return (T) viewModel;
    }
}
