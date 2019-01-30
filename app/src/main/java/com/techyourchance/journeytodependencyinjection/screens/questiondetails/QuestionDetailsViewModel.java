package com.techyourchance.journeytodependencyinjection.screens.questiondetails;

import android.arch.lifecycle.ViewModel;

import com.techyourchance.journeytodependencyinjection.questions.FetchQuestionDetailsUseCase;
import com.techyourchance.journeytodependencyinjection.questions.QuestionDetails;

import java.util.Collections;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * ViewModel for {@link QuestionDetailsActivity} to interact with the {@link FetchQuestionDetailsUseCase} class
 */
public class QuestionDetailsViewModel extends ViewModel implements FetchQuestionDetailsUseCase.Listener {

    //Use Case instance for Question Details
    private final FetchQuestionDetailsUseCase mFetchQuestionDetailsUseCase;

    //Thread-safe set of listeners
    private Set<Listener> mListeners = Collections.newSetFromMap(new ConcurrentHashMap<Listener, Boolean>(1));

    //To cache the QuestionDetails data
    private QuestionDetails mQuestionDetails;

    /**
     * Constructor of {@link QuestionDetailsViewModel}
     *
     * @param fetchQuestionDetailsUseCase Use Case instance for Question Details
     */
    public QuestionDetailsViewModel(FetchQuestionDetailsUseCase fetchQuestionDetailsUseCase) {
        mFetchQuestionDetailsUseCase = fetchQuestionDetailsUseCase;
        mFetchQuestionDetailsUseCase.registerListener(this);
    }

    /**
     * This method will be called when this ViewModel is no longer used and will be destroyed.
     * <p>
     * It is useful when ViewModel observes some data and you need to clear this subscription to
     * prevent a leak of this ViewModel.
     */
    @Override
    protected void onCleared() {
        super.onCleared();
        mFetchQuestionDetailsUseCase.unregisterListener(this);
    }

    /**
     * Method that downloads the details for the question with Id {@code questionId}
     * when not present in the cache {@link #mQuestionDetails}; or provide the details
     * when already present in cache {@link #mQuestionDetails}
     *
     * @param questionId String value of the Question Id whose details are required
     */
    public void fetchQuestionDetailsAndNotify(String questionId) {
        if (mQuestionDetails == null || !mQuestionDetails.getId().equals(questionId)) {
            mFetchQuestionDetailsUseCase.fetchQuestionDetailsAndNotify(questionId);
        } else {
            notifySuccess(mQuestionDetails);
        }
    }

    /**
     * Method that registers the listener {@link Listener} for receiving events
     *
     * @param listener The {@link Listener} to be registered
     */
    public void registerListener(Listener listener) {
        mListeners.add(listener);
    }

    /**
     * Method that unregisters the registered {@code listener}
     *
     * @param listener The {@link Listener} to be unregistered
     */
    public void unregisterListener(Listener listener) {
        mListeners.remove(listener);
    }

    /**
     * Callback Method of {@link FetchQuestionDetailsUseCase.Listener} invoked on success of
     * the API Request Call for the detail of the {@code question} to be loaded.
     *
     * @param question Instance of {@link QuestionDetails} downloaded for the request.
     */
    @Override
    public void onFetchOfQuestionDetailsSucceeded(QuestionDetails question) {
        mQuestionDetails = question;
        notifySuccess(question);
    }

    /**
     * Method that notifies the listeners on success of retrieving the details of the question.
     *
     * @param questionDetails Instance of {@link QuestionDetails} retrieved for the request.
     */
    private void notifySuccess(QuestionDetails questionDetails) {
        for (Listener listener : mListeners) {
            listener.onQuestionDetailsFetched(questionDetails);
        }
    }

    /**
     * Callback Method of {@link FetchQuestionDetailsUseCase.Listener} invoked on failure of
     * the API Request Call for the detail of the question to be loaded.
     */
    @Override
    public void onFetchOfQuestionDetailsFailed() {
        notifyFailure();
    }

    /**
     * Method that notifies the listeners on failure of retrieving the details of the question.
     */
    private void notifyFailure() {
        for (Listener listener : mListeners) {
            listener.onQuestionDetailsFetchFailed();
        }
    }

    /**
     * Listener for network events
     */
    interface Listener {
        /**
         * Callback Method of {@link Listener} invoked on success of
         * the API Request Call for the detail of the {@code question} to be loaded.
         *
         * @param question Instance of {@link QuestionDetails} downloaded for the request.
         */
        void onQuestionDetailsFetched(QuestionDetails question);

        /**
         * Callback Method of {@link Listener} invoked on failure of
         * the API Request Call for the detail of the question to be loaded.
         */
        void onQuestionDetailsFetchFailed();
    }
}
