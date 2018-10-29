package com.example.edwardhubbard.todo;

import java.io.Serializable;
import java.util.ArrayList;
/*
Prepare: this is a custom class that holds all of the list objects. it has a default constructor

Input: the input is a list object in either createList or addList;

Process: the list objects are stored in an array list called Lists

Output: the getter methods return either a single lists or all of the lists
 */
public class ListsContainer implements Serializable {
    // Array List for lists
    private ArrayList<List> lists;

    //Constructor
    public ListsContainer(){
        lists = new ArrayList<List>();
    }

    //creates a new list and adds it to ArrayList
    public void createList(String name){
        List temp = new List(name,lists.size());
        lists.add(temp);
    }
    //adds an existing list to ArrayList
    public void addList(List list){
        lists.add(list);
    }
    //gets a single list by its ArrayList index
    public List getListIndex(int index){
        return lists.get(index);
    }
    // gets the entire arrayList
    public ArrayList<List> getLists() {
        return lists;
    }
}
