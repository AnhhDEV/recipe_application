package com.tanh.recipeappp.presentation.home;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import com.tanh.recipeappp.R;
import com.tanh.recipeappp.RecipeApplication;
import com.tanh.recipeappp.data.database.Recipe;
import com.tanh.recipeappp.databinding.ActivityMainBinding;
import com.tanh.recipeappp.dependencies.AppContainer;
import com.tanh.recipeappp.factory.RecipeViewModelFactory;
import com.tanh.recipeappp.presentation.adapter.RecipesAdapter;
import com.tanh.recipeappp.presentation.fragments.FavoriteFragment;
import com.tanh.recipeappp.presentation.fragments.HomeFragment;
import com.tanh.recipeappp.presentation.fragments.MenuFragment;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    AppContainer appContainer;
    RecipeViewModel recipeViewModel;
    List<Recipe> list = new ArrayList<>();
    RecyclerView recyclerView;
    RecipesAdapter recipesAdapter = null;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //binding
        setBinding();
        //init
        init();

        loadData();

        //set menu
        makeCurrentFragment(new HomeFragment());

        binding.bottomNavigationView.setOnItemSelectedListener((menuItem) -> {
            int itemSelected = menuItem.getItemId();
            if(itemSelected == R.id.dish) {
                makeCurrentFragment(new HomeFragment());
            } else if(itemSelected == R.id.favorite) {
                makeCurrentFragment(new FavoriteFragment());
            } else if(itemSelected == R.id.menu) {
                makeCurrentFragment(new MenuFragment());
            }
            return true;
        });
    }

    private void makeCurrentFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.wrap_fragment, fragment)
                .commit();
    }

    private void loadData() {

        SharedPreferences sharedPreferences = this.getSharedPreferences("login", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        boolean status = sharedPreferences.getBoolean("status", false);
        if(!status) {
            editor.putBoolean("status", true);
            editor.apply();
            recipeViewModel.loadData(this.getApplicationContext());
        }

    }


    public void init() {
        appContainer = ((RecipeApplication) getApplication()).getAppContainer();
        RecipeViewModelFactory factory = new RecipeViewModelFactory(appContainer.getRecipeRepository());
        recipeViewModel = new ViewModelProvider(this, factory).get(RecipeViewModel.class);
    }

    private void setBinding() {
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
    }

}