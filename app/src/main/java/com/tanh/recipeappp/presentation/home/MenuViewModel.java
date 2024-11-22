package com.tanh.recipeappp.presentation.home;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.tanh.recipeappp.data.database.Menu;
import com.tanh.recipeappp.data.repository.MenuRepository;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class MenuViewModel extends ViewModel {

    private final MenuRepository menuRepository;

    public MenuViewModel(MenuRepository menuRepository) {
        this.menuRepository = menuRepository;
    }

    public LiveData<Menu> getMenuById(Integer id) {
        return menuRepository.getMenuById(id);
    }

    public LiveData<List<Menu>> getMenus() {
        return menuRepository.getMenus();
    }

    public void insertMenu(Menu menu) {
        Executor executor = Executors.newSingleThreadExecutor();
        executor.execute(() -> {
           menuRepository.insertMenu(menu);
        });
    }

    public void deleteMenu(Menu menu) {
        Executor executor = Executors.newSingleThreadExecutor();
        executor.execute(() -> {
           menuRepository.deleteMenu(menu);
        });
    }



}
