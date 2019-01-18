package com.techyourchance.journeytodependencyinjection;

import android.app.Application;
import android.support.annotation.UiThread;

import com.techyourchance.journeytodependencyinjection.networking.StackoverflowApi;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * {@link Application} class for retrieving shared instances
 */
public class MyApplication extends Application {

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
    @UiThread
    public Retrofit getRetrofit() {
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
    @UiThread
    public StackoverflowApi getStackoverflowApi() {
        if (mStackoverflowApi == null) {
            mStackoverflowApi = getRetrofit().create(StackoverflowApi.class);
        }
        return mStackoverflowApi;
    }

}