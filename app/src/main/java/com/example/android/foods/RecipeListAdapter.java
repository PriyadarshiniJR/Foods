package com.example.android.foods;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by J L Raveendran on 09-Sep-17.
 */

public class RecipeListAdapter extends RecyclerView.Adapter<RecipeViewHolder> {

    List<Recipe> recipeList = new ArrayList<>();
    Helper helper;
    View view;

    public interface Helper {
        void onRecipeClicked(Recipe recipe);
    }

    public RecipeListAdapter(Helper helper) {
        this.helper = helper;
    }

    @Override
    public RecipeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recipe,parent,false);
        return new RecipeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecipeViewHolder holder, int position) {
        final int val = position;
        holder.titleTextView.setText(recipeList.get(position).getTitle());
        Glide.with(view.getContext()).load(recipeList.get(position).getImageURL()).into(holder.imageView);

        holder.cardview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                helper.onRecipeClicked(recipeList.get(val));
            }
        });
    }

    @Override
    public int getItemCount() {
        return recipeList.size();
    }

    public void add(Recipe data){
        this.recipeList.add(data);
        this.notifyDataSetChanged();
    }
}
