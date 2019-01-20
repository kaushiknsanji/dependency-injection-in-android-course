package com.techyourchance.journeytodependencyinjection.common.dependencyinjection;

import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;

import com.techyourchance.journeytodependencyinjection.questions.FetchQuestionDetailsUseCase;
import com.techyourchance.journeytodependencyinjection.questions.FetchQuestionsListUseCase;
import com.techyourchance.journeytodependencyinjection.screens.common.ImageLoader;
import com.techyourchance.journeytodependencyinjection.screens.common.dialogs.DialogsManager;
import com.techyourchance.journeytodependencyinjection.screens.common.mvcviews.ViewMvcFactory;

/**
 * Composition Class for creating and exposing services, tied to the Activity/Fragment Lifecycle.
 */
public class PresentationCompositionRoot {

    //CompositionRoot instance tied to the Application Lifecycle
    private final CompositionRoot mCompositionRoot;

    //Activity instance
    private final AppCompatActivity mActivity;

    /**
     * Constructor of {@link PresentationCompositionRoot}
     * @param compositionRoot Instance of App {@link CompositionRoot}
     * @param activity Instance of {@link AppCompatActivity}
     */
    public PresentationCompositionRoot(CompositionRoot compositionRoot, AppCompatActivity activity) {
        mCompositionRoot = compositionRoot;
        mActivity = activity;
    }

    /**
     * Method that creates and returns a {@link DialogsManager} instance
     *
     * @return A {@link DialogsManager} instance to manage Dialogs
     */
    public DialogsManager getDialogsManager() {
        return new DialogsManager(getFragmentManager());
    }

    /**
     * Method that creates and returns a {@link FetchQuestionsListUseCase} instance
     *
     * @return A {@link FetchQuestionsListUseCase} instance
     */
    public FetchQuestionsListUseCase getFetchQuestionsListUseCase() {
        return mCompositionRoot.getFetchQuestionsListUseCase();
    }

    /**
     * Method that creates and returns a {@link FetchQuestionDetailsUseCase} instance
     *
     * @return A {@link FetchQuestionDetailsUseCase} instance
     */
    public FetchQuestionDetailsUseCase getFetchQuestionDetailsUseCase() {
        return mCompositionRoot.getFetchQuestionDetailsUseCase();
    }

    /**
     * Method that creates and returns a {@link ViewMvcFactory} instance
     * for instantiating MVC Views
     *
     * @return A {@link ViewMvcFactory} instance
     */
    public ViewMvcFactory getViewMvcFactory() {
        return new ViewMvcFactory(getLayoutInflater(), getImageLoader());
    }

    /**
     * Method that obtains the {@link LayoutInflater} from the given context.
     *
     * @return Instance of {@link LayoutInflater}
     */
    private LayoutInflater getLayoutInflater() {
        return LayoutInflater.from(mActivity);
    }

    /**
     * Method that obtains the {@link FragmentManager} for managing the Fragments
     *
     * @return Instance of {@link FragmentManager}
     */
    private FragmentManager getFragmentManager() {
        return mActivity.getSupportFragmentManager();
    }

    /**
     * Getter for the Activity registered
     *
     * @return Instance of {@link AppCompatActivity} registered
     */
    private AppCompatActivity getActivity() {
        return mActivity;
    }

    /**
     * Method that creates and returns an {@link ImageLoader} instance
     *
     * @return An {@link ImageLoader} instance
     */
    private ImageLoader getImageLoader() {
        return new ImageLoader(getActivity());
    }
}