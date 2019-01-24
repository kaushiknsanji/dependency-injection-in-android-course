package com.techyourchance.journeytodependencyinjection.screens.common.activities;

import android.annotation.SuppressLint;
import android.support.annotation.UiThread;
import android.support.v7.app.AppCompatActivity;

import com.techyourchance.journeytodependencyinjection.MyApplication;
import com.techyourchance.journeytodependencyinjection.common.dependencyinjection.Injector;
import com.techyourchance.journeytodependencyinjection.common.dependencyinjection.application.ApplicationComponent;
import com.techyourchance.journeytodependencyinjection.common.dependencyinjection.presentation.DaggerPresentationComponent;
import com.techyourchance.journeytodependencyinjection.common.dependencyinjection.presentation.PresentationComponent;
import com.techyourchance.journeytodependencyinjection.common.dependencyinjection.presentation.PresentationModule;

/**
 * An {@link AppCompatActivity} class which is the base class
 * for all the activities in this application
 */
@SuppressLint("Registered")
public class BaseActivity extends AppCompatActivity {

    //Tracks if the Injector is used more than once in an Activity
    private boolean mIsInjectorUsed;

    /**
     * Method that creates and returns the {@link Injector} instance
     *
     * @return An {@link Injector} instance to inject dependencies
     */
    @UiThread
    protected Injector getInjector() {
        if (mIsInjectorUsed) {
            //Throwing exception when invoked more than once in the same Activity
            throw new RuntimeException("No need to use Injector more than once");
        }
        mIsInjectorUsed = true;
        return new Injector(getPresentationComponent());
    }

    /**
     * Method that returns {@link ApplicationComponent} instance
     *
     * @return An {@link ApplicationComponent} instance
     */
    private ApplicationComponent getApplicationComponent() {
        return ((MyApplication) getApplication()).getApplicationComponent();
    }

    /**
     * Method that creates and returns the PresentationComponent tied to the Activity Lifecycle.
     *
     * @return A New instance of {@link PresentationComponent}
     */
    private PresentationComponent getPresentationComponent() {
        return DaggerPresentationComponent.builder()
                .presentationModule(new PresentationModule(getApplicationComponent(), this))
                .build();
    }

}
