package com.tanh.recipeappp.converter;

import androidx.room.ProvidedTypeConverter;
import androidx.room.TypeConverter;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import com.tanh.recipeappp.data.database.Recipe;

import java.util.ArrayList;
import java.util.List;

@ProvidedTypeConverter
public class Converter {

    @TypeConverter
    public List<Recipe> fromListJson(String json) {
        if (json == null || json.isEmpty()) {
            return new ArrayList<>();
        }
        try {
            return new Gson().fromJson(
                    json,
                    new TypeToken<List<Recipe>>() {
                    }.getType()
            );
        } catch (JsonSyntaxException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    @TypeConverter
    public String toListJson(List<Recipe> list) {
        if (list == null || list.isEmpty()) {
            return "[]";
        }
        return new Gson().toJson(
                list,
                new TypeToken<List<Recipe>>() {
                }.getType()
        );
    }

}
