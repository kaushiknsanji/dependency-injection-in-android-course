package com.techyourchance.journeytodependencyinjection.screens.questiondetails;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.techyourchance.journeytodependencyinjection.questions.QuestionDetails;
import com.techyourchance.journeytodependencyinjection.screens.common.activities.BaseActivity;
import com.techyourchance.journeytodependencyinjection.screens.common.dialogs.DialogsManager;
import com.techyourchance.journeytodependencyinjection.screens.common.dialogs.ServerErrorDialogFragment;
import com.techyourchance.journeytodependencyinjection.screens.common.mvcviews.ViewMvcFactory;
import com.techyourchance.journeytodependencyinjection.screens.common.viewmodel.ViewModelFactory;

import javax.inject.Inject;

public class QuestionDetailsActivity extends BaseActivity implements
        QuestionDetailsViewMvc.Listener, QuestionDetailsViewModel.Listener {

    public static final String EXTRA_QUESTION_ID = "EXTRA_QUESTION_ID";
    private static final String LOG_TAG = QuestionDetailsActivity.class.getSimpleName();
    @Inject
    ViewMvcFactory mViewMvcFactory;
    @Inject
    DialogsManager mDialogsManager;
    @Inject
    ViewModelFactory mViewModelFactory;

    private QuestionDetailsViewMvc mViewMvc;
    private String mQuestionId;
    private QuestionDetailsViewModel mQuestionDetailsViewModel;

    public static void start(Context context, String questionId) {
        Intent intent = new Intent(context, QuestionDetailsActivity.class);
        intent.putExtra(EXTRA_QUESTION_ID, questionId);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(LOG_TAG, "onCreate: Called");

        //Inject the QuestionDetailsActivity's dependencies
        getPresentationComponent().inject(this);

        mViewMvc = mViewMvcFactory.newInstance(QuestionDetailsViewMvc.class, null);
        setContentView(mViewMvc.getRootView());

        mQuestionDetailsViewModel = ViewModelProviders.of(this, mViewModelFactory).get(QuestionDetailsViewModel.class);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(mViewMvc.getFrameFragment().getId(), new SimpleFragment())
                    .commit();
        }

        //noinspection ConstantConditions
        mQuestionId = getIntent().getExtras().getString(EXTRA_QUESTION_ID);
    }

    @Override
    protected void onStart() {
        super.onStart();
        mViewMvc.registerListener(this);
        mQuestionDetailsViewModel.registerListener(this);
        mQuestionDetailsViewModel.fetchQuestionDetailsAndNotify(mQuestionId);
    }

    @Override
    protected void onStop() {
        super.onStop();
        mViewMvc.unregisterListener(this);
        mQuestionDetailsViewModel.unregisterListener(this);
    }

    /**
     * Callback Method of {@link QuestionDetailsViewModel.Listener} invoked on success of
     * the API Request Call for the detail of the {@code question} to be loaded.
     *
     * @param question Instance of {@link QuestionDetails} downloaded for the request.
     */
    @Override
    public void onQuestionDetailsFetched(QuestionDetails question) {
        mViewMvc.bindQuestion(question);
    }

    /**
     * Callback Method of {@link QuestionDetailsViewModel.Listener} invoked on failure of
     * the API Request Call for the detail of the question to be loaded.
     */
    @Override
    public void onQuestionDetailsFetchFailed() {
        mDialogsManager.showRetainedDialogWithId(ServerErrorDialogFragment.newInstance(), "");
    }
}