package com.techyourchance.journeytodependencyinjection.screens.questionslist;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;

import com.techyourchance.journeytodependencyinjection.Constants;
import com.techyourchance.journeytodependencyinjection.networking.QuestionsListResponseSchema;
import com.techyourchance.journeytodependencyinjection.networking.StackoverflowApi;
import com.techyourchance.journeytodependencyinjection.questions.Question;
import com.techyourchance.journeytodependencyinjection.screens.common.ServerErrorDialogFragment;
import com.techyourchance.journeytodependencyinjection.screens.questiondetails.QuestionDetailsActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class QuestionsListActivity extends AppCompatActivity implements
        Callback<QuestionsListResponseSchema>, QuestionsListViewMvc.Listener {

    private QuestionsListViewMvc mViewMvc;

    private StackoverflowApi mStackoverflowApi;

    private Call<QuestionsListResponseSchema> mCall;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mViewMvc = new QuestionsListViewMvcImpl(LayoutInflater.from(this), null);
        setContentView(mViewMvc.getRootView());

        // init retrofit
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        mStackoverflowApi = retrofit.create(StackoverflowApi.class);
    }

    @Override
    protected void onStart() {
        super.onStart();
        mViewMvc.registerListener(this);
        mCall = mStackoverflowApi.lastActiveQuestions(20);
        mCall.enqueue(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        mViewMvc.unregisterListener(this);
        if (mCall != null) {
            mCall.cancel();
        }
    }

    @Override
    public void onResponse(Call<QuestionsListResponseSchema> call, Response<QuestionsListResponseSchema> response) {
        QuestionsListResponseSchema responseSchema;
        if (response.isSuccessful() && (responseSchema = response.body()) != null) {
            mViewMvc.bindQuestions(responseSchema.getQuestions());
        } else {
            onFailure(call, null);
        }
    }

    @Override
    public void onFailure(Call<QuestionsListResponseSchema> call, Throwable t) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .add(ServerErrorDialogFragment.newInstance(), null)
                .commitAllowingStateLoss();
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
}
