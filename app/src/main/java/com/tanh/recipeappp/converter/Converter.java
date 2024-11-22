package com.tanh.recipeappp.converter;

import androidx.room.ProvidedTypeConverter;
import androidx.room.TypeConverter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.List;

@ProvidedTypeConverter
public class Converter {

    @TypeConverter
    public List<Integer> fromListJson(String json) {
        return new Gson().fromJson(
                json,
                new TypeToken<List<Integer>>(){}.getType()
        );
    }

    @TypeConverter
    public String toListJson(List<Integer> list) {
        return new Gson().toJson(
                list,
                new TypeToken<List<Integer>>(){}.getType()
        );
    }

}
