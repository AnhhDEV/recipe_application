package com.tanh.recipeappp.data.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {Recipe.class}, version = 1)
public abstract class MDatabase extends RoomDatabase {

    public abstract RecipeDao dao();

}
