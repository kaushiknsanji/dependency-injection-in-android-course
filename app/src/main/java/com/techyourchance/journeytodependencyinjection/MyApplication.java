package com.techyourchance.journeytodependencyinjection;

import android.app.Application;
import android.support.annotation.UiThread;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * {@link Application} class for retrieving shared instances
 */
public class MyApplication extends Application {

    //Shared Retrofit instance
    private Retrofit mRetrofit;

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
}
