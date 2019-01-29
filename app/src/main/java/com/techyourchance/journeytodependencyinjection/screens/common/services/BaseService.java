package com.techyourchance.journeytodependencyinjection.screens.common.services;

import android.app.Service;
import android.support.annotation.UiThread;

import com.techyourchance.journeytodependencyinjection.MyApplication;
import com.techyourchance.journeytodependencyinjection.common.dependencyinjection.application.ApplicationComponent;
import com.techyourchance.journeytodependencyinjection.common.dependencyinjection.service.ServiceComponent;
import com.techyourchance.journeytodependencyinjection.common.dependencyinjection.service.ServiceModule;

/**
 * An Android {@link Service} abstract class which is the base class
 * for all the Android Services in this application
 */
public abstract class BaseService extends Service {

    //Tracks if the ServiceComponent is used more than once in an Android Service
    //to inject dagger provisioned services
    private boolean mIsServiceComponentUsed;

    /**
     * Method that creates and returns the {@link ServiceComponent}
     *
     * @return A New instance of {@link ServiceComponent}
     */
    @UiThread
    protected ServiceComponent getServiceComponent() {
        if (mIsServiceComponentUsed) {
            //Throwing exception when invoked more than once in the same Android service
            throw new RuntimeException("No need to use ServiceComponent more than once");
        }
        mIsServiceComponentUsed = true;
        return getApplicationComponent().newServiceComponent(new ServiceModule(this));
    }

    /**
     * Method that returns {@link ApplicationComponent} instance
     *
     * @return An {@link ApplicationComponent} instance
     */
    private ApplicationComponent getApplicationComponent() {
        return ((MyApplication) getApplication()).getApplicationComponent();
    }

}
