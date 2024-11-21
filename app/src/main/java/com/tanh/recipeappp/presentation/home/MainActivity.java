package com.tanh.recipeappp.presentation.home;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.tanh.recipeappp.R;
import com.tanh.recipeappp.RecipeApplication;
import com.tanh.recipeappp.data.database.Recipe;
import com.tanh.recipeappp.databinding.ActivityMainBinding;
import com.tanh.recipeappp.dependencies.AppContainer;
import com.tanh.recipeappp.factory.RecipeViewModelFactory;
import com.tanh.recipeappp.presentation.adapter.RecipesAdapter;
import com.tanh.recipeappp.presentation.insert_recipe.InsertActivity;
import com.tanh.recipeappp.presentation.recipes.RecipesActivity;

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

        //data
        loadData();
        recipeViewModel.getRecipes().observe(this, recipe -> {
            list = recipe;
            recyclerView = findViewById(R.id.rv_home);
            if(recipesAdapter == null) {
                recipesAdapter = new RecipesAdapter(recipeViewModel, list);
                recyclerView.setAdapter(recipesAdapter);
                GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2);
                recyclerView.setLayoutManager(gridLayoutManager);
                recyclerView.setAdapter(recipesAdapter);
            }
            recipesAdapter.changeList(recipe);
        });

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
    }

    private void navToInsert() {
        binding.mainAdd.setOnClickListener(view -> {
            Intent intent = new Intent(this, InsertActivity.class);
            startActivity(intent);
        });
    }

    private void loadData() {

        sharedPreferences = getSharedPreferences("login", MODE_PRIVATE);
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

    //Món xào
    public void stirfried() {
        binding.ibXao.setOnClickListener(view -> {
            Intent intent = new Intent(this, RecipesActivity.class);
            intent.putExtra("categoryId", 0);
            startActivity(intent);
        });
    }

    //Món lẩu
    public void lau() {
        binding.ibLau.setOnClickListener(view -> {
            Intent intent = new Intent(this, RecipesActivity.class);
            intent.putExtra("categoryId", 1);
            startActivity(intent);
        });
    }

    //Món kho
    public void kho() {
        binding.ibKho.setOnClickListener(view -> {
            Intent intent = new Intent(this, RecipesActivity.class);
            intent.putExtra("categoryId", 2);
            startActivity(intent);
        });
    }

    //Món chiên
    public void fried() {
        binding.ibChien.setOnClickListener(view -> {
            Intent intent = new Intent(this, RecipesActivity.class);
            intent.putExtra("categoryId", 3);
            startActivity(intent);
        });
    }

    //Món canh
    public void soup() {
        binding.ibCanh.setOnClickListener(view -> {
            Intent intent = new Intent(this, RecipesActivity.class);
            intent.putExtra("categoryId", 4);
            startActivity(intent);
        });
    }

    //Món cháo
    public void congee() {
        binding.ibChao.setOnClickListener(view -> {
            Intent intent = new Intent(this, RecipesActivity.class);
            intent.putExtra("categoryId", 5);
            startActivity(intent);
        });
    }

    //Món xôi
    public void xoi() {
        binding.ibXoi.setOnClickListener(view -> {
            Intent intent = new Intent(this, RecipesActivity.class);
            intent.putExtra("categoryId", 6);
            startActivity(intent);
        });
    }

    //Món nướng
    public void grilled() {
        binding.ibNuong.setOnClickListener(view -> {
            Intent intent = new Intent(this, RecipesActivity.class);
            intent.putExtra("categoryId", 7);
            startActivity(intent);
        });
    }

    //Món hấp
    public void hap() {
        binding.ibHap.setOnClickListener(view -> {
            Intent intent = new Intent(this, RecipesActivity.class);
            intent.putExtra("categoryId", 8);
            startActivity(intent);
        });
    }

    //Món gỏi
    public void goi() {
        binding.ibGoi.setOnClickListener(view -> {
            Intent intent = new Intent(this, RecipesActivity.class);
            intent.putExtra("categoryId", 9);
            startActivity(intent);
        });
    }

    //Đồ uống
    //Món xôi
    public void drink() {
        binding.ibDrink.setOnClickListener(view -> {
            Intent intent = new Intent(this, RecipesActivity.class);
            intent.putExtra("categoryId", 10);
            startActivity(intent);
        });
    }

    //Khác
    public void other() {
        binding.ibKhac.setOnClickListener(view -> {
            Intent intent = new Intent(this, RecipesActivity.class);
            intent.putExtra("categoryId", 11);
            startActivity(intent);
        });
    }

    private void setBinding() {
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
    }

}