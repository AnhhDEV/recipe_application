package com.tanh.recipeappp.presentation.insert_menu;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.SearchView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.tanh.recipeappp.R;
import com.tanh.recipeappp.RecipeApplication;
import com.tanh.recipeappp.data.database.Menu;
import com.tanh.recipeappp.data.database.Recipe;
import com.tanh.recipeappp.databinding.ActivityInsertMenuBinding;
import com.tanh.recipeappp.dependencies.AppContainer;
import com.tanh.recipeappp.factory.MenuViewModelFactory;
import com.tanh.recipeappp.factory.RecipeViewModelFactory;
import com.tanh.recipeappp.presentation.adapter.MenuAdapter;
import com.tanh.recipeappp.presentation.adapter.RecipesAdapter;
import com.tanh.recipeappp.presentation.home.MenuViewModel;
import com.tanh.recipeappp.presentation.home.RecipeViewModel;
import com.tanh.recipeappp.presentation.model.MeRecipe;

import java.util.ArrayList;
import java.util.List;

public class InsertMenuActivity extends AppCompatActivity {

    private AppContainer appContainer;
    private MenuViewModel menuViewModel;
    private RecipeViewModel recipeViewModel;
    private ActivityInsertMenuBinding binding;

    private ArrayAdapter<String> adapter;

    private RecipesAdapter recipesAdapter1 = null;
    private RecipesAdapter recipesAdapter2 = null;
    private RecipesAdapter recipesAdapter3 = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setBinding();

        init();

        filter();

    }

    private void filter() {
        recipeViewModel.getTitles().observe(this, meRecipes -> {

            adapter = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, getList(meRecipes));

            List<Recipe> breakfast = new ArrayList<>();
            List<Recipe> launch = new ArrayList<>();
            List<Recipe> dinner = new ArrayList<>();
            //auto1
            binding.autoBreakfast.setAdapter(adapter);
            binding.autoBreakfast.setThreshold(1);
            binding.autoBreakfast.setOnItemClickListener((parent, view, position, id) -> {
                Integer recipeId = meRecipes.get(position).getId();
                recipeViewModel.getRecipeById(recipeId).observe(this, recipe -> {
                    breakfast.add(recipe);
                    setRecycle(recipesAdapter1, binding.rvBreakfast, breakfast);
                });
            });
            //auto2
            binding.autoLaunch.setAdapter(adapter);
            binding.autoLaunch.setThreshold(1);
            binding.autoLaunch.setOnItemClickListener((parent, view, position, id) -> {
                Integer recipeId = meRecipes.get(position).getId();
                recipeViewModel.getRecipeById(recipeId).observe(this, recipe -> {
                    launch.add(recipe);
                    setRecycle(recipesAdapter2, binding.rvLaunch, launch);
                });

            });
            //auto3
            binding.autoDinner.setAdapter(adapter);
            binding.autoDinner.setThreshold(1);
            binding.autoDinner.setOnItemClickListener((parent, view, position, id) -> {
                Integer recipeId = meRecipes.get(position).getId();
                recipeViewModel.getRecipeById(recipeId).observe(this, recipe -> {
                    dinner.add(recipe);
                    setRecycle(recipesAdapter3, binding.rvDinner, dinner);
                });
            });

            binding.addMenu.setOnClickListener(view -> {
                Menu newMenu = new Menu(breakfast, launch, dinner, "29/10/2004");
                menuViewModel.insertMenu(newMenu);
                finish();
            });
        });
    }

    private void setRecycle(RecipesAdapter adapter, RecyclerView recyclerView, List<Recipe> list) {
        if (adapter == null) {
            adapter = new RecipesAdapter(recipeViewModel, list);
            GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2);
            recyclerView.setLayoutManager(gridLayoutManager);
            recyclerView.setAdapter(adapter);
        } else {
            adapter.changeList(list);
        }
    }

    private List<String> getList(List<MeRecipe> list) {
        List<String> list1 = new ArrayList<>();
        for (MeRecipe meRecipe : list) {
            list1.add(meRecipe.getTitle());
        }
        return list1;
    }

    private void init() {
        appContainer = ((RecipeApplication) getApplication()).getAppContainer();
        RecipeViewModelFactory recipeViewModelFactory = new RecipeViewModelFactory(appContainer.getRecipeRepository());
        MenuViewModelFactory menuViewModelFactory = new MenuViewModelFactory(appContainer.getMenuRepository());
        recipeViewModel = new ViewModelProvider(this, recipeViewModelFactory).get(RecipeViewModel.class);
        menuViewModel = new ViewModelProvider(this, menuViewModelFactory).get(MenuViewModel.class);
    }

    private void setBinding() {
        binding = ActivityInsertMenuBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }

}