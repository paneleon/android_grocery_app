package com.example.grocery_bud;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class FoodCategoryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_category);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // inflate menu of food categories
        getMenuInflater().inflate(R.menu.food_categories, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent intent = new Intent(this, FoodCategoryItemsActivity.class);

        // check which category was selected from the menu
        switch (item.getItemId()) {
            case R.id.fruitsVegetables:
                // store that value by "category" key in intent extra
                intent.putExtra("category", "fruitsVegetables");
                break;

            case R.id.grainsCereals:
                intent.putExtra("category", "grainsCereals");
                break;

            case R.id.meatSeaPoultry:
                intent.putExtra("category", "meatSeaPoultry");
                break;

            case R.id.dairy:
                intent.putExtra("category", "dairy");
                break;
            case R.id.baked:
                intent.putExtra("category", "baked");
                break;
            default:
                return super.onOptionsItemSelected(item);

        }
        startActivity(intent);
        return true;
    }

    // event handler for Back button
    public void onBackClick(View view){
        // create intent for Main Activity
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}