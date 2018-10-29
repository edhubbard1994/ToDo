package com.example.edwardhubbard.todo;

import java.io.Serializable;
import java.util.ArrayList;
/*
Prepare: Has a name a container for items a boolean to indicate whether it has items
         and an int that stores its index in ListContainer ArrayList

Input: the input is a constructor that takes the name and index of ListContainer ArrayList

Process: Methods can store items in this lists ArrayList or get items, all items and update them
        from a new list

Output: the output is an item or all of them. or the list name
 */
public class List implements Serializable {
    //properties
    private String listName;
    private ArrayList<Item> items;
    private boolean hasItems;
    private int index;

    //Constructor that takes the name and index
    public List(String name,int ind){
        items = new ArrayList<Item>();
        listName = name;
        index = ind;
    }
    // adds an item to list
    public void addItem(Item item){
        items.add(item);
    }
    // gets a single item by index
    public Item getItem(int index){
        return items.get(index);
    }
    //returns all items
    public ArrayList<Item> getItems() {
        return items;
    }
    // gets the index of this list
    public int getIndex() {
        return index;
    }
    //updates this list by settig it equal to an updated one
    public void updateList(List newList){
        this.items = newList.getItems();
    }
    //returns the name of the list
    public String getListName(){
        return listName;
    }
}
