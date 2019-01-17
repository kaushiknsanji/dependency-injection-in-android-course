package com.techyourchance.journeytodependencyinjection.screens.common.mvcviews;

/**
 * This interface corresponds to MVC views that need to notify MVC controllers of input events
 *
 * @param <L> the class of the listeners
 */
public interface ObservableViewMvc<L> extends ViewMvc {

    /**
     * Register a listener that will be notified of any input events performed on the MVC view.
     *
     * @param listener Listener to be registered for receiving input events.
     */
    void registerListener(L listener);

    /**
     * Unregister a previously registered listener. Does nothing if the listener wasn't registered.
     *
     * @param listener Listener to be unregistered.
     */
    void unregisterListener(L listener);

}
