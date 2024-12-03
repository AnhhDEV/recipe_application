package com.tanh.recipeappp.presentation.search;

import android.annotation.SuppressLint;
import android.graphics.Rect;
import android.os.Bundle;
import android.util.Log;
import android.widget.SearchView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;

import com.tanh.recipeappp.RecipeApplication;
import com.tanh.recipeappp.databinding.ActivitySearchBinding;
import com.tanh.recipeappp.dependencies.AppContainer;
import com.tanh.recipeappp.factory.RecipeViewModelFactory;
import com.tanh.recipeappp.presentation.adapter.RecipesAdapter;
import com.tanh.recipeappp.presentation.home.RecipeViewModel;

public class SearchActivity extends AppCompatActivity {

    private AppContainer appContainer;
    private ActivitySearchBinding binding;
    private RecipesAdapter adapter = null;
    private RecipeViewModel recipeViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding = ActivitySearchBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        ViewCompat.setOnApplyWindowInsetsListener(binding.getRoot(), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        appContainer = ((RecipeApplication) getApplication()).getAppContainer();
        RecipeViewModelFactory factory = new RecipeViewModelFactory(appContainer.getRecipeRepository());
        recipeViewModel = new ViewModelProvider(this, factory).get(RecipeViewModel.class);

        seOnClick();
        setRecyclerView();
        searchFood();
        setUpSearchView();
    }

    private void setUpSearchView() {
        binding.getRoot().getViewTreeObserver().addOnGlobalLayoutListener(() -> {
            Rect r = new Rect();
            binding.getRoot().getWindowVisibleDisplayFrame(r);
            int screenHeight = binding.getRoot().getRootView().getHeight();
            int keypadHeight = screenHeight - r.bottom;

            if (keypadHeight > screenHeight * 0.15) {
                binding.rcv.setPadding(0, 0, 0, keypadHeight);
            } else {
                binding.rcv.setPadding(0, 0, 0, 0);
            }
        });
    }

    private void seOnClick() {
        binding.btnBack.setOnClickListener(v -> getOnBackPressedDispatcher().onBackPressed());
    }

    @SuppressLint("NotifyDataSetChanged")
    private void setRecyclerView() {
        recipeViewModel.getRecipes().observe(this, recipes -> {
            if(adapter == null) {
                Log.d("fragment1", "null" + (recipes.size()));
                GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2);
                adapter = new RecipesAdapter(recipeViewModel, recipes);
                binding.rcv.setLayoutManager(gridLayoutManager);
                binding.rcv.setAdapter(adapter);
            } else {
                Log.d("fragment1", "notnull" + (recipes.size()));
                adapter.changeList(recipes);
                adapter.notifyDataSetChanged();
            }
        });
    }

    private void searchFood() {
        binding.searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                adapter.getFilter().filter(query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                adapter.getFilter().filter(newText);
                return true;
            }
        });
    }
}