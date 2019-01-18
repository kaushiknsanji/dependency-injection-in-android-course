package com.techyourchance.journeytodependencyinjection;

import android.app.Application;

import com.techyourchance.journeytodependencyinjection.common.dependencyinjection.CompositionRoot;

/**
 * {@link Application} class for exposing {@link CompositionRoot}
 */
public class MyApplication extends Application {

    //CompositionRoot for exposing instances of services
    private CompositionRoot mCompositionRoot;

    /**
     * Called when the application is starting, before any activity, service,
     * or receiver objects (excluding content providers) have been created.
     * Implementations should be as quick as possible (for example using
     * lazy initialization of state) since the time spent in this function
     * directly impacts the performance of starting the first activity,
     * service, or receiver in a process.
     * If you override this method, be sure to call super.onCreate().
     */
    @Override
    public void onCreate() {
        super.onCreate();

        //Initializing CompositionRoot
        mCompositionRoot = new CompositionRoot();
    }

    /**
     * Method that returns {@link CompositionRoot} instance
     *
     * @return A {@link CompositionRoot} instance
     */
    public CompositionRoot getCompositionRoot() {
        return mCompositionRoot;
    }
}