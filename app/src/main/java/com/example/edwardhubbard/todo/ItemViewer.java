package com.example.edwardhubbard.todo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
/*
Prepare: Five text views, one button , and a custom Item object.

Input: the input for this is an item sent via Intent object and is stored in thisItem

Process: when thisItem gets the item from the intent, the views use the properties to
         populate themselves to show the user information about the item clicked on

Output: When back is pressed the view returns to SingleListView
 */

public class ItemViewer extends AppCompatActivity {
    //View Properties
    private TextView iName;
    private TextView iDate;
    private TextView iStart;
    private TextView iEnd;
    private TextView iDetails;
    private Button backBtn;
    //custom property
    private Item thisItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_viewer);

        getExtra();

        iName = findViewById(R.id.iName);
        iName.setText(thisItem.getItemName());

        iDate = findViewById(R.id.iDate);
        iDate.setText(thisItem.getItemDate());

        iStart = findViewById(R.id.iStart);
        iStart.setText(thisItem.getItemStartTime());

        iEnd = findViewById(R.id.iEnd);
        iEnd.setText(thisItem.getItemEndTime());

        iDetails = findViewById(R.id.iDetails);
        iDetails.setText(thisItem.getItemDetails());

        initailizeBackBtn();

    }
    // helper method to get the Item object from intent
    private void getExtra(){
        thisItem = (Item) getIntent().getSerializableExtra("ITEM");
    }
    //initializes back button and sets a listener for a click and returns the user to SingleListView
    protected void initailizeBackBtn(){
        backBtn = findViewById(R.id.backBtn);
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent goBack = new Intent();
                setResult(RESULT_OK,goBack);
                goBack.putExtra("I",thisItem);
                finish();
            }
        });
    }


}
