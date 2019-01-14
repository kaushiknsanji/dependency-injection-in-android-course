package com.techyourchance.journeytodependencyinjection.networking;

import com.google.gson.annotations.SerializedName;
import com.techyourchance.journeytodependencyinjection.questions.QuestionWithBody;

import java.util.Collections;
import java.util.List;

public class SingleQuestionResponseSchema {

    @SerializedName("items")
    private final List<QuestionWithBody> mQuestions;

    public SingleQuestionResponseSchema(QuestionWithBody question) {
        mQuestions = Collections.singletonList(question);
    }

    public QuestionWithBody getQuestion() {
        return mQuestions.get(0);
    }
}
