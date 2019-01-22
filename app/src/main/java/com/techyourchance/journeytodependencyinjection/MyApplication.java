package com.techyourchance.journeytodependencyinjection;

import android.app.Application;

import com.techyourchance.journeytodependencyinjection.common.dependencyinjection.application.ApplicationComponent;
import com.techyourchance.journeytodependencyinjection.common.dependencyinjection.application.ApplicationModule;
import com.techyourchance.journeytodependencyinjection.common.dependencyinjection.application.DaggerApplicationComponent;

/**
 * {@link Application} class for exposing {@link ApplicationComponent}
 */
public class MyApplication extends Application {

    //Dagger Component for exposing instances of services
    private ApplicationComponent mApplicationComponent;

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

        //Initializing the Application Component
        mApplicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule())
                .build();
    }

    /**
     * Method that returns {@link ApplicationComponent} instance
     *
     * @return An {@link ApplicationComponent} instance
     */
    public ApplicationComponent getApplicationComponent() {
        return mApplicationComponent;
    }
}