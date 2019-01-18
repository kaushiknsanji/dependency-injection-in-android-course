package com.techyourchance.journeytodependencyinjection.questions;

import com.techyourchance.journeytodependencyinjection.common.BaseObservable;
import com.techyourchance.journeytodependencyinjection.networking.QuestionsListResponseSchema;
import com.techyourchance.journeytodependencyinjection.networking.StackoverflowApi;

import java.util.Collections;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * Use case class for encapsulating the logic of obtaining a list of {@link Question}s from the
 * Stackoverflow API.
 */
public class FetchQuestionsListUseCase extends BaseObservable<FetchQuestionsListUseCase.Listener> {

    private StackoverflowApi mStackoverflowApi;
    private Call<QuestionsListResponseSchema> mCall;

    public FetchQuestionsListUseCase(Retrofit retrofit) {
        mStackoverflowApi = retrofit.create(StackoverflowApi.class);
    }

    public void fetchLastActiveQuestionsAndNotify(int numOfQuestions) {
        cancelCurrentFetchIfActive();

        mCall = mStackoverflowApi.lastActiveQuestions(numOfQuestions);
        mCall.enqueue(new Callback<QuestionsListResponseSchema>() {
            @Override
            public void onResponse(Call<QuestionsListResponseSchema> call, Response<QuestionsListResponseSchema> response) {
                QuestionsListResponseSchema responseSchema;
                if (response.isSuccessful() && (responseSchema = response.body()) != null) {
                    notifySucceeded(responseSchema.getQuestions());
                } else {
                    notifyFailed();
                }
            }

            @Override
            public void onFailure(Call<QuestionsListResponseSchema> call, Throwable t) {
                notifyFailed();
            }
        });
    }

    private void notifySucceeded(List<Question> questions) {
        List<Question> unmodifiableQuestions = Collections.unmodifiableList(questions);
        for (Listener listener : getListeners()) {
            listener.onFetchOfQuestionsSucceeded(unmodifiableQuestions);
        }
    }

    private void notifyFailed() {
        for (Listener listener : getListeners()) {
            listener.onFetchOfQuestionsFailed();
        }
    }

    private void cancelCurrentFetchIfActive() {
        if (mCall != null && !mCall.isCanceled() && !mCall.isExecuted()) {
            mCall.cancel();
        }
    }

    /**
     * Listener for network events
     */
    public interface Listener {
        /**
         * Callback Method of {@link Listener} invoked on success of
         * the API Request Call for the new list of Questions to be loaded.
         *
         * @param questions List of {@link Question}s downloaded for the request.
         */
        void onFetchOfQuestionsSucceeded(List<Question> questions);

        /**
         * Callback Method of {@link Listener} invoked on failure of
         * the API Request Call for the new list of Questions to be loaded.
         */
        void onFetchOfQuestionsFailed();
    }
}
