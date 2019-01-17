package com.techyourchance.journeytodependencyinjection.screens.questionslist;

import com.techyourchance.journeytodependencyinjection.questions.Question;
import com.techyourchance.journeytodependencyinjection.screens.common.mvcviews.ObservableViewMvc;

import java.util.List;

/**
 * MVC View Interface to notify the controller {@link QuestionsListActivity} of input events.
 */
public interface QuestionsListViewMvc extends ObservableViewMvc<QuestionsListViewMvc.Listener> {

    /**
     * Method that binds a list of questions to be displayed, to the View.
     *
     * @param questions List of Questions of instance {@link Question} to be bound to the View.
     */
    void bindQuestions(List<Question> questions);

    /**
     * Listener for User actions
     */
    interface Listener {
        /**
         * Callback Method of {@link Listener} invoked when the user clicks on any
         * question item being displayed in the list.
         * <p>
         * This method should launch the {@link com.techyourchance.journeytodependencyinjection.screens.questiondetails.QuestionDetailsActivity}
         * for the {@code question} clicked.
         * </p>
         *
         * @param question Instance of {@link Question} for the question item clicked.
         */
        void onQuestionClicked(Question question);
    }
}
