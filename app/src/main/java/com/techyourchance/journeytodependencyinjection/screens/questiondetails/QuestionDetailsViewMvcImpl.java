package com.techyourchance.journeytodependencyinjection.screens.questiondetails;

import android.text.Html;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import com.techyourchance.journeytodependencyinjection.R;
import com.techyourchance.journeytodependencyinjection.questions.QuestionWithBody;
import com.techyourchance.journeytodependencyinjection.screens.common.mvcviews.BaseViewMvc;

/**
 * Implementation of the MVC View Interface to notify the controller {@link QuestionDetailsActivity}
 * of input events.
 */
public class QuestionDetailsViewMvcImpl extends BaseViewMvc<QuestionDetailsViewMvc.Listener>
        implements QuestionDetailsViewMvc {

    private TextView mTxtQuestionBody;

    public QuestionDetailsViewMvcImpl(LayoutInflater layoutInflater, ViewGroup container) {
        setRootView(layoutInflater.inflate(R.layout.layout_question_details, container, false));
        mTxtQuestionBody = findViewById(R.id.txt_question_body);
    }

    /**
     * Method that binds the {@link QuestionWithBody} data to the View.
     *
     * @param question Instance of {@link QuestionWithBody}
     */
    @Override
    public void bindQuestion(QuestionWithBody question) {
        String questionBody = question.getBody();

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            mTxtQuestionBody.setText(Html.fromHtml(questionBody, Html.FROM_HTML_MODE_LEGACY));
        } else {
            mTxtQuestionBody.setText(Html.fromHtml(questionBody));
        }
    }
}
