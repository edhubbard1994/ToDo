package com.example.edwardhubbard.todo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.view.menu.MenuView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
/*
Prepare: This class allows the user to either add an item, view an existing item or go back
         to the MainActivity. it has two buttons and one ListView

Input: The input is the List sent by intent from MainActivity to this class.

Process: Depending on the users choice A different Activity will fire to either create an item,go back,
         or see an existing item

Output: the output is the Called activity or the ArrayList adapter
 */
public class SingleListView extends AppCompatActivity {
    //custom property
    private List thisList;
    //View Properties
    private ArrayAdapter<String> populateItems;
    private ListView items;
    private Button addItemBtn;
    private Button backBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_list_view);
        items = findViewById(R.id.items);
        initializeList();
        initialzeBtn();
        initializeBackBtn();
    }

    // gets the current list from Main by Intent
    protected void initializeList(){

        thisList = (List) getIntent().getSerializableExtra("THISLIST");

    }
    //creates add button and sets listener to AddItemPopUp when clicked
    protected void initialzeBtn(){
        addItemBtn = findViewById(R.id.button);
        addItemBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent addItem = new Intent(getApplicationContext(),AddItemPopUp.class);
                startActivityForResult(addItem,3);

            }
        });
    }
    // Initializes back Button and sets Listener to finish activity and return updated list to MainActivity
    protected void initializeBackBtn(){
        backBtn = findViewById(R.id.backBtn);
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent goBack = new Intent();
                goBack.putExtra("LIST",thisList);
                setResult(RESULT_OK,goBack);
                finish();
            }
        });
    }
    // Adapts array of item names to the ListView and sets click listener to call ItemViewer
    protected void createAdapter(){
        populateItems = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, getNames());
        items.setAdapter(populateItems);
        items.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent viewItem = new Intent(getApplicationContext(), ItemViewer.class);
                viewItem.putExtra("ITEM",thisList.getItem(i));
                startActivityForResult(viewItem,4);
            }
        });
    }

    // gets informatin returned from ItemViewer or AddItemPopup, ItemViewer does nothing
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 3) {
            if(resultCode == RESULT_OK) {
                Item temp = (Item) data.getSerializableExtra("NEWITEM");
                thisList.addItem(temp);
                createAdapter();
            }else if (requestCode == 4) {
                if (resultCode == RESULT_OK) {
                    //does nothing because there is no data to return but need to maintain current data
                }
            }
        }
    }
    //Helper method for array adapter to get all the item names
    private String[] getNames(){
        String[] arr = new String[thisList.getItems().size()];
        for(int i=0;i<thisList.getItems().size();i++){
            arr[i] = thisList.getItem(i).getItemName();
        }
        return arr;
    }


}
