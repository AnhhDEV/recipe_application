package com.tanh.recipeappp.converter;

import androidx.room.ProvidedTypeConverter;
import androidx.room.TypeConverter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.tanh.recipeappp.data.database.Recipe;

import java.util.List;

@ProvidedTypeConverter
public class Converter {

    @TypeConverter
    public List<Recipe> fromListJson(String json) {
        return new Gson().fromJson(
                json,
                new TypeToken<List<Recipe>>(){}.getType()
        );
    }

    @TypeConverter
    public String toListJson(List<Recipe> list) {
        return new Gson().toJson(
                list,
                new TypeToken<List<Recipe>>(){}.getType()
        );
    }

}
