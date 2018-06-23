package com.example.android.foods;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by J L Raveendran on 09-Sep-17.
 */

public class RecipeViewHolder extends RecyclerView.ViewHolder {
    CardView cardview;
    ImageView imageView;
    TextView titleTextView, prepTimeTextView, prepTimeValueView, cookTimeTextView, cookTimeValueView;

    public RecipeViewHolder(View itemView) {
        super(itemView);

        titleTextView = (TextView)itemView.findViewById(R.id.titleTextView);
        imageView = (ImageView)itemView.findViewById(R.id.imageView);
        cardview = (CardView)itemView.findViewById(R.id.cardview);
    }
}
