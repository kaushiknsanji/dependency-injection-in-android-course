package com.techyourchance.journeytodependencyinjection.common.dependencyinjection.service;

import android.app.Service;
import android.content.Context;

import dagger.Module;
import dagger.Provides;

/**
 * Dagger Module for creating and exposing dagger provisioned services, tied to the Android Service.
 */
@Module
public class ServiceModule {

    //Android Service instance
    private final Service mService;

    /**
     * Constructor of {@link ServiceModule}
     *
     * @param service Instance of an Android {@link Service}
     */
    public ServiceModule(Service service) {
        mService = service;
    }

    /**
     * Getter for the Android {@link Service}
     *
     * @return Instance of Android {@link Service} registered
     */
    @Provides
    Service getService() {
        return mService;
    }

    /**
     * Getter for the {@link Context}
     *
     * @param service Instance of Android {@link Service} provided by Dagger
     * @return Instance of Android Service as the {@link Context}
     */
    @Provides
    Context context(Service service) {
        return service;
    }
}
