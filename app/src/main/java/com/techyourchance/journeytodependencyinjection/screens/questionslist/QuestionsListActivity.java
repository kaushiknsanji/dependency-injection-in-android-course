package com.techyourchance.journeytodependencyinjection.screens.questionslist;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.widget.Toast;

import com.techyourchance.journeytodependencyinjection.questions.FetchQuestionsListUseCase;
import com.techyourchance.journeytodependencyinjection.questions.Question;
import com.techyourchance.journeytodependencyinjection.screens.common.activities.BaseActivity;
import com.techyourchance.journeytodependencyinjection.screens.common.dialogs.DialogsManager;
import com.techyourchance.journeytodependencyinjection.screens.common.dialogs.ServerErrorDialogFragment;
import com.techyourchance.journeytodependencyinjection.screens.common.mvcviews.ViewMvcFactory;
import com.techyourchance.journeytodependencyinjection.screens.common.viewmodel.ViewModelFactory;
import com.techyourchance.journeytodependencyinjection.screens.questiondetails.QuestionDetailsActivity;

import java.util.List;

import javax.inject.Inject;

public class QuestionsListActivity extends BaseActivity implements
        QuestionsListViewMvc.Listener, FetchQuestionsListUseCase.Listener {

    private static final int NUM_OF_QUESTIONS_TO_FETCH = 20;

    @Inject
    ViewMvcFactory mViewMvcFactory;
    @Inject
    FetchQuestionsListUseCase mFetchQuestionsListUseCase;
    @Inject
    DialogsManager mDialogsManager;
    @Inject
    ViewModelFactory mViewModelFactory;

    private QuestionsListViewMvc mViewMvc;

    private QuestionsListViewModel mQuestionsListViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Inject the QuestionsListActivity's dependencies
        getPresentationComponent().inject(this);

        mViewMvc = mViewMvcFactory.newInstance(QuestionsListViewMvc.class, null);
        setContentView(mViewMvc.getRootView());

        mQuestionsListViewModel = ViewModelProviders.of(this, mViewModelFactory).get(QuestionsListViewModel.class);
    }

    @Override
    protected void onStart() {
        super.onStart();
        mViewMvc.registerListener(this);
        mFetchQuestionsListUseCase.registerListener(this);

        if (mQuestionsListViewModel.getQuestions().isEmpty()) {
            //Downloading the Questions data when not present
            mFetchQuestionsListUseCase.fetchLastActiveQuestionsAndNotify(NUM_OF_QUESTIONS_TO_FETCH);
            Toast.makeText(this, "Loaded from Use Case", Toast.LENGTH_SHORT).show();
        } else {
            //Binding the Questions data stored previously in the ViewModel
            mViewMvc.bindQuestions(mQuestionsListViewModel.getQuestions());
            Toast.makeText(this, "Loaded from ViewModel", Toast.LENGTH_SHORT).show();
        }
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
        //Store the list of questions in the ViewModel
        mQuestionsListViewModel.setQuestions(questions);
        //Bind the Questions data to the View
        mViewMvc.bindQuestions(questions);
    }

    /**
     * Callback Method of {@link FetchQuestionsListUseCase.Listener} invoked on failure of
     * the API Request Call for the new list of Questions to be loaded.
     */
    @Override
    public void onFetchOfQuestionsFailed() {
        mDialogsManager.showDialogWithId(ServerErrorDialogFragment.newInstance(), "");
    }
}
