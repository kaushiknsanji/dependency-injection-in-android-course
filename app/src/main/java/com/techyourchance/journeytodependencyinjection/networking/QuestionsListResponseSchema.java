package com.techyourchance.journeytodependencyinjection.networking;

import com.google.gson.annotations.SerializedName;
import com.techyourchance.journeytodependencyinjection.questions.Question;

import java.util.List;

public class QuestionsListResponseSchema {

    @SerializedName("items")
    private final List<Question> mQuestions;

    public QuestionsListResponseSchema(List<Question> questions) {
        mQuestions = questions;
    }

    public List<Question> getQuestions() {
        return mQuestions;
    }
}
