package com.techyourchance.journeytodependencyinjection.common.dependencyinjection.presentation;

import android.arch.lifecycle.ViewModel;

import com.techyourchance.journeytodependencyinjection.questions.FetchQuestionDetailsUseCase;
import com.techyourchance.journeytodependencyinjection.screens.common.viewmodel.ViewModelFactory;
import com.techyourchance.journeytodependencyinjection.screens.questiondetails.QuestionDetailsViewModel;
import com.techyourchance.journeytodependencyinjection.screens.questionslist.QuestionsListViewModel;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.Map;

import javax.inject.Provider;

import dagger.MapKey;
import dagger.Module;
import dagger.Provides;
import dagger.multibindings.IntoMap;

/**
 * Dagger Module for creating and exposing services related to ViewModels
 */
@Module
public class ViewModelModule {

    /**
     * Method that creates and returns an instance of {@link ViewModelFactory}
     *
     * @param providerMap Dagger generated Provider Map of the form {@code Map<K, Provider<V>>}
     *                    where <K> is a ViewModel Class and <V> is the ViewModel instance.
     * @return Instance of {@link ViewModelFactory} required for instantiating ViewModels
     */
    @Provides
    ViewModelFactory getViewModelFactory(Map<Class<? extends ViewModel>, Provider<ViewModel>> providerMap) {
        return new ViewModelFactory(providerMap);
    }

    /**
     * Method that creates and returns an instance of {@link QuestionDetailsViewModel}
     *
     * @param fetchQuestionDetailsUseCase {@link FetchQuestionDetailsUseCase} instance for Question Details
     * @return Instance of {@link QuestionDetailsViewModel}
     */
    @Provides
    @IntoMap
    @ViewModelKey(QuestionDetailsViewModel.class)
    ViewModel getQuestionDetailsViewModel(FetchQuestionDetailsUseCase fetchQuestionDetailsUseCase) {
        return new QuestionDetailsViewModel(fetchQuestionDetailsUseCase);
    }

    /**
     * Method that creates and returns an instance of {@link QuestionsListViewModel}
     *
     * @return Instance of {@link QuestionsListViewModel}
     */
    @Provides
    @IntoMap
    @ViewModelKey(QuestionsListViewModel.class)
    ViewModel getQuestionsListViewModel() {
        return new QuestionsListViewModel();
    }

    //Annotation that identifies the Key for the Provider Map Entry
    //Accepts a ViewModel Class as the Key
    @Target(ElementType.METHOD)
    @Retention(RetentionPolicy.RUNTIME)
    @MapKey
    @interface ViewModelKey {
        Class<? extends ViewModel> value();
    }
}
