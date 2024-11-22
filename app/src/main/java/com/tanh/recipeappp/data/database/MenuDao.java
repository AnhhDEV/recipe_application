package com.tanh.recipeappp.data.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface MenuDao {

    @Query("SELECT * FROM menus")
    LiveData<List<Menu>> getAllMenus();

    @Query("SELECT * FROM menus WHERE id=:id")
    LiveData<Menu> getMenuById(Integer id);

    @Insert
    void insertMenu(Menu menu);

    @Delete
    void deleteMenu(Menu menu);

}
