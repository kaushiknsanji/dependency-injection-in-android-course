package com.techyourchance.journeytodependencyinjection.common.dependencyinjection.presentation;

import com.techyourchance.journeytodependencyinjection.screens.questiondetails.QuestionDetailsActivity;
import com.techyourchance.journeytodependencyinjection.screens.questionslist.QuestionsListActivity;

import dagger.Component;

/**
 * Dagger Component for exposing services from the Module {@link PresentationModule}
 */
@Component(modules = PresentationModule.class)
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
}