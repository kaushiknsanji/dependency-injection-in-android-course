package com.techyourchance.journeytodependencyinjection.questions;

import com.google.gson.annotations.SerializedName;

public class Question {

    @SerializedName("title")
    private final String mTitle;

    @SerializedName("question_id")
    private final String mId;

    public Question(String title, String id) {
        mTitle = title;
        mId = id;
    }

    public String getTitle() {
        return mTitle;
    }

    public String getId() {
        return mId;
    }
}
