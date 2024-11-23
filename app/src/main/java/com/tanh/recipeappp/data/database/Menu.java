package com.tanh.recipeappp.data.database;

import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import com.tanh.recipeappp.converter.Converter;

import java.util.List;

@Entity(tableName = "menus")
public class Menu {

    @PrimaryKey(autoGenerate = true)
    Integer id;
    private List<Recipe> breakfast;
    private List<Recipe> lunch;
    private List<Recipe> dinner;
    private String date;

    public Menu() {}

    public Menu(List<Recipe> breakfast, List<Recipe> lunch, List<Recipe> dinner, String date) {
        this.breakfast = breakfast;
        this.lunch = lunch;
        this.dinner = dinner;
        this.date = date;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<Recipe> getBreakfast() {
        return breakfast;
    }

    public void setBreakfast(List<Recipe> breakfast) {
        this.breakfast = breakfast;
    }

    public List<Recipe> getLunch() {
        return lunch;
    }

    public void setLunch(List<Recipe> lunch) {
        this.lunch = lunch;
    }

    public List<Recipe> getDinner() {
        return dinner;
    }

    public void setDinner(List<Recipe> dinner) {
        this.dinner = dinner;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
