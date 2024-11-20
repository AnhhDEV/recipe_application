package com.tanh.recipeappp.data.database;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "recipe")
public class Recipe {

    @PrimaryKey
    Integer id;
    String title;
    int category;
    String ingredients;
    String instruction;
    String imageUrl;
    Boolean isFavorite;

    public Recipe() {}

    public Recipe(Integer id, String title, int category, String ingredients, String instruction, String imageUrl, Boolean isFavorite) {
        this.id = id;
        this.title = title;
        this.category = category;
        this.ingredients = ingredients;
        this.instruction = instruction;
        this.imageUrl = imageUrl;
        this.isFavorite = isFavorite;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getCategory() {
        return category;
    }

    public void setCategory(int category) {
        this.category = category;
    }

    public String getIngredients() {
        return ingredients;
    }

    public void setIngredients(String ingredients) {
        this.ingredients = ingredients;
    }

    public String getInstruction() {
        return instruction;
    }

    public void setInstruction(String instruction) {
        this.instruction = instruction;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Boolean getFavorite() {
        return isFavorite;
    }

    public void setFavorite(Boolean favorite) {
        isFavorite = favorite;
    }

}