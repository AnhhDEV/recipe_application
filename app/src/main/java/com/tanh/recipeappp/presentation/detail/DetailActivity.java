package com.tanh.recipeappp.presentation.detail;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.bumptech.glide.Glide;

import com.tanh.recipeappp.RecipeApplication;
import com.tanh.recipeappp.databinding.ActivityDetailBinding;
import com.tanh.recipeappp.dependencies.AppContainer;
import com.tanh.recipeappp.factory.RecipeViewModelFactory;
import com.tanh.recipeappp.presentation.home.MainActivity;
import com.tanh.recipeappp.presentation.home.RecipeViewModel;

public class DetailActivity extends AppCompatActivity {

    ActivityDetailBinding binding;
    AppContainer appContainer;
    RecipeViewModel recipeViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //init
        binding = ActivityDetailBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        appContainer = ((RecipeApplication) getApplication()).getAppContainer();
        RecipeViewModelFactory factory = new RecipeViewModelFactory(appContainer.getRecipeRepository());
        recipeViewModel = new ViewModelProvider(this, factory).get(RecipeViewModel.class);

        //intent
        Intent intent = getIntent();
        Integer recipeId = intent.getIntExtra("recipeId", 0);
        //setdata

        recipeViewModel.getRecipeById(recipeId).observe(this, recipe -> {
            binding.tvIngredients.setText(recipe.getIngredients());
            binding.tvInstruction.setText(recipe.getInstruction());
            StringBuilder str = new StringBuilder();
            str.append("Cách làm ");
            str.append(recipe.getTitle());
            binding.tvDish.setText(str.toString());
            Glide.with(this)
                    .load(recipe.getImageUrl())
                    .into(binding.ivDetail);

            binding.tvDish.setTextSize(18);
            binding.tvIngredients.setTextSize(15);
            binding.tvInstruction.setTextSize(15);
        });

        //back
        binding.back1.setOnClickListener(view -> {
            Intent intent1 = new Intent(this, MainActivity.class);
            startActivity(intent1);
        });
    }


}