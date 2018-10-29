package com.example.edwardhubbard.todo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
/*
Entry Point of application.

Prepare:The main view has one button and one list view. the list view shows
        all of the lists that the user created and the button allows the user ot add more.
        an ArrayAdapter of Strings shows the lists by the list name in the list view.
        a n object of custom type ListContainer contains all of the lists and items within.

Input: the input for this class is either the user pressing the add button or one of the lists
        in the list view.

Process: based on the users selection, This Activity will create an Intent wich will fire the
         appropriate next activity to either create a new list or view an existing one.

Output: the output for this class is the appropriate Activity for the users selection. Also
        an output could be the array adapter populating the ListView
 */
public class MainActivity extends AppCompatActivity {
    // class View properties
    private ListView lists;
    private Button addBtn;
    private ArrayAdapter<String> populateLists;
    //class custom properties
    private ListsContainer toDoLists;
    private int count = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lists = (ListView) findViewById(R.id.Lists);
        initializeLists();
        initializeAddBtn();

    }
    // creates the add btn and sets an event listener for a click to fire the AddListPopup
    protected void initializeAddBtn(){
        addBtn = (Button) this.findViewById(R.id.addBtn);
        addBtn.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent addPopUp = new Intent(getApplicationContext(),AddListsPopUp.class);
                        startActivityForResult(addPopUp,1);

                    }
                }
        );
    }
    // initializes the single ListContainer Object
    protected void initializeLists() {
        this.toDoLists = new ListsContainer();
    }

    // Creates the array adapter for the List View and creates a click event listener for each list element which will fire SingleListView
    protected void createAdapter(){
        populateLists = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, getNames());
        lists.setAdapter(populateLists);

        lists.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent goToList = new Intent(getApplicationContext(),SingleListView.class);
                goToList.putExtra("THISLIST",toDoLists.getListIndex(i));
                startActivityForResult(goToList,2);

            }
        }
        );
    }

    //Gets the results of ether SingleListView or AddListsPopUp
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            if(resultCode == RESULT_OK) {
                List temp =(List) data.getSerializableExtra("NEWLIST");
                toDoLists.addList(temp);
                createAdapter();
            }else if(requestCode==2) {
                if (resultCode == RESULT_OK) {
                    List temp = (List) data.getSerializableExtra("LIST");
                    toDoLists.getListIndex(temp.getIndex()).updateList(temp);
                    Log.d("NEWLIST",toDoLists.getListIndex(temp.getIndex()).getItem(0).getItemName());
                    createAdapter();
                }
            }

        }
    }
    // Helper method for Array adapter which creates an array of list names
    private String[] getNames(){
        String[] arr = new String[toDoLists.getLists().size()];
        for(int i =0;i<toDoLists.getLists().size();i++){
            arr[i] = toDoLists.getListIndex(i).getListName();
        }
        return arr;
    }
}
