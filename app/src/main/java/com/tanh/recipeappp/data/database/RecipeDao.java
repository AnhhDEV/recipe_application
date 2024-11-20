package com.tanh.recipeappp.data.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

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
}
