package com.tanh.recipeappp.presentation.adapter;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.view.menu.MenuView;
import androidx.recyclerview.widget.RecyclerView;

import com.tanh.recipeappp.R;
import com.tanh.recipeappp.data.database.Menu;
import com.tanh.recipeappp.presentation.home.MenuViewModel;

import java.util.ArrayList;
import java.util.List;

public class MenuAdapter extends RecyclerView.Adapter<MenuAdapter.MyViewHolder> {

    private List<Menu> list = new ArrayList<>();
    private MenuViewModel menuViewModel = null;

    public MenuAdapter(List<Menu> list, MenuViewModel menuViewModel) {
        this.list = list;
        this.menuViewModel = menuViewModel;
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        View itemView;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            this.itemView = itemView;
        }
    }

    @NonNull
    @Override
    public MenuAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(
                LayoutInflater.from(parent.getContext()).inflate(R.layout.menu_item, parent, false)
        );
    }

    @Override
    public void onBindViewHolder(@NonNull MenuAdapter.MyViewHolder holder, int position) {
        Menu currentMenu = list.get(position);

    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    @SuppressLint("NotifyDataSetChanged")
    public void changeList(List<Menu> list) {
        this.list = list;
        notifyDataSetChanged();
    }

}
