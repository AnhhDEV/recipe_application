package com.tanh.recipeappp;

import android.app.Application;

import com.tanh.recipeappp.dependencies.AppContainer;

public class RecipeApplication extends Application {

    private AppContainer appContainer;

    @Override
    public void onCreate() {
        super.onCreate();
        appContainer = new AppContainer(this);
    }

    public AppContainer getAppContainer() {
        return appContainer;
    }
}
