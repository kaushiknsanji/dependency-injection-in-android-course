package com.techyourchance.journeytodependencyinjection.common.dependencyinjection;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Field Annotation used by {@link Injector} to inject dependencies for the client fields annotated by this.
 */

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface Service {
}
