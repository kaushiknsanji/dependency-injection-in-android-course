package com.techyourchance.journeytodependencyinjection.screens.questiondetails;

import android.widget.FrameLayout;

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
     * Method that returns the container view 'R.id.frame_fragment' for Fragments
     *
     * @return Instance of {@link FrameLayout} which is a container view for Fragments
     */
    FrameLayout getFrameFragment();

    /**
     * Listener for user actions
     */
    interface Listener {
        //currently no user actions
    }
}
