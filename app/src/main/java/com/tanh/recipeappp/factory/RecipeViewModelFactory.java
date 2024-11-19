package com.tanh.recipeappp.factory;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.tanh.recipeappp.data.repository.RecipeRepository;
import com.tanh.recipeappp.presentation.home.RecipeViewModel;

public class RecipeViewModelFactory implements ViewModelProvider.Factory {

    private final RecipeRepository recipeRepository;

    public RecipeViewModelFactory(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(RecipeViewModel.class)) {
            return (T) new RecipeViewModel(recipeRepository);
        }
        throw new IllegalArgumentException("Unknown ViewModel class");
    }
}
