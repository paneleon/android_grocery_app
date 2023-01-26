package com.example.grocery_bud;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.TextView;

public class CheckoutActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // display appropriate layout for activity
        setContentView(R.layout.activity_checkout);

        // get application preferences
        SharedPreferences itemsPreference = getSharedPreferences("SelectedFoodItems", MODE_PRIVATE);
        // retrieve keys of selected stored in application preferences
        Object[] selectedItems = itemsPreference.getAll().keySet().toArray();
        // find the Text View to display information
        TextView checkout = (TextView) findViewById(R.id.checkout);

        float total = 0f;
        // go through all keys stored in shared preferences
        for (int i = 0; i < selectedItems.length; i++) {
            // append key (item) to TextView
            checkout.append(selectedItems[i] + " \t\t$");
            // append value found by key (price of item) to TextView
            checkout.append(itemsPreference.getFloat(selectedItems[i].toString(), 0) + " \n");

            // add price to total
            total += itemsPreference.getFloat(selectedItems[i].toString(), 0);
        }

        // find TextView and append total sum
        TextView totalSum = (TextView) findViewById(R.id.totalSum);
        totalSum.append(String.format("%.2f", total));
    }

    // event handler for Next Button
    public void onNextClick(View view){
        // find cards radio buttons
        RadioButton creditCardRadioButton = (RadioButton) findViewById(R.id.creditCard);
        RadioButton debitCardRadioButton = (RadioButton) findViewById(R.id.debitCard);

        Intent intent = null;
        // if either credit card or debit card radio button is selected
        if (creditCardRadioButton.isChecked() || debitCardRadioButton.isChecked()){
            // create intent for Payment Activity
            intent = new Intent(this, PaymentActivity.class);
        } else {
            // if cash is selected, create intent for Finalizing Order Activity
            intent = new Intent(this, FinalOrderActivity.class);
        }
        startActivity(intent);
    }

    // event handler for Back Button
    public void onBackClick(View view){
        // create intent for Food Category Activity
        Intent intent = new Intent(this, FoodCategoryActivity.class);
        startActivity(intent);
    }
}



