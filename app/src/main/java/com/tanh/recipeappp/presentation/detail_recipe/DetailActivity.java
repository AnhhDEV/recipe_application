package com.tanh.recipeappp.presentation.detail_recipe;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.bumptech.glide.Glide;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.tanh.recipeappp.R;
import com.tanh.recipeappp.RecipeApplication;
import com.tanh.recipeappp.databinding.ActivityDetailBinding;
import com.tanh.recipeappp.dependencies.AppContainer;
import com.tanh.recipeappp.factory.RecipeViewModelFactory;
import com.tanh.recipeappp.presentation.home.RecipeViewModel;

public class DetailActivity extends AppCompatActivity {

    ActivityDetailBinding binding;
    AppContainer appContainer;
    RecipeViewModel recipeViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //init
        binding = ActivityDetailBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        appContainer = ((RecipeApplication) getApplication()).getAppContainer();
        RecipeViewModelFactory factory = new RecipeViewModelFactory(appContainer.getRecipeRepository());
        recipeViewModel = new ViewModelProvider(this, factory).get(RecipeViewModel.class);

        //intent
        Intent intent = getIntent();
        Integer recipeId = intent.getIntExtra("recipeId", 0);
        //setdata

        recipeViewModel.getRecipeById(recipeId).observe(this, recipe -> {
            binding.tvIngredients.setText(recipe.getIngredients());
            binding.tvInstruction.setText(recipe.getInstruction());
            StringBuilder str = new StringBuilder();
            str.append("Cách làm ");
            str.append(recipe.getTitle());
            binding.tvDish.setText(str.toString());
            Glide.with(this)
                    .load(recipe.getImageUrl())
                    .into(binding.ivDetail);

            binding.tvDish.setTextSize(18);
            binding.tvIngredients.setTextSize(15);
            binding.tvInstruction.setTextSize(15);
        });

        //back
        binding.back1.setOnClickListener(view -> {
            finish();
        });
        
        //Share
        FloatingActionButton btnShare = findViewById(R.id.btn_share);
        btnShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                recipeViewModel.getRecipeById(recipeId).observe(DetailActivity.this, recipe -> {
                    if (recipe != null) {
                        // Tạo nội dung chia sẻ
                        String shareTitle = "Tên món ăn: " + recipe.getTitle();
                        String shareContent = "Tên món ăn: " + recipe.getTitle() + "\n"
                                + "Nguyên liệu: " + recipe.getIngredients() + "\n"
                                + "Cách làm: " + recipe.getInstruction();

                        // Hiển thị lựa chọn chia sẻ qua Email hoặc SMS
                        Intent chooserIntent = new Intent(Intent.ACTION_CHOOSER);
                        Intent emailIntent = new Intent(Intent.ACTION_SEND);
                        emailIntent.setType("text/plain");
                        emailIntent.putExtra(Intent.EXTRA_SUBJECT, shareTitle);
                        emailIntent.putExtra(Intent.EXTRA_TEXT, shareContent);

                        Intent smsIntent = new Intent(Intent.ACTION_VIEW);
                        smsIntent.setType("vnd.android-dir/mms-sms");
                        smsIntent.putExtra("sms_body", shareContent);

                        chooserIntent.putExtra(Intent.EXTRA_INTENT, emailIntent);
                        chooserIntent.putExtra(Intent.EXTRA_TITLE, "Share Recipe via");
                        chooserIntent.putExtra(Intent.EXTRA_INITIAL_INTENTS, new Intent[]{smsIntent});
                        startActivity(chooserIntent);
                    }
                });
            }
        });
    }


}