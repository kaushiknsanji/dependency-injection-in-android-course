package com.techyourchance.journeytodependencyinjection.common.dependencyinjection.presentation;

import com.techyourchance.journeytodependencyinjection.common.dependencyinjection.application.ApplicationComponent;
import com.techyourchance.journeytodependencyinjection.screens.questiondetails.QuestionDetailsActivity;
import com.techyourchance.journeytodependencyinjection.screens.questiondetails.SimpleFragment;
import com.techyourchance.journeytodependencyinjection.screens.questionslist.QuestionsListActivity;

import dagger.Subcomponent;

/**
 * Dagger Subcomponent for exposing services from the Modules {@link PresentationModule}
 * and {@link ViewModelModule} whose parent component is {@link ApplicationComponent}
 */
@Subcomponent(modules = {PresentationModule.class, ViewModelModule.class})
public interface PresentationComponent {
    /**
     * Method to inject services into the client {@link QuestionsListActivity}
     *
     * @param questionsListActivity Instance of {@link QuestionsListActivity}
     */
    void inject(QuestionsListActivity questionsListActivity);

    /**
     * Method to inject services into the client {@link QuestionDetailsActivity}
     *
     * @param questionDetailsActivity Instance of {@link QuestionDetailsActivity}
     */
    void inject(QuestionDetailsActivity questionDetailsActivity);

    /**
     * Method to inject services into the client {@link SimpleFragment}
     *
     * @param simpleFragment Instance of {@link SimpleFragment}
     */
    void inject(SimpleFragment simpleFragment);
}