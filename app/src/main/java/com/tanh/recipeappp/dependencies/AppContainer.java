package com.tanh.recipeappp.dependencies;

import android.app.Application;
import android.content.Context;

import androidx.room.Room;

import com.tanh.recipeappp.RecipeApplication;
import com.tanh.recipeappp.data.database.MDatabase;
import com.tanh.recipeappp.data.repository.RecipeRepository;

public class AppContainer {

    private final Application application;
    private MDatabase mDatabase;
    private RecipeRepository recipeRepository;

    public AppContainer(Application application) {
        this.application = application;
        setupDependencies();
    }

    private void setupDependencies() {
        mDatabase = Room.databaseBuilder(application, MDatabase.class, "recipes").build();
        recipeRepository = new RecipeRepository(mDatabase);
    }

    public MDatabase getDatabase() {
        return mDatabase;
    }

    public RecipeRepository getRecipeRepository() {
        return recipeRepository;
    }

}
