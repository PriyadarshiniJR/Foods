package com.example.android.foods;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by J L Raveendran on 08-Sep-17.
 */

public class Recipe implements Parcelable{
    private String title;
    private int prepTime;
    private int cookTime;
    private int calories;
    private String category;
    private String method;
    private String ingredients;
    private String imageURL;
    public Recipe(){

    }

    public Recipe(String title, int prepTime, int cookTime, int calories, String category,String method, String ingredients,String imageResource) {
        this.title = title;
        this.prepTime = prepTime;
        this.cookTime = cookTime;
        this.calories = calories;
        this.category = category;
        this.method = method;
        this.ingredients = ingredients;
        this.imageURL = imageResource;
    }

    public String getImageURL(){return imageURL;}

    public void setImageURL(String imageURL) {this.imageURL = imageURL;}

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getPrepTime() {
        return prepTime;
    }

    public void setPrepTime(int prepTime) {
        this.prepTime = prepTime;
    }

    public int getCookTime() {
        return cookTime;
    }

    public void setCookTime(int cookTime) {
        this.cookTime = cookTime;
    }

    public int getCalories() {
        return calories;
    }

    public void setCalories(int calories) {
        this.calories = calories;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getIngredients() {
        return ingredients;
    }

    public void setIngredients(String ingredients) {
        this.ingredients = ingredients;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeValue(this.calories);
        parcel.writeString(this.category);
        parcel.writeValue(this.cookTime);
        parcel.writeString(this.ingredients);
        parcel.writeString(this.method);
        parcel.writeValue(this.prepTime);
        parcel.writeString(this.title);
        parcel.writeString(this.imageURL);
    }

    protected Recipe(Parcel parcel) {
        this.calories = (Integer) parcel.readValue(Integer.class.getClassLoader());
        this.category = parcel.readString();
        this.cookTime = (Integer) parcel.readValue(Integer.class.getClassLoader());
        this.ingredients = parcel.readString();
        this.method = parcel.readString();
        this.prepTime = (Integer) parcel.readValue(Integer.class.getClassLoader());
        this.title = parcel.readString();
        this.imageURL = parcel.readString();
    }

    public static final Parcelable.Creator<Recipe> CREATOR = new Parcelable.Creator<Recipe>(){
        @Override
        public Recipe createFromParcel(Parcel parcel) {
            return new Recipe(parcel);
        }

        @Override
        public Recipe[] newArray(int size) {
            return new Recipe[size];
        }
    };
}
