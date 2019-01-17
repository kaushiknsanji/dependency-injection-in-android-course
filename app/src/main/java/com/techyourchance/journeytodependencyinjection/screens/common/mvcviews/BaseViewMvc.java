package com.techyourchance.journeytodependencyinjection.screens.common.mvcviews;

import android.content.Context;
import android.support.annotation.IdRes;
import android.support.annotation.StringRes;
import android.view.View;

import com.techyourchance.journeytodependencyinjection.common.BaseObservable;

/**
 * This is the base class which provides basic common functionality to MVC views implementations.
 *
 * @param <L> the class of the listeners
 */
public class BaseViewMvc<L> extends BaseObservable<L> implements ObservableViewMvc<L> {

    //The Root Android View of the MVC View
    private View mRootView;

    /**
     * Get the Root Android View which is used internally by this MVC view for presenting
     * information to the User.
     * <p>
     * The returned view might be used by an MVC Controller in order to query or alter the
     * properties of either the Root Android View itself or any of its child views.
     * </p>
     *
     * @return The Root Android {@link View} of this MVC View.
     */
    @Override
    public View getRootView() {
        return mRootView;
    }

    /**
     * Set the root android view of this MVC view
     *
     * @param rootView The Root Android View
     */
    protected void setRootView(View rootView) {
        mRootView = rootView;
    }

    /**
     * Convenience method for obtaining references to {@link View}s contained in the hierarchy of
     * the root {@link View}.
     * <p>
     * This method uses Java's type inference in order to automatically cast the returned
     * {@link View}s to the type of the containing variable.
     * </p>
     *
     * @return {@link View} object casted to the type inferred by Java's automatic type inference
     * mechanism, or null
     */
    @SuppressWarnings("unchecked")
    protected <T extends View> T findViewById(@IdRes int id) {
        return (T) mRootView.findViewById(id);
    }

    /**
     * Convenience method for obtaining reference to {@link Context}
     *
     * @return instance of {@link Context} associated with the root {@link View} of this MVC view
     */
    protected Context getContext() {
        return getRootView().getContext();
    }

    /**
     * Convenience method for obtaining a string resource
     */
    protected String getString(@StringRes int id) {
        return getContext().getString(id);
    }

    /**
     * Convenience method for obtaining a string resource with format arguments.
     */
    protected String getString(@StringRes int id, Object... formatArgs) {
        return getContext().getString(id, formatArgs);
    }

}
