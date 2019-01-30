package com.techyourchance.journeytodependencyinjection.screens.questionslist;

import android.arch.lifecycle.ViewModel;

import com.techyourchance.journeytodependencyinjection.questions.Question;

import java.util.ArrayList;
import java.util.List;

/**
 * ViewModel for {@link QuestionsListActivity} that stores and provides the
 * list of {@link Question}s
 */
public class QuestionsListViewModel extends ViewModel {

    //List of Questions to store
    private List<Question> mQuestions = new ArrayList<>();

    /**
     * Getter Method for the list of {@link Question}s
     *
     * @return List of {@link Question}s stored
     */
    public List<Question> getQuestions() {
        return mQuestions;
    }

    /**
     * Setter Method that updates the list of {@link Question}s
     * in the ViewModel
     *
     * @param questions New List of {@link Question}s of downloaded
     */
    public void setQuestions(List<Question> questions) {
        mQuestions = questions;
    }

}
