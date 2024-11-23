package com.tanh.recipeappp.data.repository;

import android.util.Log;

import androidx.lifecycle.LiveData;

import com.tanh.recipeappp.data.database.MDatabase;
import com.tanh.recipeappp.data.database.Menu;

import java.util.List;

public class MenuRepository {

    private final MDatabase mDatabase;

    public MenuRepository(MDatabase mDatabase) {
        this.mDatabase = mDatabase;
    }

    public LiveData<Menu> getMenuById(Integer id) {
        return mDatabase.menuDao().getMenuById(id);
    }

    public LiveData<List<Menu>> getMenus() {
        return mDatabase.menuDao().getAllMenus();
    }

    public void insertMenu(Menu menu) {
        mDatabase.menuDao().insertMenu(menu);
    }


    public void deleteMenu(Menu menu) {
        mDatabase.menuDao().deleteMenu(menu);
    }

}
