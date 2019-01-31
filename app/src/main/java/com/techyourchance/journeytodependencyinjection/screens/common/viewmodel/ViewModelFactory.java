package com.techyourchance.journeytodependencyinjection.screens.common.viewmodel;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;

import java.util.Map;

import javax.inject.Provider;

/**
 * Factory class for instantiating {@link ViewModel}
 */
public class ViewModelFactory implements ViewModelProvider.Factory {

    private final Map<Class<? extends ViewModel>, Provider<ViewModel>> mProviderMap;

    /**
     * Constructor of {@link ViewModelFactory}
     *
     * @param providerMap Dagger generated Provider Map of the form {@code Map<K, Provider<V>>}
     *                    where <K> is a ViewModel Class and <V> is the ViewModel instance.
     */
    public ViewModelFactory(Map<Class<? extends ViewModel>, Provider<ViewModel>> providerMap) {
        mProviderMap = providerMap;
    }

    /**
     * Creates a new instance of the given {@code Class}.
     *
     * @param modelClass a {@code Class} whose instance is requested
     * @return a newly created ViewModel
     */
    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        //noinspection unchecked
        return (T) mProviderMap.get(modelClass).get();
    }
}
