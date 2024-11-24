package com.tanh.recipeappp.presentation.fragments;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.tanh.recipeappp.R;
import com.tanh.recipeappp.RecipeApplication;
import com.tanh.recipeappp.data.database.Recipe;
import com.tanh.recipeappp.dependencies.AppContainer;
import com.tanh.recipeappp.factory.RecipeViewModelFactory;
import com.tanh.recipeappp.presentation.adapter.RecipesAdapter;
import com.tanh.recipeappp.presentation.home.RecipeViewModel;

import java.util.ArrayList;
import java.util.List;

public class FavoriteFragment extends Fragment {

    private AppContainer appContainer;
    private RecipesAdapter adapter = null;
    private RecyclerView recyclerView;
    private RecipeViewModel recipeViewModel;

    public FavoriteFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        appContainer = ((RecipeApplication) requireActivity().getApplication()).getAppContainer();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_favorite, container, false);

        //init
        RecipeViewModelFactory factory = new RecipeViewModelFactory(appContainer.getRecipeRepository());
        recipeViewModel = new ViewModelProvider(this, factory).get(RecipeViewModel.class);
        onBack(view);
        return view;
    }

    @SuppressLint("NotifyDataSetChanged")
    public void onBack(View view) {
        recipeViewModel.getFavoriteRecipes().observe(getViewLifecycleOwner(), recipes -> {
            if(recipes != null) {
                if(adapter == null) {
                    Log.d("fragment", String.valueOf(recipes.size()));
                    GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 2);
                    adapter = new RecipesAdapter(recipeViewModel, recipes);
                    recyclerView = view.findViewById(R.id.rv_favorite);
                    recyclerView.setLayoutManager(gridLayoutManager);
                    recyclerView.setAdapter(adapter);
                } else {
                    adapter.changeList(recipes);
                    adapter.notifyDataSetChanged();
                }
            }
        });
    }

}