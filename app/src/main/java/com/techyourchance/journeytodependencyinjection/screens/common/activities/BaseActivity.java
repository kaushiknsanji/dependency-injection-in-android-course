package com.techyourchance.journeytodependencyinjection.screens.common.activities;

import android.annotation.SuppressLint;
import android.support.annotation.UiThread;
import android.support.v7.app.AppCompatActivity;

import com.techyourchance.journeytodependencyinjection.MyApplication;
import com.techyourchance.journeytodependencyinjection.common.dependencyinjection.CompositionRoot;
import com.techyourchance.journeytodependencyinjection.common.dependencyinjection.PresentationCompositionRoot;

/**
 * An {@link AppCompatActivity} class which is the base class
 * for all the activities in this application
 */
@SuppressLint("Registered")
public class BaseActivity extends AppCompatActivity {

    //CompositionRoot instance tied to the Activity
    private PresentationCompositionRoot mPresentationCompositionRoot;

    /**
     * Method that creates and returns the CompositionRoot tied to the Activity Lifecycle.
     *
     * @return A New or existing instance of {@link PresentationCompositionRoot}
     */
    @UiThread
    protected PresentationCompositionRoot getCompositionRoot() {
        if (mPresentationCompositionRoot == null) {
            mPresentationCompositionRoot = new PresentationCompositionRoot(
                    getAppCompositionRoot(),
                    this
            );
        }
        return mPresentationCompositionRoot;
    }

    /**
     * Method that returns {@link CompositionRoot} instance
     *
     * @return A {@link CompositionRoot} instance
     */
    private CompositionRoot getAppCompositionRoot() {
        return ((MyApplication) getApplication()).getCompositionRoot();
    }

}
