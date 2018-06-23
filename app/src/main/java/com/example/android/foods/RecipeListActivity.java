package com.example.android.foods;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RecipeListActivity extends AppCompatActivity {

    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference mRecipeDatabaseReference;
    private RecyclerView mRecyclerView;
    private RecipeListAdapter mRecipeListAdapter;
    private ChildEventListener mChildEventListener;

    public static void startActivity(Activity startingActivity) {
        Intent intent = new Intent(startingActivity, RecipeListActivity.class);
        startingActivity.startActivity(intent);

        //To clear the stack, so that the user cannot go back to the authentication activity on hardware back press
        startingActivity.finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_list);

        mFirebaseDatabase = FirebaseDatabase.getInstance();
        mRecipeDatabaseReference = mFirebaseDatabase.getReference();

        mRecipeListAdapter = new RecipeListAdapter(new RecipeListAdapter.Helper(){
            @Override
            public void onRecipeClicked(Recipe recipe) {
                RecipeDetailActivity.startActivity(RecipeListActivity.this,recipe);
            }
        });
        mRecyclerView = (RecyclerView)findViewById(R.id.recyclerview);
        mRecyclerView.setAdapter(mRecipeListAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        attachDatabaseListener();
    }

    private void attachDatabaseListener(){
        if(mChildEventListener == null){
            mChildEventListener = new ChildEventListener() {
                @Override
                public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                    Recipe recipe = dataSnapshot.getValue(Recipe.class);
                    mRecipeListAdapter.add(recipe);
                }
                @Override
                public void onChildChanged(DataSnapshot dataSnapshot, String s) {}
                @Override
                public void onChildRemoved(DataSnapshot dataSnapshot) {
                }
                @Override
                public void onChildMoved(DataSnapshot dataSnapshot, String s) {}
                @Override
                public void onCancelled(DatabaseError databaseError) {}
            };
            mRecipeDatabaseReference.addChildEventListener(mChildEventListener);
        }
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu options from the res/menu/menu_catalog.xml file.
        // This adds menu items to the app bar.
        getMenuInflater().inflate(R.menu.menu_search, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // User clicked on a menu option in the app bar overflow menu
        switch (item.getItemId()) {
            // Respond to a click on the "Insert dummy data" menu option
            case R.id.action_search:
                Intent intent = new Intent(this,RecipeFinderActivity.class);
                startActivity(intent);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
