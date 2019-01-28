package com.techyourchance.journeytodependencyinjection.common.dependencyinjection.application;

import com.techyourchance.journeytodependencyinjection.common.dependencyinjection.presentation.PresentationComponent;
import com.techyourchance.journeytodependencyinjection.common.dependencyinjection.presentation.PresentationModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Dagger Component for exposing services from the Module {@link ApplicationModule}
 */
@Singleton
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {
    /**
     * Method that returns the Subcomponent {@link PresentationComponent}
     *
     * @param presentationModule Instance of {@link PresentationModule} which is the Module of {@link PresentationComponent}
     * @return Instance of Subcomponent {@link PresentationComponent}
     */
    PresentationComponent newPresentationComponent(PresentationModule presentationModule);
}
