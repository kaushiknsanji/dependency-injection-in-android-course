package com.techyourchance.journeytodependencyinjection.questions;

import com.techyourchance.journeytodependencyinjection.common.BaseObservable;
import com.techyourchance.journeytodependencyinjection.networking.QuestionSchema;
import com.techyourchance.journeytodependencyinjection.networking.SingleQuestionResponseSchema;
import com.techyourchance.journeytodependencyinjection.networking.StackoverflowApi;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Use case class for encapsulating the logic of obtaining the details of
 * a question for the given question id, from the Stackoverflow API.
 */
public class FetchQuestionDetailsUseCase extends BaseObservable<FetchQuestionDetailsUseCase.Listener> {

    private StackoverflowApi mStackoverflowApi;
    private Call<SingleQuestionResponseSchema> mCall;

    public FetchQuestionDetailsUseCase(StackoverflowApi stackoverflowApi) {
        mStackoverflowApi = stackoverflowApi;
    }

    public void fetchQuestionDetailsAndNotify(String questionId) {
        cancelCurrentFetchIfActive();

        mCall = mStackoverflowApi.questionDetails(questionId);
        mCall.enqueue(new Callback<SingleQuestionResponseSchema>() {
            @Override
            public void onResponse(Call<SingleQuestionResponseSchema> call, Response<SingleQuestionResponseSchema> response) {
                SingleQuestionResponseSchema questionResponseSchema;
                if (response.isSuccessful() && (questionResponseSchema = response.body()) != null) {
                    notifySucceeded(questionDetailsFromQuestionSchema(questionResponseSchema.getQuestion()));
                } else {
                    notifyFailed();
                }
            }

            @Override
            public void onFailure(Call<SingleQuestionResponseSchema> call, Throwable t) {
                notifyFailed();
            }
        });
    }

    private QuestionDetails questionDetailsFromQuestionSchema(QuestionSchema questionSchema) {
        return new QuestionDetails(
                questionSchema.getId(),
                questionSchema.getTitle(),
                questionSchema.getBody(),
                questionSchema.getOwner().getUserDisplayName(),
                questionSchema.getOwner().getUserAvatarUrl()
        );
    }

    private void notifySucceeded(QuestionDetails question) {
        for (Listener listener : getListeners()) {
            listener.onFetchOfQuestionDetailsSucceeded(question);
        }
    }

    private void notifyFailed() {
        for (Listener listener : getListeners()) {
            listener.onFetchOfQuestionDetailsFailed();
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
         * the API Request Call for the detail of the {@code question} to be loaded.
         *
         * @param question Instance of {@link QuestionDetails} downloaded for the request.
         */
        void onFetchOfQuestionDetailsSucceeded(QuestionDetails question);

        /**
         * Callback Method of {@link Listener} invoked on failure of
         * the API Request Call for the detail of the question to be loaded.
         */
        void onFetchOfQuestionDetailsFailed();
    }

}
