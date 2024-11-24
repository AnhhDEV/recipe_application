package com.tanh.recipeappp.presentation.detail_menu;

import static com.tanh.recipeappp.databinding.ActivityDetailMenuBinding.bind;
import static com.tanh.recipeappp.databinding.ActivityDetailMenuBinding.inflate;

import android.animation.LayoutTransition;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.tanh.recipeappp.R;
import com.tanh.recipeappp.RecipeApplication;
import com.tanh.recipeappp.data.database.Recipe;
import com.tanh.recipeappp.databinding.ActivityDetailMenuBinding;
import com.tanh.recipeappp.dependencies.AppContainer;
import com.tanh.recipeappp.factory.MenuViewModelFactory;
import com.tanh.recipeappp.factory.RecipeViewModelFactory;
import com.tanh.recipeappp.presentation.adapter.Recipes2Adapter;
import com.tanh.recipeappp.presentation.home.MenuViewModel;
import com.tanh.recipeappp.presentation.home.RecipeViewModel;

import java.util.List;

public class DetailMenuActivity extends AppCompatActivity {

    private AppContainer appContainer;
    private MenuViewModel menuViewModel;
    private RecipeViewModel recipeViewModel;
    private ActivityDetailMenuBinding binding;

    private Recipes2Adapter adapter1 = null;
    private Recipes2Adapter adapter2 = null;
    private Recipes2Adapter adapter3 = null;

    private LinearLayout layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        init();

        //getIntent
        Intent intent = this.getIntent();
        Integer menuId = intent.getIntExtra("menuId", 0);

        layout = this.findViewById(R.id.my_layout);
            Log.d("layout1", layout.toString());

        menuViewModel.getMenuById(menuId).observe(this, menu -> {

            List<Recipe> breakfast = menu.getBreakfast();
            List<Recipe> launch = menu.getLunch();
            List<Recipe> dinner = menu.getDinner();

            Log.d("breakfast", breakfast.size() + "");
            Log.d("launch", launch.size() + "");
            Log.d("dinner", dinner.size() + "");

            setRecycle(adapter1, binding.rvShowBreakfast, breakfast);
            setRecycle(adapter2, binding.rvShowLaunch, launch);
            setRecycle(adapter3, binding.rvShowDinner, dinner);

            expandCardView(binding.expandable1, binding.rvShowBreakfast, binding.up1);
            expandCardView(binding.expandable2, binding.rvShowLaunch, binding.up2);
            expandCardView(binding.expandable3, binding.rvShowDinner, binding.up3);

        });

        navToMenu();

    }

    void navToMenu() {
        binding.back5.setOnClickListener(view -> {
           finish();
        });
    }

    void expandCardView(CardView cardView, RecyclerView recyclerView, ImageView imageView) {
        cardView.setOnClickListener(view -> {
            int currentStatus = recyclerView.getVisibility();
            if(currentStatus == View.GONE) {
                recyclerView.setVisibility(View.VISIBLE);
                imageView.setImageResource(R.drawable.up);
            } else {
                recyclerView.setVisibility(View.GONE);
                imageView.setImageResource(R.drawable.down);
            }
        });
    }

    void setRecycle(Recipes2Adapter adapter, RecyclerView recyclerView, List<Recipe> list) {
        if(adapter == null) {
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false);
            adapter = new Recipes2Adapter(recipeViewModel, list);
            recyclerView.setAdapter(adapter);
            recyclerView.setLayoutManager(linearLayoutManager);
        } else {
            adapter.changeList(list);
        }
    }

    void init() {
        appContainer = ((RecipeApplication) getApplication()).getAppContainer();
        RecipeViewModelFactory recipeViewModelFactory = new RecipeViewModelFactory(appContainer.getRecipeRepository());
        MenuViewModelFactory menuViewModelFactory = new MenuViewModelFactory(appContainer.getMenuRepository());
        recipeViewModel = new ViewModelProvider(this, recipeViewModelFactory).get(RecipeViewModel.class);
        menuViewModel = new ViewModelProvider(this, menuViewModelFactory).get(MenuViewModel.class);
    }


}