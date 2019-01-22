package com.techyourchance.journeytodependencyinjection.common.dependencyinjection.application;

import com.techyourchance.journeytodependencyinjection.Constants;
import com.techyourchance.journeytodependencyinjection.networking.StackoverflowApi;
import com.techyourchance.journeytodependencyinjection.questions.FetchQuestionDetailsUseCase;
import com.techyourchance.journeytodependencyinjection.questions.FetchQuestionsListUseCase;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Dagger Module for creating and exposing services, tied to the Application Lifecycle.
 */

@Module
public class ApplicationModule {

    /**
     * Method that creates and returns a {@link Retrofit} instance
     * for the url {@link Constants#BASE_URL}
     *
     * @return New of existing instance of {@link Retrofit}
     */
    @Singleton
    @Provides
    Retrofit getRetrofit() {
        return new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    /**
     * Method that creates and returns a {@link StackoverflowApi} instance.
     *
     * @param retrofit Instance of {@link Retrofit} provided by Dagger.
     * @return New or existing instance of {@link StackoverflowApi}
     */
    @Singleton
    @Provides
    StackoverflowApi getStackoverflowApi(Retrofit retrofit) {
        return retrofit.create(StackoverflowApi.class);
    }

    /**
     * Method that creates and returns a {@link FetchQuestionsListUseCase} instance
     *
     * @param stackoverflowApi Instance of {@link StackoverflowApi} provided by Dagger.
     * @return A {@link FetchQuestionsListUseCase} instance
     */
    @Provides
    FetchQuestionsListUseCase getFetchQuestionsListUseCase(StackoverflowApi stackoverflowApi) {
        return new FetchQuestionsListUseCase(stackoverflowApi);
    }

    /**
     * Method that creates and returns a {@link FetchQuestionDetailsUseCase} instance
     *
     * @param stackoverflowApi Instance of {@link StackoverflowApi} provided by Dagger.
     * @return A {@link FetchQuestionDetailsUseCase} instance
     */
    @Provides
    FetchQuestionDetailsUseCase getFetchQuestionDetailsUseCase(StackoverflowApi stackoverflowApi) {
        return new FetchQuestionDetailsUseCase(stackoverflowApi);
    }
}
