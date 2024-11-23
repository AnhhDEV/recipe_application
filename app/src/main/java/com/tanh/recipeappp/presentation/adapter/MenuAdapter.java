package com.tanh.recipeappp.presentation.adapter;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.snackbar.Snackbar;
import com.tanh.recipeappp.R;
import com.tanh.recipeappp.data.database.Menu;
import com.tanh.recipeappp.data.database.Recipe;
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

    @SuppressLint({"ShowToast", "NotifyDataSetChanged"})
    @Override
    public void onBindViewHolder(@NonNull MenuAdapter.MyViewHolder holder, int i) {


        Menu currentMenu = list.get(i);
        int position = holder.getAdapterPosition();
        //up  data
        TextView breakfast = holder.itemView.findViewById(R.id.tv_breakfast);
        TextView launch = holder.itemView.findViewById(R.id.tv_launch);
        TextView dinner = holder.itemView.findViewById(R.id.tv_dinner);
        TextView date = holder.itemView.findViewById(R.id.tv_date);
//
//        breakfast.setText(getRecipes(currentMenu.getBreakfast()));
//        launch.setText(getRecipes(currentMenu.getLunch()));
//        dinner.setText(getRecipes(currentMenu.getDinner()));
        date.setText(currentMenu.getDate());

        //delete
        holder.itemView.findViewById(R.id.btn_delete).setOnClickListener(view -> {
            menuViewModel.deleteMenu(currentMenu);
            list.remove(position);
            notifyItemRemoved(position);

            Snackbar.make(view, "Xóa thành công", Snackbar.LENGTH_LONG)
                    .setAction("Undo", new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            menuViewModel.insertMenu(currentMenu);
                            list.add(position, currentMenu);
                            notifyItemInserted(position);
                        }
                    });
        });

    }

    private String getRecipes(List<Recipe> list) {
        StringBuilder sb = new StringBuilder();
        for(Recipe recipe : list) {
            sb.append(recipe.getTitle());
            sb.append(", ");
        }
        sb.deleteCharAt(sb.length() - 1);
        sb.deleteCharAt(sb.length() - 1);
        return sb.toString();
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
