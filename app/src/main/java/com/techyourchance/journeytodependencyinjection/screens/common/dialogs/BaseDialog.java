package com.techyourchance.journeytodependencyinjection.screens.common.dialogs;

import android.support.annotation.UiThread;
import android.support.v4.app.DialogFragment;

import com.techyourchance.journeytodependencyinjection.MyApplication;
import com.techyourchance.journeytodependencyinjection.common.dependencyinjection.application.ApplicationComponent;
import com.techyourchance.journeytodependencyinjection.common.dependencyinjection.presentation.PresentationComponent;
import com.techyourchance.journeytodependencyinjection.common.dependencyinjection.presentation.PresentationModule;

/**
 * A {@link DialogFragment} class which is the base class
 * for all the Dialogs in this application.
 */
public class BaseDialog extends DialogFragment {

    //Tracks if the PresentationComponent is used more than once in a Fragment to inject services
    private boolean mIsComponentUsed;

    /**
     * Method that creates and returns the PresentationComponent tied to the Activity Lifecycle.
     *
     * @return A New instance of {@link PresentationComponent}
     */
    @UiThread
    protected PresentationComponent getPresentationComponent() {
        if (mIsComponentUsed) {
            //Throwing exception when invoked more than once in the same Fragment
            throw new RuntimeException("No need to use PresentationComponent more than once");
        }
        mIsComponentUsed = true;
        return getApplicationComponent()
                .newPresentationComponent(new PresentationModule(requireActivity()));
    }

    /**
     * Method that returns {@link ApplicationComponent} instance
     *
     * @return An {@link ApplicationComponent} instance
     */
    private ApplicationComponent getApplicationComponent() {
        return ((MyApplication) requireActivity().getApplication()).getApplicationComponent();
    }

}
