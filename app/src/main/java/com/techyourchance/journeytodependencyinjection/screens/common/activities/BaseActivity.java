package com.techyourchance.journeytodependencyinjection.screens.common.activities;

import android.support.annotation.UiThread;
import android.support.v7.app.AppCompatActivity;

import com.techyourchance.journeytodependencyinjection.MyApplication;
import com.techyourchance.journeytodependencyinjection.common.dependencyinjection.application.ApplicationComponent;
import com.techyourchance.journeytodependencyinjection.common.dependencyinjection.presentation.PresentationComponent;
import com.techyourchance.journeytodependencyinjection.common.dependencyinjection.presentation.PresentationModule;

/**
 * An Abstract {@link AppCompatActivity} class which is the base class
 * for all the activities in this application
 */
public abstract class BaseActivity extends AppCompatActivity {

    //Tracks if the PresentationComponent is used more than once in an Activity to inject services
    private boolean mIsComponentUsed;

    /**
     * Method that creates and returns the PresentationComponent tied to the Activity Lifecycle.
     *
     * @return A New instance of {@link PresentationComponent}
     */
    @UiThread
    protected PresentationComponent getPresentationComponent() {
        if (mIsComponentUsed) {
            //Throwing exception when invoked more than once in the same Activity
            throw new RuntimeException("No need to use PresentationComponent more than once");
        }
        mIsComponentUsed = true;
        return getApplicationComponent()
                .newPresentationComponent(new PresentationModule(this));
    }

    /**
     * Method that returns {@link ApplicationComponent} instance
     *
     * @return An {@link ApplicationComponent} instance
     */
    private ApplicationComponent getApplicationComponent() {
        return ((MyApplication) getApplication()).getApplicationComponent();
    }

}
