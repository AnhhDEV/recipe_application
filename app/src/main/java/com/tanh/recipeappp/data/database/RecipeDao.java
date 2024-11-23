package com.tanh.recipeappp.data.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.tanh.recipeappp.presentation.model.MeRecipe;

import java.util.List;

@Dao
public interface RecipeDao {

    @Query("SELECT * FROM recipe")
    LiveData<List<Recipe>> getRecipes();

    @Query("SELECT * FROM recipe WHERE id=:id")
    LiveData<Recipe> getRecipeById(Integer id);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertRecipe(Recipe recipe);

    @Query("SELECT * FROM recipe WHERE category=:categoryId")
    LiveData<List<Recipe>> getRecipesByCategory(Integer categoryId);

    @Update
    void updateRecipe(Recipe recipe);

    @Query("SELECT MAX(id) FROM recipe")
    LiveData<Integer> getMaxRecipeId();

    @Query("SELECT * FROM recipe WHERE isFavorite=1")
    LiveData<List<Recipe>> getFavoriteRecipes();

    @Query("SELECT id,title from recipe")
    LiveData<List<MeRecipe>> getTitles();



}
