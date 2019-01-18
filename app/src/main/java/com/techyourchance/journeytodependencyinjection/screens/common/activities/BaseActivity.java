package com.techyourchance.journeytodependencyinjection.screens.common.activities;

import android.annotation.SuppressLint;
import android.support.v7.app.AppCompatActivity;

import com.techyourchance.journeytodependencyinjection.MyApplication;
import com.techyourchance.journeytodependencyinjection.common.dependencyinjection.CompositionRoot;

/**
 * An {@link AppCompatActivity} class which is the base class
 * for all the activities in this application
 */
@SuppressLint("Registered")
public class BaseActivity extends AppCompatActivity {

    /**
     * Method that returns {@link CompositionRoot} instance
     *
     * @return A {@link CompositionRoot} instance
     */
    protected CompositionRoot getCompositionRoot() {
        return ((MyApplication) getApplication()).getCompositionRoot();
    }

}
