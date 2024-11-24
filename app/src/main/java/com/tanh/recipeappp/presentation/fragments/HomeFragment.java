package com.tanh.recipeappp.presentation.fragments;

import static android.content.Context.MODE_PRIVATE;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tanh.recipeappp.R;
import com.tanh.recipeappp.RecipeApplication;
import com.tanh.recipeappp.databinding.FragmentHomeBinding;
import com.tanh.recipeappp.dependencies.AppContainer;
import com.tanh.recipeappp.factory.RecipeViewModelFactory;
import com.tanh.recipeappp.presentation.adapter.RecipesAdapter;
import com.tanh.recipeappp.presentation.home.RecipeViewModel;
import com.tanh.recipeappp.presentation.insert_recipe.InsertActivity;
import com.tanh.recipeappp.presentation.recipes.RecipesActivity;

public class HomeFragment extends Fragment {

    private AppContainer appContainer;
    private RecipesAdapter adapter = null;
    private RecyclerView recyclerView;
    private RecipeViewModel recipeViewModel;
    private FragmentHomeBinding binding;

    public HomeFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        appContainer = ((RecipeApplication) requireActivity().getApplication()).getAppContainer();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentHomeBinding.inflate(inflater, container, false);

        //init
        if(getArguments() != null) {
            appContainer = (AppContainer) getArguments().getSerializable("appContainer");
        }
        RecipeViewModelFactory factory = new RecipeViewModelFactory(appContainer.getRecipeRepository());
        recipeViewModel = new ViewModelProvider(this, factory).get(RecipeViewModel.class);

        setRecyclerView();
        //button
        stirfried();
        lau();
        kho();
        fried();
        soup();
        congee();
        xoi();
        grilled();
        hap();
        goi();
        drink();
        other();
        //nav
        navToInsert();

        return binding.getRoot();
    }

    private void navToInsert() {
        binding.mainAdd.setOnClickListener(view -> {
            Intent intent = new Intent(getContext(), InsertActivity.class);
            startActivity(intent);
        });
    }

    @SuppressLint("NotifyDataSetChanged")
    private void setRecyclerView() {
        recipeViewModel.getRecipes().observe(getViewLifecycleOwner(), recipes -> {
            if(adapter == null) {
                Log.d("fragment1", "null" + (recipes.size()));
                GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 2);
                adapter = new RecipesAdapter(recipeViewModel, recipes);
                recyclerView = binding.rvHome;
                recyclerView.setLayoutManager(gridLayoutManager);
                recyclerView.setAdapter(adapter);
            } else {
                Log.d("fragment1", "notnull" + (recipes.size()));
                adapter.changeList(recipes);
                adapter.notifyDataSetChanged();
            }
        });
    }




    public void stirfried() {
        binding.ibXao.setOnClickListener(view -> {
            Intent intent = new Intent(getContext(), RecipesActivity.class);
            intent.putExtra("categoryId", 0);
            startActivity(intent);
        });
    }

    //Món lẩu
    public void lau() {
        binding.ibLau.setOnClickListener(view -> {
            Intent intent = new Intent(getContext(), RecipesActivity.class);
            intent.putExtra("categoryId", 1);
            startActivity(intent);
        });
    }

    //Món kho
    public void kho() {
        binding.ibKho.setOnClickListener(view -> {
            Intent intent = new Intent(getContext(), RecipesActivity.class);
            intent.putExtra("categoryId", 2);
            startActivity(intent);
        });
    }

    //Món chiên
    public void fried() {
        binding.ibChien.setOnClickListener(view -> {
            Intent intent = new Intent(getContext(), RecipesActivity.class);
            intent.putExtra("categoryId", 3);
            startActivity(intent);
        });
    }

    //Món canh
    public void soup() {
        binding.ibCanh.setOnClickListener(view -> {
            Intent intent = new Intent(getContext(), RecipesActivity.class);
            intent.putExtra("categoryId", 4);
            startActivity(intent);
        });
    }

    //Món cháo
    public void congee() {
        binding.ibChao.setOnClickListener(view -> {
            Intent intent = new Intent(getContext(), RecipesActivity.class);
            intent.putExtra("categoryId", 5);
            startActivity(intent);
        });
    }

    //Món xôi
    public void xoi() {
        binding.ibXoi.setOnClickListener(view -> {
            Intent intent = new Intent(getContext(), RecipesActivity.class);
            intent.putExtra("categoryId", 6);
            startActivity(intent);
        });
    }

    //Món nướng
    public void grilled() {
        binding.ibNuong.setOnClickListener(view -> {
            Intent intent = new Intent(getContext(), RecipesActivity.class);
            intent.putExtra("categoryId", 7);
            startActivity(intent);
        });
    }

    //Món hấp
    public void hap() {
        binding.ibHap.setOnClickListener(view -> {
            Intent intent = new Intent(getContext(), RecipesActivity.class);
            intent.putExtra("categoryId", 8);
            startActivity(intent);
        });
    }

    //Món gỏi
    public void goi() {
        binding.ibGoi.setOnClickListener(view -> {
            Intent intent = new Intent(getContext(), RecipesActivity.class);
            intent.putExtra("categoryId", 9);
            startActivity(intent);
        });
    }

    //Đồ uống
    //Món xôi
    public void drink() {
        binding.ibDrink.setOnClickListener(view -> {
            Intent intent = new Intent(getContext(), RecipesActivity.class);
            intent.putExtra("categoryId", 10);
            startActivity(intent);
        });
    }

    //Khác
    public void other() {
        binding.ibKhac.setOnClickListener(view -> {
            Intent intent = new Intent(getContext(), RecipesActivity.class);
            intent.putExtra("categoryId", 11);
            startActivity(intent);
        });
    }
}