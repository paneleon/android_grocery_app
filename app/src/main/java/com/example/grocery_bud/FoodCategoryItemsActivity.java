package com.example.grocery_bud;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class FoodCategoryItemsActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_category_items);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            String category = extras.getString("category");

            String[] items = new String[]{};
            Number[] prices = new Number[]{};

            ImageView categoryLogo = (ImageView) findViewById(R.id.categoryLogo);

            switch (category){
                case "fruitsVegetables":
                    items = FoodCategoryItems.fruitsVegetables.keySet().toArray(new String[FoodCategoryItems.fruitsVegetables.keySet().size()]);
                    prices = FoodCategoryItems.fruitsVegetables.values().toArray(new Number[FoodCategoryItems.fruitsVegetables.values().size()]);
                    categoryLogo.setImageResource(R.drawable.fruits_vegetables);
                    break;
                case "grainsCereals":
                    items = FoodCategoryItems.grainsCereals.keySet().toArray(new String[FoodCategoryItems.grainsCereals.keySet().size()]);
                    prices = FoodCategoryItems.grainsCereals.values().toArray(new Number[FoodCategoryItems.grainsCereals.values().size()]);
                    categoryLogo.setImageResource(R.drawable.grains);
                    break;
                case "meatSeaPoultry":
                    items = FoodCategoryItems.meatSeaPoultry.keySet().toArray(new String[FoodCategoryItems.meatSeaPoultry.keySet().size()]);
                    prices = FoodCategoryItems.meatSeaPoultry.values().toArray(new Number[FoodCategoryItems.meatSeaPoultry.values().size()]);
                    categoryLogo.setImageResource(R.drawable.fish_meat);
                    break;
                case "dairy":
                    items = FoodCategoryItems.dairy.keySet().toArray(new String[FoodCategoryItems.dairy.keySet().size()]);
                    prices = FoodCategoryItems.dairy.values().toArray(new Number[FoodCategoryItems.dairy.values().size()]);
                    categoryLogo.setImageResource(R.drawable.dairy);
                    break;
                case "baked":
                    items = FoodCategoryItems.baked.keySet().toArray(new String[FoodCategoryItems.baked.keySet().size()]);
                    prices = FoodCategoryItems.baked.values().toArray(new Number[FoodCategoryItems.baked.values().size()]);
                    categoryLogo.setImageResource(R.drawable.baked_goods);
                    break;

            }

            SharedPreferences myPreference = getSharedPreferences("SelectedFoodItems", 0);

            // find the table layout
            TableLayout table = (TableLayout) findViewById(R.id.itemsLayout);

            // dynamically creating checkboxes and prices depending on user's choice of category
            for (int i = 0; i < items.length; i++) {

                // create new table row
                TableRow tableRow = new TableRow(this);

                // create new checkbox and set text to be the item's name
                CheckBox checkBox = new CheckBox(this);
                checkBox.setText(items[i]);

                // check if the item was selected
                checkBox.setChecked(myPreference.getAll().containsKey(items[i]));

                // create new textview and set text to be price of the item
                TextView priceText = new TextView(this);
                priceText.setText(String.format("$ %s", prices[i]));

                // add checkbox and text to new row
                tableRow.addView(checkBox);
                tableRow.addView(priceText);
                // and add the row to the table
                table.addView(tableRow);

                Float price = prices[i].floatValue();

                // add onClickListener method
                checkBox.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (checkBox.isChecked()){
                            addFoodItem(checkBox.getText().toString(), price);
                        }else{
                            removeFoodItem(checkBox.getText().toString());
                        }
                    }
                });
            }
        }
    }


    public void addFoodItem(String itemName, Float price){

        SharedPreferences myPreference = getSharedPreferences("SelectedFoodItems", 0);
        //prepare it for edit by creating and Edit object
        SharedPreferences.Editor prefEditor = myPreference.edit();

        //store a float in memory
        prefEditor.putFloat(itemName, price);

        //commit the transaction
        prefEditor.commit();

        System.out.println(myPreference.getAll().values());
    }

    public void removeFoodItem(String itemName){
        SharedPreferences myPreference = getSharedPreferences("SelectedFoodItems", 0);

        // prepare it for edit by creating and Edit object
        SharedPreferences.Editor prefEditor = myPreference.edit();

        // remove item from memory
        prefEditor.remove(itemName);

        // commit the transaction
        prefEditor.commit();

        System.out.println(myPreference.getAll().values());
    }

    public void onCheckoutClick(View view){
        Intent intent = new Intent(this, CheckoutActivity.class);
        startActivity(intent);
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
        Intent intent = new Intent(this, FoodCategoryActivity.class);
        startActivity(intent);
    }
}