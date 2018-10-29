package com.example.edwardhubbard.todo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.time.LocalDate;

/*
Prepare: Creates five EditTexts with relevant keyboards to fill in properties of an item object. And
          one button to add the object and go back to the SingleListView

Input: the user inputs the prompted information which it then uses to create an Item object.

Process: an Item object item is created

Output: the object item is sent back to SingleListView to be placed in its list by Intent

 */
public class AddItemPopUp extends AppCompatActivity {
    //View Properties
    private EditText itemName;
    private EditText itemDate;
    private EditText itemStartTime;
    private EditText itemEndTime;
    private EditText itemDetails;
    private Button addButton;
    //custom properties
    private Item item;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_item_pop_up);
        makePopUp();
        itemName = findViewById(R.id.Name);
        itemDate = findViewById(R.id.date);
        itemStartTime = findViewById(R.id.itemStartTime);
        itemEndTime = findViewById(R.id.itemEndTime);
        itemDetails = findViewById(R.id.detailText);
        addButton = findViewById(R.id.addItemBtn);
        renderBtn();
    }
    //this method makes the window smaller to make it appear as a popup
    protected void makePopUp(){
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        int height = dm.heightPixels;
        int width = dm.widthPixels;
        getWindow().setLayout( (int)(width*.8), (int)(height*.8));
    }

    //initializes the item object to the EditText fields
    protected void initializeItem(){
        String name = itemName.getText().toString();
        String date =itemDate.getText().toString();
        String sTime = itemStartTime.getText().toString();
        String eTime = itemEndTime.getText().toString();
        String details = itemDetails.getText().toString();
        item = new Item(name,date,sTime,eTime,details);
    }

    // creates the add button and sets click listener to go back to SingleListView and send item via intent
    protected void renderBtn(){
        addButton = findViewById(R.id.addItemBtn);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                initializeItem();
                Intent toList = new Intent(getApplicationContext(),SingleListView.class);
                toList.putExtra("NEWITEM",item);
                setResult(RESULT_OK,toList);
                finish();
            }
        });
    }
}
