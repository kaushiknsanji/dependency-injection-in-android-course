package com.techyourchance.journeytodependencyinjection.common.dependencyinjection;

import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;

import com.techyourchance.journeytodependencyinjection.questions.FetchQuestionDetailsUseCase;
import com.techyourchance.journeytodependencyinjection.questions.FetchQuestionsListUseCase;
import com.techyourchance.journeytodependencyinjection.screens.common.dialogs.DialogsManager;
import com.techyourchance.journeytodependencyinjection.screens.common.mvcviews.ViewMvcFactory;

/**
 * Composition Class for creating and exposing services, tied to the Activity/Fragment Lifecycle.
 */
public class PresentationCompositionRoot {

    //CompositionRoot instance tied to the Application Lifecycle
    private final CompositionRoot mCompositionRoot;

    //FragmentManager instance to manage the Fragments
    private final FragmentManager mFragmentManager;

    //LayoutInflater instance to inflate views
    private final LayoutInflater mLayoutInflater;

    /**
     * Constructor of {@link PresentationCompositionRoot}
     * @param compositionRoot Instance of App {@link CompositionRoot}
     * @param fragmentManager Instance of {@link FragmentManager} to manage Fragments
     * @param layoutInflater Instance of {@link LayoutInflater} for inflating the views
     */
    public PresentationCompositionRoot(CompositionRoot compositionRoot, FragmentManager fragmentManager, LayoutInflater layoutInflater) {
        mCompositionRoot = compositionRoot;
        mFragmentManager = fragmentManager;
        mLayoutInflater = layoutInflater;
    }

    /**
     * Method that creates and returns a {@link DialogsManager} instance
     *
     * @return A {@link DialogsManager} instance to manage Dialogs
     */
    public DialogsManager getDialogsManager() {
        return new DialogsManager(mFragmentManager);
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
        return new ViewMvcFactory(mLayoutInflater);
    }
}