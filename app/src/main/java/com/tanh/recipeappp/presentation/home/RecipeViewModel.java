package com.tanh.recipeappp.presentation.home;

import android.content.Context;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.tanh.recipeappp.data.database.Recipe;
import com.tanh.recipeappp.data.repository.RecipeRepository;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class RecipeViewModel extends ViewModel {

    private final RecipeRepository recipeRepository;

    public RecipeViewModel(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }

    //Lấy danh sách recipe theo categoryId
    public LiveData<List<Recipe>> getRecipesByCategory(Integer categoryId) {
        return recipeRepository.getRecipesByCategoryId(categoryId);
    }

    //Lấy danh sách recipe
    public LiveData<List<Recipe>> getRecipes() {
        return recipeRepository.getRecipes();
    }

    //Lấy recipe theo id
    public LiveData<Recipe> getRecipeById(Integer id) {
        return recipeRepository.getRecipeById(id);
    }

    //Thêm recipe
    void insertRecipe(Recipe recipe) {
        Executor executor = Executors.newSingleThreadExecutor();
        executor.execute(() -> {
            recipeRepository.insertRecipe(recipe);
        });
    }

    //Dữ liệu ban đầu
    void loadData(Context context) {
        Executor executor = Executors.newSingleThreadExecutor();
        executor.execute(() -> {
            Log.d("recipe", "viewmodel");
            recipeRepository.loadRecipes(context);
        });
    }

    public void updateRecipe(Recipe recipe) {
        Executor executor = Executors.newSingleThreadExecutor();
        executor.execute( () -> {
            recipeRepository.updateRecipe(recipe);
        });
    }

    public RecipeRepository getRecipeRepository() {
        return recipeRepository;
    }
}
