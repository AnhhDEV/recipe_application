package com.tanh.recipeappp.presentation.recipes;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;

import com.tanh.recipeappp.RecipeApplication;
import com.tanh.recipeappp.data.database.Recipe;
import com.tanh.recipeappp.databinding.ActivityRecipesActivitiyBinding;
import com.tanh.recipeappp.dependencies.AppContainer;
import com.tanh.recipeappp.factory.RecipeViewModelFactory;
import com.tanh.recipeappp.presentation.home.MainActivity;
import com.tanh.recipeappp.presentation.home.RecipeViewModel;
import com.tanh.recipeappp.presentation.adapter.RecipesAdapter;
import com.tanh.recipeappp.util.Category;

import java.util.ArrayList;
import java.util.List;

public class RecipesActivity extends AppCompatActivity {

    ActivityRecipesActivitiyBinding binding;
    List<Recipe> list = new ArrayList<>();
    RecipeViewModel recipeViewModel;
    AppContainer appContainer;
    RecipesAdapter recipeAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setBinding();
        //viewmodel
        appContainer = ((RecipeApplication) getApplication()).getAppContainer();
        RecipeViewModelFactory factory = new RecipeViewModelFactory(appContainer.getRecipeRepository());
        recipeViewModel = new ViewModelProvider(this, factory).get(RecipeViewModel.class);

        //getIntent
        Intent intent = this.getIntent();
        int categoryId = intent.getIntExtra("categoryId", 0);

        //Set data
        String categoryName = Category.categories.get(categoryId);
        binding.tvDishtype.setText(categoryName);
        binding.tvDishtype.setTextSize(18);
        //recycleview
        recipeViewModel.getRecipesByCategory(categoryId).observe(this, recipe -> {
            list = recipe;
            recipeAdapter = new RecipesAdapter(recipeViewModel, list);
            binding.rvDishtype.setAdapter(recipeAdapter);
            GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2);
            binding.rvDishtype.setLayoutManager(gridLayoutManager);
            binding.rvDishtype.setAdapter(recipeAdapter);
        });
        onBack();
    }

    private void onBack() {
        binding.back.setOnClickListener(v -> {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        });
    }

    private void setBinding() {
        binding = ActivityRecipesActivitiyBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }

}