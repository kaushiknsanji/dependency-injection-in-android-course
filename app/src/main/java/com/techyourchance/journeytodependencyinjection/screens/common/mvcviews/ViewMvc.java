package com.techyourchance.journeytodependencyinjection.screens.common.mvcviews;

import android.view.View;

/**
 * MVC View Interface used for presenting information to the user.
 */
public interface ViewMvc {

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
    View getRootView();
}
