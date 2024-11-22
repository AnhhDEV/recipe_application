package com.tanh.recipeappp.factory;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.tanh.recipeappp.data.repository.MenuRepository;
import com.tanh.recipeappp.presentation.home.MenuViewModel;

public class MenuViewModelFactory implements ViewModelProvider.Factory {

    private final MenuRepository menuRepository;

    public MenuViewModelFactory(MenuRepository menuRepository) {
        this.menuRepository = menuRepository;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
            return (T) new MenuViewModel(menuRepository);
    }
}

