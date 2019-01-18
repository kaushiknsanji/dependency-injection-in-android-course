package com.techyourchance.journeytodependencyinjection.screens.questiondetails;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;

import com.techyourchance.journeytodependencyinjection.MyApplication;
import com.techyourchance.journeytodependencyinjection.questions.FetchQuestionDetailsUseCase;
import com.techyourchance.journeytodependencyinjection.questions.QuestionWithBody;
import com.techyourchance.journeytodependencyinjection.screens.common.dialogs.DialogsManager;
import com.techyourchance.journeytodependencyinjection.screens.common.dialogs.ServerErrorDialogFragment;

public class QuestionDetailsActivity extends AppCompatActivity implements
        QuestionDetailsViewMvc.Listener, FetchQuestionDetailsUseCase.Listener {

    public static final String EXTRA_QUESTION_ID = "EXTRA_QUESTION_ID";

    private QuestionDetailsViewMvc mViewMvc;

    private FetchQuestionDetailsUseCase mFetchQuestionDetailsUseCase;

    private DialogsManager mDialogsManager;

    private String mQuestionId;

    public static void start(Context context, String questionId) {
        Intent intent = new Intent(context, QuestionDetailsActivity.class);
        intent.putExtra(EXTRA_QUESTION_ID, questionId);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mViewMvc = new QuestionDetailsViewMvcImpl(LayoutInflater.from(this), null);
        setContentView(mViewMvc.getRootView());

        mFetchQuestionDetailsUseCase = new FetchQuestionDetailsUseCase(
                ((MyApplication) getApplication()).getRetrofit()
        );

        mDialogsManager = new DialogsManager(getSupportFragmentManager());

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
     * @param question Instance of {@link QuestionWithBody} downloaded for the request.
     */
    @Override
    public void onFetchOfQuestionDetailsSucceeded(QuestionWithBody question) {
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