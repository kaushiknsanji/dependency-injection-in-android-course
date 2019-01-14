package com.techyourchance.journeytodependencyinjection.questions;

import com.google.gson.annotations.SerializedName;

public class QuestionWithBody {

    @SerializedName("title")
    private final String mTitle;

    @SerializedName("question_id")
    private final String mId;

    @SerializedName("body")
    private final String mBody;

    public QuestionWithBody(String title, String id, String body) {
        mTitle = title;
        mId = id;
        mBody = body;
    }

    public String getTitle() {
        return mTitle;
    }

    public String getId() {
        return mId;
    }

    public String getBody() {
        return mBody;
    }
}
