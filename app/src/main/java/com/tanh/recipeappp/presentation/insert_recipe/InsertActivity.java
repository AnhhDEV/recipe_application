package com.tanh.recipeappp.presentation.insert_recipe;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.PickVisualMediaRequest;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModelProvider;

import com.tanh.recipeappp.R;
import com.tanh.recipeappp.RecipeApplication;
import com.tanh.recipeappp.data.database.Recipe;
import com.tanh.recipeappp.databinding.ActivityInsertBinding;
import com.tanh.recipeappp.dependencies.AppContainer;
import com.tanh.recipeappp.factory.RecipeViewModelFactory;
import com.tanh.recipeappp.presentation.home.MainActivity;
import com.tanh.recipeappp.presentation.home.RecipeViewModel;
import com.tanh.recipeappp.util.Category;

public class InsertActivity extends AppCompatActivity {

    ActivityInsertBinding binding;
    ActivityResultLauncher<PickVisualMediaRequest> pickMedia;
    AppContainer appContainer;
    RecipeViewModel recipeViewModel;
    Uri currentUri = null;
    int categoryId;
    private boolean isAddingRecipe = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setBinding();
        //init
        init();

        //đăng ký callback
        pickMedia = registerForActivityResult(new ActivityResultContracts.PickVisualMedia(), uri -> {
            if (uri != null) {
                binding.tvAnh.setImageURI(uri);
                currentUri = uri;
                isAddingRecipe = false;
            }
        });
        binding.spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                categoryId = position;
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        onImageSelected();
        insertRecipe();
        back();
    }

    private void back() {
        binding.back2.setOnClickListener(view -> {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        });
    }

    private void insertRecipe() {
        binding.btnSave.setOnClickListener(view -> {
            String ingredients = binding.etIngredients.getText().toString();
            String instruction = binding.etInstruction.getText().toString();
            String title = binding.etTitle.getText().toString();

            recipeViewModel.getMaxId().observe(this, currentId -> {
                if (isAddingRecipe) return;

                isAddingRecipe = true;
                Log.d("recipeId", "id " + currentId);
                Recipe recipe = new Recipe(currentId + 1, title, categoryId, ingredients, instruction, currentUri.toString(), false);
                recipeViewModel.insertRecipe(recipe);

            });

            binding.etTitle.setText("");
            binding.etIngredients.setText("");
            binding.etInstruction.setText("");

            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        });
    }

    private void onImageSelected() {
        binding.tvAnh.setOnClickListener(view -> {
            registerPicture();
        });
    }

    private void init() {
        appContainer = ((RecipeApplication) getApplication()).getAppContainer();
        RecipeViewModelFactory factory = new RecipeViewModelFactory(appContainer.getRecipeRepository());
        recipeViewModel = new ViewModelProvider(this, factory).get(RecipeViewModel.class);
    }

    private void registerPicture() {
        pickMedia.launch(new PickVisualMediaRequest.Builder()
                .setMediaType(ActivityResultContracts.PickVisualMedia.ImageOnly.INSTANCE)
                .build());
    }

    private void setBinding() {
        binding = ActivityInsertBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }
}