package com.techyourchance.journeytodependencyinjection.common.dependencyinjection.service;

import com.techyourchance.journeytodependencyinjection.common.dependencyinjection.application.ApplicationComponent;

import dagger.Subcomponent;

/**
 * Dagger Subcomponent for exposing Dagger provisioned services from the Module {@link ServiceModule}
 * whose parent component is {@link ApplicationComponent}
 */
@Subcomponent(modules = ServiceModule.class)
public interface ServiceComponent {
}
