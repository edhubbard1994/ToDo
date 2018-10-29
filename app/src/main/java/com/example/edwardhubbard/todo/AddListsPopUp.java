package com.example.edwardhubbard.todo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.io.Serializable;

/*
Prepare: this Activity has one EditText and one button. Also a list object to create and send back to MainActivity

Input: The user enters the desired name of the list that will be created;

Process: when the button is clicked, the new list object is created and put into an Intent

Output: the output is the new list objet to be sent back to MainActivity to be processed

 */
public class AddListsPopUp extends AppCompatActivity {
    //View Properties
    private Button addBtn;
    private EditText name;
    //custom property
    private List list;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_lists_pop_up);
        makePopUp();
        renderName();
        renderBtn();

    }
    // changes window dimensions to make it appear as a popup
    protected void makePopUp(){
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        int height = dm.heightPixels;
        int width = dm.widthPixels;
        getWindow().setLayout( (int)(width*.8), (int)(height*.8));
    }
    //initializes the name property
    protected void renderName(){
        name = findViewById(R.id.Name);
    }
    //initializes the add button  and sets click listener to create list object and send back to MainActivity via intent
    protected void renderBtn(){
        addBtn = findViewById(R.id.addListBtn);
        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                list =  new List(name.getText().toString(),0);
                Intent toMain = new Intent();
                toMain.putExtra("NEWLIST",list);
                setResult(RESULT_OK,toMain);
                finish();

            }
        });
    }
}
