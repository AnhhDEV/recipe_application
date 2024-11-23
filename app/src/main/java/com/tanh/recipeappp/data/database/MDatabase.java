package com.tanh.recipeappp.data.database;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverter;
import androidx.room.TypeConverters;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.tanh.recipeappp.converter.Converter;

@Database(entities = {Recipe.class, Menu.class}, version = 2)
@TypeConverters(Converter.class)
public abstract class MDatabase extends RoomDatabase {

    public abstract RecipeDao dao();
    public abstract MenuDao menuDao();

    public static final Migration MIGRATION_1_2 = new Migration(1, 2) {
        @Override
        public void migrate(@NonNull SupportSQLiteDatabase supportSQLiteDatabase) {
            supportSQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS `menus` (`id` INTEGER PRIMARY KEY AUTOINCREMENT, `breakfast` TEXT, `lunch` TEXT, `dinner` TEXT, `date` TEXT) ");
        }
    };


}
