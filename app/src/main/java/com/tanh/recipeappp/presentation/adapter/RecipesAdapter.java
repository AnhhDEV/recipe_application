package com.tanh.recipeappp.presentation.adapter;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.tanh.recipeappp.R;
import com.tanh.recipeappp.data.database.Recipe;
import com.tanh.recipeappp.presentation.detail_recipe.DetailActivity;
import com.tanh.recipeappp.presentation.home.RecipeViewModel;

import java.util.ArrayList;
import java.util.List;

public class RecipesAdapter extends RecyclerView.Adapter<RecipesAdapter.MyViewHolder> {

    private RecipeViewModel recipeViewModel;
    private List<Recipe> recipes = new ArrayList<>();

    public RecipesAdapter(RecipeViewModel recipeViewModel, List<Recipe> recipes) {
        this.recipes = recipes;
        this.recipeViewModel = recipeViewModel;
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        View itemView;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            this.itemView = itemView;
        }
    }

    @NonNull
    @Override
    public RecipesAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(
                LayoutInflater.from(parent.getContext()).inflate(R.layout.recipe_item, parent, false)
        );
    }

    @Override
    public void onBindViewHolder(@NonNull RecipesAdapter.MyViewHolder holder, int position) {
        Recipe currentRecipe = recipes.get(position);
        TextView tv = holder.itemView.findViewById(R.id.ri_title);
        ImageView image = holder.itemView.findViewById(R.id.ri_dish);
        tv.setText(currentRecipe.getTitle());

        Log.d("recipe", currentRecipe.getImageUrl());
        Glide.with(holder.itemView.getContext())
                .load(currentRecipe.getImageUrl())
                .into(image);
        image.setOnClickListener(view -> {
            Intent intent = new Intent(holder.itemView.getContext(), DetailActivity.class);
            intent.putExtra("recipeId", currentRecipe.getId());
            holder.itemView.getContext().startActivity(intent);
        });

        ImageView favorite = holder.itemView.findViewById(R.id.iv_favorite);
        if (currentRecipe.getFavorite()) {
            favorite.setImageResource(R.drawable.filled_favorite);
        } else {
            favorite.setImageResource(R.drawable.outlined_favorite);
        }

        favorite.setOnClickListener(view -> {
            boolean newFavoriteState = !currentRecipe.getFavorite();
            currentRecipe.setFavorite(newFavoriteState);

            recipeViewModel.updateRecipe(currentRecipe);

            if (newFavoriteState) {
                favorite.setImageResource(R.drawable.filled_favorite);
            } else {
                favorite.setImageResource(R.drawable.outlined_favorite);
            }

            notifyItemChanged(position);
        });
    }

    @Override
    public int getItemCount() {
        return recipes.size();
    }

    @SuppressLint("NotifyDataSetChanged")
    public void changeList(List<Recipe> list) {
        this.recipes = list;
        notifyDataSetChanged();
    }

}
