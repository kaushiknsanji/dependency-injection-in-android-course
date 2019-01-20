package com.techyourchance.journeytodependencyinjection.screens.questiondetails;

import com.techyourchance.journeytodependencyinjection.questions.QuestionDetails;
import com.techyourchance.journeytodependencyinjection.screens.common.mvcviews.ObservableViewMvc;

/**
 * MVC View Interface to notify the controller {@link QuestionDetailsActivity} of input events.
 */
public interface QuestionDetailsViewMvc extends ObservableViewMvc<QuestionDetailsViewMvc.Listener> {

    /**
     * Method that binds the {@link QuestionDetails} data to the View.
     *
     * @param question Instance of {@link QuestionDetails}
     */
    void bindQuestion(QuestionDetails question);

    /**
     * Listener for user actions
     */
    interface Listener {
        //currently no user actions
    }
}
