package com.example.grocery_bud;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class FinalOrderActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // display appropriate layout for activity
        setContentView(R.layout.activity_final_order);

        // initialize application preferences to retrieve payment and items information
        SharedPreferences paymentPreference = getSharedPreferences("PaymentInfo", 0);
        SharedPreferences itemsPreference = getSharedPreferences("SelectedFoodItems", 0);

        // get keys from shared preferences storing selected items
        Object[] selectedItems = itemsPreference.getAll().keySet().toArray();

        // initialize a string builder object to build a string of all selected items and prices
        StringBuilder itemsInfoString = new StringBuilder();

        float total = 0f;
        float itemPrice;
        // go through all keys in shared preferences of selected items and append to a strin
        for (int i = 0; i < selectedItems.length; i++) {
            // append item to string
            itemsInfoString.append(selectedItems[i] + " \t\t$");
            // get price by key from shared preferences
            itemPrice = itemsPreference.getFloat(selectedItems[i].toString(), 0);
            // append to string to display in TextView
            itemsInfoString.append(itemPrice + " \n");
            // add to total sum
            total += itemPrice;
        }

        // find TextView of items by id
        TextView itemsInfoTextView = (TextView) findViewById(R.id.itemsInfo);
        // set string of all items as text
        itemsInfoTextView.setText(itemsInfoString);

        // find TextView of sum
        TextView finalSum = (TextView) findViewById(R.id.finalSum);
        // append total sum to text
        finalSum.append(String.format("%.2f", total));


        // get values from shared preferences storing payment information
        Object[] paymentInfo = paymentPreference.getAll().values().toArray();

        // check if payment information was provided
        if (paymentPreference.getAll().size() > 0){
            // find TextViews by id
            TextView nameTextView = (TextView) findViewById(R.id.fullNameFinal);
            TextView addressTextView = (TextView) findViewById(R.id.addressFinal);
            TextView cardTextView = (TextView) findViewById(R.id.cardInfoFinal);
            TextView phoneTextView = (TextView) findViewById(R.id.phoneNumberFinal);
            TextView emailTextView = (TextView) findViewById(R.id.emailFinal);

            // append information to TextViews
            nameTextView.append(paymentPreference.getString("fullName", ""));
            addressTextView.append(paymentPreference.getString("address", ""));
            cardTextView.append(paymentPreference.getString("cardNumber", "") + "  |  ");
            cardTextView.append(paymentPreference.getString("cardCVV", ""));
            phoneTextView.append(paymentPreference.getString("phoneNumber", ""));
            emailTextView.append(paymentPreference.getString("emailAddress", ""));

        } else {
            // if not provided, hide the payment information layout
            LinearLayout paymentInfoLayout = (LinearLayout) findViewById(R.id.paymentInfoLayout);
            paymentInfoLayout.setVisibility(View.INVISIBLE);
        }
    }

    // event handler for clicking back button
    public void onBackClick(View view){
        // create intent and go to Checkout activity
        Intent intent = new Intent(this, CheckoutActivity.class);
        startActivity(intent);
    }

    // event handler for clicking finalize button
    public void onFinalizeClick(View view){
        // create intent and go to Main activity
        Intent intent = new Intent(this, MainActivity.class);
        Toast.makeText(this, "You order is confirmed! \nThe items are about to be delivered.", Toast.LENGTH_LONG).show();
        startActivity(intent);
    }
}