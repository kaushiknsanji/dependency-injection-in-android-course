package com.techyourchance.journeytodependencyinjection.screens.questiondetails;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.techyourchance.journeytodependencyinjection.R;
import com.techyourchance.journeytodependencyinjection.screens.common.fragments.BaseFragment;
import com.techyourchance.journeytodependencyinjection.screens.common.viewmodel.ViewModelFactory;

import javax.inject.Inject;

/**
 * A {@link android.support.v4.app.Fragment} that inflates only a TextView,
 * whose Activity is {@link QuestionDetailsActivity}
 */
public class SimpleFragment extends BaseFragment {

    @Inject
    ViewModelFactory mViewModelFactory;

    //For the Activity shared ViewModel Instance
    private QuestionDetailsViewModel mViewModelActivity;
    //For the Fragment ViewModel Instance
    private QuestionDetailsViewModel mViewModelFragment;

    /**
     * Called to do initial creation of a fragment.  This is called after
     * {@link #onAttach(Context)} and before
     * {@link #onCreateView(LayoutInflater, ViewGroup, Bundle)}.
     * <p>
     * <p>Note that this can be called while the fragment's activity is
     * still in the process of being created.  As such, you can not rely
     * on things like the activity's content view hierarchy being initialized
     * at this point.  If you want to do work once the activity itself is
     * created, see {@link #onActivityCreated(Bundle)}.
     * <p>
     * <p>Any restored child fragments will be created before the base
     * <code>Fragment.onCreate</code> method returns.</p>
     *
     * @param savedInstanceState If the fragment is being re-created from
     *                           a previous saved state, this is the state.
     */
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Inject the SimpleFragment's dependencies
        getPresentationComponent().inject(this);

        //Get the Activity shared ViewModel Instance
        mViewModelActivity = ViewModelProviders.of(requireActivity(), mViewModelFactory).get(QuestionDetailsViewModel.class);
        //Get the Fragment ViewModel Instance
        mViewModelFragment = ViewModelProviders.of(this, mViewModelFactory).get(QuestionDetailsViewModel.class);
    }


    /**
     * Called to have the fragment instantiate its user interface view.
     * This is optional, and non-graphical fragments can return null (which
     * is the default implementation).  This will be called between
     * {@link #onCreate(Bundle)} and {@link #onActivityCreated(Bundle)}.
     * <p>
     * <p>If you return a View from here, you will later be called in
     * {@link #onDestroyView} when the view is being released.
     *
     * @param inflater           The LayoutInflater object that can be used to inflate
     *                           any views in the fragment,
     * @param container          If non-null, this is the parent view that the fragment's
     *                           UI should be attached to.  The fragment should not add the view itself,
     *                           but this can be used to generate the LayoutParams of the view.
     * @param savedInstanceState If non-null, this fragment is being re-constructed
     *                           from a previous saved state as given here.
     * @return Return the View for the fragment's UI, or null.
     */
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        TextView textView = new TextView(requireContext());

        if (mViewModelActivity.equals(mViewModelFragment)) {
            textView.setText(R.string.fragment_message_viewmodels_same);
        } else {
            textView.setText(R.string.fragment_message_viewmodels_different);
        }

        return textView;
    }

}