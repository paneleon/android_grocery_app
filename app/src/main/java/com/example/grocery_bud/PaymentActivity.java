package com.example.grocery_bud;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class PaymentActivity extends AppCompatActivity {

    boolean validationPassed = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);
    }

    // event handler for Next button
    public void onNextClick(View view){
        // validate input text fields
        validationPassed = CheckInputFields();

        // if validation passed
        if (validationPassed){
            // get shared preferences for payment info
            SharedPreferences sharedPreference = getSharedPreferences("PaymentInfo", 0);
            // initialize preference editor
            SharedPreferences.Editor prefEditor = sharedPreference.edit();

            // get text values of input fields
            String name = ((EditText)findViewById(R.id.fullName)).getText().toString();
            String address = ((EditText)findViewById(R.id.address)).getText().toString();
            String cardNumber = ((EditText)findViewById(R.id.cardNumber)).getText().toString();
            String cardCVV = ((EditText)findViewById(R.id.cardCVV)).getText().toString();
            String phone = ((EditText)findViewById(R.id.phoneNumber)).getText().toString();
            String email = ((EditText)findViewById(R.id.emailAddress)).getText().toString();
            String comments = ((EditText)findViewById(R.id.comments)).getText().toString();
            String question1 = ((EditText)findViewById(R.id.question1Answer)).getText().toString();
            String question2 = ((EditText)findViewById(R.id.questions2Answer)).getText().toString();

            // store payment info in memory
            prefEditor.putString("fullName", name);
            prefEditor.putString("address", address);
            prefEditor.putString("cardNumber", cardNumber);
            prefEditor.putString("cardCVV", cardCVV);
            prefEditor.putString("phoneNumber", phone);
            prefEditor.putString("emailAddress", email);
            prefEditor.putString("comments", comments);
            prefEditor.putString("question1Answer", question1);
            prefEditor.putString("questions2Answer", question2);

            // commit the transaction
            prefEditor.commit();

            // create intent for Finalizing the order
            Intent intent = new Intent(this, FinalOrderActivity.class);
            startActivity(intent);
        } else {
            // if validation did not pass, let user know
            Toast.makeText(this, "Please provide the required information", Toast.LENGTH_LONG).show();
        }
    }

    // event handler for Back button
    public void onBackClick(View view){
        // create intent for Checkout screen
        Intent intent = new Intent(this, CheckoutActivity.class);
        startActivity(intent);
    }

    // text input validation method
    private boolean CheckInputFields() {
        // get all EditText fields by id
        EditText name = (EditText)findViewById(R.id.fullName);
        EditText address = (EditText)findViewById(R.id.address);
        EditText cardNumber = (EditText)findViewById(R.id.cardNumber);
        EditText cardCVV = (EditText)findViewById(R.id.cardCVV);
        EditText phone = (EditText)findViewById(R.id.phoneNumber);
        EditText email = (EditText)findViewById(R.id.emailAddress);
        EditText question1 = (EditText)findViewById(R.id.question1Answer);
        EditText question2 = (EditText)findViewById(R.id.questions2Answer);

        // check that full name is not empty
        if (name.getText().length() == 0) {
            name.setError("Full name is required");
            return false;
        }
        // check that address is not empty
        if (address.getText().length() == 0) {
            address.setError("Address is required");
            return false;
        }

        // check that card number is 16 digits
        if (cardNumber.getText().length() != 16) {
            cardNumber.setError("Card number is required and must be 16 digits");
            return false;
        }

        // check that CVV of card is 3 digits
        if (cardCVV.getText().length() != 3) {
            cardCVV.setError("Card CVV must be 3 digits");
            return false;
        }

        // check that phone number is not empty
        if (phone.getText().length() == 0) {
            phone.setError("Phone number is required");
            return false;
        } else if (phone.getText().length() < 8) {
            // check that phone number is at least 8 digits
            phone.setError("A valid phone number must be at least 8 digits");
            return false;
        }

        // check that email address is not empty
        if (email.getText().length() == 0) {
            email.setError("Email address is required");
            return false;
            // check that email address matches the pattern
        } else if (!Patterns.EMAIL_ADDRESS.matcher(email.getText()).matches()){
            email.setError("Please provide a valid email address");
            return false;
        }

        // check that optional questions answers are valid if provided
        if (question1.getText().length() != 0 && question1.getText().length() < 3) {
            question1.setError("Please provide a valid answer");
            return false;
        }

        if (question2.getText().length() != 0 && question2.getText().length() < 3) {
            question2.setError("Please provide a valid answer");
            return false;
        }

        // return true if all validation passes
        return true;
    }
}