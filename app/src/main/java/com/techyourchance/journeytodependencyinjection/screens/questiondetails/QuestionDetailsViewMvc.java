package com.techyourchance.journeytodependencyinjection.screens.questiondetails;

import com.techyourchance.journeytodependencyinjection.questions.QuestionWithBody;
import com.techyourchance.journeytodependencyinjection.screens.common.mvcviews.ObservableViewMvc;

/**
 * MVC View Interface to notify the controller {@link QuestionDetailsActivity} of input events.
 */
public interface QuestionDetailsViewMvc extends ObservableViewMvc<QuestionDetailsViewMvc.Listener> {

    /**
     * Method that binds the {@link QuestionWithBody} data to the View.
     *
     * @param question Instance of {@link QuestionWithBody}
     */
    void bindQuestion(QuestionWithBody question);

    /**
     * Listener for user actions
     */
    interface Listener {
        //currently no user actions
    }
}
