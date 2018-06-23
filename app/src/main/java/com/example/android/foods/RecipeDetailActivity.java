package com.example.android.foods;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class RecipeDetailActivity extends AppCompatActivity {

    String title;

    private static final String RECIPE_KEY = "recipeKey";
    public static void startActivity(Activity startingActivity, Recipe recipe) {
        Intent intent = new Intent(startingActivity, RecipeDetailActivity.class);
        intent.putExtra(RECIPE_KEY, recipe);
        startingActivity.startActivity(intent);
        if(startingActivity instanceof RecipeFinderActivity)
            startingActivity.finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_detail);

        //TextView recipeTitle = (TextView)findViewById(R.id.title);
        TextView recipeCookTime = (TextView)findViewById(R.id.cookTime);
        TextView recipePrepTime = (TextView)findViewById(R.id.prepTime);
        TextView recipeCalories = (TextView)findViewById(R.id.calories);
        TextView recipeCategory = (TextView)findViewById(R.id.category);
        TextView methodList = (TextView)findViewById(R.id.methodList);
        TextView recipeIngrdients = (TextView)findViewById(R.id.ingredients);
        ImageView recipeImage = (ImageView)findViewById(R.id.foodimage);

        if(getIntent() != null){
            Recipe recipe = getIntent().getParcelableExtra(RECIPE_KEY);
            //recipeImage.setImageIcon(recipe.getImageResource());
            Glide.with(this).load(recipe.getImageURL()).into(recipeImage);
            System.out.print("hey me:"+recipe.getImageURL());
            //recipeTitle.setText(recipe.getTitle());
            title = recipe.getTitle();
            recipeCookTime.setText(recipe.getCookTime()+" min");
            recipePrepTime.setText(recipe.getPrepTime()+" min");
            recipeCalories.setText(recipe.getCalories()+" cal");
            recipeCategory.setText(recipe.getCategory());
            methodList.setText(recipe.getMethod());
            recipeIngrdients.setText(recipe.getIngredients());
        }
        setTitle(title);
    }
}
