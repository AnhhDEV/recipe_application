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
    @TypeConverters(Converter.class)
    private List<Integer> breakfast;
    @TypeConverters(Converter.class)
    private List<Integer> lunch;
    @TypeConverters(Converter.class)
    private List<Integer> dinner;
    private String date;

    public Menu() {}

    public Menu(List<Integer> breakfast, List<Integer> lunch, List<Integer> dinner, String date) {
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

    public List<Integer> getBreakfast() {
        return breakfast;
    }

    public void setBreakfast(List<Integer> breakfast) {
        this.breakfast = breakfast;
    }

    public List<Integer> getLunch() {
        return lunch;
    }

    public void setLunch(List<Integer> lunch) {
        this.lunch = lunch;
    }

    public List<Integer> getDinner() {
        return dinner;
    }

    public void setDinner(List<Integer> dinner) {
        this.dinner = dinner;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
