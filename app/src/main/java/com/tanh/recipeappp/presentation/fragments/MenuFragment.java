package com.tanh.recipeappp.presentation.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.tanh.recipeappp.RecipeApplication;
import com.tanh.recipeappp.data.database.Menu;
import com.tanh.recipeappp.databinding.FragmentMenuBinding;
import com.tanh.recipeappp.dependencies.AppContainer;
import com.tanh.recipeappp.factory.MenuViewModelFactory;
import com.tanh.recipeappp.factory.RecipeViewModelFactory;
import com.tanh.recipeappp.presentation.home.MenuViewModel;
import com.tanh.recipeappp.presentation.home.RecipeViewModel;

import java.util.List;

public class MenuFragment extends Fragment {

    private AppContainer appContainer;
    private FragmentMenuBinding binding;
    private RecipeViewModel recipeViewModel;
    private MenuViewModel menuViewModel;

    public MenuFragment(AppContainer appContainer) {
        this.appContainer = appContainer;
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentMenuBinding.inflate(inflater,container, false);

        //init
        init();



        return binding.getRoot();
    }

    private void init() {
        appContainer = ((RecipeApplication) getContext().getApplicationContext()).getAppContainer();
        RecipeViewModelFactory recipeViewModelFactory = new RecipeViewModelFactory(appContainer.getRecipeRepository());
        MenuViewModelFactory menuViewModelFactory = new MenuViewModelFactory(appContainer.getMenuRepository());
        recipeViewModel = new ViewModelProvider(this, recipeViewModelFactory).get(RecipeViewModel.class);
        menuViewModel = new ViewModelProvider(this,  menuViewModelFactory).get(MenuViewModel.class);
    }

}