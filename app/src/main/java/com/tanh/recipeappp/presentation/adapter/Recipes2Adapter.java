package com.tanh.recipeappp.presentation.adapter;

import android.annotation.SuppressLint;
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
import com.tanh.recipeappp.presentation.home.RecipeViewModel;

import java.util.ArrayList;
import java.util.List;

public class Recipes2Adapter extends RecyclerView.Adapter<Recipes2Adapter.MyViewHolder> {

    private RecipeViewModel recipeViewModel;
    private List<Recipe> recipes = new ArrayList<>();

    public Recipes2Adapter(RecipeViewModel recipeViewModel, List<Recipe> recipes) {
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
    public Recipes2Adapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(
                LayoutInflater.from(parent.getContext()).inflate(R.layout.recipe2_item, parent, false)
        );
    }

    @Override
    public void onBindViewHolder(@NonNull Recipes2Adapter.MyViewHolder holder, int position) {
        Recipe currentRecipe = recipes.get(position);
        TextView tv = holder.itemView.findViewById(R.id.ri_title2);
        ImageView image = holder.itemView.findViewById(R.id.ri_dish2);
        tv.setText(currentRecipe.getTitle());

        Log.d("recipe", currentRecipe.getImageUrl());
        Glide.with(holder.itemView.getContext())
                .load(currentRecipe.getImageUrl())
                .into(image);
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
