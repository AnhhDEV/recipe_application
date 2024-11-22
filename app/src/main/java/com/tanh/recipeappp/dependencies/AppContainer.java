package com.tanh.recipeappp.dependencies;

import android.app.Application;
import android.content.Context;

import androidx.room.Room;

import com.tanh.recipeappp.converter.Converter;
import com.tanh.recipeappp.data.database.MDatabase;
import com.tanh.recipeappp.data.repository.MenuRepository;
import com.tanh.recipeappp.data.repository.RecipeRepository;

public class AppContainer {

    private final Application application;
    private MDatabase mDatabase;
    private RecipeRepository recipeRepository;
    private MenuRepository menuRepository;

    public AppContainer(Application application) {
        this.application = application;
        setupDependencies();
    }

    private void setupDependencies() {
        mDatabase = Room.databaseBuilder(application, MDatabase.class, "recipes")
                .addMigrations(MDatabase.MIGRATION_1_2)
                .addTypeConverter(new Converter())
                .build();
        recipeRepository = new RecipeRepository(mDatabase);
        menuRepository = new MenuRepository(mDatabase);
    }

    public MDatabase getDatabase() {
        return mDatabase;
    }

    public RecipeRepository getRecipeRepository() {
        return recipeRepository;
    }

    public MenuRepository getMenuRepository() {
        return menuRepository;
    }
}
