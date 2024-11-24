package com.tanh.recipeappp.presentation.insert_menu;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.widget.ArrayAdapter;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.tanh.recipeappp.RecipeApplication;
import com.tanh.recipeappp.data.database.Menu;
import com.tanh.recipeappp.data.database.Recipe;
import com.tanh.recipeappp.databinding.ActivityInsertMenuBinding;
import com.tanh.recipeappp.dependencies.AppContainer;
import com.tanh.recipeappp.factory.MenuViewModelFactory;
import com.tanh.recipeappp.factory.RecipeViewModelFactory;
import com.tanh.recipeappp.presentation.adapter.Recipes2Adapter;
import com.tanh.recipeappp.presentation.adapter.RecipesAdapter;
import com.tanh.recipeappp.presentation.home.MenuViewModel;
import com.tanh.recipeappp.presentation.home.RecipeViewModel;
import com.tanh.recipeappp.presentation.model.MeRecipe;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class InsertMenuActivity extends AppCompatActivity {

    private AppContainer appContainer;
    private MenuViewModel menuViewModel;
    private RecipeViewModel recipeViewModel;
    private ActivityInsertMenuBinding binding;

    private ArrayAdapter<String> adapter;

    private Recipes2Adapter recipesAdapter1 = null;
    private Recipes2Adapter recipesAdapter2 = null;
    private Recipes2Adapter recipesAdapter3 = null;


    String date = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setBinding();

        init();

        filter();

        handleDatePicker();

        navToMenuFragment();
    }

    private void navToMenuFragment() {
        binding.back4.setOnClickListener(view -> {
            finish();
        });
    }

    private void handleDatePicker() {

        binding.pickDate.setOnClickListener(v -> {
            Calendar calendar = Calendar.getInstance();
            int year = calendar.get(Calendar.YEAR);
            int month = calendar.get(Calendar.MONTH);
            int day = calendar.get(Calendar.DAY_OF_MONTH);

            DatePickerDialog datePickerDialog = new DatePickerDialog(
                    InsertMenuActivity.this,
                    (view, selectedYear, selectedMonth, selectedDay) -> {
                        date = selectedDay + "/ " + selectedMonth + "/ " + selectedYear;
                        binding.tvDatepicker.setText(date);
                    },
                    year, month, day
            );
            datePickerDialog.show();
        });
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
                String title = adapter.getItem(position);
                Integer recipeId = findId(meRecipes, title);
                if (recipeId != null) {
                    recipeViewModel.getRecipeById(recipeId).observe(this, recipe -> {
                        breakfast.add(recipe);
                        setRecycle(recipesAdapter1, binding.rvBreakfast, breakfast);
                    });
                }
            });
            //auto2
            binding.autoLaunch.setAdapter(adapter);
            binding.autoLaunch.setThreshold(1);
            binding.autoLaunch.setOnItemClickListener((parent, view, position, id) -> {
                String title = adapter.getItem(position);
                Integer recipeId = findId(meRecipes, title);
                if(recipeId != null) {
                    recipeViewModel.getRecipeById(recipeId).observe(this, recipe -> {
                        launch.add(recipe);
                        setRecycle(recipesAdapter2, binding.rvLaunch, launch);
                    });
                }
            });
            //auto3
            binding.autoDinner.setAdapter(adapter);
            binding.autoDinner.setThreshold(1);
            binding.autoDinner.setOnItemClickListener((parent, view, position, id) -> {
                String title = adapter.getItem(position);
                Integer recipeId = findId(meRecipes, title);
                if(recipeId != null) {
                    recipeViewModel.getRecipeById(recipeId).observe(this, recipe -> {
                        dinner.add(recipe);
                        setRecycle(recipesAdapter3, binding.rvDinner, dinner);
                    });
                }
            });

            binding.addMenu.setOnClickListener(view -> {
                Menu newMenu = new Menu(breakfast, launch, dinner, date);
                menuViewModel.insertMenu(newMenu);
                date = null;
                finish();
            });
        });
    }

    private Integer findId(List<MeRecipe> list, String query) {
        Integer id = null;
        for (MeRecipe meRecipe : list) {
            if (meRecipe.getTitle().equals(query)) {
                id = meRecipe.getId();
                break;
            }
        }
        return id;
    }

    private void setRecycle(Recipes2Adapter adapter, RecyclerView recyclerView, List<Recipe> list) {
        if (adapter == null) {
            adapter = new Recipes2Adapter(recipeViewModel, list);
            LinearLayoutManager gridLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
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