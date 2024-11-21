package com.tanh.recipeappp.data.repository;

import android.content.Context;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.tanh.recipeappp.data.database.MDatabase;
import com.tanh.recipeappp.data.database.Recipe;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

public class RecipeRepository {

    private final MDatabase mDatabase;

    public RecipeRepository(MDatabase mDatabase) {
        this.mDatabase = mDatabase;
    }

    public void loadRecipes(Context context) {
        Log.d("recipe", "repostiory");
        try {
            InputStream inputStream = context.getAssets().open("recipes.json");
            String json = new BufferedReader(new InputStreamReader(inputStream))
                    .lines()
                    .collect(Collectors
                            .joining("\n"));
            Type listType = new TypeToken<List<Recipe>>() {}.getType();
            List<Recipe> list = new Gson().fromJson(json, listType);
            for(Recipe recipe : list) {
                insertRecipe(recipe);
            }
        } catch (IOException e) {
            Log.d("recipe", "error");
        }
    }

    public LiveData<List<Recipe>> getFavoriteRecipes() {
        return mDatabase.dao().getFavoriteRecipes();
    }

    public LiveData<Integer> getMaxId() {
        return mDatabase.dao().getMaxRecipeId();
    }

    public void updateRecipe(Recipe recipe) {
        mDatabase.dao().updateRecipe(recipe);
    }

    public LiveData<List<Recipe>> getRecipesByCategoryId(Integer categoryId) {
        return mDatabase.dao().getRecipesByCategory(categoryId);
    }

    public LiveData<List<Recipe>> getRecipes() {
        return mDatabase.dao().getRecipes();
    }

    public LiveData<Recipe> getRecipeById(Integer id) {
        return mDatabase.dao().getRecipeById(id);
    }

    public void insertRecipe(Recipe recipe) {
        mDatabase.dao().insertRecipe(recipe);
    }

}
