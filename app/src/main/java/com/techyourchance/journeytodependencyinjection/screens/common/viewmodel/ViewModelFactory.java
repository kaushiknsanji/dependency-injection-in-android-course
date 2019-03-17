package com.techyourchance.journeytodependencyinjection.screens.common.viewmodel;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;

import com.techyourchance.journeytodependencyinjection.screens.questiondetails.QuestionDetailsViewModel;
import com.techyourchance.journeytodependencyinjection.screens.questionslist.QuestionsListViewModel;

import javax.inject.Provider;

/**
 * Factory class for instantiating {@link ViewModel}
 */
public class ViewModelFactory implements ViewModelProvider.Factory {

    //Provider wrapped ViewModel Instances
    private final Provider<QuestionsListViewModel> mQuestionsListViewModelProvider;
    private final Provider<QuestionDetailsViewModel> mQuestionDetailsViewModelProvider;

    /**
     * Constructor of {@link ViewModelFactory}
     * @param questionsListViewModelProvider {@link Provider} wrapped instance of {@link QuestionsListViewModel}
     * @param questionDetailsViewModelProvider {@link Provider} wrapped instance of {@link QuestionDetailsViewModel}
     */
    public ViewModelFactory(Provider<QuestionsListViewModel> questionsListViewModelProvider,
                            Provider<QuestionDetailsViewModel> questionDetailsViewModelProvider) {
        mQuestionsListViewModelProvider = questionsListViewModelProvider;
        mQuestionDetailsViewModelProvider = questionDetailsViewModelProvider;
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
            viewModel = mQuestionDetailsViewModelProvider.get();
        } else if (modelClass.isAssignableFrom(QuestionsListViewModel.class)) {
            viewModel = mQuestionsListViewModelProvider.get();
        } else {
            throw new RuntimeException("Invalid ViewModel Class: " + modelClass);
        }
        //noinspection unchecked
        return (T) viewModel;
    }
}
