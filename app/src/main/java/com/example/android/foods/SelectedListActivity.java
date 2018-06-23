package com.example.android.foods;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SelectedListActivity extends AppCompatActivity {

    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference mRecipeDatabaseReference;
    private RecyclerView mRecyclerView;
    private RecipeListAdapter mRecipeListAdapter;
    private ChildEventListener mChildEventListener;

    public static void startActivity(Activity startingActivity) {
        Intent intent = new Intent(startingActivity, SelectedListActivity.class);
        startingActivity.startActivity(intent);

        //To clear the stack, so that the user cannot go back to the authentication activity on hardware back press
        startingActivity.finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selected_list);

        mFirebaseDatabase = FirebaseDatabase.getInstance();
        mRecipeDatabaseReference = mFirebaseDatabase.getReference();

        mRecipeListAdapter = new RecipeListAdapter(new RecipeListAdapter.Helper(){
            @Override
            public void onRecipeClicked(Recipe recipe) {
                RecipeDetailActivity.startActivity(SelectedListActivity.this,recipe);
            }
        });
        mRecyclerView = (RecyclerView)findViewById(R.id.recyclerviewTwo);
        mRecyclerView.setAdapter(mRecipeListAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        int count = getIntent().getIntExtra("count",0);
        for(int i=0;i<count;i++)
        {
            Recipe recipe = getIntent().getParcelableExtra("R"+i);
            mRecipeListAdapter.add(recipe);
        }
    }
}
