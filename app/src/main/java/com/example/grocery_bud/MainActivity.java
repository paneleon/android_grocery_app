package com.example.grocery_bud;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // clear shared preferences when the user is back on the main screen

        // get application preferences for selected food items
        SharedPreferences itemsPreference = getSharedPreferences("SelectedFoodItems", MODE_PRIVATE);
        // initialize the preferences editor
        SharedPreferences.Editor itemPrefEditor = itemsPreference.edit();
        // clear data
        itemPrefEditor.clear();
        // commit
        itemPrefEditor.commit();

        // get application preferences for payment information
        SharedPreferences paymentPreference = getSharedPreferences("PaymentInfo", MODE_PRIVATE);
        // initialize the preferences editor
        SharedPreferences.Editor paymentPrefEditor = paymentPreference.edit();
        // clear data
        paymentPrefEditor.clear();
        // commit
        paymentPrefEditor.commit();
    }

    // event handler for Enter button
    public void enterApp(View view) {
        // create intent for Food Category Screen
        Intent intent = new Intent(this, FoodCategoryActivity.class);
        startActivity(intent);
    }
}