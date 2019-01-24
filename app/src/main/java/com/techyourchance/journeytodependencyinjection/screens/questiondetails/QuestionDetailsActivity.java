package com.techyourchance.journeytodependencyinjection.screens.questiondetails;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.techyourchance.journeytodependencyinjection.questions.FetchQuestionDetailsUseCase;
import com.techyourchance.journeytodependencyinjection.questions.QuestionDetails;
import com.techyourchance.journeytodependencyinjection.screens.common.activities.BaseActivity;
import com.techyourchance.journeytodependencyinjection.screens.common.dialogs.DialogsManager;
import com.techyourchance.journeytodependencyinjection.screens.common.dialogs.ServerErrorDialogFragment;
import com.techyourchance.journeytodependencyinjection.screens.common.mvcviews.ViewMvcFactory;

import javax.inject.Inject;

public class QuestionDetailsActivity extends BaseActivity implements
        QuestionDetailsViewMvc.Listener, FetchQuestionDetailsUseCase.Listener {

    public static final String EXTRA_QUESTION_ID = "EXTRA_QUESTION_ID";

    @Inject
    ViewMvcFactory mViewMvcFactory;
    @Inject
    FetchQuestionDetailsUseCase mFetchQuestionDetailsUseCase;
    @Inject
    DialogsManager mDialogsManager;

    private QuestionDetailsViewMvc mViewMvc;
    private String mQuestionId;

    public static void start(Context context, String questionId) {
        Intent intent = new Intent(context, QuestionDetailsActivity.class);
        intent.putExtra(EXTRA_QUESTION_ID, questionId);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Inject the QuestionDetailsActivity's dependencies
        getPresentationComponent().inject(this);

        mViewMvc = mViewMvcFactory.newInstance(QuestionDetailsViewMvc.class, null);
        setContentView(mViewMvc.getRootView());

        //noinspection ConstantConditions
        mQuestionId = getIntent().getExtras().getString(EXTRA_QUESTION_ID);
    }

    @Override
    protected void onStart() {
        super.onStart();
        mViewMvc.registerListener(this);
        mFetchQuestionDetailsUseCase.registerListener(this);
        mFetchQuestionDetailsUseCase.fetchQuestionDetailsAndNotify(mQuestionId);
    }

    @Override
    protected void onStop() {
        super.onStop();
        mViewMvc.unregisterListener(this);
        mFetchQuestionDetailsUseCase.unregisterListener(this);
    }

    /**
     * Callback Method of {@link FetchQuestionDetailsUseCase.Listener} invoked on success of
     * the API Request Call for the detail of the {@code question} to be loaded.
     *
     * @param question Instance of {@link QuestionDetails} downloaded for the request.
     */
    @Override
    public void onFetchOfQuestionDetailsSucceeded(QuestionDetails question) {
        mViewMvc.bindQuestion(question);
    }

    /**
     * Callback Method of {@link FetchQuestionDetailsUseCase.Listener} invoked on failure of
     * the API Request Call for the detail of the question to be loaded.
     */
    @Override
    public void onFetchOfQuestionDetailsFailed() {
        mDialogsManager.showRetainedDialogWithId(ServerErrorDialogFragment.newInstance(), "");
    }
}