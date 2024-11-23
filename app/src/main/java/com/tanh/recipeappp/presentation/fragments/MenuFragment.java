package com.tanh.recipeappp.presentation.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.tanh.recipeappp.databinding.FragmentMenuBinding;
import com.tanh.recipeappp.dependencies.AppContainer;
import com.tanh.recipeappp.factory.MenuViewModelFactory;
import com.tanh.recipeappp.factory.RecipeViewModelFactory;
import com.tanh.recipeappp.presentation.adapter.MenuAdapter;
import com.tanh.recipeappp.presentation.home.MenuViewModel;
import com.tanh.recipeappp.presentation.home.RecipeViewModel;
import com.tanh.recipeappp.presentation.insert_menu.InsertMenuActivity;

public class MenuFragment extends Fragment {

    private final AppContainer appContainer;
    private FragmentMenuBinding binding;
    private RecipeViewModel recipeViewModel;
    private MenuViewModel menuViewModel;
    private MenuAdapter adapter = null;

    public MenuFragment(AppContainer appContainer) {
        this.appContainer = appContainer;
    }

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentMenuBinding.inflate(inflater,container, false);

        //init
        init();

        menuViewModel.getMenus().observe(getViewLifecycleOwner(), menus -> {
            if (menus != null) {
                if (adapter == null) {
                    adapter = new MenuAdapter(menus, menuViewModel);
                    LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
                    binding.rvMenu.setLayoutManager(linearLayoutManager);
                    binding.rvMenu.setAdapter(adapter);
                } else {
                    adapter.changeList(menus);
                }
            }
        });

        //nav
        onNavToAddMenu();

        return binding.getRoot();
    }

    private void onNavToAddMenu() {
        binding.fbAdd.setOnClickListener(v -> {
            Intent intent = new Intent(getContext(), InsertMenuActivity.class);
            startActivity(intent);
        });
    }

    private void init() {
        RecipeViewModelFactory recipeViewModelFactory = new RecipeViewModelFactory(appContainer.getRecipeRepository());
        MenuViewModelFactory menuViewModelFactory = new MenuViewModelFactory(appContainer.getMenuRepository());
        recipeViewModel = new ViewModelProvider(this, recipeViewModelFactory).get(RecipeViewModel.class);
        menuViewModel = new ViewModelProvider(this,  menuViewModelFactory).get(MenuViewModel.class);
    }

}