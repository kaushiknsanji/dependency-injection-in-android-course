package com.techyourchance.journeytodependencyinjection.common.dependencyinjection.presentation;

import android.app.Activity;
import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;

import com.techyourchance.journeytodependencyinjection.screens.common.ImageLoader;
import com.techyourchance.journeytodependencyinjection.screens.common.dialogs.DialogsManager;

import dagger.Module;
import dagger.Provides;

/**
 * Dagger Module for creating and exposing services, tied to the Activity Lifecycle.
 */
@Module
public class PresentationModule {

    //Activity instance
    private final FragmentActivity mActivity;

    /**
     * Constructor of {@link PresentationModule}
     *
     * @param activity Instance of {@link FragmentActivity}
     */
    public PresentationModule(FragmentActivity activity) {
        mActivity = activity;
    }

    /**
     * Method that creates and returns a {@link DialogsManager} instance
     *
     * @param fragmentManager Instance of {@link FragmentManager} provided by Dagger.
     * @return A {@link DialogsManager} instance to manage Dialogs
     */
    @Provides
    DialogsManager getDialogsManager(FragmentManager fragmentManager) {
        return new DialogsManager(fragmentManager);
    }

    /**
     * Method that obtains the {@link LayoutInflater} from the given context.
     *
     * @param activity Instance of {@link Activity} provided by Dagger
     * @return Instance of {@link LayoutInflater}
     */
    @Provides
    LayoutInflater getLayoutInflater(Activity activity) {
        return LayoutInflater.from(activity);
    }

    /**
     * Method that obtains the {@link FragmentManager} for managing the Fragments
     *
     * @return Instance of {@link FragmentManager}
     */
    @Provides
    FragmentManager getFragmentManager() {
        return mActivity.getSupportFragmentManager();
    }

    /**
     * Getter for the Activity registered
     *
     * @return Instance of {@link Activity} registered
     */
    @Provides
    Activity getActivity() {
        return mActivity;
    }

    /**
     * Getter for the {@link Context}
     *
     * @param activity Instance of {@link Activity} provided by Dagger
     * @return Instance of Activity as the {@link Context}
     */
    @Provides
    Context context(Activity activity) {
        return activity;
    }

    /**
     * Method that creates and returns an {@link ImageLoader} instance
     *
     * @param activity Instance of {@link Activity} provided by Dagger
     * @return An {@link ImageLoader} instance
     */
    @Provides
    ImageLoader getImageLoader(Activity activity) {
        return new ImageLoader(activity);
    }
}
