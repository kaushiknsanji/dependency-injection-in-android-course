package com.techyourchance.journeytodependencyinjection.common.dependencyinjection.presentation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Scope;

/**
 * {@link Scope} annotation used for injecting scoped services exposed
 * by {@link com.techyourchance.journeytodependencyinjection.common.dependencyinjection.application.ApplicationComponent}
 * into {@link PresentationComponent}
 */
@Scope
@Retention(RetentionPolicy.RUNTIME)
public @interface PresentationScope {
}
