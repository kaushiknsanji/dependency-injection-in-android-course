package com.techyourchance.journeytodependencyinjection.screens.questionslist;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;

import com.techyourchance.journeytodependencyinjection.MyApplication;
import com.techyourchance.journeytodependencyinjection.questions.FetchQuestionsListUseCase;
import com.techyourchance.journeytodependencyinjection.questions.Question;
import com.techyourchance.journeytodependencyinjection.screens.common.dialogs.DialogsManager;
import com.techyourchance.journeytodependencyinjection.screens.common.dialogs.ServerErrorDialogFragment;
import com.techyourchance.journeytodependencyinjection.screens.questiondetails.QuestionDetailsActivity;

import java.util.List;

public class QuestionsListActivity extends AppCompatActivity implements
        QuestionsListViewMvc.Listener, FetchQuestionsListUseCase.Listener {

    private static final int NUM_OF_QUESTIONS_TO_FETCH = 20;

    private QuestionsListViewMvc mViewMvc;

    private FetchQuestionsListUseCase mFetchQuestionsListUseCase;

    private DialogsManager mDialogsManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mViewMvc = new QuestionsListViewMvcImpl(LayoutInflater.from(this), null);
        setContentView(mViewMvc.getRootView());

        mFetchQuestionsListUseCase = new FetchQuestionsListUseCase(
                ((MyApplication) getApplication()).getStackoverflowApi()
        );

        mDialogsManager = new DialogsManager(getSupportFragmentManager());
    }

    @Override
    protected void onStart() {
        super.onStart();
        mViewMvc.registerListener(this);
        mFetchQuestionsListUseCase.registerListener(this);
        mFetchQuestionsListUseCase.fetchLastActiveQuestionsAndNotify(NUM_OF_QUESTIONS_TO_FETCH);
    }

    @Override
    protected void onStop() {
        super.onStop();
        mViewMvc.unregisterListener(this);
        mFetchQuestionsListUseCase.unregisterListener(this);
    }

    /**
     * Callback Method of {@link QuestionsListViewMvc.Listener} invoked when the user clicks on any
     * question item being displayed in the list.
     * <p>
     * This method should launch the {@link QuestionDetailsActivity}
     * for the {@code question} clicked.
     * </p>
     *
     * @param question Instance of {@link Question} for the question item clicked.
     */
    @Override
    public void onQuestionClicked(Question question) {
        QuestionDetailsActivity.start(this, question.getId());
    }

    /**
     * Callback Method of {@link FetchQuestionsListUseCase.Listener} invoked on success of
     * the API Request Call for the new list of Questions to be loaded.
     *
     * @param questions List of {@link Question}s downloaded for the request.
     */
    @Override
    public void onFetchOfQuestionsSucceeded(List<Question> questions) {
        mViewMvc.bindQuestions(questions);
    }

    /**
     * Callback Method of {@link FetchQuestionsListUseCase.Listener} invoked on failure of
     * the API Request Call for the new list of Questions to be loaded.
     */
    @Override
    public void onFetchOfQuestionsFailed() {
        mDialogsManager.showRetainedDialogWithId(ServerErrorDialogFragment.newInstance(), "");
    }
}
