package com.tanh.recipeappp.data.database;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = {Recipe.class}, version = 1)
public abstract class MDatabase extends RoomDatabase {

    public abstract RecipeDao dao();

    static final Migration MIGRATION_1_2 = new Migration(1, 2) {

        @Override
        public void migrate(@NonNull SupportSQLiteDatabase supportSQLiteDatabase) {
            supportSQLiteDatabase.execSQL("ALTER TABLE recipe ADD COLUMN isFavorite INTEGER NOT NULL DEFAULT 0");
        }
    };

}
