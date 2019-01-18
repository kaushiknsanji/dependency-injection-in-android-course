package com.techyourchance.journeytodependencyinjection.common.dependencyinjection;

import android.support.annotation.UiThread;

import com.techyourchance.journeytodependencyinjection.Constants;
import com.techyourchance.journeytodependencyinjection.networking.StackoverflowApi;
import com.techyourchance.journeytodependencyinjection.questions.FetchQuestionDetailsUseCase;
import com.techyourchance.journeytodependencyinjection.questions.FetchQuestionsListUseCase;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Composition class for creating and exposing services
 */
@UiThread
public class CompositionRoot {
    //Shared Retrofit instance
    private Retrofit mRetrofit;

    //Shared Stackoverflow API instance
    private StackoverflowApi mStackoverflowApi;

    /**
     * Method that creates and returns a {@link Retrofit} instance
     * for the url {@link Constants#BASE_URL}
     *
     * @return New of existing instance of {@link Retrofit}
     */
    private Retrofit getRetrofit() {
        if (mRetrofit == null) {
            mRetrofit = new Retrofit.Builder()
                    .baseUrl(Constants.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return mRetrofit;
    }

    /**
     * Method that creates and returns a {@link StackoverflowApi} instance.
     *
     * @return New or existing instance of {@link StackoverflowApi}
     */
    private StackoverflowApi getStackoverflowApi() {
        if (mStackoverflowApi == null) {
            mStackoverflowApi = getRetrofit().create(StackoverflowApi.class);
        }
        return mStackoverflowApi;
    }

    /**
     * Method that creates and returns a {@link FetchQuestionsListUseCase} instance
     *
     * @return A {@link FetchQuestionsListUseCase} instance
     */
    public FetchQuestionsListUseCase getFetchQuestionsListUseCase() {
        return new FetchQuestionsListUseCase(getStackoverflowApi());
    }

    /**
     * Method that creates and returns a {@link FetchQuestionDetailsUseCase} instance
     *
     * @return A {@link FetchQuestionDetailsUseCase} instance
     */
    public FetchQuestionDetailsUseCase getFetchQuestionDetailsUseCase() {
        return new FetchQuestionDetailsUseCase(getStackoverflowApi());
    }
}
