package com.example.android.foods;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

public class RecipeFinderActivity extends AppCompatActivity {
    String text[]= new String[20];
    String ing1=" ",ing2=" ",ing3=" ";

    int i = 0,flag=0;

    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference mRecipeDatabaseReference;
    private ChildEventListener mChildEventListener;
    private Query mQuery;
    private static final String RECIPE_KEY = "recipeKey";

    public static void startActivity(Activity startingActivity) {
        Intent intent = new Intent(startingActivity, RecipeFinderActivity.class);
        startingActivity.startActivity(intent);

        //To clear the stack, so that the user cannot go back to the authentication activity on hardware back press
        startingActivity.finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_finder);

        AutoCompleteTextView textView = (AutoCompleteTextView) findViewById(R.id.ingr_available);
        String[] countries = getResources().getStringArray(R.array.ingredients_allowed);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, countries);
        textView.setAdapter(adapter);

        mFirebaseDatabase = FirebaseDatabase.getInstance();
        mRecipeDatabaseReference = mFirebaseDatabase.getReference();

        Button buttonFind = (Button) findViewById(R.id.finder_btn);
        buttonFind.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TextView ingredient = (TextView) findViewById(R.id.ingr_available);
                text[i++] = String.valueOf(ingredient.getText());
                ingredient.setText("");
            }
        });

        Button buttonFinish = (Button) findViewById(R.id.finish_btn);
        buttonFinish.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                if(text[0]!=null && !text[0].isEmpty()) ing1=text[0];
                if(text[1]!=null && !text[1].isEmpty()) ing2=text[1];
                if(text[2]!=null && !text[2].isEmpty()) ing3=text[2];

                if(ing1!=" " || ing2!=" " || ing3!=" ") {
                    mRecipeDatabaseReference.addChildEventListener(new ChildEventListener() {
                        int i=0,ct=0;
                        Intent intent=new Intent(RecipeFinderActivity.this,SelectedListActivity.class);

                        @Override
                        public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                            DataSnapshot ing = dataSnapshot.child("ingredients");
                            String ing_value = ing.getValue().toString();
                            ct++;
                            if (ing_value.contains(ing1) && ing_value.contains(ing2) && ing_value.contains(ing3) && flag == 0) {
                                //System.out.print("hey:" + dataSnapshot.child("ingredients").toString());
                                Recipe recipe = dataSnapshot.getValue(Recipe.class);
                                intent.putExtra("R"+i,recipe);
                                i++;
                                intent.putExtra("count",i);
                            }
                            if(ct==8)

                                startActivity(intent);
                        }

                        @Override
                        public void onChildChanged(DataSnapshot dataSnapshot, String s) {

                        }

                        @Override
                        public void onChildRemoved(DataSnapshot dataSnapshot) {

                        }

                        @Override
                        public void onChildMoved(DataSnapshot dataSnapshot, String s) {

                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });
                }

                else{
                    Toast.makeText(RecipeFinderActivity.this, "Enter atleast one ingredient.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
